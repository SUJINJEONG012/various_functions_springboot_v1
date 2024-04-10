package com.various_functions.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.various_functions.admin.dto.AccommodationsDto;
import com.various_functions.admin.dto.AccommodationsFileDto;
import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.service.AccommodationFileService;
import com.various_functions.admin.service.AccommodationService;
import com.various_functions.admin.service.RoomInfoService;
import com.various_functions.admin.vo.AccommodationsFileVo;
import com.various_functions.admin.vo.AccommodationsVo;
import com.various_functions.utils.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccommodationController {

	private final AccommodationService accommodationService;
	private final AccommodationFileService accommodationFileService;
	private final RoomInfoService roomInfoService;
	private final FileUtils fileUtils;

	
	@GetMapping("/admin/accommodation/write")
	public String accommodationSave(Model model) {
		log.info("로그 메시지");
		return "/admin/accommodation/write";
	}

	@PostMapping("/admin/accommodation/save")
	public ResponseEntity<String> saveAccommodationAndRoomInfo(@ModelAttribute AccommodationsDto accommodationsDto,
	        @ModelAttribute RoomInfoDto roomInfoDto) {

	    Long accommodationId = accommodationService.insertAccommodationAndRoomInfo(accommodationsDto);
	    log.info("Received AccommodationsDto: {}", accommodationsDto);

	    List<MultipartFile> files = accommodationsDto.getFiles(); // 이 부분을 여기로 이동

	    if (accommodationId != null) {
	        // 객실정보를 저장하는 메서드 호출
	        log.info("accommodationId 이 아라는거 로그 출력");
	        roomInfoDto.setAccommodationId(accommodationId);
	        roomInfoService.insertRoomInfo(accommodationId, roomInfoDto);

	        if (files != null && !files.isEmpty()) {
	            List<AccommodationsFileDto> fileList = fileUtils.uploadFileAccommodations(files);
	            accommodationFileService.saveFiles(accommodationId, fileList); // saveFile 메서드를 사용하여 단일 파일을 저장
	        }
	        

	        return ResponseEntity.ok("숙소정보가 성공적으로 저장되었습니다.");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("숙소 정보 저장에 실패했습니다.");
	    }
	}
	
	
	@GetMapping("/admin/accommodation/list")
	public String AdminAccommodationsList(Model model) {
		return accommodationsList(model, "admin/accommodation/list");
	}

	@GetMapping("/accommodation/list")
	public String UserAccommodationsList( Model model) {
		return accommodationsList(model, "accommodation/list");
	}
	
	 
	// 숙소 조회 리스트
	public String accommodationsList( Model model, String viewName) {
		log.info("숙소 리스트 페이지 진입!!!");
		//숙소정보가져오기
		List<AccommodationsVo> accommodations = accommodationService.findAllAccommodations();
		
		// 각 숙소별 파일 리스트 가져오기
	    Map<Long, List<AccommodationsFileVo>> filesMap = new HashMap<>();
	    for (AccommodationsVo accommodation : accommodations) {
	        List<AccommodationsFileVo> files = accommodationFileService.findFileByAccommodationId(accommodation.getAccommodationId());
	        filesMap.put(accommodation.getAccommodationId(), files);
	    }
	    
		// 모델에 데이터 추가
		model.addAttribute("accommodations", accommodations);
		model.addAttribute("filesMap", filesMap);
		
		return viewName;
	}
	

}
