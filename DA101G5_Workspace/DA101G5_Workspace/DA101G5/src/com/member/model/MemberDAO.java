package com.member.model;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;





public class MemberDAO implements MemberDAO_interface {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

	    private static final String INSERT_STMT = 
	    		"INSERT INTO member (member_id, mem_name, mem_ac, mem_email, mem_pwd, mem_nick, mem_sex, mem_city, mem_country,  mem_mobile, mem_birthday,mem_createtime, mem_pic, mem_status,mem_profile ) VALUES ('M'||LPAD(to_char(MEM_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
	    private static final String GET_ALL_STMT = 
	    		"SELECT member_id,mem_ac,mem_name,mem_pwd,mem_nick,mem_email,to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,mem_mobile,mem_city,mem_country,mem_sex,mem_status,friend_pic,friend_profile,friend_choose,friend_appli,mem_point,couponqty,mem_profile,to_char(mem_createtime,'yyyy-mm-dd') mem_createtime, mem_pic FROM member order by member_id";
		private static final String GET_ONE_STMT = 
			"SELECT member_id,mem_ac,mem_name,mem_pwd,mem_nick,mem_email,to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,mem_mobile,mem_city,mem_country,mem_sex,mem_status,friend_pic,friend_profile,friend_choose,friend_appli,mem_point,couponqty,mem_profile,to_char(mem_createtime,'yyyy-mm-dd') mem_createtime, mem_pic FROM member where member_id = ?";
		private static final String DELETE = 
			"DELETE FROM member where member_id = ?";
		private static final String UPDATE = 
			"UPDATE member set mem_name=?, mem_pwd=?, mem_nick=?, mem_email=?, mem_birthday=?, mem_mobile=?, mem_city=?, mem_country=?, mem_sex=?, mem_status=?, friend_pic=?, friend_profile=?, friend_choose=?, friend_appli=?, mem_point=?, couponqty=?, mem_profile=?, mem_createtime=?, mem_pic=? where member_id = ?";
		private static final String UPDATE2 = 
			"UPDATE member set mem_name=?, mem_pwd=?, mem_nick=?, mem_email=?, mem_birthday=?, mem_mobile=?, mem_city=?, mem_country=?, mem_profile=?, mem_pic=? where member_id = ?";
		private static final String GET_EMAIL = 
			"SELECT * FROM member where mem_email = ?";
		private static final String GET_ALL_ONEID = 
			"SELECT member_id,mem_ac,mem_name,mem_pwd,mem_nick,mem_email,to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,mem_mobile,mem_city,mem_country,mem_sex,mem_status,friend_pic,friend_profile,friend_choose,friend_appli,mem_point,couponqty,mem_profile,to_char(mem_createtime,'yyyy-mm-dd') mem_createtime, mem_pic FROM member where member_id=? order by member_id";
		
		
	@Override
	public String insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String member_id = null;
		try {

			con = ds.getConnection();
			
			
			
			String cols[] = {"member_id"};
			//掘取對應的自增主鍵值
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			
			pstmt.setString(1, memberVO.getMem_name());
			pstmt.setString(2, memberVO.getMem_ac());
			pstmt.setString(3, memberVO.getMem_email());
			pstmt.setString(4, memberVO.getMem_pwd());
			
			pstmt.setString(5, memberVO.getMem_nick());
			pstmt.setString(6, memberVO.getMem_sex());
			pstmt.setString(7, memberVO.getMem_city());
			pstmt.setString(8, memberVO.getMem_country());
			pstmt.setString(9, memberVO.getMem_mobile());
			pstmt.setDate(10, memberVO.getMem_birthday());
			pstmt.setDate(11, memberVO.getMem_createtime());
			pstmt.setBytes(12, memberVO.getMem_pic());
			
//			pstmt.setString(9, memberVO.getMem_profile());
//			pstmt.setBytes(10, memberVO.getFriend_pic());
			pstmt.setInt(13, memberVO.getMem_status());
			pstmt.setString(14, memberVO.getFriend_profile());
			
//			
//			pstmt.setInt(14, memberVO.getFriend_choose());
//			pstmt.setInt(15, memberVO.getFriend_appli());
//			pstmt.setDouble(16, memberVO.getMem_point());
//			pstmt.setDouble(17, memberVO.getCouponqty());
			

			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				member_id = rs.getString(1);
				System.out.println("自增主鍵值= " + member_id +"(剛新增成功的會員編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			
			
			

			// Handle any SQL errors
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
			// Clean up JDBC resources
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return member_id;

	}

	@Override
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, memberVO.getMem_name());
			pstmt.setString(2, memberVO.getMem_pwd());
			pstmt.setString(3, memberVO.getMem_nick());
			pstmt.setString(4, memberVO.getMem_email());
			pstmt.setDate(5, memberVO.getMem_birthday());
			pstmt.setString(6, memberVO.getMem_mobile());
			pstmt.setString(7, memberVO.getMem_city());
			pstmt.setString(8, memberVO.getMem_country());
			pstmt.setString(9, memberVO.getMem_sex());
			pstmt.setInt(10, memberVO.getMem_status());
			pstmt.setBytes(11, memberVO.getFriend_pic());
			pstmt.setString(12, memberVO.getFriend_profile());
			pstmt.setInt(13, memberVO.getFriend_choose());
			pstmt.setInt(14, memberVO.getFriend_appli());
			pstmt.setDouble(15, memberVO.getMem_point());
			pstmt.setDouble(16, memberVO.getCouponqty());
			pstmt.setString(17, memberVO.getMem_profile());
			pstmt.setDate(18, memberVO.getMem_createtime());
			pstmt.setBytes(19, memberVO.getMem_pic());
			pstmt.setString(20, memberVO.getMember_id());
//			pstmt.setString(1, memberVO.getMem_ac());
			
			pstmt.executeUpdate();
			
			
			// Handle any driver errors
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
			// Clean up JDBC resources
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update2(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE2);
			pstmt.setString(1, memberVO.getMem_name());
			pstmt.setString(2, memberVO.getMem_pwd());
			pstmt.setString(3, memberVO.getMem_nick());
			pstmt.setString(4, memberVO.getMem_email());
			pstmt.setDate(5, memberVO.getMem_birthday());
			pstmt.setString(6, memberVO.getMem_mobile());
			pstmt.setString(7, memberVO.getMem_city());
			pstmt.setString(8, memberVO.getMem_country());
			pstmt.setString(9, memberVO.getMem_profile());
			pstmt.setBytes(10, memberVO.getMem_pic());
			pstmt.setString(11, memberVO.getMember_id());

//			pstmt.setString(9, memberVO.getMem_sex());
//			pstmt.setInt(10, memberVO.getMem_status());
//			pstmt.setBytes(11, memberVO.getFriend_pic());
//			pstmt.setString(12, memberVO.getFriend_profile());
//			pstmt.setInt(13, memberVO.getFriend_choose());
//			pstmt.setInt(14, memberVO.getFriend_appli());
//			pstmt.setDouble(15, memberVO.getMem_point());
//			pstmt.setDouble(16, memberVO.getCouponqty());	
//			pstmt.setDate(18, memberVO.getMem_createtime());
//			pstmt.setString(1, memberVO.getMem_ac());
			
			pstmt.executeUpdate();
			
			
			// Handle any driver errors
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
			// Clean up JDBC resources
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void delete(String member_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, member_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured."+se.getMessage());
			// Clean up JDBC resources
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public MemberVO findOneAllMessages(String member_id) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVo 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getString("member_id"));
				memberVO.setMem_ac(rs.getString("mem_ac"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_pwd(rs.getString("mem_pwd"));
				memberVO.setMem_nick(rs.getString("mem_nick"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_mobile(rs.getString("mem_mobile"));
				memberVO.setMem_city(rs.getString("mem_city"));
				memberVO.setMem_country(rs.getString("mem_country"));
				memberVO.setMem_sex(rs.getString("mem_sex"));
				memberVO.setMem_status(rs.getInt("mem_status"));
				memberVO.setFriend_pic(rs.getBytes("friend_pic"));
				memberVO.setFriend_profile(rs.getString("friend_profile"));
				memberVO.setFriend_choose(rs.getInt("friend_choose"));
				memberVO.setFriend_appli(rs.getInt("friend_appli"));
				memberVO.setMem_point(rs.getDouble("mem_point"));
				memberVO.setCouponqty(rs.getDouble("couponqty"));
				memberVO.setMem_profile(rs.getString("mem_profile"));
				memberVO.setMem_createtime(rs.getDate("mem_createtime"));
				memberVO.setMem_pic(rs.getBytes("mem_pic"));
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memberVO;
	}

	@Override
	public MemberVO findMem_email(String mem_email) {
		
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_EMAIL);

			pstmt.setString(1, mem_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// MemberVO Domain objects
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getString("member_id"));
				memberVO.setMem_ac(rs.getString("mem_ac"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_pwd(rs.getString("mem_pwd"));
				memberVO.setMem_nick(rs.getString("mem_nick"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_mobile(rs.getString("mem_mobile"));
				memberVO.setMem_city(rs.getString("mem_city"));
				memberVO.setMem_country(rs.getString("mem_country"));
				memberVO.setMem_sex(rs.getString("mem_sex"));
				memberVO.setMem_status(rs.getInt("mem_status"));
				memberVO.setFriend_pic(rs.getBytes("friend_pic"));
				memberVO.setFriend_profile(rs.getString("friend_profile"));
				memberVO.setFriend_choose(rs.getInt("friend_choose"));
				memberVO.setFriend_appli(rs.getInt("friend_appli"));
				memberVO.setMem_point(rs.getDouble("mem_point"));
				memberVO.setCouponqty(rs.getDouble("couponqty"));
				memberVO.setMem_profile(rs.getString("mem_profile"));
				memberVO.setMem_createtime(rs.getDate("mem_createtime"));
				memberVO.setMem_pic(rs.getBytes("mem_pic"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memberVO;
	}
	
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVo 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getString("member_id"));
				memberVO.setMem_ac(rs.getString("mem_ac"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_pwd(rs.getString("mem_pwd"));
				memberVO.setMem_nick(rs.getString("mem_nick"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_mobile(rs.getString("mem_mobile"));
				memberVO.setMem_city(rs.getString("mem_city"));
				memberVO.setMem_country(rs.getString("mem_country"));
				memberVO.setMem_sex(rs.getString("mem_sex"));
				memberVO.setMem_status(rs.getInt("mem_status"));
				memberVO.setFriend_pic(rs.getBytes("friend_pic"));
				memberVO.setFriend_profile(rs.getString("friend_profile"));
				memberVO.setFriend_choose(rs.getInt("friend_choose"));
				memberVO.setFriend_appli(rs.getInt("friend_appli"));
				memberVO.setMem_point(rs.getDouble("mem_point"));
				memberVO.setCouponqty(rs.getDouble("couponqty"));
				memberVO.setMem_profile(rs.getString("mem_profile"));
				memberVO.setMem_createtime(rs.getDate("mem_createtime"));
				memberVO.setMem_pic(rs.getBytes("mem_pic"));
				list.add(memberVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
//	@Override
//	public void insertWithMem(MemberVO memberVO , List<MemberVO> list) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			
//			
//			// 1●設定於 pstm.executeUpdate()之前
//    		con.setAutoCommit(false);
//			
//    		// 先新增部門
//			String cols[] = {"Member_id"};
//			pstmt = con.prepareStatement(INSERT_STMT , cols);			
//			pstmt.setString(1, memberVO.getMember_id());
//			
//			pstmt.executeUpdate();
//			//掘取對應的自增主鍵值
//			String next_member_id = null;
//			ResultSet rs = pstmt.getGeneratedKeys();
//			if (rs.next()) {
//				next_member_id = rs.getString(1);
//				System.out.println("自增主鍵值= " + next_member_id +"(剛新增成功的部門編號)");
//			} else {
//				System.out.println("未取得自增主鍵值");
//			}
//			rs.close();
			
	
	@Override
	public List<MemberVO> getAllById(String Member_id) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ONEID);
			pstmt.setString(1, Member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVo 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMember_id(rs.getString("member_id"));
				memberVO.setMem_ac(rs.getString("mem_ac"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_pwd(rs.getString("mem_pwd"));
				memberVO.setMem_nick(rs.getString("mem_nick"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_mobile(rs.getString("mem_mobile"));
				memberVO.setMem_city(rs.getString("mem_city"));
				memberVO.setMem_country(rs.getString("mem_country"));
				memberVO.setMem_sex(rs.getString("mem_sex"));
				memberVO.setMem_status(rs.getInt("mem_status"));
				memberVO.setFriend_pic(rs.getBytes("friend_pic"));
				memberVO.setFriend_profile(rs.getString("friend_profile"));
				memberVO.setFriend_choose(rs.getInt("friend_choose"));
				memberVO.setFriend_appli(rs.getInt("friend_appli"));
				memberVO.setMem_point(rs.getDouble("mem_point"));
				memberVO.setCouponqty(rs.getDouble("couponqty"));
				memberVO.setMem_profile(rs.getString("mem_profile"));
				memberVO.setMem_createtime(rs.getDate("mem_createtime"));
				memberVO.setMem_pic(rs.getBytes("mem_pic"));
				list.add(memberVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
