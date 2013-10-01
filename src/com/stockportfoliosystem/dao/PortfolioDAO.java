package com.stockportfoliosystem.dao;

import java.util.List;

import com.stockportfoliosystem.bean.MailBean;
import com.stockportfoliosystem.entity.PortfolioDetails;

import com.stockportfoliosystem.exception.StockPortfolioDaoException;
/**
 * @author Girish Lalwani
 * 
 */
public interface PortfolioDAO {

	/**
	 * This method is used to save company details to portfolio
	 * 
	 * @param portfolio 
	 * @throws StockApplicationException
	 */
	void addToPortfolio(PortfolioDetails portfolio)
			throws StockPortfolioDaoException;

	/**
	 * This method is used for fetching the portfolio for user by its email id
	 * 
	 * @param emailStr
	 * @return portfolio
	 * @throws StockApplicationException
	 */
	List<PortfolioDetails> getPortfolioByEmailId(String emailStr)
			throws StockPortfolioDaoException;

	/**
	 * This method is used for getting current price
	 * 
	 * @param companyId for which one has to get the current price
	 * @return current price 
	 * @throws StockApplicationException
	 */
	Float getCurrentPriceById(String companyId)
			throws StockPortfolioDaoException;

	/**
	 * This method is used for submitting percentage change specified by user
	 * 
	 * @param companyId
	 * @param percentageChange
	 * @param emailId
	 * @throws StockApplicationException
	 */
	void submitPercentageChange(String companyId, Float percentageChange,
			String emailId) throws StockPortfolioDaoException;

	/**
	 * This method is used to give the mailInfo for which the system has to send
	 * the mail
	 * 
	 * @return List of users which match the criteria of percentage variation
	 */
	List<MailBean> getMailInfo();

	/**
	 * This method is used for deleting the records from portfolio
	 * 
	 * @param emailId 
	 * @param companyId
	 * @throws StockApplicationException
	 */
	void deleteRecordFromPortfolio(String emailId, String companyId)
			throws StockPortfolioDaoException;

	/**
	 * This method is used to remove the alert from portfolio
	 * 
	 * @param emailId
	 * @param companyId
	 * @throws StockApplicationException
	 */
	void removeAlertFromPortfolio(String emailId, String companyId)
			throws StockPortfolioDaoException;

}
