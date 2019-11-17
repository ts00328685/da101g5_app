package com.que.model;

import java.util.List;

public class QueService {
	
	private QueDAO queDAO;
	
	public QueService() {
		queDAO = new QueDAO();
	}
	
	public void addQue(String member_id,String quetitle,String quecontent,byte[] quefile,String[] hashtagArray,String filetype) {
		
		QueVO queVO = new QueVO();
		queVO.setMember_id(member_id);
		queVO.setQuetitle(quetitle);
		queVO.setQuecontent(quecontent);
		queVO.setQuefile(quefile);
		queVO.setFiletype(filetype);
		queDAO.insert(queVO,hashtagArray);
		
	}
	
	public void updateQue(Integer quethumb, Integer watchcount, Integer rescount, String que_id) {
		QueVO queVO = new QueVO();
		queVO.setQue_id(que_id);
		queVO.setQuethumb(quethumb);
		queVO.setWatchcount(watchcount);
		queVO.setRescount(rescount);
		queDAO.update(queVO);
	}
	
	public void editQue(byte[] quefile,String filetype,String que_id,String[] hashtagArray) {
		queDAO.edit(quefile, filetype, que_id, hashtagArray);
	}
	
	public void hideQue(String que_id) {
		queDAO.hide(que_id);
	}
	
	public void deleteQue(String que_id) {
		queDAO.delete(que_id);
		
	}
	
	public void onshelfQue(String que_id) {
		queDAO.onshelf(que_id);
	}
	
	public QueVO findQueByPK(String que_id) {
		
		QueVO queVO = queDAO.findByPK(que_id);
		return queVO;
	}
	
	public List<QueVO> getAllQue(){
		List<QueVO> quelist = queDAO.getAll();
		
		return quelist;
	}
}
