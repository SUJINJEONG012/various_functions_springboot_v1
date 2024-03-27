package com.various_functions.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
		return noticeList(model, "notice/list");
	}

	// 유저,어드민 페이지 공통으로 사용하기 위한 메서드
	private String noticeList(Model model, String viewName) {
		List<NoticeVo> notices = noticeService.findAllNotices();
		model.addAttribute("notices", notices);
		return viewName;
	}

	@GetMapping("/notice/view")
	private String userNoticeView(@RequestParam Long noticeId, Model model, HttpSession session) {
		log.info("유저페이지 상세보기 공지사항");

		// 세션에서 현재 사용자 정보를 가져옵니다.
		String loginId = (String) session.getAttribute("loginId");
		log.info("세션에서 현재 사용자 정보를 가져옵니다." + loginId); // null로 나옴
		// 게시물 상세 정보를 가져옵니다.
		NoticeVo notice = noticeService.findById(noticeId);
		model.addAttribute("notice", notice);

		// 게시물을 작성한 사용자의 이름을 가져옵니다.
		String memberName = notice.getWriter();
		log.info("게시물을 작성한 사용자의 이름을 가져오기 : " + memberName);

		// 현재 로그인한 사용자와 게시물 작성자의 이름이 동일한지 확인합니다.
		boolean isCurrentUserAuthor = loginId != null && loginId.equals(memberName);
		model.addAttribute("isCurrentUserAuthor", isCurrentUserAuthor);
		return NoticeView(noticeId, model, "/notice/view");
	}

	@GetMapping("/admin/notice/view")
	private String adminNoticeView(@RequestParam Long noticeId, Model model) {
		return NoticeView(noticeId, model, "/admin/notice/view");
	}

	

	// 게시글 상세 페이지
	public String NoticeView(@RequestParam final Long noticeId, Model model, String viewName) {
		NoticeVo notice = noticeService.findById(noticeId);
		model.addAttribute("notice", notice);
		return viewName;
	}

}
