package com.various_functions.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * 페이징 처리, 검색기능
 * */

@Getter
@Setter
public class PagedSearchDto {

	private int page; // 현재 페이지 번호
	private int recordSize; // 페이지당 출력할 데이터 개수
	private int pageSize; // 화면 하단에 출력할 페이지 사이즈
	private String keyword; // 검색키워드
	private String searchType; // 검색유형
	
}
