package com.various_functions.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.AccommodationAndRoomInfoDto;
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
	public void saveAccommodationAndRoomInfo(AccommodationsDto accommodationsDto, RoomInfoDto roomInfoDto) throws Exception {		
		log.info("Saving accommodation : {}", accommodationsDto);
		// 1. 숙소 정보 저장
		accommodationsMapper.saveAccommodation(accommodationsDto);
		
		// 2. 저장된 숙소 정보의 id 값을 가져옴
		Long accommodationId = accommodationsDto.getAid();
		log.info("Accommodation saved successfully !");
		
		// 3. 가져온 숙소 ID를 객실 정보에 설정
		roomInfoDto.setAccommodationId(accommodationId);
		log.info("Saving room info : {} ", roomInfoDto);
		
		// 4. 객실정보 저장
		roomInfoMapper.saveRoomInfo(roomInfoDto);
		log.info("Room info saved successfully !");
	}

	
	
	public List<AccommodationsVo> getAllAwccommodations() {
		return accommodationsMapper.getAllAccommodations();
	}

	
}
