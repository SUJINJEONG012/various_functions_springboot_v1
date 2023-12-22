package com.various_functions.dto;

import com.various_functions.aop.LoggerAspect;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Pagination {

	private int totalRecordCount; // 전체 데이터 수 
	private int totalPageCount; // 전체 페이지 수
	private int startPage; // 첫 페이지 번호
	private int endPage; //마지막 페이지 번호
	private int limitStart; //limit 시작 위치
	private boolean existPrevPage; // 이전 페이지 존재 여부
	private boolean existNextPage; // 다음 페이지 존재 여부
	
	
	public Pagination (int totalRecordCount, SearchDto params) {
		if(totalRecordCount > 0) {
			this.totalRecordCount = totalRecordCount;
			calculation(params);
			params.setPagination(this);
		}
	}
	
	private  void calculation(SearchDto params) {
		
		//전체 페이지 수 계산
		totalPageCount = ((totalRecordCount - 1) / params.getRecordSize()) + 1;
		
		//현재 페이지 번호가 전체 페에지 수보다 큰 경우, 현재 페이지번호에 전체 페이지 수 지정
		if(params.getPage() > totalPageCount) {
			params.setPage(totalPageCount);
		}
		
		//첫 페이지 번호 계산
		log.info("params.getPage() -1 :: ", params.getPage() -1);
		startPage = ((params.getPage() -1) / params.getPageSize())* params.getPageSize() +1;
		
		//끝 페이지 번호 계산
		endPage = startPage + params.getPageSize()-1;
		//끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지 전체 페이지 수 저장
		if(endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		
		// Limit 시작 위치 계산
		limitStart = (params.getPage() -1) * params.getRecordSize();
		// 이전 페이지 존재 여부 확인
		existPrevPage = startPage != 1;
		// 다음 페이지 존재 여부 확인
		existNextPage = (endPage * params.getRecordSize()) < totalRecordCount;
	}
	
}
