package com.Teacher_ad.model;


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


public class Teacher_adDAO implements Teacher_adDAO_interface{
	
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
				"INSERT INTO teacher_ad (teacher_ad_id,teacher_id,ad_time,ad_start,ad_state) VALUES ('TA'||LPAD(to_char(TEACHER_AD_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?)";
			private static final String GET_ALL_STMT = 
				"SELECT teacher_ad_id,teacher_id,ad_time,to_char(ad_start,'yyyy-mm-dd HH24:MI:ss')ad_start,ad_state FROM teacher_ad order by teacher_ad_id desc";
			private static final String GET_ONE_STMT = 
				"SELECT teacher_ad_id,teacher_id,ad_time,to_char(ad_start,'yyyy-mm-dd HH24:MI:ss')ad_start,ad_state FROM teacher_ad where teacher_ad_id = ?";
			private static final String DELETE = 
				"DELETE FROM teacher_ad where teacher_ad_id = ?";
			private static final String UPDATE = 
				"UPDATE teacher_ad set teacher_id=?, ad_time=?, ad_start=?, ad_state=? where teacher_ad_id = ?";

	@Override
	public void insert(Teacher_adVO teacher_adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, teacher_adVO.getTeacher_id());
			pstmt.setInt(2, teacher_adVO.getAd_time());
			pstmt.setTimestamp(3, (Timestamp) teacher_adVO.getAd_start());
			pstmt.setDouble(4, teacher_adVO.getAd_state());


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
	public void update(Teacher_adVO teacher_adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, teacher_adVO.getTeacher_id());
			pstmt.setInt(2, teacher_adVO.getAd_time());
			pstmt.setTimestamp(3, (Timestamp) teacher_adVO.getAd_start());
			pstmt.setInt(4, teacher_adVO.getAd_state());
			pstmt.setString(5, teacher_adVO.getTeacher_ad_id());
			
//System.out.println("DAO789");
//System.out.println(teacher_adVO.getAd_state());
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
	public void delete(String teacher_ad_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, teacher_ad_id);

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
	public Teacher_adVO findByPrimaryKey(String teacher_ad_id) {
		Teacher_adVO teacher_adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, teacher_ad_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 銋迂� Domain objects
				teacher_adVO = new Teacher_adVO();
				teacher_adVO.setTeacher_ad_id(rs.getString("teacher_ad_id"));
				teacher_adVO.setTeacher_id(rs.getString("teacher_id"));
				teacher_adVO.setAd_time(rs.getInt("ad_time"));
				teacher_adVO.setAd_start(rs.getTimestamp("ad_start"));
				teacher_adVO.setAd_state(rs.getInt("ad_state"));
				
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// teacher_adVO 銋迂� Domain objects
				teacher_adVO = new Teacher_adVO();
				teacher_adVO.setTeacher_ad_id(rs.getString("teacher_ad_id"));
				teacher_adVO.setTeacher_id(rs.getString("teacher_id"));
				teacher_adVO.setAd_time(rs.getInt("ad_time"));
				teacher_adVO.setAd_start(rs.getTimestamp("ad_start"));
				teacher_adVO.setAd_state(rs.getInt("ad_state"));
				list.add(teacher_adVO); // Store the row in the list
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

	

}
