<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Sort_course: Home</title>

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
   <tr><td><h3>IBM Sort_course: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Sort_course: Home</p>

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
  <li><a href='listAllSort_course.jsp'>List</a> all Sort_course.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="sort_course.do" >
        <b>輸入課程種類編號 (如7001):</b>
        <input type="text" name="sort_course_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="sort_courseSvc" scope="page" class="com.Sort_course.model.Sort_courseService" />
   
  <li>
     <FORM METHOD="post" ACTION="sort_course.do" >
       <b>選擇課程種類編號:</b>
       <select size="1" name="sort_course_id">
         <c:forEach var="sort_courseVO" items="${sort_courseSvc.all}" > 
          <option value="${sort_courseVO.sort_course_id}">${sort_courseVO.sort_course_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="sort_course.do" >
       <b>選擇課程種類:</b>
       <select size="1" name="sort_course_id">
         <c:forEach var="sort_courseVO" items="${sort_courseSvc.all}" > 
          <option value="${sort_courseVO.sort_course_id}">${sort_courseVO.sort_course}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>課程種類管理</h3>

<ul>
  <li><a href='addSort_course.jsp'>Add</a> a new Sort_course.</li>
</ul>

</body>
</html>