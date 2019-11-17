<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.message.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.friendmanage.model.*"%>

<%
	MessageService MessageSvc = new MessageService();
	MemberVO member = (MemberVO) session.getAttribute("memberVO");
	List<MessageVO> inviteList = MessageSvc.getOneAllMessages(member.getMember_id());
	pageContext.setAttribute("inviteList",inviteList);
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/friend/bootstrap/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/friend/font_awesome/css/all.min.css">
<link href="<%= request.getContextPath() %>/front-end/friend/manage/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<link href="<%= request.getContextPath() %>/front-end/friend/manage/css/sb-admin-2.min.css" rel="stylesheet">
<title>管理訊息</title>
<%@ include file="/front-end/file/head.file" %>
<style>
	body { 
     
     font-family: '標楷體';     
   } 
	

	.error{
		color: red;
		list-style-type:none;
		font-size: 14px;		
	}
	
	
	
	#page-top{
		background-color: #f5f5f5;
		display:flex;
		
	}
	
	
	
	.bg-color{
		background-color: #f5f5f5;
		margin-top:5px;
		margin-left:80px;
	}
	
	
	.sidebar-brand-icon{
		width:200px;
		height:200px;
		overflow: hidden;
		border-radius:5px;
		
	}
	
	
	#profile{
		height: 200px;
    	width: 400px;
    	margin-left: -100px;
    	margin-bottom: 20px;	
	}
	
	#profile img{
		width:100%;	  
		max-width:200px;  
		max-height:200px; 
		myimg:expression(onload=function(){  
		this.style.width=(this.offsetWidth > 200)?"200px":"auto"}); 
		this.style.height=(this.offsetHeight > 200)?"200px":"auto"}); 
	}
	
	
	
	
	
	
	.page-content{
		
		width: 1000px;
    	height: 500px;
		background-color: #FFF;
    	margin-left: 5%;
    	text-align: center;
    	display: flex;    	
    	flex-wrap: wrap;
    	justify-content:left;
		margin-left: 135px;
		
		
		
	}
	
	
	.Invitecontent{
		display:flex;
		flex-direction:column;		
		text-align: center;
		width:75%;
		height: 50%;
	}	
	
	
	.choose{
		display:flex;
		justify-content:center;
		
	}
	
	.formList{
		display:flex;
		margin-top:70px;
	}
	
	#mesgsImg{
		width:100px;
		height:100px;
	}
	
	.container{
		text-align:center;
	}
	
	
</style>
</head>
<body>
<%@ include file="/front-end/file/header.jsp" %>	


	<!-- Sidebar -->
	<div id="page-top" style="padding-top:200px;padding-bottom:50px;font-size:24px;">
		<div id="wrapper" style="padding-top:0px;margin-left:50px;">

			<!-- Sidebar -->
			<ul	class="navbar-nav bg-color sidebar sidebar-dark accordion" id="accordionSidebar">
			
			<!-- Sidebar - Profile -->
				<div class="sidebar-brand d-flex align-items-center justify-content-center" id="profile">
					<div class="sidebar-brand-icon">
						<img src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id=${memberVO.getMember_id()}" class="memberPic">
					</div>
				</div>


			<!-- Divider -->

			<!-- Nav Item - Friend -->
				<li class="nav-item">
					<div class="nav-link collapsed">
						<form action="<%=request.getContextPath()%>/friend/friendManage.do" method="post">
							<i class="fas fa-users"></i>
							<input type="Submit" value="學伴名單" class="btn"	id="btn_myFriend" style="font-size:24px;">
							<input type="hidden" name="action"	value="myFriend">
						</form>
					</div>					
				</li>

			<!-- Nav Item - Invitation -->
				<li class="nav-item">
					<div class="nav-link collapsed">
						<form action="<%=request.getContextPath()%>/friend/friendManage.do" method="post">
							<i class="far fa-envelope"></i>
							<input type="Submit" value="訊息管理" class="btn"	id="btn_myInvite" style="font-size:24px;">
							<input type="hidden" name="action"	value="myInvite">
						</form>
					</div>					
				</li>

			


				<!-- 分割線 -->
				<hr class="sidebar-divider d-none d-md-block">

				<!-- GoToChoose -->
				<div class="text-center d-none d-md-inline goToChoose">
					<form action="<%=request.getContextPath()%>/friend/friendChoose.do" method="post">
						<input type="Submit" value="每日抽籤" class="btn btn-outline-success btn-lg">
						<input type="hidden" name="action" value="goToChoose">
						<input type="hidden" name="choose_location" value="<%=request.getServletPath()%>">
					</form>
				</div>
			</ul>
		</div>
		
		<div class="container" style="padding-top: 20px;padding-bottom: 50px;justify-content:center;margin-left:90px;">
  <div class="column justify-content-center">
    <div class="col-4">
      <div class="list-group list-group-horizontal-lg" id="list-tab" role="tablist">
        <a class="list-group-item list-group-item-action active" id="list-home-list" data-toggle="list" href="#list-home" role="tab" aria-controls="home" style="margin-left:10px;border-radius:10px;">邀請</a>
        <a class="list-group-item list-group-item-action" id="list-profile-list" data-toggle="list" href="#list-profile" role="tab" aria-controls="profile" style="margin-left:10px;border-radius:10px;">通知</a>
      </div>
    </div>
    <div class="col-8" style="max-width:1000px;margin-top:5px;">
      <div class="tab-content" id="nav-tabContent">
        <div class="tab-pane fade show active" id="list-home" role="tabpanel" aria-labelledby="list-home-list">
          <jsp:useBean id="friend_memberSvc" scope="page" class="com.member.model.MemberService"/>
          <% int i = 0; %>
          <c:forEach var="mesgsLi" items="${inviteList}">
          <c:if test="${mesgsLi.memmsg_state == '0'}" var="con" scope="page">
          <% ++i; %>
           <li class="list-group-item list-group-item-light" style="display: flex;">
            <div class="col-md-4">              
              <img id="mesgsImg" src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id=${mesgsLi.memmsg_ent}">
            </div>
            <div class="col-md-2">
              <p>${friend_memberSvc.getOneMember(mesgsLi.memmsg_ent).mem_nick}</p>
            </div>
              <div class="col-md-3">
                  <form action="<%=request.getContextPath()%>/friend/friendManage.do" class="choose<%= i%>" method="post">
                    <input type="Submit" value="接受" class="btn btn-success">
                    <input type="hidden" name="action" value="accept">
                    <input type="hidden" name="whichOne" value="${mesgsLi.memmsg_ent}">
                   </form>
              </div>
              <div class="col-md-3">
                  <form action="<%=request.getContextPath()%>/friend/friendManage.do" class="choose<%= i%>" method="post">
                    <input type="Submit" value="不接受" class="btn btn-warning">
                    <input type="hidden" name="action" value="reject">
                    <input type="hidden" name="whichOne" value="${mesgsLi.memmsg_ent}">
                </form>
              </div>
           </li>
           </c:if>
           </c:forEach>
        </div>
        <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list">
        	<jsp:useBean id="friend_noti" scope="page" class="com.friendmanage.model.FriendManageService"/>
        	<c:forEach var="FnoneCheck" items="${friend_noti.getOneAllFriends(memberVO.member_id)}">
        	<c:if test="${FnoneCheck.friend_status == 0}" var="conn" scope="page">
        		<li class="list-group-item list-group-item-light" style="display: flex;">
            <div class="col-md-4">              
              <img id="mesgsImg" src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id=${FnoneCheck.friend_member_fid}">
            </div>
            <div class="col-md-2">
              <p>${friend_memberSvc.getOneMember(FnoneCheck.friend_member_fid).mem_nick}</p>
            </div>
              <div class="col-md-3">
                  <form action="<%=request.getContextPath()%>/friend/friendChoose.do" method="post">
						<input type="Submit" class="card-link btn-outline-success btn btn-lg" value="YES">
						<input type="hidden" name="action" value="SayInvite">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<input type="hidden" name="choosewho" value="${FnoneCheck.friend_member_fid}">
						<input type="hidden" name="token2" value="else">					
				  </form>
              </div>
              <div class="col-md-3">
                  <form action="<%=request.getContextPath()%>/friend/friendChoose.do" method="post">
						<input type="Submit" class="card-link btn-outline-success btn btn-lg" value="NO">
						<input type="hidden" name="action" value="SayNoInvite">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
						<input type="hidden" name="choosewho" value="${FnoneCheck.friend_member_fid}">
						<input type="hidden" name="token2" value="else">						
				  </form>
              </div>
           </li>
        	</c:if>
        	</c:forEach>
        </div>
      </div>
    </div>
  </div>
</div>

		</div>
		
<%@ include file="/front-end/file/footer.file" %>


<!-- ----------- -->
<script src="<%= request.getContextPath() %>/front-end/friend/bootstrap/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>

<script	src="<%= request.getContextPath() %>/front-end/friend/manage/vendor/jquery-easing/jquery.easing.min.js"></script>
<script	src="<%= request.getContextPath() %>/front-end/friend/manage/js/sb-admin-2.min.js"></script>
<script	src="<%= request.getContextPath() %>/front-end/friend/manage/vendor/chart.js/Chart.min.js"></script>
<script	src="<%= request.getContextPath() %>/front-end/friend/manage/js/demo/chart-area-demo.js"></script>
<script	src="<%= request.getContextPath() %>/front-end/friend/manage/js/demo/chart-pie-demo.js"></script>
<script>

</script>
</body>
</html>