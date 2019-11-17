package com.chatroom.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.HandshakeResponse;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.chatroom.model.ChatMessageVO;
import com.chatroom.model.StateVO;
import com.google.gson.Gson;
import com.member.model.MemberVO;

@ServerEndpoint(value="/TwoChat/{sender_member_id}", configurator=ServletAwareConfig.class)
public class TwoChat {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	private EndpointConfig config;
	Gson gson = new Gson();
	

	@OnOpen
	public void onOpen(@PathParam("sender_member_id") String sender_member_id, Session userSession, EndpointConfig config) throws IOException, JSONException, EncodeException {
		this.config = config;
			
		/* save the new user in the map */
		sessionsMap.put(sender_member_id, userSession);
		/* Sends all the connected users to the new user */
		Set<String> userIds = sessionsMap.keySet();
		StateVO stateMessage = new StateVO("open", sender_member_id, userIds);
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			
			if (session.isOpen()) {				
					session.getAsyncRemote().sendText(stateMessageJson);
					System.out.println(stateMessageJson);
					
				
			}
				
			
		}
		
		
		

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				sender_member_id, userIds);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) throws IOException, JSONException {
		ChatMessageVO chatMessageVO = gson.fromJson(message, ChatMessageVO.class);
		System.out.println(message);
		String sender = chatMessageVO.getSender();		
		String receiver = chatMessageVO.getReceiver();
		String content = chatMessageVO.getMessage();
		
		
//		if("getnone".equals(chatMessageVO.getMtype())) {
//			List<String> noneMesgsData = JedisMessageAction.getHistoryMsg(sender, receiver);
//			if(!(noneMesgsData.isEmpty())) {				
//				JSONArray array = new JSONArray();
//				for(int i = 0; i < noneMesgsData.size(); i++) {	
//					JSONObject obj = new JSONObject(noneMesgsData.get(i));
//					if("none".equals(obj.get("Mtype")) && sender.equals(obj.get("receiver"))) {
//						array.put(obj);				
//					}
//				}					
//					
//					ChatMessageVO cmNoneMesgs = new ChatMessageVO("none", sender, receiver, array.toString());
//					if (userSession != null && userSession.isOpen()) {
//						userSession.getBasicRemote().sendText(gson.toJson(cmNoneMesgs));
//						System.out.println("cmHistory= " + cmNoneMesgs);
//						return;
//				}
//			}
//		}
		
		if("read".equals(chatMessageVO.getMtype())) {
			Session receiverSession = sessionsMap.get(receiver);
			if (receiverSession != null && receiverSession.isOpen()) {
				receiverSession.getAsyncRemote().sendText(message);
				JedisMessageAction.saveChatMessage(sender, receiver, message);
				
			}
		}
		
		if("image".equals(chatMessageVO.getMtype())) {
			
//			InputStream in = new FileInputStream(picMessage);
//			byte[] buffer1 = new byte[in.available()];
//			in.read(buffer1);
//			in.close();
//
//			//Base64編碼
//			Base64.Encoder encoder = Base64.getEncoder();
//			String encodedText = encoder.encodeToString(buffer1);
//			System.out.println(encodedText);
//			chatMessageVO.setMessage(encodedText);
			
			Session receiverSession = sessionsMap.get(receiver);
			if (receiverSession != null && receiverSession.isOpen()) {
				receiverSession.getAsyncRemote().sendText(message);
				JedisMessageAction.saveChatMessage(sender, receiver, message);
				return;
			}else {
				JedisMessageAction.saveChatMessage(sender, receiver, message);
				return;
			}
		}
		
		
		if ("history".equals(chatMessageVO.getMtype())) {
			
			List<String> historyData = JedisMessageAction.getHistoryMsg(sender, receiver);
			if(!(historyData.isEmpty())) {				
				JSONArray array = new JSONArray();
				for(int i = 0; i < historyData.size(); i++) {					
					JSONObject obj = new JSONObject(historyData.get(i));
						array.put(obj);						
				}					
					
					ChatMessageVO cmHistory = new ChatMessageVO("history", sender, receiver, array.toString());
					if (userSession != null && userSession.isOpen()) {
						userSession.getBasicRemote().sendText(gson.toJson(cmHistory));
						System.out.println("cmHistory= " + cmHistory);
						return;
				}
			}	
			
		}
		
		if("chat".equals(chatMessageVO.getMtype())) {
			Session receiverSession = sessionsMap.get(receiver);
			if (receiverSession != null && receiverSession.isOpen()) {
				receiverSession.getAsyncRemote().sendText(message);
				JedisMessageAction.saveChatMessage(sender, receiver, message);
				
			}else {
				JSONObject obj = new JSONObject();
				obj.put("Mtype", "none");
				obj.put("sender", sender);
				obj.put("receiver", receiver);
				obj.put("message", content);
				JedisMessageAction.saveChatMessage(sender, receiver, obj.toString());
				System.out.println("obj: " + obj.toString());
			}
			
		}
			
		System.out.println("Message received: " + message);
		
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		
		e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) throws JSONException {
		String userIdClose = null;
		Set<String> userIds = sessionsMap.keySet();
		for (String userId : userIds) {
			if (sessionsMap.get(userId).equals(userSession)) {
				userIdClose = userId;
				sessionsMap.remove(userId);
				break;
			}
		}

		if (userIdClose != null) {
			StateVO stateMessage = new StateVO("close", userIdClose, userIds);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				if(session != sessionsMap.get(userIdClose)) {
					session.getAsyncRemote().sendText(stateMessageJson);
				}
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userIds);
		System.out.println(text);
	}
	
	
}
