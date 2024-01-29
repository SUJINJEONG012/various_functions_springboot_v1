package com.various_functions.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminMainController {

	@GetMapping("/admin")
	public String main() {
	log.info("관리자 메인에 진입");
		return "admin/main";
	}
}