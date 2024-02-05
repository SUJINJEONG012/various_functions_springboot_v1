package com.various_functions.admin.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.various_functions.admin.dto.NoticeDto;
import com.various_functions.admin.mapper.NoticeMapper;
import com.various_functions.admin.vo.NoticeVo;
import com.various_functions.dto.Pagination;
import com.various_functions.dto.PagingResponse;
import com.various_functions.dto.SearchDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeMapper noticeMapper;
	
	
	
	
}
