package com.rc.openapi.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dao.TSysParameterDAO;
import com.rc.openapi.dubbo.vo.TSysParameter;
import com.rc.openapi.dubbo.vo.TSysParameterExample;

public class TSysParameterDAOImpl implements TSysParameterDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TSysParameterDAOImpl() {
        super();
    }

    public TSysParameterDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TSysParameterExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_sys_parameter.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TSysParameterExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_sys_parameter.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TSysParameter key = new TSysParameter();
        key.setId(id);
        int rows = sqlMapClient.delete("t_sys_parameter.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TSysParameter record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_sys_parameter.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TSysParameter record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_sys_parameter.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TSysParameterExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_sys_parameter.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TSysParameter selectByPrimaryKey(Long id) throws SQLException {
        TSysParameter key = new TSysParameter();
        key.setId(id);
        TSysParameter record = (TSysParameter) sqlMapClient.queryForObject("t_sys_parameter.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TSysParameter record, TSysParameterExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_sys_parameter.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TSysParameter record, TSysParameterExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_sys_parameter.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TSysParameter record) throws SQLException {
        int rows = sqlMapClient.update("t_sys_parameter.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TSysParameter record) throws SQLException {
        int rows = sqlMapClient.update("t_sys_parameter.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TSysParameterExample {
        private Object record;

        public UpdateByExampleParms(Object record, TSysParameterExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    public String getKeys(String sysKey) throws SQLException{
    	TSysParameterExample example = new TSysParameterExample();
    	example.createCriteria().andSysKeyEqualTo(sysKey);
    	List<TSysParameter> list = this.selectByExample(example);
    	String val = "";
    	if(null!=list&&0<list.size()){
    		TSysParameter obj = list.get(0);
    		val = obj.getSysValue();
    	}
    	return val;
    }
	public PageWraper selectByRepositoryByPage(TSysParameterExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_sys_parameter.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
