<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Course_report.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    Course_reportService course_reportSvc = new Course_reportService();
    List<Course_reportVO> list = course_reportSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��ҵ{���| - listAllCourse_report.jsp</title>

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

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��ҵ{���| - listAllCourse_report.jsp</h3>
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
		<th>���|�s��</th>
		<th>�Юv�s��</th>
		<th>�|���s��</th>
		<th>���|���e</th>
		<th>���|���A</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="/front-end/course_report/course_report_file/course_report_page1.file" %> 
	<c:forEach var="course_reportVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${course_reportVO.course_report_id}</td>
			<td>${course_reportVO.teacher_id}</td>
			<td>${course_reportVO.member_id}</td>
			<td>${course_reportVO.report_text}</td>
			<td>${course_reportVO.report_state}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/course_report/course_report.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="course_report_id"  value="${course_reportVO.course_report_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/course_report/course_report.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="course_report_id"  value="${course_reportVO.course_report_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/front-end/course_report/course_report_file/course_report_page2.file" %>

</body>
</html>