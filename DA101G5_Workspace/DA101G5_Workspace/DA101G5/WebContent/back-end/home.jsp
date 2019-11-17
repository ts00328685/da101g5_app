<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="file/head.file" %>
<style>
body{
	width:100%;
	height:100%;
}

.row {
  display: flex;
}

.block{
	padding:20px;
}

#container{
	background-image: url("back-end/images/9.jpg");
	
}

</style>
<meta charset="UTF-8">
<title>管理員功能頁</title>
</head>
<body>
<%@include file="file/header_back.jsp"%>
<div id="container" class="container-fluid" style="padding-top:200px; padding-bottom:400px;">
	<div class="row justify-content-center">
			<div class="block">
				<a href="<%=request.getContextPath()%>/back-end/event/EventIndex.jsp">
					活動管理<br>
					<img src="<%=request.getContextPath()%>/back-end/images/1.jpg" style="width:240px;height:180px;">
				</a>
			</div>
			<div class="block">
				<a href="<%=request.getContextPath()%>/back-end/hashtag/HashtagIndex.jsp">
					hashtag管理<br>
					<img src="<%=request.getContextPath()%>/back-end/images/2.jpg" style="width:240px;height:180px;">
				</a>
			</div>
	
			<div class="block">		
				<a href="<%=request.getContextPath()%>/back-end/qrreport/QRReportIndex.jsp">
					討論區檢舉處理<br>
					<img src="<%=request.getContextPath()%>/back-end/images/3.jpg" style="width:240px;height:180px;">
				</a>
			</div>
		</div>
		<div class="row justify-content-center">	
			<div class="block">
				<a href="<%=request.getContextPath()%>/back-end/recruit/RecrepoArea.jsp">
					派單檢舉處理<br>
					<img src="<%=request.getContextPath()%>/back-end/images/4.png" style="width:240px;height:180px;">
				</a>
			</div>
			<div class="block">
				<a href="<%=request.getContextPath()%>/back-end/teacher/listAllTeacher.jsp">
					教師審核<br>
					<img src="<%=request.getContextPath()%>/back-end/images/5.png" style="width:240px;height:180px">
				</a>
			</div>
			<div class="block">
				
                  <a href="<%=request.getContextPath()%>/course_report/course_report.do?action=orderAll"> 	
                   	課程檢舉<br>
                   	<img src="<%=request.getContextPath()%>/back-end/images/6.png" style="width:240px;height:180px">
      			  </a>		
  				
  			</div>
  	  	</div>	
 </div>
  <%@include file="file/footer.file" %>
</body>
</html>