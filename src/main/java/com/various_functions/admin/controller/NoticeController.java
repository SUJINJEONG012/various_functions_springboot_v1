package com.various_functions.admin.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import com.various_functions.admin.vo.NoticeFileVo;
import com.various_functions.admin.vo.NoticeVo;
import com.various_functions.service.MemberService;
import com.various_functions.utils.FileUtils;
import com.various_functions.vo.MemberVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	private final NoticeFileService noticeFileService;
	private final FileUtils fileUtils;
	private final MemberService memberService;

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
	public String saveNotice(final NoticeDto noticeDto, Model model,HttpSession session) {
	
		
		MemberVo member = (MemberVo) session.getAttribute("loginMember");
		if(member == null) {
			return "redirect:/member/login";
		}
		Long noticeId = noticeService.noticeSave(noticeDto);

		// 단일 파일 업로드시에도 파일을 리스트에 담아서 전달
		NoticeFileDto file = fileUtils.uploadFile(noticeDto.getFile());
		List<NoticeFileDto> fileList = new ArrayList<>();
		fileList.add(file);
		noticeFileService.saveFile(noticeId, fileList); // saveFile 메서드를 사용하여 단일 파일을 저장
		return "redirect:/admin/notice/list";
		
	}


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
		return NoticeView(noticeId, model, "/notice/view");
	}

	@GetMapping("/admin/notice/view")
	private String adminNoticeView(@RequestParam Long noticeId, Model model) {
		return NoticeView(noticeId, model, "/admin/notice/view");
	}

	// 게시글 상세 페이지
	public String NoticeView(@RequestParam final Long noticeId, Model model, String viewName) {
		
		NoticeVo notice = noticeService.findById(noticeId);
		
		//여기에 파일저장도포함되어있어야함
		List<NoticeFileVo> files = noticeFileService.findFilesByNoticeId(noticeId);
		log.info("공지사항 저장되는 부분 데이터 확인 files : {}" , files);
		// 파일이 저장된 경로
        String uploadPath = fileUtils.getUploadPath(noticeId.toString());

		model.addAttribute("uploadPath", uploadPath);
		model.addAttribute("notice", notice);
		model.addAttribute("files", files);
		return viewName;
	}

}
