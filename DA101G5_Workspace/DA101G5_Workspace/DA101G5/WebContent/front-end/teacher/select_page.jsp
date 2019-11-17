<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Teacher.model.*"%>
<html>
<head>
  <title>IBM Teacher: Home</title>
<%@ include file="/front-end/file/head.file"%>

<script>
  $(document).ready(function () {
    $('.selectpicker').selectpicker();
  });
</script>



</head>
<body >
<%@ include file="/front-end/file/header.jsp"%>
  <div class="container justify-content-center" style="padding-top:200px;">

    <table id="table-1">
     <tr><td><h3>IBM Teacher: Home</h3>
   </table>

   <p>This is the Home page for IBM Teacher: Home</p>
   

   <h3>資料查詢:</h3>

   <%-- 錯誤表列 --%>
   <c:if test="${not empty errorMsgs}">
   <font style="color:red">請修正以下錯誤:</font>
   <ul>
     <c:forEach var="message" items="${errorMsgs}">
     <li style="color:red">${message}</li>
   </c:forEach>
 </ul>
</c:if>

<ul>
  <li><a href='listAllTeacher.jsp'>List</a> all Teachers.  <br><br></li>
  
  
<!--   <li> -->
  
<!--     <FORM  METHOD="post" ACTION="teacher.do" > -->
<!--       <div class="form-group"> -->
<!--       <b class="col-4 ">輸入老師編號 (如T00001):</b> -->
<!--       <input class="form-control col-6 mr-auto" type="text" name="teacher_id"> -->
<!--       <input class="form-control" type="hidden" name="action" value="getOne_For_Display"> -->
<!--       <input class="form-control col-2 form-control btn btn-primary" type="submit" value="送出"> -->
<!--       </div> -->
<!--     </FORM> -->
  
<!--   </li> -->

  <jsp:useBean id="teacherSvc" scope="page" class="com.Teacher.model.TeacherService" />

  <li>
   <FORM METHOD="post" ACTION="teacher.do" >
     <div class="form-group">
     <b class="col-4 " >選擇老師編號:</b>
     <select class="browser-default custom-select  col-6 " size="1" name="teacher_id">
       <c:forEach var="teacherVO" items="${teacherSvc.all}" > 
       <option value="${teacherVO.teacher_id}">${teacherVO.teacher_id}
       </c:forEach>   
     </select>
     <input class="form-control" type="hidden" name="action" value="getOne_For_Display">
     <input type="submit" class="form-control col-2 btn  btn-primary" value="送出">
     </div>
   </FORM>
 </li>
 
  
     <div class="form-group">  
     <a href="selectedAllTeacher.jsp">selectedAllTeacher.jsp</a>  
     
     </div>
  

 <li>
   <FORM METHOD="post" ACTION="teacher.do" >
    <div class="form-group">
     <b class="col-4" >選擇老師:</b>
     <select class="browser-default custom-select  col-6 "  size="1" name="teacher_id">
       <c:forEach var="teacherVO" items="${teacherSvc.all}" > 
       <option value="${teacherVO.teacher_id}">${teacherVO.teacher_id}
       </c:forEach>   
     </select>
     <input class="form-control" type="hidden" name="action" value="getOne_For_Display">
     <input class="form-control col-2 btn btn-primary" type="submit" value="送出">
   </div>
   </FORM>
 </li>
</ul>
  

<h3>老師管理</h3>

<ul>
  <li><a href='addTeacher.jsp'>Add</a> a new Teacher.</li>
</ul>


</div>
<br><br><br><br>
<%@ include file="/front-end/file/footer.file"%>
</body>
</html>