package com.various_functions.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.various_functions.domain.CommentRequest;
import com.various_functions.domain.CommentResponse;
import com.various_functions.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentMapper commentMapper;
	
	/*댓글 저장
	 * @param - 댓글 정보
	 * @return Generated PK
	 */
	@Transactional
	public Long saveComment(final CommentRequest params) {
		commentMapper.save(params);
		return params.getId();
	}
	
	/* 댓글 상세정보 조회 */
	public CommentResponse findCommentById(final Long id) {
		return commentMapper.findById(id);
	}
	
	/* 댓글 수정 */
	@Transactional
	public Long updateComment(final CommentRequest params) {
		commentMapper.update(params);
		return params.getId();
	}
	
	/* 댓글삭제 */
	@Transactional
	public Long deleteComment(final Long id) {
		commentMapper.deleteById(id);
		return id;
	}
	
	//댓글조회
	public List<CommentResponse> findAllComment(final Long postId){
		return commentMapper.findAll(postId);
	}
	
	
}
