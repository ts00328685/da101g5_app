<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Time_order.model.*"%>

<%
Time_orderVO time_orderVO = (Time_orderVO) request.getAttribute("time_orderVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Time_order資料新增 - addTime_order.jsp</title>

<style>
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
	width: 450px;
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

<table id="table-1">
	<tr><td>
		 <h3>Time_order資料新增 - addTime_order.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/front-end/time_order/select_page.jsp">
		 <img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="time_order.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>teacher_id:</td>
		<td><input type="TEXT" name="teacher_id" size="45" 
			 value="<%= (time_orderVO==null)? "T00001" : time_orderVO.getTeacher_id()%>" /></td>
	</tr>
	<tr>
		<td>course_order_id:</td>
		<td><input type="TEXT" name="course_order_id" size="45"
			 value="<%= (time_orderVO==null)? "CO00001" : time_orderVO.getCourse_order_id()%>" /></td>
	</tr>
	
	<tr>
		<td>language_id:</td>
		<td><input type="TEXT" name="language_id" size="45"
			 value="<%= (time_orderVO==null)? "L00001" : time_orderVO.getLanguage_id()%>" /></td>
	</tr>
	<tr>
		<td>sort_course_id:</td>
		<td><input type="TEXT" name="sort_course_id" size="45"
			 value="<%= (time_orderVO==null)? "SC00001" : time_orderVO.getSort_course_id()%>" /></td>
	</tr>
	<tr>
		<td>c_state:</td>
		<td><input type="TEXT" name="c_state" size="45"
			 value="<%= (time_orderVO==null)? "0" : time_orderVO.getC_state()%>" /></td>
	</tr>
	<tr>
		<td>c_judge:</td>
		<td><input type="TEXT" name="c_judge" size="45"
			 value="<%= (time_orderVO==null)? "5" : time_orderVO.getC_judge()%>" /></td>
	</tr>
	
	<tr>
		<td> start_time:</td>
		<td><input name=" start_time" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td> end_time:</td>
		<td><input name="end_time" id="f_date2" type="text"></td>
	</tr>

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(time_orderVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
	java.sql.Timestamp start_time = null;
  try {
	  start_time = time_orderVO.getStart_time();
   } catch (Exception e) {
	   java.sql.Timestamp  start_time1 = null;
	    start_time1 = new java.sql.Timestamp(System.currentTimeMillis());
	    start_time=new java.sql.Timestamp(start_time1.getYear(),start_time1.getMonth(),start_time1.getDate(),start_time1.getHours()+12,0,0,0);
  }
%>

<% 
	java.sql.Timestamp end_time = null;
  try {
	  end_time = time_orderVO.getStart_time();
   } catch (Exception e) {
	   java.sql.Timestamp  end_time1 = null;
	   end_time1 = new java.sql.Timestamp(System.currentTimeMillis());
	    end_time=new java.sql.Timestamp(end_time1.getYear(),end_time1.getMonth(),end_time1.getDate(),end_time1.getHours()+12,0,0,0);
  }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.full.js"></script>

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
	       timepicker:true,       //timepicker:true,
	       step:60,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=start_time%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=end_time%>', // value:   new Date(),
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
//         		            (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() == somedate1.getDate()&& date.getHours() < somedate1.getHours())
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