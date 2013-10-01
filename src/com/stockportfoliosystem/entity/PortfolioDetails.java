package com.stockportfoliosystem.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Girish Lalwani
 * 
 */
@Entity
@Table(name="PortfolioDetails")
public class PortfolioDetails {
	         
		     /**
		     * Instantiating portfolio compound key
		     */
		    private PortfolioCompoundKey portfolioCompoundKey = new PortfolioCompoundKey();
		     
		    
		
	         /**
	         * Declared  Variable trackedPrice corresponding to field trackedPrice in table Portfolio
	         */
	        @Column(name="trackedPrice")
		     private float trackedPrice;
		
		   
		

			/**
			 * Declared variable percentageChange corresponding to field percentageChange in table Portfolio
			 */
			@Column(name="percentageChange")
		     private float percentageChange;
	         
	        
		   

			/**
			 * This method is used to set the portfolio compositekey 
		 
			 * @param portfolioCompoundKey  to set the compoundKey
			 */
			
			public void setPortfolioCompoundKey(PortfolioCompoundKey portfolioCompoundKey) {
				this.portfolioCompoundKey = portfolioCompoundKey;
			}

			/**
			 * This method is used to get the portfolio compositekey 
			 * @return the portfolioCompoundKey
			 */
			 @Id
	         @Embedded
            public PortfolioCompoundKey getPortfolioCompoundKey() {
				return portfolioCompoundKey;
			}
			
		

			/**
			 * This method is used to get  the tracked price
			 * @return trackedPrice
			 */
			public float getTrackedPrice() {
				return trackedPrice;
			}

			/**
			 * This method is used to set  the tracked price
			 * @param trackedPrice in float
			 */
			public void setTrackedPrice(float trackedPrice) {
				this.trackedPrice = trackedPrice;
			}

			/**
			 * This method is used to get  the percentageChange
			 * @return percentageChange in float
			 */
			public float getPercentageChange() {
				return percentageChange;
			}

			/**
			 * 
			 * This method is used to set percentageChange
			 * @param percentageChange
			 */
			public void setPercentageChange(float percentageChange) {
				this.percentageChange = percentageChange;
			}

	}
