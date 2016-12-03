package com.rc.openapi.service;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dubbo.vo.TReturn;
import com.rc.openapi.dubbo.vo.TReturnExample;

public interface TReturnManager {
	int countByExample(TReturnExample example) throws SQLException;

	int deleteByExample(TReturnExample example) throws SQLException;

	int deleteByPrimaryKey(Long id) throws SQLException;

	Long insert(TReturn record) throws SQLException;

	Long insertSelective(TReturn record) throws SQLException;

	List selectByExample(TReturnExample example) throws SQLException;

	TReturn selectByPrimaryKey(Long id) throws SQLException;

	int updateByExampleSelective(TReturn record, TReturnExample example) throws SQLException;

	int updateByExample(TReturn record, TReturnExample example) throws SQLException;

	int updateByPrimaryKeySelective(TReturn record) throws SQLException;

	int updateByPrimaryKey(TReturn record) throws SQLException;

	/**
	 * 存储过程取退换货订单编号
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException 
	 */
	String getTurnOrderSn(Map<String, Object> paramMap) throws SQLException;

	/**
	 * 
	 * @param price_type平台(pc,wap,app)
	 * @param orderId订单ID
	 * @param returnType:退换货类型(0:退货退款,2:仅退款);
	 * @param returnRemark:退换货原因
	 * @param imgs图片;
	 * @param fileList图片集合;
	 * @param returnParam(商品id,商品个数:商品id,商品数量:...)
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	boolean submitReturnInfo(String price_type,long orderId, String returnType, String returnRemark, List<String> imgs, List<File> fileList,
			Map<Long, Integer> returnParam) throws SQLException, Exception ;
	
	
	/**
	 * 查询退款退货订单  分页查询
	 * @param memberid 用户id
	 * @param returnOrderNo 退款退货订单编号
	 * @param orderStatus   退款订单状态集合
	 * @param fromDate   退款订单创建开始时间 YYYY-MM-dd 
	 * @param toDate 退款订单创建结束时间 YYYY-MM-dd
	 * @param page   当前页
	 * @param pagesize  每页显示多少条
	 * @param serviceTypeList  退换货类型(0:退货;2:退款)
	 * @return  PageWraper
	 * @throws Exception
	 */
	public PageWraper getOrderReturn (long memberid,String returnOrderNo,List<Integer> orderStatus,Date fromDate,Date toDate,int page,
			int pagesize,List<Integer> serviceTypeList) throws Exception;
	
	public PageWraper getAllOrderReturn (String userName,String returnOrderNo,String refundDescribe,List<Integer> orderStatus,Date fromDate,Date toDate,int page,
			int pagesize,List<Integer> serviceTypeList) throws Exception;

}
