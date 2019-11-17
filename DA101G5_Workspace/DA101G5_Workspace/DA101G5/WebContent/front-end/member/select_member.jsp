<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>會員資料查詢</title>

</head>
<body bgcolor='white'>

<h1>會員資料查詢</h1>


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
  <li><a href='listAllMember.jsp'>List</a> all Members. <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="member.do">
        <b>輸入會員編號 (如M00001):</b>
        <input type="text" name="member_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
  
  <li>
     <FORM METHOD="post" ACTION="member.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="member_id">
         <c:forEach var="memberVO" items="${memberSvc.all}" > 
          <option value="${memberVO.member_id}">${memberVO.member_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="member.do" >
       <b>選擇會員姓名:</b>
       <select size="1" name="member_id">
         <c:forEach var="memberVO" items="${memberSvc.all}" > 
          <option value="${memberVO.member_id}">${memberVO.mem_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>
 
 <h3>會員管理</h3>

<ul>
  <li><a href='addMember.jsp'>Add</a> a new Member.</li>
</ul>
 
</body>
</html>