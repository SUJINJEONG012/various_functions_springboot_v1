package com.various_functions.dto;

import java.time.LocalDate;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.various_functions.vo.Gender;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 회원가입과 회원정보수정에 사용될 요청 클래스
 * 댓글처리와 마찬가지로 ajax 통신
*/
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

	private Long id;
	private String loginId;
	private String password;
	private String name;
	private Gender gender;
	private LocalDate birthday;
	
	public void encodingPassword(PasswordEncoder passwordEncoder) {
		if(StringUtils.isEmpty(password)) {
			return ;
		}
		password = passwordEncoder.encode(password);
	}
	
}
