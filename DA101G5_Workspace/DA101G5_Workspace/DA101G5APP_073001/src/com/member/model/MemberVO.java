package com.member.model;
import java.sql.Date;

public class MemberVO implements java.io.Serializable{

	private String member_id;
	private String mem_ac;
	private String mem_name;
	private String mem_pwd;
	private String mem_nick;
	private String mem_email;
	private Date mem_birthday;
	private String mem_mobile;
	private String mem_city;
	private String mem_country;
	private String mem_sex;
	private Integer mem_status;
	private byte[] friend_pic;
	private String friend_profile;
	private Integer friend_choose;
	private Integer friend_appli;
	private Double mem_point;
	private Double couponqty;
	private String mem_profile;
	private Date mem_createtime;
	private byte[] mem_pic;
	
	public byte[] getMem_pic() {
		return mem_pic;
	}
	public void setMem_pic(byte[] mem_pic) {
		this.mem_pic = mem_pic;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMem_ac() {
		return mem_ac;
	}
	public void setMem_ac(String mem_ac) {
		this.mem_ac = mem_ac;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_pwd() {
		return mem_pwd;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public Date getMem_birthday() {
		return mem_birthday;
	}
	public void setMem_birthday(Date mem_birthday) {
		this.mem_birthday = mem_birthday;
	}
	public String getMem_mobile() {
		return mem_mobile;
	}
	public void setMem_mobile(String mem_mobile) {
		this.mem_mobile = mem_mobile;
	}
	public String getMem_city() {
		return mem_city;
	}
	public void setMem_city(String mem_city) {
		this.mem_city = mem_city;
	}
	public String getMem_country() {
		return mem_country;
	}
	public void setMem_country(String mem_country) {
		this.mem_country = mem_country;
	}
	public String getMem_sex() {
		return mem_sex;
	}
	public void setMem_sex(String mem_sex) {
		this.mem_sex = mem_sex;
	}
	public Integer getMem_status() {
		return mem_status;
	}
	public void setMem_status(Integer mem_status) {
		this.mem_status = mem_status;
	}
	public byte[] getFriend_pic() {
		return friend_pic;
	}
	public void setFriend_pic(byte[] friend_pic) {
		this.friend_pic = friend_pic;
	}
	public String getFriend_profile() {
		return friend_profile;
	}
	public void setFriend_profile(String friend_profile) {
		this.friend_profile = friend_profile;
	}
	public Integer getFriend_choose() {
		return friend_choose;
	}
	public void setFriend_choose(Integer friend_choose) {
		this.friend_choose = friend_choose;
	}
	public Integer getFriend_appli() {
		return friend_appli;
	}
	public void setFriend_appli(Integer friend_appli) {
		this.friend_appli = friend_appli;
	}
	public Double getMem_point() {
		return mem_point;
	}
	public void setMem_point(Double mem_point) {
		this.mem_point = mem_point;
	}
	public Double getCouponqty() {
		return couponqty;
	}
	public void setCouponqty(Double couponqty) {
		this.couponqty = couponqty;
	}
	public String getMem_profile() {
		return mem_profile;
	}
	public void setMem_profile(String mem_profile) {
		this.mem_profile = mem_profile;
	}
	public Date getMem_createtime() {
		return mem_createtime;
	}
	public void setMem_createtime(Date mem_createtime) {
		this.mem_createtime = mem_createtime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member_id == null) ? 0 : member_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (member_id == null) {
			if (other.member_id != null)
				return false;
		} else if (!member_id.equals(other.member_id))
			return false;
		return true;
	}
	
	
}
