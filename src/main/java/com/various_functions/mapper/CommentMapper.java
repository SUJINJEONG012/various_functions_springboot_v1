package com.various_functions.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.domain.CommentRequest;
import com.various_functions.domain.CommentResponse;

@Mapper
public interface CommentMapper {

	//댓글저장
	void save(CommentRequest params);
	
	//댓글 상세정보 조회
	CommentResponse findById(Long id);
	
	//댓글수정
	void update(CommentRequest params);
	
	// 댓글삭제
	void deleteById(Long id);
	
	// 댓글리스트 조회
	List<CommentResponse> findAll(Long postId);
	
	// 댓글 수 카운팅
	int count(Long postId);
}
