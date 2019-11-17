package com.que.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestClobString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "DA101G5";
		String passwd = "123456";
		
		String select = "select quecontent from question where que_id = 'Q00001'";
		String test = "JAVA";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statement st = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			st = con.createStatement();
			rs = st.executeQuery(select);
			while(rs.next()) {
				String str = rs.getString(1);
				System.out.println(str);
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

}
