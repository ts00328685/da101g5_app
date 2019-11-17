<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.point_trans.model.*"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>


<jsp:useBean id="point_transSvc" scope="page" class="com.point_trans.model.Point_transService" />


<html>
<head>

<title>點數交易資料 - listOnePoint_trans.jsp</title>

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


<table id="table-1">
	<tr><td>
		 <h3>點數交易資料 - ListOnePoint_trans.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/index.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>點數交易編號</th>
		<th>會員編號</th>
		<th>點數紀錄</th>
		<th>交易日期</th>
		<th>刪除</th>
	</tr>
<c:forEach var="point_transVO" items="${point_transSvc.all}">	
	<tr>
		<td>${point_transVO.point_trans_id}</td>
		<td>${point_transVO.member_id}</td>
		<td>${point_transVO.point_record}</td>
		<td>${point_transVO.transdate}</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/point_trans/point_trans.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="point_trans_id"  value="${point_transVO.point_trans_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
	</c:forEach>
</table>

</body>
</html>