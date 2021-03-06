<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.commodity.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	CommodityVO commodityVO = (CommodityVO) request.getAttribute("commodityVO"); //EmpServlet.java (Concroller) 存入req的commodityVO物件 (包括幫忙取出的commodityVO, 也包括輸入資料錯誤時的commodityVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品資料修改 - update_commodity_input.jsp</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/bootstrap/css/bootstrap.css" />
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
</style>

<style>
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
  
	<div  style="display:flex; flex-direction:column; align-items:center;" >
	<h3>商品資料修改:</h3>
	</div>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/commodity/commodity.do" name="form1"  enctype="multipart/form-data">
	<div class="form-group">
		<table>
			<tr>
				<td>商品編號:<font color=red><b>*</b></font></td>
				<td><%=commodityVO.getCom_id()%></td>
			</tr>
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
							value="<%=((MemberVO) session.getAttribute("memberVO")).getMember_id()%>" />
							</td>
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
					

						<td>商品內容:</td>
						<td><input class="form-control" type="TEXT"
							name="com_detail" size="45"
							value="<%=(commodityVO == null) ? "100" : commodityVO.getCom_detail()%>" /></td>
					</tr>


					<jsp:useBean id="commodityVO2" scope="page"
						class="com.commodity.model.CommodityService" />
					

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="com_id" value="<%=commodityVO.getCom_id()%>">
			<div  style="display:flex; flex-direction:column; align-items:center;" >
			<input type="submit"  class="form-control col-2 btn btn-primary" value="送出修改">
			</div>
		</div>
	</FORM>
	
	
	</div>
	
<%@ include file="/front-end/file/shop_footer.jsp"%>  
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%//=commodityVO.getHiredate()%>
	', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>