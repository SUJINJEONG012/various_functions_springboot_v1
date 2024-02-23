package com.various_functions.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

	// 파일업로드 관련 컨트롤러
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		if(file.isEmpty()) {
			return ResponseEntity.badRequest().body("Please select a file to upload");
		}
		
		try {
			// 파일 저장 경로 설정
			String filePath = uploadDir + File.separator + file.getOriginalFilename();
			File dest = new File(filePath);
			
			// 파일 저장
			file.transferTo(dest);
			return ResponseEntity.ok().body("File upload succeessfully.");
		}catch(IOException  e) {	
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Failed to upload file.");
		}
	}
}
