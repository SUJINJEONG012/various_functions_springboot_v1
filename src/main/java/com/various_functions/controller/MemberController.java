package com.various_functions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.various_functions.dto.MemberDto;
import com.various_functions.service.MemberService;
import com.various_functions.vo.MemberVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
	
	private final MemberService memberService;

	//로그인 페이지
	@GetMapping("/login")
	public String openLogin() {
		log.info("로그인페이지 진입중");
		return "member/login";
	}
	
	//회원정보 저장 회원가입 
	@PostMapping("/save")
	@ResponseBody
	public Long saveMember(@RequestBody final MemberDto memberDto) {
		return memberService.saveMember(memberDto);
	}
	
	//회원상세정보
	@GetMapping("{loginId}")
	@ResponseBody
	public MemberVo findMemberByLoginId(@PathVariable final String loginId) {
		return memberService.findMemberByLoginId(loginId);
	}
}
