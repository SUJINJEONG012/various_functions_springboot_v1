package com.various_functions.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
		String filePath = uploadDir + "/" + StringUtils.cleanPath(file.getOriginalFilename());
		// 파일을 저장할 경로에 파일을 저장
		Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
	}
	
}
