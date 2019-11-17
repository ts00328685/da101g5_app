package com.member_card_group.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Member_card_groupDAO implements Member_card_groupDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "Insert into member_card_group "
			+ "(member_card_group_id, teacher_card_group_id, member_id, expiration_num, new_card_num, spend_time) values "
			+ "('MCG'||LPAD(to_char(mc_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT * FROM member_card_group order by member_card_group_id";
	private static final String GET_ONE_STMT = "SELECT * FROM member_card_group where member_card_group_id = ?";
	private static final String DELETE = "DELETE FROM member_card_group where member_card_group_id = ?";
	private static final String UPDATE = "UPDATE member_card_group set teacher_card_group_id=?, member_id = ?"
			+ ", expiration_num = ?, new_card_num= ?, spend_time = ? where member_card_group_id = ?";

// JNDI
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) {

		Member_card_groupDAO dao = new Member_card_groupDAO();

		// 新增
		Member_card_groupVO member_card_groupVO1 = new Member_card_groupVO();
		member_card_groupVO1.setTeacher_card_group_id("TCG00003");
		member_card_groupVO1.setMember_id("M00011");
		member_card_groupVO1.setExpiration_num(5);
		member_card_groupVO1.setNew_card_num(10);
		member_card_groupVO1.setSpend_time(3000);
		dao.insert(member_card_groupVO1);
		System.out.println("新增成功!");

		// 修改
		Member_card_groupVO member_card_groupVO2 = new Member_card_groupVO();
		member_card_groupVO2.setMember_card_group_id("MCG00004");
		member_card_groupVO2.setTeacher_card_group_id("TCG00002");
		member_card_groupVO2.setMember_id("M00012");
		member_card_groupVO2.setExpiration_num(6);
		member_card_groupVO2.setNew_card_num(11);
		member_card_groupVO2.setSpend_time(4000);				
		dao.update(member_card_groupVO2);
		System.out.println("修改成功!");

		// 查詢一筆
		Member_card_groupVO member_card_groupVO3 = dao.findByPrimaryKey("MCG00004");
		System.out.print(member_card_groupVO3.getMember_card_group_id() + ", ");
		System.out.print(member_card_groupVO3.getTeacher_card_group_id() + ", ");
		System.out.print(member_card_groupVO3.getMember_id() + ", ");
		System.out.print(member_card_groupVO3.getExpiration_num() + ", ");
		System.out.print(member_card_groupVO3.getNew_card_num() + ", ");
		System.out.print(member_card_groupVO3.getSpend_time() + ", ");
		System.out.println("---------------------");

		// 刪除
		dao.delete("MCG00004");
		System.out.println("刪除成功!");
		
		// 查詢全部
		List<Member_card_groupVO> list = dao.getAll();
		for (Member_card_groupVO aMCG : list) {
			System.out.print(aMCG.getMember_card_group_id() + ", ");
			System.out.print(aMCG.getTeacher_card_group_id() + ", ");
			System.out.print(aMCG.getMember_id() + ", ");
			System.out.print(aMCG.getExpiration_num() + ", ");
			System.out.print(aMCG.getNew_card_num() + ", ");
			System.out.print(aMCG.getSpend_time() + ", ");
			System.out.println();
		}
		
	}
	
	@Override
	public void insert(Member_card_groupVO member_card_groupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			//con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, member_card_groupVO.getTeacher_card_group_id() );
			pstmt.setString(2, member_card_groupVO.getMember_id() );
			pstmt.setInt(3, member_card_groupVO.getExpiration_num() );
			pstmt.setInt(4, member_card_groupVO.getNew_card_num());
			pstmt.setInt(5, member_card_groupVO.getSpend_time() );

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e){
			throw new RuntimeException("A class error occured. "
					+ e.getMessage());
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

	@Override
	public void update(Member_card_groupVO member_card_groupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			//con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, member_card_groupVO.getTeacher_card_group_id() );
			pstmt.setString(2, member_card_groupVO.getMember_id() );
			pstmt.setInt(3, member_card_groupVO.getExpiration_num() );
			pstmt.setInt(4, member_card_groupVO.getNew_card_num());
			pstmt.setInt(5, member_card_groupVO.getSpend_time() );
			pstmt.setString(6, member_card_groupVO.getMember_card_group_id() );
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e){
			throw new RuntimeException("A class error occured. "
					+ e.getMessage());
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

	@Override
	public void delete(String member_card_group_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			//con = ds.getConnection();
			
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, member_card_group_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e){
			throw new RuntimeException("A class error occured. "
					+ e.getMessage());
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

	@Override
	public Member_card_groupVO findByPrimaryKey(String member_card_group_id) {

		Member_card_groupVO member_card_groupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			//con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, member_card_group_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cardVo  Domain objects
				member_card_groupVO = new Member_card_groupVO();
				member_card_groupVO.setMember_card_group_id(rs.getString("member_card_group_id"));
				member_card_groupVO.setTeacher_card_group_id(rs.getString("teacher_card_group_id"));
				member_card_groupVO.setMember_id(rs.getString("member_id"));
				member_card_groupVO.setExpiration_num(rs.getInt("expiration_num"));
				member_card_groupVO.setNew_card_num(rs.getInt("new_card_num"));
				member_card_groupVO.setSpend_time(rs.getInt("spend_time"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e){
			throw new RuntimeException("A class error occured. "
					+ e.getMessage());
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
		return member_card_groupVO;
	}

	@Override
	public List<Member_card_groupVO> getAll() {
		List<Member_card_groupVO> list = new ArrayList<Member_card_groupVO>();
		Member_card_groupVO member_card_groupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			//con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// member_card_groupVO Domain objects
				member_card_groupVO = new Member_card_groupVO();
				member_card_groupVO.setMember_card_group_id(rs.getString("member_card_group_id"));
				member_card_groupVO.setTeacher_card_group_id(rs.getString("teacher_card_group_id"));
				member_card_groupVO.setMember_id(rs.getString("member_id"));
				member_card_groupVO.setExpiration_num(rs.getInt("expiration_num"));
				member_card_groupVO.setNew_card_num(rs.getInt("new_card_num"));
				member_card_groupVO.setSpend_time(rs.getInt("spend_time"));
				list.add(member_card_groupVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e){
			throw new RuntimeException("A class error occured. "
					+ e.getMessage());
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