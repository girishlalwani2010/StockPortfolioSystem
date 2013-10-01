package com.stockportfoliosystem.controller;

import javax.servlet.http.HttpSession;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Girish Lalwani
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "../../../dispatcher-servlet.xml",
		"../../../applicationContext.xml" })
@Transactional
public class PortfolioControllerTest {

	/**
	 * Autowiring of PortfolioController in PortfolioControllerTest
	 */

	@Autowired
	private PortfolioController portfolioControllerTest;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private HttpSession session;
	private ModelMap model;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		this.request = new MockHttpServletRequest();

		this.response = new MockHttpServletResponse();

		this.model = new ModelMap();

		this.session = request.getSession();

	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.PortfolioController#addToPortfolio(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
	 * .
	 */
	@Test
	public void testPortfolioController() {
		request.setParameter("companyName", "tataconsultancyservices");

		request.setParameter("currentPrice", "90");
		String positive = portfolioControllerTest.addToPortfolio(model, request,
				response);

		Assert.assertEquals("stock has been added successfully",positive);
		request.setParameter("companyName", "tataconsultancyservices");

		request.setParameter("currentPrice", "90");
		String negative= portfolioControllerTest.addToPortfolio(model, request,
				response);
		Assert.assertEquals("company Already added", negative);

	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.PortfolioController#getPortfolioByEmailId(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)}
	 * .
	 */
	@Test
	public void testMyportfolioController() {
		session = request.getSession();
		session.setAttribute("emailStr", "girish.lalwani@impetus.co.in");
		String currentPrice = portfolioControllerTest.getPortfolioByEmailId(
				model, request);
		Assert.assertEquals(currentPrice != null, true);

	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.PortfolioController#submitPercentageChange(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
	 * .
	 */
	@Test
	public void testSubmitPercentageChange() {

		request.setParameter("trackedPrice", "90");
		request.setParameter("companyId", "tcs");
		request.setParameter("PercentageVariation", "10");
		request.setParameter("emailStr", "girish.lalwani@impetus.co.in");
		portfolioControllerTest.submitPercentageChange(request, response);
	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.PortfolioController#deleteRecordFromPortfolio(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
	 */
	@Test
	@RequestMapping(value = "/deleteFromPortfolio.do", method = RequestMethod.GET)
	public void testDeleteRecordFromPortfolio() {
		request.setParameter("emailId", "girish.lalwani@impetus.co.in");
		request.setParameter("companyId", "tcs");
		portfolioControllerTest.deleteRecordFromPortfolio(request, response);

	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.PortfolioController#removeAlertFromPortfolio(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
	 */
	@Test
	@RequestMapping(value = "/deleteFromPortfolio.do", method = RequestMethod.GET)
	public void testRemoveAlertFromPortfolio()

	{
		session = request.getSession();
		session.setAttribute("emailStr", "girish.lalwani@impetus.co.in");
		request.setParameter("companyId", "tcs");
		portfolioControllerTest.removeAlertFromPortfolio(request, response);

	}

}
