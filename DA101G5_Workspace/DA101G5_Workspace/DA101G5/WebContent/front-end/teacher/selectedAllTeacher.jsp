<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Teacher.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
	TeacherService teacherSvc = new TeacherService();
List<TeacherVO> selectedList = teacherSvc.getSelectedAll();
	List<TeacherVO>searchingResult=null;
	List<TeacherVO>sendResult=null;
	if(request.getAttribute("searchingResult")!=null){
		searchingResult=(List<TeacherVO>)request.getAttribute("searchingResult");
	}
	for(TeacherVO teacherVO:selectedList){
		if(searchingResult!=null&&searchingResult.contains(teacherVO)){
			sendResult=searchingResult;
			break;
		}
	}
	if(sendResult==null){
		sendResult=selectedList;
		pageContext.setAttribute("list", sendResult);
	}else {
		pageContext.setAttribute("list", sendResult);
	}
	
	
	
%>


<%
	MemberService memberSvc = new MemberService();
	List<MemberVO> memlist = memberSvc.getAll();
	pageContext.setAttribute("memlist", memlist);
%> 
	
<%-- <c:forEach var="teacherVO" items="${list}"> --%>
<%-- 	<c:if test="${teacherVO.teacher_state==1}"> --%>
<%-- 	<c:remove var="teacherVO"/> --%>
<%-- 	</c:if> --%>
<%-- </c:forEach> --%>

<style>
.checked {
  color: gold;
}
</style>
<html>
<head>


<title>懶骨雞</title>

<style>
body {
	font-family: 微軟正黑體;
}
</style>

<%@ include file="/front-end/file/head.file"%>

</head>
<body >
<%@ include file="/front-end/file/header.jsp"%>
<div class="container justify-content-center"style="padding-top:200px;padding-bottom:200px;">


	<%-- 錯誤表列 --%>
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 		<font style="color: red">請修正以下錯誤:</font> -->
<!-- 		<ul> -->
<%-- 			<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 				<li style="color: red">${message}</li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<%-- 	</c:if> --%>

	
		
<%-- 		<%@ include file="/front-end/teacher/t_file/teacher_page1.file"%> --%>
		<c:forEach var="teacherVO" items="${list}"> 
<%-- 		 begin="<%=pageIndex%>" --%>
<%--  			end="<%=pageIndex+rowsPerPage-1%>"  --%>

<div style="padding-bottom: 30px">
<div class="card border-primary" style="max-width: 100rem;height: 300px; border-radius: 10px;padding-bottom: 30px;">
<div class="card-body text-primary  row">


			
<div style="padding-left:50px; padding-right:50px;">
<a href="<%=request.getContextPath()%>/teacher/teacher.do?action=getOne_For_Display&teacher_id=${teacherVO.teacher_id}" >	
	<img width="270px"height="270px"style="border-radius: 10px;"src="<%=request.getContextPath() %>/teacher/teacherwrite.do?teacher_id=${teacherVO.teacher_id} "/>
</a>
</div>



<div class="col-7" style="height:60px;" >

				<c:forEach var="memberVO" items="${memlist}">
				<c:if test="${teacherVO.member_id==memberVO.member_id}">

				
				
				<h3><a >${memberVO.mem_nick}</a></h3>

				</c:if>
				</c:forEach>				
				
				<p>
				<c:if test="${teacherVO.appraisal_count!=0}">
				老師評價:
<%-- 				${teacherVO.appraisal_accum/teacherVO.appraisal_count} --%>
				
				<span class="fa fa-star 
				<c:if test="${(teacherVO.appraisal_accum/teacherVO.appraisal_count)>0}">
				checked
				</c:if>
				"></span>
				<span class="fa fa-star 
				<c:if test="${(teacherVO.appraisal_accum/teacherVO.appraisal_count)>1}">
				checked
				</c:if>
				"></span>
				<span class="fa fa-star 
				<c:if test="${(teacherVO.appraisal_accum/teacherVO.appraisal_count)>2}">
				checked
				</c:if>
				"></span>
				<span class="fa fa-star 
				<c:if test="${(teacherVO.appraisal_accum/teacherVO.appraisal_count)>3}">
				checked
				</c:if>
				"></span>
				<span class="fa fa-star 
				<c:if test="${(teacherVO.appraisal_accum/teacherVO.appraisal_count)>4}">
				checked
				</c:if>
				"></span>
				
				</c:if>
				<c:if test="${teacherVO.appraisal_count==0}">
								尚未評價
							</c:if>
				<p>
				
				
				
				<p>課堂完成數:${teacherVO.appraisal_count} </p>
				<p>每小時:${teacherVO.course_price}點數</p>
				
				
				 <div class="box" style="height: 100px;max-width: 600px;
				 overflow: hidden;text-overflow: ellipsis;-webkit-line-clamp:4;
				 display: -webkit-box;-webkit-box-orient: vertical;white-space: normal;">
				 <p >${teacherVO.teacher_introduce}</p>
				 </div>
				 
				 
				</div>
			
			
</div>
</div>
</div>			
			
		</c:forEach>
	
	

<%-- 	<%@ include file="/front-end/teacher/t_file/teacher_page2.file"%> --%>
	 		
	

</div>
<%@ include file="/front-end/file/footer.file"%>
</body>
</html>