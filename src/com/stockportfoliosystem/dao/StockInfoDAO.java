/**
 * 
 */
package com.stockportfoliosystem.dao;

import java.util.List;

import com.stockportfoliosystem.entity.StockHistory;
import com.stockportfoliosystem.exception.StockPortfolioDaoException;
/**
 * @author Girish Lalwani
 * 
 */
public interface StockInfoDAO {
 
	/**
	 * This method is used to save the info from  xml to StockHistory entity
	 * @param stockHistory
	 * @throws StockApplicationException
	 */
 void saveInfoToStockHistory(List<StockHistory> stockHistory) throws StockPortfolioDaoException;
	
		
	
	

}
