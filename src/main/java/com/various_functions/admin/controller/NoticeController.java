package com.various_functions.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.various_functions.admin.dto.NoticeDto;
import com.various_functions.admin.dto.NoticeFileDto;
import com.various_functions.admin.service.NoticeFileService;
import com.various_functions.admin.service.NoticeService;
import com.various_functions.admin.vo.NoticeFileVo;
import com.various_functions.admin.vo.NoticeVo;
import com.various_functions.common.dto.PagedSearchDto;
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
	public String saveNotice(final NoticeDto noticeDto, Model model, HttpSession session) {

		MemberVo member = (MemberVo) session.getAttribute("loginMember");
		if (member == null) {
			return "redirect:/member/login";
		}

		// 게시글insert
		Long noticeId = noticeService.noticeSave(noticeDto);

		// 단일 파일 업로드시에도 파일을 리스트에 담아서 전달
		List<MultipartFile> noticeFiles = noticeDto.getFiles();
		log.info("noticeFiles : ", noticeFiles);
		if (noticeFiles != null && !noticeFiles.isEmpty()) {
			List<NoticeFileDto> fileList = fileUtils.uploadFiles(noticeFiles);
			noticeFileService.saveFiles(noticeId, fileList); // saveFile 메서드를 사용하여 단일 파일을 저장
		}

		return "redirect:/admin/notice/list";
	}

	

	// @@PathVariable을 사용한 방법 => noticeId를 받아와서
	@GetMapping("/admin/notice/update/{noticeId}")
	public String showUpdateForm(@PathVariable Long noticeId, Model model) {

		log.info("수정 게시글 페이지진입!1");

		NoticeVo noticeVo = noticeService.findById(noticeId);

		model.addAttribute("notice", noticeVo);
		return "/admin/notice/update";
	}

	
	// 공지사항 수정 //파라미터 일시 지우고 @RequestParam("files") MultipartFile[] files
	@PostMapping("/admin/notice/update/{noticeId}")
	public ResponseEntity<Map<String, Object>> updateNotice(@PathVariable Long noticeId, @ModelAttribute NoticeDto noticeDto,
			@RequestParam(value = "filesToDelete", required = false) List<Long> filesToDelete,
			RedirectAttributes redirectAttributes) {
		
		log.info("공지사항 수정 진입 메서드");
		Map<String, Object> response = new HashMap<>();
		
		try {
			// 파일 삭제
	        if (filesToDelete != null && !filesToDelete.isEmpty()) {
	            log.info("파일삭제하는 if문 진입! : " + filesToDelete);
	            noticeFileService.deleteFiles(filesToDelete);
	        }

			// 파일 업로드
			List<MultipartFile> noticeFiles = noticeDto.getFiles();
			if (noticeFiles != null && !noticeFiles.isEmpty()) {
				List<NoticeFileDto> fileList = fileUtils.uploadFiles(noticeFiles);
				noticeFileService.saveFiles(noticeId, fileList);
			}

			// 게시물 수정
			noticeDto.setNoticeId(noticeId);
			noticeService.updateNotice(noticeDto);

			// 성공 응답
	        response.put("success", true);
	        response.put("message", "수정이 완료되었습니다.");
	        return ResponseEntity.ok(response);
		
		} catch (Exception e) {
	        // 실패 응답
	        response.put("success", false);
	        response.put("message", "수정 중 오류가 발생했습니다: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	// 관리자 페이지 리스트페이지
	@GetMapping("/admin/notice/list")
	public String adminNoticeList(@ModelAttribute("pagedSearchDto") PagedSearchDto pagedSearchDto,Model model) {
		return noticeList(pagedSearchDto, model, "/admin/notice/list");
	}

	// 유저 게시글 리스트 페이지
	@GetMapping("/notice/list")
	public String userNoticeList(@ModelAttribute("pagedSearchDto") PagedSearchDto pagedSearchDto,Model model) {
		return noticeList(pagedSearchDto, model, "notice/list");
	}

	// 유저,어드민 페이지 공통으로 사용하기 위한 메서드
	private String noticeList(PagedSearchDto pagedSearchDto,Model model, String viewName) {	
		  List<NoticeVo> notices = noticeService.findAllNotices(pagedSearchDto);
		  int totalRecordCount = noticeService.getTotalRecordCount(pagedSearchDto);
		  model.addAttribute("notices", notices);
		  model.addAttribute("searchDto", pagedSearchDto);
		  model.addAttribute("totalRecordCount", totalRecordCount);
		  model.addAttribute("currentPageGroupStart", pagedSearchDto.getCurrentPageGroupStart());
	      model.addAttribute("currentPageGroupEnd", pagedSearchDto.getCurrentPageGroupEnd(totalRecordCount));
	       
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

		// 여기에 파일저장도 포함되어있어야함
		List<NoticeFileVo> files = noticeFileService.findFilesByNoticeId(noticeId);
		log.info("공지사항 저장되는 부분 데이터 확인 files : {}", files);
		// 파일이 저장된 경로
		String uploadPath = fileUtils.getSingUploadPath(noticeId.toString());
		
		 // 조회수 증가
        noticeService.noticeViewCount(noticeId);
        
		// model.addAttribute("uploadPath", uploadPath);
		model.addAttribute("notice", notice);
		// 파일 나오는 곳
		model.addAttribute("files", files);
		return viewName;
	}
	
	
	
	// 게시글 삭제
	@DeleteMapping("/admin/notice/delete/{noticeId}")
	public ResponseEntity<?> deleteNotice(@PathVariable final Long noticeId) {
		try{
			noticeService.delete(noticeId);
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제실패");
		}
		
	}

}
