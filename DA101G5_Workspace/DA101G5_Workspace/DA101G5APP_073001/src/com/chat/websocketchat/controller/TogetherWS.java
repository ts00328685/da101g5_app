package com.chat.websocketchat.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.member.model.*;
@ServerEndpoint("/TogetherWS/{member_id}")
public class TogetherWS {
	private static final Map<Session, MemberVO> connectedSessions = Collections.synchronizedMap(new HashMap<Session, MemberVO>());
	private MemberVO memberVO;
	private MemberService memberService = new MemberService();
	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	@OnOpen
	public void onOpen(@PathParam("member_id") String member_id, Session userSession) throws IOException {
		
		memberVO = memberService.getOneMember(member_id);
		connectedSessions.put(userSession, memberVO);
		String text = String.format("Session ID = %s, connected; member_id = %s", userSession.getId(), member_id);
		System.out.println(text);
		
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		
		System.out.println(message);
	    Iterator it = connectedSessions.entrySet().iterator();
	    Session session;
	    System.out.println("發送訊息人:" + connectedSessions.get(userSession).getMember_id());
	    
	    Gson gson = new Gson();
	    JsonObject jobj = gson.fromJson(message, JsonObject.class);
	    
	    String targetMember_id = jobj.get("targetMember_id").getAsString();
	    
	    
	    while (it.hasNext()) {
	    	
	        Map.Entry pair = (Map.Entry)it.next();
	        session = (Session) pair.getKey();
	        
	        if (session.isOpen()) {
	        	
	        	if(connectedSessions.get(session).getMember_id().equals(targetMember_id) || session == userSession) {
	        		session.getAsyncRemote().sendText(message);
	        		System.out.println("message送出對象: (sessionID & member_id)" + ((Session)pair.getKey()).getId() + " = " + ((MemberVO)pair.getValue()).getMember_id());
	        	}
				
	        }
	        
//	        it.remove(); // avoids a ConcurrentModificationException
	    }
		
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s name=%s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase(), connectedSessions.get(userSession).getMember_id());
		System.out.println(text);
		connectedSessions.remove(userSession);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
