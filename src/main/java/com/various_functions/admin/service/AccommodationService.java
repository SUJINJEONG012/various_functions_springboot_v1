package com.various_functions.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.mapper.AccommodationsMapper;
import com.various_functions.admin.mapper.RoomInfoMapper;
import com.various_functions.admin.vo.AccommodationsVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccommodationService {

	private final AccommodationsMapper accommodationsMapper;
	private final RoomInfoMapper roomInfoMapper;

	@Transactional
	public Long insertAccommodationAndRoomInfo(AccommodationsDto accommodationsDto) {

		log.info("insertAccommodation 메서드 진입 ");

		// 숙소정보저장
		accommodationsMapper.insertAccommodation(accommodationsDto);
		return accommodationsDto.getAccommodationId();
	}

	public List<AccommodationsVo> findAllAccommodations() {
		List<AccommodationsVo> accommodations = accommodationsMapper.findAllAccommodations();
		log.info("Found {} accommodations", accommodations.size());
        
		return accommodationsMapper.findAllAccommodations();
	}

}
