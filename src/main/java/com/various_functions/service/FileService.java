package com.various_functions.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	/*
	 * application.properties 에 설정해도 됨.	 
	 * file.upload.dir=/path/to/upload/directory
	*/
	@Value("${file.upload.dir}")
	private String uploadDir;
	
	public void saveFile(MultipartFile file) throws IOException {
		// 파일 저장 경로 설정
		String filePath = uploadDir
	}
	
}
