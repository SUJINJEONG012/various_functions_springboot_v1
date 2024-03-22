package com.various_functions.admin.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.mapper.RoomInfoMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoomInfoService {

	private final RoomInfoMapper roomInfoMapper;
	
	@Autowired
	public RoomInfoService(RoomInfoMapper roomInfoMapper) {
		this.roomInfoMapper = roomInfoMapper;
	}
	

	@Transactional
    public Long insertRoomInfo(RoomInfoDto roomInfoDto) {
        log.info("insertRoomInfo 메서드 진입 ");
        if (roomInfoDto == null) {
            throw new IllegalArgumentException("RoomInfoDto is null");
        }

        log.info("insertRoomInfo 객실 정보 저장 전 ");
        // 객실 정보 저장
        roomInfoMapper.insertRoomInfo(roomInfoDto);
        log.info("insertRoomInfo 객실 정보 저장 후 ");

        // 저장된 객실의 키(주키)를 가져오기 위해 MyBatis의 selectKey를 사용하여 생성된 키를 가져옴
        Long riid = roomInfoDto.getRiid();

        return riid;
    }

}
