<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ page import="com.que.model.*" %>
<%@ page import="com.res.model.*" %>
<%
    if(request.getAttribute("queVO")==null && request.getAttribute("resVO")==null){
    	response.sendRedirect("/back-end/qrreport/QRReportIndex.jsp");
    	return;
    }
    
    QueVO queVO = null;
    ResVO resVO = null;
    
    if(request.getAttribute("resVO")==null){
    	queVO = (QueVO)request.getAttribute("queVO");
    	pageContext.setAttribute("queVO",queVO);
    }else{
    	resVO = (ResVO)request.getAttribute("resVO");
    	pageContext.setAttribute("resVO",resVO);
    }
%>    
<!DOCTYPE html>
<html>
<head>
<style>
body{
	font-family:Microsoft JhengHei;
}
</style>
<%@ include file="/back-end/file/head.file"%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="/back-end/file/header_back.jsp" %>
<br>
<br>
<br>
<br>
<br>
<jsp:useBean id="hashtagSvc" class="com.hashtag.model.HashtagService"/>
<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
<c:if test="${queVO!=null}">
 
<div class="row justify-content-center">
<div class="card" style="width: 54rem;">
  <div class="card-header bg-primary text-white">
        <div class="row justify-content-center">問題標題:${queVO.quetitle}</div>
        <div class="float-left">問題編號:${queVO.que_id}</div><br>
        <div class="float-left">發問者:${memberSvc.getOneMember(queVO.member_id).mem_nick}</div>
        <div class="float-right">讚:${queVO.quethumb}</div><br>
        <div class="float-right">
                              文章顯示狀態:
           <c:if test="${queVO.questatus==1}">
                                              顯示中
           </c:if>
           <c:if test="${queVO.questatus==0}">
                                              隱藏中
            </c:if>               
        </div>  
  </div>
  
  <div class="card-body">
    <p class="card-title float-left">
            <p class="card-text"><small class="text-muted">發文時間:${queVO.quetime.toString().substring(0,19) }</small></p>
           ${queVO.quecontent }
    </p>
    <br>
    <c:if test="${queVO.questatus ==1 }">
      <c:if test="${queVO.quefile !=null && queVO.getFiletype().trim().substring(0,5) eq'image'}">
        <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}">
          <img class="threadfile" src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}"/></a>
      </c:if>
      
      <c:if test="${queVO.quefile !=null && queVO.getFiletype().trim().substring(0,5) eq'video'}">
        <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}">
          <video class="threadfile" controls loop>
            <source src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}" type="video/webm"> 
          </video>
        </a>
      </c:if>
      
      <c:if test="${queVO.quefile !=null && queVO.getFiletype().trim().substring(0,5) eq'audio'}">
        <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}">
          <video class="threadfile"  controls loop>
             <source src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}" type="audio/webm">
          </video>
        </a>
      </c:if>
      <c:if test="${queVO.quefile ==null }">
                            無圖片
      </c:if>
      </c:if>    
  </div>
  
  <div class="card-footer text-muted">
     hashtag:<br>
       <c:if test="${qhashtaglist!=null}">
          <c:forEach var="qhashtagVO" items="${qhashtaglist}">
              <input disabled class="btn btn-primary" type="submit" value="# ${hashtagSvc.findHashtagByPK(qhashtagVO.getHashtag_id()).getHashtag()}">
          </c:forEach>
       </c:if>
  </div>
</div>
</div>      
</c:if>
<!-- ---------------------------------------------------------------------------------------------------- -->
<c:if test="${resVO!=null}">

<div class="row justify-content-center">
<div class="card" style="width: 54rem;">
  <div class="card-header bg-primary text-white">
        
        <div class="float-left">回應編號:${resVO.res_id}</div><br>
        <div class="float-left">回應者:${memberSvc.getOneMember(resVO.member_id).mem_nick }</div>
        <div class="float-right">讚:${resVO.resthumb}</div><br>
        <div class="float-right">
                                    文章顯示狀態:
              <c:if test="${resVO.resstatus==1}">
                                        顯示中
              </c:if>
              <c:if test="${resVO.resstatus==0}">
                                        隱藏中
              </c:if>            
        </div>  
  </div>
  
  <div class="card-body">
    <p class="card-title float-left">
            <p class="card-text"><small class="text-muted">回應時間:${resVO.restime.toString().substring(0,19)}</small></p>
           ${resVO.rescontent}
    </p>
    <br>
    <c:if test="${resVO.resfile !=null && resVO.getFiletype().trim().substring(0,5) eq 'image'}">
      <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}">
         <img id="" src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}" />
      </a>
    </c:if>
    <c:if test="${resVO.resfile !=null && resVO.getFiletype().trim().substring(0,5) eq 'video'}">
      <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}">
         <video controls loop>
           <source src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}" type="video/webm">
         </video>
      </a>
    </c:if>
    <c:if test="${resVO.resfile !=null && resVO.getFiletype().trim().substring(0,5) eq 'audio'}">
      <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}">
        <video  controls loop>
          <source src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}" type="audio/webm">
        </video> 
      </a>
    </c:if>
    <c:if test="${resVO.resfile ==null }">
                       無圖片
    </c:if>
  </div>
</div>
</div>        
</c:if>
<div class="row justify-content-center">
	<button type="button" class="btn btn-primary" onclick="history.back()">回上頁</button>
</div>
<%@include file="/back-end/file/footer.file" %>	
</body>
</html>