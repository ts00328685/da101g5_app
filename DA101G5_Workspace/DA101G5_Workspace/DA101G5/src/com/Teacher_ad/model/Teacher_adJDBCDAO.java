package com.Teacher_ad.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class Teacher_adJDBCDAO  implements Teacher_adDAO_interface{
	

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO teacher_ad (teacher_ad_id,teacher_id,ad_time,ad_start,ad_state) VALUES ('TA'||LPAD(to_char(TEACHER_AD_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT teacher_ad_id,teacher_id,ad_time,to_char(ad_start,'yyyy-mm-dd')ad_start,ad_state FROM teacher_ad order by teacher_ad_id desc";
		private static final String GET_ONE_STMT = 
			"SELECT teacher_ad_id,teacher_id,ad_time,to_char(ad_start,'yyyy-mm-dd')ad_start,ad_state FROM teacher_ad where teacher_ad_id = ?";
		private static final String DELETE = 
			"DELETE FROM teacher_ad where teacher_ad_id = ?";
		private static final String UPDATE = 
			"UPDATE teacher_ad set teacher_id=?, ad_time=?, ad_start=?, ad_state=? where teacher_ad_id = ?";

@Override
public void insert(Teacher_adVO teacher_adVO) {
	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT_STMT);

		pstmt.setString(1, teacher_adVO.getTeacher_id());
		pstmt.setInt(2, teacher_adVO.getAd_time());
		pstmt.setTimestamp(3, (Timestamp) teacher_adVO.getAd_start());
		pstmt.setDouble(4, teacher_adVO.getAd_state());


		pstmt.executeUpdate();

		// Handle any SQL errors
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
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
public void update(Teacher_adVO teacher_adVO) {
	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(UPDATE);

		
		pstmt.setString(1, teacher_adVO.getTeacher_id());
		pstmt.setInt(2, teacher_adVO.getAd_time());
		pstmt.setTimestamp(3, (Timestamp) teacher_adVO.getAd_start());
		pstmt.setInt(4, teacher_adVO.getAd_state());
		pstmt.setString(5, teacher_adVO.getTeacher_ad_id());
		

		pstmt.executeUpdate();

		// Handle any driver errors
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	}catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
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
public void delete(String teacher_ad_id) {
	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(DELETE);

		pstmt.setString(1, teacher_ad_id);

		pstmt.executeUpdate();

		// Handle any driver errors
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	}catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
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
public Teacher_adVO findByPrimaryKey(String teacher_ad_id) {
	Teacher_adVO teacher_adVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_ONE_STMT);

		pstmt.setString(1, teacher_ad_id);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVo 也稱為 Domain objects
			teacher_adVO = new Teacher_adVO();
			teacher_adVO.setTeacher_ad_id(rs.getString("teacher_ad_id"));
			teacher_adVO.setTeacher_id(rs.getString("teacher_id"));
			teacher_adVO.setAd_time(rs.getInt("ad_time"));
			teacher_adVO.setAd_start(rs.getTimestamp("ad_start"));
			teacher_adVO.setAd_state(rs.getInt("ad_state"));
			
		}

		// Handle any driver errors
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	}catch (SQLException se) {
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
	return teacher_adVO;
}

@Override
public List<Teacher_adVO> getAll() {
	List<Teacher_adVO> list = new ArrayList<Teacher_adVO>();
	Teacher_adVO teacher_adVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			// teacher_adVO 也稱為 Domain objects
			teacher_adVO = new Teacher_adVO();
			teacher_adVO.setTeacher_ad_id(rs.getString("teacher_ad_id"));
			teacher_adVO.setTeacher_id(rs.getString("teacher_id"));
			teacher_adVO.setAd_time(rs.getInt("ad_time"));
			teacher_adVO.setAd_start(rs.getTimestamp("ad_start"));
			teacher_adVO.setAd_state(rs.getInt("ad_state"));
			list.add(teacher_adVO); // Store the row in the list
		}

		// Handle any driver errors
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	}catch (SQLException se) {
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

	Teacher_adJDBCDAO dao = new Teacher_adJDBCDAO();

//	// 新增
//	Teacher_adVO teacher_adVO1 = new Teacher_adVO();
////	teacher_adVO1.setTeacher_ad_id();
//	teacher_adVO1.setTeacher_id("T00001");
//	teacher_adVO1.setAd_time(1);
//	teacher_adVO1.setAd_start(java.sql.Date.valueOf("2005-01-01"));
//	teacher_adVO1.setAd_state(1);
//	dao.insert(teacher_adVO1);
//
//	// 修改
//	Teacher_adVO teacher_adVO2 = new Teacher_adVO();
//	teacher_adVO2.setTeacher_ad_id("TA00001");
//	teacher_adVO2.setTeacher_id("T00001");
//	teacher_adVO2.setAd_time(1);
//	teacher_adVO2.setAd_start(java.sql.Date.valueOf("2005-01-01"));
//	teacher_adVO2.setAd_state(1);
//	dao.update(teacher_adVO2);
//
////	// 刪除
////	dao.delete("TO00001");
//
//	// 查詢
//	Teacher_adVO teacher_adVO3 = dao.findByPrimaryKey("TA00001");
//	System.out.print(teacher_adVO3.getTeacher_ad_id() + ",");
//	System.out.print(teacher_adVO3.getTeacher_id() + ",");
//	System.out.print(teacher_adVO3.getAd_time() + ",");
//	System.out.print(teacher_adVO3.getAd_start() + ",");
//	System.out.print(teacher_adVO3.getAd_state() + ",");
//	
//
//	System.out.println("---------------------");

	// 查詢
	List<Teacher_adVO> list = dao.getAll();
	for (Teacher_adVO teacher_adVO : list) {
		System.out.print(teacher_adVO.getTeacher_id() + ",");
		System.out.print(teacher_adVO.getAd_time() + ",");
		System.out.print(teacher_adVO.getAd_start() + ",");
		System.out.print(teacher_adVO.getAd_state() + ",");
		System.out.print(teacher_adVO.getTeacher_ad_id() + ",");
		System.out.println();
	}
}

}
