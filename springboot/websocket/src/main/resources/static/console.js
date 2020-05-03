//$(document).ready(function() {
    //首次打开页面自动连接
//    connect();
//})

//执行连接
function connect() {
    //接入端点/backend
    var socket = new
    SockJS('http://127.0.0.1:8080/endpoint');
    window.stompClient = Stomp.over(socket);
    window.stompClient.connect({},
    function(frame) {
        log('Connected: ' + frame);
        //订阅服务端输出的 Topic
        stompClient.subscribe('/topic/env',
        function(message) {
            log("[服务器环境]：" + message.body);
        });
        stompClient.subscribe('/topic/broadcast',
        function(message) {
            log("[服务器广播]：" + message.body);
        });

        stompClient.subscribe('/topic/reply',
        function(message) {
            log("[服务器消息]：" + message.body);
        });

    });
}

//断开连接
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    clearConsole();
    log("Disconnected");
}

//发送消息
function sendMessage() {
    var content = $("#word").val();
    if (!content) {
        alert("请输入消息!")
        return;
    }
    //向应用Topic发送消息
    stompClient.send("/app/speech", {}, content);
    log("[你说]：" + content);
}

//记录控制台消息
function log(message) {
    $("<p></p>").text(message).appendTo($("#console"));
}

//清空控制台
function clearConsole() {
    $("#console").empty();
}