package com.stockportfoliosystem.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.stockportfoliosystem.bean.MailBean;
import com.stockportfoliosystem.dao.PortfolioDAO;
import com.stockportfoliosystem.entity.PortfolioDetails;
import com.stockportfoliosystem.exception.StockPortfolioDaoException;
import com.stockportfoliosystem.exception.StockPortfolioServiceException;

/**
 * @author Girish Lalwani
 * 
 */
public class PortfolioServiceImplementation implements PortfolioService {
	/**
	 * Instantiated logger
	 */
	private Logger logger = LogManager
			.getLogger(CompanyServiceImplementation.class.getName());
	/**
	 * Created string constant for storing the exception message
	 */
	private static final String EXCEPTIONMSG = "something went wrong during adding companies to portfolio";
	/**
	 * Created the reference of portfolio dao
	 * 
	 */
	private PortfolioDAO portfolioDao;

	/**
	 * This method is used for autowiring portfolio dao
	 * 
	 * @param portfolioDao
	 */
	@Autowired
	public void setPortfolioDao(PortfolioDAO portfolioDao) {
		this.portfolioDao = portfolioDao;
	}

	/**
	 * This method is used to for saving into portfolio by getting the searching
	 * the particular company
	 * 
	 * 
	 * @param portfolio
	 */
	public void addToPortfolio(PortfolioDetails portfolio)
			throws StockPortfolioServiceException {

		try {
			portfolioDao.addToPortfolio(portfolio);
		} catch (StockPortfolioDaoException e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);

		}

	}

	/**
	 * This method is used for getting the portfolio by emailId
	 * 
	 * @return List<Portfolio>
	 * 
	 * */
	public List<PortfolioDetails> getPortfolioByEmailId(String emailStr)
			throws StockPortfolioServiceException {

		try {
			return portfolioDao.getPortfolioByEmailId(emailStr);
		} catch (StockPortfolioDaoException e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		}

	}

	/**
	 * This method is called for getting the current price by ID
	 * 
	 * @return float currentPrice according to selected companyId
	 */
	public float getCurrentPriceById(String companyId)
			throws StockPortfolioServiceException {

		try {
			return portfolioDao.getCurrentPriceById(companyId);
		} catch (StockPortfolioDaoException e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		}
	}

	/**
	 * This method is used for submitting the percentage change from portfolio
	 * controller
	 * 
	 * @see com.stockportfoliosystem.service.PortfolioService#submitPercentageChange(java.lang.String,
	 *      float, java.lang.String)
	 */
	public void submitPercentageChange(String companyId,
			float percentageChange, String emailId)
			throws StockPortfolioServiceException {

		try {
			portfolioDao.submitPercentageChange(companyId, percentageChange,
					emailId);
		} catch (StockPortfolioDaoException e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		}

	}

	/**
	 * This method is used for getting mail info
	 * 
	 * @return List<MailBean>
	 */
	public List<MailBean> getMailInfo() {
		logger.debug("getMailInfo() inside service layer");

		return portfolioDao.getMailInfo();
	}

	/**
	 * This method is used for deleting the records from portfolio
	 * 
	 * @see com.stockportfoliosystem.service.PortfolioService#deleteRecordFromPortfolio(java.lang.String,
	 *      java.lang.String)
	 */
	public void deleteRecordFromPortfolio(String emailId, String companyId)
			throws StockPortfolioServiceException {
		try {
			portfolioDao.deleteRecordFromPortfolio(emailId, companyId);
		} catch (StockPortfolioDaoException e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		}

	}

	/**
	 * This method is used for removing the alerts from portfolio
	 * 
	 * @see com.stockportfoliosystem.service.PortfolioService#removeAlertFromPortfolio(java.lang.String,
	 *      java.lang.String)
	 */
	public void removeAlertFromPortfolio(String emailId, String companyId)
			throws StockPortfolioServiceException {
		try {
			portfolioDao.removeAlertFromPortfolio(emailId, companyId);
		} catch (StockPortfolioDaoException e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		}
	}

	/**
	 * This method is used for getting the current price by Id
	 * 
	 * @see com.stockportfoliosystem.service.PortfolioService#getCurrentPriceById(java.util.List)
	 */
	public List<Float> getCurrentPriceById(
			List<PortfolioDetails> portfolioDetails)
			throws StockPortfolioServiceException {

		List<Float> prices = new ArrayList<Float>();
		float price = 0f;
		for (int i = 0; i < portfolioDetails.size(); i++) {
			logger.debug(portfolioDetails.get(i).getPortfolioCompoundKey()
					.getCompanyId());
			try {
				price = portfolioDao.getCurrentPriceById(portfolioDetails
						.get(i).getPortfolioCompoundKey().getCompanyId());
			} catch (StockPortfolioDaoException e) {
				logger.error(e.getMessage(), e);
				throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
			}
			prices.add(price);
		}
		return prices;
	}
}
