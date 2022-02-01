package com.crm.GenericLibrary;

import java.util.Date;
import java.util.Random;

public class JavaUtility {
	/**
	 * This class consist of all java utilityt
	 * @author isabe
	 * 
	 */
	/**
	 * This method generate the random number and return to the caller
	 * @return
	 */
	public int getRandomNum()
	{
		Random r = new Random();
		int randomNum = r.nextInt(1000);
		return randomNum;
	}
	/**
	 * This method return the current date 
	 * @return
	 */
	public String getCurrentDate() 
	{
		Date date = new Date();
		String currentDate = date.toString();
		return currentDate;
	}
	/**
	 * This method return the current date in specific format
	 * @return
	 */
	public String getFinalDateFormat()
	{
		Date date = new Date();
		String d = date.toString();
		String[] dte = d.split("");
		String YYYY = dte[5];
		String DD = dte[2];
		String MM = dte[1];
		String today = YYYY+"-"+MM+"-"+DD;
		return today;
	}
}
