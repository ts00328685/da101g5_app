package com.recruit.model;

import java.util.List;

public class RecruitService {
	
	private RecruitDAO recruitDAO;
	
	public RecruitService() {
		recruitDAO = new RecruitDAO();
	}
	
	public void addRecruit(String member_id, String rcontent, String rtitle) {
		RecruitVO recruitVO = new RecruitVO();
		recruitVO.setMember_id(member_id);
		recruitVO.setRtitle(rtitle);
		recruitVO.setRcontent(rcontent);
		recruitDAO.insert(recruitVO);
	}
	
	public void deleteRecruit(String recruit_id) {
		recruitDAO.delete(recruit_id);
	}
	
	public void updateRecruit(String recruit_id,Integer recstatus) {
		recruitDAO.update(recruit_id,recstatus);
	}
	
	public void updateMmePoint(String member_id,Integer mem_point) {
		recruitDAO.updateMemberPoint(member_id, mem_point);
	}
	
	public RecruitVO findRecruitByPK(String recruit_id) {
		RecruitVO recruitVO = recruitDAO.findByPK(recruit_id);
		return recruitVO;
	}
	
	public List<RecruitVO> getAllRecruit(){
		List<RecruitVO> recruitlist = recruitDAO.getAll();
		return recruitlist;
	}
}
