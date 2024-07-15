package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.vo.AccommodationsVo;

@Mapper
public interface AccommodationsMapper {

	Long insertAccommodation(AccommodationsDto accommodationsDto);
	
	// 숙소 전체 리스트 조회
	List<AccommodationsVo> findAllAccommodations();
	
	// 메인에 노출할 3개 출력
	List<AccommodationsVo> findRecentAccommodations();

	// 숙소 상세정보 조회
	AccommodationsVo findById(Long accommodationId);
	
	// 숙소 수정
	void update(AccommodationsDto accommodationsDto);
	
	// 숙소 삭제
	void delete(Long accommodationId);
	
	
}
