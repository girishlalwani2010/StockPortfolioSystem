package com.stockportfoliosystem.service;

import com.stockportfoliosystem.entity.CompanyDetails;

import com.stockportfoliosystem.exception.StockPortfolioServiceException;
/**
 * @author Girish Lalwani
 * 
 */
public interface CompanyService {
	/**
	 * This method is used to get the company details by its name
	 * @param companyName contains the name of particular company.
	 * @return CompanyDetails 
	 * @throws StockApplicationException
	 */
	CompanyDetails getCompanyDetails(String companyName)
			throws StockPortfolioServiceException;

	/**
	 * This method is used to get the list which contains company name and its
	 * ticker symbol
	 * 
	 * @return CompanyDetails list which contains the name and id of companies for giving the suggetions to user.
	 */
	String getCompanyNameAndIdList();

}
