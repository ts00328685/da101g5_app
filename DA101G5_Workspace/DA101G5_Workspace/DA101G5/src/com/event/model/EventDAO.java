package com.event.model;

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

public class EventDAO implements EventDAO_interface {
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
			"insert into event (event_id, evtitle, evstartdate, evenddate, evcontent, evpic, evstatus,filetype) values ('E'||LPAD(to_char(event_seq.nextval),5,'0'), ?, ?, ?, ?, ?, 1,?)";
	private static final String UPDATE = 
			"update event set evtitle = ? ,evstartdate = ?, evenddate = ?, evcontent = ?, evpic = ?, evstatus = ?, filetype = ? where event_id =?";
	
	private static final String FIND_BY_PK = "select * from event where event_id = ?";
	
	private static final String GETALL = "select * from event ORDER BY EVENT_ID DESC";
	
	private static final String GETALLOnshelf = "select * from event where EVSTATUS>0 ORDER BY EVSTARTDATE DESC";
	
	@Override
	public void insert(EventVO eventVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, eventVO.getEvtitle());
			pstmt.setTimestamp(2, eventVO.getEvstartdate());
			pstmt.setTimestamp(3, eventVO.getEvendate());
			pstmt.setString(4, eventVO.getEvcontent());
			pstmt.setBytes(5, eventVO.getEvpic());
			pstmt.setString(6, eventVO.getFiletype());
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, eventVO.getEvtitle());
			pstmt.setTimestamp(2, eventVO.getEvstartdate());
			pstmt.setTimestamp(3, eventVO.getEvendate());
			pstmt.setString(4, eventVO.getEvcontent());
			pstmt.setBytes(5, eventVO.getEvpic());
			pstmt.setInt(6, eventVO.getEvstatus());
			pstmt.setString(7, eventVO.getFiletype());
			pstmt.setString(8, eventVO.getEvent_id());
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
			con = ds.getConnection();
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
				eventVO.setFiletype(rs.getString(8));
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
			con = ds.getConnection();
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
				eventVO.setFiletype(rs.getString(8));
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
			con = ds.getConnection();
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
				eventVO.setFiletype(rs.getString(8));
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
	
	
}
