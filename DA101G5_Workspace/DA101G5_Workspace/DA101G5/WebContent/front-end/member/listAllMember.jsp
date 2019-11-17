<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MemberService memberSvc = new MemberService();
	List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有會員資料 - listAllMember.jsp</title>

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
	width: auto;
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
		 <h3>所有會員資料 - listAllMember.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/member/select_member.jsp">回首頁</a></h4>
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
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員密碼</th>
		<th>會員暱稱</th>
		<th>會員信箱</th>
		<th>會員生日</th>
		<th>會員電話</th>
		<th>會員城市</th>
		<th>會員國家</th>
		<th>會員性別</th>
		<th>會員狀態</th>
		<th>學伴照片</th>
		<th>學伴簡介</th>
		<th>學伴抽籤資格</th>
		<th>學伴狀態</th>
		<th>點數</th>
		<th>優惠卷數量</th>
		<th>會員簡介</th>
		<th>會員創建日期</th>
		<th>會員照片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="/front-end/member/member_file/member_page1.file" %> 
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${memberVO.member_id}</td>
			<td>${memberVO.mem_name}</td>
			<td>${memberVO.mem_pwd}</td>
			<td>${memberVO.mem_nick}</td>
			<td>${memberVO.mem_email}</td> 
			<td>${memberVO.mem_birthday}</td>
			<td>${memberVO.mem_mobile}</td>
			<td>${memberVO.mem_city}</td>
			<td>${memberVO.mem_country}</td>
			<td>${memberVO.mem_sex}</td>
			<td>${memberVO.mem_status}</td>
			<td>${memberVO.friend_pic}</td>
			<td>${memberVO.friend_profile}</td>
			<td>${memberVO.friend_choose}</td>
			<td>${memberVO.friend_appli}</td>
			<td>${memberVO.mem_point}</td>
			<td>${memberVO.couponqty}</td>
			<td>${memberVO.mem_profile}</td>
			<td>${memberVO.mem_createtime}</td>
			<td>${memberVO.mem_pic}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="member_id"  value="${memberVO.member_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="member_id"  value="${memberVO.member_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/front-end/member/member_file/member_page2.file" %>

</body>
</html>