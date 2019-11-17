<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.card.model.*"%>

<%
	CardVO cardVO = (CardVO) request.getAttribute("cardVO"); //EmpServlet.java (Concroller) 存入req的cardVO物件 (包括幫忙取出的cardVO, 也包括輸入資料錯誤時的cardVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>單字資料修改 - update_card_input.jsp</title>

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
	<%@ include file="/front-end/file/card_header.jsp"%>	
  <div class="container justify-content-center">
  
	<div  style="display:flex; flex-direction:column; align-items:center;" >
	<h3>單字資料修改:</h3>
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/card/card.do" name="form1">
	<div class="form-group">
		<table>
			<tr>
				<td>單字編號:<font color=red><b>*</b></font></td>
				<td><%=cardVO.getCard_id()%></td>
			</tr>
			<tr>
				<td>單字:</td>
				<td><input  class="form-control" type="TEXT" name="word" size="45"
					value="<%=cardVO.getWord()%>" /></td>
			</tr>
			<tr>
				<td>教師編號:</td>
				<td><input  class="form-control" type="TEXT" name="teacher_id" size="45"
					value="<%=cardVO.getTeacher_id()%>" /></td>
			</tr>
			<tr>
				<td>音標:</td>
				<td><input  class="form-control" name="phonetic_symbol" id="" type="text"></td>
			</tr>
			<tr>
				<td>發音:</td>
				<td><input  class="form-control" type="TEXT" name="voice" size="45"
					value="<%=cardVO.getVoice()%>" /></td>
			</tr>
			<tr>
				<td>解釋:</td>
				<td><input class="form-control"  type="TEXT" name="native_explain" size="45"
					value="<%=cardVO.getNative_explain()%>" /></td>
			</tr>
			<tr>
				<td>例句:</td>
				<td><input class="form-control"  type="TEXT" name="example" size="45"
					value="<%=cardVO.getExample()%>" /></td>
			</tr>

			<jsp:useBean id="cardVO2" scope="page"
				class="com.card.model.CardService" />
			<tr>
				<td>XX編號:<font color=red><b>*</b></font></td>
				<td><select class="browser-default custom-select  col-6" size="1" name="teacher_id">
						<c:forEach var="cardVO" items="${cardSvc.all}">
							<option value="${cardVO.teacher_id}"
								${(cardVO.teacher_id==cardVO.teacher_id)? 'selected':'' }>${cardVO.teacher_id}
						</c:forEach>
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="card_id" value="<%=cardVO.getCard_id()%>">
			<div  style="display:flex; flex-direction:column; align-items:center;" >
			<input type="submit"  class="form-control col-2 btn btn-primary" value="送出修改">
			</div>
		</div>
	</FORM>
	
	
	</div>
	
	<%@ include file="/front-end/file/card_footer.jsp"%>		
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
 		   value: '<%//=cardVO.getHiredate()%>
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