package com.various_functions.admin.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.service.AccommodationService;
import com.various_functions.admin.service.RoomInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AccommodationController {

	private final AccommodationService accommodationService;
	private final RoomInfoService roomInfoService;
	
	@GetMapping("/accommodation/write")
	public String accommodationSave(Model model) {
		 log.info("로그 메시지");
		return "/admin/accommodation/write";
	}

	@PostMapping("/accommodation/save")
    public ResponseEntity<String> saveAccommodationAndRoomInfo(
    		@ModelAttribute AccommodationsDto accommodationsDto) {
		
		Long accommodationId = accommodationService.insertAccommodationAndRoomInfo(accommodationsDto);
		log.info("Received AccommodationsDto: {}", accommodationsDto);
		
		if(accommodationId != null) {
			List<RoomInfoDto> roomInfoList = accommodationsDto.getRoomInfoList();
			if(roomInfoList != null) {
				for(RoomInfoDto roomInfo : roomInfoList) {
					roomInfo.setAccommodationId(accommodationId);
					roomInfoService.insertRoomInfo(accommodationId, roomInfo);
				}
			}
			return ResponseEntity.ok("숙소정보가 성공적으로 저장되었습니다.");
		} else {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("숙소 정보 저장에 실패했습니다.");
	    }
        
    }
	
	

}
