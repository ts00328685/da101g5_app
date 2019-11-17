<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.Teacher_ad.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
  Teacher_adVO teacher_adVO = (Teacher_adVO) session.getAttribute("teacher_adVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�i����</title>

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
                <h2 >�s�i�R�U�h</h2>
                <h2>�n�����}��</h2>
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

<%-- ���~��C --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">�Эץ��H�U���~:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>
<div class="col-4">
<a href="<%=request.getContextPath()%>/front-end/teacher/update_teacher_input.jsp"><h1>�^��Ѯv�ק���</h1></a>
<br><p style="color:green;font-size:20px">�C��100�I��</p>
<FORM METHOD="post" ACTION="teacher_ad.do" name="form1" enctype="multipart/form-data">

<table class=""style="border-collapse:separate; border-spacing:0px 10px;">
<h3>�s�i�ʶR:</h3>

<!-- 	<tr> -->
<!-- 		<td>�Юv�s�i�s��:</td> -->
<%-- 		<td><input type="TEXT" name="teacher_ad_id" size="45"	value="<%=teacher_adVO.getTeacher_ad_id()%>" /></td> --%>
<!-- <!-- 		<td>�Юv�s�i�s��:<font color=red><b>*</b></font></td> -->
<%-- <%-- 		<td><%=teacher_adVO.getTeacher_ad_id()%></td> --%>
<!-- 	</tr> -->
<%-- 	<jsp:useBean id="teacherSvc" scope="page" class="com.Teacher.model.TeacherService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>�Юv�s��:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="teacher_id"> -->
<%-- 			<c:forEach var="teacherVO" items="${teacherSvc.all}"> --%>
<%-- 				<option value="${teacherVO.teacher_id}" ${(teacher_adVO.teacher_id==teacherVO.teacher_id)?'selected':'' } >${teacherVO.teacher_id} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->
	
	<tr >
		<td style="font-size:20px">�}�l�ɶ�:</td>
		<td style="font-size:20px"><input name="ad_start" id="f_date1" type="text" ></td>
	</tr>
	
	<tr>
		<td style="font-size:20px">�ʶR�Ѽ�:</td>
		<td style="font-size:20px"><input min="1"type="number" name="ad_time" size="45"	value="1" /></td>
	</tr>
	
<!-- 	<tr> -->
<!-- 		<td>�s�i���A:</td> -->
<%-- 		<td><input type="TEXT" name="ad_state" size="45"	value="<%=teacher_adVO.getAd_state()%>" /></td> --%>
<!-- 	</tr> -->
</table >
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="teacher_id" value="<%=teacher_adVO.getTeacher_ad_id()%>">
<input  class="btn btn-primary"type="submit" value="�e�X�q��">
</FORM>

<script>
// onclick="purchase()"
// function purchase(){
// 	swal("�U�ӵo�j�]���N�O�A!!!");
// }
</script>
</div>



<div style="padding-top:130px">
<h3>�{���s�i:</h3>
<table class="" style="border-collapse:separate; border-spacing:0px 10px;">
<!-- 	<tr> -->
<!-- 		<th>�Юv�s�i�s��</th> -->
<!-- 		<th>�Юv�s��</th> -->
<!-- 		<th>�}�l�ɶ�</th> -->
<!-- 		<th></th> -->
<!-- 		<th>�s�i���A</th> -->
		
<!-- 	</tr> -->
	<tr>
	
<%-- 			<td>${teacher_adVO.teacher_ad_id}</td> --%>
<%-- 			<td>${teacher_adVO.teacher_id}</td> --%>
			<td style="font-size:20px">�}�l�ɶ�: <fmt:formatDate value="${teacher_adVO.ad_start}" pattern="yyyy�~-MM��-dd��"/></td>
		
			
			
<%-- 			<td>${teacher_adVO.ad_time}</td> --%>
<!-- 			<td id="end_time"></td> -->
<%-- 		<td><%=teacher_adVO.getTeacher_ad_id()%></td> --%>
<%-- 		<td><%=teacher_adVO.getTeacher_id()%></td> --%>
<%-- 		<td><%=teacher_adVO.getAd_time()%></td> --%>
<%-- 		<td><%=teacher_adVO.getAd_start()%></td> --%>
<%-- 		<td><%=teacher_adVO.getAd_state()%></td> --%>

	</tr>
	<tr>
	<td style="font-size:20px">�ʶR�Ѽ�: ${teacher_adVO.ad_time}</td>
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
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">				

swal( <c:forEach var="message" items="${errorMsgs}"> 
	"${message}"
</c:forEach> 

);
</c:if>	

</script>




<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->
<!-- <script> -->
<%--  var end=new Date(${teacher_adVO.ad_start}); --%>
<!--  document.getElementById('end_time').innerHTML=end; -->
<!-- </script> -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.full.js"></script>
<!-- -------�����ɶ�------------------------------ -->
<script>
var end_time=(new Date('${teacher_adVO.ad_start}')).getTime()+'${teacher_adVO.ad_time}'*60*60*1000*24;

da = new Date(end_time);
var year = da.getFullYear()+'�~';
var month = da.getMonth()+1+'��';
var date = da.getDate()+'��';
console.log([year,month,date].join('-'));
document.getElementById("end_time").innerHTML='�����ɶ�: '+[year,month,date].join('-');
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
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: new Date() // value:   new Date(),
<%--  		  '<%=teacher_adVO.getAd_start()%>' --%>
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
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

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
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


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
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