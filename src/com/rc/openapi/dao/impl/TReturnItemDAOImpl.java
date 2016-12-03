package com.rc.openapi.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dao.TReturnItemDAO;
import com.rc.openapi.dubbo.vo.TReturnItem;
import com.rc.openapi.dubbo.vo.TReturnItemExample;

public class TReturnItemDAOImpl implements TReturnItemDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TReturnItemDAOImpl() {
        super();
    }

    public TReturnItemDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TReturnItemExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_return_item.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TReturnItemExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_return_item.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TReturnItem key = new TReturnItem();
        key.setId(id);
        int rows = sqlMapClient.delete("t_return_item.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TReturnItem record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_return_item.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TReturnItem record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_return_item.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TReturnItemExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_return_item.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TReturnItem selectByPrimaryKey(Long id) throws SQLException {
        TReturnItem key = new TReturnItem();
        key.setId(id);
        TReturnItem record = (TReturnItem) sqlMapClient.queryForObject("t_return_item.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TReturnItem record, TReturnItemExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_return_item.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TReturnItem record, TReturnItemExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_return_item.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TReturnItem record) throws SQLException {
        int rows = sqlMapClient.update("t_return_item.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TReturnItem record) throws SQLException {
        int rows = sqlMapClient.update("t_return_item.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TReturnItemExample {
        private Object record;

        public UpdateByExampleParms(Object record, TReturnItemExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TReturnItemExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_return_item.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
