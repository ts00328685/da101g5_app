<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.Course_order.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  Course_orderVO course_orderVO = (Course_orderVO) request.getAttribute("course_orderVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>
<%=  course_orderVO==null  %>>  -- ${course_orderVO==null}
<html>
<head>
<title>�ҵ{�q���� - listOneCourse_order.jsp</title>

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
		 <h3>�ҵ{�q���� - lsitOneCourse_order.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�ҵ{�q��s��</th>
		<th>�|���s��</th>
		<th>�Юv�s��</th>
		<th>�ʶR�ɼ�</th>
		<th>��O�I��</th>
		<th>�Ѿl�ɼ�</th>
		<th>���s�w������</th>
	</tr>
	<tr>
		<td><%=course_orderVO.getCourse_order_id()%></td>
		<td><%=course_orderVO.getMember_id()%></td>
		<td><%=course_orderVO.getTeacher_id()%></td>
		<td><%=course_orderVO.getBuy_time()%></td>
		<td><%=course_orderVO.getSpend_point()%></td>
		<td><%=course_orderVO.getRemain_hour()%></td>
		<td><%=course_orderVO.getRe_appointment()%></td>
	</tr>
</table>

</body>
</html>