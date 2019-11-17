package com.res.model;

import java.util.List;

public class ResService {
	private ResDAO resDAO;
	
	public ResService() {
		resDAO = new ResDAO();
	}
	
	public void addRes(String member_id, String rescontent, byte[] resfile, String belongto,String filetype) {
		ResVO resVO = new ResVO();
		resVO.setBelongto(belongto);
		resVO.setMember_id(member_id);
		resVO.setResfile(resfile);
		resVO.setRescontent(rescontent);
		resVO.setFiletype(filetype);
		resDAO.insert(resVO);
	}
	
	public void updateRes(String res_id, Integer resthumb, Integer bestres) {/*在controller包裝*/
		ResVO resVO = new ResVO();
		resVO.setBestres(bestres);
		resVO.setResthumb(resthumb);
		resVO.setRes_id(res_id);
		resDAO.update(resVO);
	}
	
	public void editRes(String res_id, byte[] resfile, String filetype) {
		resDAO.edit(res_id, resfile, filetype);
	}
	
	public void deleteRes(String res_id) {
		resDAO.delete(res_id);
	}
	
	public void hideRes(String res_id) {
		resDAO.hide(res_id);
	}
	
	public void onshelfRes(String res_id) {
		resDAO.onshelf(res_id);
	}
	
	public ResVO findResByPK(String res_id) {
		ResVO resVO = resDAO.findByPK(res_id);
		return resVO;
	}
	
	public List<ResVO> findResByBelongto(String que_id) {
		List<ResVO> reslist = resDAO.findByBelongto(que_id);
		return reslist;
	}
	
	public List<ResVO> getAllRes(){
		List<ResVO> reslist = resDAO.getAll();
		return reslist;
	}
}
