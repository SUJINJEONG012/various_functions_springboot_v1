package com.various_functions.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.various_functions.domain.PostRequest;
import com.various_functions.domain.PostResponse;
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
	public List<PostResponse> findAllPost(final SearchDto params){
		return postMapper.findAll(params);
	}
	
	
}
