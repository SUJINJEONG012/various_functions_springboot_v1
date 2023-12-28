package com.various_functions.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.dto.FileDto;

@Mapper
public interface FileMapper {

	// 파일 정보 저장
	void saveAll(List<FileDto> files);
}
