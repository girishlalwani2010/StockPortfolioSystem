
 
package com.stockportfoliosystem.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.mail.MailSendException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.stockportfoliosystem.service.StockInfoService;
/**
 * @author Girish Lalwani
 * 
 */
/**
 * Created the scheduler for saving the xml data by calling
 * currentStockInfo() and sending the mail if percentage varies from specified
 * limit in every 5 minutes this scheduler runs
 * 
 */
public class Schedulers extends QuartzJobBean {
	private Logger logger = LogManager.getLogger(Schedulers.class.getName());
	private MailUtility mailUtility;
	private StockInfoService stockInfoService;

	/**
	 * This method is used to set StockInfoService
	 * @param stockInfoService
	 */
	public void setStockInfoService(StockInfoService stockInfoService) {
		this.stockInfoService = stockInfoService;
	}

	/**
	 * This method is used to set MailUtility
	 * @param mailUtility
	 */
	public void setMailUtility(MailUtility mailUtility) {
		this.mailUtility = mailUtility;
	}

	/**
	 * This method is used to start the scheduler  
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		logger.debug("Inside excecute internal method of scheduler");
		stockInfoService.currentStockInfo();
		logger.debug("before mail info  in executeInternal");
		try {
			mailUtility.sendMail();
		} catch (MailSendException e) {
		  logger.error(e.getMessage(),e);
		}
		logger.debug("after mail info" + "mailUtility.sendMail()");
	}

}
