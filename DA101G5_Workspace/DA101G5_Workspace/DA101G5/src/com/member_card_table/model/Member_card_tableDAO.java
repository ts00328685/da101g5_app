package com.member_card_table.model;

import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Member_card_tableDAO implements Member_card_tableDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";

	private static final String INSERT_STMT = "Insert into member_card_table "
			+ "(card_id, member_card_group_id, review_time, wrong_number, right_number, daily_answer, choice_card_group) values "
			+ "(?, ?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT * FROM member_card_table order by member_card_group_id";
	private static final String GET_ONE_STMT = "SELECT * FROM member_card_table where card_id = ? AND member_card_group_id = ?";
	private static final String DELETE = "DELETE FROM member_card_table where card_id = ? AND member_card_group_id = ?";
	private static final String UPDATE = "UPDATE member_card_table SET review_time = ?, wrong_number = ?, right_number = ?, daily_answer = ?, choice_card_group = ? "
			+ " where card_id = ? AND member_card_group_id = ?";

// JNDI
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	public static void main(String[] args) {

		Member_card_tableDAO dao = new Member_card_tableDAO();

		// 新增
		Member_card_tableVO member_card_tableVO1 = new Member_card_tableVO();
		member_card_tableVO1.setCard_id("C00001");
		member_card_tableVO1.setMember_card_group_id("MCG00003");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = "2019-6-22 01:01:01";
		java.sql.Timestamp sqlTimestamp;
		try {
			sqlTimestamp = new java.sql.Timestamp(sdf.parse(date).getTime());
			member_card_tableVO1.setReview_time(sqlTimestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		member_card_tableVO1.setWrong_number(1);
		member_card_tableVO1.setRight_number(2);
		member_card_tableVO1.setDaily_answer(3);
		member_card_tableVO1.setChoice_card_group(4);
		
		dao.insert(member_card_tableVO1);
		System.out.println("新增成功!");

		
		
		
		// 修改
		Member_card_tableVO member_card_tableVO2 = new Member_card_tableVO();
		member_card_tableVO2.setCard_id("C00001");
		member_card_tableVO2.setMember_card_group_id("MCG00003");

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2 = "2019-6-12 02:02:02";
		java.sql.Timestamp sqlDate2;
		try {
			sqlDate2 = new java.sql.Timestamp(sdf2.parse(date2).getTime());
			member_card_tableVO2.setReview_time(sqlDate2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		member_card_tableVO2.setWrong_number(5);
		member_card_tableVO2.setRight_number(6);
		member_card_tableVO2.setDaily_answer(7);
		member_card_tableVO2.setChoice_card_group(8);
		dao.update(member_card_tableVO2);
		System.out.println("修改成功!");

		
		
		
		// 查詢一筆
		Member_card_tableVO member_card_tableVO3 = dao.findByPrimaryKey("C00001", "MCG00003");
		System.out.print(member_card_tableVO3.getCard_id() + ", ");
		System.out.print(member_card_tableVO3.getMember_card_group_id() + ", ");
		System.out.print(member_card_tableVO3.getReview_time() + ", ");
		System.out.print(member_card_tableVO3.getWrong_number() + ", ");
		System.out.print(member_card_tableVO3.getRight_number() + ", ");
		System.out.print(member_card_tableVO3.getDaily_answer());
		System.out.println("---------------------");
		
		

		// 刪除
		dao.delete("C00001", "MCG00003");
		System.out.println("刪除成功!");
		
		
		
		// 查詢全部
		List<Member_card_tableVO> list = dao.getAll();
		for (Member_card_tableVO aMCT : list) {
			System.out.print(aMCT.getCard_id() + ", ");
			System.out.print(aMCT.getMember_card_group_id() + ", ");
			System.out.print(aMCT.getReview_time() + ", ");
			System.out.print(aMCT.getWrong_number() + ", ");
			System.out.print(aMCT.getRight_number() + ", ");
			System.out.print(aMCT.getDaily_answer());
			System.out.println();
		}
		
	}
	
	@Override
	public void insert(Member_card_tableVO member_card_tableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			//con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, member_card_tableVO.getCard_id() );
			pstmt.setString(2, member_card_tableVO.getMember_card_group_id() );
			pstmt.setTimestamp(3,  member_card_tableVO.getReview_time());
			pstmt.setInt(4, member_card_tableVO.getWrong_number());
			pstmt.setInt(5, member_card_tableVO.getRight_number());
			pstmt.setInt(6, member_card_tableVO.getDaily_answer());
			pstmt.setInt(7, member_card_tableVO.getChoice_card_group());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e){
			throw new RuntimeException("A class error occured. "
					+ e.getMessage());
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
	public void update(Member_card_tableVO member_card_tableVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			//con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setTimestamp(1,  member_card_tableVO.getReview_time());
			pstmt.setInt(2, member_card_tableVO.getWrong_number());
			pstmt.setInt(3, member_card_tableVO.getRight_number());
			pstmt.setInt(4, member_card_tableVO.getDaily_answer());
			pstmt.setInt(5, member_card_tableVO.getChoice_card_group());
			pstmt.setString(6, member_card_tableVO.getCard_id() );
			pstmt.setString(7, member_card_tableVO.getMember_card_group_id() );
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e){
			throw new RuntimeException("A class error occured. "
					+ e.getMessage());
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
	public void delete(String card_id, String member_card_group_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			//con = ds.getConnection();
			
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, card_id);
			pstmt.setString(2, member_card_group_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e){
			throw new RuntimeException("A class error occured. "
					+ e.getMessage());
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
	public Member_card_tableVO findByPrimaryKey(String card_id, String member_card_group_id) {

		Member_card_tableVO member_card_tableVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			//con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, card_id);
			pstmt.setString(2, member_card_group_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cardVo  Domain objects
				member_card_tableVO = new Member_card_tableVO();
				member_card_tableVO.setCard_id(rs.getString("card_id"));
				member_card_tableVO.setMember_card_group_id(rs.getString("member_card_group_id"));
				member_card_tableVO.setReview_time(rs.getTimestamp("review_time"));
				member_card_tableVO.setWrong_number(rs.getInt("wrong_number"));
				member_card_tableVO.setWrong_number(rs.getInt("right_number"));
				member_card_tableVO.setWrong_number(rs.getInt("daily_answer"));
				member_card_tableVO.setWrong_number(rs.getInt("choice_card_group"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e){
			throw new RuntimeException("A class error occured. "
					+ e.getMessage());
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
		return member_card_tableVO;
	}

	@Override
	public List<Member_card_tableVO> getAll() {
		
		List<Member_card_tableVO> list = new ArrayList<Member_card_tableVO>();
		Member_card_tableVO member_card_tableVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			//con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// member_card_tableVO Domain objects
				member_card_tableVO = new Member_card_tableVO();
				member_card_tableVO.setCard_id(rs.getString("card_id"));
				member_card_tableVO.setMember_card_group_id(rs.getString("member_card_group_id"));
				member_card_tableVO.setReview_time(rs.getTimestamp("review_time"));
				member_card_tableVO.setWrong_number(rs.getInt("wrong_number"));
				member_card_tableVO.setRight_number(rs.getInt("right_number"));
				member_card_tableVO.setDaily_answer(rs.getInt("daily_answer"));
				member_card_tableVO.setChoice_card_group(rs.getInt("choice_card_group"));
				list.add(member_card_tableVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e){
			throw new RuntimeException("A class error occured. "
					+ e.getMessage());
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
		return list;
	}
}