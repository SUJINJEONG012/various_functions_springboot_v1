package com.various_functions.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.various_functions.admin.dto.ReservationDto;
import com.various_functions.admin.vo.ReservationVo;

@Mapper
public interface ReservationMapper {

	
	//예약 생성 메서드
	void insertReservation(ReservationDto reservationDto);
	
	// 특정 회원의 예약 목록 조회
    List<ReservationDto> getReservationsByMemberId(@Param("memberId") Long memberId);
    
    // 특정 방의 예약 목록 조회
    List<ReservationDto> getReservationsByRoomId(@Param("roomId") Long roomId);
    
}
