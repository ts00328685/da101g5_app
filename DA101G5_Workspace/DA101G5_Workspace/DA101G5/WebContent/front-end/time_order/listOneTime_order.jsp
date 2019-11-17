<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.Time_order.model.*"%>
<%-- <%@ page import="com.time_order.model.*"%> --%>
<%-- <%@ page import="com.dept.model.*"%> --%>
<%-- <%-- 此頁暫練習採用 Script 的寫法取值 --%> --%>

<%-- <%-- 取出 Concroller Time_orderServlet.java已存入request的Time_orderVO物件--%> --%>
<%Time_orderVO time_orderVO = (Time_orderVO) request.getAttribute("time_orderVO");%> 

<%-- <%-- 取出 對應的DeptVO物件--%> --%>
<%-- <% --%>
//   DeptService deptSvc = new DeptService();
//   DeptVO deptVO = deptSvc.getOneDept(time_orderVO.getDeptno());
<%-- %> --%>

<html>
<head>
<title>員工資料 - listOneTime_order.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneTime_order.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/time_order/select_page.jsp">
		 <img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>預約時間明細編號</th>
		<th>教師編號</th>
		<th>課程訂單編號</th>
		<th>語言編號</th>
		<th>課程種類編號</th>
		<th>完課狀態</th>
		<th>完課評價</th>
		<th>預約開始時間</th>
		<th>預約結束時間</th>
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
		
		
		
<%-- 		<td><%=time_orderVO.getDeptno()%>【<%=deptVO.getDname()%> - <%=deptVO.getLoc()%>】</td> --%>
	</tr>
</table>

</body>
</html>