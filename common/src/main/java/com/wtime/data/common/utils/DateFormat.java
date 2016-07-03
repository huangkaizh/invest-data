package com.wtime.data.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateFormat {
	
	public static final DateTimeFormatter[] FORMAT;
	public static final SimpleDateFormat DEFAULT_FORMART = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static {
		FORMAT = new DateTimeFormatter[] {
				DateTimeFormat.forPattern("MM-dd"), 
				DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"),
				DateTimeFormat.forPattern("yyyy-MM-dd HH:mm"),
				DateTimeFormat.forPattern("yyyy-MM-dd　HH：mm"),
				DateTimeFormat.forPattern("yyyy-MM-dd"),
				DateTimeFormat.forPattern("yyyy-MM"),
				DateTimeFormat.forPattern("yyyy年MM月dd日 HH:mm:ss"),
				DateTimeFormat.forPattern("yyyy年MM月dd日 HH:mm"),
				DateTimeFormat.forPattern("yyyy年MM月dd日"),
				DateTimeFormat.forPattern("yyyy年MM月dd"),
				DateTimeFormat.forPattern("yyyy年MM月"),
				DateTimeFormat.forPattern("yyyy年"),
				DateTimeFormat.forPattern("yy-MM-dd HH:mm:ss"),
				DateTimeFormat.forPattern("yy-MM-dd HH:mm"),
				DateTimeFormat.forPattern("yy-MM-dd HH"),
				DateTimeFormat.forPattern("yy-MM-dd"),
				DateTimeFormat.forPattern("yyyy年MM月dd日 HH时mm分ss秒"),
				DateTimeFormat.forPattern("yyyy年MM月dd日 HH时mm分"),
				DateTimeFormat.forPattern("MM/dd HH:mm"),
				DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss"),
				DateTimeFormat.forPattern("yyyy/MM/dd HH:mm"),
				DateTimeFormat.forPattern("yyyy/MM/dd"),
				DateTimeFormat.forPattern("yyyy/MMdd"),
				DateTimeFormat.forPattern("yyyy/MM"),				
				DateTimeFormat.forPattern("yyyy-M-D"),
				DateTimeFormat.forPattern("HH:mm:ss"),
				DateTimeFormat.forPattern("HH:mm"), 	
				DateTimeFormat.forPattern("yyyy.MM.dd HH:mm"),
				DateTimeFormat.forPattern("yyyy.M.dd HH:mm"),
				DateTimeFormat.forPattern("yyyy") };
	}

	public static Date toDate(String dateformat) {
		DateTime date = null;
		dateformat = dateformat.trim();
		for (int i = 0; i < FORMAT.length; i++) {
			DateTimeFormatter df = FORMAT[i];
			try {
				date = df.parseDateTime(dateformat);
				if (date.getYear() == 2000 && dateformat.indexOf("yy") < 0) {
					date = date.withYear(Calendar.getInstance().get(Calendar.YEAR));
				}
				return date.toDate();
			} catch (Exception e) {
			}
		}
		return new Date();
	}

	public static String format(String date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(toDate(date));
	}
	
	public static String format(Date date , String format){
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
	public static String format(long timestamp){
		return DEFAULT_FORMART.format(new Date(timestamp));
	}

	public static String format(String date) {
		Date d = toDate(date);
		if (d == null)
			return date;
		else {
			return DEFAULT_FORMART.format(d);
		}
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(format("2015年"));

	}
}
