//package com.member.model;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.*;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//public class MemberJNDIDAO implements MemberDAO_interface {
//	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
//			private static DataSource ds = null;
//			static {
//				try {
//					Context ctx = new InitialContext();
//					ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//				}catch (NamingException e) {
//					e.printStackTrace();
//				}
//			}
//			
//			private static final String INSERT_STMT = 
//					"INSERT INTO member (member_id,mem_ac,mem_name,mem_pwd,mem_nick,mem_email,mem_birthday,mem_mobile,mem_city,mem_country,mem_sex,mem_status,friend_profile,friend_choose,friend_appli,mem_point,couponqty,mem_profile,mem_createtime) VALUES (MEM_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,)";
//			private static final String GET_ALL_STMT = 
//					"SELECT member_id,mem_ac,mem_name,mem_pwd,mem_nick,mem_email,to_char(mem_birthday,'yyyy-mm-dd')mem_birthday,mem_mobile,mem_city,mem_country,mem_sex,mem_status,friend_profile,friend_choose,friend_appli,mem_point,couponqty,mem_profile,to_char(mem_createtime,'yyyy-mm-dd')mem_creattime FROM member order by member_id";
//			private static final String GET_ONE_STMT = 
//					"SELECT member_id,mem_ac,mem_name,mem_pwd,mem_nick,mem_email,to_char(mem_birthday,'yyyy-mm-dd')mem_birthday,mem_mobile,mem_city,mem_country,mem_sex,mem_status,friend_profile,friend_choose,friend_appli,mem_point,couponqty,mem_profile,to_char(mem_createtime,'yyyy-mm-dd')mem_creattime FROM member where member_id = ?";
//			private static final String DELETE = 
//					"DELETE FROM member where member_id = ?";
//			private static final String UPDATE = 
//					"UPDATE member set mem_ac=?, mem_name=? ,mem_pwd=? ,mem_nick=? ,mem_email=? ,mem_birthday=? ,mem_mobile=? ,mem_city=? ,mem_country=? ,mem_sex=? ,mem_status=? ,friend_profile=? ,friend_choose=? ,friend_appli=? ,mem_point=? ,couponqty=? ,mem_profile=? ,mem_createtime=? where member_id = ?";
//
//	@Override
//	public void insert(MemberVO memberVO) {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, memberVO.getMem_ac());
//			pstmt.setString(2, memberVO.getMem_name());
//			pstmt.setString(3, memberVO.getMem_pwd());
//			pstmt.setString(4, memberVO.getMem_nick());
//			pstmt.setString(5, memberVO.getMem_email());
//			pstmt.setDate(6, memberVO.getMem_birthday());
//			pstmt.setString(7, memberVO.getMem_mobile());
//			pstmt.setString(8, memberVO.getMem_city());
//			pstmt.setString(9, memberVO.getMem_country());
//			pstmt.setString(10, memberVO.getMem_sex());
//			pstmt.setInt(11, memberVO.getMem_status());
//			pstmt.setByte(12, memberVO.getFriend_pic());
//			pstmt.setString(13, memberVO.getFriend_profile());
//			pstmt.setInt(14, memberVO.getFriend_choose());
//			pstmt.setInt(15, memberVO.getFriend_appli());
//			pstmt.setDouble(16, memberVO.getMem_point());
//			pstmt.setDouble(17, memberVO.getCouponqty());
//			pstmt.setString(18, memberVO.getMem_profile());
//			pstmt.setDate(19, memberVO.getMem_createtime());
//
//			pstmt.executeUpdate();
//
//			// Handle any SQL errors
//		}catch(SQLException se) {
//			throw new RuntimeException("A database error occured."+se.getMessage());
//			// Clean up JDBC resources
//		}finally {
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				}catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(con != null) {
//				try {
//					con.close();
//				}catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	@Override
//	public void update(MemberVO memberVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, memberVO.getMem_ac());
//			pstmt.setString(2, memberVO.getMem_name());
//			pstmt.setString(3, memberVO.getMem_pwd());
//			pstmt.setString(4, memberVO.getMem_nick());
//			pstmt.setString(5, memberVO.getMem_email());
//			pstmt.setDate(6, memberVO.getMem_birthday());
//			pstmt.setString(7, memberVO.getMem_mobile());
//			pstmt.setString(8, memberVO.getMem_city());
//			pstmt.setString(9, memberVO.getMem_country());
//			pstmt.setString(10, memberVO.getMem_sex());
//			pstmt.setInt(11, memberVO.getMem_status());
//			pstmt.setByte(12, memberVO.getFriend_pic());
//			pstmt.setString(13, memberVO.getFriend_profile());
//			pstmt.setInt(14, memberVO.getFriend_choose());
//			pstmt.setInt(15, memberVO.getFriend_appli());
//			pstmt.setDouble(16, memberVO.getMem_point());
//			pstmt.setDouble(17, memberVO.getCouponqty());
//			pstmt.setString(18, memberVO.getMem_profile());
//			pstmt.setDate(19, memberVO.getMem_createtime());
//			pstmt.setString(20, memberVO.getMember_id());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		}catch(SQLException se) {
//			throw new RuntimeException("A database error occured."+se.getMessage());
//			// Clean up JDBC resources
//		}finally {
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				}catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(con != null) {
//				try {
//					con.close();
//				}catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void delete(String member_id) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, member_id);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		}catch(SQLException se) {
//			throw new RuntimeException("A database error occured."+se.getMessage());
//			// Clean up JDBC resources
//		}finally {
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				}catch(SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if(con != null) {
//				try {
//					con.close();
//				}catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public MemberVO findByPrimaryKey(String member_id) {
//
//		MemberVO memberVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, member_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// memberVo 也稱為 Domain objects
//				memberVO = new MemberVO();
//				memberVO.setMember_id(rs.getString("member_id"));
//				memberVO.setMem_ac(rs.getString("mem_ac"));
//				memberVO.setMem_name(rs.getString("mem_name"));
//				memberVO.setMem_pwd(rs.getString("mem_pwd"));
//				memberVO.setMem_nick(rs.getString("mem_nick"));
//				memberVO.setMem_email(rs.getString("mem_email"));
//				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
//				memberVO.setMem_mobile(rs.getString("mem_mobile"));
//				memberVO.setMem_city(rs.getString("mem_city"));
//				memberVO.setMem_country(rs.getString("mem_country"));
//				memberVO.setMem_sex(rs.getString("mem_sex"));
//				memberVO.setMem_status(rs.getInt("mem_status"));
//				memberVO.setFriend_pic(rs.getByte("friend_pic"));
//				memberVO.setFriend_profile(rs.getString("friend_profile"));
//				memberVO.setFriend_choose(rs.getInt("friend_choose"));
//				memberVO.setFriend_appli(rs.getInt("friend_appli"));
//				memberVO.setMem_point(rs.getDouble("mem_point"));
//				memberVO.setCouponqty(rs.getDouble("couponqty"));
//				memberVO.setMem_profile(rs.getString("mem_profile"));
//				memberVO.setMem_createtime(rs.getDate("mem_createtime"));	
//			}
//			
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return memberVO;
//	}
//
//	@Override
//	public List<MemberVO> getAll() {
//		List<MemberVO> list = new ArrayList<MemberVO>();
//		MemberVO memberVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// memberVo 也稱為 Domain objects
//				memberVO = new MemberVO();
//				memberVO.setMember_id(rs.getString("member_id"));
//				memberVO.setMem_ac(rs.getString("mem_ac"));
//				memberVO.setMem_name(rs.getString("mem_name"));
//				memberVO.setMem_pwd(rs.getString("mem_pwd"));
//				memberVO.setMem_nick(rs.getString("mem_nick"));
//				memberVO.setMem_email(rs.getString("mem_email"));
//				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
//				memberVO.setMem_mobile(rs.getString("mem_mobile"));
//				memberVO.setMem_city(rs.getString("mem_city"));
//				memberVO.setMem_country(rs.getString("mem_country"));
//				memberVO.setMem_sex(rs.getString("mem_sex"));
//				memberVO.setMem_status(rs.getInt("mem_status"));
//				memberVO.setFriend_pic(rs.getByte("friend_pic"));
//				memberVO.setFriend_profile(rs.getString("friend_profile"));
//				memberVO.setFriend_choose(rs.getInt("friend_choose"));
//				memberVO.setFriend_appli(rs.getInt("friend_appli"));
//				memberVO.setMem_point(rs.getDouble("mem_point"));
//				memberVO.setCouponqty(rs.getDouble("couponqty"));
//				memberVO.setMem_profile(rs.getString("mem_profile"));
//				memberVO.setMem_createtime(rs.getDate("mem_createtime"));	
//				list.add(memberVO); // Store the row in the list
//			}
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
//
//}
