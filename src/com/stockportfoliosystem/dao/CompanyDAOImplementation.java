package com.stockportfoliosystem.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateQueryException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.stockportfoliosystem.entity.CompanyDetails;
import com.stockportfoliosystem.exception.StockPortfolioDaoException;
/**
 * @author Girish Lalwani
 * 
 */
public class CompanyDAOImplementation implements CompanyDAO {
	private Logger logger = Logger.getLogger(CompanyDAOImplementation.class);

	/**
	 * Created the reference of hibernateTemplate
	 */
	private HibernateTemplate hibernateTemplate;

	/**
	 * This method is used for autowiring the session factory
	 * 
	 * @param sessionFactory 
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	/**
	 * This method is used for fetching the company details by its name or its
	 * ticker symbol 
	 * 
	 * @see com.stockportfoliosystem.dao.CompanyDAO#getCompanyDetails(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public CompanyDetails getCompanyDetails(String companyName)
			throws StockPortfolioDaoException {

		List<CompanyDetails> companyDetailsList = null;
		try {

			companyDetailsList = hibernateTemplate
					.find("from CompanyDetails companyDetails where companyDetails.companyName='"
							+ companyName
							+ "' or companyDetails.companyId='"
							+ companyName + "'");

		} catch (HibernateQueryException e) {
			throw new StockPortfolioDaoException("company name doesn't exist",
					e);
		} catch (Exception e) {
			throw new StockPortfolioDaoException(
					"Currently service not avialable please try later", e);
		}

		if (companyDetailsList == null
				|| ((List<CompanyDetails>) companyDetailsList).size() == 0) {
			return null;
		}
		logger.debug("In CompanyDAOImplementation" + companyDetailsList);
		return (((List<CompanyDetails>) companyDetailsList).get(0));

	}

	/**
	 * This method is used for fetching the CompanyNameIdList for giving the
	 * suggestion of companies which are available
	 * 
	 * @return company Name Id List
	 * 
	 * @see com.stockportfoliosystem.dao.CompanyDAO#getCompanyNameAndIdList()
	 */
	@SuppressWarnings("unchecked")
	public List<CompanyDetails> getCompanyNameAndIdList() {

		List<CompanyDetails> companyNameIdList = new ArrayList<CompanyDetails>();

		companyNameIdList = hibernateTemplate
				.find("from com.stockportfoliosystem.entity.CompanyDetails");

		return companyNameIdList;
	}
}
