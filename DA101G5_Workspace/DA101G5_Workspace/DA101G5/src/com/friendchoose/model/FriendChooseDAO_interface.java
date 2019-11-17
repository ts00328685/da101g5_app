package com.friendchoose.model;

import java.util.List;

import com.member.model.MemberVO;

public interface FriendChooseDAO_interface {
	
	//新增
	public void insert(FriendChooseVO friendChooseVO);
	
	//修改
	public void update(FriendChooseVO friendChooseVO);	
	
	//刪除
	public void delete(String condition_id);
	
	//查全部
	public List<FriendChooseVO> findAll();	
	
	//依語言&性別查會員
	public List<FriendChooseVO> findByCondition(String condition_language_id, Integer condition_sex);
	
	//查某個會員
	public MemberVO findOne(String condition_member_id);
	
	//查全部會員
	public List<MemberVO> findAllMember();
	
	//修改某個會員的資料
	public void updateMember(MemberVO memberVO);	

}
