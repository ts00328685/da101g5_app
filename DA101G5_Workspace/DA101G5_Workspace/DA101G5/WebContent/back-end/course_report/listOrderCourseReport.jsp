<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Teacher.model.*"%>
<%@ page import="com.Course_report.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
Course_reportService course_reportSvc=new Course_reportService();
	List<Course_reportVO> list = course_reportSvc.orderAll();	
	pageContext.setAttribute("list", list);
%>



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
	
<%-- 		<h4><a href="<%=request.getContextPath()%>/back-end/home.jsp">��x����</a></h4> --%>
		<h3>�ҵ{���|�B�z</h3>
		
			
	

</div>
	<%-- ���~��C --%>
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 		<font style="color: red">�Эץ��H�U���~:</font> -->
<!-- 		<ul> -->
<%-- 			<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 				<li style="color: red">${message}</li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<%-- 	</c:if> --%>

	<table  class="table table-bordered">
		<tr>
			
			<th>���|�s��</th>
			<th>���|���e</th>
		    <th>���|���A</th>
		    <th>�Юv���A</th>
			
		</tr>
<%-- <%@ include file="/front-end/teacher/t_file/teacher_page1.file"%>		 --%>
		<c:forEach var="myMap" items="${courseReportWithTeacher}" >


			<tr>
				<td>${myMap.key.course_report_id } </td>
				<td >
				
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#text${myMap.key.course_report_id}">�d�����|�Ա�</button>
<!-- 	======================== -->
<div style="padding-top:200px;height:400px;" id="text${myMap.key.course_report_id}"class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content" style="font-size:40px;padding-left:20px;">
      ${myMap.key.report_text }
    </div>
  </div>
</div>

				
				</td>
				
				<td>				
				<select id="report_state${myMap.key.course_report_id}"onchange="updateState('${myMap.key.course_report_id}')" size="1" name="report_state">
				<option value="0"   
				<c:if test="${myMap.key.report_state==2}">
				selected
				</c:if>
				 >���B�z
				<option value="1"   
				<c:if test="${myMap.key.report_state==1}">
				selected
				</c:if>
				>�B�z��
				<option value="2"   
				<c:if test="${myMap.key.report_state==0}">
				selected
				</c:if>
				 >�w�B�z
				</select>
<input type="hidden" id="course_report_id${myMap.key.course_report_id}" value="${myMap.key.course_report_id}">				
				
				</td>
				
				<td>
				
				<select onchange="sendState('${myMap.value.teacher_id}')" id="teacher_state${myMap.value.teacher_id}" size="1" name="teacher_state">
				<option value="0"   
				<c:if test="${myMap.value.teacher_state==0}">
				selected
				</c:if>
				 >������
				<option value="1"   
				<c:if test="${myMap.value.teacher_state==1}">
				selected
				</c:if>
				>�w����
				<option value="2"   
				<c:if test="${myMap.value.teacher_state==2}">
				selected
				</c:if>
				 >�w���v
				</select>
<input type="hidden" id="teacher_id${myMap.value.teacher_id}" value="${myMap.value.teacher_id}">

				</td>
				
				
			</tr>

		</c:forEach>
	</table>
<%-- <%@ include file="/front-end/teacher/t_file/teacher_page2.file"%> --%>
	
	
	
</div>
<br><br><br><br>
<%@include file="/back-end/file/footer.file" %>


<!-- ==========================Ajax============================= -->
<script> 
// ===�Ѯv���A===============
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
 
  var teacher_id = document.getElementById("teacher_id" + temp).value;
  var teacher_state = document.getElementById("teacher_state" + temp).value;

  
  var url= "<%=request.getContextPath()%>/teacher/teacher.do?action=updateState&teacher_id= " + teacher_id
  						+"&teacher_state=" + teacher_state;
  xhr.open("GET",url,true); 
  //�e�X�ШD 
  xhr.send( null );
  
  swal('���A��ʧ���');
}
</script>
<!-- =�q����===================== -->
<script> 

var xhr2 = null;

function updateState(temp){ 
  var xhr2 = new XMLHttpRequest();
  //�]�w�n�^�I���   
  xhr2.onload = function (){
      if( xhr2.status == 200){
        // document.getElementById("showPanel").innerHTML = xhr.responseText;
        showEmployee(xhr.responseText);
      }else{
        alert( xhr2.status );
      }//xhr.status == 200
  };//onload 
  
  //�إߦnGet�s��
 
  var course_report_id = document.getElementById("course_report_id" + temp).value;
  var report_state = document.getElementById("report_state" + temp).value;
  
  
  var url2= "<%=request.getContextPath()%>/course_report/course_report.do?action=course_reportUpdate&course_report_id=" + course_report_id
  						+"&report_state=" + report_state;
  xhr2.open("GET",url2,true); 
  //�e�X�ШD 
  xhr2.send( null );
  swal('���A��ʧ���');

}

</script>
</body>
</html>