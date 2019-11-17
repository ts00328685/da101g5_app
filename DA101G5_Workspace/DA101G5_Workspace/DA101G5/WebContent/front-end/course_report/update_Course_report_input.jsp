<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Course_report.model.*"%>

<%
  Course_reportVO course_reportVO = (Course_reportVO) request.getAttribute("course_reportVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<%=  course_reportVO==null  %>>  -- ${course_reportVO==null}--${course_reportVO.teacher_id}
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ҵ{���|��ƭק� - update_course_report_input.jsp</title>

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
		 <h3>�ҵ{���|��ƭק� - update_emp_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="course_report.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>���|�s��:<font color=red><b>*</b></font></td>
		<td><%=course_reportVO.getCourse_report_id()%></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>�Юv�s��:</td> -->
<%-- 		<td><input type="TEXT" name="teacher_id" size="45" value="<%=course_reportVO.getTeacher_id()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="member_id" size="45"	value="<%=course_reportVO.getMember_id()%>" /></td>
	</tr>
	
	<tr>
		<td>���|���e:</td>
		<td><input type="TEXT" name="report_text" size="45"	value="<%=course_reportVO.getReport_text()%>" /></td>
	</tr>
	
	<tr>
		<td>���|���A:</td>
		<td><input type="TEXT" name="report_state" size="45"	value="<%=course_reportVO.getReport_state()%>" /></td>
	</tr>
	
	
	

	<jsp:useBean id="teacherSvc" scope="page" class="com.Teacher.model.TeacherService" />
	<tr>
		<td>�Ѯv:<font color=red><b>*</b></font></td>
		<td><select size="1" name="teacher_id">
			<c:forEach var="teacherVO" items="${teacherSvc.all}">
				<option value="${teacherVO.teacher_id}" ${(course_reportVO.teacher_id==teacherVO.teacher_id)?'selected':'' } >${teacherVO.teacher_id}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="course_report_id" value="<%=course_reportVO.getCourse_report_id()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>


</html>