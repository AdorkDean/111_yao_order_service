package com.rc.openapi.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dao.TOrderDAO;
import com.rc.openapi.dubbo.vo.TOrder;
import com.rc.openapi.dubbo.vo.TOrderExample;

public class TOrderDAOImpl implements TOrderDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    

	public TOrderDAOImpl() {
        super();
    }

    public TOrderDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TOrderExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_order.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TOrderExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_order.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TOrder key = new TOrder();
        key.setId(id);
        int rows = sqlMapClient.delete("t_order.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TOrder record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_order.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TOrder record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_order.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TOrderExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_order.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TOrder selectByPrimaryKey(Long id) throws SQLException {
        TOrder key = new TOrder();
        key.setId(id);
        TOrder record = (TOrder) sqlMapClient.queryForObject("t_order.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TOrder record, TOrderExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_order.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TOrder record, TOrderExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_order.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TOrder record) throws SQLException {
        int rows = sqlMapClient.update("t_order.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TOrder record) throws SQLException {
        int rows = sqlMapClient.update("t_order.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TOrderExample {
        private Object record;

        public UpdateByExampleParms(Object record, TOrderExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TOrderExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_order.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}

}
