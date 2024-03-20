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
	public void saveAccommodation(final AccommodationsDto accommodationsDto){		
		log.info("숙소등록 저장하는 부분 진입확인");
		
		accommodationsMapper.saveAccommodation(accommodationsDto);
		log.info("숙소저장하는 매퍼에 저장되는지 확인!");
		
		//숙소가 저장된 후 해당 숙소의 id를 얻어옴
		long aid = accommodationsDto.getAid();
		log.info("accommodationsDto.getAid() :들고온느지 확인 ");
		
		
		// 4. 객실정보 저장
		roomInfoMapper.saveRoomInfo(dto.getRoomInfoDto());
	}

	
	
	public List<AccommodationsVo> getAllAwccommodations() {
		return accommodationsMapper.getAllAccommodations();
	}

	
}
