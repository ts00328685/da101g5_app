<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Teacher.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	TeacherService teacherSvc = new TeacherService();
	List<TeacherVO> list = teacherSvc.getAll();	
	pageContext.setAttribute("list", list);
%>

<%! String a; String b; %>

<html>
<head>
<%@ include file="/back-end/file/head.file"%>

<title>�i����</title>


<style>

</style>

</head>
<body>
<%@include file="/back-end/file/header_back.jsp" %>

<div class="container" style="padding-top:200px;">

<div class="align-items-center alert alert-warning alert-dismissible fade show justify-content-center">
	
		<h3>�f�֦Ѯv���</h3>
		
		
			
	

</div>
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table  class="table table-bordered">
		<tr>
			
			
			<th>�Ѯv�s��</th>
		    
		    <th>�Юv���A</th>
			<th>�d��</th>
			
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
				<option value="0"  <%if (teacherVO.getTeacher_state()==0){%> selected <%}%>>������
				<option value="1"  <%if (teacherVO.getTeacher_state()==1){%> selected <%}%> >�w����
				<option value="2" <%if (teacherVO.getTeacher_state()==2){%> selected <%}%> >�w���v
				</select>
				
<%-- 				<input type="submit" class="btn  btn-secondary" onclick="sendState('${teacherVO.teacher_id}')" value="�T�{�ק�">  --%>
<%-- 				<input type="hidden"name="teacher_id" value="${teacherVO.teacher_id}"> --%>
<%-- 				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> --%>
<!-- 				<input type="hidden"  name="action" value="updateState"> -->
			
<!-- 			 -->
			
		
				
				</td>
				
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/teacher/teacher.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn  btn-secondary" value="�d��"> <input type="hidden"
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
  //�]�w�n�^�I���   
  xhr.onload = function (){
      if( xhr.status == 200){
        // document.getElementById("showPanel").innerHTML = xhr.responseText;
        showEmployee(xhr.responseText);
      }else{
        alert( xhr.status );
      }//xhr.status == 200
  };//onload 
  
  //�إߦnGet�s��
 
  var teacher_id = document.getElementById("teacher_id" + temp).innerText;
  var teacher_state = document.getElementById("teacher_state" + temp).value;

  
  var url= "<%=request.getContextPath()%>/teacher/teacher.do?action=updateState&teacher_id= " + teacher_id
  						+"&teacher_state=" + teacher_state;
  xhr.open("GET",url,true); 
  //�e�X�ШD 
  xhr.send( null );
  swal("���A��ʦ��\!");
}
</script>
</body>
</html>