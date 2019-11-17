<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.Teacher.model.*"%>
<%@ page import="com.Teacher_course_list.model.*"%>
<%@ page import="java.util.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	TeacherVO teacherVO = (TeacherVO) request.getAttribute("teacherVO"); //TeacherServlet.java(Concroller), 存入req的teacherVO物件
%>
<%
	request.setAttribute("teacherVO",teacherVO); 
%>
<html>
 <head>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />


    <title>懶骨雞</title>
<%@ include file="/front-end/file/head.file"%>

  </head>
<body>
  <!--================ Start Header Menu Area =================-->
   <%@ include file="/front-end/file/header.jsp"%>
    <!--================ End Header Menu Area =================-->


<div class="container" style="padding-top:200px;">

			<div class="section-top-border">
				<h3 class="mb-30 title_color">認識老師</h3>
				<div class="row">
					<div class="col-md-3">
					<img src="<%=request.getContextPath() %>/teacher/teacherwrite.do?teacher_id=${teacherVO.teacher_id}"class="img-fluid"/>
					<br><br>
						<ul class="unordered-list" >
							<li>課程價格: <%=teacherVO.getCourse_price()%>點數 / 60分鐘</li>
							
<jsp:useBean id="course_listSvc" scope="page" class="com.Teacher_course_list.model.Teacher_course_listService" />							
<jsp:useBean id="languageSvc" scope="page" class="com.Language.model.LanguageService" />
<jsp:useBean id="sort_courseSvc" scope="page" class="com.Sort_course.model.Sort_courseService" />
							<li>課程總評價: 
<%-- 							<c:if test="${teacherVO.appraisal_count!=0}"> --%>
<%-- 							<fmt:formatNumber type="number" value="${teacherVO.appraisal_accum/teacherVO.appraisal_count}" maxFractionDigits="1"/>  --%>
<%-- 							</c:if> --%>
<c:if test="${teacherVO.appraisal_count!=0}"> 
							<span class="fa fa-star checked"
							<c:if test="${(teacherVO.appraisal_accum/teacherVO.appraisal_count)>0}">
							 style="color:gold;"
							</c:if>
							></span>
							
							<span class="fa fa-star checked"
							<c:if test="${(teacherVO.appraisal_accum/teacherVO.appraisal_count)>1}">
							 style="color:gold;"
							</c:if>
							></span>
							
							<span class="fa fa-star checked"
							<c:if test="${(teacherVO.appraisal_accum/teacherVO.appraisal_count)>2}">
							 style="color:gold;"
							</c:if>
							></span>
							
							<span class="fa fa-star checked"
							<c:if test="${(teacherVO.appraisal_accum/teacherVO.appraisal_count)>3}">
							 style="color:gold;"
							</c:if>
							></span>
							
							<span class="fa fa-star checked"
							<c:if test="${(teacherVO.appraisal_accum/teacherVO.appraisal_count)>4}">
							 style="color:gold;"
							</c:if>
							></span>
							<br>
							<b>已完成總堂數: </b>
							<c:out value="${teacherVO.appraisal_count}"></c:out>

</c:if> 
							<c:if test="${ teacherVO.appraisal_count==0}">
							<b>尚未評價</b>	
							</c:if>
							</li>
<!-- =============================							 -->
							<%if(course_listSvc.getAverage(teacherVO.getTeacher_id())!=null){ %>
<%-- 							<%out.print("<li>"); %> --%>
							<%Map<Teacher_course_listVO,Double> map=(Map)course_listSvc.getAverage(teacherVO.getTeacher_id()); %>
							
							<% for (Teacher_course_listVO key : map.keySet()) {
								out.print("<li>");
								out.print(languageSvc.getOneLanguage(key.getLanguage_id()).getLanguage());
								
								out.print(sort_courseSvc.getOneSort_course(key.getSort_course_id()).getSort_course());
								if(!map.get(key).isNaN()){
									
									if(map.get(key)>0){
										out.print("<span class='fa fa-star checked' style='color:gold;'></span >");
									}
									if(map.get(key)>1){
										out.print("<span class='fa fa-star checked'style='color:gold;'></span >");
									}
									if(map.get(key)>2){
										out.print("<span class='fa fa-star checked'style='color:gold;'></span >");
									}
									if(map.get(key)>3){
										out.print("<span class='fa fa-star checked'style='color:gold;'></span >");
									}
									if(map.get(key)>4){
										out.print("<span class='fa fa-star checked'style='color:gold;'></span >");
									}
								out.print("<br>");
								out.print("已完課堂數: "+key.getCourse_appraisal_count());
								out.print("</li>");
								
								
								
								
								}else{
									out.print("<li>");
									out.print("<b>尚未評價</b>");
									out.print("</li>");
								}
								
							}%>
							<%} %>
<!-- =====================================							 -->
						</ul>
						<br><br>
						購買課程: 選擇購買時數<br><br>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course_order/course_order.do" name="form1" enctype="multipart/form-data">						
						 <div>
						 <select class="custom-select"name="buy_time" style="width:100px;">
				            <option value="1" selected >1小時</option>
				            <option value="2">2小時</option>
				            <option value="3">3小時</option>
				            <option value="4">4小時</option>
				            <option value="5">5小時</option>
				            <option value="6">6小時</option>
				            <option value="7">7小時</option>
				            <option value="8">8小時</option>
				            <option value="9">9小時</option>
				            <option value="10">10小時</option>
				        </select>
				        
				        <br><br><br>
				    <input type="hidden" name="course_price
				    " value="<%=teacherVO.getCourse_price()%>">
				    <input type="hidden" name="teacher_id" value="<%=teacherVO.getTeacher_id()%>">
					<input type="hidden" name="action" value="insert">
					
					<button class="btn btn-primary">確認購買</button>
					</div>
				        
</FORM>

					</div>
					<div class="col-md-9 mt-sm-20 left-align-p">
						
					<div class="section-top-border">
						<div class="row">
							<div class="col-lg-12">
								<blockquote class="generic-blockquote">
									<%=teacherVO.getTeacher_introduce()%>
								</blockquote>
							</div>
						</div>
					</div>
					<ul class="unordered-list">
						<li>工作經驗: <%=teacherVO.getWork_experience()%></li><br>
						
						
						<li>教育背景: <%=teacherVO.getEd_background() %>	</li><br>
						
						<li>證書: <%=teacherVO.getCertification()%></li>
					</ul>
					
<!-- 			------------------section-top-border------------- -->
					</div>
				</div>
			</div>
			</div>
<!-- 			=============section-top-border============== -->



<!-- <h4>此頁暫練習採用 Script 的寫法取值:</h4> -->
<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>老師資料 - ListOneTeacher.jsp</h3> -->
<!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<!-- <table class="table table-bordered"> -->
<!-- 	<tr> -->
<!-- 		<td>老師編號</td> -->
<%-- 		<td><%=teacherVO.getTeacher_id()%></td> --%>
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<!-- 	<th>會員編號</th> -->
<%-- 	<td><%=teacherVO.getMember_id()%></td> --%>
<!-- 	</tr> -->
<!-- </table> -->



<!--================ Start footer Area  =================-->
   <%@ include file="/front-end/file/footer.file"%>
<!--================ End footer Area  =================-->



</body>

<script>

</script>
</html>