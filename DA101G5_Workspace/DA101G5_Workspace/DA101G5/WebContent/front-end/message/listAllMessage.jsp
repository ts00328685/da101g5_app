<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.message.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MessageService messageSvc = new MessageService();
    List<MessageVO> list = messageSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有訊息資料 - listAllMessage.jsp</title>

<style>
  table#table-1 {
  	width: 1200px;
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


<table id="table-1">
	<tr><td>
		 <h3>所有訊息資料 - listAllMessage.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/message/select_message.jsp">回首頁</a></h4>
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
		<th>訊息編號</th>
		<th>會員編號</th>
		<th>對方會員編號</th>
		<th>訊息日期</th>
		<th>訊息內容</th>
		<th>訊息狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="/front-end/message/message_file/message_page1.file" %> 
	<c:forEach var="messageVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${messageVO.message_id}</td>
			<td>${messageVO.member_id}</td>
			<td>${messageVO.member_id2}</td>
			<td>${messageVO.memmsg_date}</td>
			<td>${messageVO.memmsg_ent}</td>
			<td>${messageVO.memmsg_state}</td> 
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/message/message.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="message_id"  value="${messageVO.message_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/message/message.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="message_id"  value="${messageVO.message_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/front-end/message/message_file/message_page2.file" %>

</body>
</html>