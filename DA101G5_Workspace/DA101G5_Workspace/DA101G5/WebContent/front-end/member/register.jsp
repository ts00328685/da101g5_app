<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<script src="<%=request.getContextPath()%>/js/address.js"></script>
<%@ include file="/front-end/file/head.file"%>
<style>
  #memberPicShow{
		width:200px;
		height:200px;
		border-radius: 10px;
		margin-bottom: 30px;
		text-align:center;
	}
	
	#fromgp{
		margin-left:100px;
	}
	

</style>

</head>

<body bgcolor='white'>
<%@ include file="/front-end/file/header.jsp"%>



<h3>註冊:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="container" style="padding-top: 200px;padding-bottom: 50px;">
		<div class="row justify-content-center">
			<div class="col-7">
				<div style="display:flex;flex-direction:column;justify-content:right;">
					<h1  style="text-align:center;margin-bottom:20px;">註冊</h1>
				</div>
				<form method="post" action="<%= request.getContextPath() %>/member/member.do" enctype="multipart/form-data" name="add">
					<div class="form-group row">
						<label for="inputdata_name" class="col-sm-2 col-form-label">會員姓名<span style="color:red;">*</span>：</label>
						
						<div class="col-sm-10">
							<input type="text" name="mem_name" size="45" class="form-control" id="inputdata_name" placeholder=" 只能是中、英文字母, 且長度在2到10之間"
								value="${memberVO.mem_name}" required>
						</div>
					</div>
				
					<div class="form-group row">
						<label for="inputdata_nick" class="col-sm-2 col-form-label">會員暱稱<span style="color:red;">*</span>：</label>
						<div class="col-sm-10">
							<input type="text" name="mem_nick" size="45" class="form-control" id="inputdata_nick"
								value="${memberVO.mem_nick}" required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputdata_email" class="col-sm-2 col-form-label">會員信箱<span style="color:red;">*</span>：</label>
						<div class="col-sm-10">
							<input type="email" name="mem_email" size="45" class="form-control" id="inputdata_email" placeholder="example@example.com"
								value="${memberVO.mem_email}" required>
						</div>
					</div>
					
					<div class="form-group row">
						<label for="inputdata_pwd" class="col-sm-2 col-form-label">會員密碼<span style="color:red;">*</span>：</label>
						<div class="col-sm-10">
							<input type="password" pattern="^[(a-zA-Z0-9)]{2,10}$" name="mem_pwd" size="45" class="form-control" id="inputdata_pwd" placeholder="只能是英文字母、數字,且長度必需在2到10之間"
								value="${memberVO.mem_pwd}" required>
								
						</div>
					</div>
						
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員生日<span style="color:red;">*</span>：</label>
						<div class="col-sm-10">
							<input type="text" name="mem_birthday" id="f_date1" class="form-control" 
								value="${memberVO.mem_birthday}" required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputdata_mobile" class="col-sm-2 col-form-label">會員手機<span style="color:red;">*</span>：</label>
						<div class="col-sm-10">
							<input type="text" name="mem_mobile" size="45" class="form-control" id="inputdata_mobile"
								value="${memberVO.mem_mobile}" required>
						</div>
					</div>					
					
					<div class="form-group row">
						<label for="inputdata_coun" class="col-sm-2 col-form-label">會員國家<span style="color:red;">*</span>：</label>
						<div class="col-sm-10">
							<input type="text" name="mem_country" size="45" class="form-control" id="inputdata_coun"
								value="${memberVO.mem_country}" required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputdata_city" class="col-sm-2 col-form-label">會員城市<span style="color:red;">*</span>：</label>
						<div class="col-sm-10">
							<input type="text" name="mem_city" size="45" class="form-control" id="inputdata_city"
								value="${memberVO.mem_city}" required>
								
							
						</div>
					</div>
					<div class="form-group row">
						<label for="inputdata_sex" class="col-sm-2 col-form-label">會員性別<span style="color:red;">*</span>：</label>
						<div class="col-sm-10">
							<input type="radio" id="sex1" name="mem_sex" value="男" ${(memberVO.mem_sex == "男")? "checked":""}><label for="sex1">男</label>
							<input type="radio" id="sex2" name="mem_sex" value="女" ${(memberVO.mem_sex == "女")? "checked":""}><label for="sex2">女</label>						
						</div>
					</div>											
					<div class="form-group row">
						<label for="inputdata_pro" class="col-sm-2 col-form-label">會員簡介:</label>
						<div class="col-sm-10">
							<textarea name="mem_profile" size="90" class="form-control" id="inputdata_pro"	required>${memberVO.mem_profile}</textarea>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputpic" class="col-sm-2 col-form-label">會員照片:</label>
						<div class="col-sm-10">			
							
							<input type="file" name="mem_pic" onchange="myReadPic(this)" accept="image/gif, image/jpeg, image/png">
							<input type="hidden" name="mem_picStr" value="${(encodedTextM == null)? '':encodedTextM}">
							<c:if test="${encodedTextM != null}" var="conditionM" scope="page">
							<img src="data:image/jpeg;base64,${encodedTextM}" height ="200px" id="myImg" style="margin-bottom:10px;"/>			
							</c:if>
							<c:if test="${encodedTextM == null}" var="conditionM2" scope="page">
							<img  height="200px" id="myImg" style="margin-bottom:10px;"/>
							</c:if>
						</div>
					</div>
<!-- 					<div class="form-group row">						 -->
<!-- 						<div class="col-sm-10"> -->
<!-- 							<input type="hidden" name="mem_createtime" class="form-control" id="inputdata_create"> -->
<!-- 						</div> -->
<!-- 					</div>	 -->
					
							
						</div>
					</div>							
					<div class="buttongp" style="display:flex;justify-content:center;">
					<div class="form-group row" id="fromgp">
							<input type="submit" value="送出" class="btn btn-primary"> 
							<input type="hidden" name="member_id" value="${memberVO.member_id}"> 
							<input type="hidden" name="action" value="insert">	
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<span><input type="button" value="A" id="b1"></span>
							<span><input type="button" value="B" id="b2"></span>
					</div>					
					</div>
				</form>
			</div>
		</div>
	</div>


<div style="visibility:hidden">區塊中的內容</div>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date mem_birthday = null;
  try {
	  mem_birthday = memberVO.getMem_birthday();
   } catch (Exception e) {
	   mem_birthday = new java.sql.Date(System.currentTimeMillis());
   }
 
%>

<% 
  java.sql.Date mem_createtime = null;
  try {
	  mem_createtime = memberVO.getMem_createtime();
   } catch (Exception e) {
	   mem_createtime = new java.sql.Date(System.currentTimeMillis());
   }
 
%>
<%@ include file="/front-end/file/footer.file"%>

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
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%= mem_birthday%>', // value:   new Date(),
		   
		   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
       
		  
		   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
	       value: '<%=mem_createtime%>', // value:   new Date(),
		   
		   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
       
		  
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
             var somedate2 = new Date('2019-08-01');
             $('#f_date1').datetimepicker({
                 beforeShowDay: function(date) {
               	  if (  date.getYear() >  somedate2.getYear() || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                     ) {
                          return [false, ""]
                     }
                     return [true, ""];
             }});


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
 
 function myReadPic(obj) {
    var file = obj.files[0];
    var file2 = obj.files[1];
    
    console.log(obj);console.log(file);
    console.log("file.size = " + file.size);  //file.size 单位为byte

    var reader = new FileReader();

    //读取文件过程方法
    reader.onloadstart = function (e) {
        console.log("開始讀取....");
    }
    reader.onprogress = function (e) {
        console.log("正在讀取中....");
    }
    reader.onabort = function (e) {
        console.log("中斷讀取....");
    }
    reader.onerror = function (e) {
        console.log("讀取異常....");
    }
    reader.onload = function (e) {
        console.log("成功讀取....");
        
        
        var img = document.getElementById('myImg'); 
        img.src = e.target.result;
       
        //或者 img.src = this.result;  //e.target == this
    }

    reader.readAsDataURL(file)
}
 </script>

<script>
$(document).ready(function(){
	$('#b1').click(function(){
		$("#inputdata_name").val("吳帥神");
		$("#inputdata_nick").val("老司雞");
		$("#inputdata_email").val("da101g5languagegg@gmail.com");
		$("#inputdata_pwd").val("000000");
		$("#f_date1").val("1990-08-01");
		$("#inputdata_mobile").val("0921190235");
		$("#inputdata_coun").val("台灣");
		$("#inputdata_city").val("桃園");
		$("#sex1").prop("checked", true);
		$("#inputdata_pro").val("你們班比較特別，我要倒過來上!诶，我要幹嘛了?!沒關係，都那麼熟了!");
	});
});

$(document).ready(function(){
	$('#b2').click(function(){
		$("#inputdata_name").val("吳大衛");
		$("#inputdata_nick").val("大衛海鮮");
		$("#inputdata_email").val("da101g5languagegg@gmail.com");
		$("#inputdata_pwd").val("000000");
		$("#f_date1").val("2001-02-19");
		$("#inputdata_mobile").val("0921190235");
		$("#inputdata_coun").val("台灣");
		$("#inputdata_city").val("台北");
		$("#sex1").prop("checked", true);
		$("#inputdata_pro").val("大家好，我是大衛SeaFood。");
	});
});
</script>


</body>
</html>