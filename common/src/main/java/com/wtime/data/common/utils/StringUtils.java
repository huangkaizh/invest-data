package com.wtime.data.common.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {
	/**
	 * 判断字符串是否是数字
	 * @param string
	 * @return
	 */
	public static boolean isNumeric(String string) {
        if (string == null || string.length() == 0)
            return false;

        int l = string.length();
        for (int i = 0; i < l; i++) {
            if (!Character.isDigit(string.codePointAt(i)))
                return false;
        }
        return true;
    }
	/**
	 * 替换字符串
	 * @param s
	 * @param sub
	 * @param with
	 * @return
	 */
	public static String replace(String s, String sub, String with) {
		int c = 0;
		int i = s.indexOf(sub, c);
		if (i == -1) {
			return s;
		}
		int length = s.length();
		StringBuilder sb = new StringBuilder(length + with.length());
		do {
			sb.append(s.substring(c, i));
			sb.append(with);
			c = i + sub.length();
		} while ((i = s.indexOf(sub, c)) != -1);
		if (c < length) {
			sb.append(s.substring(c, length));
		}
		return sb.toString();
	}
	
	/**
	 * 移除字符串
	 * @param s
	 * @param sub
	 * @return
	 */
	public static String remove(String s, String sub) {
		int c = 0;
		int sublen = sub.length();
		if (sublen == 0) {
			return s;
		}
		int i = s.indexOf(sub, c);
		if (i == -1) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length());
		do {
			 sb.append(s.substring(c, i));
			 c = i + sublen;
		 } while ((i = s.indexOf(sub, c)) != -1);
		 if (c < s.length()) {
			 sb.append(s.substring(c, s.length()));
		 }
		 return sb.toString();
	}
	/**
	 * 截取字符串
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static String sub(String str , String start , String end){
		int pos1 = start == null ? 0 : str.indexOf(start);
		pos1 = pos1 <= 0 ? 0 : pos1 + start.length() ;
		
		int pos2 = end == null ? str.length() : str.indexOf(end, pos1);
		pos2 = pos2 < 0 ? str.length() : pos2 ;
		
		return str.substring(pos1, pos2);
	}
	
	public static List<String> split(String src, String delimiter) {
		int maxparts = (src.length() / delimiter.length()) + 2;		// one more for the last
		int[] positions = new int[maxparts];
		int dellen = delimiter.length();

		int i, j = 0;
		int count = 0;
		positions[0] = - dellen;
		while ((i = src.indexOf(delimiter, j)) != -1) {
			count++;
			positions[count] = i;
			j = i + dellen;
		}
		count++;
		positions[count] = src.length();

		String[] result = new String[count];

		for (i = 0; i < count; i++) {
			result[i] = src.substring(positions[i] + dellen, positions[i + 1]).trim();
		}
		return Arrays.asList(result);
	}
	
	public static int parserInt(String str){
		try{
			return Integer.parseInt(str);
		}catch(Exception e){
			return 0 ;
		}
	}
	
	public static float parserFloat(String str){
		try{
			return Float.parseFloat(str);
		}catch(Exception e){
			return 0 ;
		}
	}
	
	public static void main(String[] args){
		System.out.println(Integer.parseInt("-"));
	}
	public static boolean isEmpty(String param) {
		return param == null || param.trim().isEmpty();
	}
}
