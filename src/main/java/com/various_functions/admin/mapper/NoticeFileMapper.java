package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.NoticeFileDto;
import com.various_functions.admin.vo.NoticeFileVo;

@Mapper
public interface NoticeFileMapper {

	// 파일정보 저장 - 다중파일
	void saveAll(List<NoticeFileDto> files);
	
	// 단일파일 저장
	void noticeFileSave(NoticeFileDto noticeFile);
	
	
	// 이미지 데이터 반환
	List<NoticeFileVo> findFilesByNoticeId(Long noticeId);
	
	/*
	 * 파일 리스트 조회 
	 * @Param -noticeId - 게시글 번호
	 * retrun 파일 리스트 
	 * */
	
	List<NoticeFileVo> findAllByIds(List<Long> ids);
		
	/* 
	 * 파일삭제 
	 * */
	
	void deleteAllByIds(List<Long> ids);
	
	// 파일상세 조회
	NoticeFileVo findById(Long fileId);
	
}
