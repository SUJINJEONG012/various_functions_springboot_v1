package com.various_functions.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.various_functions.dto.MemberDto;
import com.various_functions.mapper.MemberMapper;
import com.various_functions.vo.MemberVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
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
