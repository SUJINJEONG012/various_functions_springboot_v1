package com.various_functions.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.various_functions.dto.MemberDto;
import com.various_functions.mapper.MemberMapper;
import com.various_functions.vo.MemberVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	//로그인
	public MemberVo login(final String loginId, final String memberPw) {
		// 1. 회원 정보 및 비밀번호 조회
		MemberVo member = findByLoginId(loginId);
		String encodedPassword = member == null ?  "" : member.getMemberPw();
		// 2. 회원 정보 및 비밀번호 체크
		if(member == null || passwordEncoder.matches(memberPw, encodedPassword)) {
			return null;
		}
		
		// 3. 회원 응답 객체에서 비밀번호를 제거한 후 회원 정보 리턴
		//member.clearPassword();
		return member;
	}
	
	//회원정보저장
	@Transactional
	public Long saveMember(final MemberDto memberDto) {
		memberDto.encodingPassword(passwordEncoder);
		memberMapper.save(memberDto);
	
		return memberDto.getMemberId();
		
	}
	
	//회원 상세정보 조회
	public MemberVo findByLoginId(final String loginId) {
		return memberMapper.findByLoginId(loginId);
	}
	
	//회원 정보수정
	
	//회원 정보삭제
	
	//회원 수 카운팅
	
	
}
