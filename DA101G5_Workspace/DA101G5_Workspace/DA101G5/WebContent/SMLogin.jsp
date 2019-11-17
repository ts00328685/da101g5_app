<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
body{
	width:100%;
	height:100%;
	background-image: url("back-end/images/7.jpg");
}

#container {
 width:100%; 
 padding-top:200px;
 padding-bottom:500px;
}
</style>
<%@include file="back-end/file/head.file" %>
<meta charset="UTF-8">
<title>管理員登入頁</title>
</head>
<body>
<%@include file="back-end/file/header_back2.jsp" %>
<div id="container" class="container">
  <div class="row justify-content-center">	
	<c:if test="${sessionScope.system_managerVO==null}">
	<form method="POST" action="<%=request.getContextPath() %>/AdministratorServlet">
		<table>
			<tr>
				<td><font>請輸入帳號:</font></td>
				<td><input type="text" name="SM_AC" value="green800809" required></td>
			</tr>
			<tr>
				<td><font>請輸入密碼:</font></td>
				<td><input type="password" name="SM_PASS" value="qeetaddg" required></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="登入"></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="Adminlogin">
	</form>
	</c:if>
	<c:if test="${sessionScope.system_managerVO!=null}">
		<div class="row justify-content-center">
			<H1 style="font-size:25px"><font color="white">你好 管理員</font><font color="red">${sessionScope.system_managerVO.sm_name}</font></H1>
			<button type="button" class="btn btn-primary"  onclick="location.href='<%=request.getContextPath()%>/back-end/home.jsp'">前往管理員功能頁</button>
		</div>
		<br>
		
	</c:if>
  </div>	
</div>	
<%@include file="back-end/file/footer.file" %>		
</body>
</html>