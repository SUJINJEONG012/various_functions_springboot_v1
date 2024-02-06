package com.various_functions.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.various_functions.admin.service.NoticeService;
import com.various_functions.admin.vo.NoticeVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService noticeService;
	
	// 게시글 작성 페이지
	@GetMapping("/admin/notice/write")
	public String openNoticeWrite(@RequestParam(value="id", required=false) final Long id, Model model) {
		
		if(id != null) {
			NoticeVo notices = noticeService.findById(id);
			model.addAttribute("notice",notices);
		}
		return "admin/notice/write";
	}
	
	
	@GetMapping("/admin/notice/list")
	public String noticeList() {
		return "admin/notice/list";
	}
	
	
	
}
