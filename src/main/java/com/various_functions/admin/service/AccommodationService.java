package com.various_functions.admin.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.various_functions.admin.dto.AccommodationAndRoomInfoDto;
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
	public void saveAccommodationAndRoomInfo(AccommodationAndRoomInfoDto dto, MultipartFile file) throws Exception {		
				
		// 숙소 정보 저장
		accommodationsMapper.saveAccommodation(dto.getAccommodationDto());
		
		// 저장된 숙소 정보의 id 값을 가져옴
		Long accommodationId = dto.getAccommodationDto().getAid();	
		// 가져온 숙소 ID를 객실정보에 설정
		dto.getRoomInfoDto().setAccommodationId(accommodationId);
		// 객실정보 저장
		roomInfoMapper.saveRoomInfo(dto.getRoomInfoDto());
	}
	
	
	

	public List<AccommodationsVo> getAllAwccommodations() {
		return accommodationsMapper.getAllAccommodations();
	}

	
}
