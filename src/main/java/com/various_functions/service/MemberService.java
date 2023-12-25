package com.various_functions.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.various_functions.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberService;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public Long saveMember(final MemberRequest params) {
		
	}
}
