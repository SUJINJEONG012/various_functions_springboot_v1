package com.various_functions.service;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.various_functions.dto.FileDto;
import com.various_functions.mapper.FileMapper;
import com.various_functions.vo.FileEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	private final FileMapper fileMapper;
	
	// 파일 저장
	@Transactional
	public void saveFiles(final Long postId, final List<FileDto> files) {
		if(CollectionUtils.isEmpty(files)) {
			return;
		}
		for(FileDto file : files) {
			file.setPostId(postId);
		}
		
		fileMapper.saveAll(files);
	}
	
	/* 파일 리스트 조회
	 * @Param postId - 게시글 번호 FK
	 * @return 파일 리스트
	 * */
	public List<FileEntity> findAllFileByPostId(final Long postId){
		return fileMapper.findAllByPostId(postId);
	}
	
	
	/* 파일 리스트 조회
	 * @Param ids - PK 리스트
	 * @return 파일 리스트
	 * */
	public List<FileEntity> findAllFileByIds(final List<Long> ids){
		
		if(CollectionUtils.isEmpty(ids)) {
			return Collections.emptyList();
		}
		return fileMapper.findAllByIds(ids);
	}
	
	// 파일 상세정보 조회 간단한 쿼리 실행결과만 리턴
	public FileEntity findFileById(final Long id) {
		return fileMapper.findById(id);
	}
	
	
	/* 파일 삭제 
	 * @Param ids PK 리스트 
	 * */
	@Transactional
	public void deleteAllFileByIds(final List<Long> ids) {
		if(CollectionUtils.isEmpty(ids)) {
			return ;
		}
		fileMapper.deleteAllByIds(ids);
	}
	
}
