package com.various_functions.admin.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NoticeFileDto {

	private Long fileId; // 파일번호
	private Long noticeId; // 게시글번호 
	private String originalName; // 원본파일명
	private String saveName; //저장 파일명
	private int size; //사이즈
	
	// 파일리스트
	//private List<MultipartFile> files;
	
	@Builder
	public NoticeFileDto(Long fileId, Long noticeId, String originalName, String saveName, int size) {
		this.fileId = fileId;
		this.noticeId= noticeId;
		this.originalName = originalName;
		this.saveName = saveName;
		this.size = size;
	}
	
	/* 
	 * 파일은 게시글이 생성(insert)된 후에 처리되어야 한다.
	 * 해당 메서드는 생성된 게시글 ID를 파일 요청 객체의 postId에 저장하는 용도로 사용
	*/
	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}
	

	
	
	
}
