<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.Teacher_course_list.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  Teacher_course_listVO teacher_course_listVO = (Teacher_course_listVO) request.getAttribute("teacher_course_listVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>
<%=  teacher_course_listVO==null  %>>  -- ${teacher_course_listVO==null}
<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�Юv�ҵ{���ӽs��</th>
		<th>�ҵ{�����s��</th>
		<th>�y���s��</th>
		<th>�Юv�s��</th>
		<th>���ҵ{�`�����֭p</th>
		<th>���ҵ{�`��������</th>

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