package com.friendchoose.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.member.model.MemberVO;

public class FriendChooseDAO implements FriendChooseDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	public static final String INSERT = "INSERT INTO CONDITION (CONDITION_ID, CONDITION_LANGUAGE_ID,CONDITION_MEMBER_ID, CONDITION_SEX) VALUES ('CD'||LPAD(to_char(condition_seq.NEXTVAL),5,'0'),?,?,?)";
	public static final String UPDATE = "UPDATE CONDITION SET CONDITION_LANGUAGE_ID=?,CONDITION_MEMBER_ID=?,CONDITION_SEX=? WHERE CONDITION_ID=?";
	public static final String DELETE = "DELETE FROM CONDITION WHERE CONDITION_ID=?"; 
	public static final String GETALL = "SELECT CONDITION_ID, CONDITION_LANGUAGE_ID,CONDITION_MEMBER_ID, CONDITION_SEX FROM CONDITION ORDER BY CONDITION_ID";
	public static final String GET_BY_CONDITION = "SELECT CONDITION_ID, CONDITION_LANGUAGE_ID,CONDITION_MEMBER_ID, CONDITION_SEX FROM CONDITION WHERE CONDITION_LANGUAGE_ID=? AND CONDITION_SEX=? ORDER BY CONDITION_ID";
	public static final String GET_ONE = "SELECT member_id, friend_profile,friend_pic, friend_choose, friend_appli FROM MEMBER WHERE member_id=?";
	public static final String GETALLMEMBER = "SELECT member_id, friend_profile,friend_pic, friend_choose, friend_appli FROM MEMBER";
	public static final String UPDATEMEMBER = "UPDATE member set friend_profile=?,friend_pic=?,friend_choose=?,friend_appli=? where member_id=?";
	
	//新增
	@Override
	public void insert(FriendChooseVO friendChooseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, friendChooseVO.getCondition_member_id());
			pstmt.setString(2, friendChooseVO.getCondition_language_id());
			pstmt.setInt(3, friendChooseVO.getCondition_sex());
			

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
	public void update(FriendChooseVO friendChooseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, friendChooseVO.getCondition_language_id());
			pstmt.setString(2, friendChooseVO.getCondition_member_id());
			pstmt.setInt(3, friendChooseVO.getCondition_sex());
			pstmt.setString(4, friendChooseVO.getCondition_id());
			

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
	public void delete(String condition_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, condition_id);		

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
	public List<FriendChooseVO> findAll() {
		List<FriendChooseVO> list = new ArrayList<FriendChooseVO>();
		FriendChooseVO friendChooseVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				friendChooseVO = new FriendChooseVO();
				friendChooseVO.setCondition_id(rs.getString("condition_id"));
				friendChooseVO.setCondition_language_id(rs.getString("condition_language_id"));
				friendChooseVO.setCondition_member_id(rs.getString("condition_member_id"));
				friendChooseVO.setCondition_sex(rs.getInt("condition_sex"));
				list.add(friendChooseVO);
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

	//依語言&性別查會員
	@Override
	public List<FriendChooseVO> findByCondition(String condition_language_id, Integer condition_sex) {
		List<FriendChooseVO> list = new ArrayList<FriendChooseVO>();
		FriendChooseVO friendChooseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_CONDITION);
			
			pstmt.setString(1, condition_language_id);
			pstmt.setInt(2, condition_sex);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				friendChooseVO = new FriendChooseVO();
				friendChooseVO.setCondition_id(rs.getString("condition_id"));
				friendChooseVO.setCondition_language_id(rs.getString("condition_language_id"));
				friendChooseVO.setCondition_member_id(rs.getString("condition_member_id"));
				friendChooseVO.setCondition_sex(rs.getInt("condition_sex"));
				list.add(friendChooseVO);
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

	
	
	
	@Override
	public void updateMember(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEMEMBER);

			pstmt.setString(5, memberVO.getMember_id());
			pstmt.setString(1, memberVO.getFriend_profile());
			pstmt.setBytes(2, memberVO.getFriend_pic());
			pstmt.setInt(3, memberVO.getFriend_choose());
			pstmt.setInt(4, memberVO.getFriend_appli());
			

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
		
	

	public static void main(String[] args) {
		FriendChooseDAO dao = new FriendChooseDAO();

//		//新增
//		FriendChooseVO fVO1 = new FriendChooseVO();
//		fVO1.setCondition_language_id("L00002");
//		fVO1.setCondition_member_id("M00018");
//		fVO1.setCondition_sex(new Integer(0));		
//		dao.insert(fVO1);
//		System.out.print("ok");

		//修改
//		FriendChooseVO fVO1 = new FriendChooseVO();
//		fVO1.setCondition_id("CD00001");
//		fVO1.setCondition_language_id("L00002");
//		fVO1.setCondition_member_id("M00001");
//		fVO1.setCondition_sex(new Integer(0));
//		dao.update(fVO1);
//		System.out.print("ok");

		//刪除
//		dao.delete("CD00001");
//		System.out.print("ok");
//
		//查全部
//		List<FriendChooseVO> list = dao.findAll();
//		for (FriendChooseVO friendChooseVO: list) {
//			System.out.print(friendChooseVO.getCondition_id() + ",");
//			System.out.print(friendChooseVO.getCondition_language_id() + ",");
//			System.out.print(friendChooseVO.getCondition_member_id() + ",");
//			System.out.print(friendChooseVO.getCondition_sex() + ",");
//			System.out.println();
//		}
//
//		//依語言&性別查會員
		List<FriendChooseVO> list = dao.findByCondition("L00001", 1);
		for (FriendChooseVO friendChooseVO: list) {
			System.out.print(friendChooseVO.getCondition_id() + ",");
			System.out.print(friendChooseVO.getCondition_language_id() + ",");
			System.out.print(friendChooseVO.getCondition_member_id() + ",");
			System.out.print(friendChooseVO.getCondition_sex() + ",");
			System.out.println();
		}
		
		//修改某個會員的抽籤狀態		
		
//		dao.update("M00005", 1);
//		System.out.print("ok");
		
		//查個會員狀態
//		MemberVO memberVO = new MemberVO();
//		memberVO = dao.findOneMemberStatus("M00007");
//		
//		System.out.print(memberVO.getMember_id() + ",");
//		System.out.print(memberVO.getFriend_profile() + ",");
//		System.out.print(memberVO.getFriend_pic() + ",");
//		System.out.print(memberVO.getFriend_choose() + ",");
//		System.out.print(memberVO.getFriend_appli() + ",");
//		System.out.println();
//		
		//修改全部具學伴資格的會員之抽籤狀態			
		
//		dao.updateAllStatus(0);
//		System.out.print("ok");
		
		//查被抽中會員的個人資料
//		MemberVO memberVO = dao.findMemberFromChoose("M00005");
//		System.out.print(memberVO.getMember_id() + ",");
//		System.out.print(memberVO.getFriend_profile() + ",");
//		System.out.print(memberVO.getFriend_pic() + ",");
//		System.out.print(memberVO.getFriend_choose() + ",");
//		System.out.print(memberVO.getFriend_appli() + ",");
//		System.out.println();
//		System.out.print("ok");
		
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
//		List<MemberVO> list = dao.findAllMember();
//		for (MemberVO memberVO: list) {
//			System.out.print(memberVO.getMember_id() + ",");
//			System.out.print(memberVO.getFriend_profile() + ",");
//			System.out.print(memberVO.getFriend_pic() + ",");
//			System.out.print(memberVO.getFriend_choose() + ",");
//			System.out.print(memberVO.getFriend_appli() + ",");
//			System.out.println();
//		}
		
		//修改某個會員的資料
//		MemberVO fVO1 = new MemberVO();
//		fVO1.setMember_id("M00007");
//		fVO1.setFriend_profile("223344");
//		fVO1.setFriend_pic(null);
//		fVO1.setFriend_choose(new Integer(1));
//		fVO1.setFriend_appli(0);
//		dao.updateMember(fVO1);
//		System.out.print("123");
	}
}
	
		
	


	
