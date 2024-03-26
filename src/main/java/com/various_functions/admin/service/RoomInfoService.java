package com.various_functions.admin.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.mapper.RoomInfoMapper;
import com.various_functions.admin.vo.RoomInfoVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomInfoService {

	private final RoomInfoMapper roomInfoMapper;

	@Transactional
    public void insertRoomInfo(final Long accommodationId, RoomInfoDto roomInfoDto) {
        log.info("insertRoomInfo 메서드 진입 ");
        
        roomInfoDto.setAccommodationId(accommodationId);        
        roomInfoMapper.insertRoomInfo(roomInfoDto);
    }

	public List<RoomInfoVo> findRoomsByAccommodationId(Long accommodationId) {
		log.info("findRoomsByAccommodationId 실행되는지확인!!!");
		return roomInfoMapper.findRoomsByAccommodationId(accommodationId);
	}
	
}
