<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Teacher_course_list.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    Teacher_course_listService teacher_course_listSvc = new Teacher_course_listService();
    List<Teacher_course_listVO> list = teacher_course_listSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��Юv�ҵ{���Ӹ�� - listAllTeacher_course_list.jsp</title>

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
<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��Юv�ҵ{���Ӹ�� - listAllTeacher_course_list.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>�Юv�ҵ{�q����ӽs��</th>
		<th>�ҵ{�����s��</th>
		<th>�y���s��</th>
		<th>�Юv�s��</th>
		<th>���ҵ{�`�����֭p</th>
		<th>���ҵ{�`��������</th>
		
		<th>�ק�</th>
		<th>�R��</th>
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="teacher_course_list_id"  value="${teacher_course_listVO.teacher_course_list_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/teacher_course_list/teacher_course_list.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
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