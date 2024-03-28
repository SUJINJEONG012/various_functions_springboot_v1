package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.AccommodationsFileDto;

@Mapper
public interface AccommodationsFileMapper {
	
	// 회원정보 저장
	void accommodationFileSaveAll(List<AccommodationsFileDto> accommodationsFiles);
	void accommodationSave(AccommodationsFileDto accommodationsFileDto);
	
}

