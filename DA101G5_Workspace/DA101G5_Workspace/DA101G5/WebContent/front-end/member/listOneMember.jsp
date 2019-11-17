<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); //MemberServlet.java(Concroller), 存入req的memberVO物件
%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

<!--  -->
<html>
<head>
<title>會員資料 - listOneMember.jsp</title>
<%@ include file="/front-end/file/head.file"%>
<style>
	#memberPicShow{
		width:200px;
		height:200px;
		border-radius: 10px;
		margin-bottom: 50px;
		margin-left: 30px;
		text-align:center;
	}
	
	#fromgp{
		margin-left:100px;
	}
	
	.itembtns:hover{
		background-color:#28a745;
		color:#FFF;
	}
	
	.itembtns{
		width:80%;
		text-align:center;
		margin-left:30px;
		border:2px solid;
	}
</style>
</head>
<body >
<%@ include file="/front-end/file/header.jsp"%>
		<div class="container" style="padding-top: 200px;padding-bottom: 50px;">
		<div class="row justify-content-center">
			<div class="col-3">
				<div style="display:flex;flex-direction:column;justify-content:right;">
					<img id="memberPicShow" src="<%= request.getContextPath() %>/member/memberReader.do?member_id=${memberVO.member_id}" >
				</div>
				<div class="list-group">  					
					<form method="post" action="<%= request.getContextPath() %>/member/member.do">
  						<button type="submit" class="list-group-item list-group-item-action btn-success btn itembtns">修改會員資料</button>
						<input type="hidden" name="member_id" value="${memberVO.member_id}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					</form>
					<form method="post" action="<%= request.getContextPath() %>/front-end/favorque/favorqueIndex.jsp">
  						<button type="submit" class="list-group-item list-group-item-action btn-success btn itembtns">問題收藏</button>
  					</form>
  					<form method="post" action="<%= request.getContextPath() %>/front-end/RecruitMessage/MessageIndex.jsp">
  						<button type="submit" class="list-group-item list-group-item-action btn-success btn itembtns">派單訊息</button>
					</form>
					<form method="post" action="<%= request.getContextPath() %>/front-end/point_trans/point_transAllByOneId.jsp">
  						<button type="submit" class="list-group-item list-group-item-action btn-success btn itembtns">點數儲值</button>
  					</form>
  					<c:if test="${teacherVO==null}">
					<form method="post" action="<%= request.getContextPath() %>/front-end/teacher/addTeacher.jsp">
						<button type="submit" class="list-group-item list-group-item-action btn-success btn itembtns">申請老師</button>
					</form>
  					</c:if>
				</div>
			</div>
			<div class="col-7" style="margin-top:30px;margin-left:80px;">
					<div class="form-group row">
						<label for="staticEmail" class="col-sm-2 col-form-label">會員姓名：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputdata"
								placeholder="${memberVO.mem_name}" readonly required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員暱稱：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputdata"
								placeholder="${memberVO.mem_nick}" readonly required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員信箱：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputdata"
								placeholder="${memberVO.mem_email}" readonly required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員生日：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputdata"
								placeholder="${memberVO.mem_birthday}" readonly required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員手機：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputdata"
								placeholder="${memberVO.mem_mobile}" readonly required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員國家：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputdata"
								placeholder="${memberVO.mem_country}" readonly required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員城市：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputdata"
								placeholder="${memberVO.mem_city}" readonly required>
						</div>
					</div>
					
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員性別：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputdata"
								placeholder="${memberVO.mem_sex}" readonly required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員狀態：</label>
						<div class="col-sm-10">
						<c:if test="${memberVO.mem_status == 0}" var="condition" scope="page">
							<p style="font-size:18px;"><b>尚未取得資格</b></p>
						</c:if>
						<c:if test="${memberVO.mem_status == 1}" var="condition" scope="page">
							<p style="font-size:18px;"><b>會員</b></p>
						</c:if>
						<c:if test="${memberVO.mem_status == 2}" var="condition" scope="page">
							<p style="font-size:18px;"><b>停權中</b></p>
						</c:if>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員點數：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputdata"
								placeholder="${memberVO.mem_point}" readonly required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員優惠卷：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputdata"
								placeholder="${memberVO.couponqty}" readonly required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員創建日期：</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inputdata"
								placeholder="${memberVO.mem_createtime}" readonly required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員簡介：</label>
						<div class="col-sm-10">
							<textarea type="text" class="form-control" id="inputdata"
								 readonly required>${memberVO.mem_profile}</textarea>
						</div>
					</div>
					<hr>
					<h3>學伴專區</h3>
					<hr>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">照片：</label>
						<div class="col-sm-10">	
							<img id="memberPicShow" src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id=${memberVO.member_id}">					
						</div>
					</div>					
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">簡介：</label>
						<div class="col-sm-10">
							<textarea type="text" class="form-control" id="inputdata"
								 readonly required>${memberVO.friend_profile}</textarea>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">興趣語言：</label>
						<div class="col-sm-10">
													
					<jsp:useBean id="language_LikeSvc" class="com.Language.model.LanguageService" scope="page"/>
					<jsp:useBean id="language_ChooseSvc" class="com.friendchoose.model.FriendChooseService" scope="page"/>
					<c:forEach var="chooseMem" items="${language_ChooseSvc.getAll()}">
					<c:if test="${chooseMem.condition_member_id == memberVO.member_id}" var="result" scope="page">
							<span style="font-size:20px;">${language_LikeSvc.getOneLanguage(chooseMem.condition_language_id).language} </span>
							<input type="hidden" name="inLan" value="${language_LikeSvc.getOneLanguage(chooseMem.condition_language_id).language_id}">
					</c:if>
					</c:forEach>
					
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">狀態：</label>
						<div class="col-sm-10">						
							<p style="font-size:18px;"><b>${(memberVO.friend_appli == 0)? "尚未取得資格":"已獲得資格" }</b></p>
						</div>
					</div>
					
					
<!-- 					<div class="form-group row" id="fromgp"> -->
<!-- 						<FORM METHOD="post" -->
<%-- 							ACTION="<%=request.getContextPath()%>/member/member.do" --%>
<!-- 							style="margin-bottom: 0px;"> -->
<!-- 							<input type="submit" value="刪除" class="btn btn-primary"> <input type="hidden" -->
<%-- 								name="member_id" value="${memberVO.member_id}"> --%>
<!-- 							<input	type="hidden" name="action" value="delete"> -->
<!-- 						</FORM> -->
<!-- 					</div> -->
					
				
			</div>
		</div>
	</div>
	<br><br>

<%@ include file="/front-end/file/footer.file"%>
</body>
</html>