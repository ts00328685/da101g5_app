package com.recruitmessage.model;

import java.util.List;

public interface RecruitmessageDAO_interface {
	
	public void insert(String sender_id,String reciver_id,String message);
	public List<RecruitmessageVO> getAll(String reciver_id);
}
