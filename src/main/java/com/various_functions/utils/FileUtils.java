package com.various_functions.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.various_functions.admin.dto.NoticeFileDto;

@Component
public class FileUtils {

	private final String uploadPath = Paths.get("C:", "develop", "upload-files").toString();
	
	/* 다중 파일 업로드 */
	public List<NoticeFileDto> uploadFiles(final List<MultipartFile> multipartFiles){
		
		List<NoticeFileDto> files = new ArrayList<>();
		for(MultipartFile multipartFile : multipartFiles) {
			if(multipartFile.isEmpty()) {
				continue;
			}
			files.add(uploadFile(multipartFile));
		}
		return files;
	}
	
	/* 단일 파일 업로드 */
	public NoticeFileDto uploadFile(final MultipartFile multipartFile) {
		
		if(multipartFile.isEmpty()) {
			return null;
		}
		
		String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
		String uploadPath = getUploadPath(today) + File.separator + saveName;
		File uploadFile = new File(uploadPath);
		
		try {
			multipartFile.transferTo(uploadFile);
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		return NoticeFileDto.builder()
				.originalName(multipartFile.getOriginalFilename())
				.saveName(saveName)
				.size((int) multipartFile.getSize())
				.build();
	}
	
	
	// 저장파일명 생성
	private String generateSaveFilename(final String filename) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String extension = StringUtils.getFilenameExtension(filename);
		return uuid + "." + extension;
	}
	
	// 업로드 경로 반환
	private String getUploadPath(String today) {
		return makeDirectories(uploadPath);
	}
	
	// 업로드 반환 폴더 생성
	private String makeDirectories(final String path) {
		File dir = new File(path);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		return dir.getPath();
	}
	

}
