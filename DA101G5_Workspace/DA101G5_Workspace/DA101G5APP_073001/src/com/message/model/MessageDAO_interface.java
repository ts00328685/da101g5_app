package com.message.model;

import java.util.*;

public interface MessageDAO_interface {
	public void insert(MessageVO messageVO);
	public void update(MessageVO messageVO);
	public void delete(String message_id);
	public MessageVO findByPrimaryKey(String message_id);
	public List<MessageVO> getAll();

}
