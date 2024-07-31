package com.various_functions.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.ResDto;

@Mapper
public interface ResMapper {

	// 게시글 저장 
	void resSave(ResDto resDto);
}
