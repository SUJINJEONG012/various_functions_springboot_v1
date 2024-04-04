package com.various_functions.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AccommodationsFileDto {

	private Long afId;
	private Long accommodationId;
	private String originalName;
	private String saveName;
	private int size;
	
	@Builder
	public AccommodationsFileDto(Long afId, Long accommodationId, String originalName, String saveName, int size) {
		this.afId = afId;
		this.accommodationId = accommodationId;
		this.originalName = originalName;
		this.saveName = saveName;
		this.size = size;
	}
	
	public void setAccommodationId(Long accommodationId) {
		this.accommodationId = accommodationId;
	}
	
}
