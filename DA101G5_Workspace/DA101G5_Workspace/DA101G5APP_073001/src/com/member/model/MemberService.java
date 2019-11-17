package com.member.model;


import java.util.List;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(String mem_name, String mem_pwd, String mem_nick, String mem_email, java.sql.Date mem_birthday, String mem_mobile, 
			String mem_city, String mem_country, String mem_profile, byte[] friend_pic
//			, Integer mem_status ,String mem_sex, byte[] friend_pic, String friend_profile, String mem_ac,  Integer friend_choose, Integer friend_appli, 
//			Double mem_point, Double couponqty,  java.sql.Date mem_createtime 
			) {

		MemberVO memberVO = new MemberVO();

//		memberVO.setMem_ac(mem_ac);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_pwd(mem_pwd);
		memberVO.setMem_nick(mem_nick);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_mobile(mem_mobile);
		memberVO.setMem_city(mem_city);
		memberVO.setMem_country(mem_country);
		memberVO.setMem_profile(mem_profile);
		memberVO.setFriend_pic(friend_pic);
//		memberVO.setMem_status(mem_status);
//		memberVO.setMem_sex(mem_sex);
		
//		
//		memberVO.setFriend_profile(friend_profile);
//		memberVO.setFriend_choose(friend_choose);
//		memberVO.setFriend_appli(friend_appli);
//		memberVO.setMem_point(mem_point);
//		memberVO.setCouponqty(couponqty);
		
//		memberVO.setMem_createtime(mem_createtime);
		dao.insert(memberVO);

		return memberVO;
	}

	public MemberVO updateMember(String member_id, String mem_name, String mem_pwd, String mem_nick, String mem_email, java.sql.Date mem_birthday, String mem_mobile, 
			String mem_city, String mem_country, String mem_profile, byte[] friend_pic
//			String mem_sex, byte[] friend_pic, String friend_profile, String mem_ac,  Integer friend_choose, Integer friend_appli, 
//			Double mem_point, Double couponqty,  java.sql.Date mem_createtime , Integer mem_status
			) {

		MemberVO memberVO = new MemberVO();
		
		memberVO.setMember_id(member_id);
//		memberVO.setMem_ac(mem_ac);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_pwd(mem_pwd);
		memberVO.setMem_nick(mem_nick);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_mobile(mem_mobile);
		memberVO.setMem_city(mem_city);
		memberVO.setMem_country(mem_country);
		memberVO.setMem_profile(mem_profile);
		memberVO.setFriend_pic(friend_pic);
//		memberVO.setMem_status(mem_status);
//		memberVO.setMem_sex(mem_sex);
//		
//		
//		memberVO.setFriend_profile(friend_profile);
//		memberVO.setFriend_choose(friend_choose);
//		memberVO.setFriend_appli(friend_appli);
//		memberVO.setMem_point(mem_point);
//		memberVO.setCouponqty(couponqty);
		
//		memberVO.setMem_createtime(mem_createtime);
		dao.update(memberVO);

		return memberVO;
	}

	public void deleteMember(String member_id) {
		dao.delete(member_id);
	}

	public MemberVO getOneMember(String member_id) {
		return dao.findByPrimaryKey(member_id);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
}
