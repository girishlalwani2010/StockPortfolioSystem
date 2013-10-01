package com.stockportfoliosystem.dao;

import com.stockportfoliosystem.entity.UserDetails;

import com.stockportfoliosystem.exception.StockPortfolioDaoException;
/**
 * @author Girish Lalwani
 * 
 */
public interface UserDAO {
     /**
      * This method is used to registered the user 
     * @param user
     * @throws StockApplicationException
     * @throws StockPortfolioDaoException 
     */
    void saveUser(UserDetails user) throws  StockPortfolioDaoException;
	
     /**
      * This method is used to get the user by its emailId
     * @param emailId
     * @return UserDetails
     */
    UserDetails getUserDetails(String emailId);

	/**
	 * This method is used to update the user
	 * @param userDetails populated userDetails object at update profile page
	 * @param emailId
	 * @throws StockApplicationException 
	 */
	void updateProfile(UserDetails userDetails, String emailId) throws StockPortfolioDaoException; 
     		
 	 
 	
	 
}
