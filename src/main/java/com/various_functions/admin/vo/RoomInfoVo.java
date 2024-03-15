package com.various_functions.admin.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomInfoVo {

	private long riid; //객실아이디
	private String riroomtype; // 객실타입 
	private int riroom; // 총 객실 
	private String riservice; // 부가서비스
	private String risize; // 객실크기 
	private int riminper; // 숙박가능인원
	private int rimaxper; // 숙박최대인원
	private int ripeak; //성수기 
	private int risemipeak; //준성수기 
	private int rioff; //비수기 
 	
	private long aid; // 숙소아이디 
	
	private List<RoomInfoVo> roominfoVo;
}
