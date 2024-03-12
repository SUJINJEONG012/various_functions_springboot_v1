package com.various_functions.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.various_functions.dto.InquiryDto;
import com.various_functions.service.InquiryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class InquiryController {
	
	private final InquiryService inquiryService;

	@GetMapping("/inquiry/write")
	public String InquiryWrite() {
		return "/inquiry/write";
	}
	
	@PostMapping("/inquiry/save")
	public ResponseEntity<String> InquiryWrite(final InquiryDto inquiryDto, Model model) {
		inquiryService.inquirySave(inquiryDto);
		return ResponseEntity.ok("글이 성공적으로 게시되었습니다.");
		
	}
	
	@GetMapping("/inquiry/list")
	public String InquiryList() {
		log.info("문의글 페이지 진입!!!!");
		return "/inquiry/list";
	}
}
