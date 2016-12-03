package com.rc.openapi.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dubbo.enumc.OrderStatusEnum;
import com.rc.openapi.dubbo.model.TOrderStatusCountModel;
import com.rc.openapi.dubbo.vo.TOrder;
import com.rc.openapi.dubbo.vo.TOrderExample;

public interface TOrderManager {
	int countByExample(TOrderExample example) throws SQLException;

	int deleteByExample(TOrderExample example) throws SQLException;

	int deleteByPrimaryKey(Long id) throws SQLException;

	Long insert(TOrder record) throws SQLException;

	Long insertSelective(TOrder record) throws SQLException;

	List selectByExample(TOrderExample example) throws SQLException;

	TOrder selectByPrimaryKey(Long id) throws SQLException;

	int updateByExampleSelective(TOrder record, TOrderExample example)
			throws SQLException;

	int updateByExample(TOrder record, TOrderExample example)
			throws SQLException;

	int updateByPrimaryKeySelective(TOrder record) throws SQLException;

	int updateByPrimaryKey(TOrder record) throws SQLException;

	/**
	 * 查询订单商品集合   
	 * @param memberId 用户id
	 * @param orderSn  订单编号
	 * @param orderStatus 订单类型
	 * @param fromDate 订单下单时间开始 YYYY-MM-dd
	 * @param toDate   订单下单时间结束 YYYY-MM-dd
	 * @return
	 * @throws SQLException
	 */
	public PageWraper getOrderGoodsList(Long memberId, String orderSn,
			OrderStatusEnum orderStatus, Date fromDate, Date toDate,int page,int pagesize)
			throws SQLException;
   
	/**
	 * 取消订单 
	 * @param orderid    订单id
	 * @param cancleType 取消类型
	 * @param cancleReason  取消原因
	 * @param memberId   用户id
	 * @return boolean true 成功 false 失败
	 * @throws Exception
	 */
	public boolean cancelOrder(long orderid,Integer cancleType,String cancleReason,long memberId) throws Exception;
	
	/**
	 * 删除订单
	 * @param orderid 订单id
	 * @return boolean true 成功 false 失败
	 * @throws Exception
	 */
	public boolean deleteOrder(long orderid) throws Exception;
	
	
	/**
	 * 订单确认收货 
	 * @param orderid 订单id
	 * @param publicurl public项目访问地址
	 * @return  json (flag 成功标识 1 成功 0 失败  message 失败原因)
	 * @throws Exception
	 */
	public String orderConfirmReceipt(long orderid,String publicurl) throws Exception;
	
	/**
	 * 根据用户id 统计该用户各个状态订单总数
	 * @param endDate 统计结束时间
	 * @param startDate 统计开始时间
	 * @param memberid 用户id
	 * @throws Exception
	 */
	public TOrderStatusCountModel orderStatusCountByMemberid(String startDate, String endDate, long memberid) throws Exception;
	
	/**
	 * 根据用户获取待评价订单个数
	 * @param memberid
	 * @return
	 * @throws Exception
	 */
	public int orderNoEvaluateCount(long memberid) throws Exception;
	
	/**
	 * 根据用户id和订单状态，查询订单列表
	 * @param memberId 用户id,startDate 开始日期YYYY-MM-dd,endDate 结束日期YYYY-MM-dd,orderType 订单状态,orderNO 订单编号,pageSize 每页显示条数，page 页码
	 * @throws Exception
	 */
	public PageWraper getOrderListByPage(Long memberId, String startDate, String endDate, Integer orderType, String orderNO, Integer pageSize,Integer page);
}
