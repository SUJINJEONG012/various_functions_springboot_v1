package com.various_functions.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.various_functions.admin.vo.RoomInfoVo;
import com.various_functions.dto.MemberDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


//회원데이터 조회
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVo {
	
	private Long memberId; //pk 아이디
	private String loginId; // 로그인 아이디
	private String memberPw;
	private String memberName;
	private String memberMail;
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
	
	public boolean isAdmin() {
		return adminCk == 1;
	}

	
	
	
}
