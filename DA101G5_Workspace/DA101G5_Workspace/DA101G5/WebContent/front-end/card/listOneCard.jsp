<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.card.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CardVO cardVO = (CardVO) request.getAttribute("cardVO"); //CardServlet.java(Concroller), 存入req的cardVO物件
%>

<html>
<head>
<title>Card資料 - listOneCard.jsp</title>
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
	background-color: lightyellow;
    border: 2px solid black;
    text-align: center;
    width: 100%;
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
</style>

<style>
  table {
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid lightyellow;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<%@ include file="/front-end/file/card_header.jsp"%>	
<div class="container">


		<h4><a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/front-end/card/images/02.png"  width="100" height="100" border="0">回首頁</a></h4>


<table class="table table-bordered">
	<tr>
		<th>單字編號</th>
		<th>老師編號</th>
		<th>單字</th>
		<th>音標</th>
		<th>發音</th>
		<th>解釋</th>
		<th>例句</th>
	</tr>
	<tr>
		<td><%=cardVO.getCard_id()%></td>
		<td><%=cardVO.getTeacher_id()%></td>
		<td><%=cardVO.getWord()%></td>
		<td><%=cardVO.getPhonetic_symbol()%></td>
		<td><button class="btn btn-warning" onclick="document.getElementById('player${cardVO.card_id}').play()">Play</button><audio id="player${cardVO.card_id}"><source src="<%=request.getContextPath()%>/card/cardmp3.do?card_id=${cardVO.card_id}" type="audio/mpeg"></audio></td>
		<td><%=cardVO.getNative_explain()%></td>
		<td><%=cardVO.getExample()%></td>
	</tr>
</table>

</div>
<%@ include file="/front-end/file/card_footer.jsp"%>		
</body>
</html>