package com.recruit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.res.model.ResDAO_interface;

public class RecruitJDBCDAO implements RecruitDAO_interface {
	
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	private static final String INSERT = "insert into recruit (recruit_id, member_id, startdate, enddate, rcontent, rtitle, recstatus) values ('RC'||LPAD(to_char(recruit_seq.nextval), 5 ,'0'), ?, to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'), to_date(to_char(sysdate+7,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'), ?, ?, 1)";
	private static final String DELETE = "delete from recruit where recruit_id = ?";
	private static final String UPDATE = "update recruit set recstatus = ? where recruit_id = ?";
	private static final String FIND_BY_PK = "select * from recruit where recruit_id = ?";
	private static final String GETALL/*BY TIME DESC*/ = "select * from recruit ORDER BY startdate desc";

	@Override
	public void insert(RecruitVO recruitVO) {
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
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
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
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
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
	public RecruitVO findByPK(String recruit_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		RecruitVO recruitVO = new RecruitVO();
		ResultSet rs = null;
		
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	
	@Override
	public void updateMemberPoint(String member_id, Integer mem_point) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		RecruitJDBCDAO dao = new RecruitJDBCDAO();
		RecruitVO vo = new RecruitVO();
		List<RecruitVO> list = new ArrayList<RecruitVO>();
		
		//insert
//		vo.setMember_id("M00001");
//		vo.setRtitle("測試");
//		vo.setRcontent("測試");
//		dao.insert(vo);
		//done
		
		//delete
//		dao.delete("RC00008");
		//done
		
		//hide
//		dao.hide("RC00008");
		//done
		
		//findbypk
//		vo = dao.findByPK("RC00008");
//		System.out.println(vo.getRecruit_id());
//		System.out.println(vo.getMember_id());
//		System.out.println(vo.getRtitle());
//		System.out.println(vo.getRcontent());
//		System.out.println(vo.getStartdate());
//		System.out.println(vo.getEnddate());
//		System.out.println(vo.getRecstatus());
		//done
		
		//getAll
//		list = dao.getAll();
//		for(int i =0;i<list.size();i++) {
//			vo = list.get(i);
//			System.out.println(vo.getRecruit_id());
//			System.out.println(vo.getMember_id());
//			System.out.println(vo.getRtitle());
//			System.out.println(vo.getRcontent());
//			System.out.println(vo.getStartdate());
//			System.out.println(vo.getEnddate());
//			System.out.println(vo.getRecstatus());
//		}
		//done
		
	}

}
