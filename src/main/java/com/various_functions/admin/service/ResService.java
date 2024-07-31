package com.various_functions.admin.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.various_functions.admin.dto.ResDto;
import com.various_functions.admin.mapper.ResMapper;
import com.various_functions.admin.mapper.RoomInfoMapper;
import com.various_functions.admin.vo.ResVo;
import com.various_functions.admin.vo.RoomInfoVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResService {

	private final ResMapper resMapper;
	private final RoomInfoMapper roomInfoMapper;
	
	 @Transactional
	public void saveReservation(ResDto resDto) {
		int totalAmount = calculatePrice(resDto.getCheckin(), resDto.getCheckout(), resDto.getRoomId());
		
		ResVo res = new ResVo();
		res.setRecheckin(resDto.getCheckin());
		res.setRecheckout(resDto.getCheckout());
		res.setRiid(resDto.getRoomId());
		res.setTotalAmount(totalAmount);
		
		// 추가적인 예약 정보를 설정
		resMapper.resSave(resDto);
	}
	
	
	private int calculatePrice(Date checkin, Date checkout, Long roomId) {
		// 객실 정보를 가져오기
		RoomInfoVo roomInfoVo = roomInfoMapper.findRoomById(roomId);
		if(roomInfoVo == null) {
			throw new RuntimeException("Room not found");
		}
		
		// 체크인 및 체크아웃 날짜를 설정
		LocalDate checkinDate = LocalDate.ofInstant(checkin.toInstant(), ZoneId.systemDefault());
		LocalDate checkoutDate = LocalDate.ofInstant(checkout.toInstant(), ZoneId.systemDefault());
		
		// 날짜 차이 계산
		long numberOfNights = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
		
		// 객실 요금 계산 (여기서는 성수기 요금만 예로 사용)
		int roomRate = roomInfoVo.getRoomPeak();
		
		return (int) (numberOfNights * roomRate);
	}
	
}

