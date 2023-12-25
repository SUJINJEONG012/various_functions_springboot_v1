package com.various_functions.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;


//회원데이터 조회
@Getter
public class MemberResponse {
	
	private Long id;
	private String loginId;
	private String password;
	private String name;
	private Gender gender;
	private LocalDate birthday;
	private Boolean deleteYn;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	// 회원상세정보를 조회한 후 비밀번호를 초기화하는 용도로 사용
	public void clearPassword() {
		this.password = "";
	}
}
