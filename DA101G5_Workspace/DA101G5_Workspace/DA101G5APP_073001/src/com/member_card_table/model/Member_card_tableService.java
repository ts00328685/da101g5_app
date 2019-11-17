package com.member_card_table.model;

import java.util.List;

public class Member_card_tableService {

	private Member_card_tableDAO_interface dao;

	public Member_card_tableService() {
		dao = new Member_card_tableDAO();
	}

	public Member_card_tableVO addMember_card_table(String card_id, String member_card_group_id,
			java.sql.Timestamp review_time, Integer wrong_number, Integer right_number,
			Integer daily_answer, Integer choice_card_group) {

		Member_card_tableVO member_card_tableVO = new Member_card_tableVO();

		member_card_tableVO.setCard_id(card_id);
		member_card_tableVO.setMember_card_group_id(member_card_group_id);
		member_card_tableVO.setReview_time(review_time);
		member_card_tableVO.setWrong_number(wrong_number);
		member_card_tableVO.setRight_number(right_number);
		member_card_tableVO.setDaily_answer(daily_answer);
		member_card_tableVO.setChoice_card_group(choice_card_group);
		dao.insert(member_card_tableVO);
		
		return member_card_tableVO;
	}

	public Member_card_tableVO updateMember_card_table(String card_id, String member_card_group_id,
			java.sql.Timestamp review_time, Integer wrong_number, Integer right_number,
			Integer daily_answer, Integer choice_card_group) {

		Member_card_tableVO member_card_tableVO = new Member_card_tableVO();

		member_card_tableVO.setCard_id(card_id);
		member_card_tableVO.setMember_card_group_id(member_card_group_id);
		member_card_tableVO.setReview_time(review_time);
		member_card_tableVO.setWrong_number(wrong_number);
		member_card_tableVO.setRight_number(right_number);
		member_card_tableVO.setDaily_answer(daily_answer);
		member_card_tableVO.setChoice_card_group(choice_card_group);
		dao.update(member_card_tableVO);

		return member_card_tableVO;
	}

	public void deleteMember_card_table(String card_id, String member_card_group_id) {
		dao.delete(card_id, member_card_group_id);
	}

	public Member_card_tableVO getOneMember_card_table(String card_id, String member_card_group_id) {
		return dao.findByPrimaryKey(card_id, member_card_group_id);
	}

	public List<Member_card_tableVO> getAll() {
		return dao.getAll();
	}
}
