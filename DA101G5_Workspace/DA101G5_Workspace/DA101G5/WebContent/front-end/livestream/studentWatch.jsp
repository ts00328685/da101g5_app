<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%
String time_order =  (String)request.getAttribute("time_order"); 
%>
<%
request.setAttribute("time_order", time_order);
%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/front-end/file/head.file"%>

<title>懶骨雞</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
<!-- <script -->
<!-- 	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script> -->
<script src="out/simplewebrtc-with-adapter.bundle.js"></script>

<style>
.star{
   display: inline-block;
   font-size: 0;
   position:relative;
}
.star-item{
   display: inline-block;
   width: 20px;
   height:20px;
   cursor: pointer;
   background: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAoCAMAAADJ7yrpAAAAYFBMVEUAAAD92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92Un92UmCTPEWAAAAH3RSTlMA9iwe++hAgPLJo13i3Td8JO3Sk4xtYkYUC76cVBIjGgBFCwAAAMtJREFUKM+1kUkOhCAQRUEFBHGep67737JRlEFNJ73wbyAv9WtRD/1O3z/AqrqzDqC7spEAkPECGagwlwRJE28wbpJAk5xjcIJ5rmBBwAsp9nLpsvJYkMWWxRk6QrHZSJFJesIU2UQnjCwLwSQ0kG4zQmzzdmcCuA5VocaQGNiyQX8G1qK/8q6jNH1wtCx3RxwhfnUkM3Vs6ToSdNUDK52NI6JL+4R1JD/HYaXriOsP9xxN+p08R0JV1ZlnzxELRBSJIPccSe1IvufoCxGAFoLwSVOBAAAAAElFTkSuQmCC') center top no-repeat;
}
input[type="radio"]{
   position: absolute;
   clip: rect(0,0,0,0)
}
input[type="radio"]:checked+.star-item~.star-item{
    background-position: center bottom;
}
.star:hover label.star-item{
   background-position: center top!important;
}
label.star-item:hover~.star-item{
   background-position: center bottom!important;
}
.star-item:after{
   position:absolute;
   width:100px;
   font-size:14px;
   height:20px;
   line-height:20px;
   right:0;
   margin-right:-105px;
   color:#666
}
.star:hover .star-item:after{
  content:''!important
}


input[type="radio"]:checked+.star-item:after{
   content:attr(title)
}

.star:hover .star-item:hover:after{
   content:attr(title)!important
}
.videoContainer {
	padding: 0 1px;
	COLOR: #FFFF;
	border: solid 5px;
	width: 1400px;
	height: 900px;
	background-image: url( 'out/blue_border.png' );
	background-size: contain;
	background-repeat: no-repeat;
	background-position: center center;
}

#localVideo {
	height: 500px;
/* 	width:889px; */
	border: 2px rgb(207, 223, 239) solid;
	border-radius: 10px;
	margin-bottom: 10px;
}
</style>
</head>
<body onload="connect();" onunload="disconnect();">

	<%@ include file="/front-end/file/header.jsp"%>

<div class="container-fluid justify-content-between row align-items-center" style="padding-top:200px;padding-bottom:200px;">

<!-- 				<div class=" align-items-center videoContainer"style="padding-top:10%;padding-left:200px;"> -->
<!-- 					<video autoplay controls id="localVideo"></video> -->
<!-- 					<div id="localVolume" class="volume_bar"></div> -->
					<iframe id="videoIframe" allow="microphone; camera"  frameborder="0" style="padding-left:50px;width:1190px;height:450px;"></iframe>
					<script>
					var url = window.location.protocol + '//' + window.location.hostname + ':8083';
					var videoIframe = document.getElementById('videoIframe');
					videoIframe.setAttribute("src", url); 
					</script>
				<!-- 	=======聊天室================================================================================			 -->
<!-- 				<h3 id="statusOutput" class="statusOutput"></h3> -->
	<textarea  style="resize:none ;border-radius:10px;position:absolute; left:1300px;top:200px; z-index:9999;overflow-y:hidden;overflow-x:hidden; border:0;background-color:rgba(192, 192, 192, 0.6)"rows="17"id="messagesArea" class="justify-content-end panel message-area col-2" readonly></textarea>
<!-- 	<div class="panel input-area" style="position:absolute;  z-index:1;overflow-y:hidden;overflow-x:hidden; border:0;background-color:transparent;" > -->
<!-- 		<input id="userName" class="text-field" type="text" -->
<!-- 			placeholder="User name" />  -->
			<input id="message" class="text-field"style="position:absolute;left:1330px;top:650px;width:230px;"
			type="text" placeholder="Message"
			onkeydown="if (event.keyCode == 13) sendMessage();" /> 
<!-- 			<input -->
<!-- 			type="submit" id="sendMessage" class="button" value="Send" -->
<!-- 			onclick="sendMessage();" />  -->
<!-- 			<input type="button" id="connect" -->
<!-- 			class="button" value="Connect" onclick="connect();" /> <input -->
<!-- 			type="button" id="disconnect" class="button" value="Disconnect" -->
<!-- 			onclick="disconnect();" /> -->
<!-- 	</div> -->
					<!-- Button trigger modal -->
<button  style="position: absolute;top: 750px;right:200px;"type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
 評價老師
</button>
<button  style="position: absolute;top: 120px;left:50px;"type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#reportModal">
 檢舉老師
</button>
				</div>
				
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalCenterTitle">課程評價</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
			
			<div class="star" style="font-size:40px;">
       		<input value="1"type="radio" name="radiobutton" id="item01" checked />  <!--这里设置checked初始状态-->
		    <label class="star-item" for="item01" title="藍瘦香菇"></label>
		    <input value="2"type="radio" name="radiobutton" id="item02" />
		    <label class="star-item" for="item02" title="很差"></label>
		    <input value="3"type="radio" name="radiobutton" id="item03" />
		    <label class="star-item" for="item03" title="一般"></label>
		    <input value="4"type="radio" name="radiobutton" id="item04" />
		    <label class="star-item" for="item04" title="很好"></label>
		    <input value="5"type="radio" name="radiobutton" id="item05" />
		    <label class="star-item" for="item05" title="完美"></label>
			</div>

       
      </div>
      <div class="modal-footer">
        <!-- <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
        <input type="hidden" id="reportCkecked" value="0">
        <input id="judge"type="hidden" name="c_judge"  >
        <input type="hidden" name="action" value="evaluate">
         <input type="hidden" name="time_order_id" value="${time_order_id}"> 
        
        <button onclick="sendState()" type="submit" class="btn btn-primary">送出評價</button>

      </div>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalCenterTitle">課程檢舉</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

<textarea  id="report_text"style="height:300px; font-size:24px"class="single-textarea"  onfocus="this.placeholder = ''" onblur="this.placeholder = '課程檢舉'"
required name="report_text"></textarea>			
			

       
      </div>
      <div class="modal-footer">
        <!-- <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
        
<!--         <input id="judge"type="hidden" name="c_judge"  > -->
        <input type="hidden" name="action" value="insert">
         <input type="hidden" name="time_order_id" value="${time_order_id}"> 
        <input type="hidden" id="isckecked" value="0">
        <button onclick="sendReport()" type="submit" class="btn btn-primary">送出檢舉</button>

      </div>
    </div>
  </div>
</div>

	


<%@ include file="/front-end/file/footer.file"%>
</body>
<script type="text/javascript">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">				

swal( <c:forEach var="message" items="${errorMsgs}"> 
	"${message}"
</c:forEach> 

);
</c:if>	



	
	


</script>
<!-- ================================================================================== -->
<script>
	var MyPoint = "/TogetherWS/${memberVO.member_id}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			updateStatus("WebSocket Connected");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
			var jsonObj = JSON.parse(event.data);
			var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
			messagesArea.value = messagesArea.value + message;
			messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket Disconnected");
		};
	}

// 	var inputUserName = document.getElementById("userName");
// 	inputUserName.focus();

	function sendMessage() {
		var userName ="${memberVO.mem_nick}";
// 		var userName = inputUserName.value.trim();
// 		if (userName === "") {
// 			alert("Input a user name");
// 			inputUserName.focus();
// 			return;
// 		}

		var inputMessage = document.getElementById("message");
		var message = inputMessage.value.trim();

		if (message === "") {
			swal("跟老師說點話吧!!");
			inputMessage.focus();
		} else {
			var jsonObj = {
				"userName" : userName,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}

	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
</script>

<!-- =====評價======================================================================================= -->
<script type="text/javascript">

	function   getRadioBoxValue(radiobutton){ 
        var obj = document.getElementsByName(radiobutton);          
         for(i   =   0;   i   <   obj.length;   i++){ 
            if(obj[i].checked){ 
               return   obj[i].value;
             } 
          }         
            return "undefined";      
    } 
	

	
    
var xhr = null;


function sendState(){ 
	
  var xhr = new XMLHttpRequest();
  //設定好回呼函數   
  xhr.onload = function (){
      if( xhr.status == 200){
        // document.getElementById("showPanel").innerHTML = xhr.responseText;
        showEmployee(xhr.responseText);
      }else{
        alert( xhr.status );
      }//xhr.status == 200
  };//onload 
  
  //建立好Get連接
  
  function abc(){
		
	  
		document.getElementById("judge").value=getRadioBoxValue("radiobutton");
		
		
	    }
var url = location.href;
var time_order_id;
//再來用去尋找網址列中是否有資料傳遞(QueryString)
if(url.indexOf('?')!=-1)
{
    //之後去分割字串把分割後的字串放進陣列中
    var ary1 = url.split('?');
    //此時ary1裡的內容為：
    //ary1[0] = 'index.aspx'，ary2[1] = 'id=U001&name=GQSM'
    
    //下一步把後方傳遞的每組資料各自分割
    var ary2 = ary1[1].split('&');
    //此時ary2裡的內容為：
    //ary2[0] = 'id=U001'，ary2[1] = 'name=GQSM'
    
    //最後如果我們要找id的資料就直接取ary[0]下手，name的話就是ary[1]
    var ary3 = ary2[1].split('=');
    //此時ary3裡的內容為：
    //ary3[0] = 'id'，ary3[1] = 'U001'
    
    //取得id值
     time_order_id = ary3[1];
    
}
  
  
//   var time_order_id = document.getElementById("time_order_id").value;
  var c_judge = getRadioBoxValue("radiobutton");
  var reportCkecked=document.getElementById("reportCkecked").value;
  if(reportCkecked==1){
	  swal("已經評價完囉!!");
  }else{
	  swal("感謝給予課程評價!!");
  var url7= "<%=request.getContextPath()%>/time_order/time_order.do?action=evaluate&time_order_id= " + time_order_id
  						+"&c_judge=" + c_judge;
  xhr.open("GET",url7,true); 
  //送出請求 
  xhr.send( null );
  document.getElementById("reportCkecked").value=1;
  }
  
}

</script>

<!-- ===========課程檢舉========================================================= -->
<script type="text/javascript">
	
    
var xhr = null;


function sendReport(){ 
	
  var xhr = new XMLHttpRequest();
  //設定好回呼函數   
  xhr.onload = function (){
      if( xhr.status == 200){
        // document.getElementById("showPanel").innerHTML = xhr.responseText;
        showEmployee(xhr.responseText);
      }else{
        alert( xhr.status );
      }//xhr.status == 200
  };//onload 
  
  //建立好Get連接
  
  
var url = location.href;
var time_order_id;
//再來用去尋找網址列中是否有資料傳遞(QueryString)
if(url.indexOf('?')!=-1)
{
    //之後去分割字串把分割後的字串放進陣列中
    var ary1 = url.split('?');
    //此時ary1裡的內容為：
    //ary1[0] = 'index.aspx'，ary2[1] = 'id=U001&name=GQSM'
    
    //下一步把後方傳遞的每組資料各自分割
    var ary2 = ary1[1].split('&');
    //此時ary2裡的內容為：
    //ary2[0] = 'id=U001'，ary2[1] = 'name=GQSM'
    
    //最後如果我們要找id的資料就直接取ary[0]下手，name的話就是ary[1]
    var ary3 = ary2[0].split('=');
    //此時ary3裡的內容為：
    //ary3[0] = 'id'，ary3[1] = 'U001'
    
    //取得id值
     time_order_id = ary3[1];
    
}
  
  
//   var time_order_id = document.getElementById("time_order_id").value;
//   var c_judge = getRadioBoxValue("radiobutton");

	var report_text=document.getElementById("report_text").value;
	var isckecked=document.getElementById("isckecked").value;
if(report_text.trim()!=""&&isckecked==0){
	document.getElementById("isckecked").value=1;
  var urlReport= "<%=request.getContextPath()%>/course_report/course_report.do?action=insert&time_order_id= " + time_order_id
  						+"&report_text=" + report_text;
  xhr.open("GET",urlReport,true); 
  //送出請求 
  xhr.send( null );
  swal("感謝您的建議!!");
}else if(isckecked==1){
	swal("已經收到您的建議囉!!");
	
}else{
	swal("請輸入內容!!");
}
 
 
}

</script>





<!-- ======直播視窗=================================================================== -->
<script>
// 	var testvideo = document.getElementById("localVideo");
// 	// grab the room from the URL
// 	var room = location.search && location.search.split('?')[1];
// 	// create our webrtc connection
// 	var webrtc = new SimpleWebRTC({

// 		// the id/element dom element that will hold remote videos
// 		remoteVideosEl : testvideo,
// 		// immediately ask for camera access
// 		autoRequestMedia : true,
// 		debug : false,
// 		detectSpeakingEvents : true,
// 		media : {
// 			video : false, //關閉參與者的攝像頭
// 		//關閉參與者的麥克風
// 		}
// 	});

// 	// when it's ready, join if we got a room from the URL
// 	webrtc.on('readyToCall', function() {
// 		if (room)
// 			webrtc.joinRoom(room);
// 		// you can name it anything
// 	});
// 	webrtc.on('localScreen', function() {
// 	});

// 	function showVolume(el, volume) {
// 		if (!el)
// 			return;
// 		if (volume < -45) { // vary between -45 and -20
// 			el.style.height = '0px';
// 		} else if (volume > -20) {
// 			el.style.height = '100%';
// 		} else {
// 			el.style.height = '' + Math.floor((volume + 100) * 100 / 25 - 220)
// 					+ '%';
// 		}
// 	}
// 	webrtc.on('channelMessage', function(peer, label, data) {
// 		if (data.type == 'volume') {
// 			showVolume(document.getElementById('volume_' + peer.id),
// 					data.volume);
// 		}
// 	});
// 	webrtc.on('videoAdded', function(video, peer) {
// 		// 								console.log('video added', peer);
// 		console.log(Object.keys(peer));
// 			testvideo.srcObject = peer.stream;

// 	});
// 	webrtc.on('videoRemoved', function(video, peer) {
// 		console.log('video removed ', peer);

// 	});
// 	webrtc.on('volumeChange', function(volume, treshold) {
// 		//console.log('own volume', volume);
// 		showVolume(document.getElementById('localVolume'), volume);
// 	});
// 	// Since we use this twice we put it here
// // 	function setRoom(name) {
// // 		$('form').remove();
// // 		$('h1').text(name);
// // 		$('#subTitle').text('Link to join: ' + location.href);
// // 		$('body').addClass('active');
// // 	}
// 	if (room) {
// 		setRoom(room);
// 	} else {
// 		$('form').submit(
// 				function() {
// 					var val = $('#sessionInput').val().toLowerCase().replace(
// 							/\s/g, '-').replace(/[^A-Za-z0-9_\-]/g, '');
// 					webrtc.createRoom(val, function(err, name) {
// 						console.log(' create room cb', arguments);

// 						var newUrl = location.pathname + '?' + name;
// 						if (!err) {
// 							history.replaceState({
// 								foo : 'bar'
// 							}, null, newUrl);
// 							setRoom(name);
// 						} else {
// 							console.log(err);
// 						}
// 					});
// 					return false;
// 				});
// 	}
</script>


</html>
