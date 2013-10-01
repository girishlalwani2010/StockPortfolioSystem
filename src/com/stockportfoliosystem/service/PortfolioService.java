package com.stockportfoliosystem.service;

import java.util.List;

import com.stockportfoliosystem.bean.MailBean;
import com.stockportfoliosystem.entity.PortfolioDetails;

import com.stockportfoliosystem.exception.StockPortfolioServiceException;
/**
 * @author Girish Lalwani
 * 
 */
public interface PortfolioService {

	/**
	 * This method is used to save the company into portfolio by searching it. 
	 * 
	 * @param portfolio
	 * @throws StockApplicationException
	 * 
	 */
	void addToPortfolio(PortfolioDetails portfolio)
			throws StockPortfolioServiceException;
      
	/**
	 * This method is used to get the portfolio by emailId
	 * 
	 * @param emailStr
	 * @return portfolio 
	 * @throws StockApplicationException
	 */
	List<PortfolioDetails> getPortfolioByEmailId(String emailStr)
			throws StockPortfolioServiceException;

	/**
	 * This method is used to getcurrent price by id
	 * 
	 * @param companyId pass the companyId 
	 * @return currentPrice by companyId
	 * @throws StockApplicationException
	 */
	float getCurrentPriceById(String companyId)
			throws StockPortfolioServiceException;

	/**
	 * This method is used to get the List of current Prices of companies which are in the user portfolio
	 * @param portfolioDetails
	 * @return list of currentPrice
	 * @throws StockPortfolioServiceException
	 */
	List<Float> getCurrentPriceById(List<PortfolioDetails> portfolioDetails)
			throws StockPortfolioServiceException;

	/**
	 * This method is used to submit the PercentageChange specified by user for
	 * alert
	 * 
	 * @param companyId 
	 * @param percentageChange which is specified by user
	 * @param emailId
	 * @throws StockApplicationException
	 */
	void submitPercentageChange(String companyId, float percentageChange,
			String emailId) throws StockPortfolioServiceException;

	/**
	 * This method is used to get mailInfo
	 * 
	 * @return MailInfo
	 */
	List<MailBean> getMailInfo();

	/**
	 * This method is used to delete the record from portfolio
	 * 
	 * @param emailId
	 * @param companyId 
	 * @throws StockApplicationException
	 */
	void deleteRecordFromPortfolio(String emailId, String companyId)
			throws StockPortfolioServiceException;

	/**
	 * This method is used to remove the alert from portfolio
	 * 
	 * @param emailId
	 * @param companyId
	 * @throws StockApplicationException
	 */
	void removeAlertFromPortfolio(String emailId, String companyId)
			throws StockPortfolioServiceException;

}
