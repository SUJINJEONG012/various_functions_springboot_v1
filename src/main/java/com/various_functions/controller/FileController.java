//package com.various_functions.controller;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//public class FileController {
//
//	@Value("${file.upload.path}")
//    private String uploadPath;
//
//	
//	@PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            // 파일 업로드 서비스를 사용하여 파일 저장
//            String fileName = saveFile(file);
//            return ResponseEntity.ok("File uploaded successfully. File name: " + fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body("Failed to upload file.");
//        }
//    }
//	
//	
//	private String saveFile(MultipartFile file) throws IOException {
//        // 파일 이름 생성 (UUID 사용)
//        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//
//        // 파일 저장 경로 설정
//        Path filePath = Paths.get(uploadPath + File.separator + fileName);
//
//        // 파일 저장
//        Files.copy(file.getInputStream(), filePath);
//
//        return fileName; // 저장된 파일 이름 반환
//    }
//	
//	
//}
