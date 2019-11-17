package com.res.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResJDBCDAO implements ResDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	private static final String INSERT = 
			"insert into response (res_id, member_id, rescontent, restime, resthumb, resfile, resstatus, bestres, belongto,filetype) values ('RS'||LPAD(to_char(response_seq.NEXTVAL), 5, '0'), ?, ?, to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'), 0, ?, 1, 0, ?, ?)";
	
	private static final String UPDATE = 
			"update response set resthumb = ?, bestres = ? where res_id = ?";
	
	private static final String DELETE = "delete from response where res_id = ?";
	
	private static final String HIDE =
			"update response set resstatus = 0 where res_id = ?";
	
	private static final String FIND_BY_PK = 
			"select * from response where res_id = ?";
	
	private static final String FIND_BY_BELONGTO = 
			"select * from response where belongto = ? ";
	
	private static final String GETALL = /*BY TIME DESC*/
			"select * from response ORDER BY restime desc";

	@Override
	public void insert(ResVO resVO) {/*�s�W*/
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
			
			pstmt.setString(1, resVO.getMember_id());
			pstmt.setString(2, resVO.getRescontent());
			pstmt.setBytes(3, resVO.getResfile());
			pstmt.setString(4, resVO.getBelongto());
			pstmt.setString(5, resVO.getFiletype());
			
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
	public void update(ResVO resVO) {
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
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1,resVO.getResthumb());
			pstmt.setInt(2, resVO.getBestres());
			pstmt.setString(3, resVO.getRes_id());
			
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
	public void delete(String res_id) {
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
			
			pstmt.setString(1, res_id);
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
	public void hide(String res_id) {
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
			pstmt = con.prepareStatement(HIDE);
			pstmt.setString(1, res_id);
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
	public ResVO findByPK(String res_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResVO resVO = new ResVO();
		
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1,res_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resVO.setRes_id(res_id);
				resVO.setMember_id(rs.getString(2));
				resVO.setRescontent(rs.getString(3));
				resVO.setRestime(rs.getTimestamp(4));
				resVO.setResthumb(rs.getInt(5));
				resVO.setResfile(rs.getBytes(6));
				resVO.setResstatus(rs.getInt(7));
				resVO.setBestres(rs.getInt(8));
				resVO.setBelongto(rs.getString(9));
				resVO.setFiletype(rs.getString(10));
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
		return resVO;
	}

	@Override
	public List<ResVO> findByBelongto(String que_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ResVO> reslist = new ArrayList<ResVO>();
		ResVO resVO = null;
		
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_BELONGTO);
			pstmt.setString(1, que_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				resVO = new ResVO();
				resVO.setRes_id(rs.getString(1));
				resVO.setMember_id(rs.getString(2));
				resVO.setRescontent(rs.getString(3));
				resVO.setRestime(rs.getTimestamp(4));
				resVO.setResthumb(rs.getInt(5));
				resVO.setResfile(rs.getBytes(6));
				resVO.setResstatus(rs.getInt(7));
				resVO.setBestres(rs.getInt(8));
				resVO.setBelongto(que_id);
				resVO.setFiletype(rs.getString(10));
				reslist.add(resVO);
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
		return reslist;
	}

	@Override
	public List<ResVO> getAll() {
		List<ResVO> reslist = new ArrayList<ResVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
				ResVO resVO = new ResVO();
				resVO.setRes_id(rs.getString(1));
				resVO.setMember_id(rs.getString(2));
				resVO.setRescontent(rs.getString(3));
				resVO.setRestime(rs.getTimestamp(4));
				resVO.setResthumb(rs.getInt(5));
				resVO.setResfile(rs.getBytes(6));
				resVO.setResstatus(rs.getInt(7));
				resVO.setBestres(rs.getInt(8));
				resVO.setBelongto(rs.getString(9));
				resVO.setFiletype(rs.getString(10));
				reslist.add(resVO);
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
		return reslist;
	}
	
	@Override
	public void onshelf(String res_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit(String res_id, byte[] resfile, String filetype) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		ResJDBCDAO dao = new ResJDBCDAO();
		ResVO vo = new ResVO();
		List<ResVO> reslist = new ArrayList<ResVO>();
		
		//insert
//		vo.setRescontent("111");
//		vo.setBelongto("Q00001");
//		vo.setMember_id("M00001");
//		dao.insert(vo);
		//done
		
		//update
//		vo.setBestres(1);
//		vo.setResthumb(500);;
//		vo.setRes_id("RS00008");
//		dao.update(vo);
		//done
		
		//hide
//		dao.hide("RS00008");
		//done
		
		//delete
//		dao.delete("RS00008");
		//done
		
		//findByPK
//		vo = dao.findByPK("RS00006");
//		System.out.println(vo.getBelongto());
//		System.out.println(vo.getMember_id());
//		System.out.println(vo.getRes_id());
//		System.out.println(vo.getRescontent());
//		System.out.println(vo.getBestres());
//		System.out.println(vo.getResfile());
//		System.out.println(vo.getResstatus());
//		System.out.println(vo.getResthumb());
//		System.out.println(vo.getRestime());
		//done
		
		//findByBelongto
//		reslist = dao.findByBelongto("Q00008");
//		for(int i = 0;i<reslist.size();i++) {
//			vo = reslist.get(i);
//			System.out.println(vo.getBelongto());
//			System.out.println(vo.getMember_id());
//			System.out.println(vo.getRes_id());
//			System.out.println(vo.getRescontent());
//			System.out.println(vo.getBestres());
//			System.out.println(vo.getResfile());
//			System.out.println(vo.getResstatus());
//			System.out.println(vo.getResthumb());
//			System.out.println(vo.getRestime());
//		}
		//done
		
		//getAll
//		reslist = dao.getAll();
//		for(int i = 0;i<reslist.size();i++) {
//			vo = reslist.get(i);
//			System.out.println(vo.getBelongto());
//			System.out.println(vo.getMember_id());
//			System.out.println(vo.getRes_id());
//			System.out.println(vo.getRescontent());
//			System.out.println(vo.getBestres());
//			System.out.println(vo.getResfile());
//			System.out.println(vo.getResstatus());
//			System.out.println(vo.getResthumb());
//			System.out.println(vo.getRestime());
//		}
		//done
	}

}
