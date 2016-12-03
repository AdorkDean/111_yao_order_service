package com.rc.openapi.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dao.TGoodsCommentDAO;
import com.rc.openapi.dubbo.vo.TGoodsComment;
import com.rc.openapi.dubbo.vo.TGoodsCommentExample;

public class TGoodsCommentDAOImpl implements TGoodsCommentDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TGoodsCommentDAOImpl() {
        super();
    }

    public TGoodsCommentDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TGoodsCommentExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_goods_comment.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TGoodsCommentExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_goods_comment.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TGoodsComment key = new TGoodsComment();
        key.setId(id);
        int rows = sqlMapClient.delete("t_goods_comment.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TGoodsComment record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_goods_comment.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TGoodsComment record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_goods_comment.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TGoodsCommentExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_goods_comment.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TGoodsComment selectByPrimaryKey(Long id) throws SQLException {
        TGoodsComment key = new TGoodsComment();
        key.setId(id);
        TGoodsComment record = (TGoodsComment) sqlMapClient.queryForObject("t_goods_comment.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TGoodsComment record, TGoodsCommentExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_goods_comment.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TGoodsComment record, TGoodsCommentExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_goods_comment.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TGoodsComment record) throws SQLException {
        int rows = sqlMapClient.update("t_goods_comment.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TGoodsComment record) throws SQLException {
        int rows = sqlMapClient.update("t_goods_comment.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TGoodsCommentExample {
        private Object record;

        public UpdateByExampleParms(Object record, TGoodsCommentExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TGoodsCommentExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_goods_comment.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}

	/**
	 * 商品单品页初始化,查询list
	 */
	@Override
	public List<TGoodsComment> selectGoodscommentByAjax(Map<String, Object> map) {
		List<TGoodsComment> list = null;
		try {
			list = sqlMapClient.queryForList("t_goods_comment.ibatorgenerated_selectByAjaxMap", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
