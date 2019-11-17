//package com.message.model;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.*;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import com.message.model.MessageVO;
//
//public class MessageJNDIDAO implements MessageDAO_interface {
//
//	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static final String INSERT_STMT = 
//			"INSERT INTO message (message_id,member_id,member_id2,message_date,memmsg_ent,memmsg_state) VALUES (message_seq.NEXTVAL, ?, ?, ?, ?, ?, ?,)";
//	private static final String GET_ALL_STMT = 
//			"SELECT message_id,member_id,member_id2,to_char(message_date,'yyyy-mm-dd')message_date,memmsg_ent,memmsg_state FROM message order by message_id";
//	private static final String GET_ONE_STMT = 
//			"SELECT message_id,member_id,member_id2,to_char(message_date,'yyyy-mm-dd')message_date,memmsg_ent,memmsg_state FROM member where member_id = ?";
//	private static final String DELETE = 
//			"DELETE FROM message where message_id = ?";
//	private static final String UPDATE = 
//			"UPDATE message set member_id=? ,member_id2=? ,message_date=? ,memmsg_ent=? ,memmsg_state=? where message_id = ?";
//
//	@Override
//	public void insert(MessageVO messageVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, messageVO.getMember_id());
//			pstmt.setString(2, messageVO.getMember_id2());
//			pstmt.setDate(3, messageVO.getMemmsg_date());
//			pstmt.setString(4, messageVO.getMemmsg_ent());
//			pstmt.setDouble(5, messageVO.getMemmsg_state());
//
//			pstmt.executeUpdate();
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	@Override
//	public void update(MessageVO messageVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, messageVO.getMember_id());
//			pstmt.setString(2, messageVO.getMember_id2());
//			pstmt.setDate(3, messageVO.getMemmsg_date());
//			pstmt.setString(4, messageVO.getMemmsg_ent());
//			pstmt.setDouble(5, messageVO.getMemmsg_state());
//			pstmt.setString(6, messageVO.getMessage_id());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void delete(String message_id) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, message_id);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured." + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public MessageVO findByPrimaryKey(String message_id) {
//
//		MessageVO messageVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, message_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// memberVo 也稱為 Domain objects
//				messageVO = new MessageVO();
//				messageVO.setMessage_id(rs.getString("message_id"));
//				messageVO.setMember_id(rs.getString("member_id"));
//				messageVO.setMember_id2(rs.getString("member_id2"));
//				messageVO.setMemmsg_date(rs.getDate("emmsg_date"));
//				messageVO.setMemmsg_ent(rs.getString("emmsg_ent"));
//				messageVO.setMemmsg_state(rs.getDouble("memmsg_state"));
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return messageVO;
//	}
//
//	@Override
//	public List<MessageVO> getAll() {
//		List<MessageVO> list = new ArrayList<MessageVO>();
//		MessageVO messageVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// memberVo 也稱為 Domain objects
//				messageVO = new MessageVO();
//				messageVO.setMessage_id(rs.getString("message_id"));
//				messageVO.setMember_id(rs.getString("member_id"));
//				messageVO.setMember_id2(rs.getString("member_id2"));
//				messageVO.setMemmsg_date(rs.getDate("memmsg_date"));
//				messageVO.setMemmsg_ent(rs.getString("memmsg_ent"));
//				messageVO.setMemmsg_state(rs.getDouble("memmsg_state"));
//				list.add(messageVO);// Store the row in the list
//			}
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
//
//}
