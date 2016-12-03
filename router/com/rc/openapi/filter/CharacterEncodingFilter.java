package com.rc.openapi.filter;

/**  
 * @Title: CharacterEncodingFilter.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2011-5-30 下午03:07:25
 * @version V1.0  
 */

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
		private FilterConfig filterConfig;
		private String encoding=null;
	   
	   public void init(FilterConfig filterConfig){
	      this.filterConfig=filterConfig;
	      encoding=filterConfig.getInitParameter("encoding");
	   }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
				request.setCharacterEncoding(encoding);
				chain.doFilter(request,response);
	}

	public void destroy() {
		
	}
	
}