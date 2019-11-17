<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if(request.getSession().getAttribute("memberVO")==null){
    
    out.print("<script>alert('欲檢舉請先登入會員');window.location.href='/front-end/Discuss/DiscussIndex.jsp'</script>");
    //非會員轉回討論區首頁
    return;
}

   String res_id = null;
   if(request.getParameter("res_id")==null||request.getParameter("res_id").trim().length()==0){
       response.sendRedirect("/front-end/Discuss/DiscussIndex.jsp");//無參數轉回討論區首頁
       return;
   }else{
       res_id = request.getParameter("res_id");
   }
   
   pageContext.setAttribute("res_id",res_id);
   String que_id = null;
   if(request.getParameter("que_id")==null||request.getParameter("que_id").trim().length()==0){
       response.sendRedirect("/front-end/Discuss/DiscussIndex.jsp");//無參數轉回討論區首頁
       return;
   }else{
       que_id = request.getParameter("que_id");
   }
   
   pageContext.setAttribute("que_id",que_id);
%>        
<!DOCTYPE html>
<html>
<head>
<style>

body{
	font-family:Microsoft JhengHei;
}


</style>
<%@ include file="/front-end/file/head.file" %>
<meta charset="UTF-8">
<title>檢舉回應</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%@ include file="/front-end/file/header.jsp" %>
<br>
<br>
<br>
<br>
<br>
<div class="container">
<div class="row justify-content-center">
<form action="<%=request.getContextPath() %>/res/res.do" method="post">
<table class="table table-striped table-bordered">
        <tr>
            <td>檢舉回應編號:${res_id}</td>
        </tr>
        <tr>
            <td>
                                        檢舉理由:<br>
                <textarea cols="50" rows="10" name="qrrepocontent" required>${param.qrrepocontent}</textarea>${errorMsgs.qrrepocontent}
            </td>
        </tr>
       
</table>
    <input type="hidden" name="action" value="reportRes">
    <input type="hidden" name="res_id" value="${res_id}">
    <input type="hidden" name="que_id" value="${que_id}">
    <input type="submit" value="送出檢舉"><br>
    <div class="row justify-content-center">  
    <a href="<%=request.getContextPath() %>/front-end/Discuss/DiscussIndex.jsp">回討論區首頁</a>
   </div> 
</form>
</div>
</div>
<%@ include file="/front-end/file/footer2.file" %>
</body>
</html>