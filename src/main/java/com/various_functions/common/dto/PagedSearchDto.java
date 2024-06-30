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
	private int pageSize; // 화면 하단에 출력할 페이지 사이즈
	private String keyword; // 검색키워드
	private String searchType; // 검색유형
	
	/*
	 * 객체가 생성되는 시점에 현재페이지는 번호 1
	 * 페이지당 출력할 데이터 개수와 하단에 출력할 페이지 개수 10으로 초기화
	 * */
	public PagedSearchDto() {
		this.page = 1;// 현재패에지 
		this.recordSize = 10; // 10개씩 페이지 처리
		this.pageSize =10; // 하단페이지 10
		
	}
	
	/* MySQL DB에서 LIMIT 구문의 시작 부분에 사용되는 메서드
	 * 현재페이지 계산하는 구문
	 */
	public int getOffset() {
		return ( page - 1) * recordSize;
	}
	/*
	 * 현재 페이지 그룹의 시작 페이지 번호를 계산합니다. 페이지 그룹은 pageSize에 따라 정의되며, 
	 * 현재 페이지 그룹의 첫 번째 페이지 번호를 반환
	 * */
	public int getCurrentPageGroupStart() {
        return ((page - 1) / pageSize) * pageSize + 1;
    }
	
	/*
	 * 전체 데이터 개수를 받아 현재 페이지 그룹의 마지막 페이지 번호를 계산합니다. 
	 * 페이지 그룹의 끝이 전체 페이지 수를 초과하지 않도록 처리
	 * */
	public int getCurrentPageGroupEnd(int totalRecordCount) {
        int totalPages = (int) Math.ceil((double) totalRecordCount / recordSize);
        int end = getCurrentPageGroupStart() + pageSize - 1;
        return Math.min(end, totalPages);
    }
	
	/*
	 * 
	 * */
	
	public boolean hasPreviousPageGroup() {
		return getCurrentPageGroupStart() > 1;
	}
	
	public boolean hasNextPageGroup(int totalRecordCount) {
		int totalPages = (int) Math.ceil((double) totalRecordCount / recordSize);
		//현재그룹의 끝페이지가 총 페이지수보다 작은지 
		return getCurrentPageGroupEnd(totalRecordCount) < totalPages;
	}
	
	// 다음페이지 그룹의 시작 페이지 반환
	public int getNextPageGroupStart(int totalRecordCount) {
		return getCurrentPageGroupEnd(totalRecordCount) +1;
	}
	
	// 이전 페이지 그룹의 마지막 페이지 반환
	public int getPreviousPageGroupEnd() {
		return getCurrentPageGroupStart() -1;
	}
	
	
}
