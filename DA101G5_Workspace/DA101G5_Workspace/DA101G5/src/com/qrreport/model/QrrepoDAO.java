package com.qrreport.model;

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

import com.qrreport.model.QrrepoVO;

public class QrrepoDAO implements QrrepoDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = 
			"insert into qrreport (qrrepo_id, repoq_id, repor_id, qrrepotime, qrrepocontent, qrrepostatus) values ('QR'||LPAD(to_char(qrreport_seq.nextval),5,'0'),? ,?, to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'),? ,0)";
	private static final String DELETE = 
			"delete from qrreport where qrrepo_id = ?  ";
	private static final String UPDATE = 
			"update qrreport set qrrepostatus = ? where qrrepo_id = ?";
	private static final String FIND_BY_PK = 
			"select * from qrreport where qrrepo_id = ?";
	private static final String GETALL = /*BY TIME DESC*/
			"select * from qrreport ORDER BY qrrepotime desc";
	private static final String GETALL_NOT_DONE_YET = /*BY TIME DESC*/
			"select * from qrreport where qrrepostatus<2 ORDER BY qrrepotime desc ";
	
	@Override
	public void insert(QrrepoVO qrrepoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1,qrrepoVO.getRepoq_id());
			pstmt.setString(2, qrrepoVO.getRepor_id());
			pstmt.setString(3, qrrepoVO.getQrrepocontent());

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
	public void delete(String qrrepo_id) {/*�R��*/
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, qrrepo_id);

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
	public void update(QrrepoVO qrrepoVO) {/*�ק�B�z���A*/
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, qrrepoVO.getQrrepostatus());
			pstmt.setString(2, qrrepoVO.getQrrepo_id());

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
	public QrrepoVO findByPK(String qrrepo_id) {
		QrrepoVO qrrepoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setString(1, qrrepo_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				qrrepoVO = new QrrepoVO();
				qrrepoVO.setQrrepo_id(qrrepo_id);
				qrrepoVO.setRepoq_id(rs.getString(2));
				qrrepoVO.setRepor_id(rs.getString(3));
				qrrepoVO.setQrrepotime(rs.getTimestamp(4));
				qrrepoVO.setQrrepocontent(rs.getString(5));
				qrrepoVO.setQrrepostatus(rs.getInt(6));
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
		return qrrepoVO;
	}
	@Override
	public List<QrrepoVO> getAll() {
		List<QrrepoVO> qrreportlist = new ArrayList<QrrepoVO>();
		QrrepoVO qrrepoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				qrrepoVO = new QrrepoVO();
				qrrepoVO.setQrrepo_id(rs.getString(1));
				qrrepoVO.setRepoq_id(rs.getString(2));
				qrrepoVO.setRepor_id(rs.getString(3));
				qrrepoVO.setQrrepotime(rs.getTimestamp(4));
				qrrepoVO.setQrrepocontent(rs.getString(5));
				qrrepoVO.setQrrepostatus(rs.getInt(6));
				
				qrreportlist.add(qrrepoVO); // Store the row in the list
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
		return qrreportlist;
	}
	@Override
	public List<QrrepoVO> getAllNotDoneYet() {
		List<QrrepoVO> qrreportlist = new ArrayList<QrrepoVO>();
		QrrepoVO qrrepoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL_NOT_DONE_YET);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				qrrepoVO = new QrrepoVO();
				qrrepoVO.setQrrepo_id(rs.getString(1));
				qrrepoVO.setRepoq_id(rs.getString(2));
				qrrepoVO.setRepor_id(rs.getString(3));
				qrrepoVO.setQrrepotime(rs.getTimestamp(4));
				qrrepoVO.setQrrepocontent(rs.getString(5));
				qrrepoVO.setQrrepostatus(rs.getInt(6));
				
				qrreportlist.add(qrrepoVO); // Store the row in the list
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
		return qrreportlist;
	}
	
	
}
