package com.various_functions.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.various_functions.dto.InquiryDto;

@Mapper
public interface InquiryMapper {

	// 게시글 저장
	void inquirySave(InquiryDto inquiryDto); 
//	@Select("select * from inquiry")
//	@ResultMap("InquiryResultMap")
//	List<InquiryDto> getInquiryList();
	
}
