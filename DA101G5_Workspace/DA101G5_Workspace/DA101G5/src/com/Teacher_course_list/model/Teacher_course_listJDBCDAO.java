package com.Teacher_course_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Teacher_course_listJDBCDAO  implements Teacher_course_listDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	private static final String INSERT_STMT = 
			"INSERT INTO teacher_course_list (teacher_course_list_id,sort_course_id,language_id,teacher_id,course_state,course_appraisal_accum,course_appraisal_count) VALUES ('TCL'||LPAD(to_char(TEACHER_COURSE_LIST_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT teacher_course_list_id,sort_course_id,language_id,teacher_id,course_state,course_appraisal_accum,course_appraisal_count FROM teacher_course_list order by teacher_course_list_id desc";
	private static final String GET_ONE_STMT = 
		"SELECT teacher_course_list_id,sort_course_id,language_id,teacher_id,course_state,course_appraisal_accum,course_appraisal_count FROM teacher_course_list where teacher_course_list_id = ?";
	private static final String DELETE = 
		"DELETE FROM teacher_course_list where teacher_course_list_id = ?";
	private static final String UPDATE = 
		"UPDATE teacher_course_list set sort_course_id=?, language_id=?, teacher_id=?, course_appraisal_accum=?, course_appraisal_count=?, course_state=?where teacher_course_list_id = ?";

@Override
public void insert(Teacher_course_listVO teacher_course_listVO) {
	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT_STMT);

		pstmt.setString(1, teacher_course_listVO.getSort_course_id());
		pstmt.setString(2, teacher_course_listVO.getLanguage_id());
		pstmt.setString(3, teacher_course_listVO.getTeacher_id());
		pstmt.setInt(4, teacher_course_listVO.getCourse_state());
		pstmt.setInt(5, teacher_course_listVO.getCourse_appraisal_accum());
		pstmt.setInt(6, teacher_course_listVO.getCourse_appraisal_count());
		
		

		pstmt.executeUpdate();

		// Handle any SQL errors
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
public void update(Teacher_course_listVO teacher_course_listVO) {
	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(UPDATE);

		
		pstmt.setString(1, teacher_course_listVO.getSort_course_id());
		pstmt.setString(2, teacher_course_listVO.getLanguage_id());
		pstmt.setString(3, teacher_course_listVO.getTeacher_id());
		pstmt.setInt(4, teacher_course_listVO.getCourse_appraisal_accum());
		pstmt.setInt(5, teacher_course_listVO.getCourse_appraisal_count());
		pstmt.setInt(6, teacher_course_listVO.getCourse_state());
		pstmt.setString(7, teacher_course_listVO.getTeacher_course_list_id());

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
public void delete(String teacher_course_list) {
	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(DELETE);

		pstmt.setString(1, teacher_course_list);

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
public Teacher_course_listVO findByPrimaryKey(String teacher_course_list) {
	Teacher_course_listVO teacher_course_listVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_ONE_STMT);

		pstmt.setString(1, teacher_course_list);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVo 也稱為 Domain objects
			teacher_course_listVO = new Teacher_course_listVO();
			teacher_course_listVO.setTeacher_course_list_id(rs.getString("teacher_course_list_id"));
			teacher_course_listVO.setSort_course_id(rs.getString("sort_course_id"));
			teacher_course_listVO.setLanguage_id(rs.getString("language_id"));
			teacher_course_listVO.setTeacher_id(rs.getString("teacher_id"));
			teacher_course_listVO.setCourse_state(rs.getInt("course_state"));
			teacher_course_listVO.setCourse_appraisal_accum(rs.getInt("course_appraisal_accum"));
			teacher_course_listVO.setCourse_appraisal_count(rs.getInt("course_appraisal_count"));
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
	return teacher_course_listVO;
}

@Override
public List<Teacher_course_listVO> getAll() {
	List<Teacher_course_listVO> list = new ArrayList<Teacher_course_listVO>();
	Teacher_course_listVO teacher_course_listVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			// teacher_course_listVO 也稱為 Domain objects
			teacher_course_listVO = new Teacher_course_listVO();
			teacher_course_listVO.setTeacher_course_list_id(rs.getString("teacher_course_list_id"));
			teacher_course_listVO.setSort_course_id(rs.getString("sort_course_id"));
			teacher_course_listVO.setLanguage_id(rs.getString("language_id"));
			teacher_course_listVO.setTeacher_id(rs.getString("teacher_id"));
			teacher_course_listVO.setCourse_state(rs.getInt("course_state"));
			teacher_course_listVO.setCourse_appraisal_accum(rs.getInt("course_appraisal_accum"));
			teacher_course_listVO.setCourse_appraisal_count(rs.getInt("course_appraisal_count"));
			list.add(teacher_course_listVO); // Store the row in the list
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


//public static void main(String[] args) {
////
//	Teacher_course_listJDBCDAO dao = new Teacher_course_listJDBCDAO();
////
//	// 新增
//	Teacher_course_listVO teacher_course_listVO1 = new Teacher_course_listVO();
//	
//	teacher_course_listVO1.setSort_course_id("SC00001");
//	teacher_course_listVO1.setLanguage_id("L00001");
//	teacher_course_listVO1.setTeacher_id("T00001");
//	teacher_course_listVO1.setCourse_appraisal_accum(1);
//	teacher_course_listVO1.setCourse_appraisal_count(1);
//	teacher_course_listVO1.setCourse_state(0);
//	dao.insert(teacher_course_listVO1);
////
////	// 修改
////	Teacher_course_listVO teacher_course_listVO2 = new Teacher_course_listVO();
////	
////	teacher_course_listVO2.setTeacher_course_list_id("TCL00001");
////	teacher_course_listVO2.setSort_course_id("SC00001");
////	teacher_course_listVO2.setLanguage_id("L00001");
////	teacher_course_listVO2.setTeacher_id("T00001");
////	teacher_course_listVO2.setCourse_state(0);
////	teacher_course_listVO2.setCourse_appraisal_accum(1);
////	teacher_course_listVO2.setCourse_appraisal_count(1);
////	dao.update(teacher_course_listVO2);
////	System.out.println("修改成功");
////
//////	// 刪除
//////	dao.delete("TCL00001");
////
////	// 查詢
////	Teacher_course_listVO teacher_course_listVO3 = dao.findByPrimaryKey("TCL00001");
////	System.out.println(teacher_course_listVO3.getSort_course_id() + ",");
////	System.out.println(teacher_course_listVO3.getLanguage_id() + ",");
////	System.out.println(teacher_course_listVO3.getTeacher_id() + ",");
////	System.out.println(teacher_course_listVO3.getCourse_appraisal_accum() + ",");
////	System.out.println(teacher_course_listVO3.getCourse_appraisal_count() + ",");
////	System.out.println(teacher_course_listVO3.getTeacher_course_list_id() + ",");
////
////	System.out.println("---------------------");
////
////	// 查詢
////	List<Teacher_course_listVO> list = dao.getAll();
////	for (Teacher_course_listVO teacher_course_listVO4 : list) {
////		System.out.print(teacher_course_listVO4.getSort_course_id() + ",");
////		System.out.print(teacher_course_listVO4.getLanguage_id() + ",");
////		System.out.print(teacher_course_listVO4.getTeacher_id() + ",");
////		System.out.print(teacher_course_listVO4.getCourse_appraisal_accum() + ",");
////		System.out.print(teacher_course_listVO4.getCourse_appraisal_count() + ",");
////		System.out.print(teacher_course_listVO4.getTeacher_course_list_id() + ",");
////		System.out.println();
////	}
//}

}
