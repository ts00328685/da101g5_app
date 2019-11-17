<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Teacher.model.*"%>

<%
	TeacherVO teacherVO = (TeacherVO) request.getAttribute("teacherVO");
%>



<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>教師資料新增 - addTeacher.jsp</title>
<%@ include file="/front-end/file/head.file"%>

</head>
<body >
<%@ include file="/front-end/file/header.jsp"%>

	<div class="container"style="padding-top:200px;">

		

		<h3>老師資料新增:</h3>
		<br>
<button onclick="magic()"id="magic"style="color:white;"type="button" class="btn btn-light">神奇小按鈕</button>
		<%-- 錯誤表列 --%>
<%-- 		<c:if test="${not empty errorMsgs}"> --%>
<!-- 			<font style="color: red">請修正以下錯誤:</font> -->
<!-- 			<ul> -->
<%-- 				<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 					<li style="color: red">${message}</li> --%>
<%-- 				</c:forEach> --%>
<!-- 			</ul> -->
<%-- 		</c:if> --%>

		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/teacher/teacher.do" name="form1" enctype="multipart/form-data">
			<div class="form-group">
			
					<p>老師介紹:</p>
			<div class="mt-10 input-group" >
				<textarea id="introduce" style="height:300px; font-size:24px"class="single-textarea" placeholder="老師介紹" onfocus="this.placeholder = ''" onblur="this.placeholder = '老師介紹'"
				 required name="teacher_introduce" >${teacherVO.teacher_introduce }</textarea>
			</div>
			<div class="mt-10 input-group">
		
			    <p >工作經驗</p>
			  
				<input id="workExperience"style="font-size:24px" type="text"  placeholder="工作經驗" onfocus="this.placeholder = ''" onblur="this.placeholder = '工作經驗'"
				 required class="single-input-primary" name="work_experience" value="${teacherVO.work_experience }" >
			</div>
			<br><p >教育背景</p>
			<div class="mt-10 input-group">
				<input id="background"style="font-size:24px" type="text"  placeholder="教育背景" onfocus="this.placeholder = ''" onblur="this.placeholder = '教育背景'"
				 required class="single-input-primary" name="ed_background" value="${teacherVO.ed_background }">
			</div>
			<br><p >證書</p>
			<div class="mt-10 input-group">
				<input id="certificate"style="font-size:24px" type="text"  placeholder="證書" onfocus="this.placeholder = ''" onblur="this.placeholder = '證書'"
				 required class="single-input-primary" name="certification"  value="${teacherVO.certification }">
			</div>
			<br><p >課程價格</p>
			<div class="mt-10 input-group">
				<input id="coursePrice"style="font-size:24px" min="100" type="number"  placeholder="課程價格" onfocus="this.placeholder = ''" onblur="this.placeholder = '課程價格'"
				 required class="single-input-primary"name="course_price" value="${teacherVO.course_price }">
			</div>
			<div class="row col-12 justify-content-between"  >
<!-- 				<div><br><p>老師圖片:</p> -->
<%-- 					<img height ="300px"src="<%=request.getContextPath() %>/front-end/TeacherImageShow.do?teacher_id=${teacherVO.teacher_id} "/> --%>
<!-- 				</div> -->
				<div  >
				    <br><p>
				        圖片預覽：<input type="file" id="xdaTanFileImg" onchange="xmTanUploadImg(this)" accept="image/*"
				        name="introduce_pic" />
				    </p>
				     <img height ="300px" id="xmTanImg"/>
				    <div id="xmTanDiv"></div>
				</div>
			</div>
<br><br>
<!-- 				<table> -->
<!-- 					<tr> -->
<!-- 						<td>會員編號:</td> -->
<!-- 						<td><input class="form-control" type="TEXT" name="member_id" size="45" -->
<%-- 							value="<%=(teacherVO == null) ? "M00011" : teacherVO.getMember_id()%>" /></td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>工作經驗:</td> -->
<!-- 						<td><input class="form-control" type="TEXT" name="work_experience" size="45" -->
<%-- 							value="<%=(teacherVO == null) ? "Tlll" : teacherVO.getWork_experience()%>" /></td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>教育背景:</td> -->
<!-- 						<td><input class="form-control" name="ed_background" id="" type="text" -->
<%-- 						value="<%= (teacherVO == null) ? "Tlll" : teacherVO.getEd_background()%>" /></td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>證書:</td> -->
<!-- 						<td><input class="form-control" type="TEXT" name="certification" size="45" -->
<%-- 							value="<%=(teacherVO == null) ? "lll" : teacherVO.getCertification()%>" /></td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>自我介紹:</td> -->
<!-- 						<td><input class="form-control" type="TEXT" name="teacher_introduce" size="45" -->
<%-- 							value="<%=(teacherVO == null) ? "鳳梨" : teacherVO.getTeacher_introduce()%>" /></td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>老師狀態:</td> -->
<!-- 						<td><input class="form-control" type="TEXT" name="teacher_state" size="45" -->
<%-- 							value="<%=(teacherVO == null) ? "0" : teacherVO.getTeacher_state()%>" /></td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>評價累積分數:</td> -->
<!-- 						<td><input class="form-control" type="TEXT" name="appraisal_accum" size="45" -->
<%-- 							value="<%=(teacherVO == null) ? "1" : teacherVO.getAppraisal_accum()%>" /></td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>評價總次數:</td> -->
<!-- 						<td><input class="form-control" type="TEXT" name="appraisal_count" size="45" -->
<%-- 							value="<%=(teacherVO == null) ? "1" : teacherVO.getAppraisal_count()%>" /></td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>課程價格:</td> -->
<!-- 						<td><input class="form-control" type="TEXT" name="course_price" size="45" -->
<%-- 							value="<%=(teacherVO == null) ? "200" : teacherVO.getCourse_price()%>" /></td> --%>
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>老師照片:</td> -->
<!-- 						<td> -->
<!-- 							<div  width="400px"hight="400px"> -->
<!-- 							    <p> -->
<!-- 							        圖片預覽：<input type="file" id="xdaTanFileImg" onchange="xmTanUploadImg(this)" accept="image/*" -->
<%-- 							        name="introduce_pic" value="<%=(teacherVO == null) ? "" : teacherVO.getIntroduce_pic()%>"/> --%>
<!-- 							    </p> -->
<!-- 							     <img width="400px" id="xmTanImg"/> -->
<!-- 							    <div id="xmTanDiv"></div> -->
<!-- 							</div> -->
							
<!-- 						</td> -->
<!-- <!-- 						+++++++++ --> 
<!-- 					</tr> -->

					
<!-- 				</table> -->
				<br> 
				<input type="hidden" name="action" value="insert">
				<input type="submit"  class="form-control col-2 btn btn-primary" value="送出新增">
			</div>
		</FORM>

	</div>

<!-- -------------------------------------------- -->

<script type="text/javascript">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">				

swal( <c:forEach var="message" items="${errorMsgs}"> 
	"${message}"
</c:forEach> 

);
</c:if>	

</script>


<!-- -----------------------------圖片預覽------------------ -->
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
<!-- ======神奇小按鈕===================================== -->
<script>
function magic(){
	var q="大家好，我是老師機，有兼職要發車總還有乘客想搭的公車司機，身為小吳AKA大衛海鮮的胞弟除了基本的日常英語，對於車上的專業用語也是相當深入。";
	var qq="歡迎大家購課體驗，讓我帶你快速導覽美麗的西方世界。"
	var w="資深老師機  2007～至今";
	var e="中壢資策會";
	var r="中壢資策會JAVA結訓證書";
	document.getElementById("introduce").value="  "+q+"\r\r"+"  "+qq;
	document.getElementById("workExperience").value=w;
	document.getElementById("background").value=e;
	document.getElementById("certificate").value=r;
	document.getElementById("coursePrice").value='100';
	
}
</script>
<!-- ======神奇小按鈕======================================= -->



	
	
	
<%-- ---------------------------------- --%>
	
	<script type="text/javascript">
		function setText(ob,name){  
	    document.getElementById(name).value=ob.value;;  
		}  
	
	
	</script>
<%@ include file="/front-end/file/footer.file"%>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->




</html>