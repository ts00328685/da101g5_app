<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.recruit.model.*" %> 
<%
    /*if(不是管理員){
    	用濾器導向前台派單首頁
    }*/

    if(request.getParameter("recruit_id")==null){//打網址進來 導回前台派單區首頁
    	response.sendRedirect(request.getContextPath()+"/front-end/recruit/RecruitIndex.jsp");
    }
    String recruit_id = request.getParameter("recruit_id");
    RecruitService recruitSvc = new RecruitService();
    RecruitVO recruitVO = recruitSvc.findRecruitByPK(recruit_id);
    pageContext.setAttribute("recruitVO", recruitVO);
%>   
<!DOCTYPE html>
<html>
<head>
<style>
body{
	font-family:Microsoft JhengHei;
}
</style>
<%@ include file="/back-end/file/head.file"%>
<meta charset="UTF-8">
<title>被檢舉派單檢視+處理</title>
</head>
<body>
<%@include file="/back-end/file/header_back.jsp" %>
<br>
<br>
<br>
<br>
<br>
<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
<div class="row justify-content-center">
		  <div class="col-3">	
			<div class="card" style="width: 36rem;">
	            <div class="card-header">
	                <ul class="nav nav-pills card-header-pills">
	                  <li class="nav-item">
	                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">派單編號:${recruitVO.recruit_id}</a>
	                  </li>
	                </ul>
	            </div>
	            <div class="card-body">
	                <h5 class="card-title">需求:${recruitVO.rtitle}</h5> 
	                <h6 class="card-subtitle mb-2 text-muted">發文者:${memberSvc.getOneMember(recruitVO.member_id).mem_nick}</h6>
	                <p class="card-text">說明:<br>${recruitVO.rcontent}</p>
	                <a href="#" class="card-link">Another link</a>
	                <p class="card-text"><small class="text-muted">發文日期:${recruitVO.startdate.toString().substring(0,19)}</small></p>
	             </div>
	         </div>
	     </div>     
	   </div> 
<div class="row justify-content-center">
	<button type="button" class="btn btn-primary" onclick="history.back()">回上頁</button>
</div>
<%@include file="/back-end/file/footer.file" %>	        
</body>
</html>