package com.wtime.data.common.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class NetUtils {
	public static String getDomain(String url) {
		if (url.startsWith("http")) {
			try {
				return new URL(url).getHost().replaceAll("www.", "");
			} catch (MalformedURLException e) {
				return null;
			}
		} else
			return url;
	}
	
	public static void main(String[] args){
		System.out.println(getDomain("http://www.iexpib.com"));
	}
}
