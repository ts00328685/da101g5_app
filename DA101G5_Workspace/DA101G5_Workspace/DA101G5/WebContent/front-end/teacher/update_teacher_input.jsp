<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Teacher.model.*"%>
<%@ page import="com.Teacher_ad.model.*"%>

<%
	TeacherVO teacherVO = (TeacherVO) session.getAttribute("teacherVO"); //EmpServlet.java (Concroller) 存入req的teacherVO物件 (包括幫忙取出的teacherVO, 也包括輸入資料錯誤時的teacherVO物件)
%>

<html>
<head>
<title>懶骨雞</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/front-end/file/head.file"%>
<style>
	body {
		font-family: 微軟正黑體;
		}

</style>

</head>
<body>
<!--================ Start Header Menu Area =================-->
   <%@ include file="/front-end/file/header.jsp"%>
    <!--================ End Header Menu Area =================-->
<!-- ================================================================== -->
<div class="container" style="padding-top:200px;">

		<h1 class="row">老師資料
		
			 <FORM class="ml-4"METHOD="post" ACTION="<%=request.getContextPath()%>/teacher_ad/teacher_ad.do" style="margin-bottom: 0px;">
				     <input class="genric-btn info circle arrow"type="submit" value="購買廣告">
				     <% Teacher_adService teacherAdSvc=new Teacher_adService(); %>
				     <%Teacher_adVO teacher_adVO=teacherAdSvc.getOneTeacherAdUseTeacherId(teacherVO.getTeacher_id()); %>
				     <input type="hidden" name="teacher_ad_id"  value="<teacher_adVO.getTeacher_ad_id>">
				     <input type="hidden" name="action"	value="getOne_For_Update">
			</FORM>
		<a class="ml-4"href='<%=request.getContextPath()%>/front-end/teacher_course_list/addTeacher_course_list.jsp'>
		<button class="genric-btn info circle ">調整課程</button>
		</a>
		</h1>
		
		<br><br>
		
		
	
		<%-- 錯誤表列 --%>
<%-- 		<c:if test="${not empty errorMsgs}"> --%>
<!-- 			<font style="color: red">請修正以下錯誤:</font> -->
<!-- 			<ul> -->
<%-- 				<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 					<li style="color: red">${message}</li> --%>
<%-- 				</c:forEach> --%>
<!-- 			</ul> -->
<%-- 		</c:if> --%>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/teacher/teacher.do" name="form1"  enctype="multipart/form-data">
		<p>老師狀態:</p>
			<div class="mt-10 input-group" style="font-size:24px;" >
				<p value="${teacherVO.teacher_state}">
				<c:if test="${teacherVO.teacher_state==0}">
				<b>尚未驗證</b>
				</c:if>
				<c:if test="${teacherVO.teacher_state==1}">
				<b>已經驗證</b>
				</c:if>
				<c:if test="${teacherVO.teacher_state==2}">
				<b>停權中</b>
				</c:if>
				
				</p>
			</div>
			
		<p>老師介紹:</p>
			<div class="mt-10 input-group" >
				<textarea  style="height:300px; font-size:24px"class="single-textarea" placeholder="老師介紹" onfocus="this.placeholder = ''" onblur="this.placeholder = '老師介紹'"
				 required name="teacher_introduce" value="<%=teacherVO.getTeacher_introduce() %>"><%=teacherVO.getTeacher_introduce() %></textarea>
			</div>
			<div class="mt-10 input-group">
		
			    <p >工作經驗</p>
			  
				<input style="font-size:24px" type="text"  placeholder="工作經驗" onfocus="this.placeholder = ''" onblur="this.placeholder = '工作經驗'"
				 required class="single-input-primary" name="work_experience" value="<%=teacherVO.getWork_experience()%>">
			</div>
			<br><p >教育背景</p>
			<div class="mt-10 input-group">
				<input style="font-size:24px" type="text"  placeholder="教育背景" onfocus="this.placeholder = ''" onblur="this.placeholder = '教育背景'"
				 required class="single-input-primary" name="ed_background" value="<%=teacherVO.getEd_background()%>">
			</div>
			<br><p >證書</p>
			<div class="mt-10 input-group">
				<input style="font-size:24px" type="text"  placeholder="證書" onfocus="this.placeholder = ''" onblur="this.placeholder = '證書'"
				 required class="single-input-primary" name="certification" value="<%=teacherVO.getCertification()%>" >
			</div>
			<br><p >課程價格</p>
			<div class="mt-10 input-group">
				<input style="font-size:24px" min="100" type="number"  placeholder="課程價格" onfocus="this.placeholder = ''" onblur="this.placeholder = '課程價格'"
				 required class="single-input-primary"name="course_price" value="<%=teacherVO.getCourse_price()%>">
			</div>
			<div class="row col-12 justify-content-between"  >
				<div><br><p>老師圖片:</p>
					<img height ="300px"src="<%=request.getContextPath() %>/teacher/teacherwrite.do?teacher_id=${teacherVO.teacher_id}"/>
				</div>
				<div  >
				    <br><p>
				        圖片預覽：<input type="file" id="xdaTanFileImg" onchange="xmTanUploadImg(this)" accept="image/*"
				        name="introduce_pic" value="<%=(teacherVO == null) ? "" : teacherVO.getIntroduce_pic()%>"/>
				    </p>
				     <img height ="300px" id="xmTanImg"/>
				    <div id="xmTanDiv"></div>
				</div>
			</div>
<br><br>	

			<center>
			<input 	type="hidden" name="action" value="update">
			<input	type="hidden" name="teacher_id" value="<%=teacherVO.getTeacher_id()%>"/>
			<input	type="hidden" name="member_id" value="<%=teacherVO.getMember_id()%>"/>
		 	<input	type="hidden" name="appraisal_accum" value="<%=teacherVO.getAppraisal_accum()%>"/>
		 	<input	type="hidden" name="appraisal_count" value="<%=teacherVO.getAppraisal_count()%>"/>
		 	<input	type="hidden" name="teacher_state" value="<%=teacherVO.getTeacher_state()%>"/>
			<input position="center" type="submit"  class=" form-control  col-2 btn btn-primary" value="送出修改">
			</center>
		</Form>
	</div>
<%-- 	<%@ include file="/front-end/teacher_ad/update_Teacher_ad_input.jsp"%> --%>
			
<br><br><br>
<!-- ========================================================================================== -->

<!--   <div class="container justify-content-center"> -->
  
<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<h3>教師資料修改 - update_teacher_input.jsp</h3> -->
<!-- 				<h4> -->
<!-- 					<a href="select_page.jsp"> -->
<!-- 					<img src="images/back1.gif" -->
<!-- 						width="100" height="32" border="0">回首頁</a> -->
<!-- 				</h4> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

<!-- 	<h3>資料修改:</h3> -->

<%-- 	<%-- 錯誤表列 --%> 
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 		<font style="color: red">請修正以下錯誤:</font> -->
<!-- 		<ul> -->
<%-- 			<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 				<li style="color: red">${message}</li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<%-- 	</c:if> --%>

<!-- 	<FORM METHOD="post" ACTION="teacher.do" name="form1"  enctype="multipart/form-data"> -->
<!-- 	<div class="form-group"> -->
<!-- 		<table> -->
<!-- 			<tr> -->
<!-- 				<td>老師編號:<font color=red><b>*</b></font></td> -->
<%-- 				<td><%=teacherVO.getTeacher_id()%></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>會員編號:</td> -->
<!-- 				<td><input  class="form-control" type="TEXT" name="member_id" size="45" -->
<%-- 					value="<%=teacherVO.getMember_id()%>" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>工作經驗:</td> -->
<!-- 				<td><input  class="form-control" type="TEXT" name="work_experience" size="45" -->
<%-- 					value="<%=teacherVO.getWork_experience()%>" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>教育背景:</td> -->
<!-- 				<td><input  class="form-control" name="ed_background" id="" type="text" -->
<%-- 					value="<%=teacherVO.getEd_background()   %>" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>證書:</td> -->
<!-- 				<td><input  class="form-control" type="TEXT" name="certification" size="45" -->
<%-- 					value="<%=teacherVO.getCertification()%>" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>教師介紹:</td> -->
<!-- 				<td> -->
<!-- 				<input class="form-control"  type="TEXT" name="teacher_introduce" size="45" -->
<%-- 					value="<%=teacherVO.getTeacher_introduce() %>" /> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 	<tr> -->
<!-- 		<td>教師狀態:<font color=red></font></td> -->
<!-- 		<td> -->
<!-- 		<select size="1" name="teacher_state"> -->
<%-- 				<jsp:useBean id="teacherVO3" scope="page" class="com.Teacher.model.TeacherVO" /> --%>
<%-- 				<jsp:getProperty property="teacher_state" name="teacherVO3"/> --%>
<%-- 				<option value="0"  <%if (teacherVO.getTeacher_state()==0){%> selected <%}%>>未驗證 --%>
<%-- 				<option value="1"  <%if (teacherVO.getTeacher_state()==1){%> selected <%}%> >已驗證 --%>
<%-- 				<option value="2" <%if (teacherVO.getTeacher_state()==2){%> selected <%}%> >已停權 --%>
				
			
			
<!-- 		</select></td> -->
<!-- 	</tr> -->
			
<!-- 			<tr> -->
<!-- 				<td>教師累積評價:</td> -->
<!-- 				<td> -->
<!-- 				<input class="form-control"  type="TEXT" name="appraisal_accum" size="45" -->
<%-- 					value="<%=teacherVO.getAppraisal_accum()%>" /> --%>
<!-- 			    </td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>教師評價次數:</td> -->
<!-- 				<td> -->
<!-- 				<input class="form-control"  type="TEXT" name="appraisal_count" size="45" -->
<%-- 					value="<%=teacherVO.getAppraisal_count()%>" /> --%>
<!-- 			    </td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>課程價格:</td> -->
<!-- 				<td> -->
<!-- 				<input class="form-control"  type="TEXT" name="course_price" size="45" -->
<%-- 					value="<%=teacherVO.getCourse_price()%>" /> --%>
<!-- 			    </td> -->
<!-- 			</tr> -->
			
<%-- 			<img src="<%=request.getContextPath() %>/TeacherImageShow.do?teacher_id=${teacherVO.teacher_id} "/> --%>
			
<!-- 			<tr> -->
<!-- 				<td>介紹照片:</td> -->

<!-- 				<td> -->
<!-- 				<input class="form-control"  type="file" name="introduce_pic" size="45"				 -->
<%-- 					value="<%=teacherVO.getIntroduce_pic() %>" />		 --%>
					
								
<!-- 				</td> -->
<!-- 			</tr> -->

<!-- 		</table> -->
<!-- 		<br> -->
<!-- 		    <input type="hidden" name="action" value="update"> -->
<%-- 		 <input	type="hidden" name="teacher_id" value="<%=teacherVO.getTeacher_id()%>"> --%>
<!-- 		 <input type="submit"  class="form-control col-2 btn btn-primary" value="送出修改"> -->
<!-- 		</div> -->
<!-- 	</FORM> -->
	
	
<!-- 	</div> -->
	
	
	
	
<!-- =================================================================================================================== -->
<!--================ Start footer Area  =================-->
   <%@ include file="/front-end/file/footer.file"%>
    <!--================ End footer Area  =================-->
    
<script type="text/javascript">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">				

swal( <c:forEach var="message" items="${errorMsgs}"> 
	"${message}"
</c:forEach> 

);
</c:if>	

</script> 
    

<!-- -*-------------引入頁首頁尾------------------- -->
  		<script type="text/javascript">            
            //判断浏览器是否支持FileReader接口
            if (typeof FileReader == 'undefined') {
                document.getElementById("xmTanDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
                //使选择控件不可操作
                document.getElementById("xdaTanFileImg").setAttribute("disabled", "disabled");
            }

            //选择图片，马上预览
            function xmTanUploadImg(obj) {
                var file = obj.files[0];
                
                console.log(obj);console.log(file);
                console.log("file.size = " + file.size);  //file.size 单位为byte

                var reader = new FileReader();

                //读取文件过程方法
                reader.onloadstart = function (e) {
                    console.log("開始讀取....");
                }
                reader.onprogress = function (e) {
                    console.log("正在讀取中....");
                }
                reader.onabort = function (e) {
                    console.log("中斷讀取....");
                }
                reader.onerror = function (e) {
                    console.log("讀取異常....");
                }
                reader.onload = function (e) {
                    console.log("成功讀取....");

                    var img = document.getElementById("xmTanImg");
                    img.src = e.target.result;
                    //或者 img.src = this.result;  //e.target == this
                }

                reader.readAsDataURL(file)
            }
        </script>

        
	
	
</body>
</html>