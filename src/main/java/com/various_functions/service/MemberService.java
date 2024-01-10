package com.various_functions.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.various_functions.dto.MemberDto;
import com.various_functions.dto.SearchDto;
import com.various_functions.mapper.MemberMapper;
import com.various_functions.vo.MemberVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	//로그인
	public MemberVo login(final String loginId, final String password) {
		// 1. 회원 정보 및 비밀번호 조회
		MemberVo member = findMemberByLoginId(loginId);
		String encodedPassword = (member == null) ? "" : member.getMemberPw();

		// 2. 회원 정보 및 비밀번호 체크
		if(member == null || passwordEncoder.matches(password, encodedPassword) == false) {
			return null;
		}
		
		// 3. 회원 응답 객체에서 비밀번호를 제거한 후 회원 정보 리턴
		member.clearPassword();		
		return member;
	}
	
	// 회원정보 저장(회원가입)
	@Transactional
	public Long saveMember(final MemberDto params) {
		params.encodingPassword(passwordEncoder);
		memberMapper.memberSave(params);
		return params.getMemberId();
	}
	
	// 회원상세정보 조회
	public MemberVo findMemberByLoginId(final String loginId) {
		return memberMapper.findByLoginId(loginId);
	}
	
	// 회원정보 수정
	@Transactional
	public Long updateMember(final MemberDto params) {
		// 회원정보 수정 할 로직넣기
		
		params.encodingPassword(passwordEncoder);
		memberMapper.update(params);
		return params.getMemberId();
	}
	
	// 회원정보 삭제
	@Transactional
	public Long deleteMemberById(final Long id) {
		memberMapper.deleteById(id);
		return id;
	}
	
	// 회원 리스트 조회
	public List<MemberVo> findAll(final MemberVo id){
		return memberMapper.findAll(id);
	}
	
	
	// 회원 수 카운팅
	public int countMemberByLongId(final String loginId) {
		return memberMapper.countByLoginId(loginId);
	}
}
