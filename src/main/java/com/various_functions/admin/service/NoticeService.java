package com.various_functions.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.NoticeDto;
import com.various_functions.admin.mapper.NoticeMapper;
import com.various_functions.admin.vo.NoticeVo;
import com.various_functions.dto.SearchDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeMapper noticeMapper;
	
	// 공지사항 저장
	@Transactional
	public Long noticeSave(final NoticeDto noticeDto) {
		noticeMapper.noticeSave(noticeDto);	
		return noticeDto.getNoticeId();
	}
	
	// 공지사항 상세정보 조회
	public NoticeVo findById(final Long noticeId) {
		return noticeMapper.findById(noticeId);
	}
	
	// 게시글 수정
	@Transactional
	public Long noticeUpdate(final NoticeDto noticeDto) {
		noticeMapper.update(noticeDto);
		return noticeDto.getNoticeId();
	}
	
	// 게시글 삭제
	public Long delete(final Long noticeId) {
		noticeMapper.deleteById(noticeId);
		return noticeId;
	}
	
	// 게시글 리스트 조회 => user, admin 공통으로 들고오기 위한 코드
	public List<NoticeVo> findAllNotices(){
		return noticeMapper.findAllNotices();
	}
	
	
	
}
