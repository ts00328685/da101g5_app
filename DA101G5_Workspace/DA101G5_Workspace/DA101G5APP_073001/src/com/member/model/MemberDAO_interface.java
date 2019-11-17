package com.member.model;

import java.sql.Connection;
import java.util.*;

public interface MemberDAO_interface {
		public void insert(MemberVO memberVO);
		public void update(MemberVO memberVO);
		public void updatePic(String member_id, byte[] mem_pic);
		public void updateFriendPic(String member_id, byte[] friend_pic);
		public void updatePoint(MemberVO memberVO, Connection con);
		public void delete(String member_id);
		public MemberVO findByPrimaryKey(String member_id);
		public List<MemberVO> getAll();
}
