package com.stockportfoliosystem.controller;

import java.text.ParseException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
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

/**
 * @author Girish Lalwani
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "../../../dispatcher-servlet.xml",
		"../../../applicationContext.xml" })
@Transactional
public class StockHistoryControllerTest {

	/**
	 * Autowiring of StockHistoryController
	 */
	@Autowired
	private StockHistoryController stockHistoryControllerTest;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	private ModelMap model;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.request = new MockHttpServletRequest();

		this.response = new MockHttpServletResponse();

		this.model = new ModelMap();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSet() {

	}

	@Test
	public void testGetgraphimage() {
		request.setParameter("companyId", "tcs");
		request.setParameter("fromDate", "2012/02/06");
		request.setParameter("toDate", "2012/10/03");
		request.setParameter("dayGraph1", "true");
		String graphImage = stockHistoryControllerTest.getGraphData(response,
				request, model);
		Assert.assertEquals(graphImage != null, true);

	}

	@Test
	public void testShowgraph() throws ParseException {

		String graphOptions = stockHistoryControllerTest.showGraphPage(model);
		Assert.assertEquals(graphOptions != null, true);
		Assert.assertEquals(graphOptions, "graph");

	}

	@Test
	public void testAutocompleteController() {
		String companyIdNameList = stockHistoryControllerTest
				.getCompanyNameAndIdList();
		Assert.assertEquals(companyIdNameList != null, true);

	}

}
