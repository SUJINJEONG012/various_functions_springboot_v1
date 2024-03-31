package com.various_functions.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.admin.service.NoticeFileService;
import com.various_functions.admin.vo.NoticeFileVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NoticeFileController {
	
	@Autowired
    private final NoticeFileService noticeFileService;

	//파일리스트 조회
	@GetMapping("/admin/notice/{noticeId}/files")
	public List<NoticeFileVo> findAllFileByNoticeId(@PathVariable final Long noticeId){
		return noticeFileService.findFilesByNoticeId(noticeId);
	}
	

}
