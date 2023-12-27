package com.various_functions.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.various_functions.domain.MemberRequest;
import com.various_functions.domain.MemberResponse;
import com.various_functions.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	// 로그인 페이지
	@GetMapping("/login")
	public String openLogin() {
		return "member/login";
	}
	
	// 로그인
	@PostMapping("/login")
	@ResponseBody
	public MemberResponse login(HttpServletRequest request) {
		
		// 1. 회원 정보 조회
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		MemberResponse member = memberService.login(loginId, password);
		
		// 2. 세션에 회원정보 저장 & 세션 유지시간 설정
		if(member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", member);
			session.setMaxInactiveInterval(60 * 30); //세션타임아웃을 설정하는 메서드, 초를 기준으로 1800초 > 30분
		}
		
		return member;
	}
	
	
	// 로그아웃
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); //세션 무료화
		return "redirect:/login"; //사용자를 로그인 페이지로 이동
	}
	
	
	//회원정보저장 (회원가입)
	@PostMapping("/members")
	@ResponseBody
	public Long saveMember(@RequestBody final MemberRequest params) {
		System.out.println("params :: @@@@@" + params);
		return memberService.saveMember(params);
	}
	
	// 회원 상세정보 조회
	@GetMapping("/members/{loginId}")
	@ResponseBody
	public MemberResponse findMemberByLoginId(@PathVariable final String loginId) {
		return memberService.findMemberByLoginId(loginId);
	}
	
	//회원정보 수정 
	@PatchMapping("/members/{id}")
	@ResponseBody
	public Long updateMember(@PathVariable final Long id, @RequestBody final MemberRequest params) {
		return memberService.updateMember(params);
	}
	
	//회원정보 삭제(회원탈퇴)
	@DeleteMapping("/member-count")
	@ResponseBody
	public Long deleteMemberById(final Long id) {
		return memberService.deleteMemberById(id);
	}
	
	//회원 수 카운팅 (ID 중복체크)
	@ResponseBody
	@GetMapping("/member-count")
	public int countMemberByLoginId(@RequestParam final String loginId) {
		return memberService.countMemberByLongId(loginId);
	}
}
