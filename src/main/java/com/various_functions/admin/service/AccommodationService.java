package com.various_functions.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.mapper.AccommodationsMapper;
import com.various_functions.admin.mapper.RoomInfoMapper;
import com.various_functions.admin.vo.AccommodationsFileVo;
import com.various_functions.admin.vo.AccommodationsVo;
import com.various_functions.admin.vo.NoticeVo;
import com.various_functions.admin.vo.RoomInfoVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccommodationService {

	private final AccommodationsMapper accommodationsMapper;
	private final RoomInfoMapper roomInfoMapper;
	private final RoomInfoService roomInfoService;
	private final AccommodationFileService accommodationFileService;
	
	// 숙소저장
	@Transactional
	public Long insertAccommodationAndRoomInfo(AccommodationsDto accommodationsDto) {

		log.info("insertAccommodation 메서드 진입 ");
		// 숙소정보저장
		accommodationsMapper.insertAccommodation(accommodationsDto);
		return accommodationsDto.getAccommodationId();
	}
	

    // 게시글 리스트
	public List<AccommodationsVo> findAllAccommodations() {
		
		List<AccommodationsVo> accommodations = accommodationsMapper.findAllAccommodations();
		
		for(AccommodationsVo accommodation : accommodations) {
			log.info("for문 진입!!!!");
			
			//숙소에 해당하는 객실정보 조회
			List<RoomInfoVo> rooms = roomInfoService.findRoomsByAccommodationId(accommodation.getAccommodationId());
			accommodation.setRooms(rooms);
			log.info("rooms: {}", rooms);
			
			//숙소에 해당하는 이미지 정보 조회
			List<AccommodationsFileVo> accommodationsFiles = accommodationFileService.findAllAccommodations(accommodation.getAccommodationId());
			 accommodation.setAccommodationsFiles(accommodationsFiles);
			 log.info("accommodationsFiles: {}", accommodationsFiles); // 출력가능
		}
		
		return accommodations;
	}
	
	
	public List<AccommodationsVo> findRecentAccommodations(){
		return accommodationsMapper.findRecentAccommodations();
	}

}
