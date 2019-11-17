<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.event.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
response.setHeader("Pragma", "No-cache"); // HTTP 1.0
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setDateHeader("Expires", -1); // proxies

    if(request.getParameter("event_id")==null){
    	response.sendRedirect("EventIndex.jsp");//打網址不給參數 回活動區首頁
    	return;
    }
	
    String event_id = request.getParameter("event_id");
    EventService eventSvc = new EventService();
    
    if(eventSvc.findEventByPK(event_id)==null){
    	response.sendRedirect("EventIndex.jsp");//亂給參數 回活動區首頁
        return;
    }
    
    EventVO eventVO = eventSvc.findEventByPK(event_id);
    if(eventVO.getEvstatus()==0){
    	response.sendRedirect(request.getRequestURI());//想看下架的活動 轉回來的地方
    }
    pageContext.setAttribute("eventVO",eventVO);
          
%>    
<!DOCTYPE html>
<html>
<head>
<style>

body{
	font-family:Microsoft JhengHei;
}

</style>

<%@ include file="/front-end/file/head.file" %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/front-end/file/header.jsp" %>
<br>
<br>
<br>
<br>
<br>
<br>
<div class="row justify-content-center">
<div class="col-sm-6">
<div class="card">
  <h5 class="card-header">${eventVO.evtitle}</h5>
  <div class="card-body">
    <h5 class="card-title">活動內容:</h5>
    <p class="card-text">${eventVO.evcontent}</p>
    <c:if test="${eventVO.evpic!=null && eventVO.filetype.indexOf('image')>-1}">
       <img src="<%=request.getContextPath()%>/front-end/event/EventPicHandler.jsp?event_id=${eventVO.event_id}">
    </c:if>
    <c:if test="${eventVO.evpic!=null && eventVO.filetype.indexOf('video')>-1}">
       <video src="<%=request.getContextPath()%>/front-end/event/EventPicHandler.jsp?event_id=${eventVO.event_id}" controls="controls" loop="loop" >
       </video>
    </c:if>
    <c:if test="${eventVO.evpic!=null && eventVO.filetype.indexOf('audio')>-1}">
       <video src="<%=request.getContextPath()%>/front-end/event/EventPicHandler.jsp?event_id=${eventVO.event_id}" controls="controls" loop="loop" >
       </video>
    </c:if>
  </div>
</div>
</div>
</div>
<br><br>
<div class="row justify-content-center">
	<button type="button" class="btn btn-primary" onclick="history.back()">回上頁</button>
</div>
<br><br>
<%@ include file="/front-end/file/footer.file" %>	
</body>
</html>