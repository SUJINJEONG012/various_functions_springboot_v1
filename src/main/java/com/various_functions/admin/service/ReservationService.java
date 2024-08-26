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

	private final ReservationMapper reservationMapper;
	
	public void createReservation(ReservationDto reservationDto) {
		reservationMapper.insertReservation(reservationDto);
    }
	
}

