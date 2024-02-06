package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.NoticeDto;
import com.various_functions.admin.vo.NoticeVo;

@Mapper
public interface NoticeMapper {
	
	// 게시글 저장 
	void noticeSave(NoticeDto noticeDto);
	
	// 게시글 상세정보 조회 (특정 게시글 pk값으로 조회)
	NoticeVo findById(Long id);
	
	// 게시글 수정
	void update(NoticeDto noticeDto);

	// 게시글 삭제 
	void deleteById(Long id);
	
	/* 게시글 리스트 조회
	 * 여러개의 게시글을 리스트에 담아 리턴해주는 역할
	 * */
	List<NoticeVo> findAllMembers();
	
	
	// 게시글 수 
	int count();
	

}