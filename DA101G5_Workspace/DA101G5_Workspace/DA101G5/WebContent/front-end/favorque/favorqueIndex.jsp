<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.favorque.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="com.que.model.*" %>
<%@ page import="java.util.*" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%
     String member_id = null;
     if(session.getAttribute("memberVO")==null){
    	 out.print("<script>alert('請先登入會員');window.location.href='/index.jsp'</script>");
    	 return;
     }else{
    	 MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
    	 member_id = memberVO.getMember_id();
     }
     
     FavorqueService favorqueSvc = new FavorqueService();
     List<FavorqueVO> favorquelist = favorqueSvc.findFavorqueByMemberId(member_id);
     
     QueService queSvc = new QueService();
     List<QueVO> quelist = new ArrayList<QueVO>();
     
     for(int i =0; i<favorquelist.size(); i++){
    	 String que_id = favorquelist.get(i).getQue_id();
    	 QueVO queVO = queSvc.findQueByPK(que_id);
    	 quelist.add(queVO);
     }
     pageContext.setAttribute("quelist", quelist);
     
     
%>    
<!DOCTYPE html>
<html>
<head>
<script>
	function deletefavorque(queid,memid){
		var http = new XMLHttpRequest();
		var currentLocation = window.location.origin;
		var url = currentLocation + '<%=request.getContextPath()%>/favorque/favorque.do';
		
		var params = 'que_id=' + queid +'&member_id='+memid+'&action=deletefavorque';
		http.open('POST', url, true);

		//Send the proper header information along with the request
		http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

		http.onreadystatechange = function() {//Call a function when the state changes.
		    if(http.readyState == 4 && http.status == 200) {
		        if(http.responseText.indexOf('true')>-1){
		        	alert('已成功移除');
		        	window.location.reload();
		        }else{
		        	
		        	alert('移除最愛問題失敗');
		        	
		        }
		    }
		}
		http.send(params);
	}
	
</script>
<style>

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
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/front-end/file/header.jsp" %>
<jsp:useBean id="qhashtagSvc" class="com.qhashtag.model.QhashtagService" scope="page"/>
<jsp:useBean id="hashtagSvc" class="com.hashtag.model.HashtagService" scope="page"/>  


<c:if test="${quelist.size()==0}">
   <div class="container">  
     <table class="table table-bordered">
        <tr><td align='center'>你目前沒有最愛問題</td></tr>
     </table>
   </div>
</c:if>
<br>
<br>
<br>
<br>
<br>
<br>
<br>




<c:if test="${quelist.size()!=0}">
<div class="container" style="padding-top:150px;padding-bottom:100px;">
<%@ include file="favorque_file/quepage1.file" %>
<c:forEach var="queVO" items="${quelist}" begin="<%=index%>" end="<%=index+quePerPage-1%>">
<c:if test="${queVO.questatus!=0}">
<div class="card">
  
  <div class="card-header">
        <div class="float-left">發問者:${memberSvc.getOneMember(queVO.member_id).mem_nick}</div> 
        <span class="badge badge-danger float-right">讚:${queVO.quethumb }</span><br>
        <span style="cursor:pointer" class="badge badge-dark float-right" onclick="deletefavorque('${queVO.que_id}','${sessionScope.memberVO.member_id}')" >移除最愛問題</span>    
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
    <span class="badge badge-success float-right">瀏覽人次:${queVO.watchcount}</span><br>
    <span class="badge badge-warning float-right">回應數:${queVO.rescount}</span>
  </div>
  
  <div class="card-footer text-muted">
    hashtag:<br>
    <c:if test="${qhashtagSvc.findQhashtagByQue(queVO.getQue_id())!=null}">
        <c:forEach var="qhashtagVO" items="${qhashtagSvc.findQhashtagByQue(queVO.getQue_id()) }">
          <form class="hashtag" style="float:left" action="/DA101G5/que/que.do" method="post">
            <input class="btn btn-primary" type="submit" value="# ${hashtagSvc.findHashtagByPK(qhashtagVO.getHashtag_id()).getHashtag()}">
            <input type="hidden" name="action" value="tagsearch">
            <input type="hidden" name="hashtag_id" value="${qhashtagVO.hashtag_id}">
          </form>  
        </c:forEach>
    </c:if> 
  </div>
</div>

</c:if>    
</c:forEach>
<%@ include file="favorque_file/quepage2.file" %>
</div>
</c:if>  

<%@ include file="/front-end/file/footer.file" %>
</body>
</html>