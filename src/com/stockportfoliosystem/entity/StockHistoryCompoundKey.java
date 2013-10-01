/**
 * 
 */
package com.stockportfoliosystem.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Embeddable;
/**
 * @author Girish Lalwani
 * 
 */
@SuppressWarnings("serial")
@Embeddable
 public class StockHistoryCompoundKey implements Serializable{
        
	
	    
	    private Timestamp timeStamp;
	    private String companyId;
	    /**
	     * Default constructor of StockHistoryCompoundKey
	     */
	    public StockHistoryCompoundKey() {
			super();
			
		}

	
	    
	   
	    
	    /**
	     * parameter constructor of StockHistoryCompoundKey
	     * @param timeStamp
	     * @param companyId
	     */
	    public StockHistoryCompoundKey(Timestamp timeStamp,String companyId) {
	    this.companyId=companyId;
	    this.timeStamp=timeStamp;
	    }

		




		/**
		 * @return String 
		 */
		public String getCompanyId() {
			return companyId;
		}

		/**
		 * @param companyId the companyId to set
		 */
	   
	    public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}





		/**
		 * @return the timeStamp
		 */
		public Timestamp getTimeStamp() {
			return timeStamp;
		}





		/**
		 * @param timeStamp the timeStamp to set
		 */
		public void setTimeStamp(Timestamp timeStamp) {
			this.timeStamp = timeStamp;
		}





	  
		
		

		
	  
	}

