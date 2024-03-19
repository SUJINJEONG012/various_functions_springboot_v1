package com.various_functions.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.mapper.RoomInfoMapper;

@Service
public class RoomInfoService {

	 @Autowired
	    private RoomInfoMapper roomInfoMapper;

	    public void saveRoomInfo(RoomInfoDto roomInfoDto) {
	        roomInfoMapper.saveRoomInfo(roomInfoDto);
	    }

}
