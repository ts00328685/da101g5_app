package com.Language.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class LanguageJDBCDAO implements LanguageDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO language (language_id,language) VALUES ('L'||LPAD(to_char(LANGUAGE_SEQ.NEXTVAL), 5, '0'), ?)";
		private static final String GET_ALL_STMT = 
			"SELECT language_id,language FROM language order by language_id desc";
		private static final String GET_ONE_STMT = 
			"SELECT language_id,language FROM language where language_id = ?";
		private static final String DELETE = 
			"DELETE FROM language where language_id = ?";
		private static final String UPDATE = 
			"UPDATE language set language=? where language_id = ?";

	@Override
	public void insert(LanguageVO languageVO) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, languageVO.getLanguage());
			

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
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
	public void update(LanguageVO languageVO) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, languageVO.getLanguage());
			pstmt.setString(2, languageVO.getLanguage_id());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
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
	public void delete(String language_id) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, language_id);

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
	public LanguageVO findByPrimaryKey(String language_id) {


		LanguageVO languageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, language_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// LanguageVo 也稱為 Domain objects
				languageVO = new LanguageVO();
				languageVO.setLanguage_id(rs.getString("language_id"));
				languageVO.setLanguage(rs.getString("language"));
				
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
		return languageVO;
	}

	@Override
	public List<LanguageVO> getAll() {


		List<LanguageVO> list = new ArrayList<LanguageVO>();
		LanguageVO languageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// languageVO 也稱為 Domain objects
				languageVO = new LanguageVO();
				languageVO = new LanguageVO();
				languageVO.setLanguage_id(rs.getString("language_id"));
				languageVO.setLanguage(rs.getString("language"));
				list.add(languageVO); // Store the row in the list
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
	
	
	public static void main(String[] args) {

		LanguageJDBCDAO dao = new LanguageJDBCDAO();

//		// 新增
//		LanguageVO languageVO1 = new LanguageVO();
//
//		languageVO1.setLanguage("星星");
//		dao.insert(languageVO1);
//
//		// 修改
//		LanguageVO languageVO2 = new LanguageVO();
//		languageVO2.setLanguage_id("L00001");
//		languageVO2.setLanguage("火火");
//		dao.update(languageVO2);

		// 刪除
//		dao.delete("L00001");

		// 查詢
//		LanguageVO languageVO3 = dao.findByPrimaryKey("L00001");
//		System.out.print(languageVO3.getLanguage()+ ",");
//		System.out.print(languageVO3.getLanguage() + ",");
//		
//		System.out.println("---------------------");
//
//		// 查詢
//		List<LanguageVO> list = dao.getAll();
//		for (LanguageVO language : list) {
//			System.out.print(language.getLanguage()+ ",");
//			System.out.print(language.getLanguage() + ",");
//			
//			System.out.println();
//		}
	}

}
