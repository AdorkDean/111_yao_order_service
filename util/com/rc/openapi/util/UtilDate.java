package com.rc.openapi.util;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author yinbin 2010-04-17
 * 
 */
public class UtilDate {
	public static String getOrderNum() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date);
	}

	// 获取日期，格式：yyyy-MM-dd HH:mm:ss
	public static String getDateFormatter() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	public static String getDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}

	// 产生随机的三位数
	public static String getThree() {
		Random rad = new Random();
		return rad.nextInt(1000) + "";
	}

	public static final String getDate(Date aDate, String pattern) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(pattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	public static Date strToDate(String strDate, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 取当前月第一天
	 */
	public static Date getFirstDayOfMonth() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date()); // someDate 为你要获取的那个月的时间
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = ca.getTime();
		String dd = getDate(firstDate, "yyyy-MM-dd");
		dd = dd + " 00:00:01";
		Date fdate = strToDateLong(dd);
		return fdate;
	}

	/**
	 * 取当前月最后一天
	 */
	public static Date getLastDayOfMonth() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date()); // someDate 为你要获取的那个月的时间
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = ca.getTime();
		return lastDate;
	}

	/**
	 * @return 加分钟
	 */
	public static String addDate(Date d, int mi) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("今天的日期：" + df.format(d));
		return df.format(new Date(d.getTime() + mi * 60 * 1000));
	}

	/**
	 * @return 减分钟
	 */
	public static String subDate(Date d, int mi) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("今天的日期：" + df.format(d));
		return df.format(new Date(d.getTime() - mi * 60 * 1000));
	}

	/**
	 * 取下一个小时过5分钟
	 * @param date
	 * @return
	 */
	public static Date addHourPassFive(Date date,int m,String n){
		Date d=new Date(date.getTime()+1000*60*60*m);		
		String aa = UtilDate.getDate(d, "yyyy-MM-dd HH:"+n+":00");
		Date d1=strToDate(aa, "yyyy-MM-dd HH:mm:ss");
		return d1;
	}
	
	public static void main(String[] args) {
		Date d = UtilDate.addHourPassFive(new Date(),1,"05");
		System.out.println(d.getTime()-new Date().getTime());	
		System.out.println(1000*60*39);
	}
}
