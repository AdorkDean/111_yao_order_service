package com.rc.openapi.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.openapi.dao.OpenSqlDAO;
import com.rc.openapi.dao.TOrderItemDAO;
import com.rc.openapi.dubbo.model.TOrderGoodModel;
import com.rc.openapi.dubbo.vo.TOrderItem;
import com.rc.openapi.dubbo.vo.TOrderItemExample;
import com.rc.openapi.service.TOrderItemManager;

public class TOrderItemManagerImpl  implements TOrderItemManager {

    private TOrderItemDAO torderitemdao;
    
    private OpenSqlDAO opensqldao;

    public TOrderItemManagerImpl() {
        super();
    }
    public void  setTorderitemdao(TOrderItemDAO torderitemdao){
        this.torderitemdao=torderitemdao;
    }
    public TOrderItemDAO getTorderitemdao(){
        return this.torderitemdao;
    }
    public     int countByExample(TOrderItemExample example) throws SQLException{
        return torderitemdao. countByExample( example);
    }

    public     int deleteByExample(TOrderItemExample example) throws SQLException{
        return torderitemdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return torderitemdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TOrderItem record) throws SQLException{
        return torderitemdao. insert( record);
    }

    public     Long insertSelective(TOrderItem record) throws SQLException{
        return torderitemdao. insertSelective( record);
    }

    public     List selectByExample(TOrderItemExample example) throws SQLException{
        return torderitemdao. selectByExample( example);
    }

    public     TOrderItem selectByPrimaryKey(Long id) throws SQLException{
        return torderitemdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TOrderItem record, TOrderItemExample example) throws SQLException{
        return torderitemdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TOrderItem record, TOrderItemExample example) throws SQLException{
        return torderitemdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TOrderItem record) throws SQLException{
        return torderitemdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TOrderItem record) throws SQLException{
        return torderitemdao. updateByPrimaryKey( record);
    }
    /**
   	 * 用户根据订单id 查询订单项商品信息
   	 * @param orderId  订单id
   	 * @param reviewsFlag 评价状态 0 否 1 是
   	 * @return   List<TOrderGoodModel>
   	 * @throws Exception
   	 */
	public List<TOrderGoodModel> getTOrderGoodListByOrderId(long orderId,Integer reviewsFlag)
			throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", orderId);
		if(reviewsFlag!=null){
			paramMap.put("reviews", reviewsFlag);
		}
		List<TOrderGoodModel> tOrderGoodModelList =opensqldao.selectForListByMap(paramMap, "order_custom_sql.selectOrderItemGoodsNoReturnByOrderId");
		return tOrderGoodModelList;
	}
	public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}
	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}

	/**
	 * 判断订单状态为已完成是 是否已评论
	 * 方法名：getReviewsByOrderId<BR>
	 * 创建人：Marlon <BR>
	 * @param orderId  订单id
	 * @throws Exception <BR>
	 * @return int <BR>
	 */
	public int getReviewsByOrderId(long orderId){
		return (int) opensqldao.selectObjectByObject(orderId, "order_custom_sql.selectReviewsByOrderId");
	}

}
