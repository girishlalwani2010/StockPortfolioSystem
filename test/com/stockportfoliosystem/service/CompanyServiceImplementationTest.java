/**
 * 
 */
package com.stockportfoliosystem.service;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.stockportfoliosystem.exception.StockPortfolioServiceException;
/**
 * @author Girish Lalwani
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "../../../dispatcher-servlet.xml",
		"../../../applicationContext.xml" })
@Transactional
public class CompanyServiceImplementationTest {

	@Autowired
	private CompanyServiceImplementation companyServiceImplementationTest;
	/**
	 * Instantiated logger
	 */
	private Logger logger = LogManager
			.getLogger(CompanyServiceImplementationTest.class.getName());

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.service.CompanyServiceImplementation#getCompanyDetails(java.lang.String)}
	 * 
	 */
	@Test
	public void testGetCompanyDetails() {

		try {
			companyServiceImplementationTest.getCompanyDetails("tcs");
		} catch (StockPortfolioServiceException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.service.CompanyServiceImplementation#getCompanyNameAndIdList()}
	 * .
	 */
	@Test
	public void testGetCompanyNameAndIdList() {
		companyServiceImplementationTest.getCompanyNameAndIdList();
	}

}
