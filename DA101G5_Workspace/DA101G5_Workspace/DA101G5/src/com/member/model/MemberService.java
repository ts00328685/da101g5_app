package com.member.model;


import java.sql.Date;
import java.util.List;

import com.member.model.MemberVO;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(String mem_name,  String mem_email,String mem_pwd, String mem_nick,String mem_sex,String mem_city,String mem_country,String mem_mobile, Date mem_birthday, Date mem_createtime,  
			   byte[] mem_pic, Integer mem_status, String friend_profile
//			, Integer mem_status , byte[] friend_pic, String friend_profile, String mem_ac,  Integer friend_choose, Integer friend_appli, 
//			Double mem_point, Double couponqty,   
			) {

		MemberVO memberVO = new MemberVO();

		
		memberVO.setMem_name(mem_name);
		
		memberVO.setMem_email(mem_email);
		memberVO.setMem_pwd(mem_pwd);
		
		
		memberVO.setMem_nick(mem_nick);
		memberVO.setMem_sex(mem_sex);
		
		
		memberVO.setMem_city(mem_city);
		memberVO.setMem_country(mem_country);
		memberVO.setMem_mobile(mem_mobile);
//		memberVO.setMem_profile(mem_profile);
//		memberVO.setFriend_pic(friend_pic);
		memberVO.setMem_status(mem_status);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_createtime(mem_createtime);
		memberVO.setMem_pic(mem_pic);
		memberVO.setFriend_profile(friend_profile);
//		memberVO.setFriend_choose(friend_choose);
//		memberVO.setFriend_appli(friend_appli);
//		memberVO.setMem_point(mem_point);
//		memberVO.setCouponqty(couponqty);
		
		
		
		String member_id = dao.insert(memberVO);
		
		memberVO.setMember_id(member_id);
		

		return memberVO;
	}

	public MemberVO updateMember(String member_id, String mem_name,String mem_pwd, String mem_nick, String mem_email, java.sql.Date mem_birthday, String mem_mobile, 
			String mem_city, String mem_country, String mem_sex, Integer mem_status,  
	  	 byte[] friend_pic, String friend_profile, Integer friend_choose, Integer friend_appli, 
		Double mem_point, Double couponqty, String mem_profile, java.sql.Date mem_createtime , byte[] mem_pic
			) {

		MemberVO memberVO = new MemberVO();
		
		memberVO.setMember_id(member_id);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_pwd(mem_pwd);
		memberVO.setMem_nick(mem_nick);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_mobile(mem_mobile);
		memberVO.setMem_city(mem_city);
		memberVO.setMem_country(mem_country);
		memberVO.setMem_sex(mem_sex);
		memberVO.setMem_status(mem_status);
		memberVO.setFriend_pic(friend_pic);
		memberVO.setFriend_profile(friend_profile);
		memberVO.setFriend_choose(friend_choose);
		memberVO.setFriend_appli(friend_appli);
		memberVO.setMem_point(mem_point);
		memberVO.setCouponqty(couponqty);
		memberVO.setMem_profile(mem_profile);
		memberVO.setMem_createtime(mem_createtime);
		memberVO.setMem_pic(mem_pic);
		
		dao.update(memberVO);

		return memberVO;
	}
	
	public MemberVO updateMember2(String member_id, String mem_name,String mem_pwd, String mem_nick, String mem_email, java.sql.Date mem_birthday, String mem_mobile, 
			String mem_city, String mem_country,  String mem_profile, byte[] mem_pic
			) {

		MemberVO memberVO = new MemberVO();
		
		memberVO.setMember_id(member_id);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_pwd(mem_pwd);
		memberVO.setMem_nick(mem_nick);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_mobile(mem_mobile);
		memberVO.setMem_city(mem_city);
		memberVO.setMem_country(mem_country);
		memberVO.setMem_profile(mem_profile);
		memberVO.setMem_pic(mem_pic);
		
		dao.update2(memberVO);
//		memberVO.setMem_sex(mem_sex);
//		memberVO.setMem_status(mem_status);
//		memberVO.setFriend_pic(friend_pic);
//		memberVO.setFriend_profile(friend_profile);
//		memberVO.setFriend_choose(friend_choose);
//		memberVO.setFriend_appli(friend_appli);
//		memberVO.setMem_point(mem_point);
//		memberVO.setMem_createtime(mem_createtime);
//		memberVO.setCouponqty(couponqty);
		
		

		return memberVO;
	}
	

	public void deleteMember(String member_id) {
		dao.delete(member_id);
	}

	public MemberVO getOneMember(String member_id) {
		return dao.findOneAllMessages(member_id);
	}
	public MemberVO getOneMem_email(String mem_email) {
		return dao.findMem_email(mem_email);
	}
	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	public List<MemberVO> getAll2(String Member_id) {
		return dao.getAllById(Member_id);
	}
	public void updateMember(MemberVO memberVO) {
		dao.update(memberVO);
	}
	
}
