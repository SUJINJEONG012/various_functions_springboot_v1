package com.various_functions.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	private final AccommodationService accommodationService;
	
	@GetMapping("/accommodation/write")
	public String accommodationSave(Model model) {
		 log.info("로그 메시지");
		return "/admin/accommodation/write";
	}

	@PostMapping("/accommodation/save")
    public ResponseEntity<String> saveAccommodationAndRoomInfo(@ModelAttribute AccommodationAndRoomInfoDto accommodationAndRoomInfoDto, Model model)  {
		log.info("@@@@@@@@@@ 컨트롤러 저장 @");
		
		if (accommodationAndRoomInfoDto == null) {
	        log.error("AccommodationAndRoomInfoDto is null");
	        return ResponseEntity.badRequest().body("AccommodationAndRoomInfoDto is null");
	    }

		try {
			log.info("Before calling accommodationService.insertAccommoda");
            accommodationService.insertAccommodation(accommodationAndRoomInfoDto);
            log.info("@@@@@@@@@@ 서비스부분저장  @");
            return ResponseEntity.ok("Accommodation and RoomInfo added successfully");
        } catch (IllegalArgumentException e) {
        	log.info("@@@@@@@@@@ 서비스부분저장 2 @");
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        } catch (Exception e) {
        	log.info("@@@@@@@@@@ 서비스부분저장3  @");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add Accommodation and RoomInfo");
        }
		 
		
    }

}
