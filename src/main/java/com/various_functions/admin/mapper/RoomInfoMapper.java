package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.vo.RoomInfoVo;

@Mapper
public interface RoomInfoMapper {
	
	//객실정보 저장
	Long insertRoomInfo(RoomInfoDto roomInfoDto);
	void update(RoomInfoDto roomInfoDto);
	List<RoomInfoVo> findRoomsByAccommodationId(Long accommodationId);
}
