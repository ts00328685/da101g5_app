package com.teacher_card_table.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Teacher_card_tableDAO implements Teacher_card_tableDAO_interface {
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA101G5";
//	String passwd = "123456";

	private static final String INSERT_STMT = "Insert into teacher_card_table "
			+ "(card_id, teacher_card_group_id) values "
			+ "(?, ?)";

	private static final String GET_ALL_STMT = "SELECT * FROM teacher_card_table order by teacher_card_group_id";
	private static final String GET_ONE_STMT = "SELECT * FROM teacher_card_table where teacher_card_group_id = ?";
	private static final String DELETE = "DELETE FROM teacher_card_table where card_id = ? AND teacher_card_group_id = ?";
	private static final String UPDATE = "UPDATE teacher_card_table set card_id=?, teacher_card_group_id = ? where card_id = ? AND teacher_card_group_id = ?";

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

		Teacher_card_tableDAO dao = new Teacher_card_tableDAO();

		// 新增
		Teacher_card_tableVO teacher_card_tableVO1 = new Teacher_card_tableVO();
		teacher_card_tableVO1.setCard_id("C00001");
		teacher_card_tableVO1.setTeacher_card_group_id("TCG00003");
		dao.insert(teacher_card_tableVO1);
		System.out.println("新增成功!");

		// 修改
		Teacher_card_tableVO teacher_card_tableVO2 = new Teacher_card_tableVO();
		teacher_card_tableVO2.setCard_id("C00002");
		teacher_card_tableVO2.setTeacher_card_group_id("TCG00003");
		dao.update(teacher_card_tableVO2);
		System.out.println("修改成功!");

		// 查詢一筆
		Teacher_card_tableVO teacher_card_tableVO3 = dao.findByPrimaryKey("C00001", "TCG00003");
		System.out.print(teacher_card_tableVO3.getCard_id() + ", ");
		System.out.print(teacher_card_tableVO3.getTeacher_card_group_id() + ", ");
		System.out.println("---------------------");

		// 刪除
		dao.delete("C00001", "TCG00003");
		System.out.println("刪除成功!");
		
		// 查詢全部
		List<Teacher_card_tableVO> list = dao.getAll();
		for (Teacher_card_tableVO aTCT : list) {
			System.out.print(aTCT.getCard_id() + ", ");
			System.out.print(aTCT.getTeacher_card_group_id() + ", ");
			System.out.println();
		}
	}
	
	@Override
	public void insert(Teacher_card_tableVO teacher_card_tableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, teacher_card_tableVO.getCard_id() );
			pstmt.setString(2, teacher_card_tableVO.getTeacher_card_group_id() );

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
	public void update(Teacher_card_tableVO teacher_card_tableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, teacher_card_tableVO.getCard_id() );
			pstmt.setString(2, teacher_card_tableVO.getTeacher_card_group_id() );
			
			pstmt.setString(3, teacher_card_tableVO.getCard_id() );
			pstmt.setString(4, teacher_card_tableVO.getTeacher_card_group_id() );
			
			
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
	public void delete(String card_id, String teacher_card_group_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, card_id);
			pstmt.setString(2, teacher_card_group_id);

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
	public Teacher_card_tableVO findByPrimaryKey(String card_id, String teacher_card_group_id) {

		Teacher_card_tableVO teacher_card_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, card_id);
			pstmt.setString(2, teacher_card_group_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cardVo  Domain objects
				teacher_card_tableVO = new Teacher_card_tableVO();
				teacher_card_tableVO.setCard_id(rs.getString("card_id"));
				teacher_card_tableVO.setTeacher_card_group_id(rs.getString("teacher_card_group_id"));
				
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
		return teacher_card_tableVO;
	}

	@Override
	public List<Teacher_card_tableVO> getAll() {
		
		List<Teacher_card_tableVO> list = new ArrayList<Teacher_card_tableVO>();
		Teacher_card_tableVO teacher_card_tableVO = null;

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
				// teacher_card_tableVO Domain objects
				teacher_card_tableVO = new Teacher_card_tableVO();
				teacher_card_tableVO.setCard_id(rs.getString("card_id"));
				teacher_card_tableVO.setTeacher_card_group_id(rs.getString("teacher_card_group_id"));
				list.add(teacher_card_tableVO); // Store the row in the list
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