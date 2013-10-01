package com.stockportfoliosystem.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * @author Girish Lalwani
 * 
 */
@Entity
@Table(name="COMPANYDETAILS")

public class CompanyDetails {
	     /**
	     * Declared companyId field corresponding to companyId in table COMPANYDETAILS
	     */
	    @Id
	     @Column(name="companyId",nullable=false)
	     @GeneratedValue
	     private String companyId;
	
	     /**
	     * Declared companyName field corresponding to companyName in table COMPANYDETAILS
	     */
	    @Column(name="companyName")
	     private String companyName;

        
       
         /**
         * Declared totalShares field corresponding to totalShares in table COMPANYDETAILS
         */
        @Column(name="totalShares")
	     private int totalShares;
         

         /**
         * OneToMany mapping of hibernate b\w CompanyDetails & StockHistory
         */
        @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
     	 @javax.persistence.JoinColumn(name ="companyId")
         private Set<StockHistory> stockHistories = new HashSet<StockHistory>(0);
         
         /**
         * OneToMany mapping of hibernate b\w CompanyDetails & Portfolio
         */
        @OneToMany(cascade = CascadeType.ALL)
     	 @javax.persistence.JoinColumn(name ="companyId")
         private Set<PortfolioDetails> portfolioDetails = new HashSet<PortfolioDetails>(0);
         
          /**
           * This method is used to get StockHistories from StockHistory table
         * @return stockHistories
         */
        public Set<StockHistory> getStockHistories()
     	  {
     		return stockHistories;
     	  }
         
         /**
          * This method is used to set StockHistories for StockHistory table
         * @param stockHistories
         */
        public void setStockHistories(Set<StockHistory> stockHistories) {
     		this.stockHistories = stockHistories;
     	}
        
     
     	
     
		/**
		 * This method is used to get the CompanyId from StockHistory table 
		 * @return companyId
		 */
		public String getCompanyId(){
			return companyId;
		}

		/**
		 * This method is used to set the CompanyName for StockHistory table 
		 * @param companyId
		 */
		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}

		/**
		 * This method is used to get the CompanyName from StockHistory table 
		 * @return companyName
		 */
		public String getCompanyName() {
			return companyName;
		}

		

	

		

		/**
		 * This method is used to get the TotalShares of the company
		 * @return totalShares
		 */
		public int getTotalShares() {
			return totalShares;
		}

		/**
		 * This method is used to set the total shares value
		 * @param totalShares
		 */
		public void setTotalShares(int totalShares) {
			this.totalShares = totalShares;
		}

		/**
		 * This method is used to get  portfolios of particular company
		 * @return the portfolios
		 */
		
		public Set<PortfolioDetails> getPortfolios() {
			return portfolioDetails;
		}

		/**
		 * This method is used to set  portfolios of particular company
		 *
		 * @param portfolioDetails the portfolios to set
		 */
		public void setPortfolios(Set<PortfolioDetails> portfolioDetails) {
			this.portfolioDetails = portfolioDetails;
		}

	
}
