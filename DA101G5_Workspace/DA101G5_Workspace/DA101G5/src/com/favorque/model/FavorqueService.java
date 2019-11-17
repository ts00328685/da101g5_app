package com.favorque.model;

import java.util.List;

public class FavorqueService {
	private FavorqueDAO favorqueDAO;
	
	public FavorqueService() {
		favorqueDAO = new FavorqueDAO();
	}
	
	public void addFavorque(String que_id ,String member_id) {
		FavorqueVO favorqueVO = new FavorqueVO();
		favorqueVO.setMember_id(member_id);
		favorqueVO.setQue_id(que_id);
		favorqueDAO.insert(favorqueVO);
	}
	
	public void deleteFavorque(String que_id,String member_id) {
		favorqueDAO.delete(member_id,que_id);
	}
	
	public boolean searchFavorque(String que_id,String member_id) {
		boolean tf ;
		tf = favorqueDAO.search(member_id, que_id);
		return  tf;
	}
	
	public List<FavorqueVO> getAllfavorque(){
		List<FavorqueVO> favorquelist = favorqueDAO.getAll();
		return favorquelist;	
	}
	
	public List<FavorqueVO> findFavorqueByMemberId(String member_id){
		List<FavorqueVO> favorquelist = favorqueDAO.findByMemberId(member_id);
		return favorquelist ;
	}
}
