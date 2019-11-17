<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<%@ include file="/back-end/file/head.file"%>
<script type="text/javascript">
$(document).ready(function(){
     $("input[name='evpic']").change(function(){//預覽
         if(this.files && this.files[0]){
             var filetype = this.files[0].type;
             var reader = new FileReader();
             if(filetype.indexOf('image')>-1){
                 reader.onload=function(e){
                     $('#picPreview').show();
                     $('#videoPreview').hide();
                     $('#picPreview').attr('src', e.target.result);
                 }   
             }
             if(filetype.indexOf('video')>-1){
                 reader.onload=function(e){
                     $('#videoPreview').show();
                     $('#picPreview').hide();
                     $('#videoPreview').attr('src', e.target.result);
                 }
             }
          }
             reader.readAsDataURL(this.files[0]);
         });
});

function magicButton(){
	var title = "資策會結訓日，點數大放送！！！！";
	var content = "各位親愛的會員，即日起至8/2日00:00止，\r\n只要擁有本站會員資格，點數通通免費送！\r\n每人限索取200點，只限一次機會！\r\n各位學子請把握機會";
	document.getElementById("evtitle").value = title ;
	document.getElementById("evcontent").value = content;
}
</script>
<style>
body{
	font-family:Microsoft JhengHei;
}

#picPreview,#videoPreview{
        max-height: 200px;
        display:none;
    }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="/back-end/file/header_back.jsp" %>
<br>
<br>
<br>
<br>
<br>
<br>
<div class="container">
<div class="row justify-content-center">
<form action="<%=request.getContextPath() %>/event/event.do" method="post" enctype="multipart/form-data">	
	<table>
	    <tr><td>活動標題:</td><td><input type="text" name="evtitle" id="evtitle" required value="${param.evtitle}">${errorMsgs.evtitle}</td></tr>
	    <tr><td>活動內容:</td><td><textarea rows="10" cols="80" name="evcontent" id="evcontent" required>${param.evcontent}</textarea>${errorMsgs.evcontent}</td></tr>
	    <tr><td>上傳活動檔案:</td><td><input type="file" name="evpic"></td><td><input type="submit" value="送出">${errorMsgs.evpic}</td></tr>
	    <tr><td colspan="2"><input type="button" value="神奇按鈕" onclick="magicButton()"></td></tr>
		<tr>
            <td colspan="2">
			            上傳檔案預覽:
            </td>
        </tr>
        <tr><td colspan="2">
        		<img id="picPreview" src=""/>
				<video id="videoPreview" src="" controls="controls" loop="loop" muted></video>
        	</td>
        </tr>
	</table>
	<input type="hidden" name="action" value="addevent">
</form>
</div>
</div>
<%@include file="/back-end/file/footer.file" %>	
</body>
</html>