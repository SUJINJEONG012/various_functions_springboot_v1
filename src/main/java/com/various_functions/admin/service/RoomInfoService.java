//package com.various_functions.admin.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.various_functions.admin.dto.AccommodationsDto;
//import com.various_functions.admin.dto.RoomInfoDto;
//import com.various_functions.admin.mapper.AccommodationsMapper;
//
//@Service
//public class RoomInfoService {
//
//	 @Autowired
//	    private AccommodationsMapper accommodationsMapper; // AccommodationsMapper 주입
//
//	    public void saveRoomInfo(RoomInfoDto roomInfoDto) {
//	        // 숙소 이름을 이용하여 숙소 정보를 가져옴
//	        AccommodationsDto accommodationsDto = accommodationsMapper.getAccommodationByName(roomInfoDto.getAccommodationsDto().getAname());
//	        
//	        // 가져온 숙소 정보가 있으면 해당 숙소의 아이디를 RoomInfoDto 객체에 설정
//	        if (accommodationsDto != null) {
//	            roomInfoDto.getAccommodationsDto().setAid(accommodationsDto.getAid());
//	        }
//	        
//	        
//	    }
//
//}
