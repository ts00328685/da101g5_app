//package com.point_trans.model;
//
//import java.util.*;
//import java.sql.*;
//
//import com.point_trans.model.Point_transJDBCDAO;
//import com.point_trans.model.Point_transVO;
//
//public class Point_transJDBCDAO implements Point_transDAO_interface{
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA101G5";
//	String passwd = "123456";
//																							 
//	private static final String INSERT_STMT = 
//			"INSERT INTO point_trans (point_trans_id,member_id,point_record,transdate) VALUES ('PTS'||LPAD(to_char(PTS_SEQ.NEXTVAL), 5, '0'), ?, ?, ?)";
//	private static final String GET_ALL_STMT = 
//			"SELECT point_trans_id,member_id,point_record,to_char(transdate,'yyyy-mm-dd')transdate FROM point_trans order by point_trans_id";
//	private static final String GET_ONE_STMT = 
//			"SELECT point_trans_id,member_id,point_record,to_char(transdate,'yyyy-mm-dd')transdate FROM point_trans where point_trans_id = ?";
//	private static final String DELETE = 
//			"DELETE FROM point_trans where point_trans_id = ?";
//	private static final String UPDATE = 
//			"UPDATE point_trans set member_id=?, point_record=?, transdate=? where point_trans_id = ?";
//
//	@Override
//	public void insert(Point_transVO point_transVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, point_transVO.getMember_id());
//			pstmt.setDouble(2, point_transVO.getPoint_record());
//			pstmt.setDate(3, point_transVO.getTransdate());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void update(Point_transVO point_transVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, point_transVO.getMember_id());
//			pstmt.setDouble(2, point_transVO.getPoint_record());
//			pstmt.setDate(3, point_transVO.getTransdate());
//			pstmt.setString(4, point_transVO.getPoint_trans_id());
//			
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	@Override
//	public void delete(String point_trans_id) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, point_trans_id);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	@Override
//	public Point_transVO findByPrimaryKey(String point_trans_id) {
//
//		Point_transVO point_transVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, point_trans_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// memberVo 也稱為 Domain objects
//				point_transVO = new Point_transVO();
//				point_transVO.setPoint_trans_id(rs.getString("point_trans_id"));
//				point_transVO.setMember_id(rs.getString("member_id"));
//				point_transVO.setPoint_record(rs.getDouble("point_record"));
//				point_transVO.setTransdate(rs.getDate("transdate"));
//
//			}
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return point_transVO;
//	}
//
//	@Override
//	public List<Point_transVO> getAll() {
//		List<Point_transVO> list = new ArrayList<Point_transVO>();
//		Point_transVO point_transVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// point_transVO 也稱為 Domain objects
//				point_transVO = new Point_transVO();
//				point_transVO.setPoint_trans_id(rs.getString("point_trans_id"));
//				point_transVO.setMember_id(rs.getString("Member_id"));
//				point_transVO.setPoint_record(rs.getDouble("point_record"));
//				point_transVO.setTransdate(rs.getDate("transdate"));
//				list.add(point_transVO); // Store the row in the list
//			}
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
//
//	public static void main(String[] args) {
//
//		Point_transJDBCDAO dao = new Point_transJDBCDAO();
//
//		// 新增
//		Point_transVO point_transVO1 = new Point_transVO();
////		point_transVO1.setPoint_trans_id("PTS00001");
//		point_transVO1.setMember_id("M00003");
//		point_transVO1.setPoint_record(new Double(2000));
//		point_transVO1.setTransdate(java.sql.Date.valueOf("2019-01-01"));
//		dao.insert(point_transVO1);
//
//		// 修改
//		Point_transVO point_transVO2 = new Point_transVO();
//		point_transVO2.setPoint_trans_id("PTS00003");
//		point_transVO2.setMember_id("M00002");
//		point_transVO2.setPoint_record(new Double(1000));
//		point_transVO2.setTransdate(java.sql.Date.valueOf("2019-01-01"));
//		dao.update(point_transVO2);
//
//		// 刪除
//		dao.delete("PTS00013");
//
//		// 查詢
//		Point_transVO point_transVO3 = dao.findByPrimaryKey("PTS00001");
//		System.out.print(point_transVO3.getPoint_trans_id() + ",");
//		System.out.print(point_transVO3.getMember_id() + ",");
//		System.out.print(point_transVO3.getPoint_record() + ",");
//		System.out.print(point_transVO3.getTransdate());
//		System.out.println("----------------------------------");
//
//		// 查詢
//		List<Point_transVO> list = dao.getAll();
//		for (Point_transVO aPoint_trans : list) {
//			System.out.print(aPoint_trans.getPoint_trans_id() + ",");
//			System.out.print(aPoint_trans.getMember_id() + ",");
//			System.out.print(aPoint_trans.getPoint_record() + ",");
//			System.out.print(aPoint_trans.getTransdate());
//			System.out.println();
//		}
//	}
//	
//}
