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
	
}
