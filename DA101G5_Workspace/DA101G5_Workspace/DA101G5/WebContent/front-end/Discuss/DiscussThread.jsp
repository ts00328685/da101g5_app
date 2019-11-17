<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.que.model.*"%>
<%@ page import="com.res.model.*"%>
<%@page import="com.qhashtag.model.*"%>
<%@page import="com.hashtag.model.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    if(request.getAttribute("queVO")==null){
    	response.sendRedirect(request.getContextPath()+"/front-end/Discuss/DiscussIndex.jsp");
    	return;
    }
    List<ResVO> reslist =(ArrayList<ResVO>)request.getAttribute("reslist");
    pageContext.setAttribute("reslist",reslist);
%>
   
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<%@ include file="/front-end/file/head.file" %>
<style type="text/css">
img {
    max-height: 300px;
}

video {
    max-height: 300px;
}

#picPreview,#videoPreview{
        max-height: 300px;
        display:none;
    }

.hashtag{
    margin:5px;
}    

.threadfile{
    max-height:150px;
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

#response{
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

#float{
position: fixed;
width:50px;
height:50px;
left: 90%;
top:70%;
Z-index:200;
border-radius:25px;
}


</style>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>討論串</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
$(document).ready(function(){
    $("input[name='resfile']").change(function(){//預覽
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
    
    $("#float").click(function(){
    	$("#response").show();
    	$("#mask").show();
    });
    
    $("#mask").click(function(){
    	$("#response").hide();
    	$("#mask").hide();
    });
    
    $("#cross").click(function(){
    	$("#response").hide();
    	$("#mask").hide();
    });
    
    
});
function selectBest(res_id,belongto,bestansid){//選最佳回應
	
	var http = new XMLHttpRequest();
	var currentLocation = window.location.origin;
	var url = currentLocation + '<%=request.getContextPath()%>/que/que.do';
	
	var params = 'res_id=' + res_id +'&belongto='+belongto+'&action=selectBest';
	http.open('POST', url, true);

	//Send the proper header information along with the request
	http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

	http.onreadystatechange = function() {//Call a function when the state changes.
	    if(http.readyState == 4 && http.status == 200) {
	        if(http.responseText.indexOf('false')>-1){
	        	alert('已選擇過最佳回應');
	        }else{
	        	
	        	alert('選擇最佳回應成功');
	        	$("#"+bestansid).load(location.href+" #"+bestansid);
	        }
	    }
	}
	http.send(params);
}

function pressThumbque(member_id,que_id,thumbupid){//問題點讚
	var http = new XMLHttpRequest();
	var currentLocation = window.location.origin;
	var url = currentLocation + '<%=request.getContextPath()%>/que/que.do';
	var params = 'member_id='+member_id+'&que_id='+que_id+'&action=pressThumbque';
	http.open('POST', url, true);

	//Send the proper header information along with the request
	http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

	http.onreadystatechange = function() {//Call a function when the state changes.
	    if(http.readyState == 4 && http.status == 200) {
	        if(http.responseText.indexOf('false')>-1){
	        	alert('已成功退讚');
	        	$("#"+thumbupid).load(location.href+" #"+thumbupid);
	        }else{
	        	alert('讚ㄛ!!');
	        	$("#"+thumbupid).load(location.href+" #"+thumbupid);
	        }
	    }
	}
	http.send(params);
}

function pressThumbres(member_id,res_id,thumbupid){//回應點讚
	var http = new XMLHttpRequest();
	var currentLocation = window.location.origin;
	var url = currentLocation + '<%=request.getContextPath()%>/res/res.do';
	var params = 'member_id='+member_id+'&res_id='+res_id+'&action=pressThumbres';
	http.open('POST', url, true);

	//Send the proper header information along with the request
	http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

	http.onreadystatechange = function() {//Call a function when the state changes.
	    if(http.readyState == 4 && http.status == 200) {
	        if(http.responseText.indexOf('false')>-1){
	        	alert('已成功退讚');
	        	$("#"+thumbupid).load(location.href+" #"+thumbupid);
	        }else{
	        	alert('讚ㄛ!!');
				$("#"+thumbupid).load(location.href+" #"+thumbupid);
	        }
	    }
	}
	http.send(params);
}

function favorque(member_id,que_id){//加入最愛問題
    
    var http = new XMLHttpRequest();
    var currentLocation = window.location.origin;
    var url = currentLocation + '<%=request.getContextPath()%>/favorque/favorque.do';
    
    var params = 'member_id=' + member_id +'&que_id='+que_id+'&action=addfavorque';
    http.open('POST', url, true);

    //Send the proper header information along with the request
    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    http.onreadystatechange = function() {//Call a function when the state changes.
        if(http.readyState == 4 && http.status == 200) {
            if(http.responseText.indexOf('false')>-1){
                alert('已加過最愛問題');
            }else{
                alert('加入最愛問題成功!!!!!!!!');
                $("#favorque"+que_id).load(location.href+" #favorque"+que_id);
            }
        }
    }
    http.send(params);
}
</script>
</head>
<body>
<c:if test="${sessionScope.memberVO!=null}">
<input id="float" type="button" value="回應" style="border:1px blue solid;background-color: red;"/>
</c:if>
<div id="mask"></div>
<jsp:useBean id="memberSvc" class="com.member.model.MemberService"/>
<jsp:useBean id="hashtagSvc" class="com.hashtag.model.HashtagService" scope="page"/>
<jsp:useBean id="favorqueSvc" class="com.favorque.model.FavorqueService" scope="page"/>
<jsp:useBean id="thumbrecordqueSvc" class="com.thumbrecordque.model.ThumbrecordqueService" scope="page"/>
<jsp:useBean id="thumbrecordresSvc" class="com.thumbrecordres.model.ThumbrecordresService" scope="page"/>
<%@ include file="/front-end/file/header.jsp" %>
<div class="container">
<%----------------------------以下問題------------------------------------------------%>
    <br>
    <br>
    <br>
    <br>

<div class="card">
  <div class="card-header  text-white bg-info">
        <div class="float-left">問題編號:${queVO.que_id}</div><br>
        <div class="float-left">發問者:${memberSvc.getOneMember(queVO.member_id).mem_nick}</div>
        <div id="thumbup${queVO.que_id}" class="float-right">讚:
            <c:if test="${sessionScope.memberVO!=null && thumbrecordqueSvc.searchThumbrecordque(sessionScope.memberVO.member_id,queVO.que_id)==false}">
                    ${queVO.quethumb}
                    <img width="30" src="<%=request.getContextPath()%>/front-end/Discuss/images/thumbwhite.png" id="thumb${queVO.que_id }" onclick="pressThumbque('${sessionScope.memberVO.member_id}','${queVO.que_id}','thumbup${queVO.que_id}')"/>
                </c:if>
            <c:if test="${sessionScope.memberVO!=null && thumbrecordqueSvc.searchThumbrecordque(sessionScope.memberVO.member_id,queVO.que_id)==true}">
                    ${queVO.quethumb}
                    <img width="30" src="<%=request.getContextPath()%>/front-end/Discuss/images/thumborange.png" id="thumb${queVO.que_id }" onclick="pressThumbque('${sessionScope.memberVO.member_id}','${queVO.que_id}','thumbup${queVO.que_id}')"/>
                </c:if>    
                
                <c:if test="${sessionScope.memberVO==null}">
                    ${queVO.quethumb}
                    <img width="30" src="<%=request.getContextPath()%>/front-end/Discuss/images/thumbgray.png" id="thumb${queVO.que_id }"/>
                </c:if>
        </div>
        <div id="favorque${queVO.que_id}" class="float-right">
                                         加入最愛:    
                <c:if test="${sessionScope.memberVO!=null && favorqueSvc.searchFavorque(queVO.que_id , sessionScope.memberVO.member_id)==false}">
                   <img width="30" src="<%=request.getContextPath()%>/front-end/Discuss/images/whiteheart.png" onclick="favorque('${sessionScope.memberVO.member_id}','${queVO.que_id}')"/>
                </c:if>
                <c:if test="${sessionScope.memberVO!=null && favorqueSvc.searchFavorque(queVO.que_id , sessionScope.memberVO.member_id)==true}">
                   <img width="30" src="<%=request.getContextPath()%>/front-end/Discuss/images/heart.png" onclick="favorque('${sessionScope.memberVO.member_id}','${queVO.que_id}')"/>
                </c:if>
                <c:if test="${sessionScope.memberVO==null}">
                    <img width="30" src="<%=request.getContextPath()%>/front-end/Discuss/images/noheart.png"/>
                </c:if>
        </div>  
  </div>
  
  <div class="card-body">
    <p class="card-title float-left">
        <c:if test="${queVO.questatus ==1 }">
            <p class="card-text"><small class="text-muted">發文時間:${queVO.quetime.toString().substring(0,19) }</small></p>
            <c:if test="${sessionScope.memberVO!=null}">
                <div class="float-right"><a href="<%=request.getContextPath()%>/front-end/Discuss/QueReportForm.jsp?que_id=${queVO.que_id}">檢舉問題</a></div><br>
            </c:if>  
           <textarea readonly style="border: none; resize: none;" rows="20" cols="100">${queVO.quecontent }</textarea>
        </c:if> 
        <c:if test="${queVO.questatus ==0 }">
                              文章已被隱藏
        </c:if>
    </p>
    <c:if test="${sessionScope.memberVO.member_id==queVO.member_id}">
        <form action="<%=request.getContextPath()%>/que/que.do" method="POST">
        	<div class="float-right"><button type="submit" class="btn btn-info">編輯</button></div><br>
         		<input type="hidden" name="action" value="editque">
    			<input type="hidden" name="que_id" value="${queVO.que_id}">
		</form>    
    </c:if>
    <br>
    <c:if test="${queVO.questatus ==1 }">
      <c:if test="${queVO.quefile !=null && queVO.getFiletype().trim().substring(0,5) eq'image'}">
        <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}">
          <img class="threadfile" src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}"/></a>
      </c:if>
      
      <c:if test="${queVO.quefile !=null && queVO.getFiletype().trim().substring(0,5) eq'video'}">
        <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}">
          <video class="threadfile" controls loop>
            <source src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}" type="video/webm"> 
          </video>
        </a>
      </c:if>
      
      <c:if test="${queVO.quefile !=null && queVO.getFiletype().trim().substring(0,5) eq'audio'}">
        <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}">
          <video class="threadfile"  controls loop>
             <source src="<%=request.getContextPath()%>/front-end/Discuss/QRPicHandler.jsp?article_id=${queVO.que_id}" type="audio/webm">
          </video>
        </a>
      </c:if>
      <c:if test="${queVO.quefile ==null }">
                            無圖片
      </c:if>
      </c:if> 
      <c:if test="${queVO.questatus ==0 }">
                     文章已被隱藏
      </c:if>    
  </div>
  
  <div class="card-footer text-muted">
     hashtag:<br>
       <c:if test="${qhashtaglist!=null}">
          <c:forEach var="qhashtagVO" items="${qhashtaglist}">
            <form class="hashtag" style="float:left" action="<%=request.getContextPath()%>/que/que.do" method="post">
              <input class="btn btn-primary" type="submit" value="# ${hashtagSvc.findHashtagByPK(qhashtagVO.getHashtag_id()).getHashtag()}">
                <input type="hidden" name="action" value="tagsearch">
                  <input type="hidden" name="hashtag_id" value="${qhashtagVO.hashtag_id}">
                    </form>  
          </c:forEach>
       </c:if>
  </div>
</div>    
    <%-------------------------------以下回應 ------------------------------------------------%>
<%@ include file="/front-end/Discuss/Discuss_file/Discuss_page1.file" %>
<c:forEach var="resVO" items="${reslist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">    
<form action="<%=request.getContextPath()%>/res/res.do" method="POST">

<div class="card">
  <div class="card-header bg-warning">
        <div class="float-left">回應編號:${resVO.res_id}</div><br>
        <div class="float-left">回應者:${memberSvc.getOneMember(resVO.member_id).mem_nick}</div>
        <div id="thumbup${resVO.res_id}" class="float-right">讚:
            <c:if test="${sessionScope.memberVO!=null && thumbrecordresSvc.searchThumbrecordres(sessionScope.memberVO.member_id,resVO.res_id)==false}">
              ${resVO.resthumb}
              <img width="30" src="<%=request.getContextPath()%>/front-end/Discuss/images/thumbwhite.png" onclick="pressThumbres('${sessionScope.memberVO.member_id}','${resVO.res_id}','thumbup${resVO.res_id}')"/>
            </c:if>
            <c:if test="${sessionScope.memberVO!=null && thumbrecordresSvc.searchThumbrecordres(sessionScope.memberVO.member_id,resVO.res_id)==true}">
              ${resVO.resthumb}
              <img width="30" src="<%=request.getContextPath()%>/front-end/Discuss/images/thumborange.png" onclick="pressThumbres('${sessionScope.memberVO.member_id}','${resVO.res_id}','thumbup${resVO.res_id}')"/>
            </c:if>
            <c:if test="${sessionScope.memberVO==null}"> 
              ${resVO.resthumb}
              <img width="30" src="<%=request.getContextPath()%>/front-end/Discuss/images/thumbgray.png"/>
            </c:if>   
        </div>
  </div>
  
  
  <div class="card-body">
    <p class="card-title float-left">
	    <div id="bestans${resVO.res_id}" class="float-right">
	            <c:if test="${resVO.bestres==1}"><!--最佳回應  -->
	               <img width="30" src="<%=request.getContextPath()%>/front-end/Discuss/images/best.png"/>
	            </c:if>
	            <c:if test="${resVO.bestres==0 && sessionScope.memberVO.member_id eq queVO.member_id && sessionScope.memberVO.member_id ne resVO.member_id }"><!-- 發問人=登入者!=回應者 -->
	               <img width="30" onclick="selectBest('${resVO.res_id}','${resVO.belongto}','bestans${resVO.res_id}')" src="<%=request.getContextPath()%>/front-end/Discuss/images/notbest.png"/>
	            </c:if>
	            <c:if test="${resVO.bestres==0 && sessionScope.memberVO.member_id ne queVO.member_id }"><!-- 發問人!=登入者 -->
	               <img width="30" src="<%=request.getContextPath()%>/front-end/Discuss/images/notbest.png"/>
	            </c:if>
	            <c:if test="${resVO.bestres==0 && sessionScope.memberVO.member_id eq queVO.member_id && queVO.member_id eq resVO.member_id }"><!-- 發問人=登入者=回應者 -->
	                <img width="30" src="<%=request.getContextPath()%>/front-end/Discuss/images/notbest.png"/>
	            </c:if>
	     </div>            
        	<c:if test="${resVO.resstatus== 1}">
            <p class="card-text"><small class="text-muted">回應時間:${resVO.restime.toString().substring(0,19)}</small></p>
            <c:if test="${sessionScope.memberVO!=null}">
                <div class="float-right">
                   <a href="<%=request.getContextPath()%>/front-end/Discuss/ResReportForm.jsp?res_id=${resVO.res_id}&que_id=${queVO.que_id}">檢舉回應</a>
                </div><br>
            </c:if>
           <textarea readonly style="border: none; resize: none;" rows="20" cols="100">${resVO.rescontent}</textarea>
        	</c:if>
        <c:if test="${resVO.resstatus== 0 }">
                              文章已被隱藏
        </c:if>
    </p>
    <c:if test="${sessionScope.memberVO.member_id==resVO.member_id}">
        <div class="float-right"><button type="submit" class="btn btn-info">編輯</button></div><br>
    </c:if>
    <br>
    <%--回應檔案 --%> 
    <c:if test="${resVO.resstatus==1 }">
       <c:if test="${resVO.resfile !=null && resVO.getFiletype().trim().substring(0,5) eq 'image'}">
         <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}">
           <img class="threadfile" src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}"/>
         </a>
       </c:if>
       <c:if test="${resVO.resfile !=null && resVO.getFiletype().trim().substring(0,5) eq 'video'}">
         <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}">
           <video class="threadfile" controls loop>
             <source src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}" type="video/webm">
           </video>
         </a>
       </c:if>
       <c:if test="${resVO.resfile !=null && resVO.getFiletype().trim().substring(0,5) eq 'audio'}" >
         <a href="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}">
           <video class="threadfile"  controls loop>
             <source src="<%=request.getContextPath() %>/front-end/Discuss/QRPicHandler.jsp?article_id=${resVO.res_id}" type="audio/webm">
           </video>
         </a>
       </c:if>
       <c:if test="${resVO.resfile ==null }">
                         無圖片
       </c:if>
       </c:if> 
       <c:if test="${resVO.resstatus==0}">
                         文章已被隱藏
       </c:if>
  </div>
</div>
    <input type="hidden" name="action" value="editres">
    <input type="hidden" name="res_id" value="${resVO.res_id}">
</form>    
</c:forEach>
<%@ include file="/front-end/Discuss/Discuss_file/Discuss_page2.file" %> 
</div>   
    <%-------------------回文區塊--------------------------- --%>
<div class="container" id="response"> 
<span id="cross">[X]</span>   
    ${errorMsgs.member_id}
    <form action="<%=request.getContextPath()%>/res/res.do" method="POST"
        enctype="multipart/form-data">
        <table class="table table-striped table-bordered">
            <tr>
                <th>內容:</th>
                <td><textarea  id="rescontent" rows="10" cols="50" name="rescontent" wrap="hard" required></textarea><br>${errorMsgs.rescontent}</td>
            </tr>
            <tr>
                <th>上傳檔案:</th>
                <td><input type="file" name="resfile"><br>${errorMsgs.resfile}</td>
            </tr>
        </table>     
        <input type="hidden" name="member_id" value="${sessionScope.memberVO.member_id}"> 
        <input type="hidden" name="belongto" value="${queVO.que_id}">
        <input type="hidden" name="quemember" value="${queVO.member_id}"> 
        <input type="hidden" name="action" value="response"> 
        <input id="submit" type="submit" value="回應"><input type="button" value="回上頁" id="back" onclick="history.back()">
    </form>
    <br>
<table class="table table-striped table-bordered">       
      <tr>
          <td>上傳檔案預覽:</td>
          <td>
              <img id="picPreview" src=""/>
              <video id="videoPreview" src="" controls="controls" loop="loop" muted></video>
          </td>
      </tr>
</table>         
</div>    
<%@ include file="/front-end/file/footer.file" %>
</body>
</html>