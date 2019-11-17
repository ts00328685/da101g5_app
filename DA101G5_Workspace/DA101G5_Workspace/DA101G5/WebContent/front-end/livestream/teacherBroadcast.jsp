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
// TeacherVO teacherVO = (TeacherVO) session.getAttribute("teacherVO"); 
%>

<html>
<head>

<!-- ---------------------------------- -->
<%@ include file="/front-end/file/head.file"%>
<meta charset="UTF-8">


<title>${hostID}正在直播 </title>
<meta name="viewport"  content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- <link rel="stylesheet" type="text/css" 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" type="text/css" 	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700"> -->
<!-- <link rel="stylesheet" type="text/css" 	href="https://cdn.bootcss.com/limonte-sweetalert2/7.33.1/sweetalert2.css"> -->
<!-- <script src="https://cdn.bootcss.com/limonte-sweetalert2/7.33.1/sweetalert2.all.min.js"></script> -->
<!-- <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script> -->
<!-- <script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script> -->
<script	src="out/simplewebrtc-with-adapter.bundle.js"></script>
<script src="https://webrtc.github.io/adapter/adapter-latest.js"></script>

<title>懶骨雞</title>

<style>
.videoContainer {
    padding: 0 1px;
    COLOR: #FFFF;
    border: solid 5px;
    width:1400px;
    height:900px;
    background-image: url( 'out/blue_border.png' );
    background-size:     contain; 
        background-repeat:   no-repeat;
    background-position: center center; 
}

#localVideo{
height:500px;
/* width:889px; */
border:2px rgb(207, 223, 239) solid;
border-radius: 10px;
margin-bottom: 10px;
}
</style>


</head>
<body onload="connect();" onunload="disconnect();">
<%@ include file="/front-end/file/header.jsp"%>


<div class="container-fluid justify-content-between row align-items-center" style="padding-top:200px;padding-bottom:200px;">



<!-- ======================實況屋====== -->
<!-- 				<div class=" align-items-center videoContainer"style="padding-top:10%;padding-left:200px;"> -->
<!-- 					<video id="localVideo"></video> -->
<!-- 					<div id="localVolume" class="volume_bar"></div> -->
					
<!-- 				</div> -->
				<iframe id="videoIframe" allowfullscreen allow="microphone; camera"  frameborder="0" style="padding-left:50px;width:1190px;height:450px;"></iframe>
					<script>
					var url = window.location.protocol + '//' + window.location.hostname + ':8083';
					var videoIframe = document.getElementById('videoIframe');
					videoIframe.setAttribute("src", url); 
					</script>
				
				
				<!-- 	=======聊天室================================================================================			 -->
<!-- 				<h3 id="statusOutput" class="statusOutput"></h3> -->
	<textarea  style="resize:none ;border-radius:10px;position:absolute; left:1300px;top:200px; z-index:1;overflow-y:hidden;overflow-x:hidden; border:0;background-color:rgba(192, 192, 192, 0.6)"rows="17"id="messagesArea" class="justify-content-end panel message-area col-2" readonly></textarea>
<!-- 	<div class="panel input-area" style="position:absolute;left:1350px;top:600px; z-index:1;overflow-y:hidden;overflow-x:hidden; border:0;background-color:transparent"> -->
<!-- 		<input id="userName" class="text-field" type="text" -->
<!-- 			placeholder="User name" />  -->
			<input style="position:absolute;left:1330px;top:650px;width:230px;"id="message" class="text-field"
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
	
	

				
</div>



	<br><br><br><br>


<%@ include file="/front-end/file/footer.file"%>

</body>



<script>
//WEBRTC javascript
//         	// var count=0;
//             // grab the room from the URL
<%--             var room = location.search && location.search.split('?')[1];  由Query String傳人直播主ID(hostID) --%>
//             // create our webrtc connection
//             var webrtc = new SimpleWebRTC({
//                 // the id/element dom element that will hold "our" video
//                 localVideoEl: 'localVideo',
//                 // the id/element dom element that will hold remote videos
//                 remoteVideosEl: '',
//                 // immediately ask for camera access
//                 autoRequestMedia: true,
//                 debug: false,
//                 detectSpeakingEvents: true
//             });

//             // when it's ready, join if we got a room from the URL
//             webrtc.on('readyToCall', function () {
//                 // you can name it anything
//                 if (room) webrtc.joinRoom(room);
//             });

//             function showVolume(el, volume) {
//                 if (!el) return;
//                 if (volume < -45) { // vary between -45 and -20
//                     el.style.height = '0px';
//                 } else if (volume > -20) {
//                     el.style.height = '100%';
//                 } else {
//                     el.style.height = '' + Math.floor((volume + 100) * 100 / 25 - 220) + '%';
//                 }
//             }
//             webrtc.on('channelMessage', function (peer, label, data) {
//                 if (data.type == 'volume') {
//                     showVolume(document.getElementById('volume_' + peer.id), data.volume);
//                 }
//             });
// //             webrtc.on('videoAdded', function () {
// //             	count++;
// //             	document.getElementById('count').innerHTML = "在線觀看人數"+" "+count;
// //             });
// //             webrtc.on('videoRemoved', function (video, peer) {
// //             	count--;
// //             	document.getElementById('count').innerHTML = "在線觀看人數"+count;
// //                 console.log('video removed ', peer);
// //                 var remotes = document.getElementById('remotes');
// //                 var el = document.getElementById('container_' + webrtc.getDomId(peer));
// //                 if (remotes && el) {
// //                     remotes.removeChild(el);
// //                 }
// //             });
//             webrtc.on('volumeChange', function (volume, treshold) {
//                 //console.log('own volume', volume);
//                 showVolume(document.getElementById('localVolume'), volume);
//             });

            
//             // Since we use this twice we put it here
// //             function setRoom(name) {
// //                 $('form').remove();
// //                 $('#subTitle').text('Link to join: ' + location.href);
// //                 $('body').addClass('active');
       
// //             }

//             if (room) {
//                 setRoom(room);
//             } else {
//                 $('form').submit(function () {
//                     var val = $('#sessionInput').val().toLowerCase().replace(/\s/g, '-').replace(/[^A-Za-z0-9_\-]/g, '');
//                     webrtc.createRoom(val, function (err, name) {
//                         console.log(' create room cb', arguments);
//                         var newUrl = location.pathname + '?' + name;
//                         if (!err) {
//                             history.replaceState({foo: 'bar'}, null, newUrl);
//                             setRoom(name);
                            
//                             var flag = "true";
//                             function onlyOne() {
//                                 if(flag) {
//                                     webrtc.on('videoAdded', function (video, peer) {
//                                         var remotes = document.getElementById('remotes');
//                                         if (remotes) {
//                                             var container = document.createElement('div');
//                                             container.className = 'videoContainer';
//                                             container.id = 'container_' + webrtc.getDomId(peer);
//                                             container.appendChild(video);
//                                             var vol = document.createElement('div');
//                                             vol.id = 'volume_' + peer.id;
//                                             vol.className = 'volume_bar';
//                                             video.onclick = function () {
//                                                 video.style.width = video.videoWidth + 'px';
//                                                 video.style.height = video.videoHeight + 'px';
//                                             };
//                                             container.appendChild(vol);
//                                             remotes.appendChild(container);
//                                         }
//                                     });
//                                 }
//                                 flag = "false";
//                             }  
                      
//                         } else {
//                             console.log(err);
//                         }
//                     });
//                     return false;
//                 });
//             }

        </script>
        
<!--        聊天室 ============================================================ -->
<script>
	var MyPoint = "/TogetherWS/${memberVO.member_id}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;

// 	var statusOutput = document.getElementById("statusOutput");
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
			var message =  jsonObj.userName + ": " +jsonObj.message + "\r\n";
			messagesArea.value = messagesArea.value + message;
			messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket Disconnected");
		};
	}
	var userName ="${memberVO.mem_nick}";
// 	var inputUserName = document.getElementById("userName");
// 	inputUserName.focus();

	function sendMessage() {
// 		var userName = inputUserName.value.trim();
// 		if (userName === "") {
// 			alert("Input a user name");
// 			inputUserName.focus();
// 			return;
// 		}

		var inputMessage = document.getElementById("message");
		var message = inputMessage.value.trim();

		if (message === "") {
			swal("跟學生說點話吧!!");
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
        
</html>