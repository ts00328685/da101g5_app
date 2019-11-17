package com.recruitmessage.model;

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

public class RecruitmessageDAO implements RecruitmessageDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "insert into recruitmessage (message_no,sender_id,reciver_id,message,sendtime) values (recruitmessage_seq.NEXTVAL,?,?,?,to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'))";
	
	private static final String getAll = "select * from recruitmessage where reciver_id=? ORDER BY sendtime desc";

	@Override
	public void insert(String sender_id, String reciver_id, String message) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, sender_id);
			pstmt.setString(2,reciver_id);
			pstmt.setString(3, message);
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public List<RecruitmessageVO> getAll(String reciver_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RecruitmessageVO> recruitmessagelist = new ArrayList<RecruitmessageVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll);
			pstmt.setString(1, reciver_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RecruitmessageVO  recruitmessageVO = new RecruitmessageVO();
				recruitmessageVO.setMessage_no(rs.getInt(1));
				recruitmessageVO.setSender_id(rs.getString(2));
				recruitmessageVO.setReciver_id(rs.getString(3));
				recruitmessageVO.setMessage(rs.getString(4));
				recruitmessageVO.setSendtime(rs.getTimestamp(5));
				recruitmessagelist.add(recruitmessageVO);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return recruitmessagelist;
	}
	
	
}
