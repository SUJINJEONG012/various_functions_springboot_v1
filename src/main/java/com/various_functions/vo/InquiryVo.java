package com.various_functions.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InquiryVo {
	
	private Long inquiryId;
	private String title;
	private String content;
	private int viewCnt;
	private Boolean deleteYn;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	private Long memberId;
	private String memberName; // 문의글 시  작성자이름 표출하기 위해 조인하기위한 필드
	
	
	// memberName 필드에 대한 setter 메서드
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
