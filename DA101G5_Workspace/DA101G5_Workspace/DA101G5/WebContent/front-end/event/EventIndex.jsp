<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>    
<%@ page import="com.event.model.*" %>    
<%
response.setHeader("Pragma", "No-cache"); // HTTP 1.0
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setDateHeader("Expires", -1); // proxies


    EventService eventSvc = new EventService();
    List<EventVO> eventlist = eventSvc.getAllEventOnshelf();
    pageContext.setAttribute("eventlist",eventlist);
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
<title>活動快訊</title>
</head>
<body>
<%@ include file="/front-end/file/header.jsp" %>
<br>
<br>
<br>
<br>
<br>
<br>
<%@ include file="event_file/event_page1.file" %>
<ul class="list-group" style="padding-top: 200px;padding-bottom: 50px;">
<c:forEach var="eventVO" items="${eventlist}">
      <li class="list-group-item"><div class="row justify-content-center"><a href="Eventlistone.jsp?event_id=${eventVO.event_id}">${eventVO.evtitle}</a></div></li>
</c:forEach>
</ul>
<%@ include file="event_file/event_page2.file" %>
<div class="row justify-content-center">
	<button type="button" class="btn btn-primary" onclick="history.back()" style="margin-bottom: 50px;">回上頁</button>
</div>
<%@ include file="/front-end/file/footer.file" %>
</body>
</html>