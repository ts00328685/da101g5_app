<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.commodity.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CommodityVO commodityVO = (CommodityVO) request.getAttribute("commodityVO"); //CommodityServlet.java(Concroller), 存入req的commodityVO物件
%>

<html>
<head>
<title>Commodity資料 - listOneCommodity.jsp</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/bootstrap/css/bootstrap.css" />

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
.com_pic{
	border-radius: 5px;
	width: 200px;
	height: 200px;
}
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
  th,tr,td{

vertical-align: middle !important;

}
</style>
</head>
<body bgcolor='white'>
<%@ include file="/front-end/file/shop_header.jsp"%>
<div class="container">


		 <h4><a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/front-end/commodity/images/02.png"  width="100" height="100" border="0">回首頁</a></h4>


<table class="table table-bordered">
	<tr>
			<th>商品編號</th>
			<th>會員編號</th>
			<th>商品描述</th>
			<th>商品照片</th>
			<th>商品下載連結</th>
			<th>商品價格</th>
			<th>上架日期</th>
			<th>商品狀態</th>
			<th>評價總分</th>
			<th>評價人數</th>
			<th>評價內容</th>
	</tr>
	<tr>
				<td><%=commodityVO.getCom_id()%></td>
				<td><%=commodityVO.getMember_id()%></td>
				<td style="width:150px;"><%=commodityVO.getCom_description()%></td>
				<td><img class="com_pic" src="<%=request.getContextPath()%>/commodity/commoditypic.do?com_id=${commodityVO.com_id}"></td>
				<td><a href="<%=commodityVO.getCom_download()%>">Download</a></td>
				<td><%=commodityVO.getCom_price()%></td>
				<td><%=commodityVO.getTrandate()%></td>
				<td><%=commodityVO.getCom_status()%></td>
				<td><%=commodityVO.getE_score()%></td>
				<td><%=commodityVO.getE_pol_statistics()%></td>
				<td style="width:150px;"><%=commodityVO.getCom_detail()%></td>
	</tr>
</table>

</div>
<%@ include file="/front-end/file/shop_footer.jsp"%>
</body>
</html>