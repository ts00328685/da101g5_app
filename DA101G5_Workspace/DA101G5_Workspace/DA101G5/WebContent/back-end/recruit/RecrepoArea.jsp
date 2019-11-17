<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.recrepo.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<%
    /*if(不是管理員){
                    用濾器導向前台派單首頁
    }*/
    RecrepoService recrepoSvc = new RecrepoService();
    List<RecrepoVO> recrepolist = recrepoSvc.getAllNotDoneYetRecrepo();
    pageContext.setAttribute("recrepolist", recrepolist);
%>
<!DOCTYPE html>
<html>
<head>
<style>
.row{
	margin:5px;
}

body{
	font-family:Microsoft JhengHei;
}

</style>
<%@ include file="/back-end/file/head.file"%>
<meta charset="UTF-8">
<title>派單檢舉處理區</title>
</head>
<body>
<%@include file="/back-end/file/header_back.jsp" %>
<br>
<br>
<br>
<br>
<br>
<jsp:useBean id="recruitSvc" class="com.recruit.model.RecruitService" scope="page"/>
<%@ include file="page1.file" %>
<c:forEach var="recrepoVO" items="${recrepolist }" begin="<%=pageIndex %>" end="<%=pageIndex+rowsPerPage-1%>">
    <c:if test="${recrepoVO.repostatus<2 }">
    	<form action="<%=request.getContextPath() %>/recrepo/recrepo.do" method="post">
    		<div class="row justify-content-center">
                    <div class="card" style="width: 30rem;">
                        <div class="card-body">
                            <div class="row justify-content-center">
                                <h5 class="card-title">檢舉單號:${recrepoVO.recrepo_id}</h5>
                            </div>
                            <br>
                            <div class="row float-left">
                                <h5 class="card-title">派單編號:${recrepoVO.recruit_id}</h5>
                            </div>
                            <div class="row float-right">
                                <h5 class="card-title">
                                                                                            派單狀態:
                                    <c:if test="${recruitSvc.findRecruitByPK(recrepoVO.recruit_id).recstatus==1}">
                                                                                                    顯示中
                                    </c:if>
                                    <c:if test="${recruitSvc.findRecruitByPK(recrepoVO.recruit_id).recstatus==0}">
                                                                                                    隱藏中           
                                    </c:if> 
                                </h5>
                            </div>
                            <br>
                            <div class="row float-left">    
                                                                                派單標題:<a href="<%=request.getContextPath() %>/back-end/recruit/Recruitlistone.jsp?recruit_id=${recrepoVO.recruit_id}">${recruitSvc.findRecruitByPK(recrepoVO.recruit_id).rtitle}</a>
                            </div>
                            <div class="row float-left">
                                                                                 修改派單狀態:
                                <c:if test="${recruitSvc.findRecruitByPK(recrepoVO.recruit_id).recstatus==1}">  
                                    <select name="recstatus">
                                        <option value="1" selected="selected">顯示中(目前狀態)</option>
                                        <option value="0">隱藏</option>
                                    </select>
                                </c:if>
                                <c:if test="${recruitSvc.findRecruitByPK(recrepoVO.recruit_id).recstatus==0}"> 
                                    <select name="recstatus">
                                        <option value="1">顯示中</option>
                                        <option value="0" selected="selected">隱藏(目前狀態)</option>
                                    </select>
                                </c:if> 
                            </div>
                            <br>
                            <div class="row float-right">
                                                                                    處理狀態:   
                                <c:if test="${recrepoVO.repostatus==0 }">
                                    <select name="repostatus">
                                        <option value="0" selected = "selected">未處理(目前狀態)</option>  
                                        <option value="1" >已審核未處理</option>
                                        <option value="2">已處理</option>
                                    </select>
                                </c:if>
                                <c:if test="${recrepoVO.repostatus==1 }">
                                    <select name="repostatus">
                                        <option value="0" >未處理</option> 
                                        <option value="1" selected = "selected">已審核未處理(目前狀態)</option>
                                        <option value="2">已處理</option>
                                    </select>
                                </c:if>     
                            </div>
                                                                       
                    </div>
                    <div class="card-body">
                        <div class="row justify-content-center">                                              
                                                                          檢舉理由:<br>
                            <p class="card-text">${recrepoVO.repocontent}</p>
                        </div>
                        <br>  
                        <div class="row justify-content-center">    
                            <input type="submit">
                        </div>   
                    </div>
    		    </div>
    		</div>		
    		<input type="hidden" name="action" value="dorecrepo">
    		<input type="hidden" name="recruit_id" value="${recrepoVO.recruit_id}">
    		<input type="hidden" name="recrepo_id" value="${recrepoVO.recrepo_id}">
    	</form>
    </c:if>
</c:forEach>
<%@ include file="page2.file" %>
<%@include file="/back-end/file/footer.file" %>	
</body>
</html>