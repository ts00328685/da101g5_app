<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Teacher_course_list.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Teacher_course_listService teacher_course_listSvc = new Teacher_course_listService();
    List<Teacher_course_listVO> list = teacher_course_listSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有教師課程明細資料 - listAllTeacher_course_list.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<%@ include file="/front-end/file/head.file"%>
</head>
<body >

<%@ include file="/front-end/file/header.jsp"%>
<div class="container" style="padding-top:200px;">
<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有教師課程明細資料 - listAllTeacher_course_list.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>教師課程訂單明細編號</th>
		<th>課程種類編號</th>
		<th>語言編號</th>
		<th>教師編號</th>
		<th>此課程總評價累計</th>
		<th>此課程總評價次數</th>
		
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="/front-end/teacher_course_list/t_course_list_file/t_course_list_page1.file" %> 
	<c:forEach var="teacher_course_listVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${teacher_course_listVO.teacher_course_list_id}</td>
			<td>${teacher_course_listVO.sort_course_id}</td>
			<td>${teacher_course_listVO.language_id}</td>
			<td>${teacher_course_listVO.teacher_id}</td>
			<td>${teacher_course_listVO.course_appraisal_accum}</td>
			<td>${teacher_course_listVO.course_appraisal_count}</td> 
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/teacher_course_list/teacher_course_list.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="teacher_course_list_id"  value="${teacher_course_listVO.teacher_course_list_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/teacher_course_list/teacher_course_list.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="teacher_course_list_id"  value="${teacher_course_listVO.teacher_course_list_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
<%@ include file="/front-end/teacher_course_list/t_course_list_file/t_course_list_page2.file" %>
<%@ include file="/front-end/file/footer.file"%>
</body>
</html>