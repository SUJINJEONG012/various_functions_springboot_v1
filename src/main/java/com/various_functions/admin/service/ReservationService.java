package com.various_functions.admin.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.various_functions.admin.dto.ReservationDto;
import com.various_functions.admin.mapper.ReservationMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

	private ReservationMapper reservationMapper;
	
	@Transactional
    public void createReservation(ReservationDto request) {
		
		ReservationDto reservation = new ReservationDto();
		
        reservation.setRordernum(request.getRordernum());
        reservation.setRamount(request.getRamount());
        reservation.setRroomnum(request.getRroomnum());
        reservation.setRcheckin(request.getRcheckin());
        reservation.setRcheckout(request.getRcheckout());
        reservation.setRresname(request.getRresname());
        reservation.setRresphone(request.getRresphone());
        reservation.setRresemail(request.getRresemail());
        reservation.setMemberId(request.getMemberId());
        reservation.setRoomId(request.getRoomId());

        reservationMapper.insertReservation(reservation);
    }
    
    public List<ReservationDto> getReservationsByMemberId(Long memberId) {
        return reservationMapper.getReservationsByMemberId(memberId);
    }
    
    public List<ReservationDto> getReservationsByRoomId(Long roomId) {
        return reservationMapper.getReservationsByRoomId(roomId);
    }
	
}

