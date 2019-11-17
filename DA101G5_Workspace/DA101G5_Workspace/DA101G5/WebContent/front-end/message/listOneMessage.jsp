<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.message.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>


<jsp:useBean id="messageSvc" scope="page" class="com.message.model.MessageService" />

<html>
<head>
<title>訊息資料 - listOneMessage.jsp</title>

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
		 <h3>訊息資料 - ListOneMessage.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/index.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訊息編號</th>
		<th>會員姓名</th>
		<th>會員姓名</th>
		<th>訊息日期</th>
		<th>訊息內容</th>
		<th>訊息狀態</th>
		<th>刪除</th>
	</tr>
	<c:forEach var="messageVO" items="${messageSvc.all}">
	<tr>
		<td>${messageVO.message_id}</td>
		<td>${messageVO.member_id}</td>
		<td>${messageVO.member_id2}</td>
		<td>${messageVO.memmsg_date}</td>
		<td>${messageVO.memmsg_ent}</td>
		<td>${messageVO.memmsg_state}</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/message/message.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="message_id"  value="${messageVO.message_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
	</c:forEach>
</table>

</body>
</html>