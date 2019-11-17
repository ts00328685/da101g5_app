<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html>
<head>
<title>您正在觀看 ${pageContext.request.queryString}</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/styles.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/G4.css" type="text/css">
<style>
.videoContainer {
	position: relative;
	width: 800px;
	height: 600px;
}

.videoContainer video {
	position: absolute;
	width: 100%;
	height: 100%;
}

.volume_bar {
	position: absolute;
	width: 5px;
	height: 0px;
	right: 0px;
	bottom: 0px;
	background-color: #12acef;
}

.CustomCard {
	padding-top: 20px;
	margin: 10px 0 20px 0;
	background-color: rgba(214, 224, 226, 0.2);
	border-top-width: 0;
	border-bottom-width: 2px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 15px;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	box-shadow: none;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.CustomCard.hoverCustomCard {
	position: relative;
	padding-top: 0;
	overflow: hidden;
	text-align: center;
}

.CustomCard.hoverCustomCard .CustomCardheader {
	background-size: cover;
	height: 85px;
}

.CustomCard.hoverCustomCard .avatar {
	position: relative;
	top: -50px;
	margin-bottom: -50px;
}

.CustomCard.hoverCustomCard .avatar img {
	width: 100px;
	height: 100px;
	max-width: 100px;
	max-height: 100px;
	-webkit-border-radius: 50%;
	-moz-border-radius: 50%;
	border-radius: 50%;
	border: 5px solid rgba(255, 255, 255, 0.5);
}

.CustomCard.hoverCustomCard .info {
	padding: 4px 8px 10px;
}

.CustomCard.hoverCustomCard .info .desc {
	overflow: hidden;
	font-size: 12px;
	line-height: 20px;
	color: #737373;
	text-overflow: ellipsis;
}

.CustomCard.hoverCustomCard .bottom {
	padding: 20px 5px;
	margin-bottom: -6px;
	text-align: center;
}

.btn {
	border-radius: 50%;
	width: 30px;
	height: 30px;
	line-height: 18px;
}

html {
	background: #f4f9f4
}
</style>
</head>
<body onload="connect();">
	<br>
	<br>
	<br>
	<br>

	<video id="testvideo" width="680" height="480" autoplay>
	</video>
				<div id="remotes"></div>
				<script
					src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
				<script
					src="out/simplewebrtc-with-adapter.bundle.js"></script>
				<script>
				
				var testvideo = document.getElementById("testvideo");
					// grab the room from the URL
					var room = location.search && location.search.split('?')[1];
					// create our webrtc connection
					var webrtc = new SimpleWebRTC({

						// the id/element dom element that will hold remote videos
						remoteVideosEl : testvideo,
						// immediately ask for camera access
						autoRequestMedia : true,
						debug : false,
						detectSpeakingEvents : true,
						media : {
							video : false, //關閉參與者的攝像頭
						//關閉參與者的麥克風
						}
					});

					// when it's ready, join if we got a room from the URL
					webrtc.on('readyToCall', function() {
						if (room)
							webrtc.joinRoom(room);
						// you can name it anything
					});
					webrtc.on('localScreen', function() {
					});

					function showVolume(el, volume) {
						if (!el)
							return;
						if (volume < -45) { // vary between -45 and -20
							el.style.height = '0px';
						} else if (volume > -20) {
							el.style.height = '100%';
						} else {
							el.style.height = ''
									+ Math
											.floor((volume + 100) * 100 / 25 - 220)
									+ '%';
						}
					}
					webrtc.on('channelMessage', function(peer, label, data) {
						if (data.type == 'volume') {
							showVolume(document.getElementById('volume_'
									+ peer.id), data.volume);
						}
					});
					webrtc.on('videoAdded',
							function(video, peer) {
// 								console.log('video added', peer);
								console.log(Object.keys(peer));
								var remotes = document
										.getElementById('remotes');
								if (remotes) {
									
									
									testvideo.srcObject = peer.stream;
									
// 									var container = document
// 											.createElement('div');
// 									container.className = 'videoContainer';
// 									container.id = 'container_'
// 											+ webrtc.getDomId(peer);
// 									alert(container.id);
// 									container.appendChild(video);
// 									var vol = document.createElement('div');
// 									vol.id = 'volume_' + peer.id;
// 									vol.className = 'volume_bar';
// 									video.onclick = function() {
// 										video.style.width = video.videoWidth
// 												+ 'px';
// 										video.style.height = video.videoHeight
// 												+ 'px';
// 									};
// 									container.appendChild(vol);
// 									remotes.appendChild(container);
								}
							});
					webrtc.on('videoRemoved', function(video, peer) {
						console.log('video removed ', peer);
	
					});
					webrtc.on('volumeChange', function(volume, treshold) {
						//console.log('own volume', volume);
						showVolume(document.getElementById('localVolume'),
								volume);
					});
					// Since we use this twice we put it here
					function setRoom(name) {
						$('form').remove();
						$('h1').text(name);
						$('#subTitle').text('Link to join: ' + location.href);
						$('body').addClass('active');
					}
					if (room) {
						setRoom(room);
					} else {
						$('form').submit(
								function() {
									var val = $('#sessionInput').val()
											.toLowerCase().replace(/\s/g, '-')
											.replace(/[^A-Za-z0-9_\-]/g, '');
									webrtc.createRoom(val, function(err, name) {
										console.log(' create room cb',
												arguments);

										var newUrl = location.pathname + '?'
												+ name;
										if (!err) {
											history.replaceState({
												foo : 'bar'
											}, null, newUrl);
											setRoom(name);
										} else {
											console.log(err);
										}
									});
									return false;
								});
					}
				</script>
			</div>

			<div class="col-sm-3" style="height: 600px">
				<h1>與直播主聊天</h1>
				<textarea id="messagesArea" class="panel message-area" readonly></textarea>
				<div class="panel input-area">
					<input id="userName" class="text-field" type="text"
						placeholder="暱稱" value="來自路邊的觀眾" /> <input id="message"
						class="text-field" type="text" placeholder="訊息"
						onkeydown="if (event.keyCode == 13) sendMessage();" /> <input
						type="submit" id="sendMessage" class="btn btn-warning btn-sm"
						value="📣" onclick="sendMessage();" />
				</div>
			</div>

		</div>

	</div>


</body>

<!-- =============================================以下為webSocket聊天室(觀眾與直播主聊天)=============================================== -->

<script>
	var MyPoint = "/MyEchoServer/${pageContext.request.queryString}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	// 	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	var endPointURL = "wss://" + window.location.host + webCtx + MyPoint; //上線請使用 https 環境

	var webSocket;

	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			document.getElementById('sendMessage').disabled = false;
		};

		webSocket.onmessage = function(event) {
			if (event.data.indexOf('count=') == 0) {
				document.getElementById("count").innerHTML = "在線人數" + " "
						+ event.data.substring(6);
				;
			} else {
				var messagesArea = document.getElementById("messagesArea");
				var jsonObj = JSON.parse(event.data);
				var message = jsonObj.userName + "  " + jsonObj.message
						+ "\r\n";
				messagesArea.value = messagesArea.value + message;
				messagesArea.scrollTop = messagesArea.scrollHeight;
			}
		};

		webSocket.onclose = function(event) {
		};
	}

	var inputUserName = document.getElementById("userName");
	inputUserName.focus();

	function sendMessage() {
		var userName = inputUserName.value.trim();
		if (userName === "") {
			alert("使用者名稱請勿空白!");
			inputUserName.focus();
			return;
		}

		var inputMessage = document.getElementById("message");
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("訊息請勿空白!");
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
</script>

</html>
