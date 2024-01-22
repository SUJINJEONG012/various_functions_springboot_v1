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
		log.info("서비스 로그인 페이지 진입 !!!");
		
		// 1. 회원 정보 및 비밀번호 조회 => 이 부분이 지금 조회가 안됌
			log.info("loginId :@@@@@ "+loginId);
		MemberVo member = findMemberByLoginId(loginId);
		log.info("findMemberLoginId called with loginId2 : " + member.getLoginId());
		log.info("findMemberLoginId called with memberPw2 : " + member.getMemberPw());
		
			String encodedPassword = (member == null) ?  "" : member.getMemberPw();
			
		try {
			log.info("findMemberLoginId called with loginId : " + member.getLoginId());
			log.info("findMemberLoginId called with memberPw : " + member.getMemberPw());
		}catch(Exception e) {
			log.error("Error in findMemberByLoginId", e);
			throw e;
		}
		
		System.out.println("findByLoginId(loginId): " + findMemberByLoginId(loginId));
		System.out.println("loginService 설정: " + member);
		
		// 2. 회원 정보 및 비밀번호 체크
		if(member == null || passwordEncoder.matches(memberPw, encodedPassword) == false) {
			return null;
		}
		
		// 3. 회원 응답 객체에서 비밀번호를 제거한 후 회원 정보 리턴
		member.clearPassword();
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
	public MemberVo findMemberByLoginId(final String loginId) {
		return memberMapper.findByLoginId(loginId);
	}
	
	//회원 정보수정
	@Transactional
	public Long updateMember(final MemberDto memberDto) {
		memberDto.encodingPassword(passwordEncoder);
		memberMapper.update(memberDto);
		return memberDto.getMemberId();
	}

	//회원 정보삭제
	@Transactional
	public Long deleteMemberById(final Long memberId) {
		memberMapper.deleteById(memberId);
		return memberId;
	}
	
	//회원 수 카운팅 => id 중복체크
	public int countMemberByLongId(final String loginId) {
		return memberMapper.countByLoginId(loginId);
	}
	
	
}
