package com.various_functions.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//댓글요청 
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequest {

	private Long id;
	private Long postId;
	private String content;
	private String writer;
	
}
