package com.various_functions.dto;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.various_functions.vo.Gender;

import lombok.Data;

/*
 * 회원가입과 회원정보수정에 사용될 요청 클래스
 * 댓글처리와 마찬가지로 ajax 통신
*/
@Data
public class MemberDto {

	private Long memberId;
	private String loginId;
	private String memberPw;
	private String memberName;
	private String memberMail;
	private Gender gender;
	private LocalDate birthday;
	private String memberAddr1;
	private String memberAddr2;
	private String memberAddr3;
	private int adminCk;
	private int money;
	
	//비밀번호를 암호화하는 기능,
	public void encodingPassword(PasswordEncoder passwordEncoder) {
		if(StringUtils.isEmpty(memberPw)) {
			return ;
		}
		memberPw = passwordEncoder.encode(memberPw);
	}

	public HttpSession getSession() {
		
		return null;
	}		

	
}
