package com.stockportfoliosystem.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.stockportfoliosystem.dao.UserDAO;
import com.stockportfoliosystem.entity.UserDetails;
import com.stockportfoliosystem.exception.StockPortfolioDaoException;
import com.stockportfoliosystem.exception.StockPortfolioServiceException;
/**
 * @author Girish Lalwani
 * 
 */
public class UserServiceImplementation implements UserService {
	/**
	 * Instantiated Logger class
	 */
	private Logger logger = LogManager.getLogger(UserService.class.getName());
	/**
	 * Declared userDao reference
	 */

	private UserDAO userDao;

	/**
	 * Created string constant for storing the exception message
	 */
	private static final String EXCEPTIONMSG = "currently service not available please try later";

	/**
	 * This method is used for autowiring the UserDao in
	 * UserServiceImplementation
	 * 
	 * @param userDao
	 */
	@Autowired
	public void setuserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	/**
	 * This method is used to register the userDetails
	 * 
	 * @param userDetails
	 * @throws StockPortfolioServiceException
	 */
	public void saveUser(UserDetails userDetails)
			throws StockPortfolioServiceException {

		try {
			userDao.saveUser(userDetails);
		}

		catch (StockPortfolioDaoException e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException("User Already Exist", e);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);

		}

	}

	/**
	 * This method is used to get the user
	 * 
	 * @see com.stockportfoliosystem.service.UserService#getUserDetails(java.lang.String)
	 * @return UserDetails
	 */
	public UserDetails getUserDetails(String emailId) {
		UserDetails userDetails = new UserDetails();
		userDetails = userDao.getUserDetails(emailId);
		logger.debug(userDetails);
		return userDetails;

	}

	/**
	 * This method is used to update the profile of user which is currently
	 * logged in
	 * 
	 * @throws StockApplicationException
	 * @see com.stockportfoliosystem.service.UserService#updateProfile(com.stockportfoliosystem.entity.UserDetails,
	 *      java.lang.String)
	 */
	public void updateProfile(UserDetails userDetails, String emailId)
			throws StockPortfolioServiceException {
		try {
			userDao.updateProfile(userDetails, emailId);
		} catch (StockPortfolioDaoException e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		}

	}

}
