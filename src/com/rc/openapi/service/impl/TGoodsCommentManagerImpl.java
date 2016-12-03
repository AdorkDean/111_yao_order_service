package com.rc.openapi.service.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rc.openapi.dao.TGoodsCommentDAO;
import com.rc.openapi.dao.TOrderDAO;
import com.rc.openapi.dao.TOrderItemDAO;
import com.rc.openapi.dubbo.vo.TGoodsComment;
import com.rc.openapi.dubbo.vo.TGoodsCommentExample;
import com.rc.openapi.dubbo.vo.TOrder;
import com.rc.openapi.dubbo.vo.TOrderExample;
import com.rc.openapi.dubbo.vo.TOrderItem;
import com.rc.openapi.dubbo.vo.TOrderItemExample;
import com.rc.openapi.service.TGoodsCommentManager;

public class TGoodsCommentManagerImpl  implements TGoodsCommentManager {

    private TGoodsCommentDAO tgoodscommentdao;
    private TOrderDAO torderdao;
    private TOrderItemDAO torderitemdao;

    public TGoodsCommentManagerImpl() {
        super();
    }
    public void  setTgoodscommentdao(TGoodsCommentDAO tgoodscommentdao){
        this.tgoodscommentdao=tgoodscommentdao;
    }
    public TGoodsCommentDAO getTgoodscommentdao(){
        return this.tgoodscommentdao;
    }
    
    public TOrderDAO getTorderdao() {
		return torderdao;
	}
	public void setTorderdao(TOrderDAO torderdao) {
		this.torderdao = torderdao;
	}
	public TOrderItemDAO getTorderitemdao() {
		return torderitemdao;
	}
	public void setTorderitemdao(TOrderItemDAO torderitemdao) {
		this.torderitemdao = torderitemdao;
	}
	public     int countByExample(TGoodsCommentExample example) throws SQLException{
        return tgoodscommentdao. countByExample( example);
    }

    public     int deleteByExample(TGoodsCommentExample example) throws SQLException{
        return tgoodscommentdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tgoodscommentdao. deleteByPrimaryKey( id);
    }

    public     Long insert(TGoodsComment record) throws SQLException{
        return tgoodscommentdao. insert( record);
    }

    public     Long insertSelective(TGoodsComment record) throws SQLException{
        return tgoodscommentdao. insertSelective( record);
    }

    public     List selectByExample(TGoodsCommentExample example) throws SQLException{
        return tgoodscommentdao. selectByExample( example);
    }

    public     TGoodsComment selectByPrimaryKey(Long id) throws SQLException{
        return tgoodscommentdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TGoodsComment record, TGoodsCommentExample example) throws SQLException{
        return tgoodscommentdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TGoodsComment record, TGoodsCommentExample example) throws SQLException{
        return tgoodscommentdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TGoodsComment record) throws SQLException{
        return tgoodscommentdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TGoodsComment record) throws SQLException{
        return tgoodscommentdao. updateByPrimaryKey( record);
    }
	@Override
	public List<TGoodsComment> selectGoodscommentByAjax(Map<String, Object> map) {
		List<TGoodsComment> list= tgoodscommentdao.selectGoodscommentByAjax(map);
		return list;
	}
	public boolean submitOrderComment(long memberId, long orderId, long goodId, int score, String content,
			List<String> imgList, List<File> imgFilesList) throws SQLException, Exception {
		TOrderExample tOrderExample = new TOrderExample();
		tOrderExample.createCriteria().andIdEqualTo(orderId).andMemberIdEqualTo(memberId);
		List<TOrder> ll = torderdao.selectByExample(tOrderExample);
		if (ll == null || ll.size() <= 0) {
			throw new Exception("订单信息有误，或非本人订单");
		}
		TOrderItemExample tOrderItemExample = new TOrderItemExample();
		tOrderItemExample.createCriteria().andOrderIdEqualTo(orderId).andGoodsIdEqualTo(goodId);
		List<TOrderItem> itemList = torderitemdao.selectByExample(tOrderItemExample);
		if (itemList == null || itemList.size() <= 0) {
			throw new Exception("商品信息有误");
		}
		if(itemList!=null&&itemList.size()>0&&itemList.get(0).getIfReviews()==1){
			throw new Exception("该订单此商品已评论");
		}
		if (score <= 0 || score > 5) {
			score = 5;
		}
		TGoodsComment record = new TGoodsComment();
		record.setSumFraction(score * 4);
		record.setType(score == 1 ? 3 : (score == 2 || score == 3) ? 2 : 1);
		record.setSeller(score);
		record.setFastMail(score);
		record.setGoods(score);
		record.setFastMailPeople(score);
		record.setIsShow(0);
		record.setComment(content);
		record.setMemberId(memberId);
		record.setGoodsId(goodId);
		record.setOrderId(orderId);
		record.setCreateTime(new Date());
		tgoodscommentdao.insertSelective(record);
		
		TOrderItem updateItem = new TOrderItem();
		for (TOrderItem tOrderItem : itemList) {
			updateItem.setId(tOrderItem.getId());
			updateItem.setIfReviews(1);
			torderitemdao.updateByPrimaryKeySelective(updateItem);
		}
		// TODO 评论图片处理,下版处理
		return true;
	}


}
