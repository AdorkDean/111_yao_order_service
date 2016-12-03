package com.rc.openapi.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Created by wangsheng on 2015/3/26.
 */
public class SpringServiceUtils {

    private static final Logger log = Logger.getLogger(SpringServiceUtils.class);

    public static ClassPathXmlApplicationContext classPathXmlApplicationContext;

    static{
        init();
    }

    public static void init()
    {
        try {
            classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext-*.xml");
            log.info( "SpringServiceUtils.classPathXmlApplicationContext -init successful!\n" + classPathXmlApplicationContext.toString() );
        }catch ( Exception e ){
            log.error( e );
        }
    }


    public static <T> T getBean( ServletContext servletContext, String name, Class<T> type )
    {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        return (T)webApplicationContext.getBean( name, type );
    }
    public static <T> T getBean( HttpServlet httpServlet, String name, Class<T> type )
    {
        return getBean( httpServlet.getServletContext(), name, type );
    }

    public static <T> T getBean( String springXml, String name, Class<T> type )
    {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(springXml);
        return (T) classPathXmlApplicationContext.getBean(name, type);
    }

    public static <T> T getBean( String name, Class<T> type )
    {
        try {
            if( null == classPathXmlApplicationContext ) {
                log.warn( "SpringServiceUtils.classPathXmlApplicationContext is null!" );
                classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext-*.xml");
            }
        }catch ( Exception e ){
            log.error( e );
            e.printStackTrace();
        }

        return (T) classPathXmlApplicationContext.getBean(name, type);
    }

    public static ClassPathXmlApplicationContext getClassPathXmlApplicationContext() {
        return classPathXmlApplicationContext;
    }

    public static void setClassPathXmlApplicationContext(ClassPathXmlApplicationContext classPathXmlApplicationContext) {
        SpringServiceUtils.classPathXmlApplicationContext = classPathXmlApplicationContext;
    }
}
