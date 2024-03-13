package com.various_functions.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.dto.InquiryDto;
import com.various_functions.vo.InquiryVo;

@Mapper
public interface InquiryMapper {

	// 게시글 저장
	void inquirySave(InquiryDto inquiryDto); 
	
	// 게시글 전체 리스트 조회
	List<InquiryVo> findAllInquiry();
	
	// 문의글 전체리스트와 작성자 회원이름 출력하기
	List<InquiryVo> findAllInquiryWithMemberNames();
	
}
