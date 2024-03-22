package com.various_functions.admin.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.mapper.AccommodationsMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccommodationService {

	private final AccommodationsMapper accommodationsMapper;
	private final RoomInfoService roomInfoService; 
	
	@Transactional
	public Long insertAccommodation(AccommodationsDto accommodationsDto) {
		log.info("insertAccommodation 메서드 진입 ");

		if (accommodationsDto == null) {
			log.error("AccommodationsDto is null");
			return null;
		}

		log.info("insertAccommodation 숙소 정보 저장 전 ");
		// 숙소 정보 저장
		accommodationsMapper.insertAccommodation(accommodationsDto);
		log.info("insertAccommodation 숙소 정보 저장 후 ");

		
		Long aid = accommodationsDto.getAid();
		
		// 객실 정보 저장
		if(aid != null) {
			RoomInfoDto roomInfoDto = new RoomInfoDto();
			roomInfoDto.setAccommodations(accommodationsDto.getAid());
			Long riid = roomInfoService.insertRoomInfo(roomInfoDto);
			log.info("insertAccommodation 객실 정보 저장 후");
		}
		return aid;
	}

}
