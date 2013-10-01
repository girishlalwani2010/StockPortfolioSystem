/**
 * 
 */
package com.stockportfoliosystem.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
/**
 * @author Girish Lalwani
 * 
 */
public final class Utility {
	
	/**
	 * Instantiated the logger
	 */
	private static Logger logger = LogManager.getLogger(Utility.class.getName());
	/**
	 * Declaring Constant TIMECONVERTER
	 */
	private static final int TIMECONVERTER=1000*60*60*24;
	/**
	 * Default Constructor
	 */
	private Utility()
	{
		
	}
	/**
	 * This method is used to get difference between two dates in no.of days
	 * @param fDate 
	 * @param tDate
	 * @return noOfDays in integer
	 */
	public static int getNumberOfDays(Date fDate,Date tDate)
	{
		long timeDiff=(tDate.getTime()-fDate.getTime());
		
		int noOfDays=(int)(timeDiff /(TIMECONVERTER));
			
		return noOfDays;
	}
	/**
	 * This is used to get the StackTrace of the Exception passed to it.
	 * @param e Exception for which stack trace is to be obtained. 
	 * @return Strack Trace of the Exception specified.
	 */
	public static String getStackTrace(Exception e){
		String stackTrace = "";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		e.printStackTrace(ps);
		stackTrace = baos.toString();
		try{
			baos.close();
			ps.close();
		}catch(Exception ex)
		{
		logger.error("In Utility"+ex.getMessage());
		}
		return stackTrace;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		logger.debug(getNumberOfDays(new Date( new Long("1349181892027")), new Date( new Long("1449183893027"))));
	    logger.debug(new Date( new Long("1349181892027")));
	}

}
