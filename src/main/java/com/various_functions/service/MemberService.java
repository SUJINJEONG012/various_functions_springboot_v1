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
	    MemberVo member = memberMapper.findByLoginId(loginId);
	    
	    if(member != null && memberPw.equals(member.getMemberPw())) {
	    	// 비밀번호 일치하는 경우
	    	return member;
	    }else {
	    	// 로그인 실패
	    	return null;
	    }
	    
	}
	
	// 회원정보 
	@Transactional
	public Long saveMember(final MemberDto memberDto) {
		memberDto.encodingPassword(passwordEncoder);
		memberMapper.save(memberDto);
		return memberDto.getMemberId();
	}
	
	
	
}
