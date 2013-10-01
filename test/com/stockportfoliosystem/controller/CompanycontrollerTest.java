package com.stockportfoliosystem.controller;

import static org.junit.Assert.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;

/**
 * @author Girish Lalwani
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "../../../dispatcher-servlet.xml",
		"../../../applicationContext.xml" })
public class CompanycontrollerTest {

	/**
	 * Autowiring of Companycontroller
	 */
	@Autowired
	private Companycontroller companyControllerTest;

	/**
	 * Created the request variable 
	 */
	private MockHttpServletRequest request;

	/**
	 * Created the response variable
	 */
	private MockHttpServletResponse response;

	/**
	 * Created the model variable
	 */
	private ModelMap model;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.request = new MockHttpServletRequest();

		this.response = new MockHttpServletResponse();

		this.model = new ModelMap();

	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.Companycontroller#getCompanyDetails(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
	 * .
	 */
	@Test
	public void testCompanyController() {

		request.setParameter("companyName", "tataconsultancyservices");
		String redirectTo = companyControllerTest.getCompanyDetails(model,
				request, response);
		assertEquals("search", redirectTo);
		request.setParameter("companyName", "ataconsultancyservices");
		String redirectToNeg = companyControllerTest.getCompanyDetails(model,
				request, response);
		assertEquals("error", redirectToNeg);

	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.Companycontroller#getCompanyNameAndIdList()}
	 * .
	 */
	@Test
	public void testAutocompleteController() {

		String companyIdNameList = companyControllerTest
				.getCompanyNameAndIdList();
		Assert.assertEquals(companyIdNameList != null, true);

	}

}
