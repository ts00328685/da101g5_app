package com.teacher_card_group.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Teacher_card_groupDAO implements Teacher_card_groupDAO_interface {
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA101G5";
//	String passwd = "123456";

	private static final String INSERT_STMT = "Insert into teacher_card_group "
			+ "(teacher_card_group_id, TEACHER_ID, teacher_card_group_name) values "
			+ "('TCG'||LPAD(to_char(tc_seq.NEXTVAL), 5, '0'), ?, ?)";

	private static final String GET_ALL_STMT = "SELECT * FROM teacher_card_group order by teacher_card_group_id";
	private static final String GET_ONE_STMT = "SELECT * FROM teacher_card_group where teacher_card_group_id = ?";
	private static final String DELETE = "DELETE FROM teacher_card_group where teacher_card_group_id = ?";
	private static final String UPDATE = "UPDATE teacher_card_group set teacher_id=?, teacher_card_group_name = ? where teacher_card_group_id = ?";

// JNDI
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Teacher_card_groupDAO dao = new Teacher_card_groupDAO();

		// 新增
		Teacher_card_groupVO teacher_card_groupVO1 = new Teacher_card_groupVO();
		teacher_card_groupVO1.setTeacher_id("T00001");
		teacher_card_groupVO1.setTeacher_card_group_name("老師要給會話課的單字組");
		dao.insert(teacher_card_groupVO1);
		System.out.println("新增成功!");

		// 修改
		Teacher_card_groupVO teacher_card_groupVO2 = new Teacher_card_groupVO();
		teacher_card_groupVO2.setTeacher_card_group_id("TCG00004");
		teacher_card_groupVO2.setTeacher_id("T00002");
		teacher_card_groupVO2.setTeacher_card_group_name("老師要給文法課的單字組");
		dao.update(teacher_card_groupVO2);
		System.out.println("修改成功!");

		// 查詢一筆
		Teacher_card_groupVO teacher_card_groupVO3 = dao.findByPrimaryKey("TCG00004");
		System.out.print(teacher_card_groupVO3.getTeacher_card_group_id() + ", ");
		System.out.print(teacher_card_groupVO3.getTeacher_id() + ", ");
		System.out.print(teacher_card_groupVO3.getTeacher_card_group_name() + ", ");
		System.out.println("---------------------");

		// 刪除
		dao.delete("TCG00004");
		System.out.println("刪除成功!");
		
		// 查詢全部
		List<Teacher_card_groupVO> list = dao.getAll();
		for (Teacher_card_groupVO aTCG : list) {
			System.out.print(aTCG.getTeacher_card_group_id() + ", ");
			System.out.print(aTCG.getTeacher_id() + ", ");
			System.out.print(aTCG.getTeacher_card_group_name() + ", ");
			System.out.println();
		}
	}
	
	@Override
	public void insert(Teacher_card_groupVO teacher_card_groupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, teacher_card_groupVO.getTeacher_id() );
			pstmt.setString(2, teacher_card_groupVO.getTeacher_card_group_name() );

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
//		} catch (ClassNotFoundException e){
//			throw new RuntimeException("A class error occured. "
//					+ e.getMessage());
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
	public void update(Teacher_card_groupVO teacher_card_groupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, teacher_card_groupVO.getTeacher_id() );
			pstmt.setString(2, teacher_card_groupVO.getTeacher_card_group_name() );
			pstmt.setString(3, teacher_card_groupVO.getTeacher_card_group_id() );

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
//		} catch (ClassNotFoundException e){
//			throw new RuntimeException("A class error occured. "
//					+ e.getMessage());
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
	public void delete(String teacher_card_group_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, teacher_card_group_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
//		} catch (ClassNotFoundException e){
//			throw new RuntimeException("A class error occured. "
//					+ e.getMessage());
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
	public Teacher_card_groupVO findByPrimaryKey(String teacher_card_group_id) {

		Teacher_card_groupVO teacher_card_groupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, teacher_card_group_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cardVo  Domain objects
				teacher_card_groupVO = new Teacher_card_groupVO();
				teacher_card_groupVO.setTeacher_card_group_id(rs.getString("teacher_card_group_id"));
				teacher_card_groupVO.setTeacher_id(rs.getString("teacher_id"));
				teacher_card_groupVO.setTeacher_card_group_name(rs.getString("teacher_card_group_name"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
//		} catch (ClassNotFoundException e){
//			throw new RuntimeException("A class error occured. "
//					+ e.getMessage());
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
		return teacher_card_groupVO;
	}

	@Override
	public List<Teacher_card_groupVO> getAll() {
		List<Teacher_card_groupVO> list = new ArrayList<Teacher_card_groupVO>();
		Teacher_card_groupVO teacher_card_groupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// teacher_card_groupVO Domain objects
				teacher_card_groupVO = new Teacher_card_groupVO();
				teacher_card_groupVO.setTeacher_card_group_id(rs.getString("teacher_card_group_id"));
				teacher_card_groupVO.setTeacher_id(rs.getString("teacher_id"));
				teacher_card_groupVO.setTeacher_card_group_name(rs.getString("teacher_card_group_name"));
				list.add(teacher_card_groupVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
//		} catch (ClassNotFoundException e){
//			throw new RuntimeException("A class error occured. "
//					+ e.getMessage());
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