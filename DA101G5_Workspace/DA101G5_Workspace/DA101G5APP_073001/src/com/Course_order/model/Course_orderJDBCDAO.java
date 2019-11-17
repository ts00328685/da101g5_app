package com.Course_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Course_order.model.Course_orderJDBCDAO;
import com.Course_order.model.Course_orderVO;



public class Course_orderJDBCDAO implements Course_orderDAO_interface{
	
	@Override
	public List<Course_orderVOApp> getMyOneTeacherCourse(String member_id, String teacher_id) {
		// TODO Auto-generated method stub
		return null;
	}

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO course_order (course_order_id,member_id,teacher_id,buy_time,spend_point,remain_hour,re_appointment) VALUES ('CO'||LPAD(to_char(COURSE_ORDER_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT course_order_id,member_id,teacher_id,buy_time,spend_point,remain_hour,re_appointment FROM course_order order by course_order_id desc";
		private static final String GET_ONE_STMT = 
			"SELECT course_order_id,member_id,teacher_id,buy_time,spend_point,remain_hour,re_appointment FROM course_order where course_order_id = ?";
		private static final String DELETE = 
			"DELETE FROM course_order where course_order_id = ?";
		private static final String UPDATE = 
			"UPDATE course_order set member_id=?, teacher_id=?, buy_time=?, spend_point=?, remain_hour=?, re_appointment=? where course_order_id = ?";
	
	
	@Override
		public List<Course_orderVOApp> getMyCourseOrderRecords(String member_id) {
			// TODO Auto-generated method stub
			return null;
		}

	@Override
		public void insertApp(Course_orderVO course_orderVO) {
			// TODO Auto-generated method stub
			
		}

	@Override
	public void insert(Course_orderVO course_orderVO) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, course_orderVO.getMember_id());
			pstmt.setString(2, course_orderVO.getTeacher_id());
			pstmt.setInt(3, course_orderVO.getBuy_time());
			pstmt.setInt(4, course_orderVO.getSpend_point());
			pstmt.setInt(5, course_orderVO.getRemain_hour());
			pstmt.setInt(6, course_orderVO.getRe_appointment());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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
	public void update(Course_orderVO course_orderVO) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, course_orderVO.getMember_id());
			pstmt.setString(2, course_orderVO.getTeacher_id());
			pstmt.setInt(3, course_orderVO.getBuy_time());
			pstmt.setInt(4, course_orderVO.getSpend_point());
			pstmt.setInt(5, course_orderVO.getRemain_hour());
			pstmt.setInt(6, course_orderVO.getRe_appointment());
			pstmt.setString(7, course_orderVO.getCourse_order_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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
	public void delete(String course_order_id) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, course_order_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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
	public Course_orderVO findByPrimaryKey(String course_order_id) {


		
		Course_orderVO course_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,course_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Course_orderVO 也稱為 Domain objects
				course_orderVO = new Course_orderVO();
				course_orderVO.setMember_id(rs.getString("course_order_id"));
				course_orderVO.setMember_id(rs.getString("member_id"));
				course_orderVO.setTeacher_id(rs.getString("teacher_id"));
				course_orderVO.setBuy_time(rs.getInt("buy_time"));
				course_orderVO.setSpend_point(rs.getInt("spend_point"));
				course_orderVO.setRemain_hour(rs.getInt("remain_hour"));
				course_orderVO.setRe_appointment(rs.getInt("re_appointment"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
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
		return course_orderVO;
	}

	@Override
	public List<Course_orderVO> getAll() {
		List<Course_orderVO> list = new ArrayList<Course_orderVO>();
		Course_orderVO course_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Course_orderVO 也稱為 Domain objects
				course_orderVO = new Course_orderVO();
				course_orderVO.setMember_id(rs.getString("course_order_id"));
				course_orderVO.setMember_id(rs.getString("member_id"));
				course_orderVO.setTeacher_id(rs.getString("teacher_id"));
				course_orderVO.setBuy_time(rs.getInt("buy_time"));
				course_orderVO.setSpend_point(rs.getInt("spend_point"));
				course_orderVO.setRemain_hour(rs.getInt("remain_hour"));
				course_orderVO.setRe_appointment(rs.getInt("re_appointment"));
				list.add(course_orderVO); // Store the row in the list
			}

			// Handle any driver errors
		}catch (ClassNotFoundException e) {
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
		return list;
	}
	
	
	public static void main(String[] args) {

		Course_orderJDBCDAO dao = new Course_orderJDBCDAO();

		// 新增
		Course_orderVO course_orderVO1 = new Course_orderVO();
//		course_orderVO1.setCourse_order_id();
		course_orderVO1.setMember_id("M00001");
		course_orderVO1.setTeacher_id("T00001");
		course_orderVO1.setBuy_time(1);
		course_orderVO1.setSpend_point(1);
		course_orderVO1.setRemain_hour(1);
		course_orderVO1.setRe_appointment(1);
		dao.insert(course_orderVO1);

		// 修改
		Course_orderVO course_orderVO2 = new Course_orderVO();
		course_orderVO2.setCourse_order_id("CO00001");
		course_orderVO2.setMember_id("M00001");
		course_orderVO2.setTeacher_id("T00001");
		course_orderVO2.setBuy_time(1);
		course_orderVO2.setSpend_point(1);
		course_orderVO2.setRemain_hour(1);
		course_orderVO2.setRe_appointment(1);
		dao.update(course_orderVO2);

		// 刪除
//		dao.delete("CO00001");

		// 查詢
		Course_orderVO course_orderVO3 = dao.findByPrimaryKey("CO00001");
		System.out.print(course_orderVO3.getMember_id() + ",");
		System.out.print(course_orderVO3.getTeacher_id() + ",");
		System.out.print(course_orderVO3.getBuy_time() + ",");
		System.out.print(course_orderVO3.getSpend_point()+ ",");
		System.out.print(course_orderVO3.getRemain_hour() + ",");
		System.out.print(course_orderVO3.getRe_appointment() + ",");
		System.out.println(course_orderVO3.getCourse_order_id());
		System.out.println("---------------------");

		// 查詢
		List<Course_orderVO> list = dao.getAll();
		for (Course_orderVO course_orderVO4 : list) {
			System.out.println(course_orderVO4.getMember_id() + ",");
			System.out.println(course_orderVO4.getTeacher_id() + ",");
			System.out.println(course_orderVO4.getBuy_time() + ",");
			System.out.println(course_orderVO4.getSpend_point()+ ",");
			System.out.println(course_orderVO4.getRemain_hour() + ",");
			System.out.println(course_orderVO4.getRe_appointment() + ",");
			System.out.println(course_orderVO4.getCourse_order_id());
			System.out.println();
		}
	}

	@Override
	public List<Course_orderVO> getMyCourseOrders(String member_id) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
