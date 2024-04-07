package com.various_functions.admin.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.admin.service.AccommodationFileService;
import com.various_functions.admin.vo.AccommodationsFileVo;
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

	@GetMapping("/admin/accommodation/{accommodationId}/files")
	public List<AccommodationsFileVo> findAllAdminFileByAccommodationId(@PathVariable final Long accommodationId){
		log.info("숙소등록 파일 메서드 진입!!");
		return accommodationFileService.findAllAccommodations(accommodationId);
	}
	
	
	@GetMapping("/admin/accommodation/{accommodationId}/files/{afId}/view")
	public ResponseEntity<Resource> AdminviewFile(@PathVariable final Long accommodationId, @PathVariable final Long afId){
		
		AccommodationsFileVo file = accommodationFileService.findFileById(afId);
		Resource resource = fileUtils.readFileAsResource(file);
		try {
			String filename = URLEncoder.encode(file.getOriginalName(), "UTF-8");			
					return ResponseEntity.ok()
		                    .contentType(MediaType.IMAGE_JPEG) // 이미지 파일인 경우
		                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\";")
		                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
		                    .body(resource);
					
		}catch(UnsupportedEncodingException e) {
			throw new RuntimeException("filename encoding failed : " + file.getOriginalName());
		}
	
	}
	
	

}
