package com.various_functions.admin.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.ResDto;
import com.various_functions.admin.dto.RoomInfoDto;
import com.various_functions.admin.mapper.ResMapper;
import com.various_functions.admin.mapper.RoomInfoMapper;
import com.various_functions.admin.vo.RoomInfoVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResService {

	private final ResMapper resMapper;
	private final RoomInfoMapper roomInfoMapper;
	
	public ResDto makeReservation(ResDto resDto) {
		//총 금액 계산
		RoomInfoVo room = roomInfoMapper.findRoomById(resDto.getRoomId());
		long daysBetween = ChronoUnit.DAYS.between(
				resDto.getCheckin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), 
				resDto.getCheckout().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
		);
	
		int totalAmount = (int) (room.getRoomPeak() * daysBetween); //성수기 요금만 계산
		resDto.setTotalAmount(totalAmount);
		
		// 예약 저장 
		resMapper.resSave(resDto);
		
		// 삽입된 얘약의 ID를 가져오기 위해 조회
		ResDto savedReservation = resMapper.findById(resDto.getRid());
		return savedReservation;
	}
	
	public ResDto getReservationById(Long rid) {
		
		ResDto reservation = resMapper.findById(rid);
		
		if(reservation != null) {
			
			RoomInfoVo room = roomInfoMapper.findRoomById(reservation.getRid());
			long daysBetween = calculateDaysBetween(reservation.getCheckin(),reservation.getCheckout());
			int totalAmount = calculateTotalAmount(room, reservation.getCheckin(), reservation.getCheckout());
		}
		return resMapper.findById(rid);
	}
	
	private long calculateDaysBetween(Date checkin, Date checkout) {
        LocalDate checkinDate = checkin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate checkoutDate = checkout.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(checkinDate, checkoutDate);
    }

	
	private int calculateTotalAmount(RoomInfoVo room, Date checkin, Date checkout) {
        
		/*
		 * 
		 * 	성수기: 7월 1일 ~ 8월 31일
			준성수기: 6월 1일 ~ 6월 30일 및 9월 1일 ~ 9월 30일
			비수기: 나머지 기간
		 * 
		 * */
		LocalDate checkinDate = checkin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate checkoutDate = checkout.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int totalAmount = 0;
        LocalDate currentDate = checkinDate;

        while (!currentDate.isAfter(checkoutDate.minusDays(1))) {
            if (isPeakSeason(currentDate)) {
                totalAmount += room.getRoomPeak();
            } else if (isSemiPeakSeason(currentDate)) {
                totalAmount += room.getRoomSemipeak();
            } else {
                totalAmount += room.getRoomOff();
            }
            currentDate = currentDate.plusDays(1);
        }
        return totalAmount;
    }
	
	private boolean isPeakSeason(LocalDate date) {
        return (date.isAfter(LocalDate.of(date.getYear(), 6, 30)) && date.isBefore(LocalDate.of(date.getYear(), 9, 1)));
    }

    private boolean isSemiPeakSeason(LocalDate date) {
        return (date.isAfter(LocalDate.of(date.getYear(), 5, 31)) && date.isBefore(LocalDate.of(date.getYear(), 7, 1)))
                || (date.isAfter(LocalDate.of(date.getYear(), 8, 31)) && date.isBefore(LocalDate.of(date.getYear(), 10, 1)));
    }
	
	
	
}

