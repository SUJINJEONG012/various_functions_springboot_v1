package com.various_functions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String login() {
		return "member/login";
	}
	
	//회원정보저장 
	@PostMapping("/members")
	public Long saveMember(@RequestBody final MemberRequest params) {
		return memberService.saveMember(params);
	}
	
	// 회원 상세정보 조회
	@GetMapping("/members/{loginId}")
	@ResponseBody
	public MemberResponse findMemberByLoginId(@PathVariable final String loginId) {
		return memberService.findMemberByLoginId(loginId);
	}
	
	//회원정보 수정 
	@PostMapping("/members/{id}")
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
