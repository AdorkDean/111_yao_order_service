package com.rc.openapi.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.util.StringUtils;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.dst.client.util.ClientSubmitPublic;
import com.rc.openapi.dao.CPaymentWayDAO;
import com.rc.openapi.dao.OpenSqlDAO;
import com.rc.openapi.dao.TMemberIntegralDAO;
import com.rc.openapi.dao.TOrderDAO;
import com.rc.openapi.dao.TOrderItemDAO;
import com.rc.openapi.dao.TReturnDAO;
import com.rc.openapi.dao.TReturnItemDAO;
import com.rc.openapi.dubbo.enumc.OrderStatusEnum;
import com.rc.openapi.dubbo.model.TGoodModel;
import com.rc.openapi.dubbo.model.TOrderModel;
import com.rc.openapi.dubbo.model.TOrderStatusCountModel;
import com.rc.openapi.dubbo.vo.CPaymentWay;
import com.rc.openapi.dubbo.vo.TMemberIntegral;
import com.rc.openapi.dubbo.vo.TOrder;
import com.rc.openapi.dubbo.vo.TOrderExample;
import com.rc.openapi.dubbo.vo.TOrderItem;
import com.rc.openapi.dubbo.vo.TOrderItemExample;
import com.rc.openapi.dubbo.vo.TReturn;
import com.rc.openapi.dubbo.vo.TReturnItem;
import com.rc.openapi.hd.model.HdReturn;
import com.rc.openapi.service.TOrderManager;
import com.rc.openapi.util.InfoUtil;
import com.rc.portal.jms.MessageSender;

public class TOrderManagerImpl  implements TOrderManager {

    private TOrderDAO torderdao;
    private OpenSqlDAO opensqldao;
    private TOrderItemDAO torderitemdao;
    private TReturnDAO treturndao;
    private TReturnItemDAO treturnitemdao;
    private CPaymentWayDAO cpaymentwaydao;
    private TMemberIntegralDAO tmemberintegraldao;
    private MessageSender topicMessageSender;
    
    private String SYNHTTPURL = InfoUtil.getInstance().getInfo("config", "hd.order.return.httpurl");
	private String SYNHTTPURLLZ = InfoUtil.getInstance().getInfo("config", "hd.order.return.httpurl.lz");
	
	private String groupid = InfoUtil.getInstance().getInfo("config", "hd.order.return.groupid");//"1001";//企业编码
	private String olshopid = InfoUtil.getInstance().getInfo("config", "hd.order.return.olshopid");//"100138";
	private String eccode = InfoUtil.getInstance().getInfo("config", "hd.order.return.eccode");//"309
	
	
    
    public TOrderManagerImpl() {
        super();
    }
    public void  setTorderdao(TOrderDAO torderdao){
        this.torderdao=torderdao;
    }
    public TOrderDAO getTorderdao(){
        return this.torderdao;
    }
    public     int countByExample(TOrderExample example) throws SQLException{
        return torderdao. countByExample( example);
    }

    public     int deleteByExample(TOrderExample example) throws SQLException{
        return torderdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return torderdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TOrder record) throws SQLException{
        return torderdao. insert( record);
    }

    public     Long insertSelective(TOrder record) throws SQLException{
        return torderdao. insertSelective( record);
    }

    public     List selectByExample(TOrderExample example) throws SQLException{
        return torderdao. selectByExample( example);
    }

    public     TOrder selectByPrimaryKey(Long id) throws SQLException{
        return torderdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TOrder record, TOrderExample example) throws SQLException{
        return torderdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TOrder record, TOrderExample example) throws SQLException{
        return torderdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TOrder record) throws SQLException{
        return torderdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TOrder record) throws SQLException{
        return torderdao. updateByPrimaryKey( record);
    }
	public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}
	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}
	public PageWraper getOrderGoodsList(Long memberId, String orderSn,
			OrderStatusEnum orderStatus, Date fromDate, Date toDate,int page,int pagesize)
			throws SQLException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
		if(memberId!=null){
			paramMap.put("memberId", memberId.longValue());
		}
		if(orderStatus!=null){
			paramMap.put("orderStatus", orderStatus.getIndex());
		}
		if(fromDate!=null){
			paramMap.put("fromDate", sdf.format(fromDate));
		}
		if(toDate!=null){
			paramMap.put("toDate", sdf.format(toDate));
		}
		if(StringUtils.hasText(orderSn)){
			paramMap.put("orderSn", orderSn.trim());
		}
		PageWraper pw = opensqldao.selectForPageByMap(paramMap, "order_custom_sql.selectOrderListCenterCount", "order_custom_sql.selectOrderListCenterByPage", page, pagesize);
		if (pw!=null&&pw.getResult()!=null&&pw.getResult().size()>0) {
			List<TOrderModel> orderlist = pw.getResult();
			for (TOrderModel order :orderlist) {
				paramMap.clear();
				paramMap.put("orderId", order.getId());
				List<TGoodModel> goodsList = (List<TGoodModel>) opensqldao.selectForObjectByMap(paramMap, "order_custom_sql.selectOrderItemGoodsByOrderId");
				order.setGoodsList(goodsList);
			}
			pw.setResult(orderlist);
		}
		return pw;
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
	public boolean cancelOrder(long orderid, Integer cancleType,String cancleReason, long memberId)
			throws Exception {
		boolean flag = false;
		try{
        TOrder orderold = this.torderdao.selectByPrimaryKey(orderid);
		TOrder order = new TOrder();
		order.setId(orderid);
		order.setOrderStatus(OrderStatusEnum.ORDER_CANCEL.getIndex());
		order.setCancelType(cancleType);
		order.setCancelReason(cancleReason);
		this.torderdao.updateByPrimaryKeySelective(order);
		TOrderItemExample orderItemExample = new TOrderItemExample();
		orderItemExample.createCriteria().andOrderIdEqualTo(orderid);
		List<TOrderItem> orderItemList =  this.torderitemdao.selectByExample(orderItemExample);
		BigDecimal totalFee = new BigDecimal(0);
		
		if(orderItemList!=null&&orderItemList.size()>0){
			 Map<String, Object> paramMap = new HashMap<String, Object>();
			for(TOrderItem orderItem:orderItemList){
				paramMap.clear();
				paramMap.put("id", orderItem.getGoodsId());
				this.opensqldao.selectForObjectByMap(paramMap, "order_custom_sql.selectByPrimaryKeyForUpdate");
				paramMap.clear();
				paramMap.put("id", orderItem.getGoodsId());
				paramMap.put("quantity", orderItem.getQuantity());
				this.opensqldao.updateByMap_drug(paramMap, "order_custom_sql.updateGoodAddStockById");
				totalFee = totalFee.add(orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())));
			}
			
		}
       //退换货
		TReturn tReturn = new TReturn();
		if(orderold.getOrderStatus().intValue()==OrderStatusEnum.ORDER_NO_PAY.getIndex()||orderold.getOrderStatus().intValue()==OrderStatusEnum.ORDER_PAY.getIndex()){
			if(orderold.getOrderStatus().intValue()==OrderStatusEnum.ORDER_PAY.getIndex()){//判断订单是否支付过
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("len", 6);
				String returnOrderSn = (String) opensqldao.selectForObjectByMap(paramMap,
						"order_custom_sql.select_new_return_ordersn");
				while (returnOrderSn == null || "".equals(returnOrderSn) || "-1".equals(returnOrderSn)) {
					returnOrderSn = (String) opensqldao.selectForObjectByMap(paramMap, "order_custom_sql.select_new_return_ordersn");
				}
				tReturn.setOrderSn(returnOrderSn);
				tReturn.setServiceType(0);
				tReturn.setShipperPhone(orderold.getPhone());
				tReturn.setMemberId(orderold.getMemberId());
				tReturn.setRefundDescribe(orderold.getOrderSn()+"取消订单");
				tReturn.setOrderStatus(0);
				tReturn.setCreateTime(new Date());
				tReturn.setOldOrderId(orderold.getId());
				CPaymentWay paymentWay =cpaymentwaydao.selectByPrimaryKey(orderold.getPaymentId());
				if(paymentWay.getPaymentWay().intValue()==0){//线上
					tReturn.setRefundAmount(orderold.getPayableAmount());
				}else if(paymentWay.getPaymentWay().intValue()==1){//线下
					tReturn.setRefundAmount(new BigDecimal("0"));
				}
				Long returnId=this.treturndao.insertSelective(tReturn);
				TReturnItem returnItem = null;
				TOrder _tOrder = torderdao.selectByPrimaryKey(orderid);
				for(TOrderItem orderItem:orderItemList){
					returnItem = new TReturnItem();
					returnItem.setCreateTime(new Date());
					returnItem.setGoodsId(orderItem.getGoodsId());
					returnItem.setProductAmount(orderItem.getPrice());
					returnItem.setQuantity(orderItem.getQuantity());
					returnItem.setReturnId(returnId);
					this.treturnitemdao.insertSelective(returnItem);
					
					String json = packageJson(_tOrder, tReturn, returnItem, orderItem, totalFee);
					System.out.println("退换货同步海典之前封装json数据:::::"+json);
					callHdHttp(SYNHTTPURL,json,tReturn);
				}
			}
			
			flag = true;
			}else{
				throw new Exception("取消订单失败订单状态未在可取消范围内");
			}
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
		return flag;
	}
	
	/**
	 * 删除订单
	 * @param orderid 订单id
	 * @return boolean true 成功 false 失败
	 * @throws Exception
	 */
	public boolean deleteOrder(long orderid) throws Exception{
		boolean flag =false;
		try {
			TOrder order = new TOrder();
			order.setDeleteStatus(1);
			order.setId(orderid);
			int f = torderdao.updateByPrimaryKeySelective(order);
			if(f>0){
				flag=true;
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return flag;
		
	}
	/**
	 * 订单确认收货 
	 * @param orderid 订单id
	 * @param publicurl public项目访问地址
	 * @return  json (flag 成功标识 1 成功 0 失败  message 失败原因)
	 * @throws Exception
	 */
	public String orderConfirmReceipt(long orderid,String publicurl) throws Exception{
		  Map<String,Object> resultMap = new HashMap<String,Object>();
		  String flag ="1";
		  String message ="";
			try{
				 TOrder order = this.torderdao.selectByPrimaryKey(orderid);
					if (order.getOrderStatus() == OrderStatusEnum.ORDER_DELIVERY.getIndex()) {
						TOrder updateOrder = new TOrder();
						updateOrder.setId(orderid);
						updateOrder.setOrderStatus(OrderStatusEnum.ORDER_FINISH.getIndex());
						updateOrder.setFinishDate(new Date());
						this.updateByPrimaryKeySelective(updateOrder);
						TMemberIntegral tmemberintegral = new TMemberIntegral();
						tmemberintegral.setCreateDate(new Date());
						tmemberintegral.setIntegral(order.getRewardIntegration());
						tmemberintegral.setMemberId(order.getMemberId());
						tmemberintegral.setSource(9);
						tmemberintegraldao.insertSelective(tmemberintegral);
						
						Map<String, String> map = new HashMap<String, String>();
						map.put("memberId", order.getMemberId().toString());
						map.put("jifen", order.getRewardIntegration().toString());
						map.put("version", "v7");
						topicMessageSender.sendMapMessage(map);
						if (order.getPaymentId() != null && order.getPaymentId() > 0L) {
							CPaymentWay _cPaymentWay = cpaymentwaydao.selectByPrimaryKey(order.getPaymentId());
							if (_cPaymentWay != null && _cPaymentWay.getPaymentCode() != null&& order.getMemberId() != null) {
								Map<String,String> publicMap = new HashMap<String,String>();
								publicMap.put("orderId", String.valueOf(order.getId()));
								String resultJsonstr =ClientSubmitPublic.getPublicService(publicMap, publicurl+"leaderStayMoneyURLService");
								if(JSONObject.fromObject(resultJsonstr).get("statsCode").equals("0")){
									message=String.valueOf(order.getId())+":佣金计算失败,失败原因:"+JSONObject.fromObject(resultJsonstr).get("message");
									throw new Exception(String.valueOf(order.getId())+":佣金计算失败,失败原因:"+JSONObject.fromObject(resultJsonstr).get("message"));
								}
							}
						}
					} else if(order.getOrderStatus() != OrderStatusEnum.ORDER_FINISH.getIndex()){
						message="订单有误,请联系客服";
						throw new Exception("订单有误,请联系客服");
					}
			}catch(Exception e){
				flag="0";
				throw new Exception(e.getMessage());
			}
			resultMap.put("flag", flag);
			resultMap.put("message", message);
		return JSONObject.fromObject(resultMap).toString();
	}
	/**
	 * 根据用户id 统计该用户各个状态订单总数
	 * @param memberid 用户id
	 * @throws Exception
	 */
	public TOrderStatusCountModel orderStatusCountByMemberid(String startDate, String endDate, long memberid) throws Exception {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("memberid", memberid);
		if(startDate!=null){
			paramMap.put("startDate", startDate);
		}
		if(endDate!=null){
			paramMap.put("endDate", endDate);
		}
		List<Map<String, Object>> orderCountList = this.opensqldao.selectForListByMap(paramMap, "order_custom_sql.select_order_count_by_orderstatus_memberid");
		TOrderStatusCountModel orderStatusCountModel = new TOrderStatusCountModel();
		if (orderCountList!=null&&orderCountList.size()>0) {
			for (Map<String, Object> orderCountMap :orderCountList) {
				if(orderCountMap.get("order_status")!=null){ 
					int order_status = ((Integer)orderCountMap.get("order_status")).intValue();
					if(order_status ==OrderStatusEnum.ORDER_NO_PAY.getIndex()){//待支付
						orderStatusCountModel.setOrderNoPay(((Long)orderCountMap.get("orderCount")).intValue());
					}else if(order_status ==OrderStatusEnum.ORDER_PAY.getIndex()){//待发货
						orderStatusCountModel.setOrderPay(((Long)orderCountMap.get("orderCount")).intValue());
					}else if(order_status ==OrderStatusEnum.ORDER_DELIVERY.getIndex()){//待收货
						orderStatusCountModel.setOrderDelivery(((Long)orderCountMap.get("orderCount")).intValue());
					}else if(order_status ==OrderStatusEnum.ORDER_FINISH.getIndex()){//已完成
						orderStatusCountModel.setOrderFinish(((Long)orderCountMap.get("orderCount")).intValue());
					}else if(order_status ==OrderStatusEnum.ORDER_CANCEL.getIndex()){//已取消
						orderStatusCountModel.setOrderCancel(((Long)orderCountMap.get("orderCount")).intValue());
					}else if(order_status ==OrderStatusEnum.ORDER_OVERDUE.getIndex()){//已过期
						orderStatusCountModel.setOrderOverdue(((Long)orderCountMap.get("orderCount")).intValue());
					}else if(order_status ==OrderStatusEnum.ORDER_CLOSE.getIndex()){//已关闭
						orderStatusCountModel.setOrderClose(((Long)orderCountMap.get("orderCount")).intValue());
					}
				}
			}
		}
		Map<String, Object> resultMap = (Map<String, Object>) opensqldao.selectForObjectByMap(paramMap, "order_custom_sql.select_order_evaluate_count_memberid");
		if(resultMap!=null&&resultMap.get("revCount")!=null){
			orderStatusCountModel.setOrderRev(Integer.parseInt(resultMap.get("revCount").toString()));
		}
		return orderStatusCountModel;
	}
	/**
	 * 根据用户获取待评价订单个数
	 * @param memberid
	 * @return
	 * @throws Exception
	 */
	public int orderNoEvaluateCount(long memberid) throws Exception {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("memberid", memberid);
		int orderNoEvaluateCount =0;
		Map<String, Object> resultMap = (Map<String, Object>) opensqldao.selectForObjectByMap(paramMap, "order_custom_sql.select_order_evaluate_count_memberid");
		if(resultMap!=null&&resultMap.get("revCount")!=null){
			orderNoEvaluateCount=((Long)resultMap.get("revCount")).intValue();
		}
		return orderNoEvaluateCount;
	}
	/**
	 * 根据用户id和订单状态，查询订单列表
	 * @param memberId 用户id,startDate 开始日期YYYY-MM-dd,endDate 结束日期YYYY-MM-dd,orderType 订单状态,orderNO 订单编号,pageSize 每页显示条数，page 页码
	 * @throws Exception
	 */
	public PageWraper getOrderListByPage(Long memberId, String startDate, String endDate, Integer orderType, String orderNO, Integer pageSize,Integer page) {
		Map map=new HashMap();
		map.put("memberId", memberId);
		if(orderType!=null&&orderType!=-1){
			if(orderType==1){
				map.put("ordertype", 1);//待支付订单
			}else if(orderType==2){
				map.put("ordertype", 2);//待收货订单
			}else if(orderType==3){
				map.put("ordertype", 3);//待评价订单
			}else if(orderType==4){
				map.put("ordertype", 4);//待发货
			}else if(orderType==5){
				map.put("ordertype", 5);//历史订单
			}
		}else{
			map.put("ordertype", -1);//全部订单
		}
		if(startDate!=null&&!"".equals(startDate.trim())){
			map.put("startDate", startDate.trim());
		}
		if(endDate!=null&&!"".equals(endDate.trim())){
			map.put("endDate", endDate.trim());
		}
		if(orderNO!=null&&!"".equals(orderNO.trim())){
			map.put("orderno", orderNO.trim());
		}
		PageWraper pw = opensqldao.selectForPageByMap(map, "order_custom_sql.selectOrderListCount", "order_custom_sql.selectOrderListByPage", page, pageSize);
		if(pw.getResult()!=null&&pw.getResult().size()>0){
			for(Map ordermap:(List<Map>)pw.getResult()){
				Map maptem=new HashMap();
				maptem.put("orderid", ordermap.get("id").toString());
				List<Map> oi = opensqldao.selectForListByMap(maptem, "order_custom_sql.selectGoodsListByOrderId");
				ordermap.put("orderitem", oi);
			}
		}
		return pw;
	}
	
	/**
	 * 海典同步退换货信息
	 * @param httpUrl
	 * @param json
	 * @param tReturn
	 * @return
	 * @throws Exception
	 */
	public boolean callHdHttp(String httpUrl,String json,TReturn tReturn) throws Exception{
		boolean flag = false;
		URL postUrl = new URL(httpUrl);
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.connect();
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		String content = "param=" + json;//+ URLEncoder.encode(json, "UTF-8");
		out.writeBytes(content);
		out.flush();
		out.close();

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		String resultJson = "";
		while ((line = reader.readLine()) != null) {
			resultJson += line;
		}
		reader.close();
		connection.disconnect();
		Map<Object, Object> map = new HashMap<Object, Object>();
		JSONObject _jsonObject = JSONObject.fromObject(resultJson);
		Iterator<Object> it = _jsonObject.keys();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			Object key = it.next();
			Object value = _jsonObject.get(key);
			map.put(key, value);
		}
		System.out.println("【退换货】调用海典返回结果："+resultJson);
		if(map.get("code")!=null && map.get("code").toString().equals("1")){
			System.out.println("[退换货]海典返回成功");
			flag = true;
		}else{
			System.out.println("[退换货]推送海典异常,订单号【"+tReturn.getOrderSn()+"】,海典接口返回异常信息:"+map.get("msg"));
			throw new Exception("[退换货]推送海典异常,订单号【"+tReturn.getOrderSn()+"】,海典接口返回异常信息"+map.get("msg"));
		}
		return flag;
	}
	
	/**
	 * 封装json数据
	 * @return
	 */
	public String packageJson(TOrder tOrder,TReturn tReturn,TReturnItem tReturnItem,TOrderItem tOrderItem,BigDecimal totalFee){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HdReturn hdReturn = new HdReturn();
		hdReturn.setGroupid(groupid);//企业编码
		hdReturn.setEccode(eccode);//平台编码
		hdReturn.setOlorderno(tOrder.getOrderSn());//订单号
		hdReturn.setOlshopid(olshopid);//网店编号
		hdReturn.setShipping_type("");//物流方式
		hdReturn.setCs_status(2);//客服审核状态
		hdReturn.setRefund_id(tReturn.getOrderSn());//退款单号
		hdReturn.setOid(tOrderItem.getId().toString());//子订单号
		
		//总金额计算
		hdReturn.setTotal_fee(totalFee.toString());//交易总金额
		
		hdReturn.setBuyer_nick("");//买家昵称
		hdReturn.setSeller_nick("");//卖家昵称
		hdReturn.setCreated(sdf.format(tReturn.getCreateTime()==null?(new Date()):tReturn.getCreateTime()));//退款申请时间
		hdReturn.setModified(sdf.format(new Date()));//更新时间
		hdReturn.setOrder_status(0);//退款对应订单交易状态
		hdReturn.setOff_status(1);//退款状态
		
		Integer serviceType = tReturn.getServiceType();//服务类型  退货 Refunds(0), 换货 exchange(1)  ,2:退款(无需退货)
		if(serviceType!=null&&"0".equals(serviceType)){
			hdReturn.setHas_good_return(true);//买家是否需要退货
		}else{
			hdReturn.setHas_good_return(false);//买家是否需要退货
		}
		
		hdReturn.setRefund_fee("0.00");//退还金额(退还给买家的金额)
		hdReturn.setPayment("0.00");//支付给卖家的金额(交易总金额-退还给买家的金额)
		hdReturn.setReason(tReturn.getRefundDescribe());//退款原因
		hdReturn.setDescr(tReturn.getRefundRemark());//退款说明
		
		Map<String, Object> param1 = new HashMap<String,Object>();
		param1.put("goodId", tReturnItem.getGoodsId());
		param1.put("priceType", "app");//FIXME WWF平台待修改
		Map<String, Object> goodsMap = (Map<String, Object>) opensqldao.selectForObjectByMap(param1, "order_return.selectGoodsInfoById");
		
		hdReturn.setTitle((goodsMap!=null&&goodsMap.get("main_title")==null)?"":goodsMap.get("main_title").toString());//商品标题
		hdReturn.setPrice((goodsMap!=null&&goodsMap.get("price")==null)?"":goodsMap.get("price").toString());//商品价格
		hdReturn.setNum(tReturnItem.getQuantity().toString());//数量
		hdReturn.setGood_return_time(sdf.format(tReturnItem.getCreateTime()));//退货时间
		hdReturn.setCompany_name("");//物流公司名称
		hdReturn.setSid("");//退货运单号
		hdReturn.setAddress("");//卖家收货地址
		hdReturn.setNum_iid((goodsMap!=null&&goodsMap.get("goodsno")==null)?"":goodsMap.get("goodsno").toString());//退货商品数字编码
		hdReturn.setSku((goodsMap!=null&&goodsMap.get("SKU_ID")==null)?"":goodsMap.get("SKU_ID").toString());//商品SKU信息
		hdReturn.setOuter_id((goodsMap!=null&&goodsMap.get("goodsno")==null)?"":goodsMap.get("goodsno").toString());//商家外部编码
		
		JSONObject jsonObject = JSONObject.fromObject(hdReturn);
		return jsonObject.toString();
	}
	
	
	
	public TOrderItemDAO getTorderitemdao() {
		return torderitemdao;
	}
	public void setTorderitemdao(TOrderItemDAO torderitemdao) {
		this.torderitemdao = torderitemdao;
	}
	public TReturnDAO getTreturndao() {
		return treturndao;
	}
	public void setTreturndao(TReturnDAO treturndao) {
		this.treturndao = treturndao;
	}
	public TReturnItemDAO getTreturnitemdao() {
		return treturnitemdao;
	}
	public void setTreturnitemdao(TReturnItemDAO treturnitemdao) {
		this.treturnitemdao = treturnitemdao;
	}
	public CPaymentWayDAO getCpaymentwaydao() {
		return cpaymentwaydao;
	}
	public void setCpaymentwaydao(CPaymentWayDAO cpaymentwaydao) {
		this.cpaymentwaydao = cpaymentwaydao;
	}
	public TMemberIntegralDAO getTmemberintegraldao() {
		return tmemberintegraldao;
	}
	public void setTmemberintegraldao(TMemberIntegralDAO tmemberintegraldao) {
		this.tmemberintegraldao = tmemberintegraldao;
	}
	public MessageSender getTopicMessageSender() {
		return topicMessageSender;
	}
	public void setTopicMessageSender(MessageSender topicMessageSender) {
		this.topicMessageSender = topicMessageSender;
	}

	
	
}
