package com.Time_order.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class Time_orderJDBCDAO implements Time_orderDAO_interface{
	
	@Override
	public void updateTOState(String course_order_id, String time_order_id, String total_hour, String c_state,
			String c_judge) {
		// TODO Auto-generated method stub
		
	}

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA101G5";
	String passwd = "123456";

	    private static final String INSERT_STMT = 
			"INSERT INTO time_order (time_order_id,teacher_id,course_order_id,language_id,sort_course_id,c_state,c_judge,start_time,end_time) VALUES ('TO'||LPAD(to_char(TIME_ORDER_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT time_order_id,teacher_id,course_order_id,language_id,sort_course_id,c_state,c_judge,to_char(start_time,'yyyy-mm-dd')start_time,to_char(end_time,'yyyy-mm-dd') end_time FROM time_order order by time_order_id desc";
		private static final String GET_ONE_STMT = 
			"SELECT time_order_id,teacher_id,course_order_id,language_id,sort_course_id,c_state,c_judge,to_char(start_time,'yyyy-mm-dd')start_time,to_char(end_time,'yyyy-mm-dd') end_time FROM time_order where time_order_id = ?";
		private static final String DELETE = 
			"DELETE FROM time_order where time_order_id = ?";
		private static final String UPDATE = 
			"UPDATE time_order set teacher_id=?, course_order_id=?, language_id=?, sort_course_id=?, c_state=?, c_judge=?,start_time=?,end_time=? where time_order_id = ?";

		@Override
		public String insert(Time_orderVO time_orderVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, time_orderVO.getTeacher_id());
				pstmt.setString(2, time_orderVO.getCourse_order_id());
				pstmt.setString(3, time_orderVO.getLanguage_id());
				pstmt.setString(4, time_orderVO.getSort_course_id());
				pstmt.setInt(5, time_orderVO.getC_state());
				pstmt.setInt(6, time_orderVO.getC_judge());
				pstmt.setTimestamp(7,  time_orderVO.getStart_time());
				pstmt.setTimestamp(8,  time_orderVO.getEnd_time());

				pstmt.executeUpdate();

				// Handle any SQL errors
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
			return null;
			
		}

		@Override
		public void update(Time_orderVO time_orderVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				
				pstmt.setString(1, time_orderVO.getTeacher_id());
				pstmt.setString(2, time_orderVO.getCourse_order_id());
				pstmt.setString(3, time_orderVO.getLanguage_id());
				pstmt.setString(4, time_orderVO.getSort_course_id());
				pstmt.setInt(5, time_orderVO.getC_state());
				pstmt.setInt(6, time_orderVO.getC_judge());
				pstmt.setTimestamp(7,  time_orderVO.getStart_time());
				pstmt.setTimestamp(8,  time_orderVO.getEnd_time());
				pstmt.setString(9, time_orderVO.getTime_order_id());

				pstmt.executeUpdate();

				// Handle any driver errors
			}  catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
		public void delete(String time_order_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, time_order_id);

				pstmt.executeUpdate();

				// Handle any driver errors
			}  catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
		public Time_orderVO findByPrimaryKey(String time_order_id) {
			Time_orderVO time_orderVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, time_order_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					time_orderVO = new Time_orderVO();
					time_orderVO.setTime_order_id(rs.getString("time_order_id"));
					time_orderVO.setTeacher_id(rs.getString("teacher_id"));
					time_orderVO.setCourse_order_id(rs.getString("course_order_id"));
					time_orderVO.setLanguage_id(rs.getString("language_id"));
					time_orderVO.setSort_course_id(rs.getString("sort_course_id"));
					time_orderVO.setC_state(rs.getInt("c_state"));
					time_orderVO.setC_judge(rs.getInt("c_judge"));
					time_orderVO.setStart_time(rs.getTimestamp("start_time"));
					time_orderVO.setEnd_time(rs.getTimestamp("end_time"));
				}

				// Handle any driver errors
			}  catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
			return time_orderVO;
		}

		@Override
		public List<Time_orderVO> getAll() {
			List<Time_orderVO> list = new ArrayList<Time_orderVO>();
			Time_orderVO time_orderVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// time_orderVO 也稱為 Domain objects
					time_orderVO = new Time_orderVO();
					time_orderVO.setTime_order_id(rs.getString("time_order_id"));
					time_orderVO.setTeacher_id(rs.getString("teacher_id"));
					time_orderVO.setCourse_order_id(rs.getString("course_order_id"));
					time_orderVO.setLanguage_id(rs.getString("language_id"));
					time_orderVO.setSort_course_id(rs.getString("sort_course_id"));
					time_orderVO.setC_state(rs.getInt("c_state"));
					time_orderVO.setC_judge(rs.getInt("c_judge"));
					time_orderVO.setStart_time(rs.getTimestamp("start_time"));
					time_orderVO.setEnd_time(rs.getTimestamp("end_time"));
					list.add(time_orderVO); // Store the row in the list
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
		public List<Time_orderVOApp> getAllJoinCourseOrder(String member_id) {
			// TODO Auto-generated method stub
			return null;
		}
		
//		public static void main(String[] args) {
//			Time_orderJDBCDAO dao = new Time_orderJDBCDAO();
//			
//			// 新增
//			Time_orderVO time_orderVO1 = new Time_orderVO();
//			time_orderVO1.setTeacher_id("T00005");
//			time_orderVO1.setCourse_order_id("CO00001");
//		    time_orderVO1.setLanguage_id("L00001");
//			time_orderVO1.setSort_course_id("SC00001");
//			time_orderVO1.setC_state(1);
//			time_orderVO1.setC_judge(5);
//			time_orderVO1.setStart_time(java.sql.Date.valueOf("2005-01-01"));
//			time_orderVO1.setEnd_time(java.sql.Date.valueOf("2005-01-01"));
//			dao.insert(time_orderVO1);
//			System.out.println("新增成功");
//			
//			
//			// 修改
//			Time_orderVO time_orderVO2 = new Time_orderVO();
//			time_orderVO2.setTeacher_id("TO00001");
//			time_orderVO2.setTeacher_id("T00005");
//			time_orderVO2.setCourse_order_id("CO00001");
//		    time_orderVO2.setLanguage_id("L00001");
//			time_orderVO2.setSort_course_id("SC00001");
//			time_orderVO2.setC_state(1);
//			time_orderVO2.setC_judge(5);
//			time_orderVO2.setStart_time(java.sql.Date.valueOf("2006-01-01"));
//			time_orderVO2.setEnd_time(java.sql.Date.valueOf("2006-01-01"));
//			
//			dao.update(time_orderVO2);
//			
////			// 刪除
////			dao.delete("TO00001");
//			
//			
//			// 查詢
//			List<Time_orderVO> list = dao.getAll();
//			for (Time_orderVO time_orderVO : list) {
//				System.out.println(time_orderVO.getTime_order_id());
//				System.out.println(time_orderVO.getTeacher_id());
//				System.out.println(time_orderVO.getCourse_order_id());
//				System.out.println(time_orderVO.getLanguage_id());
//				System.out.println(time_orderVO.getSort_course_id());
//				System.out.println(time_orderVO.getC_state());
//				System.out.println(time_orderVO.getC_judge());
//				System.out.println(time_orderVO.getStart_time());
//				System.out.println(time_orderVO.getEnd_time());
//				System.out.println("查詢成功");
//			}
//		}

}
