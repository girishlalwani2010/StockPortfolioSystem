package com.stockportfoliosystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Girish Lalwani
 * 
 */
@Controller
public class HomeController {

	/**
	 * This method is used for showing the home page
	 * 
	 * @return The home Page
	 */
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String showHomePage() {
		return "index";
	}

}
