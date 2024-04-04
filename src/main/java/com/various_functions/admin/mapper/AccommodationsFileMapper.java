package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.AccommodationsFileDto;

@Mapper
public interface AccommodationsFileMapper {
	
	// 파일저장
	void accommodationFileSaveAll(List<AccommodationsFileDto> accommodationsFiles);
	// 단일파일저장
	void accommodationSave(AccommodationsFileDto accommodationsFileDto);
	
	// 이미지 데이터 변환
	
}

