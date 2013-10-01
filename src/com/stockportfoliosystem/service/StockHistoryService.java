package com.stockportfoliosystem.service;

import java.sql.Date;

import com.stockportfoliosystem.exception.StockPortfolioServiceException;
/**
 * @author Girish Lalwani
 * 
 */
public interface StockHistoryService {

	/**
	 * This method is ued for getting the data for plotting the graph
	 * 
	 * @param companyId
	 * @param fDate
	 * @param tDate
	 * @param noOfDays
	 * @return data to plot the graph.
	 * @throws StockApplicationException
	 */
	String getGraphData(String companyId, Date fDate, Date tDate, int noOfDays)
			throws StockPortfolioServiceException;

}
