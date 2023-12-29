package com.various_functions.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.domain.FileEntity;
import com.various_functions.dto.FileDto;

@Mapper
public interface FileMapper {

	// 파일 정보 저장
	void saveAll(List<FileDto> files);
	
	List<FileEntity> findAllByPostId(Long postId);
	
	// 파일 리스트 조회
	List<FileEntity> findAllByIds(List<Long> ids);
	
	// 파일 삭제
	void deleteAllByIds(List<Long> ids);
	
}
