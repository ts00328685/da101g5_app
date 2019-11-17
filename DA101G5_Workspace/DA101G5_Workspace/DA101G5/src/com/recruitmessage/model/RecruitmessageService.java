package com.recruitmessage.model;

import java.util.List;


public class RecruitmessageService {
private RecruitmessageDAO recruitmessageDAO;
	
	public RecruitmessageService() {
		recruitmessageDAO = new RecruitmessageDAO();
	}
	
	public void addrecruitmessage(String sender_id,String reciver_id,String message) {
		recruitmessageDAO.insert(sender_id, reciver_id, message);
	}
	
	public List<RecruitmessageVO> getAllByReciverId(String reciver_id){
		List<RecruitmessageVO> recruitmessagelist = recruitmessageDAO.getAll(reciver_id);
		return recruitmessagelist;
	}
}
