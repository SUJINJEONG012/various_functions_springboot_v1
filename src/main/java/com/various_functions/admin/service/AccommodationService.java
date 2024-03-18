package com.various_functions.admin.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.various_functions.admin.dto.AccommodationAndRoomInfoDto;
import com.various_functions.admin.dto.AccommodationsDto;
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
		accommodationsMapper.saveAccommodation(dto);
		
		// 저장된 숙소정보와 id값 가져옴
		Long aid = dto.getAccommodationsDto().getAid();
		
		//가져온 숙소id를 객실정보에 설정
		dto.getRoomInfoDto().setAid(aid);
		
		//객실정보 저장
		Long roomId = roomInfoMapper.saveRoomInfo(dto.getRoomInfoDto());
		
		return roomId;
		
	}

	public List<AccommodationsVo> getAllAwccommodations() {
		return accommodationsMapper.getAllAccommodations();
	}

	
}
