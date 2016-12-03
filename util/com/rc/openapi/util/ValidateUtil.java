package com.rc.openapi.util;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author wind
 * 公共验证类。
 */
public class ValidateUtil {	
	/**
	 * 验证电话号码（传真号码）是否合法
	 * @param phoneNo
	 * @return
	 */
	public static boolean validatePhoneNo(String phoneNo){
		if(phoneNo==null || phoneNo.equals(""))
			return false;
		StringBuffer buf = new StringBuffer("");
		buf.append("(^[0-9]{1,4}\\-[0-9]{3,4}\\-[0-9]{5,}(\\-[0-9]{1,6}){0,1}$)|");
		buf.append("(^[0-9]{3,4}\\-[0-9]{5,}$)|");
		buf.append("(^[0-9]{5,}(\\-[0-9]{1,6}){0,1}$)|");
		buf.append("(^\\([0-9]{3,4}\\)[0-9]{5,}(\\-[0-9]{1,6}){0,1}$)|");
		buf.append("(^\\([0-9]{1,4}\\)\\([0-9]{3,4}\\)[0-9]{5,}(\\-[0-9]{1,6}){0,1}$)");
		
		Pattern re = Pattern.compile(buf.toString());
		Matcher regexMatcher = re.matcher(phoneNo);
		return regexMatcher.find();
	}
	
	/**
	 * 验证手机号是否合法
	 * @param mobileNo
	 * @return
	 */
	public static boolean validateMobileNo(String mobileNo){
		if(mobileNo==null || mobileNo.equals(""))
			return false;
		Pattern re = Pattern.compile("(^[0-9]{1,4}\\-{0,1}1\\d*$)|(^\\([0-9]{1,4}\\)1[0-9]*$)|(^1[0-9]*$)");
		Matcher regexMatcher = re.matcher(mobileNo);
		return regexMatcher.find();
	}
	
	/**
	 * 验证url是否是一个合法的url。
	 */
	public static boolean validateUrl(String url){
		
		if(url==null || url.equals("") || url.length()<=6)
			return false;
		
		String array = url.substring(0,4);
		if(!array.equalsIgnoreCase("http")){
			return false;
		}else{
			char ch = url.charAt(4);
			if(ch=='s' || ch=='S'){
				StringBuffer buf = new StringBuffer("https");
				buf.append(url.substring(5,url.length()));
				url = buf.toString();
			}else{
				String u = url.substring(4,url.length());
				url=array.toLowerCase()+u;
			}				
		}
		
		Pattern re = Pattern.compile("^((http|https)://){0,1}(([a-zA-Z0-9_-]|((\\.){0,1}))+)(:[1-9][0-9]*)?(/[^/]+)*/?$");
		Matcher regexMatcher = re.matcher(url);
		return regexMatcher.find();
	}
	
	/**
	 * 验证日期格式是否合法
	 * @param date
	 * @return
	 */
	public static boolean validateDate(String date){
		Pattern re = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}");
		Matcher regexMatcher = re.matcher(date);
		if(regexMatcher.find()){
			String[] d = date.split("-");
			int year = Integer.parseInt(d[0]);
			if(year<1901 || year>2099){
				return false;
			}
			int month = Integer.parseInt(d[1]);
			if(month<1||month>12){
				return false;
			}
			int day = Integer.parseInt(d[2]);
			if(day<1 || day>31){
				return false;
			}
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	*    
	* 方法描述：验证电话号码  
	* 创建人：hexiaoliang  
	* 创建时间：2010-4-9 下午03:24:15    
	* @param 支持区号3-4位，号码7-8位
	* @return
	* @version   1.0
	*
	 */
	public static boolean validatePhone(String str){
		return str.matches("\\d{3}-\\d{7,8}|\\d{4}-\\d{7,8}");
	}
	/**
	 * 
	*    
	* 方法描述：  
	* 创建人：hexiaoliang  
	* 创建时间：2010-4-9 下午03:26:16    
	* @param args
	* @version   1.0
	*
	 */
	public static boolean validateEmail(String str){
		return str.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	}
	
	/**
	 * 
	*    
	* 方法描述：验证ip地址合法性  
	* 创建人：hexiaoliang  
	* 创建时间：2010-4-9 下午03:47:04    
	* @param str
	* @return
	* @version   1.0
	*
	 */
	public static boolean validateIp(String str){
		if(!str.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")){
			return false;
		}
		else{
			StringTokenizer st=new StringTokenizer(str,".");
			int ip=0;
			while( st.hasMoreElements() ){
				ip = Integer.valueOf(st.nextToken());
				if(ip<0||ip>255){
					
					return false;
				}
			}

			return true;
		}
	}
	
	/**
	 * 
	*    
	* 方法描述：验证身份证号合法性  
	* 创建人：hexiaoliang  
	* 创建时间：2010-4-9 下午04:16:02    
	* @param str-15或18位的身份证，包含最后一位是字母
	* @return
	* @version   1.0
	*
	 */
	public static boolean validateIdCard(String str){
		return str.matches("\\d{14}[0-9a-zA-Z]{1}|\\d{17}[0-9a-zA-Z]{1}");
	}
	/**
	 * 
	*    
	* 方法描述：验证手机号   
	* 创建人：hexiaoliang  
	* 创建时间：2010-4-9 下午08:41:50    
	* @param str
	* @return
	* @version   1.0
	*
	 */
	public static boolean validateMobile(String str){
		if(str == null || str.trim().length() == 0){
			return false;
		}
		return str.matches("[1]\\d{10}");
	}
	
	public static void main(String[] args){

		//System.out.println(validatePhone("0s51-8866185"));
		//System.out.println(validateEmail("sa@ss..lk.com"));
		//System.out.println(validateIp("254.157.12.122"));
		//System.out.println(validateIdCard("83019820901121@"));
		System.out.println(validateMobile("1323466786678"));
		//System.out.println("多as是".matches("[\u4E00-\u9FA5]+"));//匹配中文
		//System.out.println("asd@lk.com".matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"));//匹配email格式
		
		//System.out.println("a1撒旦	1a".matches("^[0-9A-Za-z\u4E00-\u9FA5]+$"));//匹配中英文字符
		//^\w+$//匹配由数字、26个英文字母或者下划线组成的字符串
		//匹配身份证：\d{15}|\d{18}
		//匹配ip地址：\d+\.\d+\.\d+\.\d+
		//匹配中国邮政编码：[1-9]\d{5}(?!\d)
		//匹配国内电话号码：\d{3}-\d{8}|\d{4}-\d{7}
	}
}
