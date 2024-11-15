package com.various_functions.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component // 이 어노테이션을 추가하여 스프링이 관리하는 빈으로 등록
public class MyWebSocketHandler implements WebSocketHandler {

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("WebSocket 연결됨");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.out.println("메시지 받음: " + message.getPayload());
		// 클라이언트에 메시지 전송
		session.sendMessage(message);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println("WebSocket 에러 발생: " + exception.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.out.println("WebSocket 연결 종료됨");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
