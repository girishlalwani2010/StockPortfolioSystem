/**
 * 
 */
package com.stockportfoliosystem.controller;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.ui.ModelMap;

import com.stockportfoliosystem.exception.StockPortfolioServiceException;

/**
 * @author Girish Lalwani
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "../../../dispatcher-servlet.xml",
		"../../../applicationContext.xml" })
@Transactional
public class UserControllerTest {

	/**
	 * Autowiring of UserController in UserControllerTest
	 */
	@Autowired
	private UserController loginControllerTest;

	private MockHttpServletRequest request;

	private MockHttpServletResponse response;

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
	 * {@link com.stockportfoliosystem.controller.UserController#showFrontPage()}
	 * .
	 */
	@Test
	public void testShowFrontPage() {
		String s = loginControllerTest.showFrontPage();
		assertEquals("index", s);
	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.UserController#showLoginForm()}
	 * .
	 */
	@Test
	public void testShowLoginForm() {
		String s = loginControllerTest.showLoginForm();
		assertEquals("Login", s);

	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.UserController#showRegistrationForm()}
	 * .
	 */
	@Test
	public void testShowRegistrationForm() {
		String s = loginControllerTest.showRegistrationForm();
		Assert.assertEquals(s != null, true);
		assertEquals("Registration", s);

	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.UserController#showUpdateProfile()}
	 * .
	 */
	@Test
	public void testShowUpdateProfile() {
		String s = loginControllerTest.showUpdateProfile();
		assertEquals("updateprofile", s);
	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.UserController#saveUser(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,org.springframework.ui.ModelMap)}
	 * .
	 */

	@Test
	public void testSaveUser() {

		request.setParameter("firstName", "girish");
		request.setParameter("lastName", "lalwani");
		request.setParameter("emailId", "ab123@impetus.co.in");
		request.setParameter("password", "123");
		request.setParameter("confirmPassword", "12345");
		request.setParameter("city", "gwalior");
		String success = loginControllerTest.saveUser(request, response, model);
		assertEquals("index", success);
		String failure = loginControllerTest.saveUser(request, response, model);
		Assert.assertEquals(failure != null, true);
		assertEquals("Registration", failure);
	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.UserController#loginUser(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
	 * .
	 */
	@Test
	public void testLoginUser() {

		request.setParameter("emailId", "girish.lalwani@impetus.co.in");
		request.setParameter("password", "12345");
		String s = loginControllerTest.loginUser(model, request, response);
		Assert.assertEquals(s != null, true);
		request.setParameter("emailId", "girish.lalwani@impetus.co.in");
		request.setParameter("password", "wrongpassword");
		String incorrectPassword = loginControllerTest.loginUser(model,
				request, response);
		assertEquals("Login", incorrectPassword);
	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.UserController#updateProfile(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,org.springframework.ui.ModelMap)}
	 * .
	 */

	@Test
	public void testSaveUpdateProfile() {
		request.setParameter("emailId", "abc@impetus.co.in");
		request.setParameter("password", "aaaa");
		request.setParameter("firstName", "abc");
		request.setParameter("lastName", "def");
		request.setParameter("confirmPassword", "aaaa");
		request.setParameter("city", "gwalior");
		String success = loginControllerTest.updateProfile(request, response,
				model);
		assertEquals("index", success);

	}

	/**
	 * Test method for
	 * {@link com.stockportfoliosystem.controller.UserController#logoutUser(javax.servlet.http.HttpServletRequest)}
	 * .
	 */
	@Test
	public void testlogoutUser() {
		String logout = loginControllerTest.logoutUser(request);
		assertEquals("index", logout);

	}
}
