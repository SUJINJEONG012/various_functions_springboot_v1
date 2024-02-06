package com.various_functions.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.various_functions.admin.dto.NoticeDto;
import com.various_functions.admin.mapper.NoticeMapper;
import com.various_functions.admin.vo.NoticeVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeMapper noticeMapper;
	
	// 게시글 저장
	@Transactional
	public Long noticeSave(final NoticeDto noticeDto) {
		noticeMapper.noticeSave(noticeDto);	
		return noticeDto.getId();
	}
	
	// 게시글 상세정보 조회
	public NoticeVo findById(final Long id) {
		return noticeMapper.findById(id);
	}
	
	// 게시글 수정
	@Transactional
	public Long noticeUpdate(final NoticeDto noticeDto) {
		noticeMapper.update(noticeDto);
		return noticeDto.getId();
	}
	
	// 게시글 삭제
	public Long delete(final Long id) {
		noticeMapper.deleteById(id);
		return id;
	}
	
	// 게시글 리스트 조회
	public List<NoticeVo> findAllMembers(){
		return noticeMapper.findAllMembers();
	}
	
	
}
