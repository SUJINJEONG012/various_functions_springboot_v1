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

@Service
public class AccommodationService {

	@Autowired
	private AccommodationsMapper accommodationsMapper;

	@Autowired
	private RoomInfoMapper roomInfoMapper;
	
	
	@Transactional
	public void saveAccommodationAndRoomInfo(final AccommodationAndRoomInfoDto accommodationAndRoomInfoDto) {
		// 숙소 정보 저장
		AccommodationsDto accommodationsDto = new AccommodationsDto();
		accommodationsDto.setAname(accommodationAndRoomInfoDto.getAname());
		
		accommodationsMapper.insertAccommodation(accommodationsDto);
		
		// 객실 정보 저장
        RoomInfoDto roomInfoDto = new RoomInfoDto();
        roomInfoDto.setRiroomtype(accommodationAndRoomInfoDto.getRiroomtype());
        // 나머지 필드 설정
        
        roomInfoMapper.insertRoomInfo(roomInfoDto);
	}
	
	
	
	
	public List<AccommodationsVo> getAllAwccommodations() {
		return accommodationsMapper.getAllAccommodations();
	}
}
