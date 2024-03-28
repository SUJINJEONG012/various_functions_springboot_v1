package com.various_functions.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AccommodationsFileDto {

	private long fileId;
	private Long accommodationId;
	private String originalName;
	private String saveName;
	private int size;
	
	@Builder
	public AccommodationsFileDto(String originalName, String saveName, int size) {
		this.originalName = originalName;
		this.saveName = saveName;
		this.size = size;
	}
	
	public void setAccommodationId(Long accommodationId) {
		this.accommodationId = accommodationId;
	}
	
}
