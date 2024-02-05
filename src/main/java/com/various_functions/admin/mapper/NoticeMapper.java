package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.vo.NoticeRequest;
import com.various_functions.admin.vo.NoticeResponse;
import com.various_functions.dto.SearchDto;

@Mapper
public interface NoticeMapper {

	/*게시글 저장
	 * @Param params - 게시글 정보
	 * */
	 void save(NoticeRequest params);
	
	/*
	 * 게시글 상세정보 조회
	 * */
	NoticeResponse findById(Long id);
	
	/*
	 * 게시글 수정
	 * */
	void update(NoticeRequest params);
	
	/*
	 * 게시글 삭제
	 * @Param id - PK
	 * */
	void deletedById(Long id);
	
	/* 게시글 리스트 조회 @return 게시글 리스트 */
	List<NoticeResponse> findAll(SearchDto params);
	
	/*
	 * 게시글 수 카운팅
	 * */
	int count(SearchDto params);
	
}
