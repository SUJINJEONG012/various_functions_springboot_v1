package com.various_functions.websocket;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {

	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		String payload = message.getPayload(); // 클라이언트로 받은 메시지

		// 응답메시지에 따라 응답을 다르게 보낼 수 있음.
		session.sendMessage(new TextMessage("hello, " + payload + "!")); // 응답 메세지 보내기
	}
}
