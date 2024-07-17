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
import com.various_functions.admin.vo.NoticeFileVo;
import com.various_functions.utils.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccommodationFileController {

	private final String uploadPath = "/Users/jeongsujin/upload/"; // 파일이 저장된 경로
	//private final String uploadPath = "C:\\Users\\NCIN\\single-upload-files"; // 파일이 저장된 경로
	
	private final AccommodationFileService accommodationFileService;
	private final FileUtils fileUtils;

	@GetMapping("/admin/accommodation/{accommodationId}/files")
	public List<AccommodationsFileVo> findAllAdminFileByAccommodationId(@PathVariable final Long accommodationId){
		// 숙소와 파일 정보를 함께 조회하는 서비스 메서드 호출
		log.info("숙소와 파일 정보를 함께 조회하는 서비스 메서드 호출!!");
		return accommodationFileService.findFileByAccommodationId(accommodationId);
	}
	
	
	@GetMapping("/admin/accommodation/{accommodationId}/files/{afId}/list")
	public ResponseEntity<Resource> AdminListFile(@PathVariable final Long accommodationId, @PathVariable final Long afId){
		
		log.info("ResponseEntity<Resource> AdminviewFile 메서드 진입!!!!");
		
		//파일서비스에서 파일 정보가져오기
		AccommodationsFileVo file = accommodationFileService.findFileById(afId);
		log.info("파일서비스에서 파일 정보가져오기 file : {}", file);
		
		/*
		 * 파일 데이터를 읽어와서 Resource로 변환
		 * */
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
	
	@GetMapping("/accommodation/{accommodationId}/files/{afId}/list")
	public ResponseEntity<Resource> UserListFile(@PathVariable final Long accommodationId, @PathVariable final Long afId){
		
		log.info("ResponseEntity<Resource> AdminviewFile 메서드 진입!!!!");
		
		//파일서비스에서 파일 정보가져오기
		AccommodationsFileVo file = accommodationFileService.findFileById(afId);
		log.info("파일서비스에서 파일 정보가져오기 file : {}", file);
		
		/*
		 * 파일 데이터를 읽어와서 Resource로 변환
		 * */
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
	@GetMapping("/{accommodationId}/files/{afId}/list")
	public ResponseEntity<Resource> MainListFile(@PathVariable final Long accommodationId, @PathVariable final Long afId){
		
		log.info("ResponseEntity<Resource> AdminviewFile 메서드 진입!!!!");
		
		//파일서비스에서 파일 정보가져오기
		AccommodationsFileVo file = accommodationFileService.findFileById(afId);
		log.info("파일서비스에서 파일 정보가져오기 file : {}", file);
		
		/*
		 * 파일 데이터를 읽어와서 Resource로 변환
		 * */
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
	
	
	 @GetMapping({"/admin/accommodation/{accommodationId}/files/{afId}/download", "/accommodation/{accommodationId}/files/{afId}/download"})
	    public ResponseEntity<Resource> downloadFile(@PathVariable final Long accommodationId, @PathVariable final Long afId) {
		 	AccommodationsFileVo file = accommodationFileService.findFileById(afId);
	       
	        //fileUtils에서 가져오는걸 넣음
	        Resource resource = fileUtils.readFileAsResource(file);
	        try {
	            String filename = URLEncoder.encode(file.getOriginalName(), "UTF-8");
	            return ResponseEntity.ok()
	                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
	                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
	                    .body(resource);

	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("filename encoding failed : " + file.getOriginalName());
	        }
	    }

	
	
	@GetMapping("/{accommodationId}/files/{afId}/view")
	public ResponseEntity<Resource> UserViewListFile(@PathVariable final Long accommodationId, @PathVariable final Long afId){
		
		log.info("ResponseEntity<Resource> AdminviewFile 메서드 진입!!!!");
		
		//파일서비스에서 파일 정보가져오기
		AccommodationsFileVo file = accommodationFileService.findFileById(afId);
		log.info("파일서비스에서 파일 정보가져오기 file : {}", file);
		
		/*
		 * 파일 데이터를 읽어와서 Resource로 변환
		 * */
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
