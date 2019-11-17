package com.Teacher_course_list.model;

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


public class Teacher_course_listDAO implements Teacher_course_listDAO_interface{
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, teacher_course_listVO.getSort_course_id());
			pstmt.setString(2, teacher_course_listVO.getLanguage_id());
			pstmt.setString(3, teacher_course_listVO.getTeacher_id());
			pstmt.setInt(4, teacher_course_listVO.getCourse_state());
			pstmt.setInt(5, teacher_course_listVO.getCourse_appraisal_accum());
			pstmt.setInt(6, teacher_course_listVO.getCourse_appraisal_count());
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
	public void update(Teacher_course_listVO teacher_course_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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
	public void delete(String teacher_course_list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, teacher_course_list);

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
	public Teacher_course_listVO findByPrimaryKey(String teacher_course_list) {
		Teacher_course_listVO teacher_course_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
		} 
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} 
		finally {
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
