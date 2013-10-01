package com.stockportfoliosystem.entity;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * @author Girish Lalwani
 * 
 */
@XmlRootElement(name="company")
public class Company 
{
	/**
	 * Declared variable companyId
	 */
	private String companyId;
	
	/**
	 * This method is used for getting the companyId
	 * @return companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * This method is used to set the companyId
	 * @param companyId
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * This method is used to get the timeStamp
	 * @return timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * This method is used to set the timeStamp
	 * @param timeStamp
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * This method is used to get the current price
	 * @return price
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * This method is used to set the price
	 * @param price
	 */
	public void setPrice(Float price) {
		this.price = price;
	}

	/**
	 * Declared variable timeStamp
	 */
	private String timeStamp;
	
	/**
	 * Declared variable price
	 */
	private Float price;
	

}
