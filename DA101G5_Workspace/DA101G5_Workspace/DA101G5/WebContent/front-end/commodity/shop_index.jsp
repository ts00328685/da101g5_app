<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.commodity.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	CommodityService commoditySvc = new CommodityService();
	List<CommodityVO> list = commoditySvc.getAll();
	pageContext.setAttribute("list", list);
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
<img src="<%=request.getContextPath()%>/front-end/commodity/images/02.png" width="45px" height="45px"> <font size="+3">懶骨雞筆記網路書城</font>
<hr>
<table id="table-1">
  <tr> 
    <th width="200">商品名稱</th>
    <th width="200">商品照片</th>
    <th width="200">商品內容</th>
    <th width="200">評價分數</th>
    <th width="200">評價內容</th>
    <th width="120">價格</th>
    <c:if test="${memberVO != null}">
    <th width="120"><img src="<%=request.getContextPath()%>/front-end/commodity/images/shopping-cart.png" width="45px" height="35px"></th>
    </c:if>
    
  </tr>
		<%@ include file="/front-end/commodity/commodity_file/commodity_page1.file"%>
		<c:forEach var="commodityVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
 


  </table>
   
    <table><tr> 
      <td width="200"><div align="center">${commodityVO.com_description}</div></td>
      <td width="200"><div align="center"><img class="com_pic" src="<%=request.getContextPath()%>/commodity/commoditypic.do?com_id=${commodityVO.com_id}"></div></td>
      <td width="200"><div align="center">${commodityVO.com_detail}</div></td>
      <td width="200"><div align="center">${commodityVO.e_score}</div></td>
      <td width="120"><div align="center">${commodityVO.e_des}</div></td>
      <td width="200"><div align="center">${commodityVO.com_price}</div></td>
      <c:if test="${memberVO != null}">
      <td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/commodity/commodity.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn  btn-secondary" value="立即購買"> 
						<input type="hidden" name="com_id" value="${commodityVO.com_id}"> 
						<input type="hidden" name="action" value="checkout">
					</FORM>
				</td>
	</c:if>
    </tr></table>
      
  </c:forEach>
	</table>
		<%@ include file="/front-end/commodity/commodity_file/commodity_page2.file"%>
</div>

	<%@ include file="/front-end/file/shop_footer.jsp"%>	

</body>
</html>