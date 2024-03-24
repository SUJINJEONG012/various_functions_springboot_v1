package com.various_functions.admin.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	@ResponseBody
    public ResponseEntity<String> saveAccommodationAndRoomInfo(
    		@ModelAttribute AccommodationsDto accommodationsDto,
    		@ModelAttribute RoomInfoDto roomInfoDto) {
       
		log.info("Received AccommodationsDto: {}", accommodationsDto);
		log.info("Received RoomInfoDto: {}", roomInfoDto);
		// accommodationsDto에서 숙소 아이디를 가져와서 roomInfoDto에 설정
	    Long accommodationId = accommodationsDto.getAccommodationId();
	    log.info("accommodationId : {}",accommodationsDto.getAccommodationId());

	    if(accommodationId != null) {
	    	return ResponseEntity.status(HttpStatus.CREATED).body("accommodation saved successfully with ID: " + accommodationId);
	    }else {
	    	return ResponseEntity.badRequest().body("Failed to save accommodation");
	    }
	    
//	    roomInfoDto.setAccommodationId(accommodationId);
//		log.info("roomInfoDto.setAid(aid): {}", accommodationId);
	
//		//숙소정보와 객실정보 저장 
//		Long saveId = accommodationService.insertAccommodationAndRoomInfo(accommodationsDto,roomInfoDto);
//		
//		if(saveId != null) {
//			return ResponseEntity.status(HttpStatus.CREATED).body("Accommodation and Room inf saved successfully");
//		}else {
//			return ResponseEntity.badRequest().body("Failed");
//		}
		
		
        
    }
	
	

}
