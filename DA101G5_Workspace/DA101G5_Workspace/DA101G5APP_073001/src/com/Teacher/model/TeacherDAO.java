package com.Teacher.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Course_order.model.Course_orderDAO;
import com.Course_order.model.Course_orderVO;
import com.Language.model.LanguageDAO;
import com.Language.model.LanguageVO;
import com.member.model.MemberDAO;
import com.member.model.MemberVO;

public class TeacherDAO implements TeacherDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO teacher (teacher_id,member_id,work_experience,ed_background,certification,teacher_introduce,teacher_state,appraisal_accum,appraisal_count,course_price,introduce_pic) VALUES ('T'||LPAD(to_char(TEACHER_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT teacher_id,member_id,work_experience,ed_background,certification,teacher_introduce,teacher_state,appraisal_accum,appraisal_count,course_price,introduce_pic FROM teacher order by teacher_id desc";

	private static final String GET_ALL_TEACHER_CARD_STMT = "SELECT teacher_id,member_id,work_experience,ed_background,certification,teacher_state,appraisal_accum,appraisal_count,course_price,introduce_pic, teacher_introduce FROM teacher order by teacher_id desc";
	private static final String GET_ONE_TEACHER_CARD_STMT = "SELECT teacher_id,member_id,work_experience,ed_background,certification,teacher_state,appraisal_accum,appraisal_count,course_price,introduce_pic, teacher_introduce FROM teacher where teacher_id = ?";
	
	private static final String GET_ONE_STMT = "SELECT teacher_id,member_id,work_experience,ed_background,certification,teacher_introduce,teacher_state,appraisal_accum,appraisal_count,course_price,introduce_pic FROM teacher where teacher_id = ?";
	private static final String DELETE = "DELETE FROM teacher where teacher_id = ?";
	private static final String UPDATE = "UPDATE teacher set member_id=?, work_experience=?, ed_background=?, certification=?, teacher_introduce=?, teacher_state=?,appraisal_accum=?,appraisal_count=?,course_price=?,introduce_pic=? where teacher_id = ?";
	private static final String UPDATE_PIC = "UPDATE teacher set introduce_pic=? where teacher_id = ?";
	private static final String GET_PIC = "select introduce_pic from teacher where teacher_id = ?";
	
	
	@Override
	public List<MyTeacherCardVO> getMyTeacherCard(String member_id) {

		List<MyTeacherCardVO> myTeacher_cardVOlist = new ArrayList<MyTeacherCardVO>();
		List<Course_orderVO> course_orderVOlist = new ArrayList<Course_orderVO>();
		
		Course_orderVO courseVO = null;
		Course_orderDAO courseDAO = new Course_orderDAO();
		
		MyTeacherCardVO myTeacherCardVO = null;
		
		TeacherCardVO teacherCardVO = null;
		TeacherDAO teacherDAO = new TeacherDAO();
		
		MemberVO memberVO = null;
		MemberDAO memberDAO = new MemberDAO();
		LanguageVO languageVO = null;
		LanguageDAO languageDAO = new LanguageDAO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			
			//得到個人所有課程訂單
			course_orderVOlist = courseDAO.getMyCourseOrders(member_id);
			System.out.println("T DAO member_id=" + member_id);
			//計算同一老師的剩餘唐樹
			Map<String, Integer> remainHourMap = new HashMap<String, Integer>(); 
			for(Course_orderVO coVO: course_orderVOlist) {
				Integer hour = (remainHourMap.get(coVO.getTeacher_id()) == null) ? 0 : remainHourMap.get(coVO.getTeacher_id());
				remainHourMap.put(coVO.getTeacher_id(), hour + coVO.getRemain_hour());
			}
			
			for(Course_orderVO coVO : course_orderVOlist) {
				
				teacherCardVO = teacherDAO.getOneTeacherCard(coVO.getTeacher_id());
				
				String temp = teacherCardVO.getWork_experience();
				if(temp != null)
				temp = temp.replaceAll("\\s","");
				
				teacherCardVO.setWork_experience(temp);
				
				temp = teacherCardVO.getEd_background();
				if(temp != null)
				temp = temp.replaceAll("\\s","");
				
				teacherCardVO.setEd_background(temp);
				
				temp = teacherCardVO.getCertification();
				
				if(temp != null)
				temp = temp.replaceAll("\\s","");
				
				teacherCardVO.setCertification(temp);
				
				temp = teacherCardVO.getTeacher_introduce();
				if(temp != null)
				temp = temp.replaceAll("\\s","");
				
				teacherCardVO.setTeacher_introduce(temp);
				
				
				
				
				myTeacherCardVO = new MyTeacherCardVO();
				
				myTeacherCardVO.setTeacher_id(teacherCardVO.getTeacher_id());
				myTeacherCardVO.setMember_id(teacherCardVO.getMember_id());
				myTeacherCardVO.setWork_experience(teacherCardVO.getWork_experience());
				myTeacherCardVO.setEd_background(teacherCardVO.getEd_background());
				myTeacherCardVO.setCertification(teacherCardVO.getCertification());
				myTeacherCardVO.setTeacher_introduce(teacherCardVO.getTeacher_introduce());
				myTeacherCardVO.setTeacher_state(teacherCardVO.getTeacher_state());
				
				myTeacherCardVO.setAppraisal_accum(teacherCardVO.getAppraisal_accum());
				myTeacherCardVO.setAppraisal_count(teacherCardVO.getAppraisal_count());
				myTeacherCardVO.setCourse_price(teacherCardVO.getCourse_price());

				memberVO = memberDAO.findByPrimaryKey(teacherCardVO.getMember_id());

				myTeacherCardVO.setMem_country(memberVO.getMem_country());
				myTeacherCardVO.setMem_name(memberVO.getMem_name());
				myTeacherCardVO.setMem_nick(memberVO.getMem_nick());

				languageVO = languageDAO.findByPrimaryKey("L00002");
				myTeacherCardVO.setLanguage(languageVO.getLanguage());
				
				myTeacherCardVO.setRemain_hour(remainHourMap.get(teacherCardVO.getTeacher_id()));
				
				myTeacher_cardVOlist.remove(myTeacherCardVO);
				myTeacher_cardVOlist.add(myTeacherCardVO);
				
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
		Collections.reverse(myTeacher_cardVOlist);
		return myTeacher_cardVOlist;

	}
	
	@Override
	public byte[] getImage(String teacher_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] introduce_pic = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PIC);

			pstmt.setString(1, teacher_id);

			rs = pstmt.executeQuery();
			
			rs.next();
			
			introduce_pic = rs.getBytes(1);
			

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
		return introduce_pic;
		
	}

	@Override
	public void updatePic(String teacher_id, byte[] introduce_pic) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PIC);

			pstmt.setBytes(1, introduce_pic);
			pstmt.setString(2, teacher_id);

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
	public List<TeacherCardVO> getAllTeacherCard() {

		List<TeacherCardVO> list = new ArrayList<TeacherCardVO>();
		TeacherCardVO teacherCardVO = null;

		MemberVO memberVO = null;
		MemberDAO memberDAO = new MemberDAO();
		LanguageVO languageVO = null;
		LanguageDAO languageDAO = new LanguageDAO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_TEACHER_CARD_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// teacherVO 也稱為 Domain objects
				teacherCardVO = new TeacherCardVO();
				teacherCardVO.setTeacher_id(rs.getString("teacher_id"));
				teacherCardVO.setMember_id(rs.getString("member_id"));
				
				String temp = rs.getString("work_experience");
				if(temp != null)
				temp = temp.replaceAll("\\s","");
				
				teacherCardVO.setWork_experience(temp);
				
				temp = rs.getString("ed_background");
				if(temp != null)
				temp = temp.replaceAll("\\s","");
				
				teacherCardVO.setEd_background(temp);
				
				temp = rs.getString("certification");
				
				if(temp != null)
				temp = temp.replaceAll("\\s","");
				
				teacherCardVO.setCertification(temp);
				
				temp = rs.getString("teacher_introduce");
				if(temp != null)
				temp = temp.replaceAll("\\s","");
				
				teacherCardVO.setTeacher_introduce(temp);
				
				teacherCardVO.setTeacher_state(rs.getInt("teacher_state"));
				teacherCardVO.setAppraisal_accum(rs.getInt("appraisal_accum"));
				teacherCardVO.setAppraisal_count(rs.getInt("appraisal_count"));
				teacherCardVO.setCourse_price(rs.getInt("course_price"));
				

				memberVO = memberDAO.findByPrimaryKey(rs.getString("member_id"));

				teacherCardVO.setMem_country(memberVO.getMem_country());
				teacherCardVO.setMem_name(memberVO.getMem_name());
				teacherCardVO.setMem_nick(memberVO.getMem_nick());

				languageVO = languageDAO.findByPrimaryKey("L00002");
				teacherCardVO.setLanguage(languageVO.getLanguage());

				list.add(teacherCardVO); // Store the row in the list
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
		return list;

	}
	
	@Override
	public TeacherCardVO getOneTeacherCard(String teacher_id) {

		TeacherCardVO teacherCardVO = null;

		MemberVO memberVO = null;
		MemberDAO memberDAO = new MemberDAO();
		LanguageVO languageVO = null;
		LanguageDAO languageDAO = new LanguageDAO();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_TEACHER_CARD_STMT);
			pstmt.setString(1, teacher_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// teacherVO 也稱為 Domain objects
				teacherCardVO = new TeacherCardVO();
				teacherCardVO.setTeacher_id(rs.getString("teacher_id"));
				teacherCardVO.setMember_id(rs.getString("member_id"));
				teacherCardVO.setWork_experience(rs.getString("work_experience"));
				teacherCardVO.setEd_background(rs.getString("ed_background"));
				teacherCardVO.setCertification(rs.getString("certification"));
				teacherCardVO.setTeacher_introduce(rs.getString("teacher_introduce"));
				teacherCardVO.setTeacher_state(rs.getInt("teacher_state"));
				teacherCardVO.setAppraisal_accum(rs.getInt("appraisal_accum"));
				teacherCardVO.setAppraisal_count(rs.getInt("appraisal_count"));
				teacherCardVO.setCourse_price(rs.getInt("course_price"));
				

				memberVO = memberDAO.findByPrimaryKey(rs.getString("member_id"));

				teacherCardVO.setMem_country(memberVO.getMem_country());
				teacherCardVO.setMem_name(memberVO.getMem_name());
				teacherCardVO.setMem_nick(memberVO.getMem_nick());

				languageVO = languageDAO.findByPrimaryKey("L00002");
				teacherCardVO.setLanguage(languageVO.getLanguage());

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
		return teacherCardVO;

	}
	@Override
	public void insert(TeacherVO teacherVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, teacherVO.getMember_id());
			pstmt.setString(2, teacherVO.getWork_experience());
			pstmt.setString(3, teacherVO.getEd_background());
			pstmt.setString(4, teacherVO.getCertification());
			pstmt.setString(5, teacherVO.getTeacher_introduce());
			pstmt.setInt(6, teacherVO.getTeacher_state());
			pstmt.setInt(7, teacherVO.getAppraisal_accum());
			pstmt.setInt(8, teacherVO.getAppraisal_count());
			pstmt.setInt(9, teacherVO.getCourse_price());
			pstmt.setBytes(10, teacherVO.getIntroduce_pic());

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(TeacherVO teacherVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, teacherVO.getMember_id());
			pstmt.setString(2, teacherVO.getWork_experience());
			pstmt.setString(3, teacherVO.getEd_background());
			pstmt.setString(4, teacherVO.getCertification());
			pstmt.setString(5, teacherVO.getTeacher_introduce());
			pstmt.setInt(6, teacherVO.getTeacher_state());
			pstmt.setInt(7, teacherVO.getAppraisal_accum());
			pstmt.setInt(8, teacherVO.getAppraisal_count());
			pstmt.setInt(9, teacherVO.getCourse_price());
			pstmt.setBytes(10, teacherVO.getIntroduce_pic());
			pstmt.setString(11, teacherVO.getTeacher_id());

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
	public void delete(String teacher_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, teacher_id);

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
	public TeacherVO findByPrimaryKey(String teacher_id) {
		TeacherVO teacherVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, teacher_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				teacherVO = new TeacherVO();
				teacherVO.setTeacher_id(rs.getString("teacher_id"));
				teacherVO.setMember_id(rs.getString("member_id"));
				teacherVO.setWork_experience(rs.getString("work_experience"));
				teacherVO.setEd_background(rs.getString("ed_background"));
				teacherVO.setCertification(rs.getString("certification"));
				teacherVO.setTeacher_introduce(rs.getString("teacher_introduce"));
				teacherVO.setTeacher_state(rs.getInt("teacher_state"));
				teacherVO.setAppraisal_accum(rs.getInt("appraisal_accum"));
				teacherVO.setAppraisal_count(rs.getInt("appraisal_count"));
				teacherVO.setCourse_price(rs.getInt("course_price"));
				teacherVO.setIntroduce_pic(rs.getBytes("introduce_pic"));
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
		return teacherVO;
	}

	@Override
	public List<TeacherVO> getAll() {
		List<TeacherVO> list = new ArrayList<TeacherVO>();
		TeacherVO teacherVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// teacherVO 也稱為 Domain objects
				teacherVO = new TeacherVO();
				teacherVO.setTeacher_id(rs.getString("teacher_id"));
				teacherVO.setMember_id(rs.getString("member_id"));
				teacherVO.setWork_experience(rs.getString("work_experience"));
				teacherVO.setEd_background(rs.getString("ed_background"));
				teacherVO.setCertification(rs.getString("certification"));
				teacherVO.setTeacher_introduce(rs.getString("teacher_introduce"));
				teacherVO.setTeacher_state(rs.getInt("teacher_state"));
				teacherVO.setAppraisal_accum(rs.getInt("appraisal_accum"));
				teacherVO.setAppraisal_count(rs.getInt("appraisal_count"));
				teacherVO.setCourse_price(rs.getInt("course_price"));
				teacherVO.setIntroduce_pic(rs.getBytes("introduce_pic"));
				list.add(teacherVO); // Store the row in the list
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
		return list;
	}

//	public static void main(String[] args) {
//		//		
//				TeacherJDBCDAO dao=new TeacherJDBCDAO();
//		//		
//		////		 新增
//		//		
//		//		 TeacherVO teacherVO1 = new TeacherVO();
//		//		 teacherVO1 = new TeacherVO();
////				 teacherVO1.setTeacher_id("T00012");
//		//		 teacherVO1.setMember_id("M00011");
//		//		 teacherVO1.setWork_experience("11");
//		//		 teacherVO1.setEd_background("11");
//		//		 teacherVO1.setCertification("11");
//		//		 teacherVO1.setTeacher_introduce("11");
//		//		 teacherVO1.setTeacher_state(0);
//		//		 teacherVO1.setAppraisal_accum(10);
//		//		 teacherVO1.setAppraisal_count(20);
//		//		 teacherVO1.setCourse_price(500);
//		//		 teacherVO1.setIntroduce_pic(null);
//		//		dao.insert(teacherVO1);
//		//		
//		//
//		////		// 修改
//		//			TeacherVO teacherVO2 = new TeacherVO();
//		//			teacherVO2 = new TeacherVO();
//		//			teacherVO2.setTeacher_id("T00004");
//		//			teacherVO2.setMember_id("M00004");
//		//			teacherVO2.setWork_experience("11");
//		//			teacherVO2.setEd_background("11");
//		//		    teacherVO2.setCertification("11");
//		//			teacherVO2.setTeacher_introduce("11");
//		//			teacherVO2.setTeacher_state(1);
//		//			teacherVO2.setAppraisal_accum(1);
//		//			teacherVO2.setAppraisal_count(1);
//		//			teacherVO2.setCourse_price(1);
//		//     		teacherVO2.setIntroduce_pic(null);
//		//			dao.update(teacherVO2);
//		//		
//		//			// 刪除
//		//			dao.delete("T00002");
//		//			
//		//			// 查詢
//					TeacherVO teacherVO3 = dao.findByPrimaryKey("T00001");
//					System.out.println(teacherVO3.getTeacher_id() + ",");
//					System.out.println(teacherVO3.getMember_id() + ",");
//					System.out.println(teacherVO3.getWork_experience() + ",");
//					System.out.println(teacherVO3.getEd_background() + ",");
//					System.out.println(teacherVO3.getCertification() + ",");
//					System.out.println(teacherVO3.getTeacher_introduce() + ",");
//					System.out.println(teacherVO3.getTeacher_state() + ",");
//					System.out.println(teacherVO3.getAppraisal_accum() + ",");
//					System.out.println(teacherVO3.getAppraisal_count() + ",");
//					System.out.println(teacherVO3.getCourse_price() + ",");
//					System.out.println(teacherVO3.getIntroduce_pic() + ",");
//					System.out.println("---------------------");
//		//		
//		//		
//		////		// 查詢(全部)
//		//		List<TeacherVO> list=dao.getAll();
//		//			 
//		//		for (TeacherVO teacherVO : list) {
//		//			System.out.println(teacherVO.getTeacher_id() + ",");
//		//			System.out.println(teacherVO.getMember_id() + ",");
//		//			System.out.println(teacherVO.getWork_experience() + ",");
//		//			System.out.println(teacherVO.getEd_background() + ",");
//		//			System.out.println(teacherVO.getCertification() + ",");
//		//			System.out.println(teacherVO.getTeacher_introduce() + ",");
//		//			System.out.println(teacherVO.getTeacher_state() + ",");
//		//			System.out.println(teacherVO.getAppraisal_accum() + ",");
//		//			System.out.println(teacherVO.getAppraisal_count() + ",");
//		//			System.out.println(teacherVO.getCourse_price() + ",");
//		//			System.out.println(teacherVO.getIntroduce_pic() + ",");
//		//		}
//			}
//
//	
//	

}
