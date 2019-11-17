package com.Course_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Course_reportJDBCDAO implements Course_reportDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO course_report (course_report_id,teacher_id,member_id,report_text,report_state) VALUES ('CR'||LPAD(to_char(COURSE_REPORT_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT course_report_id,teacher_id,member_id,report_text,report_state FROM course_report order by course_report_id desc";
		private static final String GET_ONE_STMT = 
			"SELECT course_report_id,teacher_id,member_id,report_text,report_state FROM course_report where course_report_id = ?";
		private static final String DELETE = 
			"DELETE FROM course_report where course_report_id = ?";
		private static final String UPDATE = 
			"UPDATE course_report set teacher_id=?, member_id=?, report_text=?, report_state=? where course_report_id = ?";

		@Override
		public void insert(Course_reportVO course_reportVO) {
			

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, course_reportVO.getTeacher_id());
				pstmt.setString(2, course_reportVO.getMember_id());
				pstmt.setString(3, course_reportVO.getReport_text());
				pstmt.setInt(4, course_reportVO.getReport_state());
				

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
		public void update(Course_reportVO course_reportVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				
				pstmt.setString(1, course_reportVO.getTeacher_id());
				pstmt.setString(2, course_reportVO.getMember_id());
				pstmt.setString(3, course_reportVO.getReport_text());
				pstmt.setInt(4, course_reportVO.getReport_state());
				pstmt.setString(5, course_reportVO.getCourse_report_id());

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
		public void delete(String course_report_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1,course_report_id);

				pstmt.executeUpdate();

				// Handle any driver errors
			}catch (ClassNotFoundException e) {
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
		public List<Course_reportVO> getAll() {
			List<Course_reportVO> list = new ArrayList<Course_reportVO>();
			Course_reportVO course_reportVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// course_reportVO 也稱為 Domain objects
					course_reportVO = new Course_reportVO();
					course_reportVO.setCourse_report_id(rs.getString("course_report_id"));
					course_reportVO.setTeacher_id(rs.getString("teacher_id"));
					course_reportVO.setMember_id(rs.getString("member_id"));
					course_reportVO.setReport_text(rs.getString("report_text"));
					course_reportVO.setReport_state(rs.getInt("report_state"));
					
					list.add(course_reportVO); // Store the row in the list
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
			return list;
		}

		
		@Override
		public Course_reportVO findByPrimaryKey(String course_report_id) {
			Course_reportVO course_reportVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, course_report_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// Course_reportVo 也稱為 Domain objects
					course_reportVO = new Course_reportVO();
					course_reportVO.setCourse_report_id(rs.getString("course_report_id"));
					course_reportVO.setTeacher_id(rs.getString("teacher_id"));
					course_reportVO.setMember_id(rs.getString("member_id"));
					course_reportVO.setReport_text(rs.getString("report_text"));
					course_reportVO.setReport_state(rs.getInt("report_state"));
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
			return course_reportVO;
		}
		
//		public static void main(String[] args) {
//
//			Course_reportJDBCDAO dao = new Course_reportJDBCDAO();
//
//			// 新增
//			Course_reportVO course_reportVO1 = new Course_reportVO();
////			course_reportVO1.setCourse_report_id("CR00001");
//			course_reportVO1.setTeacher_id("T00001");
//			course_reportVO1.setMember_id("M00001");
//			course_reportVO1.setReport_text("dd");
//			course_reportVO1.setReport_state(0);
//			dao.insert(course_reportVO1);
//
//			// 修改
//			Course_reportVO course_reportVO2 = new Course_reportVO();
//			course_reportVO2.setCourse_report_id("CR00001");
//			course_reportVO2.setTeacher_id("T00001");
//			course_reportVO2.setMember_id("M00001");
//			course_reportVO2.setReport_text("dd");
//			course_reportVO2.setReport_state(1);
//			dao.update(course_reportVO2);
//
//			// 刪除
////			dao.delete("CR00001");
//
//			// 查詢
//			Course_reportVO course_reportVO3 = dao.findByPrimaryKey("CR00001");
//			System.out.print(course_reportVO3.getTeacher_id() + ",");
//			System.out.print(course_reportVO3.getMember_id() + ",");
//			System.out.print(course_reportVO3.getReport_text() + ",");
//			System.out.print(course_reportVO3.getReport_state() + ",");
//			System.out.print(course_reportVO3.getCourse_report_id() + ",");
//			
//			System.out.println("---------------------");
//
//			// 查詢
//			List<Course_reportVO> list = dao.getAll();
//			for (Course_reportVO aCourse_report : list) {
//				System.out.println(course_reportVO3.getTeacher_id() + ",");
//				System.out.println(course_reportVO3.getMember_id() + ",");
//				System.out.println(course_reportVO3.getReport_text() + ",");
//				System.out.println(course_reportVO3.getReport_state() + ",");
//				System.out.println(course_reportVO3.getCourse_report_id() + ",");
//				System.out.println();
//			}
//		}

}
