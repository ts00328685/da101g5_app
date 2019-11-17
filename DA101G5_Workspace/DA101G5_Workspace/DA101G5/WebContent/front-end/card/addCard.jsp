<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.card.model.*"%>

<%
	CardVO cardVO = (CardVO) request.getAttribute("cardVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>單字資料新增 - addCard.jsp</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
body {
	font-family: 微軟正黑體;
}
.container{
margin-top:2%;
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

<%@ include file="/front-end/file/card_header.jsp"%>
	<div class="container justify-content-center">

		<h3  style="display:flex; flex-direction:column; align-items:center;" >資料新增:</h3>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/card/card.do" name="form1">
			<div class="form-group">
				<table>
					<tr>
						<td>單字:</td>
						<td><input class="form-control" type="TEXT" name="word" size="45"
							value="<%=(cardVO == null) ? "pineapple" : cardVO.getWord()%>" /></td>
					</tr>
					<tr>
						<td>教師編號:</td>
						<td><input class="form-control" type="TEXT" name="teacher_id" size="45"
							value="<%=(cardVO == null) ? "T00001" : cardVO.getTeacher_id()%>" /></td>
					</tr>
					<tr>
						<td>音標:</td>
						<td><input class="form-control" name="phonetic_symbol" id="" type="text"></td>
					</tr>
					<tr>
						<td>發音:</td>
						<td><input class="form-control" type="TEXT" name="voice" size="45"
							value="<%=(cardVO == null) ? "" : cardVO.getVoice()%>" /></td>
					</tr>
					<tr>
						<td>解釋:</td>
						<td><input class="form-control" type="TEXT" name="native_explain" size="45"
							value="<%=(cardVO == null) ? "鳳梨" : cardVO.getNative_explain()%>" /></td>
					</tr>
					<tr>
						<td>例句:</td>
						<td><input class="form-control" type="TEXT" name="example" size="45"
							value="<%=(cardVO == null) ? "Your head looks exactly like a pineapple!" : cardVO.getExample()%>" /></td>
					</tr>
					<jsp:useBean id="cardVO2" scope="page"
						class="com.card.model.CardService" />
					<tr>
						<td>XX編號:<font color=red><b>*</b></font></td>
						<td><select class="browser-default custom-select  col-6 "  size="1" name="teacher_id">
								<c:forEach var="cardVO" items="${cardSvc.all}">
									<option value="${cardVO.teacher_id}"
										${(cardVO.teacher_id==cardVO.teacher_id)? 'selected':'' }>${cardVO.teacher_id}
								</c:forEach>
						</select></td>
					</tr>
					

				</table>
					<br><input type="hidden" name="action" value="insert">
				<div  style="display:flex; flex-direction:column; align-items:center;" >
				<input type="submit"  class="form-control col-4 btn btn-primary" value="送出新增">
				</div>
			</div>
		</FORM>

	</div>
<%@ include file="/front-end/file/card_footer.jsp"%>
</body>


</html>