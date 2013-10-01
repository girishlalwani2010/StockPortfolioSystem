package com.stockportfoliosystem.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.stockportfoliosystem.dao.CompanyDAO;
import com.stockportfoliosystem.entity.CompanyDetails;

import com.stockportfoliosystem.exception.StockPortfolioDaoException;
import com.stockportfoliosystem.exception.StockPortfolioServiceException;
/**
 * @author Girish Lalwani
 * 
 */
public class CompanyServiceImplementation implements CompanyService {

	/**
	 * Instantiated logger
	 */
	private Logger logger = LogManager
			.getLogger(CompanyServiceImplementation.class.getName());
	/**
	 * Instantiated CompanyDAO
	 */
	private CompanyDAO companyNameDao;

	/**
	 * This method is used autowiring the CompanyDao in
	 * CompanyServiceImplementation
	 * 
	 * @param companyNameDao
	 * 
	 */

	@Autowired
	public void setcompanyNameDao(CompanyDAO companyNameDao) {
		this.companyNameDao = companyNameDao;
	}

	private static final String EXCEPTIONMSG = "currently service not available please try later";

	/**
	 * Instantiated companyDetails
	 */
	private CompanyDetails companyDetails = new CompanyDetails();

	/**
	 * This method is used to get the company details.
	 * 
	 * @return CompanyDetails
	 */
	public CompanyDetails getCompanyDetails(String companyName)
			throws StockPortfolioServiceException {

		try {
			companyDetails = companyNameDao.getCompanyDetails(companyName);
		} catch (StockPortfolioDaoException e) {
			throw new StockPortfolioServiceException("company doesn't exist", e);
		} catch (Exception e) {
			throw new StockPortfolioServiceException(EXCEPTIONMSG, e);
		}

		logger.debug("In CompanyServiceImplementation" + companyDetails);
		return companyDetails;

	}

	/**
	 * This method is used to get company name and id list for giving suggestion to user.
	 * 
	 * @return the list which contains the name and Id of companies
	 * 
	 */
	public String getCompanyNameAndIdList() {
		String str = "";
		logger.debug("in getCompanyNameIdList() in CompanyController");
		StringBuffer str1 = new StringBuffer("");
		List<CompanyDetails> companyNameIdList = new ArrayList<CompanyDetails>();
		companyNameIdList = companyNameDao.getCompanyNameAndIdList();
		for (CompanyDetails companyDetail : companyNameIdList) {
			str1.append(companyDetail.getCompanyId()).append(",");
			str1.append(companyDetail.getCompanyName()).append(",");
		}
		try {
			if (companyNameIdList != null && str1.length() >= 1) {
				str = str1.substring(0, str1.length() - 1);
			}
		}

		catch (StringIndexOutOfBoundsException e) {
			logger.error(e.getMessage());
		}

		logger.debug(str);
		return str;

	}

}
