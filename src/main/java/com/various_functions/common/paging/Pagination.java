package com.various_functions.common.paging;

import com.various_functions.common.dto.PagedSearchDto;

import lombok.Getter;

@Getter
public class Pagination {
	
	private int totalRecordCount; // 전체 데이터 
	private int totalPageCount; // 전체 페이지 수
	private int startPage; // 첫 페이지 번호
	private int endPage; // 끝 페이지 번호 
	private int limitStart; // Limit 시작 위치
	private boolean existPrevPage; // 이전 페이지 존재 여부
	private boolean existNextPage; // 다음 페이지 존재 여부
	
	public Pagination(int totalRecordCount, PagedSearchDto pagedSearchDto) {
		if(totalRecordCount > 0) {
			this.totalPageCount = totalRecordCount;
			calculation(pagedSearchDto);
		}
	}
	
	private void calculation(PagedSearchDto pagedSearchDto) {
		// 전체 페이지 수 계산  (전체 데이터 -1) / 출력할 개수 데이터 +1
		totalPageCount = ((totalRecordCount - 1) / pagedSearchDto.getRecordSize()) +1;
		
		// 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페에지번호에 전체 페이지 수 저장
		if(pagedSearchDto.getPage() > totalPageCount) {
			pagedSearchDto.setPage(totalPageCount);
		}
		
		// 첫 페이지 번호 계산 
		startPage =  ((pagedSearchDto.getPage()-1) / pagedSearchDto.getPageSize())  * pagedSearchDto.getPageSize();
		
		// 끝 페이지 번호 계산
		endPage = startPage + pagedSearchDto.getPageSize() -1;
		
		// 끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지 전체 페이지 수 저장
		if(endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		
		// limit 시작 위치 계산
		limitStart = (pagedSearchDto.getPage()-1) * pagedSearchDto.getRecordSize();
		// 이전 페이지 존재여부 확인
		existPrevPage = startPage != 1;
		
		// 다음 페이지 존재여부 확인
		existNextPage = (endPage * pagedSearchDto.getRecordSize()) < totalRecordCount;
	}
	

}
