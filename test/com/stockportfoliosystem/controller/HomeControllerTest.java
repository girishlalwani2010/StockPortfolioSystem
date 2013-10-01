/**
 * 
 */
package com.stockportfoliosystem.controller;

import static org.junit.Assert.assertEquals;

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

/**
 * @author Girish Lalwani
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "../../../dispatcher-servlet.xml",
		"../../../applicationContext.xml" })
@Transactional
public class HomeControllerTest {

	/**
	 * Autowiring of HomeController in HomeControllerTest
	 */
	@Autowired
	private HomeController homeControllerTest;

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.HomeController#showHomePage()}
	 * .
	 */
	@Test
	public void testShowHomePage() {

		String showHomePage = homeControllerTest.showHomePage();
		assertEquals("index", showHomePage);
	}

}
