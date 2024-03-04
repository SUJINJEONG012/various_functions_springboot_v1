package com.various_functions.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationsFileVo {
	
	private String fileName;
	private String uploadPath;
	private String uuid;
}
