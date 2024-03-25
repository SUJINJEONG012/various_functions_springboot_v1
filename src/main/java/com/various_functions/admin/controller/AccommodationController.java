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
import com.various_functions.admin.vo.AccommodationsVo;

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
	public ResponseEntity<String> saveAccommodationAndRoomInfo(@ModelAttribute AccommodationsDto accommodationsDto, 
			@ModelAttribute RoomInfoDto roomInfoDto) {

		Long accommodationId = accommodationService.insertAccommodationAndRoomInfo(accommodationsDto);
		log.info("Received AccommodationsDto: {}", accommodationsDto);


		if (accommodationId != null) {
			// 객실정보를 저장하는 메서드 호출
			log.info("accommodationId 이 아라는거 로그 출력");
			roomInfoDto.setAccommodationId(accommodationId);
			roomInfoService.insertRoomInfo(accommodationId, roomInfoDto);
			return ResponseEntity.ok("숙소정보가 성공적으로 저장되었습니다.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("숙소 정보 저장에 실패했습니다.");
		}

	}

	
	// 숙소 조회 리스트
	@GetMapping("/accommodation/list")
	private String accommodationsList(Model model) {
		log.info("숙소 리스트 페이지 진입!!!");
	    List<AccommodationsVo> accommodations = null;
	    try {
	        accommodations = accommodationService.findAllAccommodations();
	        log.info("Retrieved {} accommodations in the controller", accommodations.size());
	    } catch (Exception e) {
	        log.error("Failed to retrieve accommodations in the controller: {}", e.getMessage());
	        // 예외 처리: 실패 시 메시지를 모델에 추가하거나 다른 처리를 수행할 수 있음
	        model.addAttribute("error", "Failed to retrieve accommodations");
	    }
	    model.addAttribute("accommodations", accommodations);   
	    return "admin/accommodation/list";
	}

}
