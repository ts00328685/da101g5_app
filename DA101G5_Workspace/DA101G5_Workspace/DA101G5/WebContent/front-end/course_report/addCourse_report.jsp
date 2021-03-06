<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Course_report.model.*"%>

<%
  Course_reportVO course_reportVO = (Course_reportVO) request.getAttribute("course_reportVO");
%>

<%=  course_reportVO==null  %>>  -- ${course_reportVO==null}


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>課程檢舉新增 - addCourse_report.jsp</title>

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
		 <h3>課程檢舉新增 - addCourse_report.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="course_report.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="member_id" size="45"
			 value="<%= (course_reportVO==null)? "M00002" : course_reportVO.getMember_id()%>" /></td>
	</tr>
	
	<tr>
		<td>檢舉內容:</td>
		<td><input type="TEXT" name="report_text" size="45"
			 value="<%= (course_reportVO==null)? "10000" : course_reportVO.getReport_text()%>" /></td>
	</tr>
	<tr>
		<td>檢舉狀態:</td>
		<td><input type="TEXT" name="report_state" size="45"
			 value="<%= (course_reportVO==null)? "0" : course_reportVO.getReport_state()%>" /></td>
	</tr>

	<jsp:useBean id="teacherSvc" scope="page" class="com.Teacher.model.TeacherService" />
	<tr>
		<td>教師編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="teacher_id">
			<c:forEach var="teacherVO" items="${teacherSvc.all}">
				<option value="${teacherVO.teacher_id}" ${(course_reportVO.teacher_id==teacherVO.teacher_id)? 'selected':'' } >${teacherVO.teacher_id}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>




</html>