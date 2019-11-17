<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.event.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%
    EventVO eventVO;
    if(request.getAttribute("eventVO")==null){//網址進來 不傳參回後台活動首頁
    	response.sendRedirect(request.getContextPath()+"/back-end/event/EventIndex.jsp");
        return;
    }else{
    	eventVO = (EventVO)request.getAttribute("eventVO");
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
<%@ include file="/back-end/file/head.file"%>
<meta charset="UTF-8">
<title>修改活動</title>

<style>

body{
font-family: Microsoft JhengHei;
}

</style>


</head>
<body>
<%@include file="/back-end/file/header_back.jsp" %>
<br>
<br>
<br>
<br>
<br>
<div class="container">
<form action="<%=request.getContextPath() %>/event/event.do" method="post" enctype="multipart/form-data">
    <table class="table table-striped table-bordered">
        <tr>
            <td>標題:</td>
            <td><input type="text" name="evtitle" value="${eventVO.evtitle}" required size="50"></td>
            <td>${errorMsgs.evtitle}</td>
        </tr>
        <tr>
            <td>內文:</td>
            <td><textarea name="evcontent" cols="80" rows="10" required>${eventVO.evcontent}</textarea></td>
            <td>${errorMsgs.evcontent}</td>
        </tr>
        <tr>
            <td>活動影音:</td>
            <td>
                <c:if test="${eventVO.evpic==null}">
                                                    無影音<br>
                </c:if>
                <c:if test="${eventVO.evpic!=null && eventVO.filetype.indexOf('image')>-1}">
                    <img src="<%=request.getContextPath()%>/back-end/event/EventPicHandler.jsp?event_id=${eventVO.event_id}"><br>
                </c:if>
                <c:if test="${eventVO.evpic!=null && eventVO.filetype.indexOf('webm')>-1}">
                    <video muted src="<%=request.getContextPath()%>/back-end/event/EventPicHandler.jsp?event_id=${eventVO.event_id}" controls="controls" loop="loop"></video><br>
               </c:if>
                                        重新上傳:<input type="file" name="newevpic">
            </td>                  
        </tr>
        <tr>
        	<td>
        	狀態:
        	<c:if test="${eventVO.evstatus==1}">
	        	<select name="evstatus">
	        		<option value="1" selected>上架中(目前狀態)
	        		<option value="0">下架中
	        	</select>
	        </c:if>
	        <c:if test="${eventVO.evstatus==0}">
	        	<select name="evstatus">
	        		<option value="1">上架中
	        		<option value="0" selected>下架中(目前狀態)
	        	</select>
	        </c:if>	
	        </td>	
        </tr>
    </table>
    <input type="submit" value="確定修改">
    <input type="hidden" name="action" value="updateeventconfirm">
    <input type="hidden" name="event_id" value="${eventVO.event_id}">
    <input type="hidden" name="orievpic" value="${eventVO.evpic}">
</form>

</div>
<%@include file="/back-end/file/footer.file" %>	
</body>
</html>