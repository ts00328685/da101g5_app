<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.friendmanage.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/friend/bootstrap/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/friend/font_awesome/css/all.min.css">
<title>學伴抽籤</title>
<%@ include file="/front-end/file/head.file" %>
<style>

	body{
		background-image: url("<%= request.getContextPath() %>/front-end/friend/images/email-pattern.png");
 		background-attachment: fixed; 
 		background-repeat: no-repeat; 
 		background-position:center; 
 		background-size: cover; 
 	} 
	
	.error{
		color: red;
		list-style-type:none;		
	}
	
	
	
	.card{
		height:98%;
		border: 2px dotted #7F7F7A;
		box-shadow: 8px 10px;
	}
	
	.card-body{
		display:flex;
		flex-direction: column;
		justify-content: space-around;
		margin-bottom:10%;
		margin-left:5%;
	}
	
	.formYesOrNo{
		display:flex;
		flex-direction: row;
	}
	
	.ldd{
		margin-top:100px;
		margin-right:500px;
		margin-left:500px;
		display:flex;
		padding-bottom:150px;
	}
	
</style>
<script>
	var i = 0;
	var call;
	function ld(){
		if(i > 5){
			$('#re').css("display","none");
			$('#re').show("slow");
			$('.ldd').hide();
			clearTimeout(call);
		}else{
			$('#re').hide();
			i++;			
			console.log("i=" + i);
		}
		
		call = setTimeout(ld,500);
	}
</script>

</head>
<body onload="ld()">
<%@ include file="/front-end/file/header.jsp" %>
				
	

	<div class="container" style="padding-top:200px;padding-bottom:100px;">
		<div class="row justify-content-center">
			
			<div class="col-4">
				<div class="card text-center" style="width: 25rem;display:none" id="re">
					<img src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id=${memberPro.member_id}" class="card-img-top">
					<div class="card-body body1" >
						<h5 class="card-title">${memberPro.mem_nick}</h5>
						<p class="card-text">${memberPro.friend_profile}</p>
					</div>
					<div class="card-body formYesOrNo">
						<form action="<%=request.getContextPath()%>/friend/friendChoose.do" method="post">
							<input type="Submit" class="card-link btn-outline-success btn btn-lg" value="YES">
							<input type="hidden" name="action" value="SayInvite">
							<input type="hidden" name="token" value="${token}">
							<input type="hidden" name="choosewho" value="${memberPro.member_id}">
						</form>
						<form action="<%=request.getContextPath()%>/friend/friendChoose.do" method="post">
							<input type="Submit" class="card-link btn-outline-success btn btn-lg" value="NO">
							<input type="hidden" name="action" value="SayNoInvite">
							<input type="hidden" name="token" value="${token}">
							<input type="hidden" name="choosewho" value="${memberPro.member_id}">
						</form>
					</div>
				</div>

			</div>
			<div class="row-4">
			<div class="ldd">
				<div class="spinner-grow text-primary" role="status">
  					<span class="sr-only">Loading...</span>
				</div>
				<div class="spinner-grow text-secondary" role="status">
  					<span class="sr-only">Loading...</span>
				</div>
				<div class="spinner-grow text-success" role="status">
  					<span class="sr-only">Loading...</span>
				</div>
				<div class="spinner-grow text-danger" role="status">
  					<span class="sr-only">Loading...</span>
				</div>
				<div class="spinner-grow text-warning" role="status">
  					<span class="sr-only">Loading...</span>
				</div>
				<div class="spinner-grow text-info" role="status">
  					<span class="sr-only">Loading...</span>
				</div>
				<div class="spinner-grow text-light" role="status">
  					<span class="sr-only">Loading...</span>
				</div>
				<div class="spinner-grow text-dark" role="status">
  					<span class="sr-only">Loading...</span>
				</div>
				</div>
				</div>
	</div>
	</div>
	
	
	
	
<%@ include file="/front-end/file/footer.file" %>

<!-- ---- -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/friend/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>