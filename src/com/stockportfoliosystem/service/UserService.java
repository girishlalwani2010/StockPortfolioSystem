package com.stockportfoliosystem.service;

import com.stockportfoliosystem.entity.UserDetails;

import com.stockportfoliosystem.exception.StockPortfolioServiceException;

/**
 * @author Girish Lalwani
 * 
 */
public interface UserService {
	/**
	 * This method is used to register the user
	 * 
	 * @param userDetails
	 * @throws StockPortfolioServiceException
	 *             for example if user already exist
	 */
	void saveUser(UserDetails userDetails)
			throws StockPortfolioServiceException;

	/**
	 * This method is used to get the user
	 * 
	 * @param emailId
	 * @return UserDetails
	 */
	UserDetails getUserDetails(String emailId);

	/**
	 * This method is used to update the user profile
	 * 
	 * @param userDetails
	 * @param emailId
	 * @throws StockApplicationException
	 */
	void updateProfile(UserDetails userDetails, String emailId)
			throws StockPortfolioServiceException;

}
