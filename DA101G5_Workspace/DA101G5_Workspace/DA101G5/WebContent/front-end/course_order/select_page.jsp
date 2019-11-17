<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Course_order: Home</title>

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
   <tr><td><h3>IBM Course_order: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Course_order: Home</p>

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
  <li><a href='listAllCourse_order.jsp'>List</a> all Course_orders.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="course_order.do" >
        <b>��J�ҵ{�q��s�� (�p7001):</b>
        <input type="text" name="course_order_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="course_orderSvc" scope="page" class="com.Course_order.model.Course_orderService" />
   
  <li>
     <FORM METHOD="post" ACTION="course_order.do" >
       <b>��ܽҵ{�q��s��:</b>
       <select size="1" name="course_order_id">
         <c:forEach var="course_orderVO" items="${course_orderSvc.all}" > 
          <option value="${course_orderVO.course_order_id}">${course_orderVO.course_order_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="course_order.do" >
       <b>��ܦѮvID:</b>
       <select size="1" name="course_order_id">
         <c:forEach var="course_orderVO" items="${course_orderSvc.all}" > 
          <option value="${course_orderVO.course_order_id}">${course_orderVO.teacher_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>�ҵ{�q��޲z</h3>

<ul>
  <li><a href='addCourse_order.jsp'>Add</a> a new Course_order.</li>
</ul>

</body>
</html>