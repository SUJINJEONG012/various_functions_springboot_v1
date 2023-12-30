package com.various_functions.dto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.various_functions.domain.FileEntity;

@Component
public class FileUtils {

	private final String uploadPath = Paths.get("/Users/jeongsujin/", "develop", "upload-files").toString();
	//private final String uploadPath = Paths.get("C:", "develop", "upload-files").toString();
	
	// 다중 파일 업로드
	public List<FileDto> uploadFiles(final List<MultipartFile> multipartFiles){
		
		List<FileDto> files = new ArrayList<>();
		
		for(MultipartFile multipartFile : multipartFiles) {
			if(multipartFile.isEmpty()) {
				continue;
			}
			files.add(uploadFile(multipartFile));
		}
		return files;
	}
	
	// 단일 파일 업로드
	public FileDto uploadFile(final MultipartFile multipartFile) {
		
		if(multipartFile.isEmpty()) {
			return null;
		}
		
		String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
		String uploadPath  = getUploadPath(today) + File.separator + saveName;
		File uploadFile  = new File(uploadPath);
		
		try {
			multipartFile.transferTo(uploadFile);
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		
		return FileDto.builder()
				.originalName(multipartFile.getOriginalFilename())
				.saveName(saveName)
				.size(multipartFile.getSize())
				.build();		
		
	}
	
	
	// 저장 파일명 생성
	private String generateSaveFilename(final String filename) {
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		String extension = StringUtils.getFilenameExtension(filename);
		return uuid +  "." + extension;
	}
	
	// 업로드 경로 반환
	private String getUploadPath() {
		return makeDirectories(uploadPath);
	}

	// 업로드 경로 반환 addPath - 추가 경로
	private String getUploadPath(final String addPath) {
		return makeDirectories(uploadPath + File.separator + addPath);
	}
	
	// 업로드 폴더
	private String makeDirectories(final String path) {
		File dir = new File(path);
		if(dir.exists() == false) {
			dir.mkdir();
		}
		return dir.getPath(); 
	}
	
	/* 파일삭제 from disk
	 * @Param files - 삭제할 파일 정보 List
	*/
	public void deleteFiles(final List<FileEntity> files) {
		if(CollectionUtils.isEmpty(files)) {
			return;
		}
		for(FileEntity file : files) {
			String uploadedDate = file.getCreatedDate().toLocalDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
			deleteFile(uploadedDate, file.getSaveName());
		}
	}
	
	/* 파일 삭제 
	 * @Param addPath - 추가경로
	 * @Param filename - 파일명
	 * */
	private void deleteFile(final String addPath, final String filename) {
		String filePath = Paths.get(uploadPath, addPath, filename).toString();
		deleteFile(filePath);
	}
	
	private void deleteFile(final String filePath) {
		File file = new File(filePath);
		if(file.exists()) {
			file.delete();
		}
	}
	
	

	}