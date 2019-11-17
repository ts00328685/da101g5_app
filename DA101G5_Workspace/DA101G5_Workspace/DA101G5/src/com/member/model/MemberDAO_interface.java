package com.member.model;

import java.util.*;


import com.member.model.MemberVO;

public interface MemberDAO_interface {
		public String insert(MemberVO memberVO);
		public void update(MemberVO memberVO);
		public void update2(MemberVO memberVO);
		public void delete(String member_id);
//		public void updateMember(MemberVO memberVO);
		public MemberVO findOneAllMessages(String member_id);
		public MemberVO findMem_email(String mem_email);
		public List<MemberVO> getAll();
		public List<MemberVO> getAllById(String member_id);
		
//		public void insertWithMem(MemberVO memberVO , List<MemberVO> list);
}
