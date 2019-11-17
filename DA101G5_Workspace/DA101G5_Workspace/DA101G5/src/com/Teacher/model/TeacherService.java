package com.Teacher.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;

import javax.servlet.http.Part;

import com.Teacher_ad.model.Teacher_adService;
import com.Teacher_ad.model.Teacher_adVO;

import oracle.net.aso.l;



public class TeacherService {
	
	private TeacherDAO_interface dao;
	
	public TeacherService() {
		dao= new TeacherDAO();
	}
	
	public TeacherVO addTeacher(String member_id, String work_experience,
			String ed_background, String certification, String teacher_introduce,  Integer teacher_state
			, Integer appraisal_accum, Integer appraisal_count,Integer course_price,byte[]introduce_pic) {

		TeacherVO teacherVO = new TeacherVO();


				 teacherVO = new TeacherVO();

				 teacherVO.setMember_id(member_id);
				 teacherVO.setWork_experience(work_experience);
				 teacherVO.setEd_background(ed_background);
				 teacherVO.setCertification(certification);
				 teacherVO.setTeacher_introduce(teacher_introduce);
				 teacherVO.setTeacher_state(teacher_state);
				 teacherVO.setAppraisal_accum(appraisal_accum);
				 teacherVO.setAppraisal_count(appraisal_count);
				 teacherVO.setCourse_price(course_price);
				 teacherVO.setIntroduce_pic(introduce_pic);
		dao.insert(teacherVO);

		return teacherVO;
	}

	//預留給 Struts 2 用的
	public void addTeacher(TeacherVO teacherVO) {
		dao.insert(teacherVO);
	}
	
	public TeacherVO updateTeacher(String teacher_id, String member_id, String work_experience,
			String ed_background, String certification, String teacher_introduce,  Integer teacher_state
			, Integer appraisal_accum, Integer appraisal_count,Integer course_price,byte[]introduce_pic
			) {

		TeacherVO teacherVO = new TeacherVO();

		teacherVO.setTeacher_id(teacher_id);
		 teacherVO.setMember_id(member_id);
		 teacherVO.setWork_experience(work_experience);
		 teacherVO.setEd_background(ed_background);
		 teacherVO.setCertification(certification);
		 teacherVO.setTeacher_introduce(teacher_introduce);
		 teacherVO.setTeacher_state(teacher_state);
		 teacherVO.setAppraisal_accum(appraisal_accum);
		 teacherVO.setAppraisal_count(appraisal_count);
		 teacherVO.setCourse_price(course_price);
		 teacherVO.setIntroduce_pic(introduce_pic);
		dao.update(teacherVO);

		return dao.findByPrimaryKey(teacher_id);
	}
	
	//預留給 Struts 2 用的
	public void updateTeacher(TeacherVO teacherVO) {
		dao.update(teacherVO);
	}

	public void deleteTeacher(String teacher_id) {
		dao.delete(teacher_id);
	}

	public TeacherVO getOneTeacher(String teacher_id) {
		return dao.findByPrimaryKey(teacher_id);
	}
	public TeacherVO getOneTeacherWithMem(String member_id) {
		TeacherVO teacherVO=new TeacherVO();
		teacherVO.setMember_id(member_id);
		List<TeacherVO>list=getAll();
//		if(list.contains(teacherVO)){
//			while(list.iterator().hasNext()) {
//				if(teacherVO.equals(list.iterator().next())) {
//				teacherVO=list.iterator().next();
//				return teacherVO;
//				}
//			}
//		}
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getMember_id().equals(member_id)) {
				String teacher_id=list.get(i).getTeacher_id();
				return dao.findByPrimaryKey(teacher_id);
			}
		}
		return null;
	}

	public List<TeacherVO> getAll() {
		return dao.getAll();
	}
	
	
//	===========================修改單筆State資料＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
	public void updateState(String teacher_id,   Integer teacher_state) {

		TeacherVO teacherVO = new TeacherVO();

		teacherVO.setTeacher_id(teacher_id);
		 
		 teacherVO.setTeacher_state(teacher_state);
		 
		dao.updateState(teacherVO);

//		return dao.findByPrimaryKey(teacher_id);
	}
//	=======================取得老師排序==================================
	public List<TeacherVO> getSelectedAll() {
		List<TeacherVO>listAll=dao.getAll();
		
//		Set<TeacherVO> list=new HashSet();
//		all.stream()
//			.filter(l ->l.getTeacher_state()!=0&&l.getTeacher_state()!=2)
//			.collect(list) );
		for(int i=0;i<listAll.size();i++) {
			if(listAll.get(i).getTeacher_state()!=1) {
				listAll.remove(i);
				--i;
			}
		}
		//依評價排序
		TreeSet<TeacherVO>  list =new TreeSet<TeacherVO> ();
		list.addAll(listAll);
		List<TeacherVO>listty=new ArrayList<TeacherVO>();
		listty.addAll(list);
//		for(int i=0;i<listty.size();i++) {
//			System.out.println(listty.get(i).getTeacher_id());
//		}
//		================================================
		
		Set<Teacher_adVO>set = new Teacher_adService().getSelectedAll();
//		List<Teacher_adVO>listAd=new ArrayList();
//		listAd.addAll(set);
//System.out.println("Teacher_adService().getSelectedAll()");
		Set<TeacherVO>listStore=new HashSet<TeacherVO>();//讓有買廣告的老師無序排列在前面
		for(Teacher_adVO teacher_adVO:set) {
			for(int i=0;i<listty.size();i++) {
				if(listty.get(i).getTeacher_id().equals(teacher_adVO.getTeacher_id())) {
					listStore.add(listty.get(i));
//System.out.println(listty.size());
					listty.remove(i);
//System.out.println(listty.size());
//					listty.remove(listty.get(i));
//System.out.println(listty.get(i).getTeacher_id());					
					--i;
//					list.remove(teacherVO);
//					listStore.add(teacherVO);
				}
			}
		}
		LinkedList<TeacherVO> selected=new LinkedList<TeacherVO>();
		selected.addAll(listStore);
//System.out.println("selected:"+selected.size());
		selected.addAll(selected.size(), listty);
//System.out.println("selected:"+selected.size());
//		=============================================
		
//		for(int i=0;i<list.size();i++) {
//			for(int j=0;j<listAd.size();j++) {
//				if(listAd.get(j).getTeacher_id().equals(list.get(i).getTeacher_id())) {
//				
//					listStore.add(list.get(i));
//					list.remove(i);
//					i--;//移除list元素 - 元素右邊全部向左移動
//				}	
//			}
//		}
//		list2.addAll(listStore2);
//		list2.addAll(0, listStore);
//		listStore.addAll(listStore2);
		
//		list.addAll(0,listStore);
		return selected;
	}
	
	

//	------------------------------------------------------------
	
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	
	

}
