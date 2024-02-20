package com.various_functions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

	@GetMapping("/error")
	public String handleError() {
		// 오류 페이지를 보여줄 뷰 이름을 반환
		return "error";
	}
}
