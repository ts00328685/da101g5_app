package com.System_manager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class System_managerJDBCDAO implements System_managerDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO system_manager (sm_id, sm_name, sm_ac, sm_pass) VALUES ('SM'||LPAD(to_char(sm_seq.NEXTVAL), 5, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM system_manager order by sm_id";
	private static final String GET_ONE_STMT = "SELECT * FROM system_manager where sm_id = ?";
	private static final String UPDATE = "UPDATE system_manager set sm_name=?, sm_ac = ?, sm_pass = ? where sm_id = ?";
	
	@Override
	public void insert(System_managerVO system_managerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, system_managerVO.getSm_name());
			pstmt.setString(2, system_managerVO.getSm_ac() );
			pstmt.setString(3, system_managerVO.getSm_pass() );
			

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
	public void update(System_managerVO system_managerVO) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(4, system_managerVO.getSm_id());
			pstmt.setString(1, system_managerVO.getSm_name());
			pstmt.setString(2, system_managerVO.getSm_ac());
			pstmt.setString(3, system_managerVO.getSm_pass());
			

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
	public System_managerVO findByPrimaryKey(String sm_id) {

		System_managerVO system_managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, sm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// system_managerVO 也稱為 Domain objects
				system_managerVO = new System_managerVO();
				system_managerVO.setSm_id(rs.getString("sm_id"));
				system_managerVO.setSm_name(rs.getString("sm_name"));
				system_managerVO.setSm_ac(rs.getString("sm_ac"));
				system_managerVO.setSm_pass(rs.getString("sm_pass"));
				
				
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
		return system_managerVO;
	}
	
	
	
	
	@Override
	public System_managerVO findByAccount(String sm_ac) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<System_managerVO> getAll() {
		List<System_managerVO> list = new ArrayList<System_managerVO>();
		System_managerVO system_managerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// System_managerVO 也稱為 Domain objects
				system_managerVO = new System_managerVO();
				system_managerVO.setSm_id(rs.getString("sm_id"));
				system_managerVO.setSm_name(rs.getString("sm_name"));
				system_managerVO.setSm_ac(rs.getString("sm_ac"));
				system_managerVO.setSm_pass(rs.getString("sm_pass"));
				list.add(system_managerVO); // Store the row in the list
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

		System_managerJDBCDAO dao = new System_managerJDBCDAO();

//		// 新增
//		System_managerVO system_managerVO1 = new System_managerVO();
////
//		system_managerVO1.setSm_id("SM00003");
//		system_managerVO1.setSm_name("Jimmy");
//		system_managerVO1.setSm_ac("DA101G5");
//		system_managerVO1.setSm_pass("G5DA101");
//		dao.insert(system_managerVO1);
//		System.out.println("新增成功!");
//
//		// 修改
//		System_managerVO system_managerVO2 = new System_managerVO();
//		system_managerVO2.setSm_id("SM00003");
//		system_managerVO2.setSm_name("Lion");
//		system_managerVO2.setSm_ac("G5DA101");
//		system_managerVO2.setSm_pass("DA101G5");
//		dao.update(system_managerVO2);
//		System.out.println("修改成功!");

		// 查詢
		System_managerVO system_managerVO3 = dao.findByPrimaryKey("SM00001");
		System.out.print(system_managerVO3.getSm_id()+ ",");
		
		
		
		System.out.println("---------------------");
//
//		// 查詢
		List<System_managerVO> list = dao.getAll();
		for (System_managerVO system_managerVO4 : list) {
			System.out.print(system_managerVO4.getSm_id()+ ",");
			System.out.print(system_managerVO4.getSm_name() + ",");
			System.out.print(system_managerVO4.getSm_ac() + ",");
			System.out.print(system_managerVO4.getSm_pass() + ",");
			System.out.println();
		}
	}

}

