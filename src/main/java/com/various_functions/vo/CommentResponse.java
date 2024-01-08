package com.various_functions.vo;

import java.time.LocalDateTime;

import lombok.Getter;

//entity 

@Getter
public class CommentResponse {

	private Long id;
	private Long postId;
	private String content;
	private String writer;
	private Boolean deleteYn;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
}
