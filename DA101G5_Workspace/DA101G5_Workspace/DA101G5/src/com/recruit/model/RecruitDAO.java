package com.recruit.model;

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

public class RecruitDAO implements RecruitDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "insert into recruit (recruit_id, member_id, startdate, enddate, rcontent, rtitle, recstatus) values ('RC'||LPAD(to_char(recruit_seq.nextval), 5 ,'0'), ?, to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'), to_date(to_char(sysdate+7,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'), ?, ?, 1)";
	private static final String DELETE = "delete from recruit where recruit_id = ?";
	private static final String UPDATE = "update recruit set recstatus = ? where recruit_id = ?";
	private static final String FIND_BY_PK = "select * from recruit where recruit_id = ?";
	private static final String GETALL/*BY TIME DESC*/ = "select * from recruit ORDER BY startdate desc";
	private static final String UPDATE_POINT = "update member set mem_point=? where member_id=?";
	
	@Override
	public void insert(RecruitVO recruitVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, recruitVO.getMember_id());
			pstmt.setString(2, recruitVO.getRcontent());
			pstmt.setString(3, recruitVO.getRtitle());

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
	public void delete(String recruit_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, recruit_id);
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
	public void update(String recruit_id,Integer recstatus) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, recstatus);
			pstmt.setString(2, recruit_id);
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
	public void updateMemberPoint(String member_id,Integer mem_point) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_POINT);
			pstmt.setInt(1, mem_point);
			pstmt.setString(2, member_id);
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
	public RecruitVO findByPK(String recruit_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		RecruitVO recruitVO = new RecruitVO();
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, recruit_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				recruitVO.setRecruit_id(recruit_id);
				recruitVO.setMember_id(rs.getString(2));
				recruitVO.setStartdate(rs.getTimestamp(3));
				recruitVO.setEnddate(rs.getTimestamp(4));
				recruitVO.setRcontent(rs.getString(5));
				recruitVO.setRtitle(rs.getString(6));
				recruitVO.setRecstatus(rs.getInt(7));
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
		return recruitVO;
	}

	@Override
	public List<RecruitVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		RecruitVO recruitVO = null;
		List<RecruitVO> reclist = new ArrayList<RecruitVO>();
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				recruitVO = new RecruitVO();
				recruitVO.setRecruit_id(rs.getString(1));
				recruitVO.setMember_id(rs.getString(2));
				recruitVO.setStartdate(rs.getTimestamp(3));
				recruitVO.setEnddate(rs.getTimestamp(4));
				recruitVO.setRcontent(rs.getString(5));
				recruitVO.setRtitle(rs.getString(6));
				recruitVO.setRecstatus(rs.getInt(7));
				reclist.add(recruitVO);
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
		return reclist;
	}

}
