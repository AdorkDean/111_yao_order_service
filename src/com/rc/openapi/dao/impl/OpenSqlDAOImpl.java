package com.rc.openapi.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageInfo;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dao.OpenSqlDAO;

/**
 * @Title: OpenSqlDAO.java
 * @Description: 针对底层数据库的公共dao接口，主要是通过Map携带参数，返回包含Map的list或者一个Object，
 *               省去每次新建实体对象的过程，在分页查询时开发人员只需写好sql语句，在前台显示即可。
 * @author yinbinhome@163.com
 * @date 2012-6-27 下午10:01:41
 * @version V1.0
 */

public class OpenSqlDAOImpl implements OpenSqlDAO {
	private SqlMapClient sqlMapClient;
	private Logger log=Logger.getLogger(getClass());
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public OpenSqlDAOImpl() {
		super();
	}

	public OpenSqlDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * 传入Map 返回包含Map的List
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public List selectForListByMap(Map map, String querySql) {
//		log.info("[OpenSqlDAOImpl] 查找返回List :"+querySql);
		List list = new ArrayList();
		try {
			list = (List) sqlMapClient.queryForList(querySql, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 查找返回List异常");
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 传入Map 返回一个对象，可以使一个Bean也可以是一个Integer，Long
	 * @param map 包含参数的Map
	 * @param querySql sqlmap的id
	 * @return
	 */
	public Object selectForObjectByMap(Map map, String querySql) {
//		log.info("[OpenSqlDAOImpl] 查找返回Object :"+querySql);
		Object object = null;
		try {
			object = sqlMapClient.queryForObject(querySql, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 查找返回Object异常");
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * 公共分页工具方法，传入条件Map，计算总记录数sqlmap的id，查询结果集sqlmap的id
	 * @param map
	 * @param sql_count 计算总记录数sqlmap的id
	 * @param sql_record 查询结果集sqlmap的id
	 * @param page 页码
	 * @param pagesize 每页显示个数
	 * @return
	 */
	public PageWraper selectForPageByMap(Map map, String sql_count, String sql_record,Integer page,Integer pagesize) {
		log.info("[OpenSqlDAOImpl] 查找返回List :"+sql_count+" | "+sql_record+" | "+page +" | "+pagesize);
    	PageWraper pw=null;
    	Integer count=0;
        PageInfo pageInfo=new PageInfo();
        pageInfo.setPage(page);
        pageInfo.setPageSize(pagesize);
        pageInfo.setStart(page,pagesize);
        pageInfo.setEnd(page,pagesize);
        map.put("pageInfo", pageInfo);
		try {
			count = (Integer)sqlMapClient.queryForObject(sql_count, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 分页查找返回总记录数异常");
			e.printStackTrace();
		}
        List list=new ArrayList();;
		try {
			list = (List)sqlMapClient.queryForList(sql_record, map);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 分页查找返回List异常");
			e.printStackTrace();
		}

		pw=PageManager.getPageWraper(pageInfo, list, count);
        return pw;
	}
	
	
	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * 
	 * @param map
	 * @param updatesql
	 *            sqlmap的id
	 * @return
	 * @throws SQLException
	 */
	public int updateByMap_drug(Map map, String updatesql) throws SQLException {

		int rows = sqlMapClient.update(updatesql, map);
		return rows;
	}

	/**
	 * 说明:公共插入方法，传入插入记录的sqlmap的id，条件Map，
	 * @param map
	 * @param insertsql
	 * @throws SQLException
	 */
	public void insertByMap_drug(Map map, String insertsql) throws SQLException {
		sqlMapClient.insert(insertsql, map);
	}
	
	/**
	 * 公共更新方法，传入更新记录的sqlmap的id，条件Map，
	 * 
	 * @param List含有Map的list 
	 * @param insertsql 的id
	 * @return
	 * @throws SQLException
	 */
	public String batchInsertByList_drug(List<Object> list, String insertsql) throws SQLException {
		String reStr="";
		if (list == null || list.size() == 0 || list.size() > 500) {
			log.info("[OpenSql]批量提交数据错误");
			reStr="检查List，是否为空或者大小超过500";
			return reStr;
		}
		log.info("[OpenSql]此次批量提交数据 " + list.size() + " 条");
		try {
			sqlMapClient.startTransaction();
			sqlMapClient.startBatch();

			for (Object obj : list) {
				sqlMapClient.insert(insertsql, obj);
			}
			sqlMapClient.executeBatch();
			sqlMapClient.commitTransaction();

		} catch (Exception e) {
			sqlMapClient.getCurrentConnection().rollback();
			log.info("[OpenSql]批量提交发生异常回滚");
			reStr="[OpenSql]批量提交发生异常回滚";
		} finally {
			sqlMapClient.endTransaction();
		}
		reStr="成功提交 "+list.size()+" 条记录";
		return reStr;
	}
	
	/**
	 * 根据对象查找对象
	 * @param object 任意类型
	 * @param querySql 需要执行的sql_map ID
	 * @return 任意对象 依据sql_map定义类型
	 */
	public Object selectObjectByObject(Object object,String querySql){
//		log.info("[selectObjectByObject] 查找返回Object :"+querySql);
		Object result = null;
		try {
			result = sqlMapClient.queryForObject(querySql, object);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 查找返回Object异常");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据对象查找集合
	 * @param object 任意类型
	 * @param querySql 需要执行的sql_map ID
	 * @return 任意对象 依据sql_map定义类型集合
	 */
	public Object selectListByObject(Object object,String querySql){
//		log.info("[selectListByObject] 查找返回list :"+querySql);
		Object result = null;
		try {
			result = sqlMapClient.queryForList(querySql, object);
		} catch (SQLException e) {
			log.info("[OpenSqlDAOImpl] 查找返回Object异常");
			e.printStackTrace();
		}
		return result;
	}
}
