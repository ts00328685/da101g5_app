<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.commodity.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	CommodityService commoditySvc = new CommodityService();
	List<CommodityVO> list = commoditySvc.getAll();
	pageContext.setAttribute("list", list);
	CommodityVO commodityVO = (CommodityVO) request.getAttribute("commodityVO"); //CommodityServlet.java(Concroller), 存入req的commodityVO物件
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/bootstrap/css/bootstrap.css" />

<meta charset="UTF-8">
<title>懶骨雞筆記商城</title>

<style>
body {
	font-family: 微軟正黑體;
}
.container{
margin-top:2%;
}
.com_pic{
	border-radius: 5px;
	width: 150px;
	height: 150px;
}
th,tr,td{

vertical-align: middle !important;

}

</style>
</head>
<body>
<%@ include file="/front-end/file/shop_header.jsp"%>
<h1>購買成功!!</h1><br>
<h5>請確認以下是您購買之商品，並同步將商品下載連結寄至您的信箱</h5>
<hr>
<table class="table table-bordered">
	<tr>
			<th>商品編號</th>
			<th>商品名稱</th>
			<th>商品照片</th>
			<th>商品下載連結</th>
			<th>商品價格</th>
			<th>評價總分</th>
			<th>評價人數</th>
			<th>評價內容</th>
	</tr>
	<tr>
				<td><%=commodityVO.getCom_id()%></td>
				<td style="width:150px;"><%=commodityVO.getCom_description()%></td>
				<td><img class="com_pic" src="<%=request.getContextPath()%>/commodity/commoditypic.do?com_id=${commodityVO.com_id}"></td>
				<td><a href="<%=commodityVO.getCom_download()%>">Download</a></td>
				<td><%=commodityVO.getCom_price()%></td>
				<td><%=commodityVO.getE_score()%></td>
				<td><%=commodityVO.getE_pol_statistics()%></td>
				<td style="width:150px;"><%=commodityVO.getE_des()%></td>
	</tr>
</table>
</div>

	<%@ include file="/front-end/file/shop_footer.jsp"%>	

</body>
</html>