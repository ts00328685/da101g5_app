package com.message.model;

import java.util.*;

public interface MessageDAO_interface {
	public void insert(MessageVO messageVO);
	public void update(MessageVO messageVO);
	public void delete(String message_id);
	public MessageVO findByPrimaryKey(String message_id);
	
	public List<MessageVO> getAll();
	public List<MessageVO> getAll2();
	
	//查某個會員的全部訊息
		public List<MessageVO> findOneAllMessages(String member_id);

}
