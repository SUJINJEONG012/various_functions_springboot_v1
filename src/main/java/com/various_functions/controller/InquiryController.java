package com.various_functions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.various_functions.admin.service.NoticeFileService;
import com.various_functions.admin.service.NoticeService;
import com.various_functions.dto.InquiryDto;
import com.various_functions.service.InquiryService;
import com.various_functions.utils.FileUtils;

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
	
	@PostMapping("/inquiry/write")
	public String InquiryWrite(final InquiryDto inquiryDto) {
		Long inquiryId = inquiryService.inquirySave(inquiryDto);
		return "redirect:/inquiry/list";
		
	}
	
	@GetMapping("/inquiry/list")
	public String InquiryList() {
		log.info("문의글 페이지 진입!!!!");
		return "/inquiry/list";
	}
}
