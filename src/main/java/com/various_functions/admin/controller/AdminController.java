package com.various_functions.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/dashboard")
	public String main() {
		log.info("어드민 페이지 대시보드 진입!!");
		return "/admin/main";
	}
}
