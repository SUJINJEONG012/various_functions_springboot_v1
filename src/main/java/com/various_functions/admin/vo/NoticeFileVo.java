package com.various_functions.admin.vo;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class NoticeFileVo {

	private Long fileId;
	private Long noticeId;
	private String originalName;
	private String saveName;
	private int size;
	private Boolean deleteYn;
	private LocalDateTime createdDate; //생성일시
	private LocalDateTime modifiedDate; //최종 수정일시
}
