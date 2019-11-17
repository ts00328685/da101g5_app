package com.member.model;

import java.util.*;
import java.sql.*;

public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";

	

	private static final String INSERT_STMT = 
			"INSERT INTO member (member_id, mem_name, mem_pwd, mem_nick, mem_email, mem_birthday, mem_mobile, mem_city, mem_country, mem_profile, friend_pic) VALUES ('M'||LPAD(to_char(MEM_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT member_id,mem_ac,mem_name,mem_pwd,mem_nick,mem_email,to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,mem_mobile,mem_city,mem_country,mem_sex,mem_status,friend_pic,friend_profile,friend_choose,friend_appli,mem_point,couponqty,mem_profile,to_char(mem_createtime,'yyyy-mm-dd') mem_createtime FROM member order by member_id";
		private static final String GET_ONE_STMT = 
			"SELECT member_id,mem_ac,mem_name,mem_pwd,mem_nick,mem_email,to_char(mem_birthday,'yyyy-mm-dd') mem_birthday,mem_mobile,mem_city,mem_country,mem_sex,mem_status,friend_pic,friend_profile,friend_choose,friend_appli,mem_point,couponqty,mem_profile,to_char(mem_createtime,'yyyy-mm-dd') mem_createtime FROM member where member_id = ?";
		private static final String DELETE = 
			"DELETE FROM member where member_id = ?";
		private static final String UPDATE = 
			"UPDATE member set mem_name=?, mem_pwd=?, mem_nick=?, mem_email=?, mem_birthday=?, mem_mobile=?, mem_city=?, mem_country=?, mem_profile=?, friend_pic=? where member_id = ?";
		private static final String UPDATE_FRIEND_PIC = 
				"UPDATE member set friend_pic=? where member_id = ?";
		private static final String UPDATE_PIC = 
				"UPDATE member set mem_pic=? where member_id = ?";
		
		@Override
		public void updateFriendPic(String member_id, byte[] friend_pic) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				try {
					Class.forName(driver);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE_FRIEND_PIC);
				pstmt.setBytes(1,friend_pic);
				pstmt.setString(2, member_id);
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
		public void updatePic(String member_id, byte[] mem_pic) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {


			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt = con.prepareStatement(UPDATE_PIC);
			pstmt.setBytes(1,mem_pic);
			pstmt.setString(2, member_id);
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
		public void updatePoint(MemberVO memberVO, Connection con) {
			// TODO Auto-generated method stub
			
		}

	@Override
	public void insert(MemberVO memberVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

		
			pstmt.setString(1, memberVO.getMem_name());
			pstmt.setString(2, memberVO.getMem_pwd());
			pstmt.setString(3, memberVO.getMem_nick());
			pstmt.setString(4, memberVO.getMem_email());
			pstmt.setDate(5, memberVO.getMem_birthday());
			pstmt.setString(6, memberVO.getMem_mobile());
			pstmt.setString(7, memberVO.getMem_city());
			pstmt.setString(8, memberVO.getMem_country());
			pstmt.setString(9, memberVO.getMem_profile());
			pstmt.setBytes(10, memberVO.getFriend_pic());
//			pstmt.setInt(10, memberVO.getMem_status());
//			pstmt.setString(1, memberVO.getMem_sex());
//			pstmt.setString(1, memberVO.getMem_ac());
//			
//			pstmt.setString(13, memberVO.getFriend_profile());
//			pstmt.setInt(14, memberVO.getFriend_choose());
//			pstmt.setInt(15, memberVO.getFriend_appli());
//			pstmt.setDouble(16, memberVO.getMem_point());
//			pstmt.setDouble(17, memberVO.getCouponqty());
			
//			pstmt.setDate(19, memberVO.getMem_createtime());

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."+e.getMessage());
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

	}

	@Override
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

//			pstmt.setString(1, memberVO.getMem_ac());
			pstmt.setString(1, memberVO.getMem_name());
			pstmt.setString(2, memberVO.getMem_pwd());
			pstmt.setString(3, memberVO.getMem_nick());
			pstmt.setString(4, memberVO.getMem_email());
			pstmt.setDate(5, memberVO.getMem_birthday());
			pstmt.setString(6, memberVO.getMem_mobile());
			pstmt.setString(7, memberVO.getMem_city());
			pstmt.setString(8, memberVO.getMem_country());
			pstmt.setString(9, memberVO.getMem_profile());
			pstmt.setBytes(10, memberVO.getFriend_pic());
			pstmt.setString(11, memberVO.getMember_id());
			
//			pstmt.setString(9, memberVO.getMem_sex());
//			pstmt.setInt(10, memberVO.getMem_status());			
		
//			pstmt.setString(13, memberVO.getFriend_profile());
//			pstmt.setInt(14, memberVO.getFriend_choose());
//			pstmt.setInt(15, memberVO.getFriend_appli());
//			pstmt.setDouble(16, memberVO.getMem_point());
//			pstmt.setDouble(17, memberVO.getCouponqty());
			
//			pstmt.setDate(19, memberVO.getMem_createtime());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."+e.getMessage());
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
	}

	@Override
	public void delete(String member_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);


			pstmt.setString(1, member_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."+e.getMessage());
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
	}

	@Override
	public MemberVO findByPrimaryKey(String member_id) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			}
			// Handle any driver errors
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
				list.add(memberVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {
		
		MemberJDBCDAO dao = new MemberJDBCDAO();
		
		//新增
		MemberVO memberVO1 = new MemberVO();
		memberVO1.setMem_ac("III001");
		memberVO1.setMem_name("林立承");
		memberVO1.setMem_pwd("000000");
		memberVO1.setMem_nick("小林");
		memberVO1.setMem_email("LinWeiZheng@teleworm.us");
		memberVO1.setMem_birthday(java.sql.Date.valueOf("1937-03-09"));
		memberVO1.setMem_mobile("0917593897");
		memberVO1.setMem_city("台北市");
		memberVO1.setMem_country("台灣");
		memberVO1.setMem_profile("活潑開朗");
		
//		memberVO1.setFriend_pic(null);
//		memberVO1.setMem_status(0);
//		memberVO1.setMem_sex("男");
		
//		memberVO1.setFriend_pic(null);
//		memberVO1.setFriend_profile(null);
//		memberVO1.setFriend_choose(null);
//		memberVO1.setFriend_appli(null);
//		memberVO1.setMem_point(new Double(2000));
//		memberVO1.setCouponqty(new Double(1));
//		
//		memberVO1.setMem_createtime(java.sql.Date.valueOf("2019-06-14"));
		dao.insert(memberVO1);
		
		// 修改
		MemberVO memberVO2 = new MemberVO();
		memberVO2.setMember_id("M00002");
	
		memberVO2.setMem_name("方佩蓉");
		memberVO2.setMem_pwd("000000");
		memberVO2.setMem_nick("小方");
		memberVO2.setMem_email("WangPeiRong@dayrep.com");
		memberVO2.setMem_birthday(java.sql.Date.valueOf("1951-11-03"));
		memberVO2.setMem_mobile("0927834429");
		memberVO2.setMem_city("新北市");
		memberVO2.setMem_country("台灣");
		memberVO2.setMem_profile("學習認真");
		
//		memberVO2.setMem_status(0);
//		memberVO2.setMem_sex("女");
//		memberVO2.setMem_ac("III032");
//		memberVO2.setFriend_pic(null);
//		memberVO2.setFriend_profile(null);
//		memberVO2.setFriend_choose(null);
//		memberVO2.setFriend_appli(null);
//		memberVO2.setMem_point(new Double(5000));
//		memberVO2.setCouponqty(new Double(2));
//		
//		memberVO2.setMem_createtime(java.sql.Date.valueOf("2019-06-14"));
		dao.update(memberVO2);
		
		// 刪除
		dao.delete("M00010");
		
		// 查詢
		MemberVO memberVO3 = dao.findByPrimaryKey("M00001");
		System.out.print(memberVO3.getMember_id() + ",");
		System.out.print(memberVO3.getMem_ac() + ",");
		System.out.print(memberVO3.getMem_name() + ",");
		System.out.print(memberVO3.getMem_pwd() + ",");
		System.out.print(memberVO3.getMem_nick() + ",");
		System.out.print(memberVO3.getMem_email() + ",");
		System.out.print(memberVO3.getMem_birthday() + ",");
		System.out.print(memberVO3.getMem_mobile() + ",");
		System.out.print(memberVO3.getMem_city() + ",");
		System.out.print(memberVO3.getMem_country() + ",");
		System.out.print(memberVO3.getMem_sex() + ",");
		System.out.print(memberVO3.getMem_status() + ",");
		System.out.print(memberVO3.getFriend_pic() + ",");
		System.out.print(memberVO3.getFriend_profile() + ",");
		System.out.print(memberVO3.getFriend_choose() + ",");
		System.out.print(memberVO3.getFriend_appli() + ",");
		System.out.print(memberVO3.getMem_point() + ",");
		System.out.print(memberVO3.getCouponqty() + ",");
		System.out.print(memberVO3.getMem_profile() + ",");
		System.out.println(memberVO3.getMem_createtime());
		System.out.println("----------------------------------");
		
		// 查詢
		List<MemberVO> list = dao.getAll();
		for (MemberVO aMember : list) {
			System.out.print(aMember.getMember_id() + ",");
			System.out.print(aMember.getMem_ac() + ",");
			System.out.print(aMember.getMem_name() + ",");
			System.out.print(aMember.getMem_pwd() + ",");
			System.out.print(aMember.getMem_nick() + ",");
			System.out.print(aMember.getMem_mobile() + ",");
			System.out.print(aMember.getMem_email() + ",");
			System.out.print(aMember.getMem_birthday() + ",");
			System.out.print(aMember.getMem_city() + ",");
			System.out.print(aMember.getMem_country() + ",");
			System.out.print(aMember.getMem_sex() + ",");
			System.out.print(aMember.getMem_status() + ",");
			System.out.print(aMember.getFriend_pic() + ",");
			System.out.print(aMember.getFriend_profile() + ",");
			System.out.print(aMember.getFriend_choose() + ",");
			System.out.print(aMember.getFriend_appli() + ",");
			System.out.print(aMember.getMem_point() + ",");
			System.out.print(aMember.getCouponqty() + ",");
			System.out.print(aMember.getMem_profile() + ",");
			System.out.print(aMember.getMem_createtime());
			System.out.println();
		}	
	}
}
