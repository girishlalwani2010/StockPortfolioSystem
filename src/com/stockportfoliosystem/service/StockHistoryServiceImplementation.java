package com.stockportfoliosystem.service;

import java.sql.Date;
import java.sql.Timestamp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.stockportfoliosystem.dao.StockHistoryDAO;
import com.stockportfoliosystem.exception.StockPortfolioDaoException;
import com.stockportfoliosystem.exception.StockPortfolioServiceException;

/**
 * @author Girish Lalwani
 * 
 */
public class StockHistoryServiceImplementation implements StockHistoryService {

	/**
	 * Declared the stockHistoryDao refrence
	 * 
	 */
	private StockHistoryDAO stockHistoryDao;
	/**
	 * Created string constant for storing the exception message
	 */
	private static final String EXCEPTIONMSG = "something went wrong during adding companies to portfolio";

	/**
	 * Instantiating logger
	 */
	private Logger logger = LogManager
			.getLogger(StockHistoryServiceImplementation.class.getName());

	/**
	 * This method is used for autowiring of stockHistoryDao
	 * 
	 * @param stockHistoryDao
	 */
	@Autowired
	public void setStockHistoryDAO(StockHistoryDAO stockHistoryDao) {
		this.stockHistoryDao = stockHistoryDao;
	}

	/**
	 * This method is ued for getting the chart for user according to selected
	 * options
	 * 
	 */

	public String getGraphData(String companyId, Date fDate, Date tDate,
			int noOfDays) throws StockPortfolioServiceException {
		// TODO Auto-generated method stub

		try {
			return stockHistoryDao.getGraphData(companyId,
					new Timestamp(fDate.getTime()),
					new Timestamp(tDate.getTime()), noOfDays);
		} catch (StockPortfolioDaoException e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);

		}

	}

}
