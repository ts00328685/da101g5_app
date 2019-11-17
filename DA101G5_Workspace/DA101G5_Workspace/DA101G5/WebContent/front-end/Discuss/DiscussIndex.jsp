<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.que.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    QueService queSvc = new QueService();
    List<QueVO> quelist = queSvc.getAllQue();
    pageContext.setAttribute("quelist",quelist);
%>    
<!DOCTYPE html>
<html>
<head>
<%@ include file="/front-end/file/head.file" %>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
   $(document).ready(function(){
        $("input[name='quefile']").change(function(){//預覽
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
        
        $("#add").submit(function(){//發文送出前確認
            var r=confirm('確定新增?');
                if (r==true){
                   return true; 
                }else{
                    return false;
                } 
        });
        
        $("#cancel").click(function(){//取消確認
            var r=confirm('確定取消?');
                if (r==true){
                   history.back(); 
                }
        });
        
        $("#float").click(function(){
        	$("#ask").show();
        	$("#mask").show();
        });
        
        $("#mask").click(function(){
        	$("#ask").hide();
        	$("#mask").hide();
        });
        
        $("#cross").click(function(){
        	$("#ask").hide();
        	$("#mask").hide();
        });
        
   }) ;
   
   function magicButton(){
	   var title = '一位外國朋友每次寫 email 給我，第一句話都是寫 "I hope this email finds you well."。';
	   var content = '這句話是什麼意思？是否可以說明 find 在此的用法?'+'\r\n'+'是書信的問候語或祝福語嗎?';
	   document.getElementById("quetitle").value = title;
	   document.getElementById("quecontent").value = content;
   }
   
</script>
<style type="text/css">
#picPreview,#videoPreview{
        max-height: 200px;
        display:none;
    }
table{
    margin-top:30px;
} 

.card{
    margin:10px;
} 

.hashtag{
    margin:3px;
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

body{
	font-family:Microsoft JhengHei;
	width:100%;
	height:100%;
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

#ask{
	width:60%;
	display:none;
	Z-index:999;
	position: absolute;
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
</style>
<meta charset="UTF-8">
<title>討論區首頁</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%@ include file="/front-end/file/header.jsp" %>
<div id="mask"></div>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<c:if test="${sessionScope.memberVO!=null}">
<input id="float" type="button" value="發問" style="border:1px blue solid;background-color: red;"/>
</c:if>
<div class="container">
<a href="<%=request.getContextPath()%>/index.jsp">回首頁</a>
<form action="<%=request.getContextPath()%>/que/que.do" method="POST">
	<table>
		<tr>
			<td>搜尋關鍵字:<input type="text" name="keyword"></td><td><input type="submit" value="確定"></td>
		</tr>
	</table>
	<input type="hidden" name="action" value="search">
</form>

<!-- ------------------------------------------------------------------------------------- -->
<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
<jsp:useBean id="qhashtagSvc" class="com.qhashtag.model.QhashtagService" scope="page"/>
<jsp:useBean id="hashtagSvc" class="com.hashtag.model.HashtagService" scope="page"/>  
<%@ include file="/front-end/Discuss/Discuss_file/quepage1.file" %>
<c:forEach var="queVO" items="${quelist}" begin="<%=index%>" end="<%=index+quePerPage-1%>">

<div class="card">
  
  <div class="card-header  text-white bg-info">
        <div class="float-left">發問者:${memberSvc.getOneMember(queVO.member_id).mem_nick}</div> 
        <span class="badge badge-danger float-right">讚:${queVO.quethumb }</span><br>    
    <p class="card-text"><small class="text-muted">發文時間:${queVO.quetime.toString().substring(0,19)}</small></p>
  </div>
  
  <div class="card-body">
    <h5 class="card-title float-left">
        <c:if test="${queVO.questatus ==1 }">
                              標題:<a href="<%=request.getContextPath()%>/front-end/Discuss/forwardto.jsp?que_id=${queVO.que_id}&action=tothread">${queVO.quetitle}</a>
        </c:if>
        <c:if test="${queVO.questatus ==0 }">
                             文章已被隱藏
        </c:if>
    </h5>
    <span class="badge badge-success float-right">瀏覽人次:${queVO.watchcount}</span><br>
    <span class="badge badge-warning float-right">回應數:${queVO.rescount}</span>
  </div>
  
  <div class="card-footer text-muted">
    hashtag:<br>
    <c:if test="${qhashtagSvc.findQhashtagByQue(queVO.getQue_id())!=null}">
        <c:forEach var="qhashtagVO" items="${qhashtagSvc.findQhashtagByQue(queVO.getQue_id()) }">
          <form class="hashtag" style="float:left" action="<%=request.getContextPath()%>/que/que.do" method="post">
            <input class="btn btn-primary" type="submit" value="# ${hashtagSvc.findHashtagByPK(qhashtagVO.getHashtag_id()).getHashtag()}">
            <input type="hidden" name="action" value="tagsearch">
            <input type="hidden" name="hashtag_id" value="${qhashtagVO.hashtag_id}">
          </form>  
        </c:forEach>
    </c:if> 
  </div>
</div>
</c:forEach>    
<%@ include file="/front-end/Discuss/Discuss_file/quepage2.file" %>
</div>
<!-- ------------------------------------------------------------------------------------ -->
<br>
<div class="container" id="ask">
<span id="cross">[X]</span>

<h1>我要發問:</h1>
<p>上傳檔案時，請注意本站僅接受jpg / jpeg / png / bmp /gif/ webm類型的檔案，並且檔案大小不可超過5MB</p>
<FORM id="add" action="<%=request.getContextPath()%>/que/que.do" method="POST" enctype="multipart/form-data">
    <table class="table table-striped table-bordered">
        <tr>
            <td>標題:</td><td><input type="text" name="quetitle" id="quetitle" style="width:70%" value="${param.quetitle}" required></td>
        </tr>
        
        <tr>
            <td>內文:</td><td><textarea  cols="60" rows="8" name="quecontent" id="quecontent" required>${param.quecontent}</textarea></td>
        </tr>

        <tr>
            <td>選擇標籤:</td>
            <td>
                <c:forEach var="hashtagVO" items="${pageScope.hashtagSvc.allHashtag}">
                    <input type="checkbox" name="qhashtag" value="${pageScope.hashtagVO.hashtag_id }">${pageScope.hashtagVO.hashtag}
                </c:forEach>
            </td>
        </tr>

        <tr>
            <td>檔案:</td><td><input type="file" name="quefile" ><br></td>
        </tr>
        <tr>
            <td colspan="2">
			            上傳檔案預覽:
			     <img id="picPreview" src=""/>
			     <video id="videoPreview" src="" controls="controls" loop="loop" muted></video>
            </td>
        </tr>
</table>
<input type="hidden" name="action" value="addque">
<input type="hidden" name="member_id" value="${sessionScope.memberVO.member_id}">
<div class="row justify-content-center">
<input type="submit" value="送出新增" id="addque">
<input type="button" value="神奇按鈕" onclick="magicButton()">
</div>
</form>
</div>

<div class="container">
<div class="row justify-content-center">
    <a href="<%=request.getContextPath()%>/index.jsp">回首頁</a>
</div>
</div>

<%@ include file="/front-end/file/footer2.file" %>
</body>
</html>