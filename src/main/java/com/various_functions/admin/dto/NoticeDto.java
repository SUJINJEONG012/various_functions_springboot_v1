package com.various_functions.admin.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
/*
 * 게시글 요청 클래스
 * */
@Getter
@Setter
public class NoticeDto {
	
	//게시판에 글을 작성할 때 입력하는 필드는 제목,내용,작성자,공지글 여부
	private Long id;
	private String title;
	private String content;
	private String writer;
	private Boolean noticeYn;
	
	//파일추가
	private List<MultipartFile> files = new ArrayList<>();
	private List<Long> removeFileIds = new ArrayList<>();
	
}
