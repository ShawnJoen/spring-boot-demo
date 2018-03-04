<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%><!DOCTYPE html><html lang="zh-CN"><head><title>SpringBoot WebSocket</title><meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <script src="/static/js/sockjs.min.js"></script>
    <script src="/static/js/stomp.min.js"></script>
    <script src="/static/js/jquery-1.12.4.min.js"></script>
</head>
<body>
<div>
    <div>
        <button id="connect" onclick="connect();">连接</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">断开连接</button>
    </div>
    <div id="conversationDiv">
        <label>请输入您的姓名</label><input type="text" id="name" />
        <button id="sendName" onclick="sendName();">发送</button>
        <p id="response"></p>
    </div>
</div>
</body>
<script type="text/javascript">
    var stompClient = null;

    function connect() {
        var socket = new SockJS('/endpointAric');//连接服务器使用SockJS协议定义的Endpoint
        stompClient = Stomp.over(socket);//使用STOMP子协议的WebSocket客户端
        stompClient.connect({},function(frame) {//连接WebSocket服务端
            //订阅 /topic/pong目标(destination)发送的消息, 这个是在控制器的@SentTo中定义的
            stompClient.subscribe('/topic/pong',function(response) {

                showResponse(JSON.parse(response.body).responseMessage);
            });

            setConnected(true);
            console.log('Connected:' + frame);
        });
    }
    //发布 /ping目标(destination)发送消息, 这个是在控制器的@MessageMapping中定义的
    function sendName() {
        var name = $("#name").val();
        stompClient.send("/ping",{}, JSON.stringify({'name':name}));
    }
    //中断连接
    function disconnect() {
        if(stompClient != null) {
            stompClient.disconnect();
        }

        setConnected(false);
        console.log("Disconnected");
    }
    function showResponse(message) { $("#response").html(message); }
    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        $("#response").html();
    }
</script>
</html>