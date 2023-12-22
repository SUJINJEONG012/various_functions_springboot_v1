package com.various_functions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSearchDto extends SearchDto {

	//게시글 번호 PK
	private Long postId;
}
