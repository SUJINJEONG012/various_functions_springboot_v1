package com.various_functions.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RoomInfoDto {

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
	
	@Builder
	public RoomInfoDto(String roomRoomtype, String roomService, String roomSize, int roomMinper, int roomMaxper, int roomPeak, int roomSemipeak, int roomOff) {
		this.roomRoomtype = roomRoomtype;
		this.roomService = roomService;
		this.roomSemipeak = roomSemipeak;
		this.roomSize = roomSize;
		this.roomMinper = roomMinper;
		this.roomMaxper = roomMaxper;
		this.roomPeak = roomPeak;
		this.roomSemipeak = roomSemipeak;
		this.roomOff = roomOff;
	}

	//  객실정보는 숙소정보가 insert k후에 처리
	public void setAccommodationId(Long accommodationId) {
		this.accommodationId = accommodationId;
	}
	
	
}
