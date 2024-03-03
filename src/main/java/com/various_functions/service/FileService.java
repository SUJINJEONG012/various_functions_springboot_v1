package com.various_functions.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	@Value("${file.upload.path}")
    private String uploadPath; // 프로퍼티 파일에 설정된 파일 업로드 경로

	public void saveFile(MultipartFile file) throws IOException {
        // 파일 저장 경로 설정
        Path filePath = Paths.get(uploadPath + File.separator + file.getOriginalFilename());

        // 파일 저장
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }
	
}
