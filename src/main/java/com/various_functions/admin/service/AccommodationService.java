package com.various_functions.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.mapper.AccommodationsMapper;
import com.various_functions.admin.vo.AccommodationsVo;

@Service
public class AccommodationService {

	@Autowired
	private AccommodationsMapper accommodationsMapper;

	
	@Transactional
	public Long accommodations(final AccommodationsDto accommodationsDto) {
		accommodationsMapper.save(accommodationsDto);
		return accommodationsDto.getId();
	}
	
	public List<AccommodationsVo> getAllAwccommodations() {
		return accommodationsMapper.getAllAccommodations();
	}
}
