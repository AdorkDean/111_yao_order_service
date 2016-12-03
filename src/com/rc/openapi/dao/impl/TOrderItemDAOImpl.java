package com.rc.openapi.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dao.TOrderItemDAO;
import com.rc.openapi.dubbo.vo.TOrderItem;
import com.rc.openapi.dubbo.vo.TOrderItemExample;

public class TOrderItemDAOImpl implements TOrderItemDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TOrderItemDAOImpl() {
        super();
    }

    public TOrderItemDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TOrderItemExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_order_item.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TOrderItemExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_order_item.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TOrderItem key = new TOrderItem();
        key.setId(id);
        int rows = sqlMapClient.delete("t_order_item.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TOrderItem record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_order_item.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TOrderItem record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_order_item.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TOrderItemExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_order_item.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TOrderItem selectByPrimaryKey(Long id) throws SQLException {
        TOrderItem key = new TOrderItem();
        key.setId(id);
        TOrderItem record = (TOrderItem) sqlMapClient.queryForObject("t_order_item.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TOrderItem record, TOrderItemExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_order_item.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TOrderItem record, TOrderItemExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_order_item.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TOrderItem record) throws SQLException {
        int rows = sqlMapClient.update("t_order_item.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TOrderItem record) throws SQLException {
        int rows = sqlMapClient.update("t_order_item.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TOrderItemExample {
        private Object record;

        public UpdateByExampleParms(Object record, TOrderItemExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TOrderItemExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_order_item.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
