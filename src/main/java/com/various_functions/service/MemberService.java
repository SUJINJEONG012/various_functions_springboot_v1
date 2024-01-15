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
	
	// 로그인 
	public MemberVo login(final String loginId, final String memberPw) {
		// 회원정보 및 비밀번호 조회
		MemberVo member = findMemberByLoginId(loginId);
		String encodePassword = (member == null) ? "":member.getMemberPw();
		System.out.println("member : " + member);
		// 회원정보 및 비밀번호 체크
		if(member == null || passwordEncoder.matches(memberPw, encodePassword) == false) {
			return null;
		}
		// 회원 응답 객체에서 비밀번호를 제거한 후 회원정보 리턴
		member.clearPassword();
		return member;
	}
	
	// 회원정보 
	@Transactional
	public Long saveMember(final MemberDto memberDto) {
		memberDto.encodingPassword(passwordEncoder);
		memberMapper.save(memberDto);
		return memberDto.getMemberId();
	}
	
	// 회원상세정보
	public MemberVo findMemberByLoginId(final String loginId) {
		return memberMapper.findByLoginId(loginId);
	}
	
}
