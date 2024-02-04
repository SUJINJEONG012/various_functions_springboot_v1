package com.various_functions.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.vo.AccommodationsVo;
import com.various_functions.dto.MemberDto;

@Mapper
public interface AccommodationsMapper {
	
	public int insert(AccommodationsVo accommodationsVo);
	
	//void save(MemberDto memberDto);
}
