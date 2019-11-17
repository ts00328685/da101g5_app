package com.Sort_course.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Sort_courseJDBCDAO implements Sort_courseDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO sort_course (sort_course_id,sort_course) VALUES ('SC'||LPAD(to_char(SORT_COURSE_SEQ.NEXTVAL), 5, '0'), ?)";
		private static final String GET_ALL_STMT = 
			"SELECT sort_course_id,sort_course FROM sort_course order by sort_course_id desc";
		private static final String GET_ONE_STMT = 
			"SELECT sort_course_id,sort_course FROM sort_course where sort_course_id = ?";
		private static final String DELETE = 
			"DELETE FROM sort_course where sort_course_id = ?";
		private static final String UPDATE = 
			"UPDATE sort_course set sort_course=? where sort_course_id = ?";

@Override
public void insert(Sort_courseVO sort_courseVO) {
	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(INSERT_STMT);

		pstmt.setString(1, sort_courseVO.getSort_course());
		

		pstmt.executeUpdate();

		// Handle any SQL errors
	    }catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
		// Handle any SQL errors
		}  catch (SQLException se) {
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
public void update(Sort_courseVO sort_courseVO) {
	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(UPDATE);

		pstmt.setString(1, sort_courseVO.getSort_course());
		pstmt.setString(2, sort_courseVO.getSort_course_id());
		
		

		pstmt.executeUpdate();

		// Handle any driver errors
		} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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
public void delete(String sort_course_id) {
	
	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(DELETE);

		pstmt.setString(1, sort_course_id);

		pstmt.executeUpdate();

		// Handle any driver errors
		} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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
public Sort_courseVO findByPrimaryKey(String sort_course_id) {
	Sort_courseVO sort_courseVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_ONE_STMT);

		pstmt.setString(1, sort_course_id);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVo 也稱為 Domain objects
			sort_courseVO = new Sort_courseVO();
			sort_courseVO.setSort_course_id(rs.getString("sort_course_id"));
			sort_courseVO.setSort_course(rs.getString("sort_course"));
			
		}

		// Handle any driver errors
		} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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
	return sort_courseVO;
}

@Override
public List<Sort_courseVO> getAll() {
	List<Sort_courseVO> list = new ArrayList<Sort_courseVO>();
	Sort_courseVO sort_courseVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			// sort_courseVO 也稱為 Domain objects
			sort_courseVO = new Sort_courseVO();
			sort_courseVO = new Sort_courseVO();
			sort_courseVO.setSort_course_id(rs.getString("sort_course_id"));
			sort_courseVO.setSort_course(rs.getString("sort_course"));
			list.add(sort_courseVO); // Store the row in the list
		}

		// Handle any driver errors
		}catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
		// Handle any SQL errors
		}  catch (SQLException se) {
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
//
//	Sort_courseJDBCDAO dao = new Sort_courseJDBCDAO();
//
//	// 新增
//	Sort_courseVO sort_courseVO1 = new Sort_courseVO();
//	sort_courseVO1.setSort_course("TEST1");
//
//	
//	dao.insert(sort_courseVO1);
//
//	// 修改
//	Sort_courseVO sort_courseVO2 = new Sort_courseVO();
//	sort_courseVO2.setSort_course_id("SC00001");
//	
//	sort_courseVO2.setSort_course("TEST");
//	// 刪除
////	dao.delete("SC00001");
//
//	// 查詢
//	Sort_courseVO sort_courseVO3 = dao.findByPrimaryKey("SC00001");
//	System.out.print(sort_courseVO3.getSort_course_id() + ",");
//	System.out.print(sort_courseVO3.getSort_course()+ ",");
//	
//	
//	System.out.println("---------------------");
//
//	// 查詢
//	List<Sort_courseVO> list = dao.getAll();
//	for (Sort_courseVO sort_courseVO : list) {
//		System.out.print(sort_courseVO.getSort_course_id() + ",");
//		System.out.print(sort_courseVO.getSort_course() + ",");
//		
//		System.out.println();
//	}
//}

}
