package com.various_functions.admin.vo;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 숙소
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationsVo {
	
	private Long accommodationId; // 숙소아이디
	private String accommodationCate; //카테고리
	private String anaaccommodationName; // 숙소이름
	private String accommodationAdress; // 숙소주소
	private String accommodationPhone; //숙소 전화번호
	private int accommodationTotalroom; // 총객실수
	private String accommodationGrade; //숙소등급
	private String accommodationDetail ; //숙소설명
	private LocalDateTime accommodationRegdate;

}
