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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ResponseEntity<String> saveAccommodationAndRoomInfo(
			@ModelAttribute AccommodationsDto accommodationsDto,
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
		log.info("accommodations : ", accommodations);
		
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
	
	
	@GetMapping("/admin/accommodation/view")
	private String AdminAccommodationView(@RequestParam Long accommodationId, Model model) {
		return AccommodationView(accommodationId, model, "/admin/accommodation/view");
	}
	
	
	@GetMapping("/accommodation/view")
	private String userAccommodationView(@RequestParam Long accommodationId, Model model) {
		return AccommodationView(accommodationId, model, "/accommodation/view");
	}
	
	// 숙소 상세보기
	public String AccommodationView(@RequestParam final Long accommodationId, Model model, String viewName) {
		
		log.info("숙소 상세보기 진입!!!");
		
		AccommodationsVo accommodation = accommodationService.findById(accommodationId);
				
		// 파일정보가져오기
		List<AccommodationsFileVo> files = accommodationFileService.findFileByAccommodationId(accommodationId);
		log.info("숙소정보상세보기에 가져오는 데이터 확인 : {} ", files);
		
		
		model.addAttribute("accommodation",accommodation);
		model.addAttribute("files",files);

		return viewName;
	}
	
	// 숙소등록 수정
	@GetMapping("/admin/accommodation/update/{accommodationId}")
	public String showUpdateForm(@PathVariable Long accommodationId, Model model) {
		log.info("숙소 수정페이지 진입!");
		AccommodationsVo accommodationVo = accommodationService.findById(accommodationId);
		
		log.info("accommodationVo : " + accommodationVo);
		//파일정보가져오기
		List<AccommodationsFileVo> files = accommodationFileService.findFileByAccommodationId(accommodationId);
		log.info("숙소정보에수정하기 가져오는 데이터 확인 : ", files);
		
		model.addAttribute("accommodation", accommodationVo);
		model.addAttribute("files", files);
		
		return "/admin/accommodation/update";
	}
	
	// 숙소 수정
	@PostMapping("/admin/accommodation/update/{accommodationId}")
	public ResponseEntity<Map<String, Object>> updateAccommodation(@PathVariable Long accommodationId, 
			@ModelAttribute AccommodationsDto accommodationsDto,
			@ModelAttribute RoomInfoDto roomInfoDto,
			@RequestParam(value="filesToDelete", required = false) List<Long> filesToDelete, RedirectAttributes redirectAttributes){
		log.info("숙소 수정 진입 메서드 ");
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			//파일삭제
			if(filesToDelete != null && !filesToDelete.isEmpty()) {
				log.info("파일삭제하는 if문 진입 :" + filesToDelete);
				accommodationFileService.deleteFiles(filesToDelete);
			}
			//파일업로드
			List<MultipartFile> accommodationFiles = accommodationsDto.getFiles();
			if(accommodationFiles != null && !accommodationFiles.isEmpty()) {
				List<AccommodationsFileDto> fileList = fileUtils.uploadFileAccommodations(accommodationFiles);
				accommodationFileService.saveFiles(accommodationId, fileList);
			}
			
		    if (accommodationId != null) {
		        // 객실정보를 저장하는 메서드 호출
		    	roomInfoDto.setAccommodationId(accommodationId);
		        roomInfoService.updateRoomInfo(accommodationId, roomInfoDto); 
		    } 
		    
			// 게시물 수정
			accommodationsDto.setAccommodationId(accommodationId);
			accommodationService.updateAccommodation(accommodationsDto);
			
			// 성공응답
			response.put("success", true);
			response.put("message", "수정이 완료되었습니다.");
			return ResponseEntity.ok(response);
			
		}catch(Exception e) {
			// 실패응답
			response.put("success", false);
			response.put("message", "수정 중 오류가 발생했습니다." + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
	}
	

	   
	
	
	
	

}
