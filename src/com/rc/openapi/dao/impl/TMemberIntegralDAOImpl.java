package com.rc.openapi.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dao.TMemberIntegralDAO;
import com.rc.openapi.dubbo.vo.TMemberIntegral;
import com.rc.openapi.dubbo.vo.TMemberIntegralExample;

public class TMemberIntegralDAOImpl implements TMemberIntegralDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TMemberIntegralDAOImpl() {
        super();
    }

    public TMemberIntegralDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TMemberIntegralExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_member_integral.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TMemberIntegralExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_member_integral.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TMemberIntegral key = new TMemberIntegral();
        key.setId(id);
        int rows = sqlMapClient.delete("t_member_integral.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TMemberIntegral record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_member_integral.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TMemberIntegral record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_member_integral.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TMemberIntegralExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_member_integral.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TMemberIntegral selectByPrimaryKey(Long id) throws SQLException {
        TMemberIntegral key = new TMemberIntegral();
        key.setId(id);
        TMemberIntegral record = (TMemberIntegral) sqlMapClient.queryForObject("t_member_integral.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TMemberIntegral record, TMemberIntegralExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_member_integral.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TMemberIntegral record, TMemberIntegralExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_member_integral.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TMemberIntegral record) throws SQLException {
        int rows = sqlMapClient.update("t_member_integral.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TMemberIntegral record) throws SQLException {
        int rows = sqlMapClient.update("t_member_integral.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TMemberIntegralExample {
        private Object record;

        public UpdateByExampleParms(Object record, TMemberIntegralExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TMemberIntegralExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_member_integral.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
