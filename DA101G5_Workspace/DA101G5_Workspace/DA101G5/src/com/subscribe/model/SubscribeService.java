package com.subscribe.model;

import java.sql.Timestamp;
import java.util.List;

public class SubscribeService {

	private SubscribeDAO_interface dao;

	public SubscribeService() {
		dao = new SubscribeDAO();
	}

	public SubscribeVO addSubscribe(String com_id, String b_member_id, String s_member_id,
			Integer com_price, Timestamp trandate, Integer buyer_evaluation_score) {

		SubscribeVO subscribeVO = new SubscribeVO();

		subscribeVO.setCom_id(com_id);
		subscribeVO.setB_member_id(b_member_id);
		subscribeVO.setS_member_id(s_member_id);
		subscribeVO.setCom_price(com_price);
		subscribeVO.setTrandate(trandate);
		subscribeVO.setBuyer_evaluation_score(buyer_evaluation_score);
		
		dao.insert(subscribeVO);
		
		return subscribeVO;
	}

	public SubscribeVO updateSubscribe(String sub_id, String com_id, String b_member_id, String s_member_id,
			Integer com_price, Timestamp trandate, Integer buyer_evaluation_score) {

		SubscribeVO subscribeVO = new SubscribeVO();

		subscribeVO.setSub_id(sub_id);
		subscribeVO.setCom_id(com_id);
		subscribeVO.setB_member_id(b_member_id);
		subscribeVO.setS_member_id(s_member_id);
		subscribeVO.setCom_price(com_price);
		subscribeVO.setTrandate(trandate);
		subscribeVO.setBuyer_evaluation_score(buyer_evaluation_score);
		
		dao.update(subscribeVO);

		return subscribeVO;
	}

	public void deleteSubscribe(String sub_id) {
		dao.delete(sub_id);
	}

	public SubscribeVO getOneSubscribe(String sub_id) {
		return dao.findByPrimaryKey(sub_id);
	}

	public List<SubscribeVO> getAll() {
		return dao.getAll();
	}
}
