<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Sort_course.model.*"%>

<%
  Sort_courseVO sort_courseVO = (Sort_courseVO) request.getAttribute("sort_courseVO");
%>

<%=  sort_courseVO==null  %>>  -- ${sort_courseVO==null}


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�ҵ{������Ʒs�W - addSort_course.jsp</title>

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
		 <h3>�ҵ{������Ʒs�W - addSort_course.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="sort_course.do" name="form1" enctype="multipart/form-data">
<table>
<!-- 	<tr> -->
<!-- 		<td>�ҵ{�����s��:</td> -->
<!-- 		<td><input type="TEXT" name="sort_course_id" size="45"  -->
<%-- 			 value="<%= (sort_courseVO==null)? "SC00001" : sort_courseVO.getSort_course_id()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>�ҵ{����:</td>
		<td><input type="TEXT" name="sort_course" size="45"
			 value="<%= (sort_courseVO==null)? "MANAGER" : sort_courseVO.getSort_course()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>




</html>