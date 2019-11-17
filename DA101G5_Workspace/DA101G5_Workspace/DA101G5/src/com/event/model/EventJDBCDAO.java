package com.event.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EventJDBCDAO implements EventDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";
	
	private static final String INSERT = 
			"insert into event (event_id, evtitle, evstartdate, evenddate, evcontent, evpic, evstatus) values ('E'||LPAD(to_char(event_seq.nextval),5,'0'), ?, ?, ?, ?, ?, 1)";
	private static final String UPDATE = 
			"update event set evtitle = ? ,evstartdate = ?, evenddate = ?, evcontent = ?, evpic = ?, evstatus = ? where event_id =?";
	
	private static final String FIND_BY_PK = "select * from event where event_id = ?";
	
	private static final String GETALL = "select * from event ORDER BY EVSTARTDATE DESC";
	
	private static final String GETALLOnshelf = "select * from event where EVSTATUS>0 ORDER BY EVSTARTDATE DESC";
	
	@Override
	public void insert(EventVO eventVO) {
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
			pstmt.setString(1, eventVO.getEvtitle());
			pstmt.setTimestamp(2, eventVO.getEvstartdate());
			pstmt.setTimestamp(3, eventVO.getEvendate());
			pstmt.setString(4, eventVO.getEvcontent());
			pstmt.setBytes(5, eventVO.getEvpic());
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
	public void update(EventVO eventVO) {
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
			pstmt.setString(1, eventVO.getEvtitle());
			pstmt.setTimestamp(2, eventVO.getEvstartdate());
			pstmt.setTimestamp(3, eventVO.getEvendate());
			pstmt.setString(4, eventVO.getEvcontent());
			pstmt.setBytes(5, eventVO.getEvpic());
			pstmt.setInt(6, eventVO.getEvstatus());
			pstmt.setString(7, eventVO.getEvent_id());
			pstmt.executeUpdate();
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
	public EventVO findByPK(String event_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EventVO eventVO = new EventVO();
		
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, event_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				eventVO.setEvent_id(rs.getString(1));
				eventVO.setEvtitle(rs.getString(2));
				eventVO.setEvstartdate(rs.getTimestamp(3));
				eventVO.setEvendate(rs.getTimestamp(4));
				eventVO.setEvcontent(rs.getString(5));
				eventVO.setEvpic(rs.getBytes(6));
				eventVO.setEvstatus(rs.getInt(7));
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
		return eventVO;
	}
	@Override
	public List<EventVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EventVO eventVO = null;
		List<EventVO> eventlist = new ArrayList<EventVO>();
		
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
				eventVO = new EventVO();
				eventVO.setEvent_id(rs.getString(1));
				eventVO.setEvtitle(rs.getString(2));
				eventVO.setEvstartdate(rs.getTimestamp(3));
				eventVO.setEvendate(rs.getTimestamp(4));
				eventVO.setEvcontent(rs.getString(5));
				eventVO.setEvpic(rs.getBytes(6));
				eventVO.setEvstatus(rs.getInt(7));
				eventlist.add(eventVO);
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
		return eventlist;
	}
	
	
	@Override
	public List<EventVO> getAllOnshelf() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EventVO eventVO = null;
		List<EventVO> eventlist = new ArrayList<EventVO>();
		
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETALLOnshelf);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				eventVO = new EventVO();
				eventVO.setEvent_id(rs.getString(1));
				eventVO.setEvtitle(rs.getString(2));
				eventVO.setEvstartdate(rs.getTimestamp(3));
				eventVO.setEvendate(rs.getTimestamp(4));
				eventVO.setEvcontent(rs.getString(5));
				eventVO.setEvpic(rs.getBytes(6));
				eventVO.setEvstatus(rs.getInt(7));
				eventlist.add(eventVO);
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
		return eventlist;
	}
	
	
	public static void main(String[] args) {
		EventVO vo = new EventVO();
		EventJDBCDAO dao = new EventJDBCDAO();
		List<EventVO>  list = new ArrayList<EventVO>();
		
		//insert
//		vo.setEvtitle("喔喔喔喔");
//		vo.setEvstartdate(Timestamp.valueOf("2019-06-24 13:05:05"));
//		vo.setEvendate(Timestamp.valueOf("2019-08-24 13:07:07"));
//		vo.setEvcontent("AAAAAAAAAAAAAAA");
//		dao.insert(vo);
		//done
		
		//update
//		vo.setEvtitle("AAAAAAAAAA");
//		vo.setEvstartdate(Timestamp.valueOf("2019-06-24 13:05:05"));
//		vo.setEvendate(Timestamp.valueOf("2019-08-01 13:07:07"));
//		vo.setEvcontent("BBBBBBBBBBBB");
//		vo.setEvent_id("E00004");
//		vo.setEvstatus(0);
//		dao.update(vo);
		//done
		
		//findbypk
//		vo = dao.findByPK("E00004");
//		System.out.println(vo.getEvcontent());
		//done
		
		//getall
//		list = dao.getAll();
//		for(int i=0; i<list.size();i++) {
//			vo = list.get(i);
//			System.out.println(vo.getEvent_id());
//		}
		//done
		
	}

}
