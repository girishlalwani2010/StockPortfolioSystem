package com.stockportfoliosystem.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.stockportfoliosystem.entity.CompanyDetails;
import com.stockportfoliosystem.exception.StockPortfolioServiceException;
import com.stockportfoliosystem.service.CompanyService;
import com.stockportfoliosystem.service.PortfolioService;
/**
 * @author Girish Lalwani
 * 
 */
@Controller
public class Companycontroller {
 
	/**
	 * Instantiating logger
	 */
	private Logger logger = LogManager.getLogger(Companycontroller.class.getName());
	/**
	 * Declare company service
	 */
	private CompanyService companyService;
	/**
	 * Declare portfolio service
	 */
	private PortfolioService portfolioService;

	/**
	 * 
	 This method is used for autowiring of CompanyService in Companycontroller
	 */
	@Autowired
	void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	/**
	 * This method is used for autowiring of portfolioService in Companycontroller
	 * @param portfolioService
	 */
	@Autowired
	void setPortfolioService(PortfolioService portfolioService) {
		this.portfolioService = portfolioService;
	}

	/**
	 * This method is used for searching the company by its name or its ticker
	 * symbol
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * @return  Search Page if companyName entered is correct otherwise error Page
	 */

	@RequestMapping(value = "/Search.do", method = RequestMethod.GET)
	public String getCompanyDetails(ModelMap model, HttpServletRequest req,
			HttpServletResponse res) {
		/* Getting the companyName from search button on gui */
		String companyName = (String) req.getParameter("companyName");

		logger.debug("In companyController inside getCompanydetails companyName = "
				+ companyName);

		/* Instantiating companyDetails */
		CompanyDetails companyDetails = new CompanyDetails();

		try {
			companyDetails = companyService.getCompanyDetails(companyName);
		} catch (StockPortfolioServiceException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error("currently service not available please try later", e);
		}

		/* Checking the companyDetails is it null or not */
		if (companyDetails != null) {
			float currentPrice = 0f;
			model.addAttribute(companyDetails);
			logger.debug(companyDetails.getCompanyName());

			try {
				currentPrice = portfolioService
						.getCurrentPriceById(companyDetails.getCompanyId());
			} catch (StockPortfolioServiceException e) {
				logger.error(e.getMessage(), e);
			}
			if (currentPrice != 0) {
				model.addAttribute("currentPrice", currentPrice);
			}

			return "search";
		}

		String errMsg = "COMPANY DOES NOT EXIST";
		/* Setting the error message to model */
		model.addAttribute("errMsg", errMsg);
		/* Returning the error page if company does not exist in database */
		return "error";

	}

	/**
	 * This method is used for giving the sugessions to user about available
	 * companies
	 * 
	 * @return the string contains the suggetions of companyName and companyId
	 */
	@RequestMapping(value = "/autocomplete.do", method = RequestMethod.POST)
	@ResponseBody
	public String getCompanyNameAndIdList(){
		// Created the companynameandidlist String to hold all company names and
		// their ticker symbols
		String companyNameAndIdList = "";
		logger.debug("In getCompanyNameIdList() in CompanyController");

		companyNameAndIdList = companyService.getCompanyNameAndIdList();
		logger.debug("returning the companyNameAndIdList when write something on search textbox");

		return companyNameAndIdList;

	}

}
