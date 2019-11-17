<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.friendmanage.model.*"%>

<%
	FriendManageService friendManageSvc = new FriendManageService();
	MemberVO member = (MemberVO) session.getAttribute("memberVO");
	List<FriendManageVO> allFriendList = friendManageSvc.getOneAllFriends(member.getMember_id());
	
	List<MemberVO> allFriendProfile = new ArrayList<MemberVO>();

	if(!(allFriendList.isEmpty())){
		pageContext.setAttribute("allFriendList",allFriendList);
		
	}
	

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
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/front-end/friend/manage/css/normalize.css" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/friend/manage/css/notiflix-1.3.0.min.css">
<title>管理學伴</title>
<%@ include file="/front-end/file/head.file" %>
<style>

	body { 
     
     font-family: '標楷體';     
   } 
	.error{
		color: red;
		list-style-type:none;
		font-size:14px;		
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
	
	#profile .img-size{
		width:100%;	  
		max-width:200px;  
		max-height:200px; 
		myimg:expression(onload=function(){  
		this.style.width=(this.offsetWidth > 200)?"200px":"auto"}); 
		this.style.height=(this.offsetHeight > 200)?"200px":"auto"}); 
	}
	
	
	
	.goToChoose{
		margin-top: 5%;
		
	}
	
	
	
	.page-content-top{
		display:flex;
		align-items: baseline;
		margin-top:30px;
		justify-content: flex-start;
		margin-left: 5%
    			
	}
	
	.search-icon{
		width:40px;
		height:25px;
		text-align:center;
		margin-left:10px;
	}
	
	.search-icon-div{		
		margin-top:10px;
	}
	.SearchType{
		margin-bottom:10px;
		margin-left:50px;
		display:flex;
			
	}
	
	.choose-item{
		margin-left:20px;
		display: flex;
	}
	
	
	
	.page-content-top{
		display: flex;  
		
	}
	
	.page-content{
		width: 1200px;
    	height: 500px;
    	background-color: #FFF;
    	margin-left: 5%;
    	text-align: center;
    	display: flex;    	
    	flex-wrap: wrap;
    	justify-content:left;
		margin-left: 110px;
		border-radius:10px;
		box-shadow:5px 8px;
	}
	
	.memberProfile{
		margin:10px 10px;
		display:flex;
		align-content: center;
		justify-content:left;
		text-align:center;
		
	}
	
	.memberPic-size{
		width:100px;
		height:100px;
		border-radius:100px;
		overflow:hidden;
		margin-bottom: 5px;
		box-shadow: 3px 3px rgba(136, 136, 136, 0.3);
		cursor:pointer;
	}	
	
	.memberPic{
		width:100px;
		height:100px;
		text-align:center;
		margin-right:auto;
		margin-left:-5px;
		
	}
	
	.memberText{
		color:rgb(136, 136, 136);
		margin-left:10px;
		margin-top:15px;
	}
	
	.page-right{
		display: flex;
		flex-direction:column;
		justify-content: flex-start;
		
		
	}
	
	.friend-do{
		display:flex;
		flex-direction:column;
		margin-left:10px;
		
		
		
	}
	
	.friendDoForm{
		margin-top: 1px;
	}
	
	.pagination{
		position: absolute;
    	top: 710px;
    	right: 620px;	
	}
	
@media only screen and (max-width: 1000px) {
  .pagination{
  	right: -100px;
  	top: 720px;
  }
}	
	
	
	

	
</style>
<script src="https://code.jquery.com/jquery-3.3.1.js" ></script>
</head>
<script>


$(document).ready(function(){
	 $('#SearchType-sex').change(function(){
		 
		 $.ajax({
			 type: "GET",
			 url: "<%=request.getContextPath()%>/friend/friendManage.do",
			 data: creatQueryString($(this).val(),$('#SearchType-lan').val()),
			 dataType: "json",
			 success: function (data){
				
				clearResult();
					if(data.length > 0){
						<% int b = 0; %>
						$.each(data, function(i, item){
							<%++b;%>
							var str="";
							str += "<div class='memberProfile' id='friends'><div class='memberPic-size'>"+
							"<button type='button' class='memberPic-size btn-light' id='friend-enter' >"+"<img src='<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id="+ item.member_id + "'" + "class='memberPic'></button></div><span class='badge-dot' id='dot-<%= b%>" + item.member_id + "' style='display: none;margin-top:20px;margin-left:5px;background-color:red;'></span>"+
							"<div class='friend-do'>"+"<p class='memberText'>"+item.mem_nick+"</p>"+
							"<div class='friendDoForm'><button type='button'class='btn btn-primary' data-toggle='modal' data-target='#myModal<%= b%>'>解除關係</button></div></div>"+
							"<div class='modal fade' id='myModal<%= b%>' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel' aria-hidden='true'>"+
  					"<div class='modal-dialog  modal-dialog-centered' role='document'>"+
    					"<div class='modal-content'>"+
      						"<div class='modal-header'>"+
        						"<h5 class='modal-title' id='exampleModalLabel'>提醒</h5>"+
        						"<button type='button' class='close' data-dismiss='modal' aria-label='Close'>"+
          							"<span aria-hidden='true'>&times;</span>"+
        						"</button>"+
      						"</div>"+
     						"<div class='modal-body'>"+
        						"<p>您確定要解除學伴關係嗎?</p>"+
     						"</div>"+
      						"<div class='modal-footer'>"+
        						"<button type='button' class='btn btn-secondary' data-dismiss='modal'>取消</button>"+
        						"<form action='<%=request.getContextPath()%>/friend/friendManage.do' method='get' class='cancelBtn<%= b%>'>"+
									"<input type='Submit'class='btn btn-primary' value='確定'>"+
									"<input type='hidden' name='action' value='cancelFriend'>"+
									"<input type='hidden' name='who' value='"+item.member_id+"' id='show'>"
								"</form>"+
      						"</div>"+
      					"</div>"+
      				"</div>"+
      				"</div>";
							
							$('#people').append(str);
							console.log(str);
						});
					}
			 
		 		
//				$(data).each(function(i, item){
//					$('#class').append("<option value='"+item.classId+"'>"+item.className+"</option>");
//				});
//				jQuery.each(data, function(i, item){
//					$('#class').append("<option value='"+item.classId+"'>"+item.className+"</option>");
//				});
		     },
            error: function(){}
        })
	 })
	 
	 $('#SearchType-lan').change(function(){
		 
		 $.ajax({
			 type: "GET",
			 url: "<%=request.getContextPath()%>/friend/friendManage.do",
			 data: creatQueryString($('#SearchType-sex').val(),$(this).val()),
			 dataType: "json",
			 success: function (data){	
				clearResult();
				if(data.length > 0){
				<% int a = 0; %>
				$.each(data, function(i, item){
					<%++a;%>
					var str="";
					str += "<div class='memberProfile' id='friends'><div class='memberPic-size'>"+
					"<button type='button' class='memberPic-size btn-light' id='friend-enter' >"+"<img src='<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id="+ item.member_id + "'" + "class='memberPic'></button></div><span class='badge-dot' id='dot-<%= a%>" + item.member_id + "' style='display: none;margin-top:20px;margin-left:5px;background-color:red;'></span>"+
					"<div class='friend-do'>"+"<p class='memberText'>"+item.mem_nick+"</p>"+
					"<div class='friendDoForm'><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#myModal<%= a%>'>解除關係</button></div></div>"+
					"<div class='modal fade' id='myModal<%= a%>' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel' aria-hidden='true'>"+
  					"<div class='modal-dialog  modal-dialog-centered' role='document'>"+
    					"<div class='modal-content'>"+
      						"<div class='modal-header'>"+
        						"<h5 class='modal-title' id='exampleModalLabel'>提醒</h5>"+
        						"<button type='button' class='close' data-dismiss='modal' aria-label='Close'>"+
          							"<span aria-hidden='true'>&times;</span>"+
        						"</button>"+
      						"</div>"+
     						"<div class='modal-body'>"+
        						"<p>您確定要解除學伴關係嗎?</p>"+
     						"</div>"+
      						"<div class='modal-footer'>"+
        						"<button type='button' class='btn btn-secondary' data-dismiss='modal'>取消</button>"+
        						"<form action='<%=request.getContextPath()%>/friend/friendManage.do' method='get' class='cancelBtn<%= a%>'>"+
									"<input type='Submit'class='btn btn-primary' value='確定'>"+
									"<input type='hidden' name='action' value='cancelFriend'>"+
									"<input type='hidden' name='who' value='"+item.member_id+"' id='show'>"
								"</form>"+
      						"</div>"+
      					"</div>"+
      				"</div>"+
      				"</div>";
					$('#people').append(str);
					console.log(str);
				});
			 	}
//				$(data).each(function(i, item){
//					$('#class').append("<option value='"+item.classId+"'>"+item.className+"</option>");
//				});
//				jQuery.each(data, function(i, item){
//					$('#class').append("<option value='"+item.classId+"'>"+item.className+"</option>");
//				});
		     },
            error: function(){
            	}
        })
	 })
})



	
	
	
	function creatQueryString(paramSex, paramLan){
		console.log("paramSex:"+paramSex+"paramLan:"+paramLan);
		var queryString= "action=searchMyFriend&SearchType-sex=" + paramSex + "&SearchType-lan=" + paramLan;
		return queryString;
	}
	
	function clearResult(){
		$('#people').html('');				
	}


</script>
	
<body>
<%@ include file="/front-end/file/header.jsp" %>
		

	<!-- Sidebar -->
	<div id="page-top" style="padding-top:200px;padding-bottom:0px;font-size:24px;">
		<div id="wrapper" style="padding-top:0px;margin-left:50px;">

			<!-- Sidebar -->
			<ul	class="navbar-nav bg-color sidebar sidebar-dark accordion" id="accordionSidebar">
			
			<!-- Sidebar - Profile -->
				<div class="sidebar-brand d-flex align-items-center justify-content-center" id="profile">
					<div class="sidebar-brand-icon">
						<img src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id=${memberVO.getMember_id()}" class="img-size">
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
							<i class="far fa-envelope i-color"></i>
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
		<div class="page-right">
			<div class="page-content-top">				
				<jsp:useBean id="friend_languageSvc" scope="page" class="com.Language.model.LanguageService" />
				<div class="SearchType">
 				
					<div class="choose-item">
						<span style="width:210px;text-align: center;margin-top: 5px;margin-left: -35px;"><b>性別：</b></span>
						<select name="SearchType-sex" size="1" id="SearchType-sex" class="custom-select mr-sm-2">
							<option value="-1">不限</option>
							<option value="0">男</option>
							<option value="1">女</option>
						</select>						
					</div>
					
					<div class="choose-item">
						<span style="width:210px;text-align: center;margin-top: 5px;margin-left: -35px;"><b>語言：</b></span>
						<select name="SearchType-lan" size="1" id="SearchType-lan" class="custom-select mr-sm-2">
							<option value="-1">不限</option>				
							<c:forEach var="languageVO" items="${friend_languageSvc.all}">
								<option value="${languageVO.language_id}" >${languageVO.language}</option>
							</c:forEach>
						</select>				
					</div>
				</div>
					
			</div>

			
			
			<div class="page-content">
				
				<%@ include file="/front-end/friend/friend_file/friend_page.file" %>
				
				
				<% int number = 0; %>
				<div id="people" style="display:flex;">
				<jsp:useBean id="FindFriendProfile" class="com.member.model.MemberService"/>
				<c:forEach var="allFriend" items="${allFriendList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<c:if test="${allFriend.friend_status == '1'}" var="condition" scope="page" > 		
					
					<% ++number; %>					
					<div class="memberProfile" id="friends">
						<div class="memberPic-size">
							<button type="button" class="memberPic-size btn-light " id="friend-enter" ><img src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id=${allFriend.friend_member_fid}" class="memberPic"></button>
						</div>							
						<div class="friend-do">
						<p class="memberText">${FindFriendProfile.getOneMember(allFriend.friend_member_fid).mem_nick}</p>
							<div class="friendDoForm">
								<button type="button"class="btn btn-primary" data-toggle="modal" data-target="#myModal<%= number%>">解除關係</button>
							</div>
						</div>					
					</div>
				
				<!-- Modal -->
				<div class="modal fade" id="myModal<%= number%>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  					<div class="modal-dialog  modal-dialog-centered" role="document">
    					<div class="modal-content">
      						<div class="modal-header">
        						<h5 class="modal-title" id="exampleModalLabel">提醒</h5>
        						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          							<span aria-hidden="true">&times;</span>
        						</button>
      						</div>
     						<div class="modal-body">
        						<p>您確定要解除學伴關係嗎?</p>
     						</div>
      						<div class="modal-footer">
        						<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        						<form action="<%=request.getContextPath()%>/friend/friendManage.do" method="get" class="cancelBtn<%= number%>">
									<input type="Submit"class="btn btn-primary" value="確定">
									<input type="hidden" name="action" value="cancelFriend">
									<input type="hidden" name="who" value="${allFriend.friend_member_fid}" id="show">
								</form>
      						</div>
    					</div>
  					</div>
				</div>
				</c:if> 
				</c:forEach>
				</div>
				<nav aria-label="Page navigation example">
  					<ul class="pagination" >
    					<li class="page-item">
      						<a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>" aria-label="Previous">
        						<span aria-hidden="true">&laquo;</span>
     						</a>
    					</li>
    					<li class="page-item">
    						
    						<%if (pageNumber>0){%>
  							<span><b style="font-size: 22px;line-height: 1.8;"><%=whichPage%>/<%=pageNumber%></b></span>
							<%}%>
    					</li>
    					</li>
<%--     			<c:forEach var="myPage" begin="<%=pageIndex+1%>" end="<%=pageIndex+rowsPerPage%>" > --%>
<%--     					<li class="page-item active"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>">${myPage}</a></li> --%>
<%--     			</c:forEach> --%>
    					<li class="page-item">
      						<a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>" aria-label="Next">
        					<span aria-hidden="true">&raquo;</span>
      						</a>
    					</li>
    					
  					</ul>
				</nav>
				
			</div>
		</div>		
	</div>

	

<%@ include file="/front-end/file/footer.file" %>
<!-- ----------- -->
<script src="<%= request.getContextPath() %>/front-end/friend/bootstrap/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/front-end/friend/bootstrap/js/bootstrap.bundle.js"></script>

<script	src="<%= request.getContextPath() %>/front-end/friend/manage/vendor/jquery-easing/jquery.easing.min.js"></script>
<script	src="<%= request.getContextPath() %>/front-end/friend/manage/js/sb-admin-2.min.js"></script>
<script	src="<%= request.getContextPath() %>/front-end/friend/manage/vendor/chart.js/Chart.min.js"></script>
<script	src="<%= request.getContextPath() %>/front-end/friend/manage/js/demo/chart-area-demo.js"></script>
<script	src="<%= request.getContextPath() %>/front-end/friend/manage/js/demo/chart-pie-demo.js"></script>

<script type="text/javascript">
			
			
		$(document).on('click', '#friend-enter',function(){
			$('#friend-do').show();
			console.log("123");
		});
		
		function isSubmit(number){					
			document.getElementById("cancelFriend"+ number).submit();
		}	

</script>
</body>
</html>