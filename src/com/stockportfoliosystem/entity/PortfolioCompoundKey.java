/**
 * 
 */
package com.stockportfoliosystem.entity;
/**
 * @author Girish Lalwani
 * 
 */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class PortfolioCompoundKey implements Serializable{

    
	/**
	 *Declared variable portfolioId
	 */
	private String portfolioId;
	
	/**
	 * Declared variable companyId
	 */
	private String companyId;

    
    /**
     * Parameterized constructor
     * @param companyId
     */
    public PortfolioCompoundKey(String companyId) {
		super();
		this.companyId = companyId;
	}

	/**
	 * Declared userDetails variable
	 * Defining the many to one mapping between userDetails and portfolio
	 */
	@SuppressWarnings("unused")
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="portfolioId",insertable=false,updatable=false)
    private  UserDetails userDetails;
    
    /**
     * Declared variable companyDetails
     * Defined the many to one mapping between companyDetails and portfolio
     */
    @SuppressWarnings("unused")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="companyId",insertable=false,updatable=false) 
    private CompanyDetails companyDetails;
    
    /**
     * @param portfolioId
     * @param companyId
     */
    public PortfolioCompoundKey(String portfolioId,String companyId) {
    this.companyId=companyId;
    this.portfolioId=portfolioId;
    }

	/**
	 * Default constructor
	 */
	public PortfolioCompoundKey() {
	
	}

	/**
	 * @return the portfolioId
	 */
    @Column(name="portfolioId")
	public String getPortfolioId() {
		return portfolioId;
	}

    
	/**
	 * @param portfolioId the portfolioId to set
	 */
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	/**
	 * @return the companyId
	 */
	@Column(name="companyId")
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

  
}