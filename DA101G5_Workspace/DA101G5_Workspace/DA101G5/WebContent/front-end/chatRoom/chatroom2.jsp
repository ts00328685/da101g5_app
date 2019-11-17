<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--================ chatroom Area  =================-->
 <html>
<style type="text/css">
    	.qqBox{
	width: 798px;
	height: 600px;
	border: 1px solid #ccc;
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	margin:auto;
	display: -webkit-box;
	-webkit-box-orient: vertical;
}
.BoxHead{
	width: 100%;
	height: 52px;
	background: #0099FF;
	display: -webkit-box;
	-webkit-box-orient: horizontal;
}
.headImg{
	width: 44px;
	height: 44px;
	border-radius: 50%;
	margin:0 10px;
}
.headImg img{
	width: 100%;
	height: 100%;
	border-radius:50%;
}
.internetName{
	width: auto;
	height: 52px;
	line-height: 52px;
	color: white;
}
.context{
-webkit-box-flex: 1;
display: -webkit-box;
-webkit-box-orient: horizontal;
}
.conLeft{
	width: 200px;
	height:548px;
	overflow: auto;
	background: #C7DFF7	;
}
.conLeft::-webkit-scrollbar{
	width: 0;
}
.conLeft ul{
	list-style: none;
	margin: 0;
	padding: 0;
}
.conLeft li{
	width: 100%;
	height: 62px;
	display: -webkit-box;
	-webkit-box-orient: horizontal;
}

.conLeft li:hover{
	cursor:pointer;
}

.conLeft li .liLeft{
	width:30%;
	height: 100%;
}
.liLeft img{
	width: 44px;
	height: 44px;
	border-radius: 50%;
	margin:0 10px;
}
.liRight{
	display:flex;
}
.liRight span{
	display:block;
	font-size: 16px;
	height: 10px;
	line-height: 31px;
}
.liRight span:last-child{
	font-size: 14px;
	color: #767676;
	line-height:15px;
	overflow: hidden;
}
.conRight{
	-webkit-box-flex: 1;
	display: -webkit-box;
	-webkit-box-orient: vertical;
}
.Righthead{
	width: 100%;
	height: 42px;
	border-bottom: 1px solid #ccc;
}
.headName{
	width: auto;
	height: 100%;
	line-height: 42px;
	margin-left: 20px;
	font-family: "微软雅黑";
	font-size: 18px;
	float: left;
}
.headConfig{
	width: 20%;
	float: right;
	height: 100%;
	
}
.headConfig ul{
	list-style: none;
	margin: 0;
	padding: 0;
	display: -webkit-box;
	-webkit-box-orient: horizontal;
}
.headConfig li{
	margin:10px 5px;
}
.RightCont{
	-webkit-box-flex: 1;
	overflow-y: scroll;
}

.RightCont::-webkit-scrollbar{
	width: 15px;
}
.RightCont ul{
	list-style: none;
	margin: 0;
	padding: 0;
}
.RightCont li{
	width: 100%;
	height: 50px;
	/*display: -webkit-box;
	-webkit-box-orient: horizontal;*/
	margin-top: 70px;
}
.nesHead{
	width: 44px;
	height: 44px;
	border-radius: 50%;
	margin-left:15px ;
	float: left;
}
.nesHead img{
	width: 44px;
	height: 44px;
	border-radius: 50%;
}
.news img{
	width: 100px;
	height: 100px;
	margin:10 10px;
}
.news .jiao{
	position: absolute;
	left: -8px;
	top: 10px;
}
 .news .Expr{
	width: 50px;
	height: 30px;
	margin: 5px;
}
.answerHead{
	width: 44px;
	height: 44px;
	border-radius: 50%;
	margin-left:15px ;
	float: right;
}
.answerHead img{
	width: 44px;
	height: 44px;
	border-radius: 50%;
}
.answers{
	width: auto;
	height: auto;
	background: #eeeeee;
	padding:5px 20px;
	margin: 4px;
	line-height: 40px;
	font-size: 14px;
	border-radius:10px;
	margin-left: 10px;
	position: relative;
	float: right;
}
.news{
	width: auto;
	height: auto;
	background: #FFFF66	;
	padding:5px 20px;
	margin: 4px;
	line-height: 40px;
	font-size: 14px;
	border-radius:10px;
	margin-left: 10px;
	position: relative;
	float: left;
}
.answers img{
	width: 100px;
	height: 100px;
	
	margin:10 10px;
	
}
.answers .jiao{
	position: absolute;
	right: -8px;
	top: 10px;
}
.RightFoot{
	width: 100%;
	height: 118px;
	border-top: 1px solid #ccc;
	position: relative;
}

.emjon{
	width: 360px;
	height: 200px;
	border: 1px solid #ccc;
	position: absolute;
	left:0px;
	top:-200px;
	display: none;
	background: #fff;
}
.emjon ul{
	list-style: none;
	margin:0;
}
.emjon ul li{
	width: 50px;
	height: 30px;
	margin: 5px;
	float: left;
	
}
.emjon ul li:hover{
box-shadow:5px 5px 5px #888888;
transform: scale(1.2);
	
}
.emjon ul li img{
	width: 100%;
	height: 100%;
	
}

.footTop{
	width: 100%;
	height: auto;
}
.footTop ul{
	list-style: none;
	margin: 0;
	padding: 0;
	display: -webkit-box;
	-webkit-box-orient: horizontal;
}
.footTop li{
	margin: 5px 10px;
}
.sendBtn{
	width: 68px;
	height: 25px;
	background: #0188fb;
	border: none;
	position: absolute;
	bottom: 10px;
	right: 10px;
	color: white;
}
.bg{
	background: #ebebec;
}

.getMesgs {
    background-color: orange;
    border-radius: 10px;
    width: 10px;
    margin-left:70px;
    margin-top:15px;
    
}

.chatImg{
	width:100px;
	height:100px;
}

#chatroom{
	position:fixed;
	top:7%;
	right:40%;
	z-index:999999;
}


    </style>

<!-- ------------------------------------------------- -->
<body  onload="connect();" onunload="disconnect();">
<button type="button" class="btn btn-primary"  style="width:80px;height:80px;position:fixed;z-index:99999;top:87%;left:92%;border-radius:100px;background-color:rgba(254,153,41,0.95) ;border:1px solid  rgb(254,196,79) ;box-shadow:-3px 2px rgba(254,196,79,0.7)" id="btnchat"><i class="fas fa-comment fa-2x" style="boc-shadow:2px 5px;"></i></button>

<div style="padding-top:400px;display:none;" id="chatroom" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" >
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    <!-- ----------------------------聊天室內容-------------------------------- -->

      	<div class="qqBox" style="background-color: #FFF; ">
	<div class="BoxHead">
		<div class="headImg">
			<img src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id=${memberVO.getMember_id()}">
		</div>
		<div class="internetName">${memberVO.mem_nick}</div>
	</div>
	<div class="context">
		<div class="conLeft">
		
			<ul>
			
			<jsp:useBean id="FindChatFriendProfile" class="com.member.model.MemberService"/>
			<jsp:useBean id="FindChatFriends" class="com.friendmanage.model.FriendManageService"/>
			<c:forEach var="allFriends" items="${FindChatFriends.getOneAllFriends(memberVO.member_id)}">
			<c:if test="${allFriends.friend_status == '1'}" var="condition" scope="page" >		
				<li  id="${allFriends.friend_member_fid}">
					<div class="liLeft"><img src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id=${allFriends.friend_member_fid}"></div>
					<div class="liRight">
						<p class="whoUAre" id="whoUAre" style="display:none;">${allFriends.friend_member_fid}</p>
						<span class="badge-dot" id="dot_${allFriends.friend_member_fid}" style="width:8px;height:8px;margin-top:5px;display:none;"></span>
						<span class="intername">${FindChatFriendProfile.getOneMember(allFriends.friend_member_fid).mem_nick}</span>
						<span class="getMesgs" id="getMesgs_${allFriends.friend_member_fid}" style="display:none;"></span>
						<span class="infor"></span>
					</div>
				</li>
			</c:if>
			</c:forEach>
							
			</ul>
		</div>
		<div class="conRight">
			<div class="Righthead">
				<div class="headName"></div>				
			</div>
			<div class="RightCont">
<!-- 			<textarea class="newsList" id="messagesArea" style="" readonly></textarea> -->
				<ul class="newsList">
				
				</ul>
			</div>
			<div class="RightFoot">
				 <div class="emjon">
					<ul>						
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/510285.jpg"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/clown-face_1f921.png"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/confused-face_1f615.png"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/face-savouring-delicious-food_1f60b.png"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/face-throwing-a-kiss_1f618.png"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/fearful-face_1f628.png"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/grinning-face_1f600.png"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/kissing-face-with-closed-eyes_1f61a.png"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/rolling-on-the-floor-laughing_1f923.png"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/smiling-face-with-heart-shaped-eyes_1f60d.png"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/smirking-face_1f60f.png"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/thumbs-up-sign_1f44d.png"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/smiling-face-with-sunglasses_1f60e.png"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/sleepy-face_1f62a.png"/></li>
						<li><img src="<%= request.getContextPath() %>/front-end/chatRoom/images/persevering-face_1f623.png"/></li>

					</ul>
				</div>
				<div class="inputBox input-area panel">
					<div id="dope" style="width: 90%;height: 75px; border: 1px solid #AAAAAA; border-radius:5px;margin-top:2px;" onkeydown="if (event.keyCode == 13) sendMessage();" placeholder="please enter your message here..." contenteditable="false"></div>
					<button id="emjonshow" style="background-image:url('<%= request.getContextPath() %>/front-end/chatRoom/images/smiling-face-with-open-mouth-and-smiling-eyes_1f604.png');background-repeat:no-repeat;width:30px;height:30px;"></button>
					<input type="file" id="chat_picture" name="chat_pic" onchange="readURL(this)" accept="image/gif, image/jpeg, image/png" disabled>
					
					<div id="chatShowDiv"></div>
					<input type="submit" class="sendBtn button" style="background-color:#F5F5F5;" onclick="sendMessage();") disabled/>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<div class="messageshowshow">
	<div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
  <div class="toast-header">
    <img id="mesgsimg"  class="rounded mr-2" >
    <strong class="mr-auto">Bootstrap</strong>
    <small class="text-muted">11 mins ago</small>
    <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="toast-body">
    Hello, world! This is a toast message.
  </div>
</div>




<script>
	var MyPoint = "/TwoChat/${memberVO.member_id}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	console.log(MyPoint);
	console.log('${memberVO.member_id}');
	
	
	var webSocket;

	function connect() {		
		
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			
		};

		webSocket.onmessage = function(event) {			
			var whoUTalk = $(".bg").children('.liRight').children('.whoUAre').text();
			var whoUTalkName = $(".bg").children('.liRight').children('.intername').text();
			var whoIAm = "${memberVO.member_id}";
			var whoIAmName = "${memberVO.mem_nick}";
			var messagesArea = document.getElementById('messagesArea');
			var jsonObj = JSON.parse(event.data);
			
			var finalMesgs = "";
			
			
			<!-----訊息種類判斷---------->
			if(jsonObj.Mtype.indexOf("history") >= 0){	
				var message = jsonObj.message;
				
				<!------message是字串------------------->
				var newmm = JSON.parse(message);
				
				
				var finalStr;
				for(var i = 0; i < newmm.length; i++){
					
					if(newmm[i].sender == whoUTalk){
						
						newmm[i].sender = whoUTalkName;
						var str='';
						str+='<li>'+'<div class="nesHead"><img src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id='+whoUTalk+'">'+'</div>'+'<div class="news">'+newmm[i].message+'</div>'+'</li>';
						console.log(str);
						$('.newsList').append(str);
						$('.conLeft').find('li.bg').children('.liRight').children('.infor').text();
						$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
					}else{
						
						var answer='';
						newmm[i].sender = whoIAmName;
						answer+='<li>'+'<div class="answerHead"><img src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id=${memberVO.getMember_id()}"></div>'+'<div class="answers">'+newmm[i].message+'</div>'+'</li>';						
						$('.newsList').append(answer);
						$('.conLeft').find('li.bg').children('.liRight').children('.infor').text();
						$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
					}
					
					
								
				}
				
				

			}else if (jsonObj.Mtype.indexOf("chat") >= 0){
				
			<!--------判斷是否為當前的聊天對象---------->
				if(jsonObj.sender == whoUTalk){
					var str='';
					str+='<li>'+'<div class="nesHead"><img src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id='+whoUTalk+'">'+'</div>'+'<div class="news">'+jsonObj.message+'</div>'+'</li>';
					$('.newsList').append(str);
				}else{
					document.getElementById("getMesgs_"+jsonObj.sender).style.display="inline-block";
					document.getElementById('mesgsimg')
				
					$('#toastshow').toast('show');

				}
			}else if (jsonObj.Mtype.indexOf("open") >= 0){
				var users = jsonObj.users;
				var size = Object.keys(users).length;				
				for(var i = 0; i < size ; i++){
					if(users[i] != whoIAm){
// 						document.getElementById("dot_" + users[i]).style.display="block";	
// 						document.getElementById("dot_" + users[i]).style.backgroundColor="green";
					}
				}
				
				
			}else if(jsonObj.Mtype.indexOf("image") >= 0){
				var str='';
				str+='<li>'+'<div class="nesHead"><img src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id='+whoUTalk+'">'+'</div>'+'<div class="news">'+jsonObj.message+'</div>'+'</li>';
				$('.newsList').append(str);
			}else{
// 				document.getElementById("dot_" + jsonObj.user).style.display="none";	
			}			
			
			
			
			$('.conLeft').find('li.bg').children('.liRight').children('.infor').text();
			$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
			
		};

		webSocket.onclose = function(event) {
			
		};
	}

	function sendMessage(value){
		var picturePath = value;
		var inputMessage = document.getElementById('dope');
		var message = $('#dope').html().trim();
		var message2 =  $('#dope').text().trim();
		console.log(message2);
		var sender = "${memberVO.member_id}";
		var receiver = $(".bg").children('.liRight').children('.whoUAre').text();
		
		if(receiver != ""){
			
			if(picturePath != null){
				var jsoNOBJ ={
	            		"Mtype" : "image",
	            		"sender" : sender,
	            		"receiver" : receiver,
	            		"message" : "<img src='"+ picturePath + "' "+"height ='100px' width='100px' id='chatImg'/>",
	            };
	            webSocket.send(JSON.stringify(jsoNOBJ));
	            console.log(JSON.stringify(jsoNOBJ));
	            var answer='';
				answer+='<li>'+'<div class="answerHead"><img src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id=${memberVO.getMember_id()}"></div>'+'<div class="answers text-break">'+jsoNOBJ.message+'</div>'+'</li>';
				$('.newsList').append(answer);
				$('.conLeft').find('li.bg').children('.liRight').children('.infor').text();
				$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );							
				$('#chat_picture').val('');
				
			}else{
				
				
				if (message2 === "") {
					alert("Input a message");
					$('#dope').text('');
					$('#dope').focus();
				} else {
					var jsonObj = {
						"Mtype"   : "chat",
						"sender" : sender,
						"receiver" : receiver,
						"message" : message,
					
					};			
					$('#dope').empty();				
					$('#dope').html('');
					
					var answer='';
					answer+='<li>'+'<div class="answerHead"><img src="<%= request.getContextPath() %>/friend/writeFriendPicture.do?member_id=${memberVO.getMember_id()}"></div>'+'<div class="answers text-break">'+jsonObj.message+'</div>'+'</li>';
					$('.newsList').append(answer);
					$('.conLeft').find('li.bg').children('.liRight').children('.infor').text();
					$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
					webSocket.send(JSON.stringify(jsonObj));
					
			
				}			
				
			}
			
		}
		
	}
	
	
	function getHistory(whoUChat){
		var sender = "${memberVO.member_id}";
		var receiver = whoUChat;
		var historyRequest = {
				"Mtype" : "history",
				"sender" : sender,
				"receiver" : receiver,
				"message" : "",
				};
		webSocket.send(JSON.stringify(historyRequest));
	}

	function disconnect() {
		if(webSocket != null){
			webSocket.close();			
		}
	}

	          
    //判断浏览器是否支持FileReader接口
    if (typeof FileReader == 'undefined') {
        document.getElementById("chatShowDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
        //使选择控件不可操作
        document.getElementById("chatImg").setAttribute("disabled", "disabled");
    }
    
    

    //选择图片，马上预览
    function readURL(obj) {
        var file = obj.files[0];
        
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
            
            
            picturePath = e.target.result;
            sendMessage(e.target.result);      
            //或者 img.src = this.result;  //e.target == this
        }

        reader.readAsDataURL(file)
    }
    
    function trimDiv(){

    	var text = document.getElementById("dope").innerHTML.replace(/&nbsp;/gi, '').replace(/<div><br><\/div>/gi, '').replace(/<p><br><\/p>/gi, '');


    	document.getElementById("dope").innerHTML=text;
    	}

	
</script>
    <!-- ------------------------------------------------------------------------ -->
    </div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.js" ></script>
<script src="<%= request.getContextPath() %>/front-end/bootstrap/js/bootstrap.min.js"></script>
 <script type="text/javascript">
  	$('.conLeft li').on('click',function(){
		$(this).addClass('bg').siblings().removeClass('bg');
		var intername=$(this).children('.liRight').children('.intername').text();
		$('.headName').text(intername);
		$('.newsList').empty();
		var whoUChat =$(this).children('.liRight').children('.whoUAre').text();
		$('.sendBtn').prop("disabled",false);
		$('.sendBtn').css("background-color","#0099FF");
		$('#dope').prop("contenteditable",true);
		$('#chat_picture').prop("disabled",false);
		document.getElementById("getMesgs_"+whoUChat).style.display="none";
		getHistory(whoUChat);
	})
	
	$('#emjonshow').on('click',function(){
		$('.emjon').toggle();
	})
	$('.emjon').on('mouseleave',function(){
		$('.emjon').hide();
	})
	$('.emjon li').on('click',function(){
		var imgSrc=$(this).children('img').attr('src');
		
		var str="";
		str+="<img src='"+imgSrc+"' style='width:20px;height:20px;'>";
		$('#dope').text().trim();		
		$('#dope').append(str);		
		var el = document.getElementById("dope");
		var range = document.createRange();
		var sel = window.getSelection();
		range.setStartAfter(el.lastChild, 0);
		range.collapse(true);
		sel.removeAllRanges();
		sel.addRange(range);
		$('.emjon').hide();
		$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
	})
	
	
	$('#btnchat').on('click',function(){
		$('#chatroom').toggle();
	})
	
	
	
  </script>
 </body>
</html>