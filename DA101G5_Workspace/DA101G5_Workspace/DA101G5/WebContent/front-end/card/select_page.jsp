<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <title>IBM Card: Home</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>

<script>
  $(document).ready(function () {
    $('.selectpicker').selectpicker();
  });
</script>

  <style>

body{
 font-family: 微軟正黑體;
}
.container{
margin-top:2%;
}
.form-control{


}
.form-group{

display:flex;
}
.header{
display:flex;
flex-direction:column;
align-items:center;
margin:2%;

}

</style>

</head>
<body bgcolor='white'>
<%@ include file="/front-end/file/card_header.jsp"%>
  <div class="container justify-content-center">
<div class="header container justify-content-center">
   <h3>資料查詢:</h3>
</div>
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
  <li><a class="col-4" href='<%=request.getContextPath()%>/front-end/card/listAllCard.jsp'>List all Cards.</a><br><br></li>
  
  
  <li>
  
    <FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/card/card.do" >
      <div class="form-group">
      <b class="col-4 ">輸入單字編號 (如C00001):</b>
      <input class="form-control col-6 mr-auto" type="text" name="card_id">
      <input class="form-control" type="hidden" name="action" value="getOne_For_Display">&nbsp
      <input class="form-control col-2 form-control btn btn-primary" type="submit" value="送出">
      </div>
    </FORM>
  
  </li>

  <jsp:useBean id="cardSvc" scope="page" class="com.card.model.CardService" />

  <li>
   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/card/card.do" >
     <div class="form-group">
     <b class="col-4 " >選擇單字編號:</b>
     <select class="browser-default custom-select  col-6 " size="1" name="card_id">
       <c:forEach var="cardVO" items="${cardSvc.all}" > 
       <option value="${cardVO.card_id}">${cardVO.card_id}
       </c:forEach>   
     </select>
     <input class="form-control" type="hidden" name="action" value="getOne_For_Display">&nbsp
     <input type="submit" class="form-control col-2 btn  btn-primary" value="送出">
     </div>
   </FORM>
 </li>

 <li>
   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/card/card.do" >
    <div class="form-group">
     <b class="col-4" >選擇單字:</b>
     <select class="browser-default custom-select  col-6 "  size="1" name="card_id">
       <c:forEach var="cardVO" items="${cardSvc.all}" > 
       <option value="${cardVO.card_id}">${cardVO.word}
       </c:forEach>   
     </select>
     <input class="form-control" type="hidden" name="action" value="getOne_For_Display">&nbsp
     <input class="form-control col-2 btn btn-primary" type="submit" value="送出">
   </div>
   </FORM>
 </li>

</ul>
     
<div class="header  container justify-content-center">
<h3>單字管理</h3>
</div>

<ul>
  <li><a class="col-4" href='<%=request.getContextPath()%>/front-end/card/addCard.jsp'>Add a new Card.</a></li>
</ul>


</div>
<%@ include file="/front-end/file/card_footer.jsp"%>
</body>
</html>