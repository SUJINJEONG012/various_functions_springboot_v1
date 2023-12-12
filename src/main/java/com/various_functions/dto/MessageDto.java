package com.various_functions.dto;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageDto {

	private String message; //사용자에게 전달할 메시
	private String redirectUri; //리다이렉트 url
	private RequestMethod method; // http 요청 메서
	private Map<String, Object> data; // (화면)view 으로 전달할 데이터(파라미터)
}
