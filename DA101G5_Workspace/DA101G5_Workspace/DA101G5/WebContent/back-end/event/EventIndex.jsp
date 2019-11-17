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
    List<EventVO> eventlist = eventSvc.getAllEvent();
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
<%@ include file="/back-end/file/head.file"%>
<meta charset="UTF-8">
<title>活動快訊</title>
</head>
<body>
<%@include file="/back-end/file/header_back.jsp" %>
<br>
<br>
<br>
<br>
<br>
<%@ include file="page1.file" %>
<c:forEach var="eventVO" items="${eventlist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
  <form action="<%=request.getContextPath() %>/event/event.do" method="post">
   <div class="row justify-content-center"> 
    <div class="card" style="width: 36rem;">
  	
  	<div class="card-body">
    	<div class="row justify-content-center">	
    		<h5 class="card-title">標題:${eventVO.evtitle}</h5>
    	</div>	
    	<div class="row justify-content-center">
    		<h5 class="card-title">
    			狀態:
               	<c:if test="${eventVO.evstatus==1}">
                                                上架中
                </c:if>
                <c:if test="${eventVO.evstatus==0}">
                                                下架中
                </c:if>
    	</h5>
    </div>
    
    <div class="card-body">
    	<div class="row float-left">
    		活動內容:
    	</div>
    	<br>
    	<p class="card-text">${eventVO.evcontent}</p>
    </div>
    <c:if test="${eventVO.evpic!=null && eventVO.filetype.indexOf('image')>-1}">
       <a href="<%=request.getContextPath()%>/back-end/event/EventPicHandler.jsp?event_id=${eventVO.event_id}"><img src="<%=request.getContextPath()%>/back-end/event/EventPicHandler.jsp?event_id=${eventVO.event_id}" width="150"></a>
    </c:if>
    <c:if test="${eventVO.evpic!=null && eventVO.filetype.indexOf('webm')>-1}">
       <a href="<%=request.getContextPath()%>/back-end/event/EventPicHandler.jsp?event_id=${eventVO.event_id}"><video muted src="<%=request.getContextPath()%>/back-end/event/EventPicHandler.jsp?event_id=${eventVO.event_id}" controls="controls" loop="loop" width="150"></video></a>
    </c:if>
    <c:if test="${eventVO.evpic==null}">
                    無圖片
    </c:if>    
  	</div>
  	<div class="card-footer text-muted">
		<div class="row justify-content-center">
		    <input type="submit" name="" value="編輯">
		</div>	
	</div>
	</div>
    </div>
    <input type="hidden" name="action" value="editevent">
    <input type="hidden" name="event_id" value="${eventVO.event_id}">
  </form>
</c:forEach>
<%@ include file="page2.file" %>
<div class="row justify-content-center">
<a href="<%=request.getContextPath() %>/back-end/event/AddEvent.jsp">新增活動</a>
</div>
<%@include file="/back-end/file/footer.file" %>	
</body>
</html>