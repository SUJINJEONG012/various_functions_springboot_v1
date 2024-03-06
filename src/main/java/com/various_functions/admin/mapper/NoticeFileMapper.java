package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.NoticeFileDto;

@Mapper
public interface NoticeFileMapper {

	// 파일정보 저장
	void noticeFilesaveAll(List<NoticeFileDto> noticeFiles);
	
}
