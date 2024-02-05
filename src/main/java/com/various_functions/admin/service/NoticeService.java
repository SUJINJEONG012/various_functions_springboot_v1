package com.various_functions.admin.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.various_functions.admin.mapper.NoticeMapper;
import com.various_functions.admin.vo.NoticeRequest;
import com.various_functions.admin.vo.NoticeResponse;
import com.various_functions.dto.Pagination;
import com.various_functions.dto.PagingResponse;
import com.various_functions.dto.SearchDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeMapper noticeMapper;
	
	//게시글 저장 
	@Transactional
	public Long savePost(final NoticeRequest params) {
		noticeMapper.save(params);
		return params.getId();
	}
	
	//상세 정보 조회
	public NoticeResponse findPostById(final Long id) {
		return noticeMapper.findById(id);
	}
	
	//게시글 수정
	public Long updatePost(final NoticeRequest params) {
		noticeMapper.update(params);
		return params.getId();
	}
	
	// 게시글 삭제
	public Long deletePost(final Long id) {
		noticeMapper.deletedById(id);
		return id;
	}
	
	//게시글 리스트 조회
//	public List<PostResponse> findAllPost(final SearchDto params){
//		return postMapper.findAll(params);
//	}
	
	
	//리스트 데이터와 계산된 페이지 정보를 함께 리턴
	public PagingResponse<NoticeResponse> findAllPost(final SearchDto params ){
		//조건에 해당하는 데이터가 없는경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
		int count = noticeMapper.count(params);
		if(count < 1) {
			return new PagingResponse<>(Collections.emptyList(), null);
		}
		// Pagination 객체를 생성해서 페이지 정보 갱신 후 SearchDto타입의 객체인 params에 계산된 정보 저장 
		Pagination pagination = new Pagination(count, params);
		params.setPagination(pagination);
		
		// 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
		List<NoticeResponse> list = noticeMapper.findAll(params);
		return new PagingResponse<>(list, pagination);
	}	
	
}
