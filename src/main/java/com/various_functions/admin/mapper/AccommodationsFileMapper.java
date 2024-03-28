package com.various_functions.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.AccommodationsFileDto;

@Mapper
public interface AccommodationsFileMapper {
	
	// 회원정보 저장
	void accommodationFileSaveAll();
	void accommodationSave(AccommodationsFileDto accommodationsFileDto);
	
}

