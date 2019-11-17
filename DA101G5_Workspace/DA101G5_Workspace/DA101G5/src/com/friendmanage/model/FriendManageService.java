package com.friendmanage.model;

import java.sql.Date;
import java.util.List;

import com.member.model.MemberVO;

public class FriendManageService {
	private FriendManageDAO_interface dao;
	
	public FriendManageService() {
		dao = new FriendManageDAO();
	}
	//新增
	public FriendManageVO addFriendManage(String friend_member_id, String friend_member_fid, Date friend_time) {
		FriendManageVO friendManageVO = new FriendManageVO();		
		friendManageVO.setFriend_member_id(friend_member_id);
		friendManageVO.setFriend_member_fid(friend_member_fid);
		friendManageVO.setFriend_time(friend_time);
		dao.insert(friendManageVO);
		return friendManageVO;		
	}
	//修改
	public FriendManageVO updateFriendManage(String friend_member_id, String friend_member_fid, Date friend_time, Integer friend_status) {
		FriendManageVO friendManageVO = new FriendManageVO();
		
		friendManageVO.setFriend_member_id(friend_member_id);
		friendManageVO.setFriend_member_fid(friend_member_fid);
		friendManageVO.setFriend_time(friend_time);
		friendManageVO.setFriend_status(friend_status);
		dao.update(friendManageVO);
		return friendManageVO;	
	}
	//刪除
	public void deleteFriendManage(String friend_member_id, String friend_member_fid) {
		dao.delete(friend_member_id, friend_member_fid);
	}
	
	//查全部
	public List<FriendManageVO> getAll(){
		return dao.findAll();
	}
	//查某個會員的學伴
	public List<FriendManageVO> getOneAllFriends(String friend_member_id){
		return dao.findOneAllFriends(friend_member_id);
	}
	
	//查某個會員
	public MemberVO getOnePro(String condition_member_id) {
		return dao.findOne(condition_member_id);
	}
		
	//查全部會員
	public List<MemberVO> getAllMember(){
		return dao.findAllMember();
	}
}
