package com.System_manager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class System_managerDAO implements System_managerDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO system_manager (sm_id, sm_name, sm_ac, sm_pass) VALUES ('SM'||LPAD(to_char(sm_seq.NEXTVAL), 5, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM system_manager order by sm_id";
	private static final String GET_ONE_STMT = "SELECT * FROM system_manager where sm_id = ?";
	private static final String UPDATE = "UPDATE system_manager set sm_name=?, sm_ac = ?, sm_pass = ? where sm_id = ?";
	private static final String FIND_BY_ACCOUNT = "select * from system_manager where sm_ac = ? ";

	@Override
	public void insert(System_managerVO system_managerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, system_managerVO.getSm_name());
			pstmt.setString(2, system_managerVO.getSm_ac());
			pstmt.setString(3, system_managerVO.getSm_pass());

			pstmt.executeUpdate();

			// Handle any SQL errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(4, system_managerVO.getSm_id());
			pstmt.setString(1, system_managerVO.getSm_name());
			pstmt.setString(2, system_managerVO.getSm_ac());
			pstmt.setString(3, system_managerVO.getSm_pass());

			pstmt.executeUpdate();

			// Handle any driver errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
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

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<System_managerVO> getAll() {
		List<System_managerVO> list = new ArrayList<System_managerVO>();
		System_managerVO system_managerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public System_managerVO findByAccount(String sm_ac) {
		System_managerVO system_managerVO = new System_managerVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_ACCOUNT);
			pstmt.setString(1, sm_ac);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				system_managerVO.setSm_id(rs.getString(1));
				system_managerVO.setSm_name(rs.getString(2));
				system_managerVO.setSm_ac(rs.getString(3));
				system_managerVO.setSm_pass(rs.getString(4));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return system_managerVO;
	}

}
