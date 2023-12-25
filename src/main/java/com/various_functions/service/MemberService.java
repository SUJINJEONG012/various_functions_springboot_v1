package com.various_functions.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.various_functions.domain.MemberRequest;
import com.various_functions.domain.MemberResponse;
import com.various_functions.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	// 회원정보 저장(회원가입)
	@Transactional
	public Long saveMember(final MemberRequest params) {
		params.encodingPassword(passwordEncoder);
		memberMapper.save(params);
		return params.getId();
	}
	
	// 회원상세정보 조회
	public MemberResponse findMemberByLoginId(final String loginId) {
		return memberMapper.findByLoginId(loginId);
	}
	
	// 회원정보 수정
	@Transactional
	public Long updateMember(final MemberRequest params) {
		params.encodingPassword(passwordEncoder);
		memberMapper.update(params);
		return params.getId();
	}
	
	// 회원정보 삭제
	@Transactional
	public Long deleteMemberById(final Long id) {
		memberMapper.deleteById(id);
		return id;
	}
	
	// 회원 수 카운팅
	public int countMemberByLongId(final String loginId) {
		return memberMapper.countByLoginId(loginId);
	}
}
