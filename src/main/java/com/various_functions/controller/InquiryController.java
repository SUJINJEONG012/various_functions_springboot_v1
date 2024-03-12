package com.various_functions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class InquiryController {

	@GetMapping("/inquiry/write")
	public String InquiryWrite() {
		return "/inquiry/write";
	}
	
	@PostMapping("/inquiry/write")
	public String InquiryWrite() {
		
	}
	
	@GetMapping("/inquiry/list")
	public String InquiryList() {
		log.info("문의글 페이지 진입!!!!");
		return "/inquiry/list";
	}
}
