/**
 * 
 */
package com.stockportfoliosystem.dao;

import java.sql.Timestamp;

import com.stockportfoliosystem.exception.StockPortfolioDaoException;
/**
 * @author Girish Lalwani
 * 
 */
public interface StockHistoryDAO {
	/**
	 * This method is used to get the data for the graph
	 * @param tickerSymbol
	 * @param fDate
	 * @param tDate
	 * @param noOfDays
	 * @return the data to plot the graph to service layer
	 * @throws StockPortfolioDaoException
	 */
	String getGraphData(String tickerSymbol, Timestamp fDate, Timestamp tDate,
			int noOfDays) throws StockPortfolioDaoException,
			StockPortfolioDaoException;
}
