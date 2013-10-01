package com.stockportfoliosystem.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.stockportfoliosystem.entity.StockHistory;

import com.stockportfoliosystem.exception.StockPortfolioDaoException;

/**
 * @author Girish Lalwani
 * 
 */
public class StockInfoDAOImplementation implements StockInfoDAO {

	/**
	 * Referencing the hibernateTemplate
	 */
	private HibernateTemplate hibernateTemplate;

	/**
	 * Created the constant EXCEPTIONMSG to store the message of our need
	 */
	private static final String EXCEPTIONMSG = "Currently service not available please try later";

	/**
	 * This method is used for autowiring the hibernateTemplate with
	 * SessionFactory
	 * 
	 * @param sessionFactory
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method is used to save information to stock histroy from external
	 * xml
	 * 
	 * @see com.stockportfoliosystem.dao.StockInfoDAO#saveInfoToStockHistory(java.util.List)
	 */
	public void saveInfoToStockHistory(List<StockHistory> stockHistory)
			throws StockPortfolioDaoException {
		try {

			hibernateTemplate.saveOrUpdateAll(stockHistory);
		} catch (HibernateException e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		} catch (Exception e) {
			throw new StockPortfolioDaoException(EXCEPTIONMSG, e);
		}

	}
}
