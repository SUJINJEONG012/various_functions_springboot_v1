package com.various_functions.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.various_functions.dto.InquiryDto;
import com.various_functions.mapper.InquiryMapper;

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

}
