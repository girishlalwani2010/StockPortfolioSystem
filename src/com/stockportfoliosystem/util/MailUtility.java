package com.stockportfoliosystem.util;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.stockportfoliosystem.bean.MailBean;
import com.stockportfoliosystem.service.PortfolioService;
/**
 * @author Girish Lalwani
 * 
 */
@Component
public class MailUtility {

	/**
	 * Instantiated Logger
	 */
	private Logger logger = LogManager.getLogger(MailUtility.class.getName());

	/**
	 * Instantiated Portfolio Service
	 */
	private PortfolioService portfolioService;
    
	/**
	 * Created the constant
	 */
	private static final int HUNDRED=100;
	/**
	 * This method is used for autowiring the portfolioService
	 * @param portfolioService
	 */
	@Autowired
	public void setPortfolioService(PortfolioService portfolioService) {
		this.portfolioService = portfolioService;
	}

	/**
	 * Declared mailSender used to send the message at user's mail
	 */
	private MailSender mailSender;

	/**
	 * Declared simpleMailMessage variable
	 */
	private SimpleMailMessage simpleMailMessage;

	/**
	 * This method is used for autowiring of MailSender
	 * 
	 * @param mailSender
	 *            the mailSender to set
	 */
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
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
	 * This method is used to set the message for mail
	 * 
	 * @param simpleMailMessage
	 * 
	 */
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	/**
	 *This method is used to send the mail
	 * 
	 * @throws MailSendException
	 */
	public void sendMail() throws MailSendException {
		try {

			List<MailBean> mailBeanList = portfolioService.getMailInfo();
			SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);
			MailBean mailBean = new MailBean();
			Iterator<MailBean> mailIterator = mailBeanList.iterator();

			logger.debug(mailBeanList.size());
			while (mailIterator.hasNext()) {
				mailBean = new MailBean();
				mailBean = mailIterator.next();

				message.setTo(mailBean.getEmailId());
				StringBuffer st = new StringBuffer();
				st.append("Hi ");
				st.append(" ").append(mailBean.getEmailId());

				st.append(" Stock price of company having CompanyId = ")
						.append(mailBean.getCompanyId());
				st.append(" has varied from");
				st.append(mailBean.getTrackedPrice());

				DecimalFormat df = new DecimalFormat("#.##");
				st.append("by the percentage change that you have specified ")
						.append(df.format(mailBean.getPercentageVariation()
								* HUNDRED / mailBean.getTrackedPrice()));

				message.setText(st.toString());
				logger.debug("emaill=== " + mailBean.getEmailId());

				mailSender.send(message);
				logger.debug("mail has been send successfully");
			}
		} catch (Exception e) {
			logger.error(e);
		}

	}
}
