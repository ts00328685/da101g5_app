<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>  
<%@ page import="com.recruit.model.*" %> 
<%@ page import="com.member.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
if(request.getAttribute("list")==null){
    response.sendRedirect(request.getContextPath()+"/front-end/recruit/RecruitIndex.jsp");
    return;
}
List<RecruitVO> list = (ArrayList<RecruitVO>)request.getAttribute("list");
pageContext.setAttribute("list",list);
%>          
<!DOCTYPE html>
<html>
<head>
<style>

.card{
    margin:5px;
}

.container{
    display:flex;
    flex-wrap: wrap
}

body{
	font-family:Microsoft JhengHei;
}

</style>
<%@ include file="/front-end/file/head.file" %>
<meta charset="UTF-8">
<title>派單搜尋結果</title>
</head>
<body>
<%@ include file="/front-end/file/header.jsp" %>
<br>
<br>
<br>
<br>
<br>
<div class="container">
<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
<%@ include file="recruit_file/recruit_page1.file" %> 
<c:if test="${list.size()>0}">
<c:forEach var="recruitVO" items="${list}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
    <c:if test="${recruitVO.recstatus==1}"> 
        <div class="row">
          <div class="col-3">   
            <div class="card" style="width: 18rem;">
                <div class="card-header">
                    <ul class="nav nav-pills card-header-pills">
                      <li class="nav-item">
                        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">派單編號:${recruitVO.recruit_id}</a>
                      </li>
                    </ul>
                </div>
                <div class="card-body">
                    <h5 class="card-title">需求:${recruitVO.rtitle}</h5>
                        
                    <h6 class="card-subtitle mb-2 text-muted">發文者:${memberSvc.getOneMember(recruitVO.member_id).mem_nick}</h6>
                        
                    <p class="card-text">說明:<br>${recruitVO.rcontent}</p>
                        
                    <c:if test="${sessionScope.memberVO!=null}">
                       <a class="card-link" href="<%=request.getContextPath()%>/front-end/recruit/reportForm.jsp?recruit_id=${recruitVO.recruit_id}">檢舉派單</a>
                    </c:if>
                        
                    <a href="#" class="card-link">Another link</a>
                    <p class="card-text"><small class="text-muted">發文日期:${recruitVO.startdate.toString().substring(0,19)}</small></p>
                 </div>
             </div>
         </div>     
       </div>      
    </c:if> 
</c:forEach>
</c:if>
<c:if test="${list.size()==0}">
<div class="container">  
     <table class="table table-bordered">
        <tr><td align='center'>查無結果</td></tr>
     </table>
   </div>
</c:if>
</div>
<%@ include file="recruit_file/recruit_page2.file" %> 
<div class="row justify-content-center">
    <button type="button" class="btn btn-link" onclick="history.back()">回上頁</button>
</div>    
<%@ include file="/front-end/file/footer.file" %>
</body>
</html>