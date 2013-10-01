package com.stockportfoliosystem.entity;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * @author Girish Lalwani
 * 
 */
@Entity
@Table(name="USERDETAILS")
public class UserDetails{
	
	     /**
	     * Declared field emailId corresponding to emailId in table USERDETAILS
	     */
	     @Id
	     @Column(name="emailId")
	    
	     private String emailId;
	
	     /**
	     * Declared field firstName corresponding to firstName in table USERDETAILS
	     */
	    @Column(name="firstName")
	     private String firstName;

         /**
         * Declared field lastName corresponding to lastName in table USERDETAILS
         */
        @Column(name="lastName")
	     private String lastName;
	
	    /**
	     * Declared field password corresponding to password in table USERDETAILS
	     */
	    @Column(name="password")
	    private String password;
	
	    /**
	     * Declared field confirmPassword corresponding to confirmPassword in table USERDETAILS
	     */
	    @Column(name="confirmPassword")
	    private String confirmPassword;
	
	    /**
	     * Declared field city corresponding to city in table USERDETAILS
	     */
	    @Column(name="city")
	     private String city;
	
	    /**
	     *    
         * OneToMany mapping of hibernate b\w UserDetails & Portfolio
         */
	     
	    @OneToMany(cascade = CascadeType.ALL)
    	@javax.persistence.JoinColumn(name ="emailId")
        private Set<PortfolioDetails> portfolioDetails = new HashSet<PortfolioDetails>(0);
        
		/**
		 * This method is used to return the portfolio of particular user 
		 * @return the set of records of portfolio table 
		 */
		public Set<PortfolioDetails> getPortfolioDetails() {
			return portfolioDetails;
		}

		/**
		 * This method is used to set the portfolio of particular user 
		 * @param portfolioDetails  to set records of portfolio table
		 */
		public void setPortfolioDetails(Set<PortfolioDetails> portfolioDetails) {
			this.portfolioDetails = portfolioDetails;
		}

		/**
		 * @return emailId
		 */
		public String getEmailId() {
			return emailId;
		}

		/**
		 * @param emailId
		 */
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		/**
		 * @return firstName
		 */
		public String getFirstName() {
			return firstName;
		}

		/**
		 * @param firstName
		 */
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		/**
		 * @return lastName
		 */
		public String getLastName() {
			return lastName;
		}

		/**
		 * @param lastName
		 */
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		/**
		 * @return password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * @return confirmPassword
		 */
		public String getConfirmPassword() {
			return confirmPassword;
		}

		/**
		 * @param confirmPassword
		 */
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}

	

		/**
		 * @return city
		 */
		public String getCity() {
			return city;
		}

		/**
		 * @param city
		 */
		public void setCity(String city) {
			this.city = city;
		}

	
	
}
		
	    	






































































































































