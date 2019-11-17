package com.subscribe.model;

import java.util.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SubscribeDAO implements SubscribeDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "Insert into subscribe "
			+ "(sub_id, com_id, b_member_id, s_member_id, com_price, trandate, buyer_evaluation_score) "
			+ " values ('SU'||LPAD(to_char(sub_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT * FROM subscribe order by sub_id";
	private static final String GET_ONE_STMT = "SELECT * FROM subscribe where sub_id = ?";
	private static final String DELETE = "DELETE FROM subscribe where sub_id = ?";
	private static final String UPDATE = "UPDATE subscribe set com_id=?, b_member_id=?, "
			+ "s_member_id=?, com_price=?, trandate=?, buyer_evaluation_score=? where sub_id = ?";

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

		SubscribeDAO dao = new SubscribeDAO();

		// 新增
		SubscribeVO subscribeVO1 = new SubscribeVO();
		subscribeVO1.setCom_id("CM00001");
		subscribeVO1.setB_member_id("M00011");
		subscribeVO1.setS_member_id("M00001");
		subscribeVO1.setCom_price(100);
		
		//存日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = "2019-6-22 01:01:01";
		java.sql.Timestamp sqlTimestamp;
		try {
			sqlTimestamp = new java.sql.Timestamp(sdf.parse(date).getTime());
			
			subscribeVO1.setTrandate(sqlTimestamp);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		subscribeVO1.setBuyer_evaluation_score(5);
		
		dao.insert(subscribeVO1);
		System.out.println("新增成功!");

		
		// 修改
		SubscribeVO subscribeVO2 = new SubscribeVO();
		
		subscribeVO2.setSub_id("SU00002");
		subscribeVO2.setCom_id("CM00002");
		subscribeVO2.setB_member_id("M00012");
		subscribeVO2.setS_member_id("M00001");
		subscribeVO2.setCom_price(200);
		
		//存日期
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2 = "2019-6-25 02:02:02";
		java.sql.Timestamp sqlTimestamp2;
		try {
			sqlTimestamp2 = new java.sql.Timestamp(sdf2.parse(date2).getTime());
			
			subscribeVO2.setTrandate(sqlTimestamp2);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		subscribeVO2.setBuyer_evaluation_score(4);
		
		dao.update(subscribeVO2);
		System.out.println("修改成功!");

		// 刪除
		dao.delete("SU00002");
		System.out.println("刪除成功!");

		// 查詢一筆
		SubscribeVO subscribeVO3 = dao.findByPrimaryKey("SU00001");
		System.out.print(subscribeVO3.getSub_id() + ", ");
		System.out.print(subscribeVO3.getCom_id() + ", ");
		System.out.print(subscribeVO3.getB_member_id() + ", ");
		System.out.print(subscribeVO3.getS_member_id()+ ", ");
		System.out.print(subscribeVO3.getCom_price() + ", ");
		System.out.print(subscribeVO3.getTrandate() + ", ");
		System.out.println(subscribeVO3.getBuyer_evaluation_score());
		System.out.println("---------------------");

		// 查詢全部
		List<SubscribeVO> list = dao.getAll();
		for (SubscribeVO aSU : list) {
			System.out.print(aSU.getSub_id() + ", ");
			System.out.print(aSU.getCom_id() + ", ");
			System.out.print(aSU.getB_member_id() + ", ");
			System.out.print(aSU.getS_member_id() + ", ");
			System.out.print(aSU.getCom_price() + ", ");
			System.out.print(aSU.getTrandate() + ", ");
			System.out.print(aSU.getBuyer_evaluation_score());
			System.out.println();
		}
	}

	@Override
	public void insert(SubscribeVO subscribeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// JNDI
			// con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, subscribeVO.getCom_id());
			pstmt.setString(2, subscribeVO.getB_member_id());
			pstmt.setString(3, subscribeVO.getS_member_id());
			pstmt.setInt(4, subscribeVO.getCom_price());
			pstmt.setTimestamp(5, subscribeVO.getTrandate());
			pstmt.setInt(6, subscribeVO.getBuyer_evaluation_score());

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
	public void update(SubscribeVO subscribeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// JNDI
			// con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, subscribeVO.getCom_id());
			pstmt.setString(2, subscribeVO.getB_member_id());
			pstmt.setString(3, subscribeVO.getS_member_id());
			pstmt.setInt(4, subscribeVO.getCom_price());
			pstmt.setTimestamp(5, subscribeVO.getTrandate());
			pstmt.setInt(6, subscribeVO.getBuyer_evaluation_score());
			pstmt.setString(7, subscribeVO.getSub_id());

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
	public void delete(String sub_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// JNDI
			// con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, sub_id);

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
	public SubscribeVO findByPrimaryKey(String sub_id) {

		SubscribeVO subscribeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// JNDI
			// con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, sub_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// comVo Domain objects
				subscribeVO = new SubscribeVO();
				
				subscribeVO.setSub_id(rs.getString("sub_id"));
				subscribeVO.setCom_id(rs.getString("com_id"));
				subscribeVO.setB_member_id(rs.getString("b_member_id"));
				subscribeVO.setS_member_id(rs.getString("s_member_id"));
				subscribeVO.setCom_price(rs.getInt("com_price"));
				subscribeVO.setTrandate(rs.getTimestamp("trandate"));
				subscribeVO.setBuyer_evaluation_score(rs.getInt("buyer_evaluation_score"));
				
				
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
		return subscribeVO;
	}

	@Override
	public List<SubscribeVO> getAll() {
		List<SubscribeVO> list = new ArrayList<SubscribeVO>();
		SubscribeVO subscribeVO = null;

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
				// subscribeVO Domain objects
				subscribeVO = new SubscribeVO();
				subscribeVO.setSub_id(rs.getString("sub_id"));
				subscribeVO.setCom_id(rs.getString("com_id"));
				subscribeVO.setB_member_id(rs.getString("b_member_id"));
				subscribeVO.setS_member_id(rs.getString("s_member_id"));
				subscribeVO.setCom_price(rs.getInt("com_price"));
				subscribeVO.setTrandate(rs.getTimestamp("trandate"));
				subscribeVO.setBuyer_evaluation_score(rs.getInt("buyer_evaluation_score"));
				list.add(subscribeVO); // Store the row in the list
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