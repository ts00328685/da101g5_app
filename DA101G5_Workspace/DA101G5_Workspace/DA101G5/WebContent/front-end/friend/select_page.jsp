<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.friendchoose.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="javax.servlet.*"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/bootstrap/css/bootstrap.min.css">

<title>學伴首頁</title>

<%@ include file="/front-end/file/head.file" %>
<style>
	body{
		background-image: url("<%= request.getContextPath() %>/front-end/friend/images/chooseForm.jpg");
 		background-attachment: fixed; 
 		background-repeat: no-repeat; 
 		background-position:center; 
 		background-size: cover; 
 	} 
 	
	.content{
		display:flex;
		flex-direction: row;
	}
	
	.part{
		position:abolute;
		top:50%;
		left:50%;
		margin-right:120px;
		margin-left:-50px;
		
	}
	
	#chooseb:hover{
		box-shadow: 2px 10px rgba(0,0,0,0.2);
		transform: scale(1.6);
	}
	
	#manageb:hover{
		box-shadow: 2px 10px rgba(0,0,0,0.2);
		transform: scale(1.6);
	}
	
	
</style>

</head>
<body>
<%@ include file="/front-end/file/header.jsp" %>



	<div class="container" style="padding-top:400px;padding-bottom:300px;">
		<div class="row justify-content-center">
			<div class="col-4">
				<div class="content">
				<div class="part">
					<form action="<%=request.getContextPath()%>/friend/friendChoose.do" method="post" id="chooseForm">
						<input id="chooseb" type="Submit" value="每日抽籤" class="btn btn-success btn-lg" style="width:200px;height:100px;font-weight:900;">
						<input type="hidden" name="action" value="goToChoose">
						<input type="hidden" name="choose_location" value="<%=request.getServletPath()%>">
					</form>
				</div>
				<div class="part">
					<form action="<%=request.getContextPath()%>/friend/friendManage.do" method="post">
						<input id="manageb" type="Submit" value="學伴管理" class="btn btn-success btn-lg" style="width:200px;height:100px;font-weight:900;">
						<input type="hidden" name="action" value="goToManage">
					</form>
				</div>
				</div>
				
			</div>
			<!---------錯誤顯示----------->	
		</div>
	</div>		

	
	




<!-- ----------- -->
<%@ include file="/front-end/file/footer.file" %>
<!-- ----------- -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="<%= request.getContextPath() %>/front-end/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>