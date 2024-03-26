package com.various_functions.admin.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomInfoVo {

	private Long roomId; //객실아이디
	private String roomRoomtype; // 객실타입 
	private String roomService; // 부가서비스
	private String roomSize; // 객실크기 
	private int roomMinper; // 숙박가능인원
	private int roomMaxper; // 숙박최대인원
	private int roomPeak; //성수기 
	private int roomSemipeak; //준성수기 
	private int roomOff; //비수기 

	private Long accommodationId; // 숙소아이디 
	private AccommodationsVo accommodation;
}
