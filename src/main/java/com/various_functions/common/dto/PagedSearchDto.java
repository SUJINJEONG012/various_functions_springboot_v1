package com.various_functions.common.dto;

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
	private int offset; // 데이터 조회에서 사용할 시작 오프셋

	private String keyword; // 검색키워드
	private String searchType; // 검색유형
	
	/*
	 * 객체가 생성되는 시점에 현재페이지는 번호 1
	 * 페이지당 출력할 데이터 개수와 하단에 출력할 페이지 개수 10으로 초기화
	 * */
	public PagedSearchDto() {
		this.page = 1;
		this.recordSize = 10; // 10개씩 페이지 처리
	}
	
	/* MySQL DB에서 LIMIT 구문의 시작 부분에 사용되는 메서드
	 * 현재페이지 계산하는 구문
	 */
	public int getOffset() {
		return ( page - 1) * recordSize;
	}
	
}
