/**
 * 
 */
package com.stockportfoliosystem.exception;
/**
 * @author Girish Lalwani
 * 
 */

@SuppressWarnings("serial")
public class StockPortfolioDaoException extends Exception{
	 /**
     * Declared the variable rootCause for storing the rootCause of Exception
     */
    private Exception rootCause;
    /**
     * Declared the variable errorMessage for storing the errorMessage of Exception
     */
    private String errorMessage;
     /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param cause  which contains the actual cause of exception.
     *            
     */
    public StockPortfolioDaoException(final Exception cause) {
	this.rootCause=cause;
    }
      
   
    /**
     * Constructs a new exception with the specified detail message and cause
     * 
     * @param message which contains the message due to which exception occurs
     *           
     * @param cause which contains the actual cause of exception. 
     *            
     */
    public StockPortfolioDaoException(final String message, final Exception cause) {
	this.errorMessage=message;
	this.rootCause=cause;
    }
    

    
    /**
     * toString() method for getting the object in string format
     * @see java.lang.Throwable#toString()
     */
    public String toString()
    {
    	return this.errorMessage +"\n"+this.rootCause.getMessage();
    }


	/**
	 * This method is used for getting the errorMessage
	 * @return errorMessage which contains the message due to which exception occurs.
	 */
	public String getErrorMessage() {
		return this.errorMessage +"\n"+this.rootCause.getMessage();
	}



}
 