package com.rc.openapi.serviceApiImpl;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.rc.openapi.dubbo.vo.TOrder;
import com.rc.openapi.dubbo.vo.TOrderItem;
import com.rc.openapi.dubbo.vo.TOrderItemExample;
import com.rc.openapi.service.TGoodsCommentManager;
import com.rc.openapi.service.TOrderItemManager;
import com.rc.openapi.service.TOrderManager;
import com.rc.openapi.serviceApi.OrderCommentServiceDubboApi;

/**
 * 商品评论
 * 
 * @author WWF
 * @createTime 2016-8-25 上午9:53:13
 */
public class OrderCommentServiceDubboApiImpl implements OrderCommentServiceDubboApi {

	private TGoodsCommentManager tgoodscommentmanager;

	private TOrderManager tordermanager;
	private TOrderItemManager torderitemmanager;

	public TGoodsCommentManager getTgoodscommentmanager() {
		return tgoodscommentmanager;
	}

	public void setTgoodscommentmanager(TGoodsCommentManager tgoodscommentmanager) {
		this.tgoodscommentmanager = tgoodscommentmanager;
	}

	/**
	 * 提交评论
	 * 
	 * @param memberId用户信息
	 * @param orderId订单ID
	 * @param goodId商品ID
	 * @param score评分
	 * @param content内容
	 * @param imgList图片base64串
	 * @param imgFilesList图片文件
	 * @throws SQLException
	 */
	public boolean submitOrderComment(long memberId, long orderId, long goodId, int score, String content,
			List<String> imgList, List<File> imgFilesList) throws SQLException, Exception {
		return tgoodscommentmanager.submitOrderComment( memberId,  orderId,  goodId,  score,  content,
				 imgList,  imgFilesList);
	}
	
	/**
	 * 取未退换货的商品项
	 * @param orderId
	 * @param memberId
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<TOrderItem> getNoReturnGoods(long orderId, long memberId) throws SQLException, Exception {
		TOrder tOrder = tordermanager.selectByPrimaryKey(orderId);
		if(tOrder==null||tOrder.getMemberId().longValue()!=memberId){
			return null;
		}
		TOrderItemExample tOrderItemExample = new TOrderItemExample();
		tOrderItemExample.createCriteria().andOrderIdEqualTo(orderId).andRefundProcessEqualTo(0);
		return torderitemmanager.selectByExample(tOrderItemExample);
	}

	public TOrderManager getTordermanager() {
		return tordermanager;
	}

	public void setTordermanager(TOrderManager tordermanager) {
		this.tordermanager = tordermanager;
	}

	public TOrderItemManager getTorderitemmanager() {
		return torderitemmanager;
	}

	public void setTorderitemmanager(TOrderItemManager torderitemmanager) {
		this.torderitemmanager = torderitemmanager;
	}

}
