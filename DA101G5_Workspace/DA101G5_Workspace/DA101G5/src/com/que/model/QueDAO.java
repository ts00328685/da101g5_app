package com.que.model;

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

import com.que.model.QueVO;

public class QueDAO implements QueDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "insert into question (que_id,member_id,quetime,quethumb,quetitle,quecontent,quefile,questatus,watchcount,rescount,filetype) values ('Q'||LPAD(to_char(question_seq.NEXTVAL), 5, '0'),?,to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'),0,?,?,?,1,0,0,?)";

	private static final String INSERT_QHASHTAG = "insert into qhashtag(que_id,hashtag_id) values (?,?)";

//	private static final String GETLATEST = "SELECT * FROM question where quetime = (select max(quetime) from question)";

	private static final String UPDATE = "UPDATE question set quethumb=?, watchcount=?, rescount=? where que_id=?";

	private static final String EDIT = "update question set quefile=?,filetype=? where que_id=? ";// qhashtag要一起改
																									// 先delete 再新增

	private static final String DELETE_QHASHTAG = "delete from qhashtag where que_id = ?";

	private static final String DELETE = "delete from question where que_id = ?";// ON DELETE CASCADE 建表設定 免開交易即可刪除res
																					// favorque qhashtag

	private static final String HIDE = "UPDATE question set questatus=0 where que_id=?";// res(done) favorque 要一起

	private static final String RES_HIDE = "UPDATE response set resstatus=0 where belongto=?";

	private static final String ONSHELF = "UPDATE question set questatus=1 where que_id=?";

	private static final String RES_ONSHELF = "UPDATE response set resstatus=1 where belongto=?";

	private static final String FIND_BY_PK = "SELECT * from question where que_id=?";

	private static final String GETALL/* BY TIME DESC */ = "SELECT * from question ORDER BY quetime desc";

	@Override
	public void insert(QueVO queVO, String[] qhashtagArray) {
		Connection con = null;/**/
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String latestQue_id = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);

			// 交易開始 新增+取回主鍵
//			int[] xx = { 1 };
			String[] yy = { "QUE_ID" };
			pstmt = con.prepareStatement(INSERT, yy);
			pstmt.setString(1, queVO.getMember_id());
			pstmt.setString(2, queVO.getQuetitle());
			pstmt.setString(3, queVO.getQuecontent());
			pstmt.setBytes(4, queVO.getQuefile());
			pstmt.setString(5, queVO.getFiletype());
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				latestQue_id = rs.getString(1);
			}

			// 存入qhashtag
			pstmt = con.prepareStatement(INSERT_QHASHTAG);
			for (String qhashtag : qhashtagArray) {

				pstmt.setString(1, latestQue_id);
				pstmt.setString(2, qhashtag);
				pstmt.executeUpdate();
			}
			con.commit();

			// 交易結束

			con.setAutoCommit(true);
			// Handle any SQL errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	public void edit(byte[] quefile, String filetype, String que_id, String[] hashtagArray) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		

		try {
			con = ds.getConnection();
			// 交易開始
			con.setAutoCommit(false);

			// 更改file,filetype
			pstmt = con.prepareStatement(EDIT);
			pstmt.setBytes(1, quefile);
			pstmt.setString(2, filetype);
			pstmt.setString(3, que_id);
			pstmt.executeUpdate();

			// 刪除舊的qhashtag
			pstmt = con.prepareStatement(DELETE_QHASHTAG);
			pstmt.setString(1, que_id);
			pstmt.executeUpdate();

			// 新增qhashtag
			pstmt = con.prepareStatement(INSERT_QHASHTAG);
			for (String qhashtag : hashtagArray) {

				pstmt.setString(1, que_id);
				pstmt.setString(2, qhashtag);
				pstmt.executeUpdate();
			}
			con.commit();
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	public void update(QueVO queVO) {/* 修改觀看.回應.狀態.讚 */
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, queVO.getQuethumb());
			pstmt.setInt(2, queVO.getWatchcount());
			pstmt.setInt(3, queVO.getRescount());
			pstmt.setString(4, queVO.getQue_id());
			pstmt.executeUpdate();
			// Handle any driver errors
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
	public void delete(String que_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, que_id);
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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
	public void hide(String que_id) {/* 下架 RES一起 */
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(HIDE);

			/* 交易開始 */
			pstmt.setString(1, que_id);
			pstmt.executeUpdate();

			pstmt2 = con.prepareStatement(RES_HIDE);
			pstmt2.setString(1, que_id);
			pstmt2.executeUpdate();
			con.commit();
			/* 交易結束 */
			// Handle any driver errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Clean up JDBC resources
		} finally {
			if (pstmt2 != null) {
				try {
					pstmt2.close();
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

	}

	@Override
	public void onshelf(String que_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(ONSHELF);
			pstmt2 = con.prepareStatement(RES_ONSHELF);

			/* 交易開始 */
			pstmt.setString(1, que_id);
			pstmt.executeUpdate();
			pstmt2.setString(1, que_id);
			pstmt2.executeUpdate();
			con.commit();
			/* 交易結束 */
			// Handle any driver errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Clean up JDBC resources
		} finally {
			if (pstmt2 != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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

	}

	@Override
	public QueVO findByPK(String que_id) {
		QueVO queVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, que_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				queVO = new QueVO();
				queVO.setQue_id(rs.getString(1));
				queVO.setMember_id(rs.getString(2));
				queVO.setQuetime(rs.getTimestamp(3));
				queVO.setQuethumb(rs.getInt(4));
				queVO.setQuetitle(rs.getString(5));
				queVO.setQuecontent(rs.getString(6));
				queVO.setQuefile(rs.getBytes(7))/* (rs.getBlob(7).getBytes(0,(int)rs.getBlob(7).length())) */;
				queVO.setQuestatus(rs.getInt(8));
				queVO.setWatchcount(rs.getInt(9));
				queVO.setRescount(rs.getInt(10));
				queVO.setFiletype(rs.getString(11));
			}

			// Handle any driver errors
		} catch (SQLException se) {
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
		return queVO;
	}

	@Override
	public List<QueVO> getAll() {

		List<QueVO> quelist = new ArrayList<QueVO>();
		QueVO queVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				queVO = new QueVO();
				queVO.setQue_id(rs.getString(1));
				queVO.setMember_id(rs.getString(2));
				queVO.setQuetime(rs.getTimestamp(3));
				queVO.setQuethumb(rs.getInt(4));
				queVO.setQuetitle(rs.getString(5));
				queVO.setQuecontent(rs.getString(6));
				queVO.setQuefile(rs.getBytes(7));
				queVO.setQuestatus(rs.getInt(8));
				queVO.setWatchcount(rs.getInt(9));
				queVO.setRescount(rs.getInt(10));
				queVO.setFiletype(rs.getString(11));
				quelist.add(queVO);
			}
		} catch (SQLException se) {
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
		return quelist;
	}

}
