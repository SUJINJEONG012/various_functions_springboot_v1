// WebSocket 연결
        const socket = new WebSocket("ws://localhost:8081/websocket");

        socket.onopen = function() {
            console.log("WebSocket 연결 성공!");
        };

        // 서버로부터 메시지 받기
        socket.onmessage = function(event) {
            const message = event.data;
            const messagesDiv = document.getElementById("messages");
            const newMessage = document.createElement("div");
            newMessage.textContent = message;
            messagesDiv.appendChild(newMessage);
            messagesDiv.scrollTop = messagesDiv.scrollHeight; // 자동 스크롤
        };

        socket.onerror = function(error) {
            console.log("WebSocket 에러: ", error);
        };

        socket.onclose = function() {
            console.log("WebSocket 연결 종료");
        };

        // 메시지 전송
        document.getElementById("sendButton").onclick = function() {
            const input = document.getElementById("messageInput");
            const message = input.value;

            if (message.trim() !== "") {
                socket.send(message); // 서버로 메시지 전송
                input.value = ""; // 입력 필드 초기화
            }
        };

        // 엔터키로 메시지 전송
        document.getElementById("messageInput").addEventListener("keypress", function(event) {
            if (event.key === "Enter") {
                document.getElementById("sendButton").click();
            }
        });