package com.various_functions.admin.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.various_functions.dto.ReservationDto;
import com.various_functions.vo.ReservationVo;

@Mapper
public interface ReservationMapper {
	// 예약 생성 메서드
	int insertReservation(ReservationDto reservationDto);

	// 특정 회원의 예약 목록 조회
	List<ReservationDto> getReservationsByMemberId(@Param("memberId") Long memberId);

	// 특정 방의 예약 목록 조회
	List<ReservationDto> getReservationsByRoomId(@Param("roomId") Long roomId);

	// 예약 정보 조회
	ReservationDto findById(Long rid);

	// 특정방의 예약가능 여부 확인
	int countOverlappingReservations(@Param("roomId") Long roomId, @Param("checkInDate") Date checkInDate,
			@Param("checkOutDate") Date checkOutDate);

	boolean updateReservationStatus(ReservationDto reservationDto);

	// '예약대기' 상태의 예약들을 '예약취소'로 업데이트하는 메서드
	boolean updatePendingReservationsToCancelled(ReservationDto reservationDto);

	// 예약 조회
	List<ReservationVo> findReservationsByMemberId(Long memberId);
}
