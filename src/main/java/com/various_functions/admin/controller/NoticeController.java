package com.various_functions.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.various_functions.admin.dto.NoticeDto;
import com.various_functions.admin.dto.NoticeFileDto;
import com.various_functions.admin.service.NoticeFileService;
import com.various_functions.admin.service.NoticeService;
import com.various_functions.admin.vo.NoticeVo;
import com.various_functions.utils.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	private final NoticeFileService noticeFileService;
	private final FileUtils fileUtils;

	// 게시글 작성 페이지
	@GetMapping("/admin/notice/write")
	public String openNoticeWrite(@RequestParam(value = "id", required = false) final Long id, Model model) {

		if (id != null) {
			NoticeVo notice = noticeService.findById(id);
			model.addAttribute("notice", notice);
		}
		return "admin/notice/write";
	}

	// 공지사항작성
	@PostMapping("/admin/notice/save")
	public String saveNotice(final NoticeDto noticeDto, Model model) {
		Long noticeId = noticeService.noticeSave(noticeDto);

		// 다중파일 업로드시
		// List<NoticeFileDto> files = fileUtils.uploadFiles(noticeDto.getFilesffxx());
		// noticeFileService.saveFile(noticeId, files); // 업로르도딘 파일을 db에 저장

		NoticeFileDto file = fileUtils.uploadFile(noticeDto.getFile());
		noticeFileService.saveFile(noticeId, file);
		return "redirect:/admin/notice/list";
	}

//	@PostMapping("/admin/notice/save")
//	public String saveNotice(final NoticeDto noticeDto) {
//		noticeService.noticeSave(noticeDto);
//		return "redirect:/admin/notice/list";
//	}

	// 관리자 페이지 리스트페이지
	@GetMapping("/admin/notice/list")
	public String adminNoticeList(Model model) {
		return noticeList(model, "/admin/notice/list");
	}

	// 유저 게시글 리스트 페이지
	@GetMapping("/notice/list")
	public String userNoticeList(Model model) {
		log.info("userNoticeList");
		System.out.println("유저 게시물 리스트 페이지 !@@@");
		return noticeList(model, "notice/list");
	}

	// 유저,어드민 페이지 공통으로 사용하기 위한 메서드
	private String noticeList(Model model, String viewName) {
		List<NoticeVo> notices = noticeService.findAllNotices();
		model.addAttribute("notices", notices);
		return viewName;
	}
	
	//게시글 상세 페이지
	public String NoticeView(@RequestParam final Long NoticeId, Model model) {
		NoticeVo notice = noticeService.findById(NoticeId);
		return "/admin/notice/view";
	}

}
