package com.various_functions.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
	
	//게시글 저장
	void save(NoticeDto noticeDto);
	
	
	
}