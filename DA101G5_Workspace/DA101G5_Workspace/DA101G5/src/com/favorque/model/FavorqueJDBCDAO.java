package com.favorque.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavorqueJDBCDAO implements FavorqueDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	private static final String INSERT = "insert into favorque (que_id, member_id, addtime) values (?, ?, to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'))";
	
	private static final String DELETE = "delete from favorque where (que_id = ? and member_id = ?)";
	
	private static final String GETALL/*BY ADDTIME DESC*/ = "select * from favorque ORDER BY addtime desc";

	private static final String FIND_BY_MEMBERID/*BY ADDTIME DESC*/ = "select * from favorque where member_id =? ORDER BY addtime DESC";
	
	@Override
	public void insert(FavorqueVO favorqueVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, favorqueVO.getQue_id());
			pstmt.setString(2, favorqueVO.getMember_id());
			pstmt.executeUpdate();
		}catch (SQLException se) {
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
	public void delete(String member_id, String que_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, que_id);
			pstmt.setString(2, member_id);
			pstmt.executeUpdate();
		}catch (SQLException se) {
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
	public boolean search(String member_id, String que_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<FavorqueVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FavorqueVO> favorquelist = new ArrayList<FavorqueVO>();
		FavorqueVO favorqueVO = null;
		
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				favorqueVO = new FavorqueVO();
				favorqueVO.setQue_id(rs.getString(1));
				favorqueVO.setMember_id(rs.getString(2));
				favorqueVO.setAddtime(rs.getTimestamp(3));
				favorquelist.add(favorqueVO);
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
		return favorquelist;
	}
	
	@Override
	public List<FavorqueVO> findByMemberId(String member_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FavorqueVO> favorquelist = new ArrayList<FavorqueVO>();
		FavorqueVO favorqueVO = null;
		
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_MEMBERID);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				favorqueVO = new FavorqueVO();
				favorqueVO.setQue_id(rs.getString(1));
				favorqueVO.setMember_id(rs.getString(2));
				favorqueVO.setAddtime(rs.getTimestamp(3));
				favorquelist.add(favorqueVO);
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
		return favorquelist;
	}
	
	public static void main(String[] args) {
		FavorqueJDBCDAO dao = new FavorqueJDBCDAO();
		FavorqueVO vo = new FavorqueVO();
		List<FavorqueVO> list = new ArrayList<FavorqueVO>();
		
		//insert
//		vo.setMember_id("M00009");
//		vo.setQue_id("Q00001");
//		dao.insert(vo);
		//done
		
		//delete
//		dao.delete("M00009", "Q00001");
		//done
		
		//getall
//		list = dao.getAll();
//		for(int i =0 ; i<list.size();i++) {
//			vo = list.get(i);
//			System.out.println(vo.getMember_id());
//		}
		//done
		
		//findbymemberid
//		list = dao.findByMemberId("M00010");
//		for(int i =0 ; i<list.size();i++) {
//			vo = list.get(i);
//			System.out.println(vo.getMember_id());
//		}
		//done
	}

}
