<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="/front-end/file/head.file" %>
<body>
<%@ include file="/front-end/file/header.jsp" %>
<br>
<br>
<br>
<br>
<br>
<jsp:useBean id="recruitmessageSvc" class="com.recruitmessage.model.RecruitmessageService"/>
<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
<c:if test="${recruitmessageSvc.getAllByReciverId(sessionScope.memberVO.member_id).size()>0}">
<c:forEach var="recruitmessageVO" items="${recruitmessageSvc.getAllByReciverId(sessionScope.memberVO.member_id)}">
  <div class="container" style="padding-top: 200px;padding-bottom: 200px;">	
 	<div class="row justify-content-center"> 
	<div class="card" style="width: 36rem;">
  		<div class="card-body">
    	<h5 class="card-title">發信人:${memberSvc.getOneMember(recruitmessageVO.sender_id).mem_nick}</h5>
    	<h6 class="card-subtitle mb-2 text-muted">發送時間:${recruitmessageVO.sendtime.toString().substring(0,19)}</h6>
    	<h6 class="card-subtitle mb-2 text-muted">訊息內容:</h6>
    	<textarea readonly style="border: none; resize: none;" rows="5" cols="50">${recruitmessageVO.message }</textarea>
        </div>
    </div>    
</div>
</div>
</c:forEach>
</c:if>
<c:if test="${recruitmessageSvc.getAllByReciverId(sessionScope.memberVO.member_id).size()==0}">
<div class="container" style="padding-top: 200px;padding-bottom: 200px;">
<div class="row justify-content-center">
	查無結果
</div>
</div>
</c:if>
<%@ include file="/front-end/file/footer.file" %>
</body>
</html>