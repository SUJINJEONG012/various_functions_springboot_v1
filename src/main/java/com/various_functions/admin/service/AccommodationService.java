package com.various_functions.admin.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.mapper.AccommodationsMapper;
import com.various_functions.admin.mapper.RoomInfoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccommodationService {

	private final AccommodationsMapper accommodationsMapper;
	private final RoomInfoMapper roomInfoMapper; 
	
	@Transactional
	public Long insertAccommodationAndRoomInfo(
			AccommodationsDto accommodationsDto,
			RoomInfoDto roomInfoDto) {
		
		log.info("insertAccommodation 메서드 진입 ");

		// 숙소정보저장
		Long aid= accommodationsMapper.insertAccommodation(accommodationsDto);
		// 객실정보에숙소아이디 저장
		roomInfoDto.setAid(aid);
		
		Long riid = roomInfoMapper.insertRoomInfo(roomInfoDto);
		
		return aid;
	}

}
