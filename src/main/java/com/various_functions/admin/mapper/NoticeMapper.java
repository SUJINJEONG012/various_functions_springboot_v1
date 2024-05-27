package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.NoticeDto;
import com.various_functions.admin.vo.NoticeVo;
import com.various_functions.dto.PagedSearchDto;


@Mapper
public interface NoticeMapper {
	
	// 게시글 저장 
	void noticeSave(NoticeDto noticeDto);
	
	// 게시글 상세정보 조회 (특정 게시글 pk값으로 조회)
	NoticeVo findById(Long noticeId);
	
	// 게시글 수정
	void update(NoticeDto noticeDto);

	// 게시글 삭제 
	void deleteById(Long noticeId);
	
	
	/* 게시글 리스트 조회
	 * @return 게시글 리스트
	 * 
	 * 여러개의 게시글을 리스트에 담아 리턴해주는 역할 
	 * 2024.4.23 searchDto 매개변수로 담아서 검색 부분추가
	 * SearchDto searchDto
	 * */
	List<NoticeVo> findAllNotices(PagedSearchDto pagedSearchDto);
	
	
	List<NoticeVo> findNoticeUserById();
	
	/* 최근 게시물 5개 */
	List<NoticeVo> findRecentNotices();
	
	// 게시글 수 카운팅
	int count(PagedSearchDto pagedSearchDto);
	
	// 게시글 조회수 
	void noticeViewCount(Long noticeId);
	
}