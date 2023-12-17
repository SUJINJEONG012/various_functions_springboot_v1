package com.various_functions.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.domain.CommentRequest;
import com.various_functions.domain.CommentResponse;
import com.various_functions.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

	private final CommentService commentService;
	
	//신규 댓글 생성
	@PostMapping("/post/{postId}/comments")
	public CommentResponse saveComment(@PathVariable final Long postId, @RequestBody final CommentRequest params) {
		Long id = commentService.saveComment(params);
		return commentService.findCommentById(id);
	}
}
