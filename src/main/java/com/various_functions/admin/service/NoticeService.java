package com.various_functions.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.NoticeDto;
import com.various_functions.admin.mapper.NoticeMapper;
import com.various_functions.admin.vo.NoticeVo;
import com.various_functions.dto.PagedSearchDto;

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
	
	//게시글 조회수 추가 
	@Transactional
	public void noticeViewCount(Long noticeId) {
		noticeMapper.noticeViewCount(noticeId);
	}

		
	// 게시글 수정
	@Transactional
	public Long updateNotice(final NoticeDto noticeDto) {
		noticeMapper.update(noticeDto);
		return noticeDto.getNoticeId();
	}
	
	// 게시글 삭제
	public Long delete(final Long noticeId) {
		noticeMapper.deleteById(noticeId);
		return noticeId;
	}
	
	// 게시글 전체 리스트 조회 => user, admin 공통으로 들고오기 위한 코드
	public List<NoticeVo> findAllNotices(final PagedSearchDto pagedSearchDto){
		return noticeMapper.findAllNotices(pagedSearchDto);
	}

	// 내가 쓴글 리스트 조회하기 -> 마이페이지에서 사용 할 메서드
	public List<NoticeVo> findNoticeUserById(String loginId) {
		// 데이터베이스에서 해당 사용자가 작성한 게시글을 조회하여 반환하는 로직
		return null;
	}
	
	// 메인에 최근게시물 5개 출력
	public List<NoticeVo> findRecentNotices(){
		return noticeMapper.findRecentNotices();
	}

	
	

	
	
}
