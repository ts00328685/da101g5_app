<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.commodity.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	CommodityService commoditySvc = new CommodityService();
	List<CommodityVO> list = commoditySvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/bootstrap/css/bootstrap.css" />

<title>�޲z�ڪ��ӫ~</title>

<style>
body {
	font-family: �L�n������;
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
<body bgcolor='white'>
<%@ include file="/front-end/file/shop_header.jsp"%>
<div class="container">


		<h3 style="display:flex; flex-direction:column; align-items:center;" >�Ҧ��ӫ~��� - listAllCommodity.jsp</h3>


	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table  class="table  vertical-align table-bordered">
		<tr>
			<th>�ӫ~�s��</th>
			<th>�|���s��</th>
			<th>�ӫ~�y�z</th>
			<th>�ӫ~�Ӥ�</th>
			<th>�ӫ~�U���s��</th>
			<th>�ӫ~����</th>
			<th>�ӫ~���e</th>
			<th>�W�[���</th>
			<th>�ӫ~���A</th>
			<th>�����`��</th>
			<th>�����H��</th>
			<th>�������e</th>
		</tr>
<%-- 		<%@ include file="/front-end/commodity/commodity_file/commodity_page1.file"%> --%>
		<c:forEach var="commodityVO" items="${list}" >
		<c:if test="${commodityVO.member_id.equals(memberVO.member_id)}">
			<tr>
				<td>${commodityVO.com_id}</td>
				<td>${commodityVO.member_id}</td>
				<td style="width:150px;">${commodityVO.com_description}</td>
				<td><img class="com_pic" src="<%=request.getContextPath()%>/commodity/commoditypic.do?com_id=${commodityVO.com_id}"></td>
				<td><a href="${commodityVO.com_download}">Download</a></td>
				<td>${commodityVO.com_price}</td>
				<td style="width:150px;">${commodityVO.com_detail}</td>
				<td><fmt:formatDate value="${commodityVO.trandate}" pattern="yyyy-MM-dd  HH:mm"/></td>
				<td>${commodityVO.com_status}</td>
				<td>${commodityVO.e_score}</td>
				<td>${commodityVO.e_pol_statistics}</td>
				<td style="width:80px;">${commodityVO.e_des}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/commodity/commodity.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn  btn-secondary" value="�ק�"> <input type="hidden"
							name="com_id" value="${commodityVO.com_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/commodity/commodity.do"
						style="margin-bottom: 0px;">
						<input type="submit" class="btn  btn-primary"  value="�R��"> <input type="hidden"
							name="com_id" value="${commodityVO.com_id}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:if>
		</c:forEach>
	</table>
<%-- 		<%@ include file="/front-end/commodity/commodity_file/commodity_page2.file"%> --%>
</div>

	<%@ include file="/front-end/file/shop_footer.jsp"%>	
</body>
</html>