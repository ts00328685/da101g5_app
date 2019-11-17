<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.Teacher.model.*"%>
<%@ page import="com.Course_order.model.*"%>
<%@ page import="com.Teacher_course_list.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="languageSvc" scope="page" class="com.Language.model.LanguageService" />
<jsp:useBean id="sort_courseSvc" scope="page" class="com.Sort_course.model.Sort_courseService" />
<jsp:useBean id="time_orderSvc" scope="page" class="com.Time_order.model.Time_orderService" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="course_orderSvc" scope="page" class="com.Course_order.model.Course_orderService" />
<%
TeacherVO teacherVO = (TeacherVO) session.getAttribute("teacherVO"); 
%>

<html>
<head>
<style>
.fc-time-grid-event .fc-time, .fc-time-grid-event .fc-title {
    padding: 0 1px;
    COLOR: #FFFF;
}
</style>
<!-- ---------------------------------- -->
<script>
var Today=new Date();
//對於事件的開始和結束
var start;
var end;
var id;
  document.addEventListener('DOMContentLoaded', function() {
    // var initialLocaleCode = 'zh-tw';

    var localeSelectorEl = document.getElementById('locale-selector');
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction',  'timeGrid','dayGrid','bootstrap' ,'list' ],
//      --------註冊button------------------------- 
     eventClick: function(info) {
    	 
    	 start=info.event.start;
    	 end=info.event.end;
    	 id=info.event.id;
    	 document.getElementById("start").value=' 開始時間: '+start;  	
      	document.getElementById("end").value=' 結束時間: '+end; 
      	document.getElementById("id").value=id; 
      	document.getElementById("id1").value=id;
      	document.getElementById("id2").value=id;
// 	    alert('Event: ' + info.event.title);
// 	    alert('Coordinates: ' + info.jsEvent.pageX + ',' + info.jsEvent.pageY);
// 	    alert('View: ' + info.event.start);
	    $('#teacherCalendar').modal('show');
	    // change the border color just for fun
	    info.el.style.borderColor = 'red';
	  },

      
//       ----------------------------
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },
      defaultDate: Today,
      themeSystem: 'bootstrap',
      theme: true,
       themeSystem: 'cosmo',
      locale: 'US',
      
      // themeSystem='Cerulean',
      buttonIcons: true, // show the prev/next text
      weekNumbers: false,
      navLinks: true, // can click day/week names to navigate views
      editable: false,
      eventLimit: true, // allow "more" link when too many events
      events: [
    	  <c:forEach var="time_orderVO" items="<%=time_orderSvc.getAllForTeacher(teacherVO.getTeacher_id()) %>">
    	  {		
    		  id:'${time_orderVO.time_order_id}',
<%--     		  url: '<%=request.getContextPath()%>/front-end/time_order/time_order.do?action=getOne_For_Cancle&time_order_id=${time_orderVO.time_order_id}', --%>
        	  start:'<fmt:formatDate value="${time_orderVO.start_time}" pattern="yyyy-MM-dd HH:mm"/>',
        	  end:'<fmt:formatDate value="${time_orderVO.end_time}" pattern="yyyy-MM-dd HH:mm"/>',
// ------------------------------	 對事件狀態以顏色判斷 -------------------
       	<c:if test="${time_orderVO.c_state==0}">
       		color: 'orange',
       	 </c:if>
       	
       		<c:if test="${time_orderVO.c_state==1}">
   				color: 'green',
   	 		</c:if>
       
    <c:if test="${time_orderVO.c_state==2}">
		color: 'blue',
	</c:if>
    <c:if test="${time_orderVO.c_state==3}">
	color: 'red',
</c:if>
//   ----------------------------------------  
        	  title:<c:forEach var="course_orderVO" items="${course_orderSvc.all}">
          	  	<c:if test="${course_orderVO.course_order_id==time_orderVO.course_order_id}">
				<c:forEach var="memberVO" items="${memberSvc.all}">
          	  	<c:if test="${memberVO.member_id==course_orderVO.member_id}">
<c:forEach var="languageVO" items="${languageSvc.all}">
<c:forEach var="sort_courseVO" items="${sort_courseSvc.all}">
<c:if test="${time_orderVO.sort_course_id==sort_courseVO.sort_course_id&&time_orderVO.language_id==languageVO.language_id}">
          	  	'${memberVO.mem_nick} ${languageVO.language}${sort_courseVO.sort_course}'
          	  	
</c:if>
</c:forEach>
</c:forEach>
          	  	</c:if>
          	  	</c:forEach>
          	  	</c:if>
          	  	</c:forEach>
          	  	
        	  },
    	  </c:forEach>
        	  

       
      ]
    });

    
    calendar.render();

    


    // build the locale selector's options
//     calendar.getAvailableLocaleCodes().forEach(function(localeCode) {
//       var optionEl = document.createElement('option');
//       optionEl.value = localeCode;
//       optionEl.selected = localeCode == initialLocaleCode;
//       optionEl.innerText = localeCode;
//       localeSelectorEl.appendChild(optionEl);
//     });

    // when the selected option changes, dynamically change the calendar option
//     localeSelectorEl.addEventListener('change', function() {
//       if (this.value) {
//         calendar.setOption('locale', this.value);
//       }
//     });
  });
</script>
<%@ include file="/front-end/file/head.file"%>
<meta charset="UTF-8">
<title>懶骨雞</title>
</head>
<body>
<%@ include file="/front-end/file/header.jsp"%>
<!-- <div class="container" style="padding-top:200px;"> -->

<!-- <ul> -->
<%-- <c:forEach var="time_orderVO" items="<%=time_orderSvc.getAllForTeacher(teacherVO.getTeacher_id()) %>"> --%>

<%-- <li><fmt:formatDate value="${time_orderVO.start_time}" pattern="yyyy-MM-dd HH:mm"/></li> --%>
<%-- <li><fmt:formatDate value="${time_orderVO.end_time}" pattern="yyyy-MM-dd HH:mm"/></li>  --%>
<!-- 		<li> -->
<%-- 			<c:forEach var="course_orderVO" items="${course_orderSvc.all}"> --%>
<%-- 			<c:if test="${course_orderVO.course_order_id==time_orderVO.course_order_id}"> --%>
<%-- 			<c:forEach var="memberVO" items="${memberSvc.all}"> --%>
<%-- 			<c:if test="${memberVO.member_id==course_orderVO.member_id}">${memberVO.mem_nick}</c:if> --%>
<%-- 			</c:forEach> --%>
<%-- 			</c:if> --%>
<%-- 			</c:forEach> --%>
<!-- 		</li> -->
<%-- 	<c:forEach var="languageVO" items="${languageSvc.all}"> --%>
<%-- 	<c:forEach var="sort_courseVO" items="${sort_courseSvc.all}"> --%>
<%-- 	<c:if test="${time_orderVO.sort_course_id==sort_courseVO.sort_course_id --%>
<%-- 	          &&time_orderVO.language_id==languageVO.language_id}"> --%>
<%-- 	          <li>課程內容: ${languageVO.language} ${sort_courseVO.sort_course}</li>  --%>
<%-- 	          </c:if> --%>
<%-- 	</c:forEach> --%>
<%-- 	</c:forEach> --%>
<!-- 	<br> -->
<%-- 	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/time_order/time_order.do" name="form1" enctype="multipart/form-data"> --%>
<%-- 	<input type="hidden" name="time_order_id" value="${time_orderVO.time_order_id}"> --%>
<!-- 	<input type="hidden" name="action" value="getOne_For_Cancle"> -->
<!-- 	<button>取消預約</button> -->
<!-- 	</FORM> -->
<%-- </c:forEach> --%>
<!-- </ul> -->
<!-- </div> -->

<!-- -------------老師調整課程開始---------------------- -->
<div class="modal" id="teacherCalendar" role="dialog" style="padding-top:250px">
	
  <div class="modal-dialog">
		
	<div class="modal-content">
			
	  <div class="modal-header">
	  <h4 class="modal-title" id="myModalLabel">課程安排 </h4>
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times; </button>
		
	  </div>

	  <div class="modal-body row" style="padding-left:50px;">  

<!-- 	  <p name="start_time"id="start">開始時間</p> -->
<!-- 	  <p name="end_time"id="end">結束時間</p> -->

<!-- ----------------------抓會員名字----------------------- -->
<%--   <c:forEach var="time_orderVO" items="<%=time_orderSvc.getAllForTeacher(teacherVO.getTeacher_id()) %>"> --%>
<%--  <c:forEach var="course_orderVO" items="${course_orderSvc.all}"> --%>
<%-- <c:if test="${course_orderVO.course_order_id==time_orderVO.course_order_id}"> --%>
<%-- <c:forEach var="memberVO" items="${memberSvc.all}"> --%>
<%-- <c:if test="${memberVO.member_id==course_orderVO.member_id}"> --%>

<%-- <input style="width:300px;margin-bottom:20px;"name="mem_nick"readonly="readonly" value="${memberVO.mem_nick}"><br><br> --%>

<%-- </c:if> --%>
<%-- </c:forEach> --%>
<%-- </c:if> --%>
<%-- </c:forEach> --%>
<%--  </c:forEach> --%>
 
<!--  -------------------抓會員名字----------------------- -->
<p>您的決定將會影響學員們對你的信賴。</p>



	  </div>
			
 	
	  <div class="modal-footer" style="height:80px;">
<!-- 	  btn-primary -->
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/time_order/time_order.do" name="form1" enctype="multipart/form-data">
	  <input type="hidden" name="start_time"id="start"readonly="readonly"><br><br>
	  <input type="hidden" name="end_time"id="end"readonly="readonly"><br><br>
	  <input type="hidden" name="time_order_id"id="id"readonly="readonly">

		<input type="hidden" name="action" value="getOne_For_Cancel">
		<button  class="btn btn-secondary"type="submit" class="btn btn-default" style=" position: relative;top: -50px;">取消預約</button>
</FORM>	
<!-- -------------------第二個submit-------		 -->
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/time_order/time_order.do" name="form2" enctype="multipart/form-data">
	   
	 
	  <input type="hidden" style="width:450px;margin-bottom:20px;"name="time_order_id"id="id1"readonly="readonly">	
		
		<input type="hidden" name="action" value="commit">
		<button type="submit" class="btn btn-primary"> 確認預約 </button>	
		
<%-- 		<input type="hidden" name="teacher_id" value="${teacherVO.teacher_id}"> --%>
</FORM>	
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/time_order/time_order.do" name="form2" enctype="multipart/form-data">
	   
	 
	  <input type="hidden" style="width:450px;margin-bottom:20px;"name="time_order_id"id="id2"readonly="readonly">	
		
		<input type="hidden" name="action" value="liveShow">
<button type="submit" class="btn btn-warning">開始直播</button>
</FORM>		
	  </div>
			
	</div>
  </div>
</div>

<!-- -----------------老師調整課程結束------------------- -->
<div class="container" style="padding-left:100px;padding-top:200px;">
<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- <div class="alert alert-primary col-3" role="alert"> -->
<!-- <a href="#" class="close" data-dismiss="alert"></a> -->
<!-- 	<font style="color:red">提示訊息:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<!-- </div>	 -->
<%-- </c:if> --%>
  <div id='calendar'></div>
  <br><br><br>
</div>

<%@ include file="/front-end/file/footer.file"%>

<script type="text/javascript">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">				

swal( <c:forEach var="message" items="${errorMsgs}"> 
	"${message}"
</c:forEach> 

);
</c:if>	

</script>
</body>
</html>