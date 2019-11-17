<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>  
<%@ page import="com.que.model.*" %> 
<%@ page import="com.member.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
  
<%
if(request.getAttribute("quelist")==null){
	response.sendRedirect("/front-end/Discuss/DiscussIndex.jsp");
	return;
}
     Set<QueVO> quelist = (LinkedHashSet<QueVO>)request.getAttribute("quelist");
     pageContext.setAttribute("quelist",quelist);
%>    
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.card{
    margin:10px;
} 

.hashtag{
    margin:3px;
}

body{
	font-family:Microsoft JhengHei;
}

</style>
<%@ include file="/front-end/file/head.file" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>搜尋結果</title>
</head>
<body>
<%@ include file="/front-end/file/header.jsp" %>
<br>
<br>
<br>
<br>
<br>
<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
<jsp:useBean id="qhashtagSvc" class="com.qhashtag.model.QhashtagService" scope="page"/>
<jsp:useBean id="hashtagSvc" class="com.hashtag.model.HashtagService" scope="page"/>  

<div class="container"> 
<%@ include file="/front-end/Discuss/Discuss_file/quepage1.file" %>
<c:if test="${quelist.size()!=0}">
<c:forEach var="queVO" items="${quelist}" begin="<%=index%>" end="<%=index+quePerPage-1%>">
<div class="card">
  
  <div class="card-header">
        <div class="float-left">發問者:${memberSvc.getOneMember(queVO.member_id).mem_nick}</div> 
        <div class="float-right">讚:${queVO.quethumb }</div><br>    
    <p class="card-text"><small class="text-muted">發文時間:${queVO.quetime.toString().substring(0,19) }</small></p>
  </div>
  
  <div class="card-body">
    <h5 class="card-title float-left">
        <c:if test="${queVO.questatus ==1 }">
                              標題:<a href="<%=request.getContextPath()%>/front-end/Discuss/forwardto.jsp?que_id=${queVO.que_id}&action=tothread">${queVO.quetitle}</a>
        </c:if>
        <c:if test="${queVO.questatus ==0 }">
                             文章已被隱藏
        </c:if>
    </h5>
    <div class="float-right">瀏覽人次:${queVO.watchcount}</div><br>
    <div class="float-right">回應數:${queVO.rescount}</div>
  </div>
  
  <div class="card-footer text-muted">
    hashtag:<br>
    <c:if test="${qhashtagSvc.findQhashtagByQue(queVO.getQue_id())!=null}">
        <c:forEach var="qhashtagVO" items="${qhashtagSvc.findQhashtagByQue(queVO.getQue_id()) }">
          <form class="hashtag" style="float:left" action="<%=request.getContextPath()%>/que/que.do" method="post">
            <input class="btn btn-primary" type="submit" value="# ${hashtagSvc.findHashtagByPK(qhashtagVO.getHashtag_id()).getHashtag()}">
            <input type="hidden" name="action" value="tagsearch">
            <input type="hidden" name="hashtag_id" value="${qhashtagVO.hashtag_id}">
          </form>  
        </c:forEach>
    </c:if> 
  </div>
</div>
</c:forEach>
</c:if>
<c:if test="${quelist.size()==0}">   
     <table class="table table-bordered">
        <tr><td align='center'>查無結果</td></tr>
     </table>
</c:if>   
<%@ include file="/front-end/Discuss/Discuss_file/quepage2.file" %>
</div>
<br>
<%@ include file="/front-end/file/footer.file" %>
</body>
</html>