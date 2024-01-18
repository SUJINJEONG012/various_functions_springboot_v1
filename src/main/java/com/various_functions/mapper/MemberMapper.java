package com.various_functions.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.dto.MemberDto;
import com.various_functions.dto.SearchDto;
import com.various_functions.vo.MemberVo;

@Mapper
public interface MemberMapper {

	/* 회원정보 
	 * 파라미터로 전달받는 memberDto는 요청 클래스 객체, 
	 * memberDto에는 저장할 회원정보가 담기게 된다.
	 */	
	void save(MemberDto memberDto);
	
	/* 회원 상세정보 조회
	 * 파라미터로 memberId를 전달받아 SQL쿼리의 where조건으로 사용하며,
	 * 쿼리가 실행되면 메서드의 리턴 타입인 MemberVo 클래스객체의 
	 * 각 멤버변수에 결과값이 매핑(바인딩)
	 * */
	MemberVo findByLoginId(String loginId);
	
	/* 회원 정보 수정 */
	void update(MemberDto memberDto);
	
	/* 회원 정보 삭제 
	 * member_id - pk
	 * */
	void deleteById(Long MemberId);
	
	/* 회원 리스트 조회 */
	
	List<MemberVo> findAll();
	
	
	/* 회원 수 카운팅 id중복체크
	 * loginId - uk
	 * return 회원수
	 * */
	int countByLoginId(String loginId);
	
}
