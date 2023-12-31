package com.various_functions.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.dto.CommentSearchDto;
import com.various_functions.vo.CommentRequest;
import com.various_functions.vo.CommentResponse;

@Mapper
public interface CommentMapper {

	//댓글저장
	void save(CommentRequest params);
	
	// 댓글리스트 조회
	List<CommentResponse> findAll(CommentSearchDto params);
	
	/*
	 * 댓글 상세정보 조회
	 * @Param id -pk
	 * @return 댓글상세정보
	*/
	CommentResponse findById(Long id);
	
	//댓글수정
	void update(CommentRequest params);
	
	// 댓글삭제
	void deleteById(Long id);
	
	// 댓글 수 카운팅
	int count(CommentSearchDto params);
}
