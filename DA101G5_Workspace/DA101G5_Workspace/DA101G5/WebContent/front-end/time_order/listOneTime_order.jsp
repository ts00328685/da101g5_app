<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.Time_order.model.*"%>
<%-- <%@ page import="com.time_order.model.*"%> --%>
<%-- <%@ page import="com.dept.model.*"%> --%>
<%-- <%-- �����Ƚm�߱ĥ� Script ���g�k���� --%> --%>

<%-- <%-- ���X Concroller Time_orderServlet.java�w�s�Jrequest��Time_orderVO����--%> --%>
<%Time_orderVO time_orderVO = (Time_orderVO) request.getAttribute("time_orderVO");%> 

<%-- <%-- ���X ������DeptVO����--%> --%>
<%-- <% --%>
//   DeptService deptSvc = new DeptService();
//   DeptVO deptVO = deptSvc.getOneDept(time_orderVO.getDeptno());
<%-- %> --%>

<html>
<head>
<title>���u��� - listOneTime_order.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneTime_order.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/time_order/select_page.jsp">
		 <img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�w���ɶ����ӽs��</th>
		<th>�Юv�s��</th>
		<th>�ҵ{�q��s��</th>
		<th>�y���s��</th>
		<th>�ҵ{�����s��</th>
		<th>���Ҫ��A</th>
		<th>���ҵ���</th>
		<th>�w���}�l�ɶ�</th>
		<th>�w�������ɶ�</th>
	</tr>
	<tr>
		<td><%=time_orderVO.getTime_order_id()%></td>
		<td><%=time_orderVO.getTeacher_id()%></td>
		<td><%=time_orderVO.getCourse_order_id()%></td>
		<td><%=time_orderVO.getLanguage_id()%></td>
		<td><%=time_orderVO.getSort_course_id()%></td>
		<td><%=time_orderVO.getC_state()%></td>
		<td><%=time_orderVO.getStart_time()%></td>
		<td><%=time_orderVO.getEnd_time()%></td>
		
		
		
<%-- 		<td><%=time_orderVO.getDeptno()%>�i<%=deptVO.getDname()%> - <%=deptVO.getLoc()%>�j</td> --%>
	</tr>
</table>

</body>
</html>