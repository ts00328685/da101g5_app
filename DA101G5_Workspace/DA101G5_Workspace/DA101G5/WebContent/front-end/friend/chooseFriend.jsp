<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.friendchoose.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 	FriendChooseVO friendChooseVO = (FriendChooseVO) request.getAttribute("friendChooseVO");%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/friend/bootstrap/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
	
	
	
	.card-header{
		background-color:#C2C292;
	}
	
	.selectPart{
		display:flex;
		align-items:center;
		justify-content: center;
		flex-direction: row;
	}
	
	.sex{
		margin-left:5px;
	}
	
	.part{
		display:flex;
		font-size:20px;	
	}
	
	.card{
		height:98%;
		border-color:#7F7F7A;
	}
	
	.card-img-top{
		margin-left:-0.5%;
		margin-bottom: 10%;		
		box-sizing: border-box;
		
	}	
	
	
	
	
	
</style>

</head>
<body>
<%@ include file="/front-end/file/header.jsp" %>
	
	<div class="container" style="padding-top:300px;padding-bottom:200px;">
		<div class="row justify-content-center">
			<div class="col-6">
				<div class="card text-center" style="width: 30rem;">
					<img src="<%= request.getContextPath() %>/front-end/friend/images/students.jpg" class="card-img-top" style="width: 30rem;">
					<div class="card-body">
						<div class="card-text">
							<jsp:useBean id="friend_languageSvc" scope="page" class="com.Language.model.LanguageService" />
							<div class="selectPart">
								<form action="<%=request.getContextPath()%>/friend/friendChoose.do"	method="post">
									<div class="part">
									<div class="selectTitle">										
											<span>請選擇性別：</span>
									</div>
									<div class="selectBody">
											<input type="radio" name="condition_sex" value="0" ${(friendChooseVO.condition_sex==0)? 'checked':''} class="sex">男
											<input type="radio" name="condition_sex" value="1" ${(friendChooseVO.condition_sex==1)? 'checked':''} class="sex">女
										<br>
									</div>
									</div>
									<br>
									<div class="part">
									<div class="selectTitle">
										<span>請選擇語言：</span>
									</div>
									<div class="selectBody">
										<select name="condition_language_id" size="1" id="language" class="custom-select mr-sm-2">
											<option value="-1">請選擇</option>
											<c:forEach var="languageVO" items="${friend_languageSvc.all}">
												<option value="${languageVO.language_id}"
													${(friendChooseVO.condition_language_id==languageVO.language_id)? 'selected':''}>${languageVO.language}</option>
											</c:forEach>
										</select>
									</div>
									</div>

									<br>
									
									<div>
									
									<div>
										<input type="Submit" value="送出" class="btn btn-primary" id="submitbtn">
										<input type="hidden" name="action" value="choose">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	


<%@ include file="/front-end/file/footer.file" %>
<!-- ----------- -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="<%= request.getContextPath() %>/front-end/friend/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>