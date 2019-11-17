<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.friendchoose.model.*"%>
<%@ page import="com.Language.model.*"%>
<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改</title>
<%@ include file="/front-end/file/head.file"%>

<style>
  #memberPicShow{
		width:200px;
		height:200px;
		border-radius: 10px;
		margin-bottom: 30px;
		text-align:center;
	}
	
	
</style>

</head>
<body bgcolor='white'>
<%@ include file="/front-end/file/header.jsp"%>






<div class="container" style="padding-top: 200px;padding-bottom: 50px;">
		<div class="row justify-content-center">
			<div class="col-7">
			
				<div style="display:flex;flex-direction:column;justify-content:center;">
					<h3 style="text-align:center;margin-bottom:50px;"><b>修改會員資料 </b></h3>
				</div>
				<form method="post" action="<%= request.getContextPath() %>/member/member.do" enctype="multipart/form-data">
					<div class="form-group row">
						<label for="inputdata_name" class="col-sm-2 col-form-label">會員姓名<span style="color:red;">*</span>：</label>
						<div class="col-sm-10">
							<input type="text" name="mem_name" size="45" class="form-control" id="inputdata_name"
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
							<input type="email" name="mem_email" size="45" class="form-control" id="inputdata_email"
								value="${memberVO.mem_email}" required>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputdata_pwd" class="col-sm-2 col-form-label">會員密碼 <span style="color:red;">*</span>：</label>
						<div class="col-sm-10">
							<input type ="password" name="mem_pwd" size="45" class="form-control" id="inputdata_pwd"
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
						<label for="inputPassword" class="col-sm-2 col-form-label">會員性別<span style="color:red;">*</span>：</label>
						<div class="col-sm-10">
							<input type="radio" name="mem_sex" value="男" ${(memberVO.mem_sex == "男")? "checked":""}>男
							<input type="radio" name="mem_sex" value="女" ${(memberVO.mem_sex == "女")? "checked":""}>女							
						</div>
					</div>											
					<div class="form-group row">
						<label for="inputdata_pro" class="col-sm-2 col-form-label">會員簡介</label>
						<div class="col-sm-10">
							<textarea name="mem_profile" size="45" class="form-control" id="inputdata_pro"	required>${memberVO.mem_profile}</textarea>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">會員照片：</label>
						<div class="col-sm-10">			
							<img id="memberPicShow" src="<%= request.getContextPath() %>/member/memberReader.do?member_id=${memberVO.member_id}">
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
					<hr>
					<h3>學伴專區</h3>
					<hr>
					<div class="form-group row">
						<label for="inputPassword" class="col-sm-2 col-form-label">照片：</label>
						<div class="col-sm-10">			
							<img id="memberPicShow" src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id=${memberVO.member_id}">
							<input type="file" name="friend_pic" onchange="myFReadPic(this)" accept="image/gif, image/jpeg, image/png">
							<input type="hidden" name="friend_picStr" value="${(encodedTextF == null)? '':encodedTextF}">
							<c:if test="${encodedTextF != null}" var="conditionF2" scope="page">
							<img src="data:image/jpeg;base64,${encodedTextF}" height ="200px" id="myFImg" style="margin-bottom:10px;"/>
							</c:if>
							<c:if test="${encodedTextF == null}" var="conditionF2" scope="page">
							<img  height="200px" id="myFImg" style="margin-bottom:10px;"/>
							</c:if>
						</div>
					</div>					
					<div class="form-group row">
						<label for="inputdata_fpro" class="col-sm-2 col-form-label">簡介：</label>
						<div class="col-sm-10">
							<textarea name="friend_profile" size="45" class="form-control" id="inputdata_fpro">${memberVO.friend_profile}</textarea>
						</div>
					</div>
					<div class="form-group row">
						<label for="inputdata_fpro" class="col-sm-2 col-form-label">興趣語言：</label>
						<div class="col-sm-7">
						
							
							
  							<jsp:useBean id="language_ChooseOneSvc" class="com.Language.model.LanguageService" scope="page"/> 
 							<jsp:useBean id="Mem_ChooseLanSvc" class="com.friendchoose.model.FriendChooseService" scope="page"/> 
							

							
<!-- 							=== -->
							
							<%  List<FriendChooseVO> fclist = (List<FriendChooseVO>)request.getAttribute("new_friendChooseList");
								LanguageService lanSvc = new LanguageService();
								List<LanguageVO> lanlist = lanSvc.getAll();
								
								for(LanguageVO lanVO:lanlist){
									String str = "";
									if(fclist != null){
										for(int i = 0; i < fclist.size();i++){		
											if(fclist.get(i).getCondition_language_id().equals(lanVO.getLanguage_id())){
													str+="checked";								
													break;
											}
										}%>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="<%= lanVO.getLanguage_id()%>" name="condition_language_id" style="width:18px;height:18px;" <%= str%>>
										<label class="form-check-label" for="inlineCheckbox1" style="font-size:20px;"><%= lanVO.getLanguage()%></label>
									</div>	
								   <%}else {
									  String[] lans = request.getParameterValues("lans");
									  for(int i = 0; i < lans.length; i++){	
										  if(lans[i].equals(lanVO.getLanguage_id())){
												str+="checked";								
												break;
										}
									  }%>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="<%= lanVO.getLanguage_id()%>" name="condition_language_id" style="width:18px;height:18px;" <%= str%>>
										<label class="form-check-label" for="inlineCheckbox1" style="font-size:20px;"><%= lanVO.getLanguage()%></label>
									</div>
									  
								   <%}
							
								}%>					
						</div>
					</div>							
					<div class="buttongp" style="display:flex;justify-content:center;">
					<div class="form-group row" id="fromgp">
							<input type="submit" value="修改" class="btn btn-primary"> 
							<input type="hidden" name="member_id" value="${memberVO.member_id}"> 
							<input type="hidden" name="action" value="update2">
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					</div>					
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
<!-- <FORM METHOD="post" ACTION="member.do" name="form1"enctype="multipart/form-data"> -->
<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<td>會員編號:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=memberVO.getMember_id()%></td> --%>
<!-- 	</tr> -->
	
<!-- 	<tr> -->
<!-- 		<td>會員圖片:</td> -->
<%-- 		<td><img src="<%=request.getContextPath()%>/member/memberReader.do?member_id=${memberVO.member_id}" > --%>
<!-- 		<input type="file" name="mem_pic"   -->
<!-- 		onchange="readURL(this)" targetID="preview_progressbarTW_img" -->
<!-- 			accept="image/gif, image/jpeg, image/png" -->
<!-- 			></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>姓名:</td> -->
<%-- 		<td><input type="TEXT" name="mem_name" size="45"	value="<%=memberVO.getMem_name()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>密碼:</td> -->
<%-- 		<td><input type="TEXT" name="mem_pwd" size="45"	value="<%=memberVO.getMem_pwd()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>暱稱:</td> -->
<%-- 		<td><input type="TEXT" name="mem_nick" size="45"	value="<%=memberVO.getMem_nick()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>信箱:</td> -->
<%-- 		<td><input type="TEXT" name="mem_email" size="45" value="<%=memberVO.getMem_email()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>出生日期:</td> -->
<!-- 		<td><input name="mem_birthday" id="f_date1" type="text" ></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>電話號碼:</td> -->
<%-- 		<td><input type="TEXT" name="mem_mobile" size="45" value="<%=memberVO.getMem_mobile()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>城市:</td> -->
<%-- 		<td><input type="TEXT" name="mem_city" size="45" value="<%=memberVO.getMem_city()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>國家:</td> -->
<%-- 		<td><input type="TEXT" name="mem_country" size="45" value="<%=memberVO.getMem_country()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>會員簡介:</td> -->
<%-- 		<td><input type="TEXT" name="mem_profile" size="45" value="<%=memberVO.getMem_profile()%>" /></td> --%>
<!-- 	</tr> -->
	
<!-- <!-- 	<tr> -->
<!-- 		<td>狀態:</td> -->
<%-- 		<td><input type="TEXT" name="mem_status" size="45" value="<%=memberVO.getMem_status()%>" /></td> --%>
<!-- 	</tr> -->
	
<!-- 	<jsp:useBean id="memberV02" scope="page" class="com.member.model.MemberService" /> -->
<!-- 	<tr> -->
<!-- 		<td>狀態:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="mem_status"> -->
<!-- 			<c:forEach var="memberVO" items="${memberSvc.all}"> -->
<!-- 				<option value="${memberVO.mem_status}" ${(memberVO.mem_status==memberVO.mem_status)?'selected':'' } >${memberVO.member_status} -->
<!-- 			</c:forEach> -->
<!-- 		</select></td> -->
<!-- 	</tr> --> -->
	
<!-- </table> -->
<!-- <br> -->
<!-- <input type="hidden" name="action" value="update2"> -->
<%-- <input type="hidden" name="member_id" value="<%=memberVO.getMember_id()%>"> --%>
<!-- <input type="submit" value="送出修改"></FORM> -->


<%@ include file="/front-end/file/footer.file"%>


<script>
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

function myFReadPic(obj) {
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
        
        
        var img = document.getElementById('myFImg'); 
        img.src = e.target.result;
       
        //或者 img.src = this.result;  //e.target == this
    }

    reader.readAsDataURL(file)
}
</script>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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
 		   value: '<%=memberVO.getMem_birthday()%>', // value:   new Date(),
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
        
</script>
</html>