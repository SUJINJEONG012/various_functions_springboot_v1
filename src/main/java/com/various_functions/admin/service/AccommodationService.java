package com.various_functions.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.mapper.AccommodationsMapper;
import com.various_functions.admin.vo.AccommodationsVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccommodationService { 

	@Autowired
	private AccommodationsMapper accommodationsMapper;

	
	@Transactional
	public void saveAccommodation(final AccommodationsDto accommodationsDto){		
		log.info("숙소등록 저장하는 부분 진입확인");
		accommodationsMapper.saveAccommodation(accommodationsDto);
		
	}

		
}
