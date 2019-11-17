<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.point_trans.model.*"%>
<%@ page import="com.member.model.*"%>
<%
MemberVO memberVO =(MemberVO) session.getAttribute("memberVO");
Point_transVO point_transVO2 = (Point_transVO) request.getAttribute("point_transVO");


Point_transService point_transSvc = new Point_transService();
List<Point_transVO> list = point_transSvc.getAll2(memberVO.getMember_id());
pageContext.setAttribute("list",list);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<%@ include file="/front-end/file/head.file"%>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<style>
	body {
		font-family: 微軟正黑體;
		
		}
	
</style>

 
</head>
<body>
<!--================ Start Header Menu Area =================-->
   <%@ include file="/front-end/file/header.jsp"%>
    <!--================ End Header Menu Area =================-->
<!-- ================================================================== -->
<div class="container" style="padding-top:200px;">
	<div><img src="<%=request.getContextPath()%>/front-end/point_trans/images/買.png" width="150px"/>
							</div>
		<h1 class="row">交易紀錄</h1>
	


<div class="table-responsive">
  <table class="table">
   
    <thead>
      <tr>
        <th class="table-warning">點數紀錄</th>
		<th class="table-warning">交易日期</th>
		</tr>
    </thead>
    <tbody>
    <%@ include file="point_trans_file/point_trans_page1.file" %> 
	<%MemberService MemberSvc=new MemberService();
	pageContext.setAttribute("MemberSvc", MemberSvc); %>

	<c:forEach var="point_transVO2" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">>
      <tr>
        <td>${point_transVO2.point_record}</td>
		<td>${point_transVO2.transdate}</td>
		</tr>
     
        </c:forEach>
    </tbody>
  </table>
</div>



<%@ include file="point_trans_file/point_trans_page2.file" %>
<br><br>	

		<div class="container">
			<div class="row">

				<div class="col-12">
					<div class="card">
						<div class="card-body">

							<font size="5"
								style="display: flex; align-items: center; justify-content: center; height: 100%;"><h1>儲值點數</h1></font>
							<hr>
							<div style="text-align: center">請輸入您的信用卡卡號</div>
							<br>
							<div class="form-group form-row">
								<div class="col-sm-3">
									<input type="text" id="f1" class="form-control"
										onkeydown="change(1)">
								</div>
								<p>_</p>
								<div class="col">
									<input type="text" id="f2" class="form-control" maxlength="4">
								</div>
								<p>_</p>
								<div class="col">
									<input type="text" id="f3" class="form-control" maxlength="4">
								</div>
								<p>_</p>
								<div class="col">
									<input type="text" id="f4" class="form-control" maxlength="4">
								</div>
							</div>

							卡片有效期限<select style="margin-left: 12px" name="Select_Month"
								id="Select_Month"></select>月
							<script>
								var Select_Month = document
										.getElementById("Select_Month")
								var Month_Start = 1 //起始月 
								var Month_End = 12 //結束月 
								var Month_Len = Month_End - Month_Start + 1;
								Select_Month.options.length = Month_Len;
								for (var i = 0; i < Month_Len; i++) {
									Select_Month.options[i].text = Select_Month.options[i].value = Month_Start
											+ i;
								}
							</script>

							<select name="Select_Year" id="Select_Year"></select>年
							<script>
								var Select_Year = document
										.getElementById("Select_Year")
								var Year_Start = 2019 //起始年 
								var Year_End = 2030 //結束年 
								var Year_Len = Year_End - Year_Start + 1;
								Select_Year.options.length = Year_Len;
								for (var i = 0; i < Year_Len; i++) {
									Select_Year.options[i].text = Select_Year.options[i].value = Year_Start
											+ i;
								}
							</script>
							<br> <br>
							<div>
								<ul class="pagination justify-content-left">
									信用卡驗証碼
									<input name="depositValue" class="title"
										placeholder="請輸入您的信用卡驗証碼" maxlength="3"
										style="margin-left: 12px" id="create">
								</ul>
							</div>
						<form action="<%=request.getContextPath()%>/point_trans/point_trans.do" name="form1"
							enctype="mulpart/form-data" method="post">	
							<font size= "5" color="blue">請選擇您要儲值的點數金額：</font> 
							<label class="radio-inline">
							<input type="radio" name="point_record" value="500" id="a"/><label for="a"> 500點   </label>
							<input type="radio" name="point_record" value="1000" id="b"/><label for="b">1000點  </label>
							<input type="radio" name="point_record" value="3000" id="c"/><label for="c">3000點  </label>
							<input type="radio" name="point_record" value="5000" id="d"/><label for="d">5000點  </label>
							<input type="radio" name="point_record" value="10000" id="e"/><label for="e">10000點</label>
							</label><div>
							
							<input type="hidden" name="member_id" size="45" 
<%-- 								 value="<%= (point_transVO2==null)? "M00001" : point_transVO2.getMember_id()%>" /> --%>
							 value="<%=memberVO.getMember_id()%>" />
<!-- 							<input name="transdate" id="f_date1" type="text"> -->
						</div>
						</div>
					</div>

					<br>
					
					
					
					
					
					<div class="col-12">
						<ul class="pagination justify-content-center">
							<input type="hidden" name="action" value="insert">
							<input class="page-link" type="submit" value="送出金額">
							<span><input type="button" value="" id="c1"></span>
						</ul>
					</div>

				</div>
			</div>
		</div>

	</form>

	<script>
		function change(id1) {
			if ($("#f" + id1).val().length == 4) {
				changeNext(id1 + 1);
			}
		}

		function changeNext(id2) {
			var v = "#f" + id2;
			$(v).focus();
			$(v).keyup(function focus2() {
				if ($("#f" + id2).val().length == 4) {
					changeNext(id2 + 1);
				}
			});
			if ($("#f" + 4).val().length == 4) {
				alert("輸入完成");
			}
		}
	</script>
	
	
	
	
<!-- =================================================================================================================== -->
<!--================ Start footer Area  =================-->
   <%@ include file="/front-end/file/footer.file"%>
    <!--================ End footer Area  =================-->
<% 
  java.sql.Date transdate = null;
  try {
	  transdate = point_transVO2.getTransdate();
   } catch (Exception e) {
	   transdate = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=transdate%>', // value:   new Date(),
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

 <script>
$(document).ready(function(){
	$('#c1').click(function(){
		$("#f1").val("4581");
		$("#f2").val("2635");
		$("#f3").val("5457");
		$("#f4").val("8624");
		$("#f_date1").val("1990-08-01");
		$("#Select_Month").val("8");
		$("#Select_Year").val("2022");
		$("#create").val("230");
		$("#e").prop("checked", true);
	});
});       
</script>  
<!-- -*-------------引入頁首頁尾------------------- -->
  		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>
          

  	 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
	<!-- <td class="table-warning">${MemberSvc.getOneMember(point_transVO.member_id).mem_name}</td> -->
</body>
</html>