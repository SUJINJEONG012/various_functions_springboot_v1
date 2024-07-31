package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.vo.RoomInfoVo;

@Mapper
public interface RoomInfoMapper {
	
	// 객실정보 저장
	Long insertRoomInfo(RoomInfoDto roomInfoDto);
	// 객실정보 업데이트
	void update(RoomInfoDto roomInfoDto);
	
	// 객실정보 리스트
	List<RoomInfoVo> findRoomsByAccommodationId(Long accommodationId);
	
	// 객실정보(단일)를 조회하는 메서드
	RoomInfoVo findRoomById(Long roomId);
}
