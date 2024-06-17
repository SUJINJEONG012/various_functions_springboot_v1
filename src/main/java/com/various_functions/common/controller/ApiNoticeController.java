package com.various_functions.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.admin.service.NoticeService;

@RestController
@RequestMapping("/api/notice")
public class ApiNoticeController {

	private final NoticeService noticeService;
	
	@Autowired
    public ApiNoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
	
	
}
