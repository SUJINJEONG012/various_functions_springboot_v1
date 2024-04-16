package com.various_functions.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	private String formattedCreatedDate; // 년월일 형식으로 포맷팅된 날짜 문자열

	private LocalDateTime modifiedDate;
	
	private String formattedDate; // 포맷된 날짜 문자열

	private Long memberId;
	private String memberName; // 문의글 시  작성자이름 표출하기 위해 조인하기위한 필드
	
	
	// memberName 필드에 대한 setter 메서드
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    // formattedDate를 설정하는 메서드
    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
    
 // 생성일을 년월일 형식으로 변환하여 문자열로 반환하는 메서드
    public String getFormattedCreatedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        return createdDate.format(formatter);
    }

    // 년월일 형식으로 포맷팅된 날짜 문자열을 설정하는 메서드
    public void setFormattedCreatedDate(String formattedCreatedDate) {
        this.formattedCreatedDate = formattedCreatedDate;
    }
    
    
}
