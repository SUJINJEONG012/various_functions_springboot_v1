package com.various_functions.admin.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.various_functions.admin.dto.ReservationDto;
import com.various_functions.admin.mapper.ReservationMapper;
import com.various_functions.vo.MemberVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationMapper reservationMapper;

	public void createReservation(ReservationDto reservationDto, HttpSession session) {
		//세션에서 로그인한 사용자 정보 가져오기
		MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
		if (loginMember == null) {
            throw new IllegalStateException("로그인된 사용자가 없습니다.");
        }
		
		
		// 로그인한 사용자의 회원 아이디 가져오기
		Long memberId = loginMember.getMemberId(); 
		reservationDto.setMemberId(memberId);
		
		reservationMapper.insertReservation(reservationDto);
    }
	
	
}

