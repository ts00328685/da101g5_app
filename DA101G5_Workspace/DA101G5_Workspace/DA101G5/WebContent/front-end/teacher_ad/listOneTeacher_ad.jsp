<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.Teacher_ad.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  Teacher_adVO teacher_adVO = (Teacher_adVO) session.getAttribute("teacher_adVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>


<%
    Teacher_adService teacherSvc = new Teacher_adService();
    List<Teacher_adVO> list = teacherSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
<head>
<title>�Юv�s�i��� - listOneTeacher_ad.jsp</title>
<%@ include file="/front-end/file/head.file"%>

</head>
<body>
<%@ include file="/front-end/file/header.jsp"%>
<div class="container" style="padding-top:200px;">
<h4 >�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Юv�s�i��� - ListOneTeacher_ad.jsp</h3>
		 <h4><a href="select_page.jsp">
		 <img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>



<table>
	<tr>
		<th>�Юv�s�i�s��</th>
		<th>�Юv�s��</th>
		<th>�ʶR�ɼ�</th>
		<th>�}�l�ɶ�</th>
		<th>�s�i���A</th>
		
	</tr>
	<tr>
	
			<td>${teacher_adVO.teacher_ad_id}</td>
			<td>${teacher_adVO.teacher_id}</td>
			<td>${teacher_adVO.ad_time}</td>
			<td><fmt:formatDate value="${teacher_adVO.ad_start}" pattern="yyyy-MM-dd"/></td>
			<td>${teacher_adVO.ad_state}</td>
<%-- 		<td><%=teacher_adVO.getTeacher_ad_id()%></td> --%>
<%-- 		<td><%=teacher_adVO.getTeacher_id()%></td> --%>
<%-- 		<td><%=teacher_adVO.getAd_time()%></td> --%>
<%-- 		<td><%=teacher_adVO.getAd_start()%></td> --%>
<%-- 		<td><%=teacher_adVO.getAd_state()%></td> --%>

	</tr>
</table>
</div>
<br><br><br><br>
<%@ include file="/front-end/file/footer.file"%>
</body>
</html>