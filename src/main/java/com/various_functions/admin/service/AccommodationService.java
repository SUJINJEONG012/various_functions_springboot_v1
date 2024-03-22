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
	public Long insertAccommodation(AccommodationsDto accommodationsDto) {
		
	    log.info("insertAccommodation 메서드 진입 ");
	    
	    if (accommodationsDto == null) {
	        log.error("AccommodationsDto is null");
	    } else {
	    	
	        log.info("insertAccommodation 숙소 정보 저장 전 ");
	        // 숙소 정보 저장
	        accommodationsMapper.insertAccommodation(accommodationsDto);
	        log.info("insertAccommodation 숙소 정보 저장 후 ");
	        
	        // 저장된 숙소의 키(주키)를 가져오기 위해 MyBatis의 selectKey를 사용하여 생성된 키를 가져옴
	        Long aid = accommodationsDto.getAid();
	        
	        return aid;
	    }
	    
	    return null;
	}
	
}
