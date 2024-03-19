package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.AccommodationAndRoomInfoDto;
import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.vo.AccommodationsVo;

@Mapper
public interface AccommodationsMapper {

	//void saveAccommodation(AccommodationAndRoomInfoDto dto);
	
	void saveAccommodation(AccommodationsDto accommodationsDto);
	
	AccommodationsDto getAccommodationById(Long aid);
	
	AccommodationsDto getAccommodationByName(String aname);
	
	void updateAccommodation(AccommodationsDto accommodationsDto);
    
    void deleteAccommodation(Long aid);
	
    List<AccommodationsVo> getAllAccommodations();

}
