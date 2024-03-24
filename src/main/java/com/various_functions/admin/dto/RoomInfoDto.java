package com.various_functions.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomInfoDto {

	private Long riid;
	private String riroomtype; // 객실타입
	private int riroom;// 총객실
	private String riservice;// 부가서비스
	private String risize;// 객실크기
	private int riminper; //  숙박가능인원
	private int rimaxper; // 숙박최대인원
	private int ripeak; // 성수기 요금
	private int risemipeak; // 준성수기 요금
	private int rioff; // 비수기 요금	
	private Long aid;
	
	
}
