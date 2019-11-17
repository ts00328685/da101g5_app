<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.Sort_course.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Sort_courseVO sort_courseVO = (Sort_courseVO) request.getAttribute("sort_courseVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<%=  sort_courseVO==null  %>>  -- ${sort_courseVO==null}
<html>
<head>
<title>課程種類資料 - listOneSort_course.jsp</title>

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
		 <h3>課程種類資料 - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>課程種類編號</th>
		<th>課程種類</th>
		
	</tr>
	<tr>
		<td><%=sort_courseVO.getSort_course_id()%></td>
		<td><%=sort_courseVO.getSort_course()%></td>		
	</tr>
</table>

</body>
</html>