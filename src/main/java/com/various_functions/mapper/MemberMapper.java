package com.various_functions.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.domain.MemberRequest;
import com.various_functions.domain.MemberResponse;

@Mapper
public interface MemberMapper {

	//회원정보 저장(회원가입)
	void save(MemberRequest params);
	
	//회원정보 상세정보 조회
	MemberResponse findByLoginId(String loginId);
	
	// 회원정보 수
	void update(MemberRequest params);
	
	//회원정보 삭제
	void deleteById(Long id);
	
	//회원 수 카운팅
	int countByLoginId(String loginId);
}
