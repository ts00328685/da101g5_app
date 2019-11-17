<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.Teacher.model.*"%>
<%@ page import="com.Course_order.model.*"%>
<%@ page import="com.Teacher_course_list.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); 
%>
<%
 Course_orderService course_orderSvc=new Course_orderService();
 List<Course_orderVO>list=course_orderSvc.getMemAll(memberVO.getMember_id());
 pageContext.setAttribute("list", list);
%>
<%
 TeacherService teacherSvc=new TeacherService();
 List<TeacherVO>teacherList=teacherSvc.getAll();
 pageContext.setAttribute("teacherList", teacherList);
%>
<%
 MemberService memberSvc=new MemberService();
 List<MemberVO>memList=memberSvc.getAll();
 pageContext.setAttribute("memList", memList);
%>
<jsp:useBean id="time_orderSvc" scope="page" class="com.Time_order.model.Time_orderService" />
<jsp:useBean id="teacher_course_listSvc" scope="page" class="com.Teacher_course_list.model.Teacher_course_listService" />
<jsp:useBean id="languageSvc" scope="page" class="com.Language.model.LanguageService" />
<jsp:useBean id="sort_courseSvc" scope="page" class="com.Sort_course.model.Sort_courseService" />


<script>
// ========================
	var currentDate = new Date();
  var mm = (currentDate.getMinutes()<10 ? '0' : '')+currentDate.getMinutes();
  var hh = (currentDate.getHours()<10 ? '0' : '')+currentDate.getHours();
  var day = (currentDate.getDate()<10 ? '0' : '')+(currentDate.getDate());
  var month = (currentDate.getMonth()+1<10 ? '0' : '')+(currentDate.getMonth() + 1);
  var year = currentDate.getFullYear();

// ========================
//對於日期(尚未成為事件)的開始和結束
var start;
var end;
var Today=new Date();

  document.addEventListener('DOMContentLoaded', function() {
     
	
    var localeSelectorEl = document.getElementById('locale-selector');
    var calendarEl = document.getElementById('calendar');
//     var Draggable = FullCalendarInteraction.Draggable;
//     var containerEl = document.getElementById('external-events-list');
//     new Draggable(containerEl, {
//         itemSelector: '.fc-event',
//         eventData: function(eventEl) {
//           return {
//             title: eventEl.innerText.trim()
//           }
//         }
//       });
    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid', 'timeGrid','bootstrap' ,'list' ],
      themeSystem: 'bootstrap',
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },
//       dateClick: function(info) {
//            alert('selected ' + info.startStr + ' to ' + info.endStr
//         		 );
//     	  $('.modal').modal('show');
          
//         },
//  select: function(info) {
//           alert('selected ' + info.startStr + ' to ' + info.endStr
//         		 );
//         },
        select: function(info) {
        	start=info.startStr;
        	end=info.endStr;
         	document.getElementById("start").value=' 開始時間: '+start;  	
         	document.getElementById("end").value=' 結束時間: '+end;  
        	
        	   	
   	  $('#memberCalendar').modal('show');
  
          
          
        },
      defaultView: "dayGridMonth",
      defaultDate: Today,
      //間隔分鐘
      allDay:false,
      slotMinutes:60,
      defaultEventMinutes:60,
      //日期格式
      theme: true,
      
      
      locale: 'UTC',
      
      // themeSystem='Cerulean',
      buttonIcons: true, // show the prev/next text
      weekNumbers: false,
      navLinks: true, // can click day/week names to navigate views
      editable: false,
      durationEditable:false,
//       droppable: true, // this allows things to be dropped onto the calendar
//       drop: function(arg) {
//             arg.draggedEl.parentNode.removeChild(arg.draggedEl);
//         	},
      eventLimit: true, // allow "more" link when too many events
      selectable: true,//是否允许用户通过单击或拖动选择日历中的对象，包括天和时间
      selectHelper: true,//当点击或拖动选择时间时，显示默认加载的提示信息，该属性只在周/天视图里可用。
//       eventRender: function(info) {
//           var tooltip = new Tooltip(info.el, {
//             title: info.event.extendedProps.description,
//             placement: "top",
//             trigger: "hover",
//             container: "body"
//           });
//         },

      
      events: [
<c:forEach var="time_orderVO" items="<%=time_orderSvc.getAllForMember(memberVO.getMember_id()) %>" >  
        {
        description:'description for Long Event',
        url: '<%=request.getContextPath()%>/time_order/time_order.do?action=getOne_For_Cancel&time_order_id=${time_orderVO.time_order_id}',
          start: '<fmt:formatDate value="${time_orderVO.start_time}" pattern="yyyy-MM-dd HH:mm"/>',
       	  end: '<fmt:formatDate value="${time_orderVO.end_time}" pattern="yyyy-MM-dd HH:mm"/>',
       	  
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
       	  title: 
       		<c:forEach var="teacherVO" items="${teacherList}">
	        <c:if test="${teacherVO.teacher_id==time_orderVO.teacher_id}">
	        <c:forEach var="memberVO" items="${memList}">
	        <c:if test="${teacherVO.member_id==memberVO.member_id}">
	        <c:forEach var="languageVO" items="${languageSvc.all}">
	        <c:forEach var="sort_courseVO" items="${sort_courseSvc.all}">
	        <c:if test="${time_orderVO.sort_course_id==sort_courseVO.sort_course_id&&time_orderVO.language_id==languageVO.language_id}">
        '${memberVO.mem_nick} ${languageVO.language}${sort_courseVO.sort_course}',
	        </c:if>
	        </c:forEach>
	        </c:forEach>
	        </c:if>
	        </c:forEach>
	        </c:if>
	        </c:forEach>
	      
	        
	        
        },
</c:forEach> 
        {
            title: 'All I lost is my life.',
            
            start: '1970-01-01',
            end:year+'-'+month+'-'+day+'T'+hh+':'+mm,
            // rendering: 'background',
            color: 'rgba(255, 0, 0, 0.1)'
          }
      ],
      selectOverlap: function(event) {
          // alert(event.toString())
          // log(event.toString());
        
         
         return event.rendering === 'background';

        }
    });
 // -------------------------
//  document.addEventListener('DOMContentLoaded', function() {
//    var Calendar = FullCalendar.Calendar;
//    var Draggable = FullCalendarInteraction.Draggable

   /* initialize the external events
   -----------------------------------------------------------------*/

//    var containerEl = document.getElementById('external-events-list');
//    new Draggable(containerEl, {
//      itemSelector: '.fc-event',
//      eventData: function(eventEl) {
//        return {
//          title: eventEl.innerText.trim()
//        }
//      }
//    });


//--------------------------------   
    calendar.render();
    // build the locale selector's options
//     calendar.getAvailableLocaleCodes().forEach(function(localeCode) {
//       var optionEl = document.createElement('option');
//       optionEl.value = localeCode;
//       optionEl.selected = localeCode == initialLocaleCode;
//       optionEl.innerText = localeCode;
//       localeSelectorEl.appendChild(optionEl);
//     });

//     // when the selected option changes, dynamically change the calendar option
//     localeSelectorEl.addEventListener('change', function() {
//       if (this.value) {
//         calendar.setOption('locale', this.value);
//       }
//     });
  });
 
 
  
</script>



<html>
<head>
<%@ include file="/front-end/file/head.file"%>

<title>懶骨雞</title>

</head>
<body>
<%@ include file="/front-end/file/header.jsp"%>






<!-- <!-- 按鈕觸發模態框 --> 
<!-- <button class="btn btn-primary" data-toggle="modal" data-target="#myModal">	開始演示模態框 </button> -->
	
<!-- 模態框（Modal） -->
	
<div class="modal row" id="memberCalendar" role="dialog" style="padding-top:250px">
	
  <div class="modal-dialog row">
		
	<div class="modal-content row">
			
	  <div class="modal-header">
	  <h4 class="modal-title" id="myModalLabel">新增預約課程 </h4>
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times; </button>
		
	  </div>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/time_order/time_order.do" id="form1" enctype="multipart/form-data">
	  <div class="modal-body row" >  
	  <div style="padding-left:100px;">
	  <input style="border: none; width:400px;margin-bottom:10px;"name="start_time"id="start"readonly="readonly"><br><br>
	  
	  <input style="border: none; width:400px;margin-bottom:10px;"name="end_time"id="end"readonly="readonly"><br><br>
	  </div>
<!-- 	  <p name="start_time"id="start">開始時間</p> -->
<!-- 	  <p name="end_time"id="end">結束時間</p> -->
<!-- ==ajax=================================================== -->
	<div style="padding-left:30px;">
	老師：
	<select class="custom-select"name="teacher_id"  id="teacherSelected"style="width:100px;" >
	<option value="-1">請選擇</option>
	<c:forEach var="memberVO" items="${memList}">
	<c:forEach var="course_orderVO" items="${list}">
	<c:forEach var="teacherVO" items="${teacherList}">
	<c:if test="${teacherVO.teacher_id==course_orderVO.teacher_id}">
	<c:if test="${teacherVO.member_id==memberVO.member_id}">
		<option  value="${teacherVO.teacher_id}">${memberVO.mem_nick}</option>
	</c:if>
	</c:if>
	</c:forEach>
	</c:forEach>
	</c:forEach>
	</select>
	語言：
	<select class="custom-select"name="language_id" id="languageSelected"style="width:100px;">
		<option value="-1">請選擇</option>
	</select>
	課程:
	<select class="custom-select" name="sort_course" id="courseSelected" style="width:100px;">
		<option class="nice-select" value="-1">請選擇</option>
	</select>
	</div>
<!-- 	ajax================================= -->
<!-- ======select======================================================== -->
<!--  <select name="teacher_course_option">	   -->
<%-- 	  <c:forEach var="course_orderVO" items="${list}"> --%>



<%-- <c:forEach var="teacherVO" items="${teacherList}"> --%>
<%-- <c:if test="${teacherVO.teacher_id==course_orderVO.teacher_id}"> --%>
<%-- <c:forEach var="memberVO" items="${memList}"> --%>
<%-- <c:if test="${teacherVO.member_id==memberVO.member_id}"> --%>
	
<%--          <c:forEach var="teacher_course_listVO" items="${teacher_course_listSvc.all}" > --%>
<%--           <c:if test="${course_orderVO.teacher_id==teacher_course_listVO.teacher_id}"> --%>
          
<!-- 	          <option size="1" > -->
<%-- 	          <c:forEach var="languageVO" items="${languageSvc.all}"> --%>
<%-- 	          <c:forEach var="sort_courseVO" items="${sort_courseSvc.all}"> --%>
<%-- 	          <c:if test="${teacher_course_listVO.sort_course_id==sort_courseVO.sort_course_id --%>
<%-- 	          &&teacher_course_listVO.language_id==languageVO.language_id}"> --%>
	         
<%-- 	          ${memberVO.mem_nick}:${languageVO.language}-${sort_courseVO.sort_course} --%>
	         
<%-- 	          </c:if> --%>
<%-- 	          </c:forEach> --%>
<%-- 	          </c:forEach> --%>
<%-- </c:if> --%>
<%-- </c:forEach> --%>
<%-- </c:if> --%>
<%-- </c:forEach> --%>
	          
<%--            </c:if> --%>
<%--          </c:forEach>    --%>
 
<%-- </c:forEach> --%>
<!-- </select> -->
<!-- ================================================================================================= -->
	  </div>
			
 	
	  <div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		

		<input type="hidden" name="action" value="insert">
		<button onclick="checkSubmit()"type="submit" class="btn btn-primary"> 提交預約 </button>
<%-- 		<input type="hidden" name="teacher_id" value="${teacherVO.teacher_id}"> --%>
</FORM>		
	  </div>
			
	</div>
  </div>
</div>
              
                
<%-- <c:forEach var="course_orderVO" items="${list}"> --%>
<!-- <ul> -->
<%-- <c:forEach var="teacherVO" items="${teacherList}"> --%>
<%-- <c:if test="${teacherVO.teacher_id==course_orderVO.teacher_id}"> --%>
<%-- <c:forEach var="memberVO" items="${memList}"> --%>
<%-- <c:if test="${teacherVO.member_id==memberVO.member_id}">老師名字: ${memberVO.mem_nick}</c:if> --%>
<%-- </c:forEach> --%>
<%-- </c:if> --%>
<%-- </c:forEach> --%>
<%-- <div>剩餘時數: ${course_orderVO.remain_hour}</div> --%>
<%-- <div>重新預約次數: ${course_orderVO.re_appointment}</div> --%>

<!-- <div><b>選擇課程</b></div> -->
<!--  <select name="teacher_course_list"> -->

<%--          <c:forEach var="teacher_course_listVO" items="${teacher_course_listSvc.all}" > --%>


<%--           <c:if test="${course_orderVO.teacher_id==teacher_course_listVO.teacher_id}"> --%>
           
<!-- 	          <option size="1" > -->
<%-- 	          <c:forEach var="languageVO" items="${languageSvc.all}"> --%>
<%-- 	          <c:forEach var="sort_courseVO" items="${sort_courseSvc.all}"> --%>
<%-- 	          <c:if test="${teacher_course_listVO.sort_course_id==sort_courseVO.sort_course_id --%>
<%-- 	          &&teacher_course_listVO.language_id==languageVO.language_id}"> --%>
	         
<%-- 	          ${languageVO.language} ${sort_courseVO.sort_course} --%>
	         
<%-- 	          </c:if> --%>
<%-- 	          </c:forEach> --%>
<%-- 	          </c:forEach> --%>
	          
<%--            </c:if> --%>
<%--          </c:forEach>    --%>
<!--  </select> -->

<!-- </ul> -->
<!-- <br><br> -->
<%-- </c:forEach> --%>
<!-- </div> -->




<%-- <c:forEach var="time_orderVO" items="<%=time_orderSvc.getAllForMember(memberVO.getMember_id()) %>" > --%>
<!-- <lu> -->
<%-- <c:forEach var="teacherVO" items="${teacherList}"> --%>
<%-- <c:if test="${teacherVO.teacher_id==time_orderVO.teacher_id}"> --%>
<%-- <c:forEach var="memberVO" items="${memList}"> --%>
<%-- <c:if test="${teacherVO.member_id==memberVO.member_id}"><li>老師名字: <b>${memberVO.mem_nick}</b></li></c:if> --%>
<%-- </c:forEach> --%>
<%-- </c:if> --%>
<%-- </c:forEach> --%>
<%-- <li><fmt:formatDate value="${time_orderVO.start_time}" pattern="yyyy-MM-dd HH時"/></li> --%>
<%-- <li><fmt:formatDate value="${time_orderVO.end_time}" pattern="yyyy-MM-dd HH時"/></li>  --%>
<%-- 	<c:forEach var="languageVO" items="${languageSvc.all}"> --%>
<%-- 	<c:forEach var="sort_courseVO" items="${sort_courseSvc.all}"> --%>
<%-- 	<c:if test="${time_orderVO.sort_course_id==sort_courseVO.sort_course_id --%>
<%-- 	          &&time_orderVO.language_id==languageVO.language_id}"> --%>
	          
	          

	         
<%-- 	          <li>課程內容: ${languageVO.language} ${sort_courseVO.sort_course}</li>  --%>
	         
<%-- 	          </c:if> --%>
<%-- 	</c:forEach> --%>
<%-- 	</c:forEach> --%>
<!-- </lu> -->
<%-- </c:forEach> --%>



<!-- ---------------------------------------- -->
<div class="container-fluid row justify-content-center"style="padding-left:100px;padding-top:200px;">

<%-- <%-- 錯誤表列 --%> 
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

<!-- ----------------------------------------------- -->

<div class="col-2" style="padding-top:4px;padding-right:-20px">

<div><h4>老師尚未確認 : <b style="color:orange;">橘色</b></h4></div>
<div><h4>老師已經確認 : <b style="color:green;">綠色</b></h4></div>
<div><h4>課堂正在進行 : <b style="color:red;">紅色</b></h4></div>
<div><h4>課堂已經結束 : <b style="color:blue;">藍色</b></h4></div>
<br><br>
<c:forEach var="course_orderVO" items="${list}">
<ul style="font-size:25px;font-family:標楷體;padding-inline-start: 0px;">
<c:forEach var="teacherVO" items="${teacherList}">
<c:if test="${teacherVO.teacher_id==course_orderVO.teacher_id}">
<c:forEach var="memberVO" items="${memList}">
<c:if test="${teacherVO.member_id==memberVO.member_id}">老師名字: ${memberVO.mem_nick}</c:if>
</c:forEach>
</c:if>
</c:forEach>
<div>剩餘時數: ${course_orderVO.remain_hour}</div>
<div>重新預約次數: ${course_orderVO.re_appointment}</div>
</ul>
<br><br>
</c:forEach>

</div>



  <div id='calendar' style="width:900px;"></div>
  <br><br><br>
</div>
      



<br><br><br>
<%@ include file="/front-end/file/footer.file"%>
<script>
// function checkSubmit(){
// 	var teacher_id01=document.getelementbyid("teacherSelected").value	
// 	var language_id01=document.getelementbyid("languageSelected").value
// 	var course_id01=document.getelementbyid("courseSelected").value
// 	if(teacher_id01=='null'){
// 		swal('請選擇老師');
// 		return false;
// 	}
// 	if(language_id01=='null'){
// 			swal('請選擇語言');
// 			return false;
// 	}
// 	if(course_id01=='null'){
// 		swal('請選擇課程');
// 		return false;
// 	}
// }


</script>

<!-- ==========================Ajax============================= -->
<script>


// ----Ajax----------------------------------------------------
$(document).ready(function(){
		 $('#teacherSelected').change(function(){
			 $.ajax({
				 type: "GET",
				 url: "<%=request.getContextPath()%>/time_order/time_order.do",
				 data: creatQueryString($(this).val(), ""),
				 dataType: "json",
				 success: function (data){
					
					 clearSelect();
					 
					$.each(data, function(i, item){
						
// 						var s = document.getElementById('languageSelected');
// 						var new_option = new Option(item.language,item.language_id);
// 						s.options.add(new_option);
// 						$('#languageSelected').append(new Option('Foo', 'foo', true, true));
// 						$('#languageSelected').append($("<option></option>").attr("value", item.language_id).text(item.language));
						if( typeof(item.language)=== 'string'){
						var str="";
						str+="<option  value='"+item.language_id+"' >"+item.language+"</option>";
						$('#languageSelected').append(str);
						}
// alert(typeof(item.language)=== 'string' );
 						
					});
//   					$(data).each(function(i, item){ 
  						
  						
//   						$('#languageSelected').append("<option value='"+item.language_id+"'>"+item.language+"</option>"); 
//   						
//   					}); 
//   					jQuery.each(data, function(i, item){ -->
//   						$('#languageSelected').append("<option value='"+item.language_id+"'>"+item.language+"</option>"); 
//   					}); 
			     },
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
	         })
		 })
		 $('#languageSelected').change(function(){
			$.ajax({
				 type: "POST",
				 url: "<%=request.getContextPath()%>/time_order/time_order.do",
				 data: creatQueryString($('#teacherSelected').val(), $(this).val()),
				 dataType: "json",
				 success: function (data){
					 clearSelect2();
					 $.each(data, function(i, item){
						 $('#courseSelected').append("<option value='"+data[i].course_id+"'>"+data[i].course+"</option>");
					 });
			     },
	            error: function(){alert("AJAX-class發生錯誤囉!")}
	        })
		})
	})
	
	function creatQueryString(teacher_id, language_id){
		console.log("teacher_id:"+teacher_id+"; language_id:"+language_id);
		var queryString= {"action":"getSelect", "teacher_id":teacher_id, "language_id":language_id};
		return queryString;
	}
	function clearSelect(){
		$('#languageSelected').empty();
		$('#languageSelected').append("<option value='-1'>請選擇</option>");
		$('#courseSelected').empty();
		$('#courseSelected').append("<option value='-1'>請選擇</option>");
	}
	function clearSelect2(){
		$('#courseSelected').empty();
		$('#courseSelected').append("<option value='-1'>請選擇</option>");
	}
</script>
</body>
</html>