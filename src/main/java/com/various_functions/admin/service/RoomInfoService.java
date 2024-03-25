package com.various_functions.admin.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.mapper.AccommodationsMapper;
import com.various_functions.admin.mapper.RoomInfoMapper;

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

}
