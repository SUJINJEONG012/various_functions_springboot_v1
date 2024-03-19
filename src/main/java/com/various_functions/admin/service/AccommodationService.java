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
	public Long saveAccommodationAndRoomInfo(final AccommodationAndRoomInfoDto dto){		
		log.info("숙소등록 저장하는 부분 진입확인");
		
		// 숙소저장
		AccommodationsDto accommodationsDto = dto.getAccommodationsDto();
	    accommodationsMapper.saveAccommodation(accommodationsDto);
		
		// 저장된 숙소정보와 id값 가져옴
		Long aid = dto.getAccommodationsDto().getAid();
		
		// 객실 정보 저장
	    RoomInfoDto roomInfoDto = dto.getRoomInfoDto();
	    roomInfoDto.setAid(aid);
	    Long roomId = roomInfoMapper.saveRoomInfo(roomInfoDto);
		
		return roomId;
		
	}

	public List<AccommodationsVo> getAllAwccommodations() {
		return accommodationsMapper.getAllAccommodations();
	}

	
}
