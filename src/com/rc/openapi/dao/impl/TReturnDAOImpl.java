package com.rc.openapi.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dao.TReturnDAO;
import com.rc.openapi.dubbo.vo.TReturn;
import com.rc.openapi.dubbo.vo.TReturnExample;

public class TReturnDAOImpl implements TReturnDAO {
	private SqlMapClient sqlMapClient;

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public TReturnDAOImpl() {
		super();
	}

	public TReturnDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	public int countByExample(TReturnExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("t_return.ibatorgenerated_countByExample", example);
		return count.intValue();
	}

	public int deleteByExample(TReturnExample example) throws SQLException {
		int rows = sqlMapClient.delete("t_return.ibatorgenerated_deleteByExample", example);
		return rows;
	}

	public int deleteByPrimaryKey(Long id) throws SQLException {
		TReturn key = new TReturn();
		key.setId(id);
		int rows = sqlMapClient.delete("t_return.ibatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	public Long insert(TReturn record) throws SQLException {
		return (Long) sqlMapClient.insert("t_return.ibatorgenerated_insert", record);
	}

	public Long insertSelective(TReturn record) throws SQLException {
		return (Long) sqlMapClient.insert("t_return.ibatorgenerated_insertSelective", record);
	}

	public List selectByExample(TReturnExample example) throws SQLException {
		List list = sqlMapClient.queryForList("t_return.ibatorgenerated_selectByExample", example);
		return list;
	}

	public TReturn selectByPrimaryKey(Long id) throws SQLException {
		TReturn key = new TReturn();
		key.setId(id);
		TReturn record = (TReturn) sqlMapClient.queryForObject("t_return.ibatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	public int updateByExampleSelective(TReturn record, TReturnExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("t_return.ibatorgenerated_updateByExampleSelective", parms);
		return rows;
	}

	public int updateByExample(TReturn record, TReturnExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("t_return.ibatorgenerated_updateByExample", parms);
		return rows;
	}

	public int updateByPrimaryKeySelective(TReturn record) throws SQLException {
		int rows = sqlMapClient.update("t_return.ibatorgenerated_updateByPrimaryKeySelective", record);
		return rows;
	}

	public int updateByPrimaryKey(TReturn record) throws SQLException {
		int rows = sqlMapClient.update("t_return.ibatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	private static class UpdateByExampleParms extends TReturnExample {
		private Object record;

		public UpdateByExampleParms(Object record, TReturnExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	public PageWraper selectByRepositoryByPage(TReturnExample example) throws SQLException {
		PageWraper pw = null;
		int count = this.countByExample(example);
		List list = sqlMapClient.queryForList("t_return.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size=" + list.size());
		pw = PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}

	@Override
	public String getTurnOrderSn(Map<String, Object> paramMap) throws SQLException {
		String turnOrderSn = (String) sqlMapClient.queryForObject("t_return.select_new_return_ordersn", paramMap);
		return turnOrderSn;
	}
}
