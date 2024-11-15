package com.various_functions.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	private MyWebSocketHandler myWebSocketHandler;

	public WebSocketConfig(MyWebSocketHandler myWebSocketHandler) {
		this.myWebSocketHandler = myWebSocketHandler;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// /websocket 경로에 핸들러 등록
		registry.addHandler(myWebSocketHandler, "/websocket").setAllowedOrigins("*"); // CORS 허용 (다른 도메인에서 접근 가능)
	}

}
