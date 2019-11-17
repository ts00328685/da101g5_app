<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Time_order.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	Time_orderService time_orderSvc = new Time_orderService();
	List<Time_orderVO> list = time_orderSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<%-- <jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>

<html>
<head>
<title>所有Time_order資料 - listAllEmp.jsp</title>

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
		 <h3>所有Time_order資料 - listAllEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/time_order/select_page.jsp">
		 <img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>預約時間明細編號</th>
		<th>教師編號</th>
		<th>課程訂單編號</th>
		<th>語言編號</th>
		<th>課程種類編號</th>
		<th>完課狀態</th>
		<th>完課評價</th>
		<th>預約開始時間</th>
		<th>預約結束時間</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="/front-end/time_order/time_order_file/time_order_page1.file" %> 
	<c:forEach var="time_orderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${time_orderVO.time_order_id}</td>
			<td>${time_orderVO.teacher_id}</td>
			<td>${time_orderVO.course_order_id}</td>
			<td>${time_orderVO.language_id}</td>
			<td>${time_orderVO.sort_course_id}</td>
			<td>${time_orderVO.c_state}</td>	
			<td>${time_orderVO.c_judge}</td>
			<td><fmt:formatDate value="${time_orderVO.start_time}" pattern="yyyy-MM-dd HH時"/></td>
			<td><fmt:formatDate value="${time_orderVO.end_time}" pattern="yyyy-MM-dd HH時"/></td>		
<%-- 			<td><c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%--                     <c:if test="${time_orderVO.dept_id==deptVO.dept_id}"> --%>
<%-- 	                    ${deptVO.dept_id}【${deptVO.dname} - ${deptVO.loc}】 --%>
<%--                     </c:if> --%>
<%--                 </c:forEach> --%>
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/time_order/time_order.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="time_order_id"      value="${time_orderVO.time_order_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--><!-- 目前尚未用到  -->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/time_order/time_order.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="time_order_id"      value="${time_orderVO.time_order_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="action"     value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/front-end/time_order/time_order_file/time_order_page2.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>