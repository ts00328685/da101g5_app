package com.friendmanage.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import com.friendchoose.model.FriendChooseVO;
import com.member.model.MemberVO;



public class FriendManageDAO implements FriendManageDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	private static final String INSERT = 
	"INSERT INTO friend (friend_member_id,friend_member_fid,friend_time,friend_status) VALUES (?, ?, ?, 0)";
	private static final String UPDATE = 
	"UPDATE friend set friend_member_id=?, friend_member_fid=?, friend_time=?, friend_status=? where friend_member_id=? AND friend_member_fid = ?";
	private static final String DELETE = 
	"DELETE FROM friend where friend_member_id=? AND friend_member_fid = ?";
	private static final String GETALL =
    "SELECT friend_member_id,friend_member_fid,to_char(friend_time,'yyyy-mm-dd')friend_time,friend_status FROM friend ORDER BY friend_member_id"; 
	private static final String GET_ONE_ALL="SELECT friend_member_id,friend_member_fid,to_char(friend_time,'yyyy-mm-dd')friend_time,friend_status FROM friend WHERE friend_member_id=? ORDER BY friend_time";
	public static final String GET_ONE = "SELECT member_id, friend_profile,friend_pic, friend_choose, friend_appli FROM MEMBER WHERE member_id=?";
	public static final String GETALLMEMBER = "SELECT member_id, friend_profile,friend_pic, friend_choose, friend_appli FROM MEMBER";
	
	
	
	//新增
	@Override
	public void insert(FriendManageVO friendManageVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, friendManageVO.getFriend_member_id());
			pstmt.setString(2, friendManageVO.getFriend_member_fid());
			pstmt.setDate(3, new Date(System.currentTimeMillis()));
			
			

			pstmt.executeUpdate();

			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		} finally {
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

	}
		
	
	//修改
	@Override
	public void update(FriendManageVO friendManageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, friendManageVO.getFriend_member_id());
			pstmt.setString(2, friendManageVO.getFriend_member_fid());
			pstmt.setDate(3, friendManageVO.getFriend_time());
			pstmt.setInt(4, friendManageVO.getFriend_status());
			pstmt.setString(5, friendManageVO.getFriend_member_id());
			pstmt.setString(6, friendManageVO.getFriend_member_fid());

			pstmt.executeUpdate();

			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		} finally {
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

	}
		
	
	//刪除
	@Override
	public void delete(String friend_member_id,String friend_member_fid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, friend_member_id);
			pstmt.setString(2, friend_member_fid);

			pstmt.executeUpdate();

			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
		} finally {
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
		
	}


	//查全部
	@Override
	public List<FriendManageVO> findAll() {
		List<FriendManageVO> list = new ArrayList<FriendManageVO>();
		FriendManageVO friendManageVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				friendManageVO = new FriendManageVO();
				friendManageVO.setFriend_member_id(rs.getString("friend_member_id"));
				friendManageVO.setFriend_member_fid(rs.getString("friend_member_fid"));
				friendManageVO.setFriend_time(rs.getDate("friend_time"));
				friendManageVO.setFriend_status(rs.getInt("friend_status"));
				list.add(friendManageVO);
			}			
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
	
	


	
	//查某個會員的學伴
	@Override
	public List<FriendManageVO> findOneAllFriends(String friend_member_id) {
		List<FriendManageVO> list = new ArrayList<FriendManageVO>();
		FriendManageVO friendManageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ALL);
			
			pstmt.setString(1, friend_member_id);
			

			rs = pstmt.executeQuery();
			
			while (rs.next()) {	
				friendManageVO = new FriendManageVO();
				friendManageVO.setFriend_member_id(rs.getString("friend_member_id"));
				friendManageVO.setFriend_member_fid(rs.getString("friend_member_fid"));
				friendManageVO.setFriend_time(rs.getDate("friend_time"));
				friendManageVO.setFriend_status(rs.getInt("friend_status"));
				list.add(friendManageVO); 
			}

			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
	
	//查某個會員
		public MemberVO findOne(String condition_member_id) {
			MemberVO memberVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE);

				pstmt.setString(1, condition_member_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					memberVO = new MemberVO();
					memberVO.setMember_id(rs.getString("member_id"));
					memberVO.setFriend_profile(rs.getString("friend_profile"));
					memberVO.setFriend_pic(rs.getBytes("friend_pic"));
					memberVO.setFriend_choose(rs.getInt("friend_choose"));
					memberVO.setFriend_appli(rs.getInt("friend_appli"));
				}

			}catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		public List<MemberVO> findAllMember() {
			List<MemberVO> list = new ArrayList<MemberVO>();
			MemberVO memberVO =null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GETALLMEMBER);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					memberVO = new MemberVO();
					memberVO.setMember_id(rs.getString("member_id"));
					memberVO.setFriend_profile(rs.getString("friend_profile"));
					memberVO.setFriend_pic(rs.getBytes("friend_pic"));
					memberVO.setFriend_choose(rs.getInt("friend_choose"));
					memberVO.setFriend_appli(rs.getInt("friend_appli"));
					list.add(memberVO);
				}			
				
			}catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				
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
		FriendManageDAO dao = new FriendManageDAO();
		Date date = new Date(10000);

		//新增
//		FriendManageVO fVO1 = new FriendManageVO();
//		fVO1.setFriend_member_id("M00007");
//		fVO1.setFriend_member_fid("M00006");
//		fVO1.setFriend_time(java.sql.Date.valueOf("2005-01-01"));
//		dao.insert(fVO1);
//		System.out.print("ok");

		//修改
//		FriendManageVO fVO1 = new FriendManageVO();
//		fVO1.setFriend_member_id("M00003");
//		fVO1.setFriend_member_fid("M00006");
//		fVO1.setFriend_time(date);
//		fVO1.setFriend_status(3);	
//		dao.update(fVO1);
//		System.out.print("ok");

		//刪除
//		dao.delete("M00003","M00006");
//		System.out.print("ok");
//
		//查全部
//		List<FriendManageVO> list = dao.findAll();
//		for (FriendManageVO friendManageVO: list) {
//			System.out.print(friendManageVO.getFriend_member_id() + ",");
//			System.out.print(friendManageVO.getFriend_member_fid() + ",");
//			System.out.print(friendManageVO.getFriend_time() + ",");
//			System.out.print(friendManageVO.getFriend_status() + ",");
//			System.out.println();
//		}
//

		
		//查某個會員的學伴(資料)
//		List<FriendManageVO> list = dao.findOneAllFriends("M00001");
//		for (FriendManageVO friendManageVO: list) {
//			System.out.print(friendManageVO.getFriend_member_id() + ",");
//			System.out.print(friendManageVO.getFriend_member_fid() + ",");
//			System.out.print(friendManageVO.getFriend_time() + ",");
//			System.out.print(friendManageVO.getFriend_status() + ",");
//			System.out.println();
//		}
		
		//查某個會員
//		MemberVO memberVO = new MemberVO();
//		memberVO = dao.findOne("M00007");
//		
//		System.out.print(memberVO.getMember_id() + ",");
//		System.out.print(memberVO.getFriend_profile() + ",");
//		System.out.print(memberVO.getFriend_pic() + ",");
//		System.out.print(memberVO.getFriend_choose() + ",");
//		System.out.print(memberVO.getFriend_appli() + ",");
//		System.out.println();
	
	
	 	//查全部
		List<MemberVO> list = dao.findAllMember();
		for (MemberVO memberVO: list) {
			System.out.print(memberVO.getMember_id() + ",");
			System.out.print(memberVO.getFriend_profile() + ",");
			System.out.print(memberVO.getFriend_pic() + ",");
			System.out.print(memberVO.getFriend_choose() + ",");
			System.out.print(memberVO.getFriend_appli() + ",");
			System.out.println();
		}
	}

}
