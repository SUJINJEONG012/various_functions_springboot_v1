package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.vo.AccommodationsVo;

@Mapper
public interface AccommodationsMapper {
	
	public int insert(AccommodationsVo accommodationsVo);
	
	void save(AccommodationsDto accommodationsDto);
	
	List<AccommodationsVo> getAllAccommodations();
	
}
