package com.Course_order.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Teacher.model.TeacherDAO;
import com.Teacher.model.TeacherVO;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;
import com.point_trans.model.Point_transDAO;
import com.point_trans.model.Point_transVOApp;

public class Course_orderDAO implements Course_orderDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO course_order (course_order_id,member_id,teacher_id,buy_time,spend_point,remain_hour,re_appointment) VALUES ('CO'||LPAD(to_char(COURSE_ORDER_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT course_order_id,member_id,teacher_id,buy_time,spend_point,remain_hour,re_appointment FROM course_order order by course_order_id desc";

	private static final String GET_ONE_STMT = "SELECT course_order_id,member_id,teacher_id,buy_time,spend_point,remain_hour,re_appointment FROM course_order where course_order_id = ?";
	private static final String DELETE = "DELETE FROM course_order where course_order_id = ?";
	private static final String UPDATE = "UPDATE course_order set member_id=?, teacher_id=?, buy_time=?, spend_point=?, remain_hour=?, re_appointment=? where course_order_id = ?";
	private static final String GET_ALL_MY_COURSE_STMT = "SELECT course_order_id,member_id,teacher_id,buy_time,spend_point,remain_hour,re_appointment FROM course_order where member_id = ? order by course_order_id desc";
	
	private static final String GET_ALL_MY_ONE_TEACHER_COURSE_STMT = "SELECT course_order_id,member_id,teacher_id,buy_time,spend_point,remain_hour,re_appointment FROM course_order where member_id = ? and teacher_id = ? and remain_hour > 0 order by remain_hour asc";
	
	@Override
	public List<Course_orderVOApp> getMyOneTeacherCourse(String member_id, String teacher_id) {
		List<Course_orderVOApp> list = new ArrayList<Course_orderVOApp>();
		Course_orderVOApp course_orderVOApp = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MY_ONE_TEACHER_COURSE_STMT);
			pstmt.setString(1, member_id);
			pstmt.setString(2, teacher_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				course_orderVOApp = new Course_orderVOApp();
				course_orderVOApp.setCourse_order_id(rs.getString("course_order_id"));
				course_orderVOApp.setMember_id(rs.getString("member_id"));
				course_orderVOApp.setTeacher_id(rs.getString("teacher_id"));
				course_orderVOApp.setBuy_time(rs.getInt("buy_time"));
				course_orderVOApp.setSpend_point(rs.getInt("spend_point"));
				course_orderVOApp.setRemain_hour(rs.getInt("remain_hour"));
				course_orderVOApp.setRe_appointment(rs.getInt("re_appointment"));
				
				list.add(course_orderVOApp); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	@Override
	public List<Course_orderVOApp> getMyCourseOrderRecords(String member_id) {
		List<Course_orderVOApp> list = new ArrayList<Course_orderVOApp>();
		Course_orderVOApp course_orderVOApp = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MY_COURSE_STMT);
			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				course_orderVOApp = new Course_orderVOApp();
				course_orderVOApp.setCourse_order_id(rs.getString("course_order_id"));
				course_orderVOApp.setMember_id(rs.getString("member_id"));
				course_orderVOApp.setTeacher_id(rs.getString("teacher_id"));
				course_orderVOApp.setBuy_time(rs.getInt("buy_time"));
				course_orderVOApp.setSpend_point(rs.getInt("spend_point"));
				course_orderVOApp.setRemain_hour(rs.getInt("remain_hour"));
				course_orderVOApp.setRe_appointment(rs.getInt("re_appointment"));
				
				//get teacher name...
				TeacherDAO tdao = new TeacherDAO();
				MemberDAO mdao = new MemberDAO();
				TeacherVO tvo = tdao.findByPrimaryKey(rs.getString("teacher_id"));
				course_orderVOApp.setTeacher_name(mdao.findByPrimaryKey(tvo.getMember_id()).getMem_name());
				
				
				list.add(course_orderVOApp); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public List<Course_orderVO> getMyCourseOrders(String member_id) {
		List<Course_orderVO> list = new ArrayList<Course_orderVO>();
		Course_orderVO course_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MY_COURSE_STMT);
			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				course_orderVO = new Course_orderVO();
				course_orderVO.setCourse_order_id(rs.getString("course_order_id"));
				course_orderVO.setMember_id(rs.getString("member_id"));
				course_orderVO.setTeacher_id(rs.getString("teacher_id"));
				course_orderVO.setBuy_time(rs.getInt("buy_time"));
				course_orderVO.setSpend_point(rs.getInt("spend_point"));
				course_orderVO.setRemain_hour(rs.getInt("remain_hour"));
				course_orderVO.setRe_appointment(rs.getInt("re_appointment"));
				list.add(course_orderVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	@Override
	public void insertApp(Course_orderVO course_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, course_orderVO.getMember_id());
			pstmt.setString(2, course_orderVO.getTeacher_id());
			pstmt.setInt(3, course_orderVO.getBuy_time());
			pstmt.setInt(4, course_orderVO.getSpend_point());
			pstmt.setInt(5, course_orderVO.getRemain_hour());
			pstmt.setInt(6, course_orderVO.getRe_appointment());

			pstmt.executeUpdate();

			
			
			MemberDAO memberDAO = new MemberDAO();
			MemberVO memberVO = memberDAO.findByPrimaryKey(course_orderVO.getMember_id());

			// check if the member has got enough points
			if (memberVO.getMem_point().intValue() >= course_orderVO.getSpend_point()) {
				
				memberVO.setMem_point((double)(memberVO.getMem_point().intValue() - course_orderVO.getSpend_point()));
				
				memberDAO.updatePoint(memberVO, con);
				
				Point_transDAO point_transDAO = new Point_transDAO();
				Point_transVOApp point_transVOApp = new Point_transVOApp();
				
				point_transVOApp.setMember_id(memberVO.getMember_id());
				point_transVOApp.setPoint_record(new Double(-course_orderVO.getSpend_point()));
				point_transVOApp.setTransdate_app(new Timestamp(new java.util.Date().getTime()));
				
				point_transDAO.insertWithCourseApp(point_transVOApp, con);
				
				
			} else {
				
				
				throw new SQLException();
			}
			
			
			
			
			
			con.commit();
			
			// Handle any SQL errors
		} catch (SQLException se) {

			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void insert(Course_orderVO course_orderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, course_orderVO.getMember_id());
			pstmt.setString(2, course_orderVO.getTeacher_id());
			pstmt.setInt(3, course_orderVO.getBuy_time());
			pstmt.setInt(4, course_orderVO.getSpend_point());
			pstmt.setInt(5, course_orderVO.getRemain_hour());
			pstmt.setInt(6, course_orderVO.getRe_appointment());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
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
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, course_order_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, course_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// Course_orderVO 也稱為 Domain objects
				course_orderVO = new Course_orderVO();
				course_orderVO.setCourse_order_id(rs.getString("course_order_id"));
				course_orderVO.setMember_id(rs.getString("member_id"));
				course_orderVO.setTeacher_id(rs.getString("teacher_id"));
				course_orderVO.setBuy_time(rs.getInt("buy_time"));
				course_orderVO.setSpend_point(rs.getInt("spend_point"));
				course_orderVO.setRemain_hour(rs.getInt("remain_hour"));
				course_orderVO.setRe_appointment(rs.getInt("re_appointment"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
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
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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


}
