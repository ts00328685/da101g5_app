<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Teacher_course_list.model.*"%>

<%
  Teacher_course_listVO teacher_course_listVO = (Teacher_course_listVO) request.getAttribute("teacher_course_listVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%=  teacher_course_listVO==null  %>>  -- ${teacher_course_listVO==null}
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>教師課程明細資料修改 - update_Teacher_course_list_input.jsp</title>

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
		 <h3>教師課程明細資料修改 - update_Teacher_course_list_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="<%=request.getContextPath()%>/front-end/teacher_course_list/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="teacher_course_list.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>教師課程訂單明細編號:<font color=red><b>*</b></font></td>
		<td><%=teacher_course_listVO.getTeacher_course_list_id()%></td>
	</tr>
	<tr>
		<td>課程種類編號:</td>
		<td><input type="TEXT" name="sort_course_id" size="45" value="<%=teacher_course_listVO.getSort_course_id()%>" /></td>
	</tr>
	<tr>
		<td>語言編號:</td>
		<td><input type="TEXT" name="language_id" size="45"	value="<%=teacher_course_listVO.getLanguage_id()%>" /></td>
	</tr>
	
	<tr>
		<td>教師編號:</td>
		<td><input type="TEXT" name="teacher_id" size="45"	value="<%=teacher_course_listVO.getTeacher_id()%>" /></td>
	</tr>
	<tr>
		<td>此課程總評價累計:</td>
		<td><input type="TEXT" name="course_appraisal_accum" size="45" value="<%=teacher_course_listVO.getCourse_appraisal_accum()%>" /></td>
	</tr>
	<tr>
		<td>此課程總評價次數:</td>
		<td><input type="TEXT" name="course_appraisal_count" size="45" value="<%=teacher_course_listVO.getCourse_appraisal_count()%>" /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="teacher_course_list_id" value="<%=teacher_course_listVO.getTeacher_course_list_id()%>">
<input type="submit" value="送出修改"></FORM>
</body>




</html>