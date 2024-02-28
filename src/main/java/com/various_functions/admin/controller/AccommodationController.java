package com.various_functions.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.various_functions.admin.dto.AccommodationAndRoomInfoDto;
import com.various_functions.admin.service.AccommodationService;
import com.various_functions.admin.vo.AccommodationsVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AccommodationController {
	
	@Autowired
	private AccommodationService accommodationService;
	
	@GetMapping("/accommodation/write")
	public String accommodationSave(Model model) {	
		return "/admin/accommodation/write";
	}
	
	@PostMapping("/accommodation/save")
    public ResponseEntity<String> saveAccommodationAndRoom(
    		@RequestBody AccommodationAndRoomInfoDto accommodationAndRoomInfoDto,  
    		@RequestParam("riextraimg1") MultipartFile riextraimg1, 
    		@RequestParam("riextraimg2") MultipartFile riextraimg2)  {
		
		try{
			// JSON 문자열을 DTO로 객체로 변환
			ObjectMapper objectMapper = new ObjectMapper();
			AccommodationAndRoomInfoDto dto = objectMapper.readValue(accommodationAndRoomInfoDto, AccommodationAndRoomInfoDto.class); 
		
			// 이미지 파일 저장 및 파일 경로 반환
	        String img1Url = saveImage(riextraimg1);
	        String img2Url = saveImage(riextraimg2);
			
	        dto.setExtraImage1Url(img1Url);
	        dto.setExtraImage2Url(img2Url);
			
			accommodationService.saveAccommodationAndRoomInfo(dto);
			
			// 성공읍답
			return ResponseEntity.ok("저장 성공");
		}catch(Exception e) {
			log.error("예외발생 :", e); // 예외 스택 트레이스 출력
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("저장 중 오류!");
		}	
		
    }
	
	private void saveFile(MultipartFile file) {
		//파일저장 로직 구현
	}


	
	@GetMapping("/accommodation/list")	
	public String accommodationList(Model model) {
		//숙소리스트 모델에 추가
		List<AccommodationsVo> accommodations = accommodationService.getAllAwccommodations();
		model.addAttribute("accommodations",accommodations);
		
        // 해당하는 뷰 페이지의 이름을 반환
        return "admin/accommodation/list";
	}
	
	
	
}
