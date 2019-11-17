package com.qhashtag.model;

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

public class QhashtagDAO implements QhashtagDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "insert into qhashtag (hashtag_id, que_id) values ( ?, ?)";

	private static final String FIND_BY_QUE = "select * from qhashtag where que_id = ?";

	private static final String GETALL = "select * from qhashtag ";

	private static final String FIND_BY_HASHTAG = "select * from qhashtag where hashtag_id = ?";

	private static final String DELETE = "delete from qhashtag where que_id = ? ";

	@Override
	public void insert(QhashtagVO qhashtagVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, qhashtagVO.getHashtag_id());
			pstmt.setString(2, qhashtagVO.getQue_id());
			pstmt.executeUpdate();
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
	public List<QhashtagVO> findByQue(String que_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QhashtagVO qhashtagVO = null;
		List<QhashtagVO> qhashtaglist = new ArrayList<QhashtagVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_QUE);
			pstmt.setString(1, que_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				qhashtagVO = new QhashtagVO();
				qhashtagVO.setHashtag_id(rs.getString(1));
				qhashtagVO.setQue_id(rs.getString(2));
				qhashtaglist.add(qhashtagVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
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
		return qhashtaglist;
	}

	@Override
	public List<QhashtagVO> findByHashtag(String hashtag_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QhashtagVO qhashtagVO = null;
		List<QhashtagVO> qhashtaglist = new ArrayList<QhashtagVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_HASHTAG);
			pstmt.setString(1, hashtag_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				qhashtagVO = new QhashtagVO();
				qhashtagVO.setHashtag_id(rs.getString(1));
				qhashtagVO.setQue_id(rs.getString(2));
				qhashtaglist.add(qhashtagVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
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
		return qhashtaglist;
	}

	@Override
	public List<QhashtagVO> gatAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QhashtagVO qhashtagVO = null;
		List<QhashtagVO> qhashtaglist = new ArrayList<QhashtagVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				qhashtagVO = new QhashtagVO();
				qhashtagVO.setHashtag_id(rs.getString(1));
				qhashtagVO.setQue_id(rs.getString(2));
				qhashtaglist.add(qhashtagVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
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
		return qhashtaglist;
	}

}
