package com.thumbrecordque.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ThumbrecordqueDAO implements ThumbrecordqueDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "insert into Thumbrecordque (member_id ,que_id) values (? ,?)";
	
	private static final String FIND_BY_MEMBERID = "select * from Thumbrecordque where member_id = ?";
	
	private static final String SEARCH = "select * from Thumbrecordque where(que_id = ? and member_id = ?)";
	
	private static final String DELETE = "delete from Thumbrecordque where (member_id = ? and que_id = ?)";
	
	@Override
	public void insert(String member_id, String que_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(INSERT);
		pstmt.setString(1, member_id);
		pstmt.setString(2, que_id);
		pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	@Override
	public void delete(String member_id, String que_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(DELETE);
		pstmt.setString(1, member_id);
		pstmt.setString(2, que_id);
		pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}



	@Override
	public List<ThumbrecordqueVO> findByMemberId(String member_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ThumbrecordqueVO> thumbrecordquelist = new ArrayList<ThumbrecordqueVO>();
		
		try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(FIND_BY_MEMBERID);
		pstmt.setString(1, member_id);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			ThumbrecordqueVO thumbrecordqueVO = new ThumbrecordqueVO();
			thumbrecordqueVO.setMember_id(member_id);
			thumbrecordqueVO.setQue_id(rs.getString(2));
			thumbrecordquelist.add(thumbrecordqueVO);
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return thumbrecordquelist;
	}
	@Override
	public boolean search(String member_id, String que_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH);
			pstmt.setString(1, que_id);
			pstmt.setString(2, member_id);
			rs = pstmt.executeQuery();
			if(rs.next()==true) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	
}
