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
