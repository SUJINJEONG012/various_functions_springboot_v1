package com.various_functions.admin.dto;

import java.util.ArrayList;
import java.util.List;

import com.various_functions.dto.Pagination;

import lombok.Getter;

@Getter
public class PagingResponse<T> {

	private List<T> list = new ArrayList<>();
	private Pagination pagination;
	
	
	public PagingResponse(List<T> list, Pagination pagination) {
		this.list.addAll(list);
		this.pagination = pagination;
	}
	
}
