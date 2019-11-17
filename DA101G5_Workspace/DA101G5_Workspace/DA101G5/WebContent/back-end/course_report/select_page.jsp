<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Course_report: Home</title>

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
   <tr><td><h3>IBM Course_report: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Course_report: Home</p>

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
  <li><a href='listAllCourse_report.jsp'>List</a> all Course_reports.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="course_report.do" >
        <b>輸入課程檢舉編號 (如7001):</b>
        <input type="text" name="course_report_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="course_reportSvc" scope="page" class="com.Course_report.model.Course_reportService" />
   
  <li>
     <FORM METHOD="post" ACTION="course_report.do" >
       <b>選擇課程檢舉編號:</b>
       <select size="1" name="course_report_id">
         <c:forEach var="course_reportVO" items="${course_reportSvc.all}" > 
          <option value="${course_reportVO.course_report_id}">${course_reportVO.course_report_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="course_report.do" >
       <b>課程檢舉的老師:</b>
       <select size="1" name="course_report_id">
         <c:forEach var="course_reportVO" items="${course_reportSvc.all}" > 
          <option value="${course_reportVO.course_report_id}">${course_reportVO.teacher_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/course_report/course_report.do" >
       <input type="submit" value="測試課程檢舉">
       <input type="hidden" name="action" value="orderAll">
  </FORM>
<h3>課程檢舉管理</h3>

<ul>
  <li><a href='addCourse_report.jsp'>Add</a> a new Course_report.</li>
</ul>

</body>
</html>