package com.various_functions.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.various_functions.admin.service.NoticeService;
import com.various_functions.service.InquiryService;
import com.various_functions.service.MemberService;
import com.various_functions.vo.Gender;
import com.various_functions.vo.MemberVo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * 회원가입과 회원정보수정에 사용될 요청 클래스
 * 댓글처리와 마찬가지로 ajax 통신
*/

@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
	
	
	//비밀번호를 암호화하는 기능,
	public void encodingPassword(PasswordEncoder passwordEncoder) {
		if(StringUtils.isEmpty(memberPw)) {
			return ;
		}
		memberPw = passwordEncoder.encode(memberPw);
	}
}
