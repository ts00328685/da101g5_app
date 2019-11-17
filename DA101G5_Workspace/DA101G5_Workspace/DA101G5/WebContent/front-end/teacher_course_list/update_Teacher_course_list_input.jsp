<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Teacher_course_list.model.*"%>

<%
  Teacher_course_listVO teacher_course_listVO = (Teacher_course_listVO) request.getAttribute("teacher_course_listVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<%=  teacher_course_listVO==null  %>>  -- ${teacher_course_listVO==null}
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�Юv�ҵ{���Ӹ�ƭק� - update_Teacher_course_list_input.jsp</title>

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
		 <h3>�Юv�ҵ{���Ӹ�ƭק� - update_Teacher_course_list_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="<%=request.getContextPath()%>/front-end/teacher_course_list/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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

<FORM METHOD="post" ACTION="teacher_course_list.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>�Юv�ҵ{�q����ӽs��:<font color=red><b>*</b></font></td>
		<td><%=teacher_course_listVO.getTeacher_course_list_id()%></td>
	</tr>
	<tr>
		<td>�ҵ{�����s��:</td>
		<td><input type="TEXT" name="sort_course_id" size="45" value="<%=teacher_course_listVO.getSort_course_id()%>" /></td>
	</tr>
	<tr>
		<td>�y���s��:</td>
		<td><input type="TEXT" name="language_id" size="45"	value="<%=teacher_course_listVO.getLanguage_id()%>" /></td>
	</tr>
	
	<tr>
		<td>�Юv�s��:</td>
		<td><input type="TEXT" name="teacher_id" size="45"	value="<%=teacher_course_listVO.getTeacher_id()%>" /></td>
	</tr>
	<tr>
		<td>���ҵ{�`�����֭p:</td>
		<td><input type="TEXT" name="course_appraisal_accum" size="45" value="<%=teacher_course_listVO.getCourse_appraisal_accum()%>" /></td>
	</tr>
	<tr>
		<td>���ҵ{�`��������:</td>
		<td><input type="TEXT" name="course_appraisal_count" size="45" value="<%=teacher_course_listVO.getCourse_appraisal_count()%>" /></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="teacher_course_list_id" value="<%=teacher_course_listVO.getTeacher_course_list_id()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>




</html>