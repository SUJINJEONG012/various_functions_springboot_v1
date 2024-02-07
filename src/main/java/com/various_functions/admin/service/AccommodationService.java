package com.various_functions.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.various_functions.admin.mapper.AccommodationsMapper;
import com.various_functions.admin.vo.AccommodationsVo;

@Service
public class AccommodationService {

	@Autowired
	private AccommodationsMapper accommodationsMapper;
	
	public List<AccommodationsVo> getAllAwccommodations(){
		return accommodationsMapper.getAllAccommodations();
	}
}
