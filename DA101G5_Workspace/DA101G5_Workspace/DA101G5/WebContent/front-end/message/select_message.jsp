<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>會員訊息查詢</title>

</head>
<body bgcolor='white'>

<h1>會員訊息查詢</h1>


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
  <li><a href='listAllMessage.jsp'>List</a> all Message. <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/message/message.do">
        <b>輸入訊息編號 (如M00001):</b>
        <input type="text" name="message_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="messageSvc" scope="page" class="com.message.model.MessageService" />
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/message/message.do" >
       <b>選擇訊息編號:</b>
       <select size="1" name="message_id">
         <c:forEach var="messageVO" items="${messageSvc.all}" > 
          <option value="${messageVO.message_id}">${messageVO.message_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/message/message.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="message_id">
         <c:forEach var="messageVO" items="${messageSvc.all}" > 
          <option value="${messageVO.message_id}">${messageVO.member_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>
 
 <h3>訊息管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/front-end/message/addMessage.jsp'>Add</a> a new Message.</li>
</ul>
 
</body>
</html>