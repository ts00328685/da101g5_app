package com.card.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CardDAO implements CardDAO_interface {
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA101G5";
//	String passwd = "123456";

	private static final String INSERT_STMT = "Insert into CARD "
			+ "(CARD_ID, TEACHER_ID, WORD, PHONETIC_SYMBOL, VOICE, NATIVE_EXPLAIN, EXAMPLE) values "
			+ "('C'||LPAD(to_char(card_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT * FROM card order by card_id";
	private static final String GET_ONE_STMT = "SELECT * FROM card where card_id = ?";
	private static final String DELETE = "DELETE FROM card where card_id = ?";
	private static final String UPDATE = "UPDATE card set teacher_id=?, word=?, PHONETIC_SYMBOL=?, VOICE=?, NATIVE_EXPLAIN=?, EXAMPLE=? where CARD_ID = ?";
	private static final String UPDATE_VOICE = "UPDATE card set VOICE=? where CARD_ID = ?";
	private static final String GET_ALL_BY_MCG_ID = "select * "
			+ "from member_card_table left join card on member_card_table.card_id = card.card_id "
			+ "where member_card_table.member_card_group_id = ? ORDER BY card.word ASC";

	
// JNDI
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//
//		CardDAO dao = new CardDAO();
//
//		byte testByteArray[]  = null;
//		
//		// 新增
//		CardVO cardVO1 = new CardVO();
//		cardVO1.setTeacher_id("T00001");
//		cardVO1.setWord("crazy");
//		cardVO1.setPhonetic_symbol("[ˋkrezɪ]");
//		cardVO1.setVoice(testByteArray);
//		cardVO1.setNative_explain("瘋狂的; 蠢的; 古怪的[+to-v]");
//		cardVO1.setExample("That guy is funcking crazy");
//		dao.insert(cardVO1);
//		System.out.println("新增成功!");
//		
//		
//		
//
//		// 修改
//		CardVO cardVO2 = new CardVO();
//		cardVO2.setCard_id("C00021");
//		cardVO2.setTeacher_id("T00002");
//		cardVO2.setWord("stupid");
//		cardVO2.setPhonetic_symbol("[ˋasds]");
//		cardVO2.setVoice(testByteArray);
//		cardVO2.setNative_explain("白癡");
//		cardVO2.setExample("you are so stupid!");
//		dao.update(cardVO2);
//		System.out.println("修改成功!");
//
//		// 刪除
//		dao.delete("C00021");
//		System.out.println("刪除成功!");
//
////		// 查詢一筆
//		CardVO cardVO3 = dao.findByPrimaryKey("C00020");
//		System.out.print(cardVO3.getCard_id() + ", ");
//		System.out.print(cardVO3.getTeacher_id() + ", ");
//		System.out.print(cardVO3.getWord() + ", ");
//		System.out.print(cardVO3.getPhonetic_symbol() + ", ");
//		System.out.print(cardVO3.getVoice() + ", ");
//		System.out.print(cardVO3.getNative_explain() + ", ");
//		System.out.println(cardVO3.getExample());
//		System.out.println("---------------------");
//
//		// 查詢全部
//		List<CardVO> list = dao.getAll();
//		for (CardVO aCard : list) {
//			System.out.print(aCard.getCard_id() + ", ");
//			System.out.print(aCard.getTeacher_id() + ", ");
//			System.out.print(aCard.getWord() + ", ");
//			System.out.print(aCard.getPhonetic_symbol() + ", ");
//			System.out.print(aCard.getVoice() + ", ");
//			System.out.print(aCard.getNative_explain() + ", ");
//			System.out.println(aCard.getExample());
//			System.out.println();
//		}
//	}
//	
//	
	@Override
	public void updateVoice(String card_id, byte[] voice) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
//			JNDI
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE_VOICE);
			
			pstmt.setString(2, card_id);
			pstmt.setBytes(1, voice);

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
	public void insert(CardVO cardVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			//con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, cardVO.getTeacher_id() );
			pstmt.setString(2, cardVO.getWord() );
			pstmt.setString(3, cardVO.getPhonetic_symbol() );
			pstmt.setBytes(4, cardVO.getVoice() );
			pstmt.setString(5, cardVO.getNative_explain() );
			pstmt.setString(6, cardVO.getExample());

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
	public void update(CardVO cardVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, cardVO.getTeacher_id() );
			pstmt.setString(2, cardVO.getWord() );
			pstmt.setString(3, cardVO.getPhonetic_symbol() );
			pstmt.setBytes(4, cardVO.getVoice() );
			pstmt.setString(5, cardVO.getNative_explain() );
			pstmt.setString(6, cardVO.getExample());
			pstmt.setString(7, cardVO.getCard_id());

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
	public void delete(String card_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, card_id);

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
	public CardVO findByPrimaryKey(String card_id) {

		CardVO cardVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, card_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cardVo  Domain objects
				cardVO = new CardVO();
				cardVO.setCard_id(rs.getString("card_id"));
				cardVO.setTeacher_id(rs.getString("teacher_id"));
				cardVO.setWord(rs.getString("word"));
				cardVO.setPhonetic_symbol(rs.getString("phonetic_symbol"));
				cardVO.setVoice(rs.getBytes("voice"));
				cardVO.setNative_explain(rs.getString("native_explain"));
				cardVO.setExample(rs.getString("example"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
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
		return cardVO;
	}

	@Override
	public List<CardVO> getAll() {
		List<CardVO> list = new ArrayList<CardVO>();
		CardVO cardVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cardVO Domain objects
				cardVO = new CardVO();
				cardVO.setCard_id(rs.getString("card_id"));
				cardVO.setTeacher_id(rs.getString("teacher_id"));
				cardVO.setWord(rs.getString("word"));
				cardVO.setPhonetic_symbol(rs.getString("phonetic_symbol"));
				cardVO.setVoice(rs.getBytes("voice"));
				cardVO.setNative_explain(rs.getString("native_explain"));
				cardVO.setExample(rs.getString("example"));
				list.add(cardVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
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
		return list;
	}


	@Override
	public List<CardVOApp> getAllByMCG_Id(String member_card_group_id) {
		
		List<CardVOApp> list = new ArrayList<CardVOApp>();
		CardVOApp cardVOApp = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			
			//JNDI
			con = ds.getConnection();
			
//			private static final String GET_ALL_BY_MCG_ID = "select card.card_id, word, phonetic_symbol, native_explain, example "
//					+ "from member_card_table left join card on member_card_table.card_id = card.card_id "
//					+ "where member_card_table.member_card_group_id = ? ORDER BY card.card_id ASC";
			
			pstmt = con.prepareStatement(GET_ALL_BY_MCG_ID);
			pstmt.setString(1, member_card_group_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// cardVO Domain objects
				cardVOApp = new CardVOApp();
				cardVOApp.setCard_id(rs.getString("card_id"));
				cardVOApp.setWord(rs.getString("word"));
				cardVOApp.setPhonetic_symbol(rs.getString("phonetic_symbol"));
				cardVOApp.setNative_explain(rs.getString("native_explain"));
				cardVOApp.setExample(rs.getString("example"));
				
				cardVOApp.setMember_card_group_id(member_card_group_id);
				cardVOApp.setReview_time(rs.getTimestamp("review_time"));
				cardVOApp.setWrong_number(rs.getInt("wrong_number"));
				cardVOApp.setRight_number(rs.getInt("right_number"));
				cardVOApp.setDaily_answer(rs.getInt("daily_answer"));
				cardVOApp.setChoice_card_group(rs.getInt("choice_card_group"));
				
				
				list.add(cardVOApp); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
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
		return list;
	}
	
	
	
}