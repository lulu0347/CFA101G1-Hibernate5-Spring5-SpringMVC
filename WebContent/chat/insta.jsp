<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>CountMeIn線上聊天室</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Lato&display=swap" rel="stylesheet">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <link rel="stylesheet" href="insta-web.css">
    <style>
        :root {
            --white: #fafafa;
            --grey: #717171;
            --medium-grey: #dbdbdb;
            --strong-grey: #545454;
            --header-color: #fff;
            --text-color: #262626;
        }

        body {
            display: flex;
            justify-content: center;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
            font-size: 14px;
            line-height: 18px;
            padding: 0;
            margin: 0;
            overflow-x: hidden;
            background-color: var(--white);
        }

        header {
            position: fixed;
            width: 100vw;
            display: flex;
            justify-content: space-around;
            align-items: center;
            flex-direction: row;
            background-color: var(--header-color);
            border-bottom: 1px solid var(--medium-grey);
        }

        #logo {
            height: 90%;
            width: 130px;
            padding: 5px;
        }

        input {
            border-radius: 5px;
		    outline: none;
		    background-color: var(--white);
        }

        nav {
            display: inline-flex;
        }

        nav i {
            margin: 5px 10px;
            font-size: 20px;
            color: var(--strong-grey);
        }

        #user {
            height: 25px;
            width: 25px;
            border-radius: 50%;
        }

        #user img,
        #profile-pic img {
            height: 100%;
            width: 100%;
            margin: 2px 10px;
            border-radius: 50%;
        }

        main {
            margin-top: 15vh;
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            height: 80vh;
            width: 70vw;
            background-color: var(--white);
            border: 1px solid var(--medium-grey);
        }

        .preview {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            justify-content: flex-start;
            height: 100%;
            width: 40%;
            border-right: 1px solid var(--medium-grey);
        }

        .preview>div {
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: flex-start;
            height: 70px;
            width: 100%;
        }

        .chats {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            height: 80vh;
            width: 60%;
        }

        .chat-banner {
            width: 100%;
            height: 60px;
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: space-between;
            border-bottom: 1px solid var(--medium-grey);
        }

        .chat-banner>div {
            padding: 10px;
        }

        #user-name {
            min-height: 60px;
            display: flex;
            justify-content: center;
            align-items: center;
            font-weight: 400;
            font-size: 1.5em;
            border-bottom: 1px solid var(--medium-grey);
        }

        #user-name i {
            font-size: 1em;
            padding-left: 5px;
        }

        #pic-div,
        #pic,
        #chat-pic {
            height: 50px;
            width: 50px;
            border-radius: 50%;
        }

        #pic {
            margin-left: 0.5em;
        }

        #chat-pic {
            height: 20px;
            width: 20px;
        }

        #chat-username {
            padding: 10px;
            margin-left: 0.5em;
            display: flex;
            flex-direction: column;
        }

        #chat-username>span {
            height: 50%;
            width: auto;
        }

        #name {
            font-weight: 500;
            font-size: 1em;
        }

        #msg {
            color: var(--grey);
            font-size: 13px;
        }

        .sender,
        .receiver,
        .user-input {
            margin: 0.8em;
            padding: 10px;
            height: auto;
            width: auto;
            max-width: 140px;
            border-radius: 20px;
            border: 1px solid var(--medium-grey);
            transition: all .2s ease;
        }

        .sender:hover {
            background-color: pink;
            cursor: pointer;
        }

        .receiver,
        .user-input {
            margin-left: 22vw;
            background-color: var(--medium-grey);
        }

        .user-input {
            display: none;
        }

        #heart {
            height: 30px;
            width: 30px;
            margin-top: -1.5em;
            margin-left: 1em;
            visibility: hidden;
        }

        .input-msg {
            display: flex;
            flex-direction: row;
            justify-content: space-around;
            width: 100%;
            padding-top: 5px;
        }

        .input-msg i {
            color: var(--grey);
            padding: 10px;
            height: 30px;
            font-size: 1.5em;
        }

        #send-input {
            height: 30px;
            border-radius: 20px;
            border: 1px solid var(--grey);
            width: 80%;
        }
		
		#messageArea {
			height: 90%;
			width:100%;
			resize: none;
			box-sizing: border-box;
			background-color: #ffffff;
	    	overflow: auto;
		}
			
		.input-area {
			height: 8%;
			background: #ffffff;
			box-shadow: inset 0 0 4px grey;
			padding-bottom: 2%;
    		border-radius: 5px;
		}
		#row {
			border: 4px solid #f1e7d8;
			width: 300px;
		}
		ul{
		  list-style: none;
		  margin: 0;
		  padding: 0;
		}
		
		ul li{
		  display:inline-block;
		  clear: both;
		  padding: 10px;
		  border-radius: 15px;
		  margin-bottom: 2px;
		  font-family: Helvetica, Arial, sans-serif;
		}
		
		.friend{
		  background: #eee;
		  float: left;
		  margin-left:10px;
		  border-top-left-radius: 0px;
		}
		
		.me{
		  float: right;
		  background: #00d907;
		  color: #fff;
		  margin-right:10px;
		  border-top-right-radius: 0px;
		}
		#message{
			width:100%;
		}
		#talkto{
			font-size: 50px;
		}
		h2{
			padding-left: 10px;
		}
		#row{
			margin-left: 30px;
		}
		#online{
			width:100%;
		}
		.img-profile{
			width:35px;
		}
		.loginname{
		    font-size: 20px;
		    font-family: fantasy;
		    vertical-align: super;
		}
    </style>
</head>

<body onload="connect();" onunload="disconnect();">
    <header>
        <i class="fab fa-contao" style="font-size:50px;"></i>
        <nav>
            <div class="nav-item dropdown no-arrow">
	            <img class="img-profile" src="logo1.png">
	            <span class="loginname">${adminiVO.adminName}</span>
	            <span class="loginname">${user.memname}</span>
            </div>
        </nav>
    </header>
    <main>
        <div class="chats">
            <div class="chat-banner">
                <div>
                    <span id="chat-pic">
                        <img id="pic" src="logo1.png" />
                    </span>
                    <span id="talkto" class="talkto"></span>
                </div>
            </div>
            <div id="messageArea" class="panel message-area">            	
            </div>
            <div class="input-msg input-area">
                <input type="text" id="message" placeholder="type something" onkeydown="if (event.keyCode == 13) sendMessage();" />
                <input type="submit" id="sendMessage" class="far fa-paper-plane" value="Send" onclick="sendMessage();" />
            </div>
        </div>
        <div>
        	<div id="messagesArea" class="panel message-area" ></div>
            <div class="input-msg">
                <div id="row"></div>
            </div>
        </div>
    </main>
    <script src="index.js"></script>
    <script>
    var MyPoint = "/FriendWS/${user.memname}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("talkto");
	var messagesArea = document.getElementById("messageArea");
	var self = '${user.memname}';
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
				refreshFriendList(jsonObj);
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.sender === self ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = showMsg;
					ul.appendChild(li);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
				li.innerHTML = jsonObj.message;
				console.log(li);
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}
			
		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var friend = statusOutput.textContent;
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else if (friend === "") {
			alert("Choose a friend");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
		var friends = jsonObj.users;
		var row = document.getElementById("row");
		row.innerHTML = '';
		for (var i = 0; i < friends.length; i++) {
			if (friends[i] === self) { continue; }
			row.innerHTML +='<label for='+i+'><div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2>' + friends[i] + '</h2></div></label>';
		}
		addListener();
	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		var container = document.getElementById("row");
		container.addEventListener("click", function(e) {
			var friend = e.srcElement.textContent;
			updateFriendName(friend);
			var jsonObj = {
					"type" : "history",
					"sender" : self,
					"receiver" : friend,
					"message" : ""
				};
			webSocket.send(JSON.stringify(jsonObj));
		});
	}
	
	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
	}
	
	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
    </script>
</body>

</html>
