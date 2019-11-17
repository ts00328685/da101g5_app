package com.Course_report.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Course_reportDAO implements Course_reportDAO_interface{
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

					con = ds.getConnection();
					pstmt = con.prepareStatement(INSERT_STMT);

					pstmt.setString(1, course_reportVO.getTeacher_id());
					pstmt.setString(2, course_reportVO.getMember_id());
					pstmt.setString(3, course_reportVO.getReport_text());
					pstmt.setInt(4, course_reportVO.getReport_state());
					

					pstmt.executeUpdate();

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
			public void update(Course_reportVO course_reportVO) {

				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(UPDATE);

					pstmt.setString(1, course_reportVO.getTeacher_id());
					pstmt.setString(2, course_reportVO.getMember_id());
					pstmt.setString(3, course_reportVO.getReport_text());
					pstmt.setInt(4, course_reportVO.getReport_state());
					pstmt.setString(5, course_reportVO.getCourse_report_id());

					pstmt.executeUpdate();

					// Handle any driver errors
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
			public void delete(String course_report_id) {

				Connection con = null;
				PreparedStatement pstmt = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(DELETE);

					pstmt.setString(1,course_report_id);

					pstmt.executeUpdate();

					// Handle any driver errors
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

					con = ds.getConnection();
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

			
			@Override
			public Course_reportVO findByPrimaryKey(String course_report_id) {
				Course_reportVO course_reportVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ONE_STMT);

					pstmt.setString(1, course_report_id);

					rs = pstmt.executeQuery();

					while (rs.next()) {
						// empVo 也稱為 Domain objects
						course_reportVO = new Course_reportVO();
						course_reportVO.setCourse_report_id(rs.getString("course_report_id"));
						course_reportVO.setTeacher_id(rs.getString("teacher_id"));
						course_reportVO.setMember_id(rs.getString("member_id"));
						course_reportVO.setReport_text(rs.getString("report_text"));
						course_reportVO.setReport_state(rs.getInt("report_state"));
					}

					// Handle any driver errors
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
				return course_reportVO;
			}

	
}
