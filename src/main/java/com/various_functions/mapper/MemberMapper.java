package com.various_functions.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.dto.MemberDto;
import com.various_functions.dto.SearchDto;
import com.various_functions.vo.MemberVo;

@Mapper
public interface MemberMapper {

	//회원정보 저장(회원가입)
	void memberSave(MemberDto memberDto);

	//회원정보 상세정보 조회
	MemberVo findByLoginId(String loginId);
	        
	// 회원정보 리스트 
	List<MemberVo> findAll(MemberVo id);
	
	// 회원정보 수정
	void update(MemberDto params);
	
	//회원정보 삭제
	void deleteById(Long id);
	
	//회원 수 카운팅
	int countByLoginId(String loginId);
}
