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
	
//	private Long riid;
//	private String riroomtype; // 객실타입
//	private int riroom;// 총객실
//	private String riservice;// 부가서비스
//	private String risize;// 객실크기
//	private int riminper; //  숙박가능인원
//	private int rimaxper; // 숙박최대인원
//	private int ripeak; // 성수기 요금
//	private int risemipeak; // 준성수기 요금
//	private int rioff; // 비수기 요금
//	private String rimainimg; // 대표이미지
//	private String riextraimg1; // 추가이미지1
//	private String riextraimg2; // 추가이미지2
	
	
	
}
