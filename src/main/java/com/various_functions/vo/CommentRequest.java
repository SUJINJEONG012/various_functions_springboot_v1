package com.various_functions.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//댓글요청 
@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {

	private Long id;
	private Long postId;
	private String content;
	private String writer;
	
}
