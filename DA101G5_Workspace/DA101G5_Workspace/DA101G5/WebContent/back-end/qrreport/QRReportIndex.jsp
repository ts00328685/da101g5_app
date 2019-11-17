<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.qrreport.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	QrrepoService qrrepoSvc = new QrrepoService();
	List<QrrepoVO> qrrepolist = qrrepoSvc.getAllQrrepoNotDoneYet();
	pageContext.setAttribute("qrrepolist", qrrepolist);
%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.card{
	margin:10px;
}


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
	<jsp:useBean id="queSvc" class="com.que.model.QueService" scope="page" />
	<jsp:useBean id="resSvc" class="com.res.model.ResService" scope="page" />
	<%@ include file="page1.file"%>
	<c:forEach var="qrrepoVO" items="${qrrepolist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<div class="row justify-content-center">
			<form action="<%=request.getContextPath() %>/qrreport/qrreport.do" method="post">
				<div class="card" style="width: 54rem;">
					<div class="card-header">檢舉單號:${qrrepoVO.qrrepo_id}</div>
					<div class="card-body">
						<h5 class="card-title">
							<c:if test="${qrrepoVO.repor_id==null}">
                                                                              被檢舉問題編號:<a href="<%=request.getContextPath() %>/back-end/qrreport/forwardto.jsp?action=qrreportlistone&que_id=${qrrepoVO.repoq_id}&res_id=${qrrepoVO.repor_id}">${qrrepoVO.repoq_id}</a>
							</c:if>
							<c:if test="${qrrepoVO.repor_id!=null}">
                                                                              被檢舉回應編號:<a href="<%=request.getContextPath() %>/back-end/qrreport/forwardto.jsp?action=qrreportlistone&que_id=${qrrepoVO.repoq_id}&res_id=${qrrepoVO.repor_id}">${qrrepoVO.repor_id}</a>
							</c:if>
						</h5>
						<p class="card-text">
							檢舉理由:<br> ${qrrepoVO.qrrepocontent}
						</p>
					</div>
					<div class="card-body">
						<div class="container">
							<div class="row float-left">
								<label> 檢舉處理狀態:</label>
								<c:if test="${qrrepoVO.qrrepostatus==0}">
									<div class="col-6">
										<select name="qrrepostatus">
											<option value="0" selected>未處理(目前狀態)</option>
											<option value="1">已審核未處理</option>
											<option value="2">已處理</option>
										</select>
									</div>
								</c:if>

								<c:if test="${qrrepoVO.qrrepostatus==1}">
									<div class="col-6">
										<select name="qrrepostatus">
											<option value="0">未處理</option>
											<option value="1" selected>已審核未處理(目前狀態)</option>
											<option value="2">已處理</option>
										</select>
									</div>
								</c:if>
							</div>
						</div>
						<div class="container">
							<div class="row float-right">
								<c:if test="${qrrepoVO.repoq_id!=null}">
									<label> 問題顯示狀態:</label>
									<c:if
										test="${queSvc.findQueByPK(qrrepoVO.repoq_id).questatus==1}">
										<div class="col-6">
											<select name="questatus">
												<option value="0">隱藏</option>
												<option value="1" selected>顯示中(目前狀態)</option>
											</select>
										</div>
									</c:if>
									<c:if
										test="${queSvc.findQueByPK(qrrepoVO.repoq_id).questatus==0}">
										<div class="col-6">
											<select name="questatus">
												<option value="0" selected>隱藏中(目前狀態)</option>
												<option value="1">顯示</option>
											</select>
										</div>
									</c:if>
								</c:if>

								<c:if test="${qrrepoVO.repor_id!=null}">
                                                          回應顯示狀態:
                       <c:if test="${resSvc.findResByPK(qrrepoVO.repor_id).resstatus==1}">
										<select name="resstatus">
											<option value="0">隱藏</option>
											<option value="1" selected>顯示中(目前狀態)</option>
										</select>
									</c:if>
									<c:if test="${resSvc.findResByPK(qrrepoVO.repor_id).resstatus==0}">
										<select name="resstatus">
											<option value="0" selected>隱藏中(目前狀態)</option>
											<option value="1">顯示</option>
										</select>
									</c:if>
								</c:if>
							</div>
						</div>
					</div>
					<div class="card-footer text-muted">
						<div class="row justify-content-center">
							<input type="submit" name="" value="確定送出">
						</div>	
					</div>
				</div>
				<input type="hidden" name="action" value="qrreportconfirm">
				<input type="hidden" name="qrrepo_id" value="${qrrepoVO.qrrepo_id}">
				<input type="hidden" name="que_id" value="${qrrepoVO.repoq_id}">
				<input type="hidden" name="res_id" value="${qrrepoVO.repor_id}">
				</form>
				</div>

	</c:forEach>
	
	<%@ include file="page2.file"%>
	
	<%@include file="/back-end/file/footer.file" %>	
</body>
</html>