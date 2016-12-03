package com.rc.openapi.service;

import java.sql.SQLException;
import java.util.List;

import com.rc.openapi.dubbo.model.TOrderGoodModel;
import com.rc.openapi.dubbo.vo.TOrderItem;
import com.rc.openapi.dubbo.vo.TOrderItemExample;

public interface TOrderItemManager {
    int countByExample(TOrderItemExample example) throws SQLException;

    int deleteByExample(TOrderItemExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TOrderItem record) throws SQLException;

    Long insertSelective(TOrderItem record) throws SQLException;

    List selectByExample(TOrderItemExample example) throws SQLException;

    TOrderItem selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TOrderItem record, TOrderItemExample example) throws SQLException;

    int updateByExample(TOrderItem record, TOrderItemExample example) throws SQLException;

    int updateByPrimaryKeySelective(TOrderItem record) throws SQLException;

    int updateByPrimaryKey(TOrderItem record) throws SQLException;

    /**
	 * 用户根据订单id 查询订单项商品信息
	 * @param orderId  订单id
	 * @param reviewsFlag 评价状态 0 否 1 是
	 * @return   List<TOrderGoodModel>
	 * @throws Exception
	 */
	public List<TOrderGoodModel>  getTOrderGoodListByOrderId(long orderId,Integer reviewsFlag) throws Exception;
	
	/**
	 * 判断订单状态为已完成是 是否已评论
	 * 方法名：getReviewsByOrderId<BR>
	 * 创建人：Marlon <BR>
	 * @param orderId  订单id
	 * @throws Exception <BR>
	 * @return int <BR>
	 */
	public int getReviewsByOrderId(long orderId) throws Exception;

}
