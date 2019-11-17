package com.message.model;

import java.util.List;


public class MessageService {
	
	private MessageDAO_interface dao;

	public MessageService() {
		dao = new MessageDAO();
	}

	public MessageVO addMessage(String member_id, String member_id2, java.sql.Date memmsg_date,
			String memmsg_ent, Integer memmsg_state) {

		MessageVO messageVO = new MessageVO();

		
		messageVO.setMember_id(member_id);
		messageVO.setMember_id2(member_id2);
		messageVO.setMemmsg_date(memmsg_date);
		messageVO.setMemmsg_ent(memmsg_ent);
		messageVO.setMemmsg_state(memmsg_state);
		dao.insert(messageVO);

		return messageVO;
	}

	public MessageVO updateMessage(String message_id, String member_id, String member_id2, java.sql.Date memmsg_date,
			String memmsg_ent, Integer memmsg_state) {

		MessageVO messageVO = new MessageVO();

		messageVO.setMessage_id(message_id);
		messageVO.setMember_id(member_id);
		messageVO.setMember_id2(member_id2);
		messageVO.setMemmsg_date(memmsg_date);
		messageVO.setMemmsg_ent(memmsg_ent);
		messageVO.setMemmsg_state(memmsg_state);
		dao.update(messageVO);

		return messageVO;
	}

	public void deleteMessage(String message_id) {
		dao.delete(message_id);
	}

	public MessageVO getOneMessage(String message_id) {
		return dao.findByPrimaryKey(message_id);
	}

	public List<MessageVO> getAll() {
		return dao.getAll();
	}
	
	//查某個會員的全部訊息
		public List<MessageVO> getOneAllMessages(String member_id){
			return dao.findOneAllMessages(member_id);
		}
	
	
}
