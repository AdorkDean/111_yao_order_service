package com.rc.openapi.hd.model;

import java.io.Serializable;

/**
 * 海典退款退货实体
 * 
 * @author WWF
 * @createTime 2016-8-26 上午10:44:35
 */
public class HdReturn implements Serializable {

	private static final long serialVersionUID = -8102440705869359144L;

	private String groupid;// 企业编码
	private String eccode;// 平台编码
	private String olorderno;// 订单号
	private String olshopid;// 网店编号
	private String shipping_type;// 物流方式(free(卖家包邮),post(平邮),express(快递),ems(EMS).)
	private int cs_status;// 客服审核状态(不需客服介入1;需要客服介入2;客服已经介入3;客服初审完成
							// 4;客服主管复审失败5;客服处理完成6;)
	private String refund_id;// 退款单号
	private String oid;// 子订单号
	private String total_fee;// 交易总金额
	private String buyer_nick;// 买家昵称
	private String seller_nick;// 卖家昵称
	private String created;// 退款申请时间
	private String modified;// 更新时间
	private int order_status;// 退款对应订单交易状态(0初始创建；1待处理；2终止处理；3开始拣货；4已出库)
	private int off_status;// 退款状态(0:不处理;1:待客服审核;2:客服拒绝退货;3:客服审核通过;4:收货确认完成;5:退款成功;6:拒绝退款)
	private boolean has_good_return;// 买家是否需要退货
	private String refund_fee;// 退还金额(退还给买家的金额)
	private String payment;// 支付给卖家的金额(交易总金额-退还给买家的金额)

	private String reason;// 退款原因
	private String descr;// 退款说明
	private String title;// 商品标题
	private String price;// 商品价格
	private String num;// 数量
	private String good_return_time;// 退货时间
	private String company_name;// 物流公司名称

	private String sid;// 退货运单号
	private String address;// 卖家收货地址
	private String num_iid;// 退货商品数字编码
	private String sku;// 商品SKU信息
	private String outer_id;// 商家外部编码

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getEccode() {
		return eccode;
	}

	public void setEccode(String eccode) {
		this.eccode = eccode;
	}

	public String getOlorderno() {
		return olorderno;
	}

	public void setOlorderno(String olorderno) {
		this.olorderno = olorderno;
	}

	public String getOlshopid() {
		return olshopid;
	}

	public void setOlshopid(String olshopid) {
		this.olshopid = olshopid;
	}

	public String getShipping_type() {
		return shipping_type;
	}

	public void setShipping_type(String shipping_type) {
		this.shipping_type = shipping_type;
	}

	public int getCs_status() {
		return cs_status;
	}

	public void setCs_status(int cs_status) {
		this.cs_status = cs_status;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getBuyer_nick() {
		return buyer_nick;
	}

	public void setBuyer_nick(String buyer_nick) {
		this.buyer_nick = buyer_nick;
	}

	public String getSeller_nick() {
		return seller_nick;
	}

	public void setSeller_nick(String seller_nick) {
		this.seller_nick = seller_nick;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public int getOrder_status() {
		return order_status;
	}

	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}

	public int getOff_status() {
		return off_status;
	}

	public void setOff_status(int off_status) {
		this.off_status = off_status;
	}

	public boolean getHas_good_return() {
		return has_good_return;
	}

	public void setHas_good_return(boolean has_good_return) {
		this.has_good_return = has_good_return;
	}

	public String getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(String refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getGood_return_time() {
		return good_return_time;
	}

	public void setGood_return_time(String good_return_time) {
		this.good_return_time = good_return_time;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNum_iid() {
		return num_iid;
	}

	public void setNum_iid(String num_iid) {
		this.num_iid = num_iid;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getOuter_id() {
		return outer_id;
	}

	public void setOuter_id(String outer_id) {
		this.outer_id = outer_id;
	}

}
