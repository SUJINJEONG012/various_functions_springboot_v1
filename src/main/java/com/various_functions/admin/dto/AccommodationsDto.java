package com.various_functions.admin.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationsDto {

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
