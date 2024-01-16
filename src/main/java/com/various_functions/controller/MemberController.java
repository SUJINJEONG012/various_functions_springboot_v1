package com.various_functions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.various_functions.dto.MemberDto;
import com.various_functions.service.MemberService;
import com.various_functions.vo.MemberVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor

@Slf4j
public class MemberController {
	
	private final MemberService memberService;

	//로그인 페이지
	@GetMapping("/member/login")
	public String openLogin() {
		log.info("로그인페이지 진입중");
		return "member/login";
	}
	
	// 로그인 요청 페이지
	@PostMapping("/member/login")
	public String login(@RequestParam String loginId, @RequestParam String memberPw, Model model) {
		log.info("post 로그인페이지 @@");
		
		//회원정보조회
		MemberVo member = memberService.login(loginId, memberPw);
		if(member != null) {
			model.addAttribute("member", member);
			return "redirect:/index"; // 로그인 성공 후 이동할 페이지
		}else {
			return "/member/login"; // 로그인 실패시 이동할 페이지
		}
		
//		// 회원 정보 조회
//		String loginId = request.getParameter("loginId");
//		System.out.println("회원정보 아이디  : " + loginId);
//		String memberPw = request.getParameter("memberPw");
//		System.out.println("회원정보 비밀번호 : " + memberPw);
//		
//		MemberVo member = memberService.login(loginId, memberPw);
//		log.info("post 회원정보조회 @@");
//		System.out.println("회원정보 조회 : " + member);
		// 세션 회원정보저장 & 세션 유지 시간 설정
//		if(member != null) {
//			HttpSession session = request.getSession();
//			session.setAttribute("loginMember", member);
//			session.setMaxInactiveInterval(60*30);
//		}		
//		return member;
	}
	
	//회원정보 저장 회원가입 
	@PostMapping("/save")
	@ResponseBody
	public Long saveMember(@RequestBody final MemberDto memberDto) {
		return memberService.saveMember(memberDto);
	}
	
	
	
}
