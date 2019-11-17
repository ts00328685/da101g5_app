<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Teacher.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	TeacherService teacherSvc = new TeacherService();
	List<TeacherVO> list = teacherSvc.getAll();	
	pageContext.setAttribute("list", list);
%>

<%! String a; String b; %>

<html>
<head>
<%@ include file="/back-end/file/head.file"%>

<title>懶骨雞</title>


<style>

</style>

</head>
<body>
<%@include file="/back-end/file/header_back.jsp" %>

<div class="container" style="padding-top:200px;">

<div class="align-items-center alert alert-warning alert-dismissible fade show justify-content-center">
	
		<h3>審核老師資料</h3>
		
		
			
	

</div>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table  class="table table-bordered">
		<tr>
			
			
			<th>老師編號</th>
		    
		    <th>教師狀態</th>
			<th>查看</th>
			
		</tr>
		<%@ include file="/front-end/teacher/t_file/teacher_page1.file"%>
		<c:forEach var="teacherVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				
				<td id="teacher_id${teacherVO.teacher_id}">${teacherVO.teacher_id}</td>
				
				<td>
				
				<select onchange="sendState('${teacherVO.teacher_id}')"id="teacher_state${teacherVO.teacher_id}" size="1" name="teacher_state">
				<jsp:useBean id="teacherVO" scope="page" class="com.Teacher.model.TeacherVO" />
				<jsp:getProperty property="teacher_state" name="teacherVO"/>
<%-- 				<c:forEach var="teacherVO" items="${deptSvc.all}"> --%>
<%-- 				</c:forEach> --%>
				<option value="0"  <%if (teacherVO.getTeacher_state()==0){%> selected <%}%>>未驗證
				<option value="1"  <%if (teacherVO.getTeacher_state()==1){%> selected <%}%> >已驗證
				<option value="2" <%if (teacherVO.getTeacher_state()==2){%> selected <%}%> >已停權
				</select>
				
<%-- 				<input type="submit" class="btn  btn-secondary" onclick="sendState('${teacherVO.teacher_id}')" value="確認修改">  --%>
<%-- 				<input type="hidden"name="teacher_id" value="${teacherVO.teacher_id}"> --%>
<%-- 				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!-- 				<input type="hidden"  name="action" value="updateState"> -->
			
<!-- 			 -->
			
		
				
				</td>
				
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/teacher/teacher.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn  btn-secondary" value="查看"> <input type="hidden"
							name="teacher_id" value="${teacherVO.teacher_id}"> <input
							type="hidden" name="action" value="getOne_For_Display">
					</FORM>
				</td>
				
			</tr>
		</c:forEach>
	</table>
	<%@ include file="/front-end/teacher/t_file/teacher_page2.file"%>
	
	
</div>
<br><br><br>
<%@include file="/back-end/file/footer.file" %>	


<!-- ==========================Ajax============================= -->
<script> 



var xhr = null;


function sendState(temp){ 
  var xhr = new XMLHttpRequest();
  //設定好回呼函數   
  xhr.onload = function (){
      if( xhr.status == 200){
        // document.getElementById("showPanel").innerHTML = xhr.responseText;
        showEmployee(xhr.responseText);
      }else{
        alert( xhr.status );
      }//xhr.status == 200
  };//onload 
  
  //建立好Get連接
 
  var teacher_id = document.getElementById("teacher_id" + temp).innerText;
  var teacher_state = document.getElementById("teacher_state" + temp).value;

  
  var url= "<%=request.getContextPath()%>/teacher/teacher.do?action=updateState&teacher_id= " + teacher_id
  						+"&teacher_state=" + teacher_state;
  xhr.open("GET",url,true); 
  //送出請求 
  xhr.send( null );
  swal("狀態更動成功!");
}
</script>
</body>
</html>