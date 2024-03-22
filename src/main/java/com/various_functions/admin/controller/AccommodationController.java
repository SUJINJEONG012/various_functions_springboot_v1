package com.various_functions.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	private final RoomInfoService roomInfoService;
	
	@GetMapping("/accommodation/write")
	public String accommodationSave(Model model) {
		 log.info("로그 메시지");
		return "/admin/accommodation/write";
	}

//	@PostMapping("/accommodation/save")
//	@ResponseBody
//    public ResponseEntity<Long> saveAccommodationAndRoomInfo(@ModelAttribute AccommodationsDto accommodationsDto) {
//       
//		Long aid = accommodationService.insertAccommodation(accommodationsDto);
//        
//		if (aid != null) {
//            return new ResponseEntity<>(aid, HttpStatus.CREATED); // 숙소 정보가 성공적으로 저장될 경우, 201 Created 상태 코드와 주키를 반환
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 요청이 잘못되었을 경우, 400 Bad Request 상태 코드 반환
//        }
//        
//    }
	
	@PostMapping("/accommodation/save")
    @ResponseBody
    public ResponseEntity<Long> saveAccommodationAndRoomInfo(@ModelAttribute AccommodationAndRoomInfoDto dto) {
        try {
            
        	Long aid = accommodationService.insertAccommodation(dto.getAccommodationsDto());
            
            if (aid != null) {
                // 객실 정보 저장
                for (RoomInfoDto roomInfoDto : dto.getRoomInfoList()) {
                    roomInfoDto.setAid(aid); // 숙소 정보의 주 키를 객실 정보에 설정
                    roomInfoService.insertRoomInfo(roomInfoDto);
                }
                return new ResponseEntity<>(aid, HttpStatus.CREATED); // 숙소 정보가 성공적으로 저장될 경우, 201 Created 상태 코드와 주키를 반환
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 숙소 정보 저장 실패 시, 400 Bad Request 상태 코드 반환
            }
        } catch (Exception e) {
            log.error("Error while saving accommodation and room information", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 서버 오류 발생 시, 500 Internal Server Error 상태 코드 반환
        }
    }

}
