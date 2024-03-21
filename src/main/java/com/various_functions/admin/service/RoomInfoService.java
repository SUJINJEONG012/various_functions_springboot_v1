package com.various_functions.admin.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.mapper.RoomInfoMapper;

@Service
public class RoomInfoService {

	private final RoomInfoMapper roomInfoMapper;
	
	@Autowired
	public RoomInfoService(RoomInfoMapper roomInfoMapper) {
		this.roomInfoMapper = roomInfoMapper;
	}
	
	@Transactional
	public void insertRoomInfo(RoomInfoDto roomInfoDto) {
		roomInfoMapper.insertRoomInfo(roomInfoDto);
	}

}
