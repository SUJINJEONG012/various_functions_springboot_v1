package com.various_functions.admin.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationsDto {

	private Long accommodationId; // 숙소아이디
	private String accommodationCate; //카테고리
	private String accommodationName; // 숙소이름
	private String accommodationAdress; // 숙소주소
	private String accommodationPhone; //숙소 전화번호
	private int accommodationTotalroom; // 총객실수
	private String accommodationGrade; //숙소등급
	private String accommodationDetail; //숙소설명
	
	//객실정보 연관관계 
	//private List<RoomInfoDto> roomInfoList;
}
