package com.rc.openapi.serviceApiImpl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dubbo.vo.TReturn;
import com.rc.openapi.service.OpenSqlManage;
import com.rc.openapi.service.TOrderItemManager;
import com.rc.openapi.service.TOrderManager;
import com.rc.openapi.service.TReturnImageManager;
import com.rc.openapi.service.TReturnItemManager;
import com.rc.openapi.service.TReturnManager;
import com.rc.openapi.serviceApi.OrderReturnServiceDubboApi;

/**
 * 订单退款退货 dubbo服务 对外提供接口
 * 
 * @author 刘志强
 * @version v1.0
 */
public class OrderReturnServiceDubboApiImpl implements OrderReturnServiceDubboApi {
	
	private TOrderManager tordermanager;
	
	private TOrderItemManager torderitemmanager;
	
	private TReturnManager treturnmanager;
	
	private TReturnItemManager treturnitemmanager;
	
	private TReturnImageManager treturnimagemanager;
	
	private OpenSqlManage opensqlmanage;

	
	
	
	/**
	 * @param price_type平台字符串(pc,wap,app)
	 * @param orderId
	 *            :订单id;
	 * @param returnType
	 *            :退换货类型(0:退货,2:退款);
	 * @param returnRemark
	 *            :退款说明;
	 * @param imgs
	 *            :凭证图片;(base64图片字符串,不带前缀<data:image/JPG;base64,>,此处用在app客户端)
	 * @param returnParam<商品ID:数量>
	 *            :商品id及数量;
	 *            一个商品只能退一次,
	 * @throws Exception 
	 */
	public boolean submitReturnInfo(String price_type, long orderId, String returnType, String returnRemark, List<String> imgs,List<File> fileList,
			Map<Long, Integer> returnParam) throws Exception {
		
		return treturnmanager.submitReturnInfo(price_type, orderId, returnType,  returnRemark, imgs, fileList,
				 returnParam);
	}
	
	/**
	 * 查询退款退货订单  分页查询
	 * @param memberid 用户id
	 * @param returnOrderNo 退款退货订单编号
	 * @param orderStatus   退款订单状态集合
	 * @param fromDate   退款订单创建开始时间 yyyy-MM-dd HH:mm:ss 
	 * @param toDate 退款订单创建结束时间 yyyy-MM-dd HH:mm:ss
	 * @param page   当前页
	 * @param pagesize  每页显示多少条
	 * @return  PageWraper
	 * @throws Exception
	 */
	public PageWraper getOrderReturn (long memberid,String returnOrderNo,List<Integer> orderStatus,Date fromDate,Date toDate,int page,
			int pagesize,List<Integer> serviceTypeList) throws Exception{
		return treturnmanager.getOrderReturn(memberid, returnOrderNo, orderStatus, fromDate, toDate, page, pagesize,serviceTypeList);
	}
	
	
	public PageWraper getAllOrderReturn (String userName,String returnOrderNo,String refundDescribe,List<Integer> orderStatus,Date fromDate,Date toDate,int page,
			int pagesize,List<Integer> serviceTypeList) throws Exception{
		
		return treturnmanager.getAllOrderReturn(userName, returnOrderNo, refundDescribe, orderStatus, fromDate, toDate, page, pagesize, serviceTypeList);
	}
	
	
	public TReturn getOrderReturnById (long id) throws Exception{
		return treturnmanager.selectByPrimaryKey(id);
	}
	
	public int updateReturn (TReturn tReturn) throws Exception{
		return treturnmanager.updateByPrimaryKeySelective(tReturn);
	}

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
	}
	

	public TOrderManager getTordermanager() {
		return tordermanager;
	}

	public void setTordermanager(TOrderManager tordermanager) {
		this.tordermanager = tordermanager;
	}

	public TReturnManager getTreturnmanager() {
		return treturnmanager;
	}

	public void setTreturnmanager(TReturnManager treturnmanager) {
		this.treturnmanager = treturnmanager;
	}

	public TReturnItemManager getTreturnitemmanager() {
		return treturnitemmanager;
	}

	public void setTreturnitemmanager(TReturnItemManager treturnitemmanager) {
		this.treturnitemmanager = treturnitemmanager;
	}

	public TOrderItemManager getTorderitemmanager() {
		return torderitemmanager;
	}

	public void setTorderitemmanager(TOrderItemManager torderitemmanager) {
		this.torderitemmanager = torderitemmanager;
	}

	public OpenSqlManage getOpensqlmanage() {
		return opensqlmanage;
	}

	public void setOpensqlmanage(OpenSqlManage opensqlmanage) {
		this.opensqlmanage = opensqlmanage;
	}


	public TReturnImageManager getTreturnimagemanager() {
		return treturnimagemanager;
	}


	public void setTreturnimagemanager(TReturnImageManager treturnimagemanager) {
		this.treturnimagemanager = treturnimagemanager;
	}
	
}
