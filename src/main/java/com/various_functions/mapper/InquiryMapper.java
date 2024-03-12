package com.various_functions.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.dto.InquiryDto;

@Mapper
public interface InquiryMapper {

	// 게시글 저장
	void inquirySave(InquiryDto inquiryDto); 
}
