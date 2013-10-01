package com.stockportfoliosystem.entity;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * @author Girish Lalwani
 * 
 */
@XmlRootElement(name="stockdetails")
public class StockDetails 
{
	
	/**
	 * Declared the company object of type List
	 */
	private List<Company> company;

	/**
	 * This method is used for setting the List of companies
	 * @param company
	 */
	public void setCompany(List<Company> company) {
		this.company = company;
	}

	/**
	 * This method is used for getting the List of companies
	 * @return The list of companies
	 */
	public List<Company> getCompany() {
		return company;
	}

}
