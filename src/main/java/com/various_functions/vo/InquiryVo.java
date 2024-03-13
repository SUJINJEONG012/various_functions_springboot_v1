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
	
}
