package com.stockportfoliosystem.dao;

import java.util.List;

import com.stockportfoliosystem.entity.CompanyDetails;

import com.stockportfoliosystem.exception.StockPortfolioDaoException;

/**
 * @author Girish Lalwani
 * 
 */
public interface CompanyDAO {
	/**
	 * This method is used to select the details of company by its name or its
	 * ticker symbol
	 * 
	 * @param companyName
	 * @return CompanyDetails of company which is selected by user
	 * @throws StockApplicationException
	 */
	CompanyDetails getCompanyDetails(String companyName)
			throws StockPortfolioDaoException;

	/**
	 * This method is used to get List which contains the company names and
	 * their tickersymbol for giving the suggetions to user
	 * 
	 * @return list which contains the name and id of company
	 */
	List<CompanyDetails> getCompanyNameAndIdList();

}
