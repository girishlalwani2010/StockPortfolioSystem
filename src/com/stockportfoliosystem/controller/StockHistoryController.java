package com.stockportfoliosystem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.stockportfoliosystem.exception.StockPortfolioServiceException;
import com.stockportfoliosystem.service.CompanyService;
import com.stockportfoliosystem.service.StockHistoryService;
import com.stockportfoliosystem.util.Utility;

/**
 * @author Girish Lalwani
 * 
 */
@Controller
public class StockHistoryController {

	/**
	 * Instantiated logger
	 */
	private Logger logger = LogManager.getLogger(StockHistoryController.class
			.getName());
	/**
	 * Created the reference of StockHistoryService
	 */
	private StockHistoryService stockHistoryService;
	/**
	 * Created the constant ZEROTIME
	 */
	private static final String ZEROTIME = " 00:00:00";
	/**
	 * Created the reference of CompanyService
	 */
	@Autowired
	private CompanyService companyService;

	/**
	 * This method is used for autowiring StockHistoryService in
	 * StockHistoryController
	 * 
	 * @param stockHistoryService
	 *            used for autowiring of StockHistoryService into
	 *            StockHistoryController
	 * 
	 */
	@Autowired
	void set(StockHistoryService stockHistoryService) {
		this.stockHistoryService = stockHistoryService;
	}

	/**
	 * This method is used to get the data in string format for plotting the
	 * graph
	 * 
	 * @param response
	 * @param req
	 * @param model
	 * @return data in string format for constructing graph
	 * 
	 */
	@RequestMapping(value = "seegraph.do", method = RequestMethod.GET)
	@ResponseBody
	public String getGraphData(HttpServletResponse response,
			HttpServletRequest req, ModelMap model) {
		String graphData = "";
		try {

			String companyId = (String) req.getParameter("companyId");
			String fromDate = req.getParameter("fromDate");
			String toDate = req.getParameter("toDate");
			String dayGraph1 = req.getParameter("dayGraph");
			boolean dayGraph = Boolean.parseBoolean(dayGraph1);
			int noOfDays;
			logger.debug("In try of StockHistoryController");
			java.util.Date utilDate = new java.util.Date();
			Calendar cal = Calendar.getInstance();
			java.sql.Date fDate = new java.sql.Date(cal.getTime().getTime());
			java.sql.Date tDate = new java.sql.Date(cal.getTime().getTime());
			if (dayGraph) {
				// Convert java.util.date to java.sql.Date
				fDate = new java.sql.Date((new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").parse(new java.sql.Date(utilDate
						.getTime()) + ZEROTIME)).getTime());
				tDate = new java.sql.Date((new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").parse(new java.sql.Date(utilDate
						.getTime()) + ZEROTIME)).getTime());

			} else {
				fDate = new java.sql.Date(
						(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
								.parse(fromDate + ZEROTIME)).getTime());
				logger.debug(toDate + "\n" + fromDate + "\n");
				tDate = new java.sql.Date(
						(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
								.parse(toDate + ZEROTIME)).getTime());

			}
			// This is used to calculate the no.of days between from date and to
			// date
			noOfDays = Utility.getNumberOfDays(fDate, tDate);

			model.addAttribute("noOfDays", noOfDays);
			logger.debug("days difference between from date and to date"
					+ noOfDays);
			try {
				graphData = stockHistoryService.getGraphData(companyId, fDate,
						tDate, noOfDays);
			} catch (StockPortfolioServiceException e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			logger.error("exception in StockHistoryController", e);
		}

		model.addAttribute("chart", graphData);
		logger.debug("in stock history controller" + graphData);
		return graphData;

	}

	/**
	 * This method is for viewing graph options like the user want to interval
	 * or day graph
	 * 
	 * @param model
	 * @return return the page which contains the two datepicker and options for
	 *         user to see interval graph or day graph
	 * @throws ParseException
	 * 
	 */
	@RequestMapping(value = "graph.do", method = RequestMethod.GET)
	public String showGraphPage(ModelMap model) {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Timestamp fTimeStamp = new java.sql.Timestamp(0);
		try {
			fTimeStamp = new java.sql.Timestamp(new java.sql.Date(
					(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(new java.sql.Date(utilDate.getTime())
									+ " 11:00:00")).getTime()).getTime());
		} catch (ParseException e) {
			logger.error("currently service not available please try later", e);
		}

		model.addAttribute("currentDate", fTimeStamp);
		return "graph";

	}

	/**
	 * This method is used for autosuggestion for company names and their ticker
	 * symbols
	 * 
	 * @return company name and id List for auto suggesion
	 * 
	 */
	@RequestMapping(value = "/autocomplete1.do", method = RequestMethod.POST)
	@ResponseBody
	public String getCompanyNameAndIdList() {

		String companyNameAndIdList = "";
		companyNameAndIdList = companyService.getCompanyNameAndIdList();
		return companyNameAndIdList;

	}

}
