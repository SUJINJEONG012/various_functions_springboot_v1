package com.various_functions.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.admin.service.NoticeFileService;
import com.various_functions.admin.vo.NoticeFileVo;
import com.various_functions.utils.FileUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileApiController {

	private final FileUtils fileUtils;
	private final NoticeFileService noticeFileService;
	
	//파일리스트 조회
//	@GetMapping("/admin/notice/{noticeId}/files")
//	public List<NoticeFileVo> findAllFileByNoticeId(@PathVariable final Long noticeId){
//		return noticeFileService.findFilesByNoticeId(noticeId);
//	}
	
	@GetMapping("/admin/notice/{noticeId}/files")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
        // 파일이 저장된 경로
        Path filePath = Paths.get(fileUtils.getUploadPath(), filename);
        // 파일을 ByteArrayResource로 읽기
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(filePath));

        // 다운로드할 파일 이름 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
	
	@GetMapping("/admin/notice/{noticeId}/files/{filename:.+}/view")
	public ResponseEntity<Resource> viewImage(@PathVariable String filename) throws IOException {
	    // 이미지가 저장된 경로
	    Path imagePath = Paths.get(fileUtils.getUploadPath(), filename);
	    
	    // 이미지를 ByteArrayResource로 읽기
	    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(imagePath));

	    
	    return ResponseEntity.ok()
	            .contentType(MediaType.IMAGE_JPEG) // 이미지 타입 지정
	            .body(resource);
	}

	
}
