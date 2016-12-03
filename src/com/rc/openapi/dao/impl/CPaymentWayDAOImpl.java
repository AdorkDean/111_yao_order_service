package com.rc.openapi.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dao.CPaymentWayDAO;
import com.rc.openapi.dubbo.vo.CPaymentWay;
import com.rc.openapi.dubbo.vo.CPaymentWayExample;

public class CPaymentWayDAOImpl implements CPaymentWayDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public CPaymentWayDAOImpl() {
        super();
    }

    public CPaymentWayDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(CPaymentWayExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("c_payment_way.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(CPaymentWayExample example) throws SQLException {
        int rows = sqlMapClient.delete("c_payment_way.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        CPaymentWay key = new CPaymentWay();
        key.setId(id);
        int rows = sqlMapClient.delete("c_payment_way.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(CPaymentWay record) throws SQLException {
		return (Long)        sqlMapClient.insert("c_payment_way.ibatorgenerated_insert", record);
    }

    public Long insertSelective(CPaymentWay record) throws SQLException {
		return (Long)        sqlMapClient.insert("c_payment_way.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(CPaymentWayExample example) throws SQLException {
        List list = sqlMapClient.queryForList("c_payment_way.ibatorgenerated_selectByExample", example);
        return list;
    }

    public CPaymentWay selectByPrimaryKey(Long id) throws SQLException {
        CPaymentWay key = new CPaymentWay();
        key.setId(id);
        CPaymentWay record = (CPaymentWay) sqlMapClient.queryForObject("c_payment_way.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(CPaymentWay record, CPaymentWayExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("c_payment_way.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(CPaymentWay record, CPaymentWayExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("c_payment_way.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(CPaymentWay record) throws SQLException {
        int rows = sqlMapClient.update("c_payment_way.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(CPaymentWay record) throws SQLException {
        int rows = sqlMapClient.update("c_payment_way.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends CPaymentWayExample {
        private Object record;

        public UpdateByExampleParms(Object record, CPaymentWayExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(CPaymentWayExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("c_payment_way.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
