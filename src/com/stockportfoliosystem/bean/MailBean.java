/**
 * 
 */
package com.stockportfoliosystem.bean;
/**
 * @author Girish Lalwani
 * 
 */
public class MailBean {

	/**
	 * This field contains the email address for user
	 */
	private String emailId;

	/**
	 * For setting percentageVariation
	 */
	private float percentageVariation;

	/**
	 * For getting current price updation
	 */
	private float currentPrice;

	/**
	 * Store the price Price at which the user tracked the record of particular company
	 */
	private float trackedPrice;

	/**
	 * Password field to store password
	 */
	private String password;

	/**
	 * CompanyName field
	 */
	private String companyName;

	/**
	 * CompanyId field
	 */
	private String companyId;

	/**
	 * This method is used for fetching the companyId of company for which the
	 * user specify the alert percentage
	 * 
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * This method is used to set the companyId in DB
	 * 
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * This method is used for getting the companyName
	 * 
	 * @return  companyName 
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * This method is used for setting the companyName
	 * 
	 * @param companyName to set the companyName
	 * 
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * This method is used for fetching the password from another entity or from
	 * itself
	 * 
	 * @return Password which is entered by user during sign up
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * This method is used to set the password
	 * 
	 * @param password to set the password
	 * 
	 */

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method is used for getting the emailId
	 * 
	 * @return  emailId which is entered by user during sign up
	 */

	public String getEmailId() {
		return emailId;
	}

	/**
	 * This method is used to set the emailId
	 * 
	 * @param emailId to set the emailId
	 * 
	 */

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * This method is used to get the percentage change specify by the user
	 * 
	 * @return PercentageVariation which is specified by user
	 */
	public float getPercentageVariation() {
		return percentageVariation;
	}

	/**
	 * This method is used to set the percentage change
	 * @param percentageVariation
	 */
	public void setPercentageVariation(float percentageVariation) {
		this.percentageVariation = percentageVariation;
	}

	/**
	 * This method is used to get the current price
	 * 
	 * @return currenprice of particular stock
	 */
	public float getCurrentPrice() {
		return currentPrice;
	}

	/**
	 * This method is used to set the current price
	 * 
	 * @param currentPrice currenprice of particular stock
	 * 
	 */

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}

	/**
	 * This method is used to get the tracked price
	 * 
	 * @return  trackedPrice a price at which user track the stock
	 */
	public float getTrackedPrice() {
		return trackedPrice;
	}

	/**
	 * This method is used to set the tracked price
	 * 
	 * @param trackedPrice a price at which user track the stock
	 * 
	 */

	public void setTrackedPrice(float trackedPrice) {
		this.trackedPrice = trackedPrice;
	}

}
