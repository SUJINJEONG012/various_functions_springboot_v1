package com.various_functions.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//회원데이터 조회
@Getter
@Setter
//생성자를 통해서 값 변경 목적으로 접근하는 메시지들 차단
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberVo {
	
	private Long memberId; //pk 아이디
	private String loginId; // 로그인 아이디
	private String memberPw;
	private String memberName;
	private String memberEmail;
	private Gender gender;
	private LocalDate birthday;
	private String memberAddr1;
	private String memberAddr2;
	private String memberAddr3;
	private Boolean deleteYn;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private int adminCk;
	private int money;

	// 회원상세정보를 조회한 후 비밀번호를 초기화하는 용도로 사용
	public void clearPassword() {
		this.memberPw = "";
	}
	
	
}
