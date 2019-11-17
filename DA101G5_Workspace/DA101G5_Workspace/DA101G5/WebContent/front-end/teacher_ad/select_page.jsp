<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Teacher_ad: Home</title>

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
   <tr><td><h3>IBM Teacher_ad: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Teacher_ad: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllTeacher_ad.jsp'>List</a> all Teacher_ads.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="teacher_ad.do" >
        <b>��J�Юv�s�i�s�� (�p7001):</b>
        <input type="text" name="teacher_ad_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="teacher_adSvc" scope="page" class="com.Teacher_ad.model.Teacher_adService" />
   
  <li>
     <FORM METHOD="post" ACTION="teacher_ad.do" >
       <b>��ܱЮv�s�i�s��:</b>
       <select size="1" name="teacher_ad_id">
         <c:forEach var="teacher_adVO" items="${teacher_adSvc.all}" > 
          <option value="${teacher_adVO.teacher_ad_id}">${teacher_adVO.teacher_ad_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="teacher_ad.do" >
       <b>�s�i�Ѯv:</b>
       <select size="1" name="teacher_ad_id">
         <c:forEach var="teacher_adVO" items="${teacher_adSvc.all}" > 
          <option value="${teacher_adVO.teacher_ad_id}">${teacher_adVO.teacher_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�Юv�s�i�޲z</h3>

<ul>
  <li><a href='addTeacher_ad.jsp'>Add</a> a new Teacher_ad.</li>
</ul>

</body>
</html>