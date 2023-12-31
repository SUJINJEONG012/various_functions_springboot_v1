package com.various_functions.vo;

import java.time.LocalDateTime;

import lombok.Getter;

/* 게시글 응답 클래스 */
@Getter
public class PostResponse {

	private Long id; //pk
	private String title; //제목
	private String content; //내용
	private String writer; //작성자
	private int viewCnt; //조회수
	private Boolean noticeYn; //공지글 여부
	private Boolean deleteYn; //삭제여부
	private LocalDateTime createdDate; //생성일시
	private LocalDateTime modifiedDate; //최종 수정일시
	
}
