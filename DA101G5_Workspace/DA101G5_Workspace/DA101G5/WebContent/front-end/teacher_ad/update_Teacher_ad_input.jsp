<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Teacher_ad.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
  Teacher_adVO teacher_adVO = (Teacher_adVO) session.getAttribute("teacher_adVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>懶骨雞</title>

<style>

</style>
<%@ include file="/front-end/file/head.file"%>
</head>
<body>

<%@ include file="/front-end/file/header.jsp"%>

<div style="padding-top:80px; ">
<!--================Home Banner Area =================-->
    <section class="banner_area">
      <div class="banner_inner d-flex align-items-center"style="height:100px">
        <div class="overlay"></div>
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-6">
              <div class="banner_content text-center"  >
                <h2 >廣告買下去</h2>
                <h2>曝光打開來</h2>
                <div class="page_link">
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--================End Home Banner Area =================-->

</div>
<div class="container-fluid row " style="padding-top:80px;">

 
<div class="col-2"></div>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
<div class="col-4">
<a href="<%=request.getContextPath()%>/front-end/teacher/update_teacher_input.jsp"><h1>回到老師修改資料</h1></a>
<br><p style="color:green;font-size:20px">每天100點數</p>
<FORM METHOD="post" ACTION="teacher_ad.do" name="form1" enctype="multipart/form-data">

<table class=""style="border-collapse:separate; border-spacing:0px 10px;">
<h3>廣告購買:</h3>

<!-- 	<tr> -->
<!-- 		<td>教師廣告編號:</td> -->
<%-- 		<td><input type="TEXT" name="teacher_ad_id" size="45"	value="<%=teacher_adVO.getTeacher_ad_id()%>" /></td> --%>
<!-- <!-- 		<td>教師廣告編號:<font color=red><b>*</b></font></td> -->
<%-- <%-- 		<td><%=teacher_adVO.getTeacher_ad_id()%></td> --%>
<!-- 	</tr> -->
<%-- 	<jsp:useBean id="teacherSvc" scope="page" class="com.Teacher.model.TeacherService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>教師編號:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="teacher_id"> -->
<%-- 			<c:forEach var="teacherVO" items="${teacherSvc.all}"> --%>
<%-- 				<option value="${teacherVO.teacher_id}" ${(teacher_adVO.teacher_id==teacherVO.teacher_id)?'selected':'' } >${teacherVO.teacher_id} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->
	
	<tr >
		<td style="font-size:20px">開始時間:</td>
		<td style="font-size:20px"><input name="ad_start" id="f_date1" type="text" ></td>
	</tr>
	
	<tr>
		<td style="font-size:20px">購買天數:</td>
		<td style="font-size:20px"><input min="1"type="number" name="ad_time" size="45"	value="1" /></td>
	</tr>
	
<!-- 	<tr> -->
<!-- 		<td>廣告狀態:</td> -->
<%-- 		<td><input type="TEXT" name="ad_state" size="45"	value="<%=teacher_adVO.getAd_state()%>" /></td> --%>
<!-- 	</tr> -->
</table >
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="teacher_id" value="<%=teacher_adVO.getTeacher_ad_id()%>">
<input  class="btn btn-primary"type="submit" value="送出訂單">
</FORM>

<script>
// onclick="purchase()"
// function purchase(){
// 	swal("下個發大財的就是你!!!");
// }
</script>
</div>



<div style="padding-top:130px">
<h3>現有廣告:</h3>
<table class="" style="border-collapse:separate; border-spacing:0px 10px;">
<!-- 	<tr> -->
<!-- 		<th>教師廣告編號</th> -->
<!-- 		<th>教師編號</th> -->
<!-- 		<th>開始時間</th> -->
<!-- 		<th></th> -->
<!-- 		<th>廣告狀態</th> -->
		
<!-- 	</tr> -->
	<tr>
	
<%-- 			<td>${teacher_adVO.teacher_ad_id}</td> --%>
<%-- 			<td>${teacher_adVO.teacher_id}</td> --%>
			<td style="font-size:20px">開始時間: <fmt:formatDate value="${teacher_adVO.ad_start}" pattern="yyyy年-MM月-dd日"/></td>
		
			
			
<%-- 			<td>${teacher_adVO.ad_time}</td> --%>
<!-- 			<td id="end_time"></td> -->
<%-- 		<td><%=teacher_adVO.getTeacher_ad_id()%></td> --%>
<%-- 		<td><%=teacher_adVO.getTeacher_id()%></td> --%>
<%-- 		<td><%=teacher_adVO.getAd_time()%></td> --%>
<%-- 		<td><%=teacher_adVO.getAd_start()%></td> --%>
<%-- 		<td><%=teacher_adVO.getAd_state()%></td> --%>

	</tr>
	<tr>
	<td style="font-size:20px">購買天數: ${teacher_adVO.ad_time}</td>
	</tr>
	<tr>
		<td id="end_time"style="font-size:20px"> </td>
	</tr>
</table>
<br><br><br><br><br><br><br><br><br><br>
</div>
</div>
<%@ include file="/front-end/file/footer.file"%>
</body>

<script type="text/javascript">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">				

swal( <c:forEach var="message" items="${errorMsgs}"> 
	"${message}"
</c:forEach> 

);
</c:if>	

</script>




<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<!-- <script> -->
<%--  var end=new Date(${teacher_adVO.ad_start}); --%>
<!--  document.getElementById('end_time').innerHTML=end; -->
<!-- </script> -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.full.js"></script>
<!-- -------結束時間------------------------------ -->
<script>
var end_time=(new Date('${teacher_adVO.ad_start}')).getTime()+'${teacher_adVO.ad_time}'*60*60*1000*24;

da = new Date(end_time);
var year = da.getFullYear()+'年';
var month = da.getMonth()+1+'月';
var date = da.getDate()+'日';
console.log([year,month,date].join('-'));
document.getElementById("end_time").innerHTML='結束時間: '+[year,month,date].join('-');
</script>	
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
 		   value: new Date() // value:   new Date(),
<%--  		  '<%=teacher_adVO.getAd_start()%>' --%>
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
             var somedate1 = new Date();
             $('#f_date1').datetimepicker({
                 beforeShowDay: function(date) {
               	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});

        
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