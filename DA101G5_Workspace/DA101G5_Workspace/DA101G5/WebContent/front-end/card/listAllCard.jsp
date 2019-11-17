<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.card.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	CardService cardSvc = new CardService();
	List<CardVO> list = cardSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<title>所有單字資料 - listAllCard.jsp</title>

<style>
body {
	font-family: 微軟正黑體;
}
.container{
margin-top:2%;
}
th,tr,td{

vertical-align: middle !important;

}
</style>

<style>



</style>

</head>
<body bgcolor='white'>
<%@ include file="/front-end/file/card_header.jsp"%>
<div class="container">


		<h3 style="display:flex; flex-direction:column; align-items:center;" >所有單字資料 - listAllCard.jsp</h3>


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table  class="table table-bordered">
		<tr >
			<th>單字編號</th>
			<th>老師編號</th>
			<th>單字</th>
			<th>音標</th>
			<th>發音</th>
			<th>解釋</th>
			<th>例句</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="/front-end/card/card_file/card_page1.file"%>
		<c:forEach var="cardVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr class="align-text-middle">
				<td class="align-middle">${cardVO.card_id}</td>
				<td>${cardVO.teacher_id}</td>
				<td>${cardVO.word}</td>
				<td>${cardVO.phonetic_symbol}</td>
				<td><button class="btn btn-warning" onclick="document.getElementById('player${cardVO.card_id}').play()">Play</button><audio id="player${cardVO.card_id}"><source src="<%=request.getContextPath()%>/cardmp3servlet?card_id=${cardVO.card_id}" type="audio/mpeg"></audio></td>
				<td>${cardVO.native_explain}</td>
				<td>${cardVO.example}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/card/card.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn  btn-secondary" value="修改"> <input type="hidden"
							name="card_id" value="${cardVO.card_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/card/card.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn  btn-primary"  value="刪除"> <input type="hidden"
							name="card_id" value="${cardVO.card_id}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="/front-end/card/card_file/card_page2.file"%>
</div>

<%@ include file="/front-end/file/card_footer.jsp"%>	
</body>
</html>