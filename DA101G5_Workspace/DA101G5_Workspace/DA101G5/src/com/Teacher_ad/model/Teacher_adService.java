package com.Teacher_ad.model;
import java.util.Date;
import java.util.HashSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.Teacher.model.TeacherService;
import com.Teacher.model.TeacherVO;



public class Teacher_adService {
	
	private Teacher_adDAO_interface dao;
	
	public Teacher_adService() {
		
		dao=new Teacher_adDAO();
	}
	
	public Teacher_adVO addTeacher_ad(String teacher_id, Integer ad_time, 
			java.sql.Timestamp ad_start,Integer ad_state) {

		Teacher_adVO teacher_adVO = new Teacher_adVO();

		teacher_adVO.setTeacher_id(teacher_id);
		teacher_adVO.setAd_time(ad_time);
		teacher_adVO.setAd_start(ad_start);
		teacher_adVO.setAd_state(ad_state);
		dao.insert(teacher_adVO);

		return teacher_adVO;
	}
	
	public Teacher_adVO addTeacher_ad(String member_id) {
		TeacherService teacherSvc=new TeacherService();
		TeacherVO teacherVO=teacherSvc.getOneTeacherWithMem(member_id);
		Teacher_adVO teacher_adVO = new Teacher_adVO();
		Timestamp ad_start=new java.sql.Timestamp(System.currentTimeMillis()-(1000*60*60*24));
		teacher_adVO.setTeacher_id(teacherVO.getTeacher_id());
		teacher_adVO.setAd_time(0);
		teacher_adVO.setAd_start(ad_start);
		teacher_adVO.setAd_state(0);
		dao.insert(teacher_adVO);

		return teacher_adVO;
		
	}

	//預留給 Struts 2 用的
	public void addTeacher_ad(Teacher_adVO teacher_adVO) {
		dao.insert(teacher_adVO);
	}
	
	public Teacher_adVO updateTeacher_ad(String teacher_ad_id,String teacher_id, Integer ad_time, 
			java.sql.Timestamp ad_start,Integer ad_state) {

		Teacher_adVO teacher_adVO = new Teacher_adVO();

		teacher_adVO.setTeacher_ad_id(teacher_ad_id);
		teacher_adVO.setTeacher_id(teacher_id);
		teacher_adVO.setAd_time(ad_time);
		teacher_adVO.setAd_start(ad_start);
		teacher_adVO.setAd_state(ad_state);
		dao.update(teacher_adVO);

		return dao.findByPrimaryKey(teacher_ad_id);
	}
	
	//預留給 Struts 2 用的
	public void updateTeacher_ad(Teacher_adVO teacher_adVO) {
		dao.update(teacher_adVO);
	}

	public void deleteTeacher_ad(String teacher_ad_id) {
		dao.delete(teacher_ad_id);
	}

	public Teacher_adVO getOneTeacher_ad(String teacher_ad_id) {
		return dao.findByPrimaryKey(teacher_ad_id);
	}

	public List<Teacher_adVO> getAll() {
		return dao.getAll();
	}
	
	
	public Set<Teacher_adVO> getSelectedAll() {
		long today=new Date().getTime();
		 List<Teacher_adVO> list=dao.getAll();
		 //處理廣告狀態
		for(int i=0;i<list.size();i++) {
			long ad_start=list.get(i).getAd_start().getTime();
			long ad_time1=(list.get(i).getAd_time())*24*60*60*1000;
			long ad_time=ad_time1+ad_start;
//System.out.println("ad_time：　"+ad_time);
//System.out.println("ad_time1：　"+ad_time1);
//System.out.println("ad_start: "+ad_start);
//System.out.println("today：　"+today);

			if(today<=ad_time&&today>=ad_start) {
				
				String ad_id=list.get(i).getTeacher_ad_id();
				Teacher_adVO adVO=dao.findByPrimaryKey(ad_id);
//System.out.println(ad_id);				
				 adVO.setAd_state(1);
//System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－TeacherADId: "+adVO.getTeacher_ad_id());
				 updateTeacher_ad(adVO);
//System.out.println(adVO.getAd_state());	
//System.out.println("廣告狀態為上架");
			}else {
				String ad_id=list.get(i).getTeacher_ad_id();
				Teacher_adVO adVO=dao.findByPrimaryKey(ad_id);
				
				adVO.setAd_state(0);
				updateTeacher_ad(adVO);

			}
		}
		Set<Teacher_adVO> set=new HashSet<Teacher_adVO>();
		//把廣告狀態為下架的移除
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getAd_state()==1) {
				set.add(list.get(i));
				
				
//				list.remove(i);
//				i--;
			}
//			else {
//			set.add(list.get(i));
//			list.remove(i);
//			i--;
//			}
		}
//		System.out.println(list.size());	
//		list.addAll(set);
//		if(list.iterator().hasNext()) {
//			if(list.iterator().next().getAd_state()==0) {
//				list.iterator().remove();
//			}
//			
//		}
//		System.out.println(list.size());
		return set;
	}
	public Teacher_adVO getOneTeacherAdUseTeacherId(String teacher_id) {
		List<Teacher_adVO> teacher_adList=getAll();
		for(Teacher_adVO teacher_adVO:teacher_adList) {
			if(teacher_adVO.getTeacher_id().equals(teacher_id)) {
				return teacher_adVO;
			}
		}
		return null;
	}

}
