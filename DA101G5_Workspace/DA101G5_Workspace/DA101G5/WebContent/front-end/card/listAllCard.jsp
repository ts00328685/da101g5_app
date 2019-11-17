<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.card.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	CardService cardSvc = new CardService();
	List<CardVO> list = cardSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<title>�Ҧ���r��� - listAllCard.jsp</title>

<style>
body {
	font-family: �L�n������;
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


		<h3 style="display:flex; flex-direction:column; align-items:center;" >�Ҧ���r��� - listAllCard.jsp</h3>


	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table  class="table table-bordered">
		<tr >
			<th>��r�s��</th>
			<th>�Ѯv�s��</th>
			<th>��r</th>
			<th>����</th>
			<th>�o��</th>
			<th>����</th>
			<th>�ҥy</th>
			<th>�ק�</th>
			<th>�R��</th>
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
						<input type="submit" class="btn  btn-secondary" value="�ק�"> <input type="hidden"
							name="card_id" value="${cardVO.card_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/card/card.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn  btn-primary"  value="�R��"> <input type="hidden"
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