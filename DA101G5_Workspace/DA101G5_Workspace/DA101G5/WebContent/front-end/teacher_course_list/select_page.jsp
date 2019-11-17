<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Teacher_course_list: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Teacher_course_list: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Teacher_course_list: Home</p>

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
  <li><a href='listAllTeacher_course_list.jsp'>List</a> all Teacher_course_lists.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="teacher_course_list.do" >
        <b>輸入員工編號 (如7001):</b>
        <input type="text" name="teacher_course_list_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="teacher_course_listSvc" scope="page" class="com.Teacher_course_list.model.Teacher_course_listService" />
   
  <li>
     <FORM METHOD="post" ACTION="teacher_course_list.do" >
       <b>選擇員工編號:</b>
       <select size="1" name="teacher_course_list_id">
         <c:forEach var="teacher_course_listVO" items="${teacher_course_listSvc.all}" > 
          <option value="${teacher_course_listVO.teacher_course_list_id}">${teacher_course_listVO.teacher_course_list_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="teacher_course_list.do" >
       <b>選擇員工姓名:</b>
       <select size="1" name="teacher_course_list_id">
         <c:forEach var="teacher_course_listVO" items="${teacher_course_listSvc.all}" > 
          <option value="${teacher_course_listVO.teacher_course_list_id}">${teacher_course_listVO.teacher_course_list_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addTeacher_course_list.jsp'>Add</a> a new Teacher_course_list.</li>
</ul>

</body>
</html>