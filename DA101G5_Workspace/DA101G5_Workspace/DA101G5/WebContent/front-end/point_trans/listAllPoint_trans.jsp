<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.point_trans.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Point_transService point_transSvc = new Point_transService();
    List<Point_transVO> list = point_transSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有點數交易資料 - listAllPoint_trans.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有點數交易資料 - listAllPoint_trans.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/point_trans/select_point_trans.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>點數交易編號</th>
		<th>會員編號</th>
		<th>點數紀錄</th>
		<th>交易日期</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="/front-end/point_trans/point_trans_file/point_trans_page1.file" %> 
	<c:forEach var="point_transVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${point_transVO.point_trans_id}</td>
			<td>${point_transVO.member_id}</td>
			<td>${point_transVO.point_record}</td>
			<td>${point_transVO.transdate}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/point_trans/point_trans.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="point_trans_id"  value="${point_transVO.point_trans_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/point_trans/point_trans.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="point_trans_id"  value="${point_transVO.point_trans_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/front-end/point_trans/point_trans_file/point_trans_page2.file" %>

</body>
</html>