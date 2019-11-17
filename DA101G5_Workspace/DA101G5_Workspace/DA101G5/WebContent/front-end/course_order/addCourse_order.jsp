<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Course_order.model.*"%>

<%
  Course_orderVO course_orderVO = (Course_orderVO) request.getAttribute("course_orderVO");
%>

<%=  course_orderVO==null  %>>  -- ${course_orderVO==null}


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>課程訂單資料新增 - addCourse_order.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
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

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>課程訂單資料新增 - addCourse_order.jsp</h3></td><td>
		 <h4><a href="select_page.jsp">
		 <img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="course_order.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
	
		<td>會員編號</td>
		<td><input type="TEXT" name="member_id" size="45" 
			 value="<%= (course_orderVO==null)? "M00001" : course_orderVO.getMember_id()%>" /></td>
	</tr>
	
	<jsp:useBean id="teacherSvc" scope="page" class="com.Teacher.model.TeacherService" />
	<tr>
		<td>老師:<font color=red><b>*</b></font></td>
		<td><select size="1" name="teacher_id">
			<c:forEach var="teacherVO" items="${teacherSvc.all}">
				<option value="${teacherVO.teacher_id}" ${(teacher_orderVO.teacher_id==teacherVO.teacher_id)? 'selected':'' } >${teacherVO.teacher_id}
			</c:forEach>
		</select></td>
	</tr>
	
	<tr>
		<td>購買時數:</td>
		<td><input type="TEXT" name="buy_time" size="45"
			 value="<%= (course_orderVO==null)? "10000" : course_orderVO.getBuy_time()%>" /></td>
	</tr>
	<tr>
		<td>花費點數:</td>
		<td><input type="TEXT" name="spend_point" size="45"
			 value="<%= (course_orderVO==null)? "10000" : course_orderVO.getSpend_point()%>" /></td>
	</tr>
	<tr>
		<td>剩餘時數:</td>
		<td><input type="TEXT" name="remain_hour" size="45"
			 value="<%= (course_orderVO==null)? "100" : course_orderVO.getRemain_hour()%>" /></td>
	</tr>
	<tr>
		<td>重新預約次數:</td>
		<td><input type="TEXT" name="re_appointment" size="45"
			 value="<%= (course_orderVO==null)? "0" : course_orderVO.getRe_appointment()%>" /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>




</html>