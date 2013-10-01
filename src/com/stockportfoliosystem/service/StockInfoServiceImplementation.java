package com.stockportfoliosystem.service;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.stockportfoliosystem.dao.CompanyDAO;
import com.stockportfoliosystem.dao.StockInfoDAO;
import com.stockportfoliosystem.entity.Company;
import com.stockportfoliosystem.entity.StockDetails;
import com.stockportfoliosystem.entity.StockHistory;
import com.stockportfoliosystem.entity.StockHistoryCompoundKey;
import com.stockportfoliosystem.exception.StockPortfolioDaoException;
/**
 * @author Girish Lalwani
 * 
 */
public class StockInfoServiceImplementation implements StockInfoService {
	/**
	 * Instantiated logger
	 * 
	 */
	private Logger logger = LogManager
			.getLogger(StockInfoServiceImplementation.class.getName());

	private static final String EXCEPTIONMSG = "Currently service not available please try later";

	/**
	 * Declared stockInfoDao refrence
	 */
	private StockInfoDAO stockInfoDao;

	/**
	 * This method is used for autowiring the StockInfoDAO in
	 * StockInfoServiceImplementation
	 * 
	 * @param stockInfoDao
	 */
	@Autowired
	public void setStockInfoDAO(StockInfoDAO stockInfoDao) {
		this.stockInfoDao = stockInfoDao;
	}

	/**
	 * Declared CompanyDAO variable
	 * 
	 */
	@SuppressWarnings("unused")
	private CompanyDAO companyNameDao;

	/**
	 * This method is used for autowiring the CompanyDAO in
	 * StockInfoServiceImplementation
	 * 
	 * @param companyNameDao
	 */
	@Autowired
	public void setCompanyDAO(CompanyDAO companyNameDao) {
		this.companyNameDao = companyNameDao;
	}

	/**
	 * This method is used for saving the current stock info into db by fetching
	 * it from xml whole unmarshalling using jaxb is done here
	 * 
	 */

	public void currentStockInfo() {
		try {
			logger.debug("in StockInfoServiceImplementation Scheduler starting");
			File file = new File("config/price.xml"); 
			JAXBContext jaxbContext = JAXBContext
					.newInstance(StockDetails.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StockDetails stockDetails = (StockDetails) jaxbUnmarshaller
					.unmarshal(file);
			logger.debug(stockDetails.getCompany().size());
			List<StockHistory> stockHistories = new ArrayList<StockHistory>();

			if (stockDetails != null && stockDetails.getCompany() != null
					&& stockDetails.getCompany().size() > 0) {
				for (Company companyVar : stockDetails.getCompany()) {
					StockHistory stockHistory = new StockHistory();

					stockHistory.setPrice(companyVar.getPrice());
					stockHistory
							.setStockHistoryCompoundKey(new StockHistoryCompoundKey(
									new Timestamp(new java.sql.Date(
											new SimpleDateFormat(
													"yyyy/MM/dd HH:mm:ss")
													.parse(companyVar
															.getTimeStamp())
													.getTime()).getTime()),
									companyVar.getCompanyId()));
					stockHistories.add(stockHistory);

				}
			}

			try {
				stockInfoDao.saveInfoToStockHistory(stockHistories);
			} catch (StockPortfolioDaoException e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error(EXCEPTIONMSG, e);
		}

	}

}
