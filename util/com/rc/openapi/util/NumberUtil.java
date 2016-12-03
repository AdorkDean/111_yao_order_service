package com.rc.openapi.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * @author 作者 尹滨 E-mail:yinbinhome@163.com
 * @version 创建时间：Mar 18, 2010 11:09:13 PM 类说明
 */
public class NumberUtil {
	// java自带
	// 判断是否为数字，正整数
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	// 正则表达式
	// 判断是否为数字，正整数
	public static boolean isNumericByPattern(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	// ascii码
	// 判断是否为数字，正整数
	public static boolean isNumericByAscii(String str) {
		for (int i = str.length(); --i >= 0;) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}
	
	/**
     * BigDecimal 转换String 保留两位小数
     * @param value
     * @return
     */
	public static String format2String(BigDecimal bd) {
		if(bd!=null){
			bd = bd.setScale(2, RoundingMode.HALF_UP);
			return bd.toString();
		}else{
			return null;
		}
	}
	
	/**
     * Long 转换String 
     * @param value
     * @return
     */
	public static String long2String(Long l) {
		if(l!=null){
			return String.valueOf(l.longValue());
		}else{
			return null;
		}
	}

	/**
     * String 转换Long 
     * @param value
     * @return
     */
	public static Long string2Long(String str) {
		if(StringUtils.hasText(str)){
			return Long.parseLong(str);
		}else{
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(NumberUtil.isNumeric("11111"));
		System.out.println(Double.parseDouble(".09"));
	}

}
