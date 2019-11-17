package com.friendchoose.model;

import java.util.ArrayList;
import java.util.List;

import com.member.model.MemberVO;

public class FriendChooseService {
	
	private FriendChooseDAO_interface dao;
	
	public FriendChooseService() {
		 dao = new FriendChooseDAO();
	}
	
	//新增
	public FriendChooseVO addFriendChoose(String condition_language_id,String condition_member_id,Integer condition_sex) {
		FriendChooseVO friendChooseVO = new FriendChooseVO();
		friendChooseVO.setCondition_language_id(condition_language_id);
		friendChooseVO.setCondition_member_id(condition_member_id);
		friendChooseVO.setCondition_sex(condition_sex);
		dao.insert(friendChooseVO);
		
		return friendChooseVO;
	}
	
	//修改
	public FriendChooseVO updateFriendChoose(String condition_id,String condition_language_id,String condition_member_id,Integer condition_sex) {
		FriendChooseVO friendChooseVO = new FriendChooseVO();
		friendChooseVO.setCondition_id(condition_id);
		friendChooseVO.setCondition_language_id(condition_language_id);
		friendChooseVO.setCondition_member_id(condition_member_id);
		friendChooseVO.setCondition_sex(condition_sex);
		dao.update(friendChooseVO);
		
		return friendChooseVO;
	}
	
	
	//刪除
	public void deleteFriendChoose(String condition_id) {
		dao.delete(condition_id);
		
	}
	
	
	//查全部
	public List<FriendChooseVO> getAll(){
		return dao.findAll();
	}
	
	
	//依語言&性別查會員
	public List<FriendChooseVO> getMember(String condition_language_id,Integer condition_sex){
		return dao.findByCondition(condition_language_id, condition_sex);
	}
	
	
	
	//查某個會員
	public MemberVO getOnePro(String condition_member_id) {
		return dao.findOne(condition_member_id);
	}
	
	//查全部會員
		public List<MemberVO> getAllMember(){
			return dao.findAllMember();
		}
		
	//修改某個會員的資料
	public void updateMemberPro(MemberVO memberVO) {
		dao.updateMember(memberVO);
	}
}
