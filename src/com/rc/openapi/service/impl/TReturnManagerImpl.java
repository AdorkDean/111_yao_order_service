package com.rc.openapi.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import sun.misc.BASE64Decoder;

import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.openapi.dao.OpenSqlDAO;
import com.rc.openapi.dao.TOrderDAO;
import com.rc.openapi.dao.TOrderItemDAO;
import com.rc.openapi.dao.TReturnDAO;
import com.rc.openapi.dao.TReturnImageDAO;
import com.rc.openapi.dao.TReturnItemDAO;
import com.rc.openapi.dubbo.enumc.ReturnStatusEnum;
import com.rc.openapi.dubbo.model.TGoodModel;
import com.rc.openapi.dubbo.model.TOrderReturnModel;
import com.rc.openapi.dubbo.vo.TOrder;
import com.rc.openapi.dubbo.vo.TOrderItem;
import com.rc.openapi.dubbo.vo.TOrderItemExample;
import com.rc.openapi.dubbo.vo.TReturn;
import com.rc.openapi.dubbo.vo.TReturnExample;
import com.rc.openapi.dubbo.vo.TReturnImage;
import com.rc.openapi.dubbo.vo.TReturnItem;
import com.rc.openapi.dubbo.vo.TReturnItemExample;
import com.rc.openapi.hd.model.HdReturn;
import com.rc.openapi.service.TReturnManager;
import com.rc.openapi.util.InfoUtil;

public class TReturnManagerImpl implements TReturnManager {

	private TReturnDAO treturndao;
	private TReturnItemDAO treturnitemdao;

	
	private TOrderDAO torderdao;
	private TOrderItemDAO torderitemdao;
	
	private TReturnImageDAO treturnimagedao;
	
	private OpenSqlDAO opensqldao;

	private String SYNHTTPURL = InfoUtil.getInstance().getInfo("config", "hd.order.return.httpurl");
	private String SYNHTTPURLLZ = InfoUtil.getInstance().getInfo("config", "hd.order.return.httpurl.lz");
	
	private String groupid = InfoUtil.getInstance().getInfo("config", "hd.order.return.groupid");//"1001";//企业编码
	private String olshopid = InfoUtil.getInstance().getInfo("config", "hd.order.return.olshopid");//"100138";
	private String eccode = InfoUtil.getInstance().getInfo("config", "hd.order.return.eccode");//"309
	
	
	public TReturnManagerImpl() {
		super();
	}

	public void setTreturndao(TReturnDAO treturndao) {
		this.treturndao = treturndao;
	}

	public TReturnDAO getTreturndao() {
		return this.treturndao;
	}

	public TReturnImageDAO getTreturnimagedao() {
		return treturnimagedao;
	}

	public void setTreturnimagedao(TReturnImageDAO treturnimagedao) {
		this.treturnimagedao = treturnimagedao;
	}

	public TOrderItemDAO getTorderitemdao() {
		return torderitemdao;
	}

	public void setTorderitemdao(TOrderItemDAO torderitemdao) {
		this.torderitemdao = torderitemdao;
	}

	public int countByExample(TReturnExample example) throws SQLException {
		return treturndao.countByExample(example);
	}

	public int deleteByExample(TReturnExample example) throws SQLException {
		return treturndao.deleteByExample(example);
	}

	public int deleteByPrimaryKey(Long id) throws SQLException {
		return treturndao.deleteByPrimaryKey(id);
	}

	public Long insert(TReturn record) throws SQLException {
		return treturndao.insert(record);
	}

	public Long insertSelective(TReturn record) throws SQLException {
		return treturndao.insertSelective(record);
	}

	public List selectByExample(TReturnExample example) throws SQLException {
		return treturndao.selectByExample(example);
	}

	public TReturn selectByPrimaryKey(Long id) throws SQLException {
		return treturndao.selectByPrimaryKey(id);
	}

	public int updateByExampleSelective(TReturn record, TReturnExample example) throws SQLException {
		return treturndao.updateByExampleSelective(record, example);
	}

	public int updateByExample(TReturn record, TReturnExample example) throws SQLException {
		return treturndao.updateByExample(record, example);
	}

	public int updateByPrimaryKeySelective(TReturn record) throws SQLException {
		return treturndao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TReturn record) throws SQLException {
		return treturndao.updateByPrimaryKey(record);
	}
	
	public TOrderDAO getTorderdao() {
		return torderdao;
	}

	public void setTorderdao(TOrderDAO torderdao) {
		this.torderdao = torderdao;
	}
	
	public TReturnItemDAO getTreturnitemdao() {
		return treturnitemdao;
	}

	public void setTreturnitemdao(TReturnItemDAO treturnitemdao) {
		this.treturnitemdao = treturnitemdao;
	}

	public OpenSqlDAO getOpensqldao() {
		return opensqldao;
	}

	public void setOpensqldao(OpenSqlDAO opensqldao) {
		this.opensqldao = opensqldao;
	}

	/**
	 * 存储过程取退换货订单编号
	 * 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	@Override
	public String getTurnOrderSn(Map<String, Object> paramMap) throws SQLException {
		String turnOrderSn = treturndao.getTurnOrderSn(paramMap);
		while (turnOrderSn == null || "".equals(turnOrderSn) || "-1".equals(turnOrderSn)) {
			turnOrderSn = treturndao.getTurnOrderSn(paramMap);
		}
		return turnOrderSn;
	}

	/**
	 * 
	 */
	@Override
	public boolean submitReturnInfo(String price_type, long orderId, String returnType, String returnRemark, List<String> imgs,
			List<File> fileList, Map<Long, Integer> returnParam) throws SQLException, Exception {
		boolean flag = false;
		if(orderId<0){
			throw new Exception("订单信息有误");
		}
		TOrder tOrder = torderdao.selectByPrimaryKey(orderId);
		if(tOrder==null){
			throw new Exception("订单信息有误");
		}
		if(returnRemark==null||"".equals(returnRemark)){
			throw new Exception("退款说明必填");
		}
		if(returnParam.isEmpty()){
			throw new Exception("退货商品必填");
		}
		Map<String, Long> param = new HashMap<String, Long>();
		param.put("id", tOrder.getMemberId());
		Map<String, Object> resultMap = (Map<String, Object>) opensqldao.selectObjectByObject(param, "order_return.selectMemberByMemberId");
		
		//退款,选商品,没数量
		//退货退款,选商品选数量,只能退一次;
		
		//商品表里更改已发起(退货退款/退款)
		
		TReturnExample tReturnExample = new TReturnExample();
		tReturnExample.createCriteria().andOldOrderIdEqualTo(orderId).andMemberIdEqualTo(tOrder.getMemberId());
		
//		Long returnId = 0L;
//		List<Long> returnItemList = new ArrayList<Long>();
		List<TReturnItem> itemsList = new ArrayList<TReturnItem>();
		
		List<TReturn> returnList = treturndao.selectByExample(tReturnExample);
		TReturn tReturn = null;
		if(returnList==null||returnList.size()<=0){//没退换货信息;
			 tReturn = new TReturn();
			//操作,存
			tReturn.setCreateTime(new Date());
			tReturn.setMemberId(tOrder.getMemberId());
			tReturn.setOldOrderId(Long.valueOf(orderId));
			tReturn.setRefundDescribe(returnRemark);
			tReturn.setServiceType(Integer.valueOf(returnType));//0:退货,2:退款
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("len", 6);
			String returnOrderSn = (String) opensqldao.selectForObjectByMap(paramMap,
					"order_return.select_new_return_ordersn");
			while (returnOrderSn == null || "".equals(returnOrderSn) || "-1".equals(returnOrderSn)) {
				returnOrderSn = (String) opensqldao.selectForObjectByMap(paramMap, "order_return.select_new_return_ordersn");
			}
			tReturn.setOrderSn(returnOrderSn);
//			tReturn.setOrderSn(tOrder.getOrderSn());
			tReturn.setOrderStatus(ReturnStatusEnum.untreated.getIndex());

			tReturn.setShipperPhone((resultMap!=null&&resultMap.get("phone")==null)?null:resultMap.get("phone").toString());
			
			treturndao.insertSelective(tReturn);
			
			Set<Entry<Long, Integer>> set = returnParam.entrySet();
			//此处订单项的商品id及个数不做验证;
			TReturnItem tReturnItem = null;
			for (Entry<Long, Integer> entry : set) {
				tReturnItem = new TReturnItem();
				tReturnItem.setCreateTime(new Date());
				tReturnItem.setGoodsId(entry.getKey());
				tReturnItem.setQuantity(entry.getValue());
				tReturnItem.setReturnId(tReturn.getId());
				treturnitemdao.insertSelective(tReturnItem);
//				returnItemList.add(returnItemId);
				itemsList.add(tReturnItem);
				
				//订单项更新
				TOrderItemExample tOrderItemExample = new TOrderItemExample();
				tOrderItemExample.createCriteria().andOrderIdEqualTo(orderId).andGoodsIdEqualTo(entry.getKey());
				List<TOrderItem> ll = torderitemdao.selectByExample(tOrderItemExample);
				TOrderItem updateItem = null;
				for (TOrderItem tOrderItem : ll) {
					updateItem = new TOrderItem();
					updateItem.setId(tOrderItem.getId());
					updateItem.setRefundProcess(1);
					torderitemdao.updateByPrimaryKeySelective(updateItem);
				}
			}
			
			if(imgs!=null&&imgs.size()>0){
				List<String> imgUrlList = openThread(imgs);
				TReturnImage tReturnImage = null;
				for (String string : imgUrlList) {
					tReturnImage = new TReturnImage();
					tReturnImage.setImageUrl(string);
					tReturnImage.setReturnId(tReturn.getId());
					treturnimagedao.insertSelective(tReturnImage);
				}
				
			}
			flag = true;
		}else{//已经存在退换货主表数据
			Set<Entry<Long, Integer>> set = returnParam.entrySet();
			//判断有无重复申请的
			int repeat = 0;
			tReturn = returnList.get(0);
//			returnId = tReturn.getId();
			TReturnItemExample tReturnItemExample = new TReturnItemExample();
			tReturnItemExample.createCriteria().andReturnIdEqualTo(tReturn.getId());
			List<TReturnItem> itemList = treturnitemdao.selectByExample(tReturnItemExample);
			for (TReturnItem tReturnItem : itemList) {
				if(returnParam.containsKey(tReturnItem.getGoodsId())){
					repeat += 1;
				}
			}
			if(repeat>0){//有重复
				throw new Exception("有重复申请退换货数据");
			}else{//没重复
				TReturnItem tReturnItem = null;
				for (Entry<Long, Integer> entry : set) {
					tReturnItem = new TReturnItem();
					tReturnItem.setCreateTime(new Date());
					tReturnItem.setGoodsId(entry.getKey());
					tReturnItem.setQuantity(entry.getValue());
					tReturnItem.setReturnId(tReturn.getId());
					treturnitemdao.insertSelective(tReturnItem);
//					returnItemList.add(returnItemId);
					itemsList.add(tReturnItem);
					
					System.out.println("xxxxxxxxxxxxxxxx:::"+tReturnItem.getId());
					
					//订单项更新
					TOrderItemExample tOrderItemExample = new TOrderItemExample();
					tOrderItemExample.createCriteria().andOrderIdEqualTo(orderId).andGoodsIdEqualTo(entry.getKey());
					List<TOrderItem> ll = torderitemdao.selectByExample(tOrderItemExample);
					TOrderItem updateItem = null;
					for (TOrderItem tOrderItem : ll) {
						updateItem = new TOrderItem();
						updateItem.setId(tOrderItem.getId());
						updateItem.setRefundProcess(1);
						torderitemdao.updateByPrimaryKeySelective(updateItem);
					}
				}
				
				if(imgs!=null&&imgs.size()>0){
					List<String> imgUrlList = openThread(imgs);
					TReturnImage tReturnImage = null;
					for (String string : imgUrlList) {
						tReturnImage = new TReturnImage();
						tReturnImage.setImageUrl(string);
						tReturnImage.setReturnId(tReturn.getId());
						treturnimagedao.insertSelective(tReturnImage);
					}
				}
				flag = true;
			}
		}
		
		TOrderItemExample tOrderItemExample = new TOrderItemExample();
		tOrderItemExample.createCriteria().andOrderIdEqualTo(tOrder.getId());
		List<TOrderItem> tOrderItems = torderitemdao.selectByExample(tOrderItemExample);
		
		BigDecimal totalFee = new BigDecimal(0);
		for (TOrderItem tOrderItem : tOrderItems) {
			if(tOrderItem!=null && tOrderItem.getPrice()!=null && tOrderItem
					.getQuantity()!=null){
				totalFee = totalFee.add(tOrderItem.getPrice().multiply(new BigDecimal(tOrderItem.getQuantity())));
			}
		}
		
		//同步海典;
//		TReturn tReturn = treturndao.selectByPrimaryKey(returnId);
		TOrderItemExample tOrderItemExample2 = null;
		for (TReturnItem  tReturnItem : itemsList) {
			tOrderItemExample2 = new TOrderItemExample();
			tOrderItemExample2.createCriteria().andOrderIdEqualTo(orderId).andGoodsIdEqualTo(tReturnItem.getGoodsId());
			List<TOrderItem> orderItemList = torderitemdao.selectByExample(tOrderItemExample2);
			String json = packageJson(tOrder,tReturn,tReturnItem,orderItemList.get(0),totalFee);
			System.out.println("退换货同步海典之前封装json数据:::::"+json);
			callHdHttp(SYNHTTPURL,json,tReturn);
		}
		
		return flag;
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
	
	
	public static void main(String[] args) throws Exception {
		URL postUrl = new URL("http://118.26.140.56:100/search/100138/addrefund/111/1/");
//		String json = "{\"address\":\"\",\"buyer_nick\":\"\",\"company_name\":\"\",\"created\":\"2016-09-01 16:56:54\",\"cs_status\":2,\"descr\":\"\",\"eccode\":\"309\",\"good_return_time\":\"2016-09-01 16:56:54\",\"groupid\":\"1001\",\"has_good_return\":false,\"modified\":\"2016-09-01 16:56:53\",\"num\":\"1\",\"num_iid\":\"0909029\",\"off_status\":1,\"oid\":\"18677\",\"olorderno\":\"16090116032572\",\"olshopid\":\"100138\",\"order_status\":0,\"outer_id\":\"0909029\",\"payment\":\"0.00\",\"price\":\"7.20\",\"reason\":\"test\",\"refund_fee\":\"0.00\",\"refund_id\":\"16090116032572\",\"seller_nick\":\"\",\"shipping_type\":\"\",\"sid\":\"\",\"sku\":\"1444386294710\",\"title\":\"晕车晕船 外用贴剂 无副作用\",\"total_fee\":\"42.40\"}";
		String json = "{\"address\":\"\",\"buyer_nick\":\"\",\"company_name\":\"\",\"created\":\"2016-09-02 10:47:02\",\"cs_status\":2,\"descr\":\"\",\"eccode\":\"309\",\"good_return_time\":\"2016-09-06 10:36:09\",\"groupid\":\"1001\",\"has_good_return\":false,\"modified\":\"2016-09-06 10:36:09\",\"num\":\"2\",\"num_iid\":\"0913250\",\"off_status\":1,\"oid\":\"18688\",\"olorderno\":\"16090210867752\",\"olshopid\":\"100138\",\"order_status\":0,\"outer_id\":\"0913250\",\"payment\":\"0.00\",\"price\":\"79.00\",\"reason\":\"离离原上草，一岁一枯荣。\r\n野火烧不尽，春风吹又生。\r\n远芳侵古道，晴翠接荒城。\r\n又送王孙去，萋萋满别情。\",\"refund_fee\":\"0.00\",\"refund_id\":\"16090210637623\",\"seller_nick\":\"\",\"shipping_type\":\"\",\"sid\":\"\",\"sku\":\"1444385624336\",\"title\":\"家用电子血压计 使用方便 简单\",\"total_fee\":\"404.80\"}";
		
		
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
		System.out.println("*********"+content);
		out.writeBytes(content);
		out.flush();
		out.close();
		System.out.println("封装json:"+json);
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		String resultJson = "";
		while ((line = reader.readLine()) != null) {
			resultJson += line;
		}
		reader.close();
		connection.disconnect();
		System.out.println("[退换货订单]调用海典接口返回值:\t\t" + resultJson);
		Map<Object, Object> map = new HashMap<Object, Object>();
		JSONObject _jsonObject = JSONObject.fromObject(resultJson);
		Iterator<Object> it = _jsonObject.keys();
		// 遍历jsonObject数据，添加到Map对象
		while (it.hasNext()) {
			Object key = it.next();
			Object value = _jsonObject.get(key);
			map.put(key, value);
		}
		if(map.get("code")!=null && map.get("code").toString().equals("1")){
			System.out.println("海典返回成功");
		}else{
			throw new Exception("[退换货订单]推送海典异常,订单号【 】,海典接口返回异常信息"+map.get("msg"));
		}
	}
	

	public List<String> openThread(List<String> imgs) throws InterruptedException, ExecutionException{
		List<String> imgPathList = null;
		if(imgs!=null&&imgs.size()>0){
			int taskSize = imgs.size();
			if(taskSize>0){
				imgPathList = new ArrayList<String>();
				ExecutorService pool = Executors.newFixedThreadPool(taskSize);
				List<Future> list = new ArrayList<Future>();
				for (int i = 0; i < taskSize; i++) {
					final String img = imgs.get(i);
					final int j = i;
					Callable<Object> c = new Callable<Object>() {
						public Object call() throws Exception {
							return uploadFile(img,j);
						}
					};
					Future<Object> f = pool.submit(c);
					list.add(f);
				}
				pool.shutdown();
				for (Future f : list) {
					if(f!=null&&f.get()!=null){
						imgPathList.add(f.get().toString());
					}
				}
			}
		}
		return imgPathList;
	}
	
	public String uploadFile(String fileData,int j){
		String imgPath = null;
		if (fileData != null&&!"".equals(fileData)){
			String diskPath=InfoUtil.getInstance().getInfo("config", "images.public.head.path");
			String savePath = this.getClass().getResource("/").getPath()+getDiskName()+"/";
			System.out.println("图片前缀路径:\t\t"+savePath);
			BASE64Decoder decoder = new BASE64Decoder();  
			OutputStream out = null;
			try{  
				byte[] b = decoder.decodeBuffer(fileData);  
				for(int i=0;i<b.length;++i){  
					if(b[i]<0){//调整异常数据  
						b[i]+=256;  
					}  
				}  
				File f = new File(savePath);
				if (!f.exists() && !f.isDirectory()) {
					f.mkdirs();
				}
				String str = DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()+j)) + ".jpg";
				imgPath = diskPath+"/"+getDiskName()+"/"+str;
				out = new FileOutputStream(savePath+str);      
				out.write(b);  
			}catch (Exception e){  
				e.printStackTrace();
			}finally{
				if(null!=out){
					try {
						out.flush();
						out.close();  
					} catch (IOException e) {
						e.printStackTrace();
					}  
				}
			}
		}
		return imgPath;
	}
	
	public static String getDiskName(){
		String diskname="";
		DateFormat df = new SimpleDateFormat("yyyyMM");
		diskname =df.format(new Date());
		return diskname;
	}
	
	
	
	/**
	 * 查询退款退货订单  分页查询
	 * @param memberid 用户id
	 * @param returnOrderNo 退款退货订单编号
	 * @param orderStatus   退款订单状态集合
	 * @param fromDate   退款订单创建开始时间 yyyy-MM-dd HH:mm:ss
	 * @param toDate 退款订单创建结束时间yyyy-MM-dd HH:mm:ss
	 * @param page   当前页
	 * @param pagesize  每页显示多少条
	 * @param serviceTypeList  退换货类型(0:退货;2:退款)
	 * @return  PageWraper
	 * @throws Exception
	 */
	public PageWraper getOrderReturn (long memberid,String returnOrderNo,List<Integer> orderStatus,Date fromDate,Date toDate,int page,
			int pagesize,List<Integer> serviceTypeList) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("memberid", memberid);
		if (StringUtils.hasText(returnOrderNo)) {
			paramMap.put("returnOrderNo", returnOrderNo.trim());
		}
		if(orderStatus!=null&&orderStatus.size()>0){
			paramMap.put("statusIn", orderStatus);
		}
		if(serviceTypeList!=null&&serviceTypeList.size()>0){
			paramMap.put("serviceTypeIn", serviceTypeList);
		}
		if(fromDate!=null){
			paramMap.put("fromDate", sdf.format(fromDate));
		}
		if(toDate!=null){
			paramMap.put("toDate", sdf.format(toDate));
		}
		
		PageWraper pw =this.opensqldao.selectForPageByMap(paramMap, "order_return.selectPageCountByMap", "order_return.selectPageListByMap", page, pagesize);
		if(pw!=null&&pw.getResult()!=null&&pw.getResult().size()>0){
			List<TOrderReturnModel> returnList = pw.getResult();
			for (TOrderReturnModel returnModel :returnList) {
				paramMap.clear();
				paramMap.put("returnId", returnModel.getId());
				List<TGoodModel> goodList =this.opensqldao.selectForListByMap(paramMap, "order_return.selectReturnItemGoodsByReturnId");
			    if(goodList!=null){
			    	returnModel.setGoodsList(goodList);
			    }
			}
			pw.setResult(returnList);
		}
		return pw;
	}
	
	
	/**
	 * 分页查询所有
	 * @param userName
	 * @param returnOrderNo
	 * @param refundDescribe
	 * @param orderStatus
	 * @param fromDate
	 * @param toDate
	 * @param page
	 * @param pagesize
	 * @param serviceTypeList
	 * @return
	 * @throws Exception
	 */
	public PageWraper getAllOrderReturn (String userName,String returnOrderNo,String refundDescribe,List<Integer> orderStatus,Date fromDate,Date toDate,int page,
			int pagesize,List<Integer> serviceTypeList) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if (refundDescribe != null && !"".equals(refundDescribe)) {
			paramMap.put("refundDescribe", refundDescribe);
		}
		if (userName != null && !"".equals(userName)) {
			paramMap.put("userName", userName);
		}
		
		if(orderStatus!=null&&orderStatus.size()>0){
			paramMap.put("statusIn", orderStatus);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.hasText(returnOrderNo)) {
			paramMap.put("returnOrderNo", returnOrderNo.trim());
		}
		if(orderStatus!=null&&orderStatus.size()>0){
			paramMap.put("statusIn", orderStatus);
		}
		if(serviceTypeList!=null&&serviceTypeList.size()>0){
			paramMap.put("serviceTypeIn", serviceTypeList);
		}
		if(fromDate!=null){
			paramMap.put("fromDate", sdf.format(fromDate));
		}
		if(toDate!=null){
			paramMap.put("toDate", sdf.format(toDate));
		}
		
		PageWraper pw =this.opensqldao.selectForPageByMap(paramMap, "order_return.selectCountAllOrderReturn", "order_return.selectAllOrderReturn", page, pagesize);
//		if(pw!=null&&pw.getResult()!=null&&pw.getResult().size()>0){
//			List<TOrderReturnModel> returnList = pw.getResult();
//			for (TOrderReturnModel returnModel :returnList) {
//				paramMap.clear();
//				paramMap.put("returnId", returnModel.getId());
//				List<TGoodModel> goodList =this.opensqldao.selectForListByMap(paramMap, "order_return.selectReturnItemGoodsByReturnId");
//			    if(goodList!=null){
//			    	returnModel.setGoodsList(goodList);
//			    }
//			}
//			pw.setResult(returnList);
//		}
		return pw;
	}
	
}
