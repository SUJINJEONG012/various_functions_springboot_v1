package com.various_functions.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NoticeFileDto {

	private long fileId;
	private long noticeId;
	private String originalName;
	private String savdName;
	private int size;
	
	@Builder
	public NoticeFileDto(String originalName, String saveName, int size) {
		this.originalName = originalName;
		this.savdName = saveName;
		this.size = size;
	}
	
	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}
	
}
