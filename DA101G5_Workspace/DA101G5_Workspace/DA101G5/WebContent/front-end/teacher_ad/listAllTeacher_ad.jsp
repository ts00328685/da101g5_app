<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Teacher_ad.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Teacher_adService teacherSvc = new Teacher_adService();
    List<Teacher_adVO> list = teacherSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllTeacher_ad.jsp</title>
<%@ include file="/front-end/file/head.file"%>
<style>

</style>



</head>
<body>
<%@ include file="/front-end/file/header.jsp"%>
<div class="container"style="padding-top:200px;">
<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllTeacher_ad.jsp</h3>
		 <h4><a href="select_page.jsp">
		 <img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>教師廣告編號</th>
		<th>教師編號</th>
		<th>購買時數</th>
		<th>開始時間</th>
		<th>廣告狀態</th>

		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="/front-end/teacher_ad/teacher_ad_file/teacher_ad_page1.file" %> 
	<c:forEach var="teacher_adVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${teacher_adVO.teacher_ad_id}</td>
			<td>${teacher_adVO.teacher_id}</td>
			<td>${teacher_adVO.ad_time}</td>
			<td><fmt:formatDate value="${teacher_adVO.ad_start}" pattern="yyyy-MM-dd"/></td>
			<td>${teacher_adVO.ad_state}</td>

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/teacher_ad/teacher_ad.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="teacher_ad_id"  value="${teacher_adVO.teacher_ad_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/teacher_ad/teacher_ad.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="teacher_ad_id"  value="${teacher_adVO.teacher_ad_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<%@ include file="/front-end/teacher_ad/teacher_ad_file/teacher_ad_page2.file" %>
<%@ include file="/front-end/file/footer.file"%>
</body>
</html>