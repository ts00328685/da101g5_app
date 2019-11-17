<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.commodity.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	CommodityVO commodityVO = (CommodityVO) request.getAttribute("commodityVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品資料新增 - addCommodity.jsp</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/bootstrap/css/bootstrap.css" />

<style>
body {
	font-family: 微軟正黑體;
}

.container {
	margin-top: 2%;
}

table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

table {
	width: 100%;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<body bgcolor='white'>

	<%@ include file="/front-end/file/shop_header.jsp"%>
	<div class="container justify-content-center">

		<h3
			style="display: flex; flex-direction: column; align-items: center;">商品資料新增:</h3>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/commodity/commodity.do" name="form1" enctype="multipart/form-data">
			<div class="form-group">
				<table>
					<tr>
						<td width="150">商品描述:</td>
						<td><input class="form-control" type="TEXT"
							name="com_description" size="60"
							value="<%=(commodityVO == null) ? "筆記" : commodityVO.getCom_description()%>" /></td>
					</tr>
					<tr>
						<td>會員編號:</td>
						<td><input class="form-control" id="disabledInput"
							type="TEXT" name="member_id" size="45" disabled 
							value="<%=((MemberVO) session.getAttribute("memberVO")).getMember_id()%>" />
							<input class="form-control" id="disabledInput"
							type="hidden" name="member_id" size="45" 
							value="<%=((MemberVO) session.getAttribute("memberVO")).getMember_id()%>" /></td>
					</tr>
					<tr>
						<td>商品照片:</td>
						<td><div class="custom-file">
								<input type="file" name="com_pic" class="custom-file-input" id="inputGroupFile01">
								<label class="custom-file-label" for="inputGroupFile01"></label>
							</div></td>
					</tr>
					<tr>
						<td>下載連結:</td>
						<td><input class="form-control" type="TEXT" name="com_download"
							size="450"
							value="<%=(commodityVO == null) ? "https:\\\\drive.google.com/uc?export=download&id=0B2x595t1GJinYURENlpSb0RHVUk" : commodityVO.getCom_download()%>" /></td>
					</tr>
					<tr>
						<td>價錢:</td>
						<td><input class="form-control" type="TEXT"
							name="com_price" size="45"
							value="<%=(commodityVO == null) ? "100" : commodityVO.getCom_price()%>" /></td>
					</tr>
					
					<tr>
						<td>商品內容:</td>
						<td><input class="form-control" type="TEXT"
							name="com_detail" size="45"
							value="<%=(commodityVO == null) ? "100" : commodityVO.getCom_detail()%>" /></td>
					</tr>

					<jsp:useBean id="commodityVO2" scope="page"
						class="com.commodity.model.CommodityService" />
					


				</table>
				

				
				<br>
				<input type="hidden" name="action" value="insert">
				<div
					style="display: flex; flex-direction: column; align-items: center;">
					<input type="submit" class="form-control col-4 btn btn-primary"
						value="送出新增">
				</div>
			</div>
		</FORM>

	</div>
<%@ include file="/front-end/file/shop_footer.jsp"%>
</body>


</html>