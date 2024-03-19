package com.various_functions.admin.service;

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

	@Autowired
	private AccommodationsMapper accommodationsMapper;
	private RoomInfoMapper roomInfoMapper;

	
	@Transactional
	public void saveAccommodation(final AccommodationsDto accommodationsDto){		
		log.info("숙소등록 저장하는 부분 진입확인");
		accommodationsMapper.saveAccommodation(accommodationsDto);
		
		//숙소가 저장된 후 해당 숙소의 id를 얻어옴
		Long aid = accommodationsDto.getAid();
		
		//객실정보에 숙소의 id를 설정하여 저장
		for(RoomInfoDto roomInfo : accommodationsDto.getRoomInfoList()) {
			roomInfo.setAid(aid);
			roomInfoMapper.saveRoomInfo(roomInfo);	
		}
	}

		
}
