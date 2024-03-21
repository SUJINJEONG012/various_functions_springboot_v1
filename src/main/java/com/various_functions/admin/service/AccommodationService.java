package com.various_functions.admin.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.AccommodationAndRoomInfoDto;
import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.mapper.AccommodationsMapper;
import com.various_functions.admin.mapper.RoomInfoMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccommodationService { 

	private final AccommodationsMapper accommodationsMapper;
	
	@Autowired
	public AccommodationService(AccommodationsMapper accommodationsMapper) {
		this.accommodationsMapper = accommodationsMapper;
	}
	
	@Transactional
	public void insertAccommodation(AccommodationsDto accommodationsDto) {
		accommodationsMapper.insertAccommodation(accommodationsDto);
	}
	
	
}
