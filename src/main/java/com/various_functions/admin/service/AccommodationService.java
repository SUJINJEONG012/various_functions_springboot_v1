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
	public void saveAccommodationAndRoomInfo(AccommodationAndRoomInfoDto accommodationAndRoomInfoDto){		
		log.info("숙소등록 저장하는 부분 진입확인");
		
		// 숙소정보저장
		AccommodationsDto accommodationsDto = accommodationAndRoomInfoDto.getAccommodationsDto();
		accommodationsMapper.saveAccommodation(accommodationAndRoomInfoDto);
		
		//숙소 앋이디 가져오기
		Long accommodationId = accommodationsDto.getAid();
		
		// 객실정보 저장 => 숙소한곳에는 여러개의 객실이 있으니 리스트로 들고옴
		RoomInfoDto roomInfoDto = accommodationAndRoomInfoDto.getRoomInfoDto();
		if(roomInfoDto != null) {
			// 숙소 정보 아이디 설정
			roomInfoDto.setAid(accommodationId);
			roomInfoMapper.saveRoomInfo(roomInfoDto);
		}
		
		List<RoomInfoDto> roomInfoList = accommodationAndRoomInfoDto.getRoomInfoList();
				if(roomInfoList != null ) {
			for(RoomInfoDto roomInfo : roomInfoList) {
				// 숙소정보 아이디 설정
				roomInfo.setAid(accommodationId);
				roomInfoMapper.saveRoomInfo(roomInfo);
			}
		}
	}

	
	
}
