package com.stockportfoliosystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.stockportfoliosystem.entity.UserDetails;
import com.stockportfoliosystem.exception.StockPortfolioServiceException;
import com.stockportfoliosystem.service.UserService;
/**
 * @author Girish Lalwani
 * 
 */
@Controller
public class UserController {
	/**
	 * Instantiated Logger
	 */
	private Logger logger = LogManager
			.getLogger(UserController.class.getName());
	/**
	 * 
	 */
	private SimpleMailMessage simpleMailMessage;

	/**
	 * Declared mailSender used to send the message at user's mail
	 */
	private MailSender mailSender;
	/**
	 * Created the constant to hold the index page
	 */
	private static final String index = "index";
	/**
	 * Created the reference of UserService
	 * 
	 */
	private UserService userService;
	/**
	 * Created string constant for storing the exception message
	 */
	private static final String EXCEPTIONMSG = "currently service not available please try later";

	/**
	 * This method is used for autowiring the UserService in UserController
	 * 
	 * @param userService for autowiring of userservice in UserController
	 */
	@Autowired
	void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * This method is used to get the message for mail
	 * 
	 * @return the simpleMailMessage
	 */
	public SimpleMailMessage getSimpleMailMessage() {
		return simpleMailMessage;
	}

	/**
	 * This method is used for autowiring of MailSender
	 * 
	 * @param mailSender
	 *            the mailSender to set
	 */
	@Autowired
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * This method is used to set the message for mail
	 * 
	 * @param simpleMailMessage
	 * 
	 */
	@Autowired
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	/**
	 * This method is used to show the front page
	 * @return the Home page
	 */
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String showFrontPage() {
         return "index";
	}

	/**
	 * This method is used to show the login page
	 * @return Login page
	 */
	@RequestMapping(value = "/Login.do", method = RequestMethod.GET)
	public String showLoginForm() {
		return "Login";
	}

	/**
	 * This method is used to show the registration page
	 * @return Registration page
	 */
	@RequestMapping(value = "/Registration.do", method = RequestMethod.GET)
	public String showRegistrationForm() {
		return "Registration";
	}

	/**
	 * This method is used to show the update profile page 
	 * @return Updateprofile page
	 */
	@RequestMapping(value = "/updateprofile.do", method = RequestMethod.GET)
	public String showUpdateProfile() {
		return "updateprofile";

	}

	/**
	 * This method is used to update the profile of user
	 * 
	 * @param req
	 * @param res
	 * @param model
	 * @return home page if updation of profile is successful otherwise update profile page
	 */
	@RequestMapping(value = "/saveUpdateProfile.do", method = RequestMethod.POST)
	public String updateProfile(HttpServletRequest req,
			HttpServletResponse res, ModelMap model) {

		String firstName = (String) req.getParameter("firstName");

		String lastName = (String) req.getParameter("lastName");

		String emailId = (String) req.getParameter("emailId");

		String password = (String) req.getParameter("password");

		String confirmPassword = (String) req.getParameter("confirmPassword");

		String city = (String) req.getParameter("city");
		// calling the loginService from loginController by passing emailId
		UserDetails userDetails = userService.getUserDetails(emailId);
		// checking that firstName is not null and blank
		if (firstName != null && (!firstName.equals(""))) {
			userDetails.setFirstName(firstName);
		}
		// checking that lastName is not null and blank
		if (lastName != null && (!lastName.equals(""))) {
			userDetails.setLastName(lastName);
		}
		// checking that password is not null and blank
		if (password != null && (!password.equals(""))) {
			userDetails.setPassword(password);
		}
		// checking that confirmPassword is not null and blank
		if (confirmPassword != null && (!confirmPassword.equals(""))) {
			userDetails.setConfirmPassword(confirmPassword);
		}
		// checking that city is not null and blank
		if (city != null && (!city.equals(""))) {
			userDetails.setCity(city);
		}
		try {
			// calling the loginService update user from loginController
			userService.updateProfile(userDetails, emailId);
		} catch (StockPortfolioServiceException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(EXCEPTIONMSG, e);
		}
		return "index";
	}

	/**
	 * This method is used to registered the user
	 * 
	 * @param req
	 * @param res
	 * @return index if user registered successfully otherwise registration Page
	 * 
	 */
	@RequestMapping(value = "/SaveUser.do", method = RequestMethod.POST)
	public String saveUser(HttpServletRequest req, HttpServletResponse res,
			ModelMap model) {

		String firstName = (String) req.getParameter("firstName");

		String lastName = (String) req.getParameter("lastName");

		String emailId = (String) req.getParameter("emailId");

		String password = (String) req.getParameter("password");

		String confirmPassword = (String) req.getParameter("confirmPassword");

		String city = (String) req.getParameter("city");
		// instantiating userDetails object
		UserDetails userDetails = new UserDetails();
		// fedding the data into userDetails object
		userDetails.setFirstName(firstName);
		userDetails.setLastName(lastName);
		userDetails.setPassword(password);
		userDetails.setConfirmPassword(confirmPassword);
		userDetails.setEmailId(emailId);
		userDetails.setCity(city);
		logger.debug(userDetails);
		try {
			// registering the user into database
			userService.saveUser(userDetails);
		} catch (StockPortfolioServiceException e) {

			logger.error(e.getMessage(), e);
			// setting the error message in registerationError if user already
			// exist

			model.addAttribute("registerationError", e.getErrorMessage());
			// if failure returns the registration page
			return "Registration";
		} catch (Exception e) {
			logger.error(EXCEPTIONMSG, e);
		}
		// if success returns the index page
		return index;
	}

	/**
	 * This method is used to check that user is valid for login or not if he is valid than return the control to home page otherwise to login page
	 * @param model
	 * @param req
	 * @param res
	 * @return Home page if credentials are correct otherwise login page
	 */
	@RequestMapping(value = "/SubmitLogin.do", method = RequestMethod.POST)
	public String loginUser(ModelMap model, HttpServletRequest req,
			HttpServletResponse res) {
		String errMsg = "";
		String emailId = req.getParameter("emailId");
	
		String password = req.getParameter("password");
		UserDetails userDetails = new UserDetails();

		// Here we get the User from database

		userDetails = userService.getUserDetails(emailId);

		if (userDetails != null) {

			if (!(password.equals(userDetails.getPassword()))) {

				errMsg = "INCORRECT PASSWORD";
				model.addAttribute("errMsg", errMsg);
				return "Login";

			} else {
				logger.debug("maintaining a session in login controller");
				HttpSession session = req.getSession();
				session.setAttribute("emailStr", emailId);
			}

		}

		else {
			errMsg = "NOT EXISTS";
		}

		if (errMsg != null && (!errMsg.equals(""))) {

			model.addAttribute("errMsg", errMsg);
			logger.debug(errMsg);
		}
		return index;

	}

	/**
	 * This method is used to logout the particular user
	 * 
	 * @param req
	 * @return String view name for the user where the user has been forwarded
	 *         after pressing logout button
	 */
	@RequestMapping(value = "/Logout.do", method = RequestMethod.GET)
	public String logoutUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return index;
	}
	@RequestMapping(value = "/ForgetPassword.do", method = RequestMethod.GET)
	public String showForgetPasswordPage() {
         return "ForgetPassword";
	}
	@RequestMapping(value = "/getMailOnForgetPassword.do", method = RequestMethod.POST)
	public String getMailOnForgetPassword(ModelMap model, HttpServletRequest req,
			HttpServletResponse res) {
       
		    UserDetails userDetails = new UserDetails();

             String emailId=req.getParameter("emailId");
             System.out.println(emailId);
             userDetails = userService.getUserDetails(emailId);
             
             SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);
             message.setTo(emailId);
             message.setText(userDetails.getPassword());
				
              System.out.println(userDetails.getPassword());
				mailSender.send(message);
            return index;

         
	}
	



}
