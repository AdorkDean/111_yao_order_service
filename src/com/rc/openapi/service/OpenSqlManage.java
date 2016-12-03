package com.rc.openapi.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.page.PageWraper;

/**  
 * @Title: OpenSqlManage.java
 * @Description: 
 * @author yinbinhome@163.com  
 * @date 2012-6-27 下午10:43:31
 * @version V1.0  
 */

public interface OpenSqlManage {
	/**
	 * 传入Map 返回包含Map的List
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public List selectForListByMap(Map map,String querySql);
	
	/**
	 * 传入Map 返回一个对象，可以使一个Bean也可以是一个Integer，Long
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public Object selectForObjectByMap(Map map,String querySql);

	/**
	 * 公共分页工具方法，传入条件Map，计算总记录数sqlmap的id，查询结果集sqlmap的id
	 * @param map
	 * @param sql_count 计算总记录数sqlmap的id
	 * @param sql_record 查询结果集sqlmap的id
	 * @param page 页码
	 * @param pagesize 每页显示个数
	 * @return
	 */
	public PageWraper selectForPageByMap(Map map,String sql_count,String sql_record,Integer page,Integer pagesize);
	
	
	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * @param map
	 * @param updatesql sqlmap的id
	 * @return
	 * @throws SQLException 
	 */
	public int updateByMap_drug(Map map, String updatesql) throws SQLException;
	
	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * 
	 * @param List含有Map的list 
	 * @param insertsql 的id
	 * @return
	 * @throws SQLException
	 */
	public String batchInsertByList_drug(List<Object> list, String insertsql) throws SQLException ;
	
	
	/**
	 * 说明:公共插入方法，传入插入记录的sqlmap的id，条件Map，
	 * @param map
	 * @param insertsql
	 * @throws SQLException
	 */
	public void insertByMap_drug(Map map, String insertsql) throws SQLException;
	
	/**
	 * 根据对象查找对象
	 * @param object 任意类型
	 * @param querySql 需要执行的sql_map ID
	 * @return 任意对象 依据sql_map定义类型
	 */
	public Object selectObjectByObject(Object object,String querySql);
	
	/**
	 * 根据对象查找集合
	 * @param object 任意类型
	 * @param querySql 需要执行的sql_map ID
	 * @return 任意对象 依据sql_map定义类型集合
	 */
	public Object selectListByObject(Object object,String querySql);
	
}
