package com.various_functions.admin.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 숙소
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationsVo {
	
	private Long aid; // 숙소아이디
	private String acate; //카테고리
	private String aname; // 숙소이름
	private String aadress; // 숙소주소
	private String aphone; //숙소 전화번호
	private int atotalroom; // 총객실수
	private String agrade; //숙소등급
	private String adetail; //숙소설명
	private LocalDateTime aregdate;
}
