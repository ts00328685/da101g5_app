<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.Teacher_course_list.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Teacher_course_listVO teacher_course_listVO = (Teacher_course_listVO) request.getAttribute("teacher_course_listVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<%=  teacher_course_listVO==null  %>>  -- ${teacher_course_listVO==null}
<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
	width: 600px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>教師課程明細編號</th>
		<th>課程種類編號</th>
		<th>語言編號</th>
		<th>教師編號</th>
		<th>此課程總評價累計</th>
		<th>此課程總評價次數</th>

	</tr>
	<tr>
		<td><%=teacher_course_listVO.getTeacher_course_list_id()%></td>
		<td><%=teacher_course_listVO.getSort_course_id()%></td>
		<td><%=teacher_course_listVO.getLanguage_id()%></td>
		<td><%=teacher_course_listVO.getTeacher_id()%></td>
		<td><%=teacher_course_listVO.getCourse_appraisal_accum()%></td>
		<td><%=teacher_course_listVO.getCourse_appraisal_count()%></td>

	</tr>
</table>

</body>
</html>