package com.various_functions.dto;

import lombok.Data;

@Data
public class InquiryDto {

	//게시판에 글을 작성할 때 입력하는 필드는 제목,내용,작성자,공지글 여부
		private Long inquiryId;
		private String title;
		private String content;
		private Long memberId;

		
}
