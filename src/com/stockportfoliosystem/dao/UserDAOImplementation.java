package com.stockportfoliosystem.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.stockportfoliosystem.entity.UserDetails;
import com.stockportfoliosystem.exception.StockPortfolioDaoException;
/**
 * @author Girish Lalwani
 * 
 */
public class UserDAOImplementation implements UserDAO {
	/**
	 * Created the instance of Logger class
	 */
	private Logger logger = LogManager.getLogger(UserDAOImplementation.class
			.getName());
	/**
	 * Created the referencing of hibernateTemplate
	 */
	private HibernateTemplate hibernateTemplate;

	/**
	 * Created the SessionFactory for autowiring the sessionFactory
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Created string constant for storing the exception message
	 */
	private static final String EXCEPTIONMSG = "currently service not available please try later";

	/**
	 * 
	 * This method is used for autowiring hibernateTemplate
	 * 
	 * @param sessionFactory for autowiring hibernateTemplate
	 * 
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method is used to save user into db by getting the info from service
	 * layer
	 * 
	 * @see com.stockportfoliosystem.dao.UserDAO#saveUser(UserDetails)
	 * 
	 */
	public void saveUser(UserDetails user) throws StockPortfolioDaoException {
		// exception handling of exception user already exist
		try {
			hibernateTemplate.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new StockPortfolioDaoException(
					"User Already Exist with this Id", e);
		} catch (Exception e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		}

	}

	/**
	 * This method is used for getting the user details by its email id
	 * 
	 * @see com.stockportfoliosystem.dao.UserDAO#getUserDetails(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public UserDetails getUserDetails(String emailId) {

		List<UserDetails> userDetailsList = new ArrayList<UserDetails>();

		userDetailsList = hibernateTemplate
				.find("from com.stockportfoliosystem.entity.UserDetails user where user.emailId=?",
						emailId);

		return (UserDetails) userDetailsList.get(0);

	}

	/**
	 * This method is used for update the profile of particular user
	 * 
	 * @throws StockApplicationException
	 * @see com.stockportfoliosystem.dao.UserDAO#updateProfile(com.stockportfoliosystem.entity.UserDetails,
	 *      java.lang.String)
	 */
	public void updateProfile(UserDetails userDetails, String emailId)
			throws StockPortfolioDaoException {

		logger.debug(sessionFactory);

		try {
			hibernateTemplate.update(userDetails);
		} catch (HibernateException e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		} catch (Exception e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		}

	}

}
