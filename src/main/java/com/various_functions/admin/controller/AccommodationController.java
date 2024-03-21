package com.various_functions.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.various_functions.admin.dto.AccommodationAndRoomInfoDto;
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

	@Autowired
	private AccommodationService accommodationService;
	@Autowired
	private RoomInfoService roomInfoService;

	
	@GetMapping("/accommodation/write")
	public String accommodationSave(Model model) {
		 log.info("로그 메시지");
		return "/admin/accommodation/write";
	}

	@PostMapping("/accommodation/save")
    public ResponseEntity<String> saveAccommodationAndRoomInfo(AccommodationAndRoomInfoDto accommodationAndRoomInfoDto)  {
		log.info("@@@@@@@@@@ 컨트롤러 저장 @");
		
		accommodationService.saveAccommodationAndRoomInfo(accommodationAndRoomInfoDto);
	
		return ResponseEntity.ok("숙소정보와 객실정보가 성공적으로 저장!");
		
		
    }

}
