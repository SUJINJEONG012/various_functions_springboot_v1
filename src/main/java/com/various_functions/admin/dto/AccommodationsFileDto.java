package com.various_functions.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationsFileDto {

	private String fileName;
	private String uploadPath;
	private String uuid;
	
}
