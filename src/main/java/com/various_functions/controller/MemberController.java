package com.various_functions.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.various_functions.service.MemberService;
import com.various_functions.vo.MemberVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor

@Slf4j
public class MemberController {
	
	private final MemberService memberService;

	//로그인 페이지 이동
	@GetMapping("/member/login")
	public String openLogin() {
		log.info("로그인페이지 진입중");
		return "member/login";
	}
	
	// 로그인 기능 
	@PostMapping("/member/login")
	@ResponseBody
	public MemberVo login(HttpServletRequest request) {
		// 1. 회원정보 조회
		String loginId = request.getParameter("loginId");
		String memberPw =  request.getParameter("memberPw");
		MemberVo member = memberService.login(loginId, memberPw);
		// 2. 세션에 회원정보 저장 & 세션 유지시간 설정
		if(member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", member);
			session.setMaxInactiveInterval(60*30);
		}
		
		return member;
		
	}
	
//	// 회원 상세 조회
//	@GetMapping("/member/${loginId}")
//	@ResponseBody
//	public MemberVo findMemberById(@PathVariable final String loginId) {
//		return memberService.findMemberById(loginId);
//	}
	
	
	
	//회원저장
	
	//회원 상세정보 조회
	
	
	

	
	
}
