package com.various_functions.admin.service;

import java.util.List;

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
	public void saveAccommodationAndRoomInfo(AccommodationAndRoomInfoDto dto) throws Exception {		
		// 1. 숙소 정보 저장
		accommodationsMapper.saveAccommodation(dto.getAccommodationDto());
		
		// 2. 저장된 숙소 정보의 id 값을 가져옴
		Long accommodationId = dto.getAccommodationDto().getAid();	
		// 3. 가져온 숙소 ID를 객실정보에 설정
		dto.getRoomInfoDto().setAccommodationId(accommodationId);
		// 4. 객실정보 저장
		roomInfoMapper.saveRoomInfo(dto.getRoomInfoDto());
	}
	
	//파일저장 메서드
	public String saveImage(MultipartFile file) {
		try {
            // 이미지 파일 저장 로직 구현
            // 저장된 파일의 경로를 반환
            String savedFilePath = "/path/to/saved/file.jpg";
            return savedFilePath;
        } catch (Exception e) {
            // 파일 저장 실패 시 예외 처리
            e.printStackTrace();
            return null;
        }
	}
	

	public List<AccommodationsVo> getAllAwccommodations() {
		return accommodationsMapper.getAllAccommodations();
	}

	
}
