<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Teacher_course_list.model.*"%>
<%@ page import="com.Language.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.Sort_course.model.*"%>


<%
  MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
%>
<%
    Teacher_course_listService teacher_course_listSvc = new Teacher_course_listService();
    List<Teacher_course_listVO> list = teacher_course_listSvc.getAll();    
    pageContext.setAttribute("list",list);
%>



<html>
<head>
<%@ include file="/front-end/file/head.file"%>
<script src="<%=request.getContextPath()%>/file/course.js"></script>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�i����</title>



</head>
<body style=" font-family:�з���;">
<%@ include file="/front-end/file/header.jsp"%>
<div class="container-fluid row justify-content-center" style="padding-top:200px;">



<jsp:useBean id="languageSvc" scope="page" class="com.Language.model.LanguageService" />
<jsp:useBean id="sort_courseSvc" scope="page" class="com.Sort_course.model.Sort_courseService"/>
<%-- ���~��C --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">�Эץ��H�U���~:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<div class="col-4">
<a href="<%=request.getContextPath()%>/front-end/teacher/update_teacher_input.jsp"><h2 style=" font-family:�з���;">�^��Ѯv�ק���</h2></a>
<br><br>
<FORM METHOD="post" ACTION="teacher_course_list.do" name="form1" enctype="multipart/form-data">
	<font style="color:green; font-size:30px;">�ثe�}��ҵ{:</font><br><br><br>
	
		<c:forEach var="course_list" items="${list}">
			<c:if test="${teacherVO.teacher_id==course_list.teacher_id}">
			<table style="font-size:24px;">
			<tr style="color:black;width:250px;"class="row justify-content-between">
				<td>
				<c:forEach var="languageVO" items="${languageSvc.all}" > 
	          		<c:if test="${languageVO.language_id==course_list.language_id}">  ${languageVO.language}</c:if>
				</c:forEach>
				
				<c:forEach var="sort_courseVO" items="${sort_courseSvc.all}" > 
	          		<c:if test="${sort_courseVO.sort_course_id==course_list.sort_course_id}">  ${sort_courseVO.sort_course}</c:if>
				</c:forEach>  
				</td>
				<td>
			 
			     <button class="btn btn-outline-danger"id="button${course_list.teacher_course_list_id}" type="button" onclick="sendState('${course_list.teacher_course_list_id}')">
			  <c:if test="${course_list.course_state==1}">  
			    ����
			  </c:if>
			  <c:if test="${course_list.course_state==0}">  
			    �}��
			  </c:if>
			  </button>
			     <input class="btn btn-success"id="teacher_course_list_id${course_list.teacher_course_list_id}" type="hidden" name="teacher_course_list_id"  value="${course_list.teacher_course_list_id}">

				</td>
			</tr>
			</table>
			</c:if>
		</c:forEach>
	
</FORM>	
	</div>


<div style="padding-top:100px" class="col-3">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/teacher_course_list/teacher_course_list.do" name="form1" enctype="multipart/form-data">

	 
	<font style="color:green; font-size:30px;">�s�W�}��ҵ{:</font><br>
	<br>
	<div style="font-size:24px;">
       <p>��ܻy��:</p>
       <select class="custom-select my-1 mr-sm-2"id="sfs" size="1" name="language_id" style="width:100px;">
         <c:forEach var="languageVO" items="${languageSvc.all}" > 
          <option value="${languageVO.language_id}">${languageVO.language}
         </c:forEach>   
       </select>
    </div>  
    
     <br><br><br>
   <div style="font-size:24px;">
       <p>��ܽҵ{:</p>
       <select class="custom-select my-1 mr-sm-2" size="1" name="sort_course_id" style="width:100px;">
         <c:forEach var="sort_courseVO" items="${sort_courseSvc.all}" > 
         
          <option value="${sort_courseVO.sort_course_id}">${sort_courseVO.sort_course}
         
         </c:forEach>   
       </select>
    </div>   
    
	

	

<br><br><br>

<input type="hidden" name="action" value="insert">
<input class="btn btn-primary"type="submit" value="�e�X�s�W">
</FORM>
</div>





</div>
<br><br><br><br><br><br><br><br><br>
<%@ include file="/front-end/file/footer.file"%>
<!-- ----------------------------------------- -->
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
 var a='�}��';
  var b='����';
  var teacher_course_list_id = document.getElementById("teacher_course_list_id" + temp).value;
  
  var bottonValue=document.getElementById("button" + temp).innerHTML.trim();
  
  if(bottonValue==b){
	  document.getElementById("button" + temp).innerHTML=a;
	  swal("�ҵ{�w�g����");
  }else if(bottonValue==a){
	  document.getElementById("button" + temp).innerHTML=b;
	  swal("�ҵ{�w�g�}��");
  }

  
  var url= "<%=request.getContextPath()%>/teacher_course_list/teacher_course_list.do?action=update&teacher_course_list_id= " + teacher_course_list_id
		 + "&bottonValue="+bottonValue;
  						
  xhr.open("GET",url,true); 
  //�e�X�ШD 
  xhr.send( null );
  
  
}
</script>




<script type="text/javascript">
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">				

swal( <c:forEach var="message" items="${errorMsgs}"> 
	"${message}"
</c:forEach> 

);
</c:if>	

</script>

</body>
</html>