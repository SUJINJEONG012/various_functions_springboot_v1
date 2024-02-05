package com.various_functions.admin.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.various_functions.admin.dto.NoticeDto;
import com.various_functions.admin.service.NoticeService;
import com.various_functions.admin.vo.NoticeVo;
import com.various_functions.dto.FileDto;
import com.various_functions.dto.FileUtils;
import com.various_functions.dto.MessageDto;
import com.various_functions.dto.PagingResponse;
import com.various_functions.dto.SearchDto;
import com.various_functions.service.FileService;
import com.various_functions.vo.FileEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NoticeController {

	@GetMapping("/admin/notice/list")
	public String noticeList() {
		return "admin/notice/list";
	}
	
}
