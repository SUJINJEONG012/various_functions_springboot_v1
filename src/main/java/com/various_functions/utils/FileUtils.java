package com.various_functions.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.various_functions.admin.dto.NoticeFileDto;

@Component
public class FileUtils {
	
	 public ResponseEntity<Resource> loadFileAsResource(String fileName) {
	        try {
	            // 파일을 읽어올 경로를 지정합니다. 여기서는 로컬 파일 시스템에서 파일을 읽어옵니다.
	            File file = new File(fileName);

	            // 파일을 읽어서 바이트 배열로 변환합니다.
	            byte[] fileContent = readBytesFromFile(file);

	            // ByteArrayResource를 사용하여 바이트 배열을 Resource로 변환합니다.
	            Resource resource = new ByteArrayResource(fileContent);

	            // HTTP 응답 헤더를 설정합니다.
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.IMAGE_JPEG); // 이미지 타입에 따라 변경합니다.
	            headers.setContentLength(fileContent.length);
	            headers.setContentDispositionFormData("attachment", file.getName()); // 파일 다운로드 헤더 설정
	            // ResponseEntity를 사용하여 HTTP 응답을 생성합니다.
	            return ResponseEntity.ok()
	                    .headers(headers)
	                    .body(resource);
	        } catch (IOException e) {
	            // 파일을 읽어오는 도중 오류가 발생하면 에러 응답을 반환합니다.
	            return ResponseEntity.notFound().build();
	        }
	    }


    // 파일을 읽어서 바이트 배열로 변환하는 메서드입니다.
    private byte[] readBytesFromFile(File file) throws IOException {
        try (InputStream is = new FileInputStream(file)) {
            return StreamUtils.copyToByteArray(is);
        }
    }
	//private final String uploadPath = Paths.get("C:", "develop", "upload-files").toString();
	//private final String uploadPath = Paths.get("/","Users", "jeongsujin", "upload").toString();
	
	//private final String uploadPath = Paths.get("src", "main", "resources", "static", "images").toString();
	private final String uploadPath = "/Users/jeongsujin/upload/";
	
	/**
     * 다중 파일 업로드
     * @param multipartFiles - 파일 객체 List
     * @return DB에 저장할 파일 정보 List
     */
    public List<NoticeFileDto> uploadFiles(final List<MultipartFile> multipartFiles) {
        
    	// 여러 이미지를 담는 객체
    	List<NoticeFileDto> filesff = new ArrayList<>();
        
        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            
            filesff.add(uploadFile(multipartFile));
        }
        return filesff;
    }
    
   

    /**
     * 단일 파일 업로드
     * @param multipartFile - 파일 객체
     * @return DB에 저장할 파일 정보
     */
    public NoticeFileDto uploadFile(final MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
        String uploadPath = getUploadPath(today) + File.separator + saveName; // 변경된 부분
        File uploadFile = new File(uploadPath);

        try {
            multipartFile.transferTo(uploadFile);
            // 썸네일 부분 추가하기.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return NoticeFileDto.builder()
                .originalName(multipartFile.getOriginalFilename())
                .saveName(saveName)
                .size((int) multipartFile.getSize())
                .build();
    }

    /**
     * 저장 파일명 생성
     * @param filename 원본 파일명
     * @return 디스크에 저장할 파일명
     */
    public String generateSaveFilename(final String filename) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(filename);
        return uuid + "." + extension;
    }

    /**
     * 업로드 경로 반환
     * @return 업로드 경로
     */
    public String getUploadPath() {
        return makeDirectories(uploadPath);
    }

    /**
     * 업로드 경로 반환
     * @param addPath - 추가 경로
     * @return 업로드 경로
     */
    public String getUploadPath(final String addPath) {
        return makeDirectories(uploadPath + File.separator + addPath);
    }


    /**
     * 업로드 폴더(디렉터리) 생성
     * @param path - 업로드 경로
     * @return 업로드 경로
     * 디렉토리가 존재하는지 유무를반환하는 exists()메서드를 활용하여 if문 작성
     */
    private String makeDirectories(final String path) {
        File dir = new File(path);
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        return dir.getPath();
    }

}
