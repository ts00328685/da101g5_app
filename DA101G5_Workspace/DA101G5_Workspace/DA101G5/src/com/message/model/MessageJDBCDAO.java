//package com.message.model;
//
//import java.util.*;
//import java.sql.*;
//
//public class MessageJDBCDAO implements MessageDAO_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA101G5";
//	String passwd = "123456";
//
//	private static final String INSERT_STMT = 
//			"INSERT INTO message (message_id,member_id,member_id2,memmsg_date,memmsg_ent,memmsg_state) VALUES ('MS'||LPAD(to_char(MESSAGE_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = 
//			"SELECT message_id,member_id,member_id2,to_char(memmsg_date,'yyyy-mm-dd')memmsg_date,memmsg_ent,memmsg_state FROM message order by message_id";
//	private static final String GET_ONE_STMT = 
//			"SELECT message_id,member_id,member_id2,to_char(memmsg_date,'yyyy-mm-dd')memmsg_date,memmsg_ent,memmsg_state FROM message where message_id = ?";
//	private static final String DELETE = 
//			"DELETE FROM message where message_id = ?";
//	private static final String UPDATE = 
//			"UPDATE message set member_id=?, member_id2=?, memmsg_date=?, memmsg_ent=?, memmsg_state=? where message_id = ?";
//
//	@Override
//	public void insert(MessageVO messageVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, messageVO.getMember_id());
//			pstmt.setString(2, messageVO.getMember_id2());
//			pstmt.setDate(3, messageVO.getMemmsg_date());
//			pstmt.setString(4, messageVO.getMemmsg_ent());
//			pstmt.setInt(5, messageVO.getMemmsg_state());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
//
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
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, messageVO.getMember_id());
//			pstmt.setString(2, messageVO.getMember_id2());
//			pstmt.setDate(3, messageVO.getMemmsg_date());
//			pstmt.setString(4, messageVO.getMemmsg_ent());
//			pstmt.setInt(5, messageVO.getMemmsg_state());
//			pstmt.setString(6, messageVO.getMessage_id());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
//	public void delete(String message_id) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setString(1, message_id);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
//	public MessageVO findByPrimaryKey(String message_id) {
//
//		MessageVO messageVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
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
//				messageVO.setMemmsg_date(rs.getDate("memmsg_date"));
//				messageVO.setMemmsg_ent(rs.getString("memmsg_ent"));
//				messageVO.setMemmsg_state(rs.getInt("memmsg_state"));
//
//			}
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
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
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// messageVO 也稱為 Domain objects
//				messageVO = new MessageVO();
//				messageVO.setMessage_id(rs.getString("message_id"));
//				messageVO.setMember_id(rs.getString("Member_id"));
//				messageVO.setMember_id2(rs.getString("member_id2"));
//				messageVO.setMemmsg_date(rs.getDate("memmsg_date"));
//				messageVO.setMemmsg_ent(rs.getString("memmsg_ent"));
//				messageVO.setMemmsg_state(rs.getInt("memmsg_state"));
//				list.add(messageVO); // Store the row in the list
//			}
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
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
//	public static void main(String[] args) {
//
//		MessageJDBCDAO dao = new MessageJDBCDAO();
//
//		// 新增
//		MessageVO messageVO1 = new MessageVO();
////		messageVO1.setMessage_id("MS00001");
//		messageVO1.setMember_id("M00001");
//		messageVO1.setMember_id2("M00002");
//		messageVO1.setMemmsg_date(java.sql.Date.valueOf("2019-01-01"));
//		messageVO1.setMemmsg_ent("安安你好嗎");
//		messageVO1.setMemmsg_state(new Integer(1));
//		dao.insert(messageVO1);
//
//		// 修改
//		MessageVO messageVO2 = new MessageVO();
//		messageVO2.setMessage_id("MS00002");
//		messageVO2.setMember_id("M00001");
//		messageVO2.setMember_id2("M00002");
//		messageVO2.setMemmsg_date(java.sql.Date.valueOf("2019-01-01"));
//		messageVO2.setMemmsg_ent("在嘛?");
//		messageVO2.setMemmsg_state(new Integer(1));
//		dao.update(messageVO2);
//
//		// 刪除
//		dao.delete("MS00010");
//
//		// 查詢
//		MessageVO messageVO3 = dao.findByPrimaryKey("MS00001");
//		System.out.print(messageVO3.getMember_id() + ",");
//		System.out.print(messageVO3.getMember_id2() + ",");
//		System.out.print(messageVO3.getMemmsg_date() + ",");
//		System.out.print(messageVO3.getMemmsg_ent() + ",");
//		System.out.print(messageVO3.getMemmsg_state());
//		System.out.println("----------------------------------");
//
//		// 查詢
//		List<MessageVO> list = dao.getAll();
//		for (MessageVO aMessage : list) {
//			System.out.print(aMessage.getMessage_id() + ",");
//			System.out.print(aMessage.getMember_id() + ",");
//			System.out.print(aMessage.getMember_id2() + ",");
//			System.out.print(aMessage.getMemmsg_date() + ",");
//			System.out.print(aMessage.getMemmsg_ent() + ",");
//			System.out.print(aMessage.getMemmsg_state());
//			System.out.println();
//		}
//	}
//}
