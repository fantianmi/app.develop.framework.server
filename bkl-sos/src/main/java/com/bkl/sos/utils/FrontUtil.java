package com.bkl.sos.utils;


import org.apache.commons.lang.StringUtils;

import com.bkl.sos.SosConfig;
import com.bkl.sos.constants.Constants;
import com.bkltech.framework.multicoin.exception.MulticoinException;
import com.bkltech.framework.multicoin.message.Transaction;

public class FrontUtil {
	public static void main(String[] args) {
		System.out.println(formatDouble(0.0001));
	}
	public static String formatDouble(double value) {
		///java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		//return df.format(value);
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
		//nf.setMaximumintDigits(10);
		nf.setMaximumFractionDigits(100);
		nf.setGroupingUsed(false);  
		return nf.format(value);
	}
	
	public static String formatRmbDouble(double value) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		return df.format(value);
	}
	
	public static String formatCoinPriceDouble(double value) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.0000");
		return df.format(value);
	}
	
	public static String formatCoinAmountDouble(double value) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("0");
		return df.format(value);
	}
}
