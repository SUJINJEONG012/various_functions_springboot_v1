//package com.various_functions.controller;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.List;
//
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.various_functions.dto.FileUtils;
//import com.various_functions.service.FileService;
//import com.various_functions.vo.FileEntity;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequiredArgsConstructor
//public class FileApiController {
//
//	private final FileService fileService;
//	private final FileUtils fileUtils;
//	
//	// 파일 리스트 조회
//	@GetMapping("/posts/{postId}/files")
//	public List<FileEntity> findAllFileByPostId(@PathVariable final Long postId){
//		return fileService.findAllFileByPostId(postId);
//	}
//	
//	// 첨부파일 다운로드 
//	@GetMapping("/posts/{postId}/files/{fileId}/download")
//	public ResponseEntity<Resource> downloadFile(@PathVariable final Long postId, @PathVariable final Long fileId){
//		FileEntity file = fileService.findFileById(fileId);
//		Resource resource = fileUtils.readFileAsResource(file);
//		
//		try {
//			String filename = URLEncoder.encode(file.getOriginalName(), "UTF-8");
//			return ResponseEntity.ok()
//					.contentType(MediaType.APPLICATION_OCTET_STREAM)
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
//					.header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
//					.body(resource);
//		}catch(UnsupportedEncodingException e) {
//			throw new RuntimeException("filename eccoding failed : " + file.getOriginalName());
//		}
//	}
//}
