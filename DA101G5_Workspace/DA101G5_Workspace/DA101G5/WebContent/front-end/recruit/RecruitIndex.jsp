<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.recruit.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%
    RecruitService recruitSvc = new RecruitService();
    List<RecruitVO> list = recruitSvc.getAllRecruit();
    pageContext.setAttribute("list", list);
%>    
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#float").click(function(){
    	$("#recruit").show();
    	$("#mask").show();
    });
    
    $("#mask").click(function(){
    	$("#recruit").hide();
    	$("#mask").hide();
    	$(".messageform").hide();
    });
    
    $("#cross").click(function(){
    	$("#recruit").hide();
    	$("#mask").hide();
    });
    
    $(".cross").click(function(){
    	$(".messageform").hide();
    	$("#mask").hide();
    });
    
});

function magicButton(){
	var rtitle = '徵求韓文教師，聽力口說到native水準';
	var rcontent = '時間彈性 價錢可議\r\n'+'上課地點限定台北市大安區或者網站直播都可以\r\n'+'意者私我';
	document.getElementById("rtitle").value = rtitle;
	document.getElementById("rcontent").value = rcontent;
}

function magicButton2(member_id){
	var message = '哈囉 我想我有課程符合你的需求\r\n'+'如有需要請聯絡我，我的信箱是:\r\n'+'aabbcc@gmail.com';
	document.getElementById("messagecontent"+member_id).value = message;
	
}
</script>
<script>

	function openmessage(recruitVO_memberid){
		document.getElementById("message"+recruitVO_memberid).style.display="block";
		document.getElementById("mask").style.display="block";
	}
	
	function success(){
		alert('傳送成功!');
	}

</script>
<%@ include file="/front-end/file/head.file" %>

<style type="text/css">
html, body { margin: 0; padding: 0; height: 100%; border:none; }

.card{
    margin:5px;
}

#searchingform{
	margin-left:auto; 
	margin-right:auto;
}

.container{
    display:flex;
    flex-wrap: wrap
}

body{
	font-family:Microsoft JhengHei;
}

#mask{
	display:none;
	width:100%;
	height:100%;
	opacity:0.3;
	background-color:black;
	Z-index:100;
	position: fixed;
}

#recruit{
	width:60%;
	display:none;
	Z-index:999;
	position: fixed;
	top:7%;
	left:18%;
	background-color:white;
}

#cross{
	color:blue;
	float:right;
	top:0;
	cursor:pointer;
}

#float{
position: fixed;
width:50px;
height:50px;
left: 90%;
top:70%;
Z-index:200;
border-radius:25px;

}

#recruit{
	width:50%;
}

.messageform{
	width:60%;
	display:none;
	Z-index:999;
	position: fixed;
	top:7%;
	left:18%;
	background-color:white;
}

.cross{
	color:blue;
	float:right;
	top:0;
	cursor:pointer;
}

.row{
	    justify-content: space-around;
}
</style>
<meta charset="UTF-8">
<title>派單區首頁</title>
</head>
<body>
<c:if test="${sessionScope.memberVO!=null}">
<input id="float" type="button" value="派單" style="border:1px blue solid;background-color: red;"/>
</c:if>
<div id="mask"></div>
<%@ include file="/front-end/file/header.jsp" %>
<br>
<br>
<br>
<br>
<br>
<br>
<!-- ----------------------------搜尋--------------------------------------------------------- -->
<form action="<%=request.getContextPath()%>/recruit/recruit.do" method="POST">
    <table id="searchingform" >
        <tr>
            <td>搜尋關鍵字:<input type="text" name="keyword"></td><td><input type="submit" value="確定" ></td>
        </tr>
    </table>
    <input type="hidden" name="action" value="search">
</form>
<div class="row justify-content-center">
	<br>
</div>
<!------------------------顯示派單-------------------------  ---------------------------------------->
<div class="container">
<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
<%@ include file="recruit_file/recruit_page1.file" %>
<br> 
<c:forEach var="recruitVO" items="${list}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<c:if test="${recruitVO.recstatus==1}">	
		<div class="row">
		  <div class="col-3">	
			<div class="card" style="width: 18rem;">
	            <div class="card-header">
	                <ul class="nav nav-pills card-header-pills">
	                  <li class="nav-item">
	                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">派單編號:${recruitVO.recruit_id}</a>
	                  </li>
	                </ul>
	            </div>
	            <div class="card-body">
	                <h5 class="card-title">需求:${recruitVO.rtitle}</h5>
	                    
	                <c:if test="${sessionScope.memberVO!=null}">   
	                <h6 class="card-subtitle mb-2 text-muted"><span style="cursor:pointer; color:blue" onclick="openmessage('${recruitVO.member_id}')">發文者:${memberSvc.getOneMember(recruitVO.member_id).mem_nick}</span></h6>
	                </c:if>
	                <c:if test="${sessionScope.memberVO==null}">
	                <h6 class="card-subtitle mb-2 text-muted"><span>發文者:${memberSvc.getOneMember(recruitVO.member_id).mem_nick}</span></h6>
	                </c:if>  
	                    
	                <p class="card-text">說明:</p>
	                 <textarea readonly style="border: none; resize: none;" rows="6" cols="25">${recruitVO.rcontent}</textarea><br>   
	                <c:if test="${sessionScope.memberVO!=null}">
	                   <a class="card-link" href="<%=request.getContextPath()%>/front-end/recruit/reportForm.jsp?recruit_id=${recruitVO.recruit_id}">檢舉派單</a>
	                </c:if>
	                <p class="card-text"><small class="text-muted">發文日期:${recruitVO.startdate.toString().substring(0,19)}</small></p>
	             </div>
	         </div>
	     </div>     
	   </div>      
	</c:if>
	<c:if test="${recruitVO.recstatus==0}">     
	  <div class="row">
        <div class="col-3"> 
		   <div class="card" style="width: 18rem;">
	            <div class="card-header">
	                <ul class="nav nav-pills card-header-pills">
	                  <li class="nav-item">
	                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">派單編號:${recruitVO.recruit_id}</a>
	                  </li>
	                </ul>
	            </div>
	            <div class="card-body">
	                <h5 class="card-title">文章已被隱藏</h5>
	                        
	                <h6 class="card-subtitle mb-2 text-muted">發文者:${memberSvc.getOneMember(recruitVO.member_id).mem_nick}</h6>
	                <p class="card-text">文章已被隱藏</p>
	                <p class="card-text"><small class="text-muted">發文日期:${recruitVO.startdate.toString().substring(0,19)}</small></p>
	             </div>
	        </div>
	      </div>
	    </div>    
	</c:if> 
<!-- ---------------------------------------------------------------------- -->	
	<c:if test="${sessionScope.memberVO!=null}">
		<div class="messageform" style="display:none" id="message${recruitVO.member_id}">
		<div class="container">
		<span class="cross">[X]</span>
		私訊:<br>
			<form action="<%=request.getContextPath()%>/recruit/recruit.do" method="post">
			<table class="table table-bordered">
				<tr>
					<th>收件者:</th><th>${memberSvc.getOneMember(recruitVO.member_id).mem_nick}</th>
				<tr>
				<tr>
		    		<th>訊息內容:</th><td><textarea id="messagecontent${recruitVO.member_id}" cols="50" rows="8" name="messagecontent" required></textarea></td>
	    		</tr>
	    		<tr><td><input type="submit" value="傳送" onsubmit="success()"></td></tr>
	    		<tr><td><input type="button" value="神奇按鈕" onclick="magicButton2('${recruitVO.member_id}')"></td></tr>
			</table>
			<input type="hidden" name="action" value="sendmessage">
			<input type="hidden" name="sender_id" value="${sessionScope.memberVO.member_id}">
			<input type="hidden" name="reciver_id" value="${recruitVO.member_id}">
			</form>
			
		</div>
		</div>
	</c:if>
<!-- ----------------------------------------------------------------------- -->	 	 		
</c:forEach>
<%@ include file="recruit_file/recruit_page2.file" %>
<br>
</div> 
<br>
<div style="text-align:center;margin-bottom:10px;">
<%if (pageNumber>0){%>
  <b><font color=red>第<%=whichPage%>/<%=pageNumber%>頁</font></b>
<%}%>
<b>共<font color=red><%=rowNumber%></font>筆</b>
</div>
<!-- ----------------------------發派單---------------------------------------------------------- -->
<c:if test="${sessionScope.memberVO!=null}">
<div id="recruit" class="container">
<span id="cross">[X]</span>
我要派單:<br>

<form action="<%=request.getContextPath() %>/recruit/recruit.do" method="POST">
	<table class="table table-bordered">
		<tr>
			<th>需求:</th><td><input id="rtitle" type="text" required style="width:60%;" name="rtitle" value="${param.rtitle}">${errorMsgs.rtitle}</td>
		</tr>
		<tr>
		    <th>需求說明:</th><td><textarea id="rcontent" cols="50" rows="8" name="rcontent" required>${param.rcontent}</textarea>${errorMsgs.rcontent}</td>
	    </tr>
	</table>
	<input type="hidden" name="action" value="addRecruit">
	<input type="hidden" name="member_id" value="${sessionScope.memberVO.member_id}">
	<input type="submit" value="新增">
	<input type="button" value="神奇按鈕" onclick="magicButton()">
</form>
</div>
</c:if>

<!-- ---------------------------------------------------------------------------------------------- -->
<%@ include file="/front-end/file/footer2.file" %>

</body>
</html>