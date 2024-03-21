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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccommodationService { 

	private final AccommodationsMapper accommodationsMapper;
	private final RoomInfoMapper roomInfoMapper;
	
	@Transactional
	public void insertAccommodation(AccommodationAndRoomInfoDto accommodationAndRoomInfoDto) {
		log.info("insertAccommodation 메서드 진입 ");
		if (accommodationAndRoomInfoDto == null) {
            throw new IllegalArgumentException("AccommodationAndRoomInfoDto is null");
        }
		
		log.info("insertAccommodation 숙소 정보 저장 전 ");
		// 숙소 정보 저장
        accommodationsMapper.insertAccommodation(accommodationAndRoomInfoDto.getAccommodationsDto());
        log.info("insertAccommodation 숙소 정보 저장 후 ");
        // 객실 정보 저장
//        for (RoomInfoDto roomInfoDto : accommodationAndRoomInfoDto.getRoomInfoList()) {
//            roomInfoMapper.insertRoomInfo(roomInfoDto);
//        }
		
	}
	
	
}
