package com.various_functions.admin.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 숙소
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationsFileVo {
	
	private Long afId;
	private Long accommodationId;
	private String originalName;
	private String saveName;
	private int size;
	private Boolean deleteYn;
	private LocalDateTime createdDate; //생성일시
	private LocalDateTime modifiedDate; //최종 수정일시
	
	

}
