/**
 * 
 */
package com.stockportfoliosystem.service;

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
public class StockInfoServiceImplementationTest {

	/**
	 * Autowiring of stockInfoServiceImplementationTest in
	 * StockInfoServiceImplementationTest
	 */
	@Autowired
	private StockInfoServiceImplementation stockInfoServiceImplementationTest;

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.service.StockInfoServiceImplementation#currentStockInfo()}
	 * .
	 */
	@Test
	public void testCurrentStockInfo() {

		stockInfoServiceImplementationTest.currentStockInfo();
	}

}
