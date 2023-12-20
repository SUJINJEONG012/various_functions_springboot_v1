package com.various_functions.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	@PostMapping("/posts/{postId}/comments")
	public CommentResponse saveComment(@PathVariable final Long postId, @RequestBody final CommentRequest params) {
		Long id = commentService.saveComment(params);
		return commentService.findCommentById(id);
	}
	
	//댓글 리스트 조회
	@GetMapping("/posts/{postId}/comments")
	public List<CommentResponse> findAllComment(@PathVariable final Long postId){
		return commentService.findAllComment(postId);
	}
	
	//댓글 상세정보 조회
	@GetMapping("/posts/{postId}/comments/{id}")
	public CommentResponse findCommentById(@PathVariable final Long postId, @PathVariable final Long id) {
		return commentService.findCommentById(id);
	}
	
	//기존댓글 수정
	@PatchMapping("/posts/{postId}/comments/{id}")
	public CommentResponse updateComment(@PathVariable final Long postId, @PathVariable final Long id,@RequestBody final CommentRequest params) {
		commentService.updateComment(params);
		return commentService.findCommentById(id);
	}

	
}
