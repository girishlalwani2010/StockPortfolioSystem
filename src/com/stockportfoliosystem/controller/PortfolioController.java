package com.stockportfoliosystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stockportfoliosystem.entity.CompanyDetails;
import com.stockportfoliosystem.entity.PortfolioDetails;
import com.stockportfoliosystem.entity.PortfolioCompoundKey;


import com.stockportfoliosystem.exception.StockPortfolioServiceException;
import com.stockportfoliosystem.service.CompanyService;
import com.stockportfoliosystem.service.PortfolioService;
/**
 * @author Girish Lalwani
 * 
 */
@Controller
public class PortfolioController {
	/**
	 * Created the refrenced of portfolioService
	 */
	private PortfolioService portfolioService;
	/**
	 * Created the reference of companyService
	 */
	private CompanyService companyService;

	/**
	 * Instantiated logger
	 */
	private Logger logger = LogManager.getLogger(PortfolioController.class
			.getName());
	/**
	 * Created constant HUNDRED
	 */
	private static final int HUNDRED = 100;
	/**
	 * Created String Literal 
	 */
	private String emailStr1 = "emailStr";
	/**
	 * Created String literal which stored the exception message 
	 */
	private static final String EXCEPTIONMSG = "currently service not available please try later";

	/**
	 * This method is used for autowiring PortfolioService in
	 * PortfolioController
	 * 
	 * @param portfolioService used to set portfolioService.
	 */
	@Autowired
	public void setPortfolioService(PortfolioService portfolioService) {
		this.portfolioService = portfolioService;
	}

	/**
	 * This method is used for autowiring CompanyService in PortfolioController
	 * 
	 * @param companyService used for autowiring of CompanyService in portfolio controller
	 * 
	 */
	@Autowired
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	/**
	 * This method is used to add companydetails to portfolio of user which is
	 * currently loged in
	 * 
	 * @param model
	 * @param req
	 * @param res
	 * @return message stock has been added successfully if stock is not already in portfolio otherwise return the message company Already added  
	 */
	@RequestMapping(value = "/portfolio.do", method = RequestMethod.GET)
	@ResponseBody
	public String addToPortfolio(ModelMap model, HttpServletRequest req,
			HttpServletResponse res) {
		String companyName = req.getParameter("companyName");
		String currentPrice = req.getParameter("currentPrice");

		CompanyDetails companyDetails = new CompanyDetails();
		PortfolioDetails portfolio = new PortfolioDetails();
		try {
			// Here we get the companyDetails from database
			companyDetails = companyService.getCompanyDetails(companyName);

			if (currentPrice != null) {
				portfolio.setTrackedPrice(Float.valueOf(currentPrice.trim())
						.floatValue());
			}
			// Getting the session of user
			HttpSession session = req.getSession();
			String emailStr = (String) session.getAttribute(emailStr1);
			logger.debug(emailStr + "\n" + companyDetails.getCompanyId());
			if (companyDetails != null) {
				portfolio.setPortfolioCompoundKey(new PortfolioCompoundKey(
						emailStr, companyDetails.getCompanyId()));
				portfolioService.addToPortfolio(portfolio);
			}
		} catch (StockPortfolioServiceException e) {

			logger.error(e.getMessage(), e);

			return "company Already added";
		}

		catch (Exception e) {
			logger.error(EXCEPTIONMSG, e);
		}

		return "stock has been added successfully";
	}

	/**
	 * This method is used to fetch the data of a particular user's portfolio
	 * 
	 * @param model
	 * @param req
	 * @return portfolio the record of companies which is tracked by user
	 */
	@RequestMapping(value = "/myportfolio.do", method = RequestMethod.GET)
	public String getPortfolioByEmailId(ModelMap model, HttpServletRequest req) {

		List<PortfolioDetails> portfolioDetails = new ArrayList<PortfolioDetails>();

		HttpSession session = req.getSession();
		String emailStr = (String) session.getAttribute("emailStr");
		List<Float> prices = new ArrayList<Float>();
		logger.debug(emailStr);
		try {
			portfolioDetails = portfolioService.getPortfolioByEmailId(emailStr);
		} catch (StockPortfolioServiceException e) {
			logger.error(e.getMessage());
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(), e);
		}
		if (portfolioDetails == null) {
			portfolioDetails = new ArrayList<PortfolioDetails>();
		}

		try {
			prices = portfolioService.getCurrentPriceById(portfolioDetails);
		} catch (StockPortfolioServiceException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(EXCEPTIONMSG, e);
		}

		model.addAttribute("emailStr", emailStr);
		model.addAttribute("prices", prices);
		model.addAttribute("portfolio", portfolioDetails);

		return "myportfolio";
	}

	/**
	 * This method is used to submit the percentage specified by user
	 * 
	 * @param req
	 * @param res
	 * 
	 */
	@RequestMapping(value = "/alert.do", method = RequestMethod.POST)
	public void submitPercentageChange(HttpServletRequest req,
			HttpServletResponse res) {
		String trakedPrice1 = req.getParameter("trackedPrice");
		String companyId = req.getParameter("companyId");
		String percentageChange1 = req.getParameter("PercentageVariation");
		String emailId = req.getParameter("emailStr");
		if ((percentageChange1 != null) && (!percentageChange1.equals(""))) {
			float trakedPrice = Float.valueOf(trakedPrice1.trim()).floatValue();
			float percentageChange = Float.valueOf(percentageChange1.trim())
					.floatValue() * trakedPrice / HUNDRED;

			try {
				portfolioService.submitPercentageChange(companyId,
						percentageChange, emailId);
			} catch (StockPortfolioServiceException e) {
				logger.error(e.getMessage(), e);
			} catch (Exception e) {
				logger.error(EXCEPTIONMSG, e);
			}
			try {
				res.sendRedirect("myportfolio.do");

			} catch (IOException e) {
				logger.error(e.getMessage());
			}

		}
	}

	/**
	 * This method is used to remove the alert specified earlier by user
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping(value = "/removeAlert.do", method = RequestMethod.GET)
	public void removeAlertFromPortfolio(HttpServletRequest req,
			HttpServletResponse res) {
		String companyId = req.getParameter("companyId");
		HttpSession session = req.getSession();
		String emailId = (String) session.getAttribute(emailStr1);
		if (emailId != null) {
			try {
				portfolioService.removeAlertFromPortfolio(emailId, companyId);
			} catch (StockPortfolioServiceException e) {
				logger.error(e.getMessage(), e);
			} catch (Exception e) {
				logger.error(EXCEPTIONMSG, e);
			}
			try {
				res.sendRedirect("myportfolio.do");
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			try {
				res.sendRedirect("index.do");
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * This method is used delete the record from portfolio
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping(value = "/deleteFromPortfolio.do", method = RequestMethod.GET)
	public void deleteRecordFromPortfolio(HttpServletRequest req,
			HttpServletResponse res) {
		String emailId = req.getParameter("emailId");
		String companyId = req.getParameter("companyId");
		try {
			portfolioService.deleteRecordFromPortfolio(emailId, companyId);
		} catch (StockPortfolioServiceException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(EXCEPTIONMSG, e);
		}

		try {
			res.sendRedirect("myportfolio.do");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}

}