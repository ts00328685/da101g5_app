<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.Course_report.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Course_reportVO course_reportVO = (Course_reportVO) request.getAttribute("course_reportVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<%=  course_reportVO==null  %>>  -- ${course_reportVO==null}
<html>
<head>
<title>課程檢舉資料 - listOneReport_state.jsp</title>

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
		 <h3>課程檢舉資料 - listOneReport_state.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>檢舉編號</th>
		<th>教師編號</th>
		<th>會員編號</th>
		<th>檢舉內容</th>
		<th>檢舉狀態</th>
		
	</tr>
	<tr>
		<td>${course_reportVO.course_report_id}</td>
			<td>${course_reportVO.teacher_id}</td>
			<td>${course_reportVO.member_id}</td>
			<td>${course_reportVO.report_text}</td>
			<td>${course_reportVO.report_state}</td>
	</tr>
</table>

</body>
</html>