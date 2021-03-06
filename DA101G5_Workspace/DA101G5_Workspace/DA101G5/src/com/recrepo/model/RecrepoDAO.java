package com.recrepo.model;

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

public class RecrepoDAO implements RecrepoDAO_interface{
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
			"insert into recrepo (recrepo_id, recruit_id, repodate, repocontent, repostatus) values ('RP'||LPAD(to_char(recrepo_seq.nextval),5,'0'),? ,to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'), ?, 0)";
	private static final String DELETE = 
			"delete from recrepo where recrepo_id = ?  ";
	private static final String UPDATE = 
			"update recrepo set repostatus = ? where recrepo_id = ?";
	private static final String FIND_BY_PK/**/ = 
			"select * from recrepo where recrepo_id = ?";
	private static final String GETALL = /*BY TIME DESC*/
			"select * from recrepo ORDER BY repodate desc";
	
	private static final String GETALLNOTDONEYET = /*BY TIME DESC*/
			"select * from recrepo where repostatus<2 ORDER BY repodate desc";
	@Override
	public void insert(RecrepoVO recrepoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, recrepoVO.getRecruit_id());
			pstmt.setString(2,recrepoVO.getRepocontent());
			pstmt.executeUpdate();
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
	public void delete(String recrepo_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,recrepo_id);
			pstmt.executeUpdate();
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
	public void update(String recrepo_id,Integer repostatus) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, repostatus);
			pstmt.setString(2, recrepo_id);
			pstmt.executeUpdate();
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
	public RecrepoVO findByPK(String recrepo_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RecrepoVO recrepoVO = new RecrepoVO();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, recrepo_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				recrepoVO.setRecrepo_id(recrepo_id);
				recrepoVO.setRecruit_id(rs.getString(2));
				recrepoVO.setRepodate(rs.getTimestamp(3));
				recrepoVO.setRepocontent(rs.getString(4));
				recrepoVO.setRepostatus(rs.getInt(5));
			}
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
		return recrepoVO;
	}
	@Override
	public List<RecrepoVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RecrepoVO recrepoVO = null;
		List<RecrepoVO> recrepolist = new ArrayList<RecrepoVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				recrepoVO = new RecrepoVO();
				recrepoVO.setRecrepo_id(rs.getString(1));
				recrepoVO.setRecruit_id(rs.getString(2));
				recrepoVO.setRepodate(rs.getTimestamp(3));
				recrepoVO.setRepocontent(rs.getString(4));
				recrepoVO.setRepostatus(rs.getInt(5));
				recrepolist.add(recrepoVO);
			}
		}catch (SQLException se) {
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
		return recrepolist;
	}
	@Override
	public List<RecrepoVO> getAllNotDoneYet() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RecrepoVO recrepoVO = null;
		List<RecrepoVO> recrepolist = new ArrayList<RecrepoVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALLNOTDONEYET);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				recrepoVO = new RecrepoVO();
				recrepoVO.setRecrepo_id(rs.getString(1));
				recrepoVO.setRecruit_id(rs.getString(2));
				recrepoVO.setRepodate(rs.getTimestamp(3));
				recrepoVO.setRepocontent(rs.getString(4));
				recrepoVO.setRepostatus(rs.getInt(5));
				recrepolist.add(recrepoVO);
			}
		}catch (SQLException se) {
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
		return recrepolist;
	}
	
	
}
