//package com.point_trans.model;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.*;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import com.message.model.MessageVO;
//
//public class Point_transJNDIDAO implements Point_transDAO_interface {
//
//	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
//		private static DataSource ds = null;
//		static {
//			try {
//				Context ctx = new InitialContext();
//				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//		}
//
//		private static final String INSERT_STMT = 
//				"INSERT INTO point_trans (point_trans_id,member_id,point_record,transdate) VALUES (PTS_SEQ.NEXTVAL, ?, ?, ?, ?,)";
//		private static final String GET_ALL_STMT = 
//				"SELECT point_trans_id,member_id,point_record,to_char(transdate,'yyyy-mm-dd')transdate FROM point_trans order by point_trans_id";
//		private static final String GET_ONE_STMT = 
//				"SELECT point_trans_id,member_id,point_record,to_char(transdate,'yyyy-mm-dd')transdate FROM point_trans where point_trans_id = ?";
//		private static final String DELETE = 
//				"DELETE FROM point_trans where point_trans_id = ?";
//		private static final String UPDATE = 
//				"UPDATE point_trans set member_id=? ,point_record=? ,transdate=? where point_trans_id = ?";
//
//		@Override
//		public void insert(Point_transVO point_transVO) {
//
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(INSERT_STMT);
//
//				pstmt.setString(1, point_transVO.getMember_id());
//				pstmt.setDouble(2, point_transVO.getPoint_record());
//				pstmt.setDate(3, point_transVO.getTransdate());
//
//				pstmt.executeUpdate();
//
//				// Handle any SQL errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured." + se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//		}
//
//		@Override
//		public void update(Point_transVO point_transVO) {
//
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(UPDATE);
//
//				pstmt.setString(1, point_transVO.getMember_id());
//				pstmt.setDouble(2, point_transVO.getPoint_record());
//				pstmt.setDate(3, point_transVO.getTransdate());
//				pstmt.setString(4, point_transVO.getPoint_trans_id());
//
//				pstmt.executeUpdate();
//
//				// Handle any driver errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured." + se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//
//		}
//
//		@Override
//		public void delete(String point_trans_id) {
//
//			Connection con = null;
//			PreparedStatement pstmt = null;
//
//			try {
//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(UPDATE);
//
//				pstmt.setString(1, point_trans_id);
//
//				pstmt.executeUpdate();
//
//				// Handle any driver errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured." + se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//
//		}
//
//		@Override
//		public Point_transVO findByPrimaryKey(String point_trans_id) {
//
//			Point_transVO point_transVO = null;
//			Connection con = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//
//			try {
//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(GET_ONE_STMT);
//
//				pstmt.setString(1, point_trans_id);
//
//				rs = pstmt.executeQuery();
//
//				while (rs.next()) {
//					// point_transVO 也稱為 Domain objects
//					point_transVO = new Point_transVO();
//					point_transVO.setPoint_trans_id(rs.getString("point_trans_id"));
//					point_transVO.setMember_id(rs.getString("member_id"));
//					point_transVO.setPoint_record(rs.getDouble("point_record"));
//					point_transVO.setTransdate(rs.getDate("transdate"));
//					
//				}
//
//				// Handle any driver errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. " + se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//			return point_transVO;
//		}
//
//		@Override
//		public List<Point_transVO> getAll() {
//			List<Point_transVO> list = new ArrayList<Point_transVO>();
//			Point_transVO point_transVO = null;
//
//			Connection con = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//
//			try {
//
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(GET_ALL_STMT);
//				rs = pstmt.executeQuery();
//
//				while (rs.next()) {
//					// memberVo 也稱為 Domain objects
//					point_transVO = new Point_transVO();
//					point_transVO.setPoint_trans_id(rs.getString("point_trans_id"));
//					point_transVO.setMember_id(rs.getString("member_id"));
//					point_transVO.setPoint_record(rs.getDouble("point_record"));
//					point_transVO.setTransdate(rs.getDate("transdate"));
//					list.add(point_transVO);// Store the row in the list
//				}
//				// Handle any driver errors
//			} catch (SQLException se) {
//				throw new RuntimeException("A database error occured. " + se.getMessage());
//				// Clean up JDBC resources
//			} finally {
//				if (rs != null) {
//					try {
//						rs.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (pstmt != null) {
//					try {
//						pstmt.close();
//					} catch (SQLException se) {
//						se.printStackTrace(System.err);
//					}
//				}
//				if (con != null) {
//					try {
//						con.close();
//					} catch (Exception e) {
//						e.printStackTrace(System.err);
//					}
//				}
//			}
//			return list;
//		}
//
//}
