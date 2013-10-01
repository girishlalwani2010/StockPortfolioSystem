package com.stockportfoliosystem.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateQueryException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.stockportfoliosystem.entity.CompanyDetails;
import com.stockportfoliosystem.entity.StockHistory;

import com.stockportfoliosystem.exception.StockPortfolioDaoException;
/**
 * @author Girish Lalwani
 * 
 */
public class StockHistoryDAOImplementation implements StockHistoryDAO {
	/**
	 * Instantiating the logger
	 */
	private Logger logger = LogManager
			.getLogger(StockHistoryDAOImplementation.class.getName());

	/**
	 * Autowired annotation for autowiring session factory
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Created the refrence of hibernate template
	 */
	private HibernateTemplate hibernateTemplate;

	/**
	 * Created string constant for storing the exception message
	 */
	private static final String EXCEPTIONMSG = "something went wrong during adding companies to portfolio";

	/**
	 * This method is used for autowiring the SessionFactory
	 * 
	 * @param sessionFactory
	 * 
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method is used to return the graph data to service layer
	 * 
	 * @see com.stockportfoliosystem.dao.StockHistoryDAO#getGraphData(java.lang.String,
	 *      java.sql.Timestamp, java.sql.Timestamp, int)
	 */
	@SuppressWarnings("unchecked")
	public String getGraphData(String companyId, Timestamp fDate,
			Timestamp tDate, int noOfDays) throws StockPortfolioDaoException {
		StringBuffer graph = new StringBuffer("[");
		String finalGraph = "";
		List<StockHistory> stockHistoryList = new ArrayList<StockHistory>();

		logger.debug(sessionFactory);
		Session session = sessionFactory.openSession();

		Query query;
		try {
			if (noOfDays > 0) {
				List<CompanyDetails> companyDetails = hibernateTemplate
						.find("from CompanyDetails companyDetails where companyDetails.companyName='"
								+ companyId
								+ "' or companyDetails.companyId='"
								+ companyId + "'");
				String companyId1 = companyDetails.get(0).getCompanyId();
				query = session
						.createSQLQuery(
								"select * from (select * from stockdetails.StockHistory sh  where  sh.timeStamp between '"
										+ fDate
										+ "' and '"
										+ tDate
										+ "' and sh.companyId = '"
										+ companyId1
										+ "' order by sh.timeStamp desc) AS x group by date(x.timeStamp)")
						.addEntity(StockHistory.class);
			} else {
				List<CompanyDetails> companyDetails = hibernateTemplate
						.find("from CompanyDetails companyDetails where companyDetails.companyName='"
								+ companyId
								+ "' or companyDetails.companyId='"
								+ companyId + "'");
				String companyId1 = companyDetails.get(0).getCompanyId();
				query = session
						.createSQLQuery(
								"select * from stockdetails.StockHistory sh where sh.timeStamp between ADDTIME('"
										+ fDate
										+ "','11:00:00') and  ADDTIME('"
										+ tDate
										+ "','17:00:00')and sh.companyId = '"
										+ companyId1 + "'").addEntity(
								StockHistory.class);
			}
		} catch (HibernateQueryException e) {
			// TODO Auto-generated catch block
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		} catch (Exception e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		}

		logger.debug("size of list" + stockHistoryList.size());

		stockHistoryList = (List<StockHistory>) query.list();
		logger.debug("size of list" + stockHistoryList.size());

		for (StockHistory stockHistory : stockHistoryList) {
			logger.debug("stockHistory"
					+ stockHistory.getStockHistoryCompoundKey().getTimeStamp());
			graph.append("[\""
					+ stockHistory.getStockHistoryCompoundKey().getTimeStamp()
					+ "\"," + stockHistory.getPrice() + "],");

		}
		if (stockHistoryList != null) {
			logger.debug("into if");
			finalGraph = graph.substring(0, graph.length() - 1);
		}

		logger.debug("graph--------->" + graph);
		finalGraph += "];";
		return finalGraph;

	}

}
