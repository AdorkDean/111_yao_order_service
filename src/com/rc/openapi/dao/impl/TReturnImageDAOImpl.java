package com.rc.openapi.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dao.TReturnImageDAO;
import com.rc.openapi.dubbo.vo.TReturnImage;
import com.rc.openapi.dubbo.vo.TReturnImageExample;

public class TReturnImageDAOImpl implements TReturnImageDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TReturnImageDAOImpl() {
        super();
    }

    public TReturnImageDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TReturnImageExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_return_image.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TReturnImageExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_return_image.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TReturnImage key = new TReturnImage();
        key.setId(id);
        int rows = sqlMapClient.delete("t_return_image.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TReturnImage record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_return_image.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TReturnImage record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_return_image.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TReturnImageExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_return_image.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TReturnImage selectByPrimaryKey(Long id) throws SQLException {
        TReturnImage key = new TReturnImage();
        key.setId(id);
        TReturnImage record = (TReturnImage) sqlMapClient.queryForObject("t_return_image.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TReturnImage record, TReturnImageExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_return_image.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TReturnImage record, TReturnImageExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_return_image.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TReturnImage record) throws SQLException {
        int rows = sqlMapClient.update("t_return_image.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TReturnImage record) throws SQLException {
        int rows = sqlMapClient.update("t_return_image.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TReturnImageExample {
        private Object record;

        public UpdateByExampleParms(Object record, TReturnImageExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TReturnImageExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_return_image.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
