package com.various_functions.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.mapper.AccommodationsMapper;
import com.various_functions.admin.mapper.RoomInfoMapper;
import com.various_functions.admin.vo.AccommodationsVo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccommodationService { 

	private final AccommodationsMapper accommodationsMapper;
	private final RoomInfoMapper roomInfoMapper;
	
	@Autowired
	public AccommodationService(AccommodationsMapper accommodationsMapper, RoomInfoMapper roomInfoMapper ) {
		this.accommodationsMapper = accommodationsMapper;
		this.roomInfoMapper = roomInfoMapper;
	}
	
	@Transactional
	public void saveAccommodationAndRoomInfo(AccommodationsDto accommodationsDto, RoomInfoDto roomInfoDto) {
		// 숙소 정보와 객실 정보를 각각 따로 저장
		
		// 1. 숙소 정보 저장
        accommodationsMapper.saveAccommodation(accommodationsDto);
        
        // 2. 속소 정보의 id값을 가져와 객실정보 할당
        Long accommodationId  = accommodationsDto.getAid();
        roomInfoDto.setAccommodationId(accommodationId);
        // 3. 객실 정보 저장
        roomInfoMapper.saveRoomInfo(roomInfoDto);
        log.info("roomInfoDto : "  + roomInfoDto );
	}

	public List<AccommodationsVo> getAllAwccommodations() {
		return accommodationsMapper.getAllAccommodations();
	}
}
