package com.various_functions.service;

import java.util.Collections;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.various_functions.domain.PostRequest;
import com.various_functions.domain.PostResponse;
import com.various_functions.dto.Pagination;
import com.various_functions.dto.PagingResponse;
import com.various_functions.dto.SearchDto;
import com.various_functions.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostMapper postMapper;
	
	//게시글 저장 
	@Transactional
	public Long savePost(final PostRequest params) {
		postMapper.save(params);
		return params.getId();
	}
	
	//상세 정보 조회
	public PostResponse findPostById(final Long id) {
		return postMapper.findById(id);
	}
	
	//게시글 수정
	public Long updatePost(final PostRequest params) {
		postMapper.update(params);
		return params.getId();
	}
	
	// 게시글 삭제
	public Long deletePost(final Long id) {
		postMapper.deletedById(id);
		return id;
	}
	
	//게시글 리스트 조회
//	public List<PostResponse> findAllPost(final SearchDto params){
//		return postMapper.findAll(params);
//	}
	
	public PagingResponse<PostResponse> finaAllPost(final SearchDto params ){
		//조건에 해당하는 데이터가 없는경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
		int count = postMapper.count(params);
		if(count < 1) {
			return new PagingResponse<>(Collections.emptyList(), null);
		}
		// Pagination 객체를 생성해서 페이지 정보 갱신 후 SearchDto타입의 객체인 params에 계산된 정보 저장 
		Pagination pagination = new Pagination(count, params);
		params.setPagination(pagination);
		
		
		
		return new PagingResponse<>(list, pagination);
	}	
	
}
