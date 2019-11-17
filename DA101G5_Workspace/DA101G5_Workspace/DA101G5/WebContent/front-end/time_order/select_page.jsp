<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Time_order: Home</title>

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
   <tr><td><h3>IBM Time_order: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Time_order: Home</p>

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
  <li><a href='listAllTime_order.jsp'>List</a> all Time_orders.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="time_order.do" >
        <b>輸入員工編號 (如7001):</b>
        <input type="text" name="time_order_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="time_orderSvc" scope="page" class="com.Time_order.model.Time_orderService" />
   
  <li>
     <FORM METHOD="post" ACTION="time_order.do" >
       <b>選擇員工編號:</b>
       <select size="1" name="time_order_id">
         <c:forEach var="time_orderVO" items="${time_orderSvc.all}" > 
          <option value="${time_orderVO.time_order_id}">${time_orderVO.time_order_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="time_order.do" >
       <b>選擇教師的預約明細:</b>
       <select size="1" name="time_order_id">
         <c:forEach var="time_orderVO" items="${time_orderSvc.all}" > 
          <option value="${time_orderVO.time_order_id}">${time_orderVO.teacher_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addTime_order.jsp'>Add</a> a new Time_order.</li>
</ul>

</body>
</html>