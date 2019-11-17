package com.Time_order.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Course_order.model.Course_orderDAO;
import com.Course_order.model.Course_orderVO;
import com.Language.model.LanguageDAO;
import com.Language.model.LanguageVO;
import com.Sort_course.model.Sort_courseDAO;
import com.Sort_course.model.Sort_courseVO;
import com.Teacher.model.TeacherDAO;
import com.Teacher.model.TeacherVO;
import com.member.model.MemberDAO;

public class Time_orderDAO implements Time_orderDAO_interface {
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
	private static final String INSERT_STMT = "INSERT INTO time_order (time_order_id,teacher_id,course_order_id,language_id,sort_course_id,c_state,c_judge,start_time,end_time) VALUES ('TO'||LPAD(to_char(TIME_ORDER_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?,?,?)";
	private static final String GET_ALL_STMT = "SELECT time_order_id,teacher_id,course_order_id,language_id,sort_course_id,c_state,c_judge,to_char(start_time,'yyyy-mm-dd hh:mm:ss')start_time,to_char(end_time,'yyyy-mm-dd hh:mm:ss') end_time FROM time_order order by time_order_id desc";
	private static final String GET_ONE_STMT = "SELECT time_order_id,teacher_id,course_order_id,language_id,sort_course_id,c_state,c_judge,to_char(start_time,'yyyy-mm-dd hh:mm:ss')start_time,to_char(end_time,'yyyy-mm-dd hh:mm:ss') end_time FROM time_order where time_order_id = ?";
	private static final String DELETE = "DELETE FROM time_order where time_order_id = ?";
	private static final String UPDATE = "UPDATE time_order set teacher_id=?, course_order_id=?, language_id=?, sort_course_id=?, c_state=?, c_judge=?,start_time=?,end_time=? where time_order_id = ?";

	private static final String GET_ALL_JOIN_COURSE_ORDER = "select * from TIME_ORDER LEFT JOIN COURSE_ORDER ON TIME_ORDER.COURSE_ORDER_ID = course_order.course_order_id where member_id = ? order by time_order_id desc";

	private static final String GET_ALL_JOIN_COURSE_ORDER_CHECK_TIME = "select * from TIME_ORDER LEFT JOIN COURSE_ORDER ON TIME_ORDER.COURSE_ORDER_ID = course_order.course_order_id "
			+ " where member_id = ? AND (start_time BETWEEN to_date(?, 'YYYY-MM-DD HH24:MI:SS') AND to_date(?, 'YYYY-MM-DD HH24:MI:SS') - 1/24/60/60 "
			+ " OR end_time BETWEEN to_date(?, 'YYYY-MM-DD HH24:MI:SS') + 1/24/60/60 AND to_date(?, 'YYYY-MM-DD HH24:MI:SS')) "
			+ " order by time_order_id desc";

	private static final String UPDATE_TO_CONFIRM_APPOINTMENT = "UPDATE time_order set c_state=? where time_order_id = ?";
	private static final String UPDATE_TO_JUDGE = "UPDATE time_order set c_judge=? where time_order_id = ?";

	private static final String UPDATE_COURSE_ORDER = "UPDATE course_order set remain_hour=? where course_order_id = ?";

	private static final String UPDATE_TEACHER_EVALUATION = "UPDATE teacher set appraisal_accum=?, appraisal_count=? where teacher_id = ?";

	@Override
	public void updateTOState(String course_order_id, String time_order_id, String total_hour, String c_state,
			String c_judge) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);

			Time_orderDAO tdao = new Time_orderDAO();

			if (c_state.equals("1")) {
				Time_orderVO tvo = tdao.findByPrimaryKey(time_order_id);
				if (tvo.getC_state() != 0) {
					throw new SQLException();
				}
				pstmt = con.prepareStatement(UPDATE_TO_CONFIRM_APPOINTMENT);
				pstmt.setInt(1, Integer.valueOf(c_state));
				pstmt.setString(2, time_order_id);
				pstmt.executeUpdate();
				pstmt.close();
			}

			// update CO if it's coming from course confirmation
			if (c_state.equals("2")) {
				Time_orderVO tvo = tdao.findByPrimaryKey(time_order_id);
				if (tvo.getC_state() == 2) {
					throw new SQLException();
				}

				pstmt = con.prepareStatement(UPDATE_TO_CONFIRM_APPOINTMENT);
				pstmt.setInt(1, Integer.valueOf(c_state));
				pstmt.setString(2, time_order_id);
				pstmt.executeUpdate();
				pstmt.close();

				pstmt = con.prepareStatement(UPDATE_COURSE_ORDER);

				Course_orderDAO coDao = new Course_orderDAO();
				Course_orderVO coVO = coDao.findByPrimaryKey(course_order_id);
				int remainHour = coVO.getRemain_hour();
				int hourToBeDeduceted = Integer.valueOf(total_hour);
				int newHour = remainHour - hourToBeDeduceted;
				if (newHour < 0) {
					throw new SQLException();
				}
				pstmt.setInt(1, newHour);
				pstmt.setString(2, course_order_id);
				System.out.println("updateTO new Hour = " + newHour);
				pstmt.executeUpdate();
				pstmt.close();

				pstmt = con.prepareStatement(UPDATE_TO_JUDGE);
				pstmt.setDouble(1, Double.valueOf(c_judge));
				pstmt.setString(2, time_order_id);
				pstmt.executeUpdate();
				pstmt.close();

				pstmt = con.prepareStatement(UPDATE_TEACHER_EVALUATION);

				TeacherDAO teacherDAO = new TeacherDAO();
				TeacherVO teacherVO = teacherDAO.findByPrimaryKey(tvo.getTeacher_id());
				Integer newAccu = teacherVO.getAppraisal_accum() + Float.valueOf(c_judge).intValue();

				pstmt.setInt(1, newAccu);
				pstmt.setInt(2, teacherVO.getAppraisal_count() + 1);
				pstmt.setString(3, tvo.getTeacher_id());
				pstmt.executeUpdate();
			}

			con.commit();

			// Handle any driver errors
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

	}

	@Override
	public List<Time_orderVOApp> getAllJoinCourseOrder(String member_id) {
		List<Time_orderVOApp> list = new ArrayList<Time_orderVOApp>();
		Time_orderVOApp time_orderVOApp = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_JOIN_COURSE_ORDER);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// time_orderVO 也稱為 Domain objects
				time_orderVOApp = new Time_orderVOApp();
				time_orderVOApp.setTime_order_id(rs.getString("time_order_id"));
				time_orderVOApp.setTeacher_id(rs.getString("teacher_id"));
				time_orderVOApp.setCourse_order_id(rs.getString("course_order_id"));
				time_orderVOApp.setLanguage_id(rs.getString("language_id"));
				time_orderVOApp.setSort_course_id(rs.getString("sort_course_id"));
				time_orderVOApp.setC_state(rs.getInt("c_state"));
				time_orderVOApp.setC_judge(rs.getInt("c_judge"));
				time_orderVOApp.setStart_time(rs.getTimestamp("start_time"));
				time_orderVOApp.setEnd_time(rs.getTimestamp("end_time"));

				TeacherDAO tdao = new TeacherDAO();
				MemberDAO mdao = new MemberDAO();
				TeacherVO tvo = tdao.findByPrimaryKey(rs.getString("teacher_id"));
				time_orderVOApp.setTeacher_name(mdao.findByPrimaryKey(tvo.getMember_id()).getMem_name());

				time_orderVOApp.setStudent_member_id(member_id);

				time_orderVOApp.setStudent_name(mdao.findByPrimaryKey(member_id).getMem_name());

				LanguageDAO ldao = new LanguageDAO();
				Sort_courseDAO sdao = new Sort_courseDAO();
				LanguageVO lvo = ldao.findByPrimaryKey(rs.getString("language_id"));
				Sort_courseVO svo = sdao.findByPrimaryKey(rs.getString("sort_course_id"));
				time_orderVOApp.setLanguage(lvo.getLanguage());
				time_orderVOApp.setCourse(svo.getSort_course());

				list.add(time_orderVOApp); // Store the row in the list
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
	public String insert(Time_orderVO time_orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String output = null;

		try {

			con = ds.getConnection();

			Time_orderVOApp tvoapp = (Time_orderVOApp) time_orderVO;
			pstmt = con.prepareStatement(GET_ALL_JOIN_COURSE_ORDER_CHECK_TIME);
			pstmt.setString(1, tvoapp.getStudent_member_id());
			pstmt.setString(2,
					tvoapp.getStart_time().toString().substring(0, tvoapp.getStart_time().toString().length() - 2));
			System.out.println(
					tvoapp.getStart_time().toString().substring(0, tvoapp.getStart_time().toString().length() - 2));
			pstmt.setString(3,
					tvoapp.getEnd_time().toString().substring(0, tvoapp.getEnd_time().toString().length() - 2));
			pstmt.setString(4,
					tvoapp.getStart_time().toString().substring(0, tvoapp.getStart_time().toString().length() - 2));
			pstmt.setString(5,
					tvoapp.getEnd_time().toString().substring(0, tvoapp.getEnd_time().toString().length() - 2));

			rs = pstmt.executeQuery();
			if (rs.next()) {
				output = "repeated";
			}
			System.out.println(output);

			if (output==null) {
				pstmt.close();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, time_orderVO.getTeacher_id());
				pstmt.setString(2, time_orderVO.getCourse_order_id());
				pstmt.setString(3, time_orderVO.getLanguage_id());
				pstmt.setString(4, time_orderVO.getSort_course_id());
				pstmt.setInt(5, time_orderVO.getC_state());
				pstmt.setInt(6, time_orderVO.getC_judge());
				pstmt.setTimestamp(7, time_orderVO.getStart_time());
				pstmt.setTimestamp(8, time_orderVO.getEnd_time());
				pstmt.executeUpdate();
			}

			

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
		return output;

	}

	@Override
	public void update(Time_orderVO time_orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, time_orderVO.getTeacher_id());
			pstmt.setString(2, time_orderVO.getCourse_order_id());
			pstmt.setString(3, time_orderVO.getLanguage_id());
			pstmt.setString(4, time_orderVO.getSort_course_id());
			pstmt.setInt(5, time_orderVO.getC_state());
			pstmt.setInt(6, time_orderVO.getC_judge());
			pstmt.setTimestamp(7, time_orderVO.getStart_time());
			pstmt.setTimestamp(8, time_orderVO.getEnd_time());
			pstmt.setString(9, time_orderVO.getTime_order_id());

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
	public void delete(String time_order_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, time_order_id);

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
	public Time_orderVO findByPrimaryKey(String time_order_id) {
		Time_orderVO time_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, time_order_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				time_orderVO = new Time_orderVO();
				time_orderVO.setTime_order_id(rs.getString("time_order_id"));
				time_orderVO.setTeacher_id(rs.getString("teacher_id"));
				time_orderVO.setCourse_order_id(rs.getString("course_order_id"));
				time_orderVO.setLanguage_id(rs.getString("language_id"));
				time_orderVO.setSort_course_id(rs.getString("sort_course_id"));
				time_orderVO.setC_state(rs.getInt("c_state"));
				time_orderVO.setC_judge(rs.getInt("c_judge"));
				time_orderVO.setStart_time(rs.getTimestamp("start_time"));
				time_orderVO.setEnd_time(rs.getTimestamp("end_time"));
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
		return time_orderVO;
	}

	@Override
	public List<Time_orderVO> getAll() {
		List<Time_orderVO> list = new ArrayList<Time_orderVO>();
		Time_orderVO time_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// time_orderVO 也稱為 Domain objects
				time_orderVO = new Time_orderVO();
				time_orderVO.setTime_order_id(rs.getString("time_order_id"));
				time_orderVO.setTeacher_id(rs.getString("teacher_id"));
				time_orderVO.setCourse_order_id(rs.getString("course_order_id"));
				time_orderVO.setLanguage_id(rs.getString("language_id"));
				time_orderVO.setSort_course_id(rs.getString("sort_course_id"));
				time_orderVO.setC_state(rs.getInt("c_state"));
				time_orderVO.setC_judge(rs.getInt("c_judge"));
				time_orderVO.setStart_time(rs.getTimestamp("start_time"));
				time_orderVO.setEnd_time(rs.getTimestamp("end_time"));
				list.add(time_orderVO); // Store the row in the list
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

	public static void main(String[] args) {
		Time_orderJDBCDAO dao = new Time_orderJDBCDAO();

//	// 新增
//	Time_orderVO time_orderVO1 = new Time_orderVO();
//	time_orderVO1.setTeacher_id("T00005");
//	time_orderVO1.setCourse_order_id("CO00001");
//    time_orderVO1.setLanguage_id("L00001");
//	time_orderVO1.setSort_course_id("SC00001");
//	time_orderVO1.setC_state(1);
//	time_orderVO1.setC_judge(5);
//	time_orderVO1.setStart_time(java.sql.Date.valueOf("2005-01-01"));
//	time_orderVO1.setEnd_time(java.sql.Date.valueOf("2005-01-01"));
//	dao.insert(time_orderVO1);
//	System.out.println("新增成功");
////	
//	
//	// 修改
//	Time_orderVO time_orderVO2 = new Time_orderVO();
//	time_orderVO2.setTeacher_id("TO00001");
//	time_orderVO2.setTeacher_id("T00005");
//	time_orderVO2.setCourse_order_id("CO00001");
//    time_orderVO2.setLanguage_id("L00001");
//	time_orderVO2.setSort_course_id("SC00001");
//	time_orderVO2.setC_state(1);
//	time_orderVO2.setC_judge(5);
//	time_orderVO2.setStart_time(java.sql.Date.valueOf("2006-01-01"));
//	time_orderVO2.setEnd_time(java.sql.Date.valueOf("2006-01-01"));
//	
//	dao.update(time_orderVO2);
//	
////	// 刪除
////	dao.delete("TO00001");
//	
//	
		// 查詢
		List<Time_orderVO> list = dao.getAll();
		for (Time_orderVO time_orderVO : list) {
			System.out.println(time_orderVO.getTime_order_id());
			System.out.println(time_orderVO.getTeacher_id());
			System.out.println(time_orderVO.getCourse_order_id());
			System.out.println(time_orderVO.getLanguage_id());
			System.out.println(time_orderVO.getSort_course_id());
			System.out.println(time_orderVO.getC_state());
			System.out.println(time_orderVO.getC_judge());
			System.out.println(time_orderVO.getStart_time());
			System.out.println(time_orderVO.getEnd_time());
			System.out.println("查詢成功");
		}
	}

}
