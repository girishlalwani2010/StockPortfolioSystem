package com.stockportfoliosystem.service;

import static org.junit.Assert.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.stockportfoliosystem.entity.PortfolioDetails;
import com.stockportfoliosystem.exception.StockPortfolioServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "../../../dispatcher-servlet.xml",
		"../../../applicationContext.xml" })
@Transactional
public class PortfolioServiceImplementationTest {

	@Autowired
	private PortfolioServiceImplementation portfolioServiceImplementationTest;
	/**
	 * Instantiated logger
	 */
	private Logger logger = LogManager
			.getLogger(CompanyServiceImplementationTest.class.getName());

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.service.PortfolioServiceImplementation#addToPortfolio(com.stockportfoliosystem.entity.PortfolioDetails)}
	 * .
	 */
	@Test
	public void testAddToPortfolio() {
		PortfolioDetails portfolioDetails = new PortfolioDetails();
		try {
			portfolioServiceImplementationTest.addToPortfolio(portfolioDetails);
		} catch (StockPortfolioServiceException e) {
			logger.error(e.getMessage(), e);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.service.PortfolioServiceImplementation#getPortfolioByEmailId(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetPortfolioByEmailId() {
		try {
			portfolioServiceImplementationTest
					.getPortfolioByEmailId("girish.lalwani@impetus.co.in");
		} catch (StockPortfolioServiceException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.service.PortfolioServiceImplementation#getCurrentPriceById(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCurrentPriceByIdString() {
		try {
			portfolioServiceImplementationTest.getCurrentPriceById("tcs");
		} catch (StockPortfolioServiceException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.service.PortfolioServiceImplementation#submitPercentageChange(java.lang.String, float, java.lang.String)}
	 * .
	 */
	@Test
	public void testSubmitPercentageChange() {
		try {
			portfolioServiceImplementationTest.submitPercentageChange("tcs",
					10, "girish.lalwani@impetus.co.in");
		} catch (StockPortfolioServiceException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.service.PortfolioServiceImplementation#getMailInfo()}
	 * .
	 */
	@Test
	public void testGetMailInfo() {
		portfolioServiceImplementationTest.getMailInfo();
	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.service.PortfolioServiceImplementation#deleteRecordFromPortfolio(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testDeleteRecordFromPortfolio() {
		try {
			portfolioServiceImplementationTest.deleteRecordFromPortfolio("girish.lalwani@impetus.co.in","tcs");
		} catch (StockPortfolioServiceException e) {
			logger.error(e.getMessage(), e);
		}
		catch(Exception e)
		{logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.service.PortfolioServiceImplementation#removeAlertFromPortfolio(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testRemoveAlertFromPortfolio() {
		try {
			portfolioServiceImplementationTest.removeAlertFromPortfolio("girish.lalwani@impetus.co.in", "tcs");
		} catch (StockPortfolioServiceException e) {
			logger.error(e.getMessage(), e);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(), e);
		}
	}

	
}
