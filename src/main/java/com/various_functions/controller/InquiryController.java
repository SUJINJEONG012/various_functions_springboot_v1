package com.various_functions.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.various_functions.dto.InquiryDto;
import com.various_functions.service.InquiryService;
import com.various_functions.service.MemberService;
import com.various_functions.vo.InquiryVo;
import com.various_functions.vo.MemberVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class InquiryController {
	
	private final InquiryService inquiryService;
	private final MemberService memberService;

	@GetMapping("/inquiry/write")
	public String Inquiry(final InquiryDto inquiryDto, Model model, HttpSession session) {
		MemberVo member = (MemberVo) session.getAttribute("loginMember");

		return "/inquiry/write";
	}
	
	@PostMapping("/inquiry/save")
	public ResponseEntity<String> InquiryWrite(final InquiryDto inquiryDto, Model model, HttpSession session) {
		MemberVo member = (MemberVo) session.getAttribute("loginMember");
		
		if(member == null ) {
			return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/member/login?message=로그인이 필요합니다.").build();
		}
		
		// 외래키로 설정된 회원 아이디를 inquiryDto에 설정
		inquiryDto.setMemberId(member.getMemberId());
		log.info("member.getMemberId() : " + member.getMemberId());
		
		
		// 문의글 저장처리
		inquiryService.inquirySave(inquiryDto);
		return ResponseEntity.ok("글이 성공적으로 게시되었습니다.");
		
	}
	
	@GetMapping("/inquiry/list")
	public String InquiryList(Model model) {
		log.info("문의글 페이지 진입!!!!");
	
		List<InquiryVo> inquirys = inquiryService.findAllInquiry();
		
		// 문의하기 리스트에 작성자이름 출력
		for(InquiryVo inquiry : inquirys) {
			String memberName = memberService.getMemberNameById(inquiry.getMemberId());
			inquiry.setMemberName(memberName);
		}
		model.addAttribute("inquirys",inquirys);
		return "/inquiry/list";
	}
}
