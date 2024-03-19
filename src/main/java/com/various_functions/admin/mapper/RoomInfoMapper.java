package com.various_functions.admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.various_functions.admin.dto.RoomInfoDto;

@Mapper
public interface RoomInfoMapper {
	
	//Long saveRoomInfo(RoomInfoDto roomInfoDto);
	Long saveRoomInfo(RoomInfoDto roomInfoDto);
	
    RoomInfoDto getRoomInfoById(Long riid);
    
    void updateRoomInfo(RoomInfoDto roomInfoDto);
    
    void deleteRoomInfo(Long riid);
    
}
