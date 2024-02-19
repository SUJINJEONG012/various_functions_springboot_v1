package com.various_functions.admin.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationsDto {

	private long aid; // 숙소아이디
	private String acate; //카테고리
	private String aname; // 숙소이름
	private String aadress; // 숙소주소

	private String aphone; //숙소 전화번호
	private String axcoordi; // 좌표 
	private int atotalroom; // 총객실수
	private String agrade; //숙소등급
	private String adetail; //숙소설명
	private String amainimg; //이미지
	
	
}
