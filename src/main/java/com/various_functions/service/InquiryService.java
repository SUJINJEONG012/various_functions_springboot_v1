package com.various_functions.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.various_functions.dto.InquiryDto;
import com.various_functions.mapper.InquiryMapper;
import com.various_functions.vo.InquiryVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InquiryService {
	
	private final InquiryMapper inquiryMapper;
	
	// 문의글 저장
	@Transactional
	public Long inquirySave(final InquiryDto inquiryDto) {
		inquiryMapper.inquirySave(inquiryDto);
		return inquiryDto.getInquiryId();
	}
	
	// 문의글 전체 리스트 조회 
	public List<InquiryVo> findAllInquiry(){
		return inquiryMapper.findAllInquiry();
	}
	
	 // 문의글 전체 리스트와 작성자 회원 이름 출력하기
    public List<InquiryVo> findAllInquiryWithMemberNames() {
        return inquiryMapper.findAllInquiryWithMemberNames();
    }
	

}
