package com.various_functions.admin.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationAndRoomInfoDto {
	private AccommodationsDto accommodationsDto;
    private RoomInfoDto roomInfoDto;
    private List<RoomInfoDto> roomInfoList;
}
