package com.friendmanage.model;

import java.util.List;
import java.util.Set;

import com.friendmanage.model.FriendManageVO;
import com.member.model.MemberVO;

public interface FriendManageDAO_interface {
	//新增
	public void insert(FriendManageVO friendManageVO);
	
	//修改
	public void update(FriendManageVO friendManageVO);	
	
	//刪除
	public void delete(String friend_member_id,String friend_member_fid);
	
	//查全部
	public List<FriendManageVO> findAll();	
	
	//查某個會員的學伴
	public List<FriendManageVO> findOneAllFriends(String friend_member_id);
	
	
	//查某個會員
	public MemberVO findOne(String condition_member_id);
		
	//查全部會員
	public List<MemberVO> findAllMember();
	
}
