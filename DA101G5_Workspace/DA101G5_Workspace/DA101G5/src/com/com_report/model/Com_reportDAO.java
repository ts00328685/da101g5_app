package com.com_report.model;

import java.util.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Com_reportDAO implements Com_reportDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "Insert into com_report "
			+ "(com_report_id, com_id, r_des, trandate, r_status) "
			+ " values ('CR'||LPAD(to_char(cr_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT * FROM com_report order by com_report_id";
	private static final String GET_ONE_STMT = "SELECT * FROM com_report where com_report_id = ?";
	private static final String DELETE = "DELETE FROM com_report where com_report_id = ?";
	private static final String UPDATE = "UPDATE com_report set com_id=?, r_des=?, "
			+ "trandate=?, r_status=? where com_report_id = ?";

// JNDI
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) {

		Com_reportDAO dao = new Com_reportDAO();

		// 新增
		Com_reportVO com_reportVO1 = new Com_reportVO();
		com_reportVO1.setCom_id("CM00001");
		com_reportVO1.setR_des("圖文不符");
		//存日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = "2019-6-22 01:01:01";
		java.sql.Timestamp sqlTimestamp;
		try {
			sqlTimestamp = new java.sql.Timestamp(sdf.parse(date).getTime());
			
			com_reportVO1.setTrandate(sqlTimestamp);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		com_reportVO1.setR_status(0);
		
		dao.insert(com_reportVO1);
		System.out.println("新增成功!");

		
		// 修改
		Com_reportVO com_reportVO2 = new Com_reportVO();
		
		com_reportVO2.setCom_report_id("CR00001");
		com_reportVO2.setCom_id("CM00002");
		com_reportVO2.setR_des("猥褻");
		
		//存日期
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2 = "2019-6-25 02:02:02";
		java.sql.Timestamp sqlTimestamp2;
		try {
			sqlTimestamp2 = new java.sql.Timestamp(sdf2.parse(date2).getTime());
			
		com_reportVO2.setTrandate(sqlTimestamp2);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		com_reportVO2.setR_status(1);
		
		dao.update(com_reportVO2);
		System.out.println("修改成功!");

		// 刪除
		dao.delete("CR00002");
		System.out.println("刪除成功!");

		// 查詢一筆
		Com_reportVO com_reportVO3 = dao.findByPrimaryKey("CR00001");
		System.out.print(com_reportVO3.getCom_report_id() + ", ");
		System.out.print(com_reportVO3.getCom_id() + ", ");
		System.out.print(com_reportVO3.getR_des() + ", ");
		System.out.print(com_reportVO3.getTrandate() + ", ");
		System.out.println(com_reportVO3.getR_status());
		System.out.println("---------------------");

		// 查詢全部
		List<Com_reportVO> list = dao.getAll();
		for (Com_reportVO aCR : list) {
			System.out.print(aCR.getCom_report_id() + ", ");
			System.out.print(aCR.getCom_id() + ", ");
			System.out.print(aCR.getR_des() + ", ");
			System.out.print(aCR.getTrandate() + ", ");
			System.out.print(aCR.getR_status());
			System.out.println();
		}
	}

	@Override
	public void insert(Com_reportVO com_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// JNDI
			// con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, com_reportVO.getCom_id());
			pstmt.setString(2, com_reportVO.getR_des());
			pstmt.setTimestamp(3, com_reportVO.getTrandate());
			pstmt.setInt(4, com_reportVO.getR_status());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("A class error occured. " + e.getMessage());
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
	public void update(Com_reportVO com_reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// JNDI
			// con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, com_reportVO.getCom_id());
			pstmt.setString(2, com_reportVO.getR_des());
			pstmt.setTimestamp(3, com_reportVO.getTrandate());
			pstmt.setInt(4, com_reportVO.getR_status());
			pstmt.setString(5, com_reportVO.getCom_report_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("A class error occured. " + e.getMessage());
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
	public void delete(String com_report_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// JNDI
			// con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, com_report_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("A class error occured. " + e.getMessage());
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
	public Com_reportVO findByPrimaryKey(String com_report_id) {

		Com_reportVO com_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// JNDI
			// con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, com_report_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// comVo Domain objects
				com_reportVO = new Com_reportVO();
				
				com_reportVO.setCom_report_id(rs.getString("com_report_id"));
				com_reportVO.setCom_id(rs.getString("com_id"));
				com_reportVO.setR_des(rs.getString("r_des"));
				com_reportVO.setTrandate(rs.getTimestamp("trandate"));
				com_reportVO.setR_status(rs.getInt("r_status"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("A class error occured. " + e.getMessage());
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
		return com_reportVO;
	}

	@Override
	public List<Com_reportVO> getAll() {
		List<Com_reportVO> list = new ArrayList<Com_reportVO>();
		Com_reportVO com_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// JNDI
			// con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// com_reportVO Domain objects
				com_reportVO = new Com_reportVO();
				com_reportVO.setCom_report_id(rs.getString("com_report_id"));
				com_reportVO.setCom_id(rs.getString("com_id"));
				com_reportVO.setR_des(rs.getString("r_des"));
				com_reportVO.setTrandate(rs.getTimestamp("trandate"));
				com_reportVO.setR_status(rs.getInt("r_status"));
				list.add(com_reportVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("A class error occured. " + e.getMessage());
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