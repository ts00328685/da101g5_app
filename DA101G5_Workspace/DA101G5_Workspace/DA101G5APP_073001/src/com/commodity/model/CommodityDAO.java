package com.commodity.model;

import java.util.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CommodityDAO implements CommodityDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "Insert into commodity "
			+ "(com_id, member_id, com_description, com_pic, com_download, com_price, "
			+ " trandate, com_status, e_score, e_pol_statistics, e_des) values "
			+ "('CM'||LPAD(to_char(com_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT * FROM commodity order by com_id";
	private static final String GET_ONE_STMT = "SELECT * FROM commodity where com_id = ?";
	private static final String DELETE = "DELETE FROM commodity where com_id = ?";
	private static final String UPDATE = "UPDATE commodity set member_id=?, com_description=?, "
			+ "com_pic=?, com_download=?, com_price=?, trandate=?, com_status=?, e_score=?, e_pol_statistics=?, e_des=? "
			+ " where com_id = ?";
	private static final String UPDATE_IMAGE = "UPDATE commodity set com_pic=? where com_id = ?";

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

		CommodityDAO dao = new CommodityDAO();

		// 新增
		CommodityVO commodityVO1 = new CommodityVO();
		commodityVO1.setMember_id("M00001");
		commodityVO1.setCom_description("基礎英文文法講義，僅十四頁即涵蓋所有常用的重點部分，讓你輕鬆掌握閱讀的關鍵技巧。");
		commodityVO1.setCom_pic(null);
		commodityVO1.setCom_download("https://drive.google.com/uc?export=download&id=0B2x595t1GJinYURENlpSb0RHVUk");
		commodityVO1.setCom_price(500);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = "2019-6-22 01:01:01";
		java.sql.Timestamp sqlTimestamp;
		try {
			sqlTimestamp = new java.sql.Timestamp(sdf.parse(date).getTime());
			
			commodityVO1.setTrandate(sqlTimestamp);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		commodityVO1.setCom_status(1);
		commodityVO1.setE_score(5.0D);
		commodityVO1.setE_pol_statistics(10);
		commodityVO1.setE_des("M00002:讚讚");
		
		dao.insert(commodityVO1);
		System.out.println("新增成功!");

		
		// 修改
		CommodityVO commodityVO2 = new CommodityVO();
		commodityVO2.setCom_id("CM00002");
		commodityVO2.setMember_id("M00001");
		commodityVO2.setCom_description("基礎 會話 講義，僅十四頁即涵蓋所有常用的重點部分，讓你輕鬆掌握閱讀的關鍵技巧。");
		commodityVO2.setCom_pic(null);
		commodityVO2.setCom_download("https://drive.google.com/uc?export=download&id=0B2x595t1GJinYURENlpSb0RHVUk");
		commodityVO2.setCom_price(500);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2 = "2019-6-23 22:22:22";
		java.sql.Timestamp sqlTimestamp2;
		try {
			sqlTimestamp2 = new java.sql.Timestamp(sdf.parse(date2).getTime());
			
			commodityVO1.setTrandate(sqlTimestamp2);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		commodityVO2.setCom_status(0);
		commodityVO2.setE_score(4.9D);
		commodityVO2.setE_pol_statistics(11);
		commodityVO2.setE_des("M00002:讚讚, M00003:爛爛");
		
		dao.update(commodityVO2);
		System.out.println("修改成功!");

		// 刪除
		dao.delete("CM00002");
		System.out.println("刪除成功!");

//		// 查詢一筆
		CommodityVO commodityVO3 = dao.findByPrimaryKey("CM00002");
		System.out.print(commodityVO3.getCom_id() + ", ");
		System.out.print(commodityVO3.getMember_id() + ", ");
		System.out.print(commodityVO3.getCom_description() + ", ");
		System.out.print(commodityVO3.getCom_pic() + ", ");
		System.out.print(commodityVO3.getCom_download() + ", ");
		System.out.print(commodityVO3.getCom_price() + ", ");
		System.out.print(commodityVO3.getTrandate() + ", ");
		System.out.print(commodityVO3.getCom_status() + ", ");
		System.out.print(commodityVO3.getE_score() + ", ");
		System.out.print(commodityVO3.getE_pol_statistics() + ", ");
		System.out.print(commodityVO3.getE_des() + ", ");
		System.out.println("---------------------");

		// 查詢全部
		List<CommodityVO> list = dao.getAll();
		for (CommodityVO aCM : list) {
			System.out.print(aCM.getCom_id() + ", ");
			System.out.print(aCM.getMember_id() + ", ");
			System.out.print(aCM.getCom_description() + ", ");
			System.out.print(aCM.getCom_pic() + ", ");
			System.out.print(aCM.getCom_download() + ", ");
			System.out.print(aCM.getCom_price() + ", ");
			System.out.print(aCM.getTrandate() + ", ");
			System.out.print(aCM.getCom_status() + ", ");
			System.out.print(aCM.getE_score() + ", ");
			System.out.print(aCM.getE_pol_statistics() + ", ");
			System.out.print(aCM.getE_des() + ", ");
			System.out.println();
		}
	}

	@Override
	public void updateImage(String commodity_id, byte[] com_pic) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			//con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE_IMAGE);
			
			pstmt.setString(2, commodity_id);
			pstmt.setBytes(1, com_pic);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e){
			throw new RuntimeException("A class error occured. "
					+ e.getMessage());
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
	public void insert(CommodityVO commodityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// JNDI
			// con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, commodityVO.getMember_id());
			pstmt.setString(2, commodityVO.getCom_description());
			pstmt.setBytes(3, commodityVO.getCom_pic());
			pstmt.setString(4, commodityVO.getCom_download());
			pstmt.setInt(5, commodityVO.getCom_price());
			pstmt.setTimestamp(6, commodityVO.getTrandate());
			pstmt.setInt(7, commodityVO.getCom_status());
			pstmt.setDouble(8, commodityVO.getE_score());
			pstmt.setInt(9, commodityVO.getE_pol_statistics());
			pstmt.setString(10, commodityVO.getE_des());

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
	public void update(CommodityVO commodityVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// JNDI
			// con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, commodityVO.getMember_id());
			pstmt.setString(2, commodityVO.getCom_description());
			pstmt.setBytes(3, commodityVO.getCom_pic());
			pstmt.setString(4, commodityVO.getCom_download());
			pstmt.setInt(5, commodityVO.getCom_price());
			pstmt.setTimestamp(6, commodityVO.getTrandate());
			pstmt.setInt(7, commodityVO.getCom_status());
			pstmt.setDouble(8, commodityVO.getE_score());
			pstmt.setInt(9, commodityVO.getE_pol_statistics());
			pstmt.setString(10, commodityVO.getE_des());
			pstmt.setString(11, commodityVO.getCom_id());

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
	public void delete(String com_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// JNDI
			// con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, com_id);

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
	public CommodityVO findByPrimaryKey(String com_id) {

		CommodityVO commodityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// JNDI
			// con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, com_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// comVo Domain objects
				commodityVO = new CommodityVO();
				commodityVO.setCom_id(rs.getString("com_id"));
				commodityVO.setMember_id(rs.getString("member_id"));
				commodityVO.setCom_description(rs.getString("com_description"));
				commodityVO.setCom_pic(rs.getBytes("com_pic"));
				commodityVO.setCom_download(rs.getString("com_download"));
				commodityVO.setCom_price(rs.getInt("com_price"));
				commodityVO.setTrandate(rs.getTimestamp("trandate"));
				commodityVO.setCom_status(rs.getInt("com_status"));
				commodityVO.setE_score(rs.getDouble("e_score"));
				commodityVO.setE_pol_statistics(rs.getInt("e_pol_statistics"));
				commodityVO.setE_des(rs.getString("e_des"));
				
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
		return commodityVO;
	}

	@Override
	public List<CommodityVO> getAll() {
		List<CommodityVO> list = new ArrayList<CommodityVO>();
		CommodityVO commodityVO = null;

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
				// commodityVO Domain objects
				commodityVO = new CommodityVO();
				commodityVO.setCom_id(rs.getString("com_id"));
				commodityVO.setMember_id(rs.getString("member_id"));
				commodityVO.setCom_description(rs.getString("com_description"));
				commodityVO.setCom_pic(rs.getBytes("com_pic"));
				commodityVO.setCom_download(rs.getString("com_download"));
				commodityVO.setCom_price(rs.getInt("com_price"));
				commodityVO.setTrandate(rs.getTimestamp("trandate"));
				commodityVO.setCom_status(rs.getInt("com_status"));
				commodityVO.setE_score(rs.getDouble("e_score"));
				commodityVO.setE_pol_statistics(rs.getInt("e_pol_statistics"));
				commodityVO.setE_des(rs.getString("e_des"));
				list.add(commodityVO); // Store the row in the list
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