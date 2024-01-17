package com.various_functions.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.dto.MemberDto;
import com.various_functions.dto.SearchDto;
import com.various_functions.vo.MemberVo;

@Mapper
public interface MemberMapper {

	// 회원정보 
	void save(MemberDto memberDto);
	
	
	
}
