package com.various_functions.admin.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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

	//private final String uploadPath = "/Users/jeongsujin/upload/"; // 파일이 저장된 경로
	private final String uploadPath = "C:\\Users\\NCIN\\single-upload-files"; // 파일이 저장된 경로
	
	private final AccommodationFileService accommodationFileService;
	private final FileUtils fileUtils;

	@GetMapping("/admin/accommodation{accommodationId}/files/{afId}")
	public List<AccommodationsFileVo> findAllAdminFileByAccommodationId(@PathVariable final Long accommodationId,@PathVariable final Long afId){
		log.info("숙소등록 파일 메서드 진입!!");
		return accommodationFileService.findAllAccommodations(accommodationId,afId);
	}
	
	
	@GetMapping("/admin/accommodation/{accommodationId}/files/{afId}/list")
	public ResponseEntity<Resource> AdminviewFile(@PathVariable final Long accommodationId, @PathVariable final Long afId){
		
		log.info("ResponseEntity<Resource> AdminviewFile 메서드 진입!!!!");
		//파일서비스에서 파일 정보가져오기
		AccommodationsFileVo file = (AccommodationsFileVo) accommodationFileService.findFileById(afId);
		
		if (file == null) {
	        // 파일을 찾지 못한 경우에 대한 예외 처리
	        throw new RuntimeException("File not found with ID: " + afId);
	    }
		log.info("파일서비스에서 파일 정보가져오기 : {}", file);
		
		// 파일 데이터를 읽어와서 Resource로 변환
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
