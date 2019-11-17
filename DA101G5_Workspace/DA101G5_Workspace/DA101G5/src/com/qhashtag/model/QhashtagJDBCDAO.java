package com.qhashtag.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QhashtagJDBCDAO implements QhashtagDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	private static final String INSERT = "insert into qhashtag (hashtag_id, que_id) values ( ?, ?)";
	
	private static final String FIND_BY_QUE = "select * from qhashtag where que_id = ?";

	@Override
	public void insert(QhashtagVO qhashtagVO) {
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
			pstmt.setString(1,qhashtagVO.getHashtag_id());
			pstmt.setString(2, qhashtagVO.getQue_id());
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
	public List<QhashtagVO> findByHashtag(String hashtag_id) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<QhashtagVO> gatAll() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<QhashtagVO> findByQue(String que_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QhashtagVO qhashtagVO = null;
		List<QhashtagVO> qhashtaglist = new ArrayList<QhashtagVO>();
		
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_QUE);
			pstmt.setString(1,que_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				qhashtagVO = new QhashtagVO();
				qhashtagVO.setHashtag_id(rs.getString(1));
				qhashtagVO.setQue_id(rs.getString(2));
				qhashtaglist.add(qhashtagVO);
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
		return qhashtaglist;
	}
	
	
	public static void main(String[] args) {
			QhashtagVO vo = new QhashtagVO();
			QhashtagJDBCDAO dao = new QhashtagJDBCDAO();
			List<QhashtagVO> list = new ArrayList<QhashtagVO>();
			
			//insert
//			vo.setHashtag_id("H00001");
//			vo.setQue_id("Q00001");
//			dao.insert(vo);
			//done
			
			//findbyque
//			list= dao.findByQue("Q00005");
//			for(int i = 0; i<list.size(); i++) {
//				vo = list.get(i);
//				System.out.println(vo.getHashtag_id());
//			}
			//done
	}

}
