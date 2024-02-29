package com.various_functions.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		// 로그에 예외를 기록
		logger.error("Exception occurred", e);
		return "redirect:/error";
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // 유효성 검사에 실패한 필드와 관련된 메시지를 가져옴
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        // 클라이언트에게 400 Bad Request와 메시지를 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
	
	
	
}
