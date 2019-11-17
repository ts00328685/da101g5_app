<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>點數交易查詢</title>

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


<h3>點數交易資料查詢:</h3>
	
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
  <li><a href='listAllPoint_trans.jsp'>List</a> all Point_trans.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="point_trans.do" >
        <b>輸入點數交易編號 (如PTS00001):</b>
        <input type="text" name="point_trans_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="point_transSvc" scope="page" class="com.point_trans.model.Point_transService" />
   
  <li>
     <FORM METHOD="post" ACTION="point_trans.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="point_trans_id">
         <c:forEach var="point_transVO" items="${point_transSvc.all}" > 
          <option value="${point_transVO.point_trans_id}">${point_transVO.point_trans_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="point_trans.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="point_trans_id">
         <c:forEach var="point_transVO" items="${point_transSvc.all}" > 
          <option value="${point_transVO.point_trans_id}">${point_transVO.member_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>點數交易管理</h3>

<ul>
  <li><a href='addPoint_trans.jsp'>Add</a> a new Point_trans.</li>
</ul>

</body>
</html>