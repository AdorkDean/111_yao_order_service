package com.rc.openapi.util;

import java.util.ResourceBundle;


/**
 * @author 作者 尹滨  E-mail:yinbinhome@163.com
 * @version 创建时间：Mar 8, 2010 11:44:29 AM
 * 类说明 提取指定资源文件key=>value
 */
public class InfoUtil {
	
	private static InfoUtil instance = null;

	private InfoUtil() {

	}

	public synchronized static InfoUtil getInstance() {
		if (instance == null) {
			instance = new InfoUtil();
		}
		return instance;
	}
	
	
	public String getInfo(String name,String key){
		ResourceBundle rb = ResourceBundle.getBundle(name);
		return rb.getString(key);
	}
	
}
