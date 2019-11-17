package com.Teacher_course_list.model;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Teacher_course_listService {
	
	private Teacher_course_listDAO_interface dao;
	
	public Teacher_course_listService() {
		
		dao=new Teacher_course_listDAO();
	}
	
	public Teacher_course_listVO addTeacher_course_list(String sort_course_id, String language_id,  String teacher_id,
			Integer course_appraisal_accum, Integer course_appraisal_count,Integer course_state) {

		Teacher_course_listVO teacher_course_listVO = new Teacher_course_listVO();

		teacher_course_listVO.setSort_course_id(sort_course_id);
		teacher_course_listVO.setLanguage_id(language_id);
		teacher_course_listVO.setTeacher_id(teacher_id);
		teacher_course_listVO.setCourse_appraisal_accum(course_appraisal_accum);
		teacher_course_listVO.setCourse_appraisal_count(course_appraisal_count);
		teacher_course_listVO.setCourse_appraisal_count(course_state);
		dao.insert(teacher_course_listVO);

		return teacher_course_listVO;
	}

	//預留給 Struts 2 用的
	public void addTeacher_course_list(Teacher_course_listVO teacher_course_listVO) {
		dao.insert(teacher_course_listVO);
	}
	
	public Teacher_course_listVO updateTeacher_course_list(String teacher_course_list_id, String sort_course_id, 
			String language_id,  String teacher_id,
			Integer course_appraisal_accum, Integer course_appraisal_count,Integer course_state) {

		Teacher_course_listVO teacher_course_listVO = new Teacher_course_listVO();

		teacher_course_listVO.setTeacher_course_list_id(teacher_course_list_id);
		teacher_course_listVO.setSort_course_id(sort_course_id);
		teacher_course_listVO.setLanguage_id(language_id);
		teacher_course_listVO.setTeacher_id(teacher_id);
		teacher_course_listVO.setCourse_appraisal_accum(course_appraisal_accum);
		teacher_course_listVO.setCourse_appraisal_count(course_appraisal_count);
		teacher_course_listVO.setCourse_appraisal_count(course_state);
		dao.update(teacher_course_listVO);

		return dao.findByPrimaryKey(teacher_course_list_id);
	}
	
	//預留給 Struts 2 用的
	public void updateTeacher_course_list(Teacher_course_listVO teacher_course_listVO) {
		dao.update(teacher_course_listVO);
	}

	public void deleteTeacher_course_list(String teacher_course_list_id) {
		dao.delete(teacher_course_list_id);
	}

	public Teacher_course_listVO getOneTeacher_course_list(String teacher_course_list_id) {
		return dao.findByPrimaryKey(teacher_course_list_id);
	}

	public List<Teacher_course_listVO> getAll() {
		return dao.getAll();
	}
	
	public Set<Teacher_course_listVO>getAllForTeacher(String teacher_id){
		List<Teacher_course_listVO>list=getAll();
		Set <Teacher_course_listVO>set =new HashSet<Teacher_course_listVO>();
		
		for(Teacher_course_listVO courseVO:list) {
			if(courseVO.getTeacher_id().equals(teacher_id)) {
				set.add(courseVO);
			}
		}
		System.out.println("----------list: "+list.size());
		System.out.println("----------set: "+set.size());
		return set;
	}
	
	public Map<Teacher_course_listVO,Double>getAverage(String teacher_id){
		Map <Teacher_course_listVO,Double>map=new HashMap();
		Set <Teacher_course_listVO>set=getAllForTeacher(teacher_id);
		if(set!=null) {
			String average=null;
			Double ave=null;
			
			int i=0;
			int setSize=set.size();
System.out.println("-------setSize:"+setSize);
			DecimalFormat fmt = new DecimalFormat("##0.0");
			for(Teacher_course_listVO vo:set) {
				double accum = 0;
				double count = 0;
//System.out.println("getTeacher_course_list_id------------"+vo.getTeacher_course_list_id());
				 accum+=vo.getCourse_appraisal_accum();
//System.out.println("accum: "+accum);
				 count+=vo.getCourse_appraisal_count();
//System.out.println("count: "+count);
				
//				++i;
//				if(i==set.size()) {
					try {
						average=fmt.format(accum/count);
						ave=Double.parseDouble(average);
					}catch (java.lang.NumberFormatException e){
						return null;
					}
					
				
					map.put(vo, ave);
//				}
			}
			return map;
		}
		return null;
	}

}
