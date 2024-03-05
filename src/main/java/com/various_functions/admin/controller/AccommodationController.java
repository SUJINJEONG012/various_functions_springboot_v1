package com.various_functions.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.various_functions.admin.dto.AccommodationAndRoomInfoDto;
import com.various_functions.admin.service.AccommodationService;
import com.various_functions.admin.vo.AccommodationsVo;
import com.various_functions.service.FileService;

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
	private FileService fileService;

	@GetMapping("/accommodation/write")
	public String accommodationSave(Model model) {
		return "/admin/accommodation/write";
	}

//	@PostMapping("/accommodation/save")
//	public ResponseEntity<String> saveAccommodation(@RequestParam("file") MultipartFile file,
//			@RequestParam("dto") String dtoJson) {
//		
//		try {
//           // 파일 업로드 서비스를 사용하여 파일 저장
//			String fileName = file != null ? file.getOriginalFilename() : null;
//
//			// 숙소 정보 저장
//			AccommodationAndRoomInfoDto dto = objectMapper.readValue(dtoJson, AccommodationAndRoomInfoDto.class);
//			dto.getAccommodationDto().setAmainimg(fileName);
//			accommodationService.saveAccommodationAndRoomInfo(dto, file);
//
//			return ResponseEntity.ok("Accommodation saved successfully.");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.badRequest().body("Failed to save accommodation.");
//		}
//		
//	}

	@GetMapping("/accommodation/list")
	public String accommodationList(Model model) {
		// 숙소리스트 모델에 추가
		List<AccommodationsVo> accommodations = accommodationService.getAllAwccommodations();
		model.addAttribute("accommodations", accommodations);

		// 해당하는 뷰 페이지의 이름을 반환
		return "admin/accommodation/list";
	}

}
