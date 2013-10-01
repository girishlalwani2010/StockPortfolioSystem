/**
 * 
 */
package com.stockportfoliosystem.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.stockportfoliosystem.bean.MailBean;
import com.stockportfoliosystem.entity.PortfolioDetails;
import com.stockportfoliosystem.entity.StockHistory;

import com.stockportfoliosystem.exception.StockPortfolioDaoException;
/**
 * @author Girish Lalwani
 * 
 */
public class PortfolioDAOImplementation implements PortfolioDAO {

	/**
	 * Instantiated the logger
	 */
	private Logger logger = LogManager
			.getLogger(PortfolioDAOImplementation.class.getName());
	/**
	 * Created string constant for storing the exception message
	 */
	private static final String EXCEPTIONMSG = "something went wrong during adding companies to portfolio";
	/**
	 * Autowired annotation For autowiring the session factory
	 */
	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * Created the reference of hibernate template
	 */
	private HibernateTemplate hibernateTemplate;

	/**
	 * This method is used for instantiating hibernate template
	 * 
	 * @param sessionFactory
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method is used for adding the records into portfolio of user which
	 * is currently loged in
	 * 
	 * @see com.stockportfoliosystem.dao.PortfolioDAO#addToPortfolio(com.stockportfoliosystem.entity.PortfolioDetails)
	 */
	public void addToPortfolio(PortfolioDetails portfolio)
			throws StockPortfolioDaoException {

		try {
			hibernateTemplate.save(portfolio);
		} catch (DataIntegrityViolationException e) {
			throw new StockPortfolioDaoException("company Already added", e);
		} catch (Exception e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		}

	}

	/**
	 * This method is used for fetching the portfolio by users email Id
	 * 
	 * @see com.stockportfoliosystem.dao.PortfolioDAO#getPortfolioByEmailId(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<PortfolioDetails> getPortfolioByEmailId(String emailStr)
			throws StockPortfolioDaoException {

		List<PortfolioDetails> portfolioList = new ArrayList<PortfolioDetails>();
		try {
			portfolioList = hibernateTemplate
					.find("from com.stockportfoliosystem.entity.PortfolioDetails portfolioDetails where portfolioDetails.portfolioCompoundKey.portfolioId=?",
							emailStr);

		} catch (HibernateException e) {
			throw new StockPortfolioDaoException(e.getMessage(), e);
		} catch (Exception e) {
			throw new StockPortfolioDaoException(e.getMessage(), e);
		}

		return (List<PortfolioDetails>) (((List<PortfolioDetails>) portfolioList));
	}

	/**
	 * This method is used for fetching the current price
	 * 
	 * @see com.stockportfoliosystem.dao.PortfolioDAO#getCurrentPriceById(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Float getCurrentPriceById(String companyId)
			throws StockPortfolioDaoException {

		try {
			List<StockHistory> historyList = hibernateTemplate
					.find("from StockHistory where companyId='" + companyId
							+ "' order by timeStamp desc");
			logger.debug("in portfolio Dao implementation latest price"
					+ ((StockHistory) historyList.get(0)).getPrice());
			return ((StockHistory) historyList.get(0)).getPrice();
		}

		catch (HibernateException e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);

		} catch (Exception e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		}
	}

	/**
	 * This method is used for submitting the percentage change
	 * 
	 * 
	 * @see com.stockportfoliosystem.dao.PortfolioDAO#submitPercentageChange(java.lang.String,
	 *      java.lang.Float, java.lang.String)
	 */
	public void submitPercentageChange(String companyId,
			Float percentageChange, String emailId)
			throws StockPortfolioDaoException {
		logger.debug(sessionFactory);
		Session session = sessionFactory.openSession();
		Query query = null;
		try {
			query = session
					.createQuery("update PortfolioDetails set percentageChange=:percentageChange where companyId=:companyId and portfolioId=:emailId");
		} catch (HibernateException e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		} catch (Exception e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		}

		query.setParameter("companyId", companyId);
		query.setParameter("percentageChange", percentageChange);

		query.setParameter("emailId", emailId);

		query.executeUpdate();
		session.close();

	}

	/**
	 * This method is used for fetching the users from db for which the
	 * percentage change varies above the specified limit
	 * 
	 * @see com.stockportfoliosystem.dao.PortfolioDAO#getMailInfo()
	 */
	public List<MailBean> getMailInfo() {
		// TODO Auto-generated method stub
		logger.debug("inside doa layer in getMailInfo()");

		List<MailBean> mailBeanList = new ArrayList<MailBean>();
		try {

			logger.debug("PortfolioDAOImplementation in getMailInfo()"
					+ sessionFactory);
			Session session = sessionFactory.openSession();
			Query query = session
					.createSQLQuery(
							"select * from stockdetails.PortfolioDetails where percentageChange!=0 and not(((trackedPrice-percentageChange)< (select Price from stockdetails.stockhistory where portfoliodetails.companyId=stockhistory.companyId order by timeStamp desc limit 1)) and  ((select Price from stockdetails.stockhistory where portfoliodetails.companyId=stockhistory.companyId order by timeStamp desc limit 1) < (trackedPrice+percentageChange)))")
					.addEntity(PortfolioDetails.class);
			@SuppressWarnings("unchecked")
			List<PortfolioDetails> portfolioList1 = (List<PortfolioDetails>) query
					.list();

			Iterator<PortfolioDetails> portfolioListIterator = portfolioList1
					.iterator();

			while (portfolioListIterator.hasNext()) {

				MailBean mailBean = new MailBean();

				PortfolioDetails portfolioDetails = portfolioListIterator
						.next();

				logger.debug(portfolioDetails.getPortfolioCompoundKey()
						.getPortfolioId());
				mailBean.setEmailId(portfolioDetails.getPortfolioCompoundKey()
						.getPortfolioId());

				mailBean.setPercentageVariation(portfolioDetails
						.getPercentageChange());
				logger.debug(portfolioDetails.getPercentageChange());

				mailBean.setTrackedPrice(portfolioDetails.getTrackedPrice());
				mailBean.setCompanyId(portfolioDetails
						.getPortfolioCompoundKey().getCompanyId());

				mailBeanList.add(mailBean);

			}

			return mailBeanList;

		} catch (Exception e) {
			logger.debug(e.getMessage());

			return null;
		}

	}

	/**
	 * This method is used to delete the records from portfolio
	 * 
	 * @see com.stockportfoliosystem.dao.PortfolioDAO#deleteRecordFromPortfolio(java.lang.String,
	 *      java.lang.String)
	 */
	public void deleteRecordFromPortfolio(String emailId, String companyId)
			throws StockPortfolioDaoException {
		logger.debug("inside portfoliodoa layer in deleteFromPortfolio(String,String)");
		Session session = sessionFactory.openSession();
		Query query = null;
		try {
			query = session
					.createQuery("delete PortfolioDetails where portfolioId='"
							+ emailId + "' and companyId='" + companyId + "'");
		} catch (HibernateException e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		}

		catch (Exception e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		}
		query.executeUpdate();
		session.close();
	}

	/**
	 * This method is used for removing the alert from user portfolio
	 * 
	 * @see com.stockportfoliosystem.dao.PortfolioDAO#removeAlertFromPortfolio(java.lang.String,
	 *      java.lang.String)
	 */
	public void removeAlertFromPortfolio(String emailId, String companyId)
			throws StockPortfolioDaoException {
		logger.debug(sessionFactory);
		Session session = sessionFactory.openSession();
		Query query = null;
		try {
			query = session
					.createSQLQuery("update stockdetails.PortfolioDetails set percentageChange=0 where  portfolioId='"
							+ emailId + "' and companyId='" + companyId + "'");

		} catch (HibernateException e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		}

		catch (Exception e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		}

		query.executeUpdate();
		session.close();
	}

}
