package com.hashtag.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HashtagJDBCDAO implements HashtagDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	private static final String INSERT = "insert into hashtag (hashtag_id, hashtag) values ('H'||LPAD(to_char(hashtag_seq.nextval),5,'0'), ?)";
	private static final String DELETE = "delete from hashtag where hashtag_id = ?";
	private static final String FIND_BY_PK = "select * from hashtag  where hashtag_id = ?";
	private static final String GETALL = "select * from hashtag";
	
	@Override
	public void insert(HashtagVO hashtagVO) {
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
			pstmt.setString(1, hashtagVO.getHashtag());
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
	public void delete(String hashtag_id) {
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
			pstmt.setString(1, hashtag_id);
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
	public HashtagVO findByPK(String hashtag_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashtagVO hashtagVO = new HashtagVO();
		
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, hashtag_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				hashtagVO.setHashtag_id(rs.getString(1));
				hashtagVO.setHashtag(rs.getString(2));
			}
		}catch (SQLException se) {
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
		return hashtagVO;
	}
	@Override
	public List<HashtagVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashtagVO hashtagVO = null;
		List<HashtagVO> hashtaglist = new ArrayList<HashtagVO>();
		
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
				hashtagVO = new HashtagVO();
				hashtagVO.setHashtag_id(rs.getString(1));
				hashtagVO.setHashtag(rs.getString(2));
				hashtaglist.add(hashtagVO);
			}
		}catch (SQLException se) {
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
		return hashtaglist;
	}
	public static void main(String[] args) {
		HashtagJDBCDAO dao = new HashtagJDBCDAO();
		HashtagVO vo = new HashtagVO();
		List<HashtagVO> list = new ArrayList<HashtagVO>();
		
		//insert
//		vo.setHashtag("測試");
//		dao.insert(vo);
		//done
		
		//delete
//		dao.delete("H00013");
		//done
		
		//findbypk
//		vo = dao.findByPK("H00010");
//		System.out.println(vo.getHashtag());
		//done
		
		//getall
//		list = dao.getAll();
//		for(int i = 0;i<list.size(); i++) {
//			vo = list.get(i);
//			System.out.println(vo.getHashtag());
//		}
		//done
	}

}
