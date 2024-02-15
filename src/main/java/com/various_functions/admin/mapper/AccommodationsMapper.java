package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.vo.AccommodationsVo;

@Mapper
public interface AccommodationsMapper {

	void insertAccommodation(AccommodationsDto accommodationsDto);
	
	AccommodationsDto getAccommodationById(Long aid);
	
	void updateAccommodation(AccommodationsDto accommodationsDto);
    
    void deleteAccommodation(Long aid);
	
    List<AccommodationsVo> getAllAccommodations();
}
