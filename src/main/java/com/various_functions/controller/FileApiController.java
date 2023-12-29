package com.various_functions.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.domain.FileEntity;
import com.various_functions.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileApiController {

	private final FileService fileService;
	
	// 파일 리스트 조회
	@GetMapping("/posts/{postId}/files")
	public List<FileEntity> findAllFileByPostId(@PathVariable final Long postId){
		return fileService.findAllFileByPostId(postId);
	}
}
