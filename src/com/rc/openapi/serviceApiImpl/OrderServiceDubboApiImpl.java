package com.rc.openapi.serviceApiImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dubbo.enumc.OrderStatusEnum;
import com.rc.openapi.dubbo.model.TOrderGoodModel;
import com.rc.openapi.dubbo.model.TOrderStatusCountModel;
import com.rc.openapi.dubbo.vo.TOrder;
import com.rc.openapi.dubbo.vo.TOrderFlow;
import com.rc.openapi.dubbo.vo.TOrderShipment;
import com.rc.openapi.service.OpenSqlManage;
import com.rc.openapi.service.TOrderItemManager;
import com.rc.openapi.service.TOrderManager;
import com.rc.openapi.service.TSysParameterManager;
import com.rc.openapi.serviceApi.OrderServiceDubboApi;
/**
 * 订单dubbo服务 对外提供接口  实现
 * @author 刘志强  
 * @version v1.0
 *
 */
public class OrderServiceDubboApiImpl implements OrderServiceDubboApi {

	private OpenSqlManage opensqlmanage;
	private TOrderManager tordermanager;
	private TSysParameterManager tsysparametermanager;
	
	private TOrderItemManager torderitemmanager;
	
	public TOrderItemManager getTorderitemmanager() {
		return torderitemmanager;
	}
	public void setTorderitemmanager(TOrderItemManager torderitemmanager) {
		this.torderitemmanager = torderitemmanager;
	}
	
	public TOrderManager getTordermanager() {
		return tordermanager;
	}
	public void setTordermanager(TOrderManager tordermanager) {
		this.tordermanager = tordermanager;
	}
	public TSysParameterManager getTsysparametermanager() {
		return tsysparametermanager;
	}
	public void setTsysparametermanager(TSysParameterManager tsysparametermanager) {
		this.tsysparametermanager = tsysparametermanager;
	}
	
	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}
	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}
	/**
	 * 根据订单id 查询订单所有信息
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public TOrder getOrderById(long orderId) throws Exception {
		return tordermanager.selectByPrimaryKey(orderId);
	}
	/**
	 * 查询订单商品集合   按照创建时间倒叙
	 * @param memberId 用户id
	 * @param orderSn  订单编号
	 * @param orderStatus 订单类型枚举
	 * @param fromDate 订单下单时间开始 YYYY-MM-dd 
	 * @param toDate   订单下单时间结束 YYYY-MM-dd
	 * @return
	 * @throws SQLException
	 */
	public PageWraper getOrderGoodsList(Long memberId, String orderSn,
			OrderStatusEnum orderStatus, Date fromDate, Date toDate, int page,
			int pagesize) throws Exception {
		return tordermanager.getOrderGoodsList(memberId, orderSn, orderStatus, fromDate, toDate, page, pagesize);
	}
	/**
	 * 取消订单 
	 * @param orderid    订单id
	 * @param cancleType 取消类型
	 * @param cancleReason  取消原因
	 * @param memberId   用户id
	 * @return boolean true 成功 false 失败
	 * @throws Exception
	 */
	public boolean cancelOrder(long orderid,Integer cancleType,String cancleReason,long memberId) throws Exception{
		return tordermanager.cancelOrder(orderid, cancleType, cancleReason, memberId);
	}
	/**
	 * 删除订单
	 * @param orderid 订单id
	 * @return boolean true 成功 false 失败
	 * @throws Exception
	 */
	public boolean deleteOrder(long orderid) throws Exception {
		return tordermanager.deleteOrder(orderid);
	}
	/**
	 * 订单确认收货 
	 * @param orderid 订单id
	 * @return  json (flag 成功标识 1 成功 0 失败  message 失败原因)
	 * @throws Exception
	 */
	public String orderConfirmReceipt(long orderid) throws Exception {
		String publicServiceUrl = tsysparametermanager.getKeys("public_service_url");
		return tordermanager.orderConfirmReceipt(orderid, publicServiceUrl);
	}
	
	/**
	 * 根据用户id 统计该用户各个状态订单总数
	 * @param startDate 统计开始日期,endDate 统计结束日期,memberid 用户id
	 * @throws Exception
	 */
	public TOrderStatusCountModel orderStatusCountByMemberid(String startDate,String endDate,long memberid) throws Exception{
		return tordermanager.orderStatusCountByMemberid(startDate,endDate,memberid);
	}
	/**
	 * 根据用户获取待评价订单个数
	 * @param memberid
	 * @return
	 * @throws Exception
	 */
	public int orderNoEvaluateCount(long memberid) throws Exception {
		return tordermanager.orderNoEvaluateCount(memberid);
	}
	/**
	 * 根据用户id和订单状态，查询订单列表
	 * @param memberId 用户id,startDate 开始日期YYYY-MM-dd,endDate 结束日期YYYY-MM-dd,orderType 订单状态,orderNO 订单编号,pageSize 每页显示条数，page 页码
	 * @throws Exception
	 */
	public PageWraper getOrderListByPage(Long memberId,String startDate, String endDate, Integer orderType,String orderNO, Integer pageSize,Integer page) throws Exception {
		return tordermanager.getOrderListByPage(memberId,startDate,endDate,orderType,orderNO,pageSize,page);
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
		return torderitemmanager.getTOrderGoodListByOrderId(orderId,reviewsFlag);
	}
	
	/**
	 * 根据订单id查询物流信息
	 * 方法名：getOrderShipByOrderId<BR>
	 * 创建人：Marlon <BR>
	 * @param orderId 订单id
	 * @throws Exception <BR>
	 * @return TOrderShipment <BR>
	 */
	public TOrderShipment getOrderShipByOrderId(long orderId){
		return (TOrderShipment) opensqlmanage.selectObjectByObject(orderId, "order_custom_sql.selectShipmentByOrderId");
	}

	/**
	 * 根据订单id查询应付金额
	 * 方法名：getTOrderShipByOrderId<BR>
	 * 创建人：Marlon <BR>
	 * @param orderId 订单id
	 * @return Map<String,Object>
	 * @throws Exception <BR>
	 * @return Map<String,Object> <BR>
	 */
	public TOrderFlow getOrderFlowOrderId(long orderId){
		return (TOrderFlow) opensqlmanage.selectObjectByObject(orderId, "order_custom_sql.selectFlowByOrderId");
	}
	
	/**
	 * 判断订单状态为已完成是 是否已评论
	 * 方法名：getReviewsByOrderId<BR>
	 * 创建人：Marlon <BR>
	 * @param orderId  订单id
	 * @throws Exception <BR>
	 * @return int <BR>
	 */
	public int getReviewsByOrderId(long orderId) throws Exception{
		return torderitemmanager.getReviewsByOrderId(orderId);
	}
	
}
