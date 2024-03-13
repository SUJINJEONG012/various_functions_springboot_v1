package com.various_functions.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.various_functions.dto.InquiryDto;
import com.various_functions.service.InquiryService;
import com.various_functions.vo.MemberVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class InquiryController {
	
	private final InquiryService inquiryService;

	@GetMapping("/inquiry/write")
	public String Inquiry(final InquiryDto inquiryDto, Model model, HttpSession session) {
		MemberVo member = (MemberVo) session.getAttribute("loginMember");
		log.info("member 제대로 들고온느지 확인 : ", member);
		model.addAttribute("LoginInMember", member);
		return "/inquiry/write";
	}
	
	@PostMapping("/inquiry/save")
	public ResponseEntity<String> InquiryWrite(final InquiryDto inquiryDto, Model model, HttpSession session) {
		MemberVo member = (MemberVo) session.getAttribute("loginMember");
		
		if(member == null ) {
			return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/member/login?message=로그인이 필요합니다.").build();
		}
		
		//문의글 저장처리
		inquiryService.inquirySave(inquiryDto);
		// 모델에 회원정보추가
		model.addAttribute("loginMember", member);
		return ResponseEntity.ok("글이 성공적으로 게시되었습니다.");
		
	}
	
	@GetMapping("/inquiry/list")
	public String InquiryList() {
		log.info("문의글 페이지 진입!!!!");
		return "/inquiry/list";
	}
}
