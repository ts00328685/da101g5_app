<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hashtag.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    HashtagService hashtagSvc = new HashtagService();
    List<HashtagVO> hashtaglist = hashtagSvc.getAllHashtag();
    pageContext.setAttribute("hashtaglist",hashtaglist);
%>    
<!DOCTYPE html>
<html>
<head>
<%@ include file="/back-end/file/head.file"%>
<style>
body{
	font-family:Microsoft JhengHei;
}

.tag{
	padding:10px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="/back-end/file/header_back.jsp" %>
<div id="container" class="container">
<br>
<br>
<br>
<br>
<br>
   <div class="row justify-content-center">  
    <form action="<%=request.getContextPath() %>/hashtag/hashtag.do" method="post">
                     新增hashtag:<input type="text" name="hashtag" required>
    	<input type="submit" value="新增hashtag">
    	<input type="hidden" name="action" value="addhashtagconfirm">
	</form>
  </div>
     <br>
     <br>
     <br>
     <br>
     
     <div class="row">	
        <c:forEach var="hashtagVO" items="${hashtaglist}">
           <div class="tag">${hashtagVO.hashtag_id}:<button type="button" class="btn btn-outline-primary" disabled="disabled"># ${hashtagVO.hashtag}</button></div>
        </c:forEach>
     </div> 
  
</div>  	
<%@include file="/back-end/file/footer.file" %>	
</body>
</html>