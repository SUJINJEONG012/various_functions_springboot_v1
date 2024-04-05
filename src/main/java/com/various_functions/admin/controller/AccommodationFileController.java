package com.various_functions.admin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.admin.service.AccommodationFileService;
import com.various_functions.admin.vo.AccommodationsVo;
import com.various_functions.utils.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccommodationFileController {

	
	private final String uploadPath = "/Users/jeongsujin/upload/"; // 파일이 저장된 경로
	private final AccommodationFileService accommodationFileService;
	private final FileUtils fileUtils;
	

	// 파일리스트 조회
	@GetMapping("/admin/accommodation/{accomodationId}/files")
	public List<AccommodationsVo> findAllAdminFileByAccommodationId(){
		log.info("숙소등록파일 컨트롤러");
		return accommodationFileService.findFilesByAccommodationId(accommodationId);
	}
	
}
