package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.AccommodationAndRoomInfoDto;
import com.various_functions.admin.dto.RoomInfoDto;

@Mapper
public interface RoomInfoMapper {
	
	//객실정보 저장
	void insertRoomInfo(RoomInfoDto roomInfoDto);
	
}
