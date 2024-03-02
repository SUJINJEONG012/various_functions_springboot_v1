package com.various_functions.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationAndRoomInfoDto {

	private AccommodationsDto accommodationDto;
    private RoomInfoDto roomInfoDto;
    
    //파일경로를 저장할 필드 추가
    private String accommodationImageFilePath;
    private String roomImageFilePath;
    
}
