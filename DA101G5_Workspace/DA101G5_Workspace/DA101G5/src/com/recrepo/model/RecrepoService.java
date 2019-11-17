package com.recrepo.model;

import java.util.List;

public class RecrepoService {
	
	private RecrepoDAO recrepoDAO ;
	
	public RecrepoService() {
		recrepoDAO = new RecrepoDAO();
	}
	
	public void addRecrepo(String recruit_id, String repocontent) {
		RecrepoVO recrepoVO = new RecrepoVO();
		recrepoVO.setRecruit_id(recruit_id);
		recrepoVO.setRepocontent(repocontent);
		recrepoDAO.insert(recrepoVO);
	}
	
	public void deleteRecrepo(String recrepo_id) {
		recrepoDAO.delete(recrepo_id);
	}
	
	public void updateRecrepo(String recrepo_id,Integer repostatus) {
		recrepoDAO.update(recrepo_id,repostatus);
	}
	
	public RecrepoVO findRecrepoByPK(String recrepo_id) {
		RecrepoVO recrepoVO = recrepoDAO.findByPK(recrepo_id);
		return recrepoVO;
	}
	
	public List<RecrepoVO> getAllRecrepo(){
		List<RecrepoVO> recrepolist = recrepoDAO.getAll();
		return recrepolist;
	}
	
	public List<RecrepoVO> getAllNotDoneYetRecrepo(){
		List<RecrepoVO> recrepolist = recrepoDAO.getAllNotDoneYet();
		return recrepolist;
	}
}
