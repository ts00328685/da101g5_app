package com.member_card_group.model;

import java.util.List;

public class Member_card_groupService {

	private Member_card_groupDAO_interface dao;

	public Member_card_groupService() {
		dao = new Member_card_groupDAO();
	}

	public Member_card_groupVO addMember_card_group(String teacher_card_group_id, String member_id, 
								Integer expiration_num, Integer new_card_num, Integer spend_time) {

		Member_card_groupVO member_card_groupVO = new Member_card_groupVO();

		member_card_groupVO.setTeacher_card_group_id(teacher_card_group_id);
		member_card_groupVO.setMember_id(member_id);
		member_card_groupVO.setExpiration_num(expiration_num);
		member_card_groupVO.setNew_card_num(new_card_num);
		member_card_groupVO.setSpend_time(spend_time);
		dao.insert(member_card_groupVO);
		
		return member_card_groupVO;
	}

	public Member_card_groupVO updateMember_card_group(String member_card_group_id, String teacher_card_group_id, String member_id,
											Integer expiration_num, Integer new_card_num, Integer spend_time) {

		Member_card_groupVO member_card_groupVO = new Member_card_groupVO();
		
		member_card_groupVO.setMember_card_group_id(member_card_group_id);
		member_card_groupVO.setTeacher_card_group_id(teacher_card_group_id);
		member_card_groupVO.setMember_id(member_id);
		member_card_groupVO.setExpiration_num(expiration_num);
		member_card_groupVO.setNew_card_num(new_card_num);
		member_card_groupVO.setSpend_time(spend_time);
		dao.update(member_card_groupVO);

		return member_card_groupVO;
	}

	public void deleteMember_card_group(String member_card_group_id) {
		dao.delete(member_card_group_id);
	}

	public Member_card_groupVO getOneMember_card_group(String member_card_group_id) {
		return dao.findByPrimaryKey(member_card_group_id);
	}

	public List<Member_card_groupVO> getAll() {
		return dao.getAll();
	}
}
