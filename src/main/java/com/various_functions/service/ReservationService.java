package com.various_functions.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	// 예약생성 로직에서 체크인/체크아웃 겹침 여부확인
	@Transactional // 트랜잭션 관리
	public Long createReservation(ReservationDto reservationDto, HttpSession session) {

		// 세션에서 로그인한 사용자 정보 가져오기
		MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
		if (loginMember == null) {
			log.error("로그인된 사용자가 없습니다."); // 로그인 확인 로그
			throw new IllegalStateException("로그인된 사용자가 없습니다.");
		}

		// room_id 가 존재하지않으면 예외발생
		Long roomId = reservationDto.getRoomId();
		if (roomId == null) {
			throw new IllegalArgumentException("roomId가 필요합니다.");
		}

		// 로그인한 사용자의 회원 아이디 가져오기
		Long memberId = loginMember.getMemberId();
		reservationDto.setMemberId(memberId);

		// 체크인 및 체크아웃 날짜 검증 추가
		validateReservationDates(reservationDto); // 유효성 검사 메소드 호출

		log.info("체크인 날짜: " + reservationDto.getCheckInDate());
		log.info("체크아웃 날짜: " + reservationDto.getCheckOutDate());

		int overlappingCount = reservationMapper.countOverlappingReservations(roomId, reservationDto.getCheckInDate(),
				reservationDto.getCheckOutDate());
		log.info("중복 예약 수: " + overlappingCount); // 중복 예약 수 로그

		if (overlappingCount > 0) {
			log.info("해당 날짜에 이미 예약이 있습니다");
			throw new IllegalStateException("해당 날짜에 이미 예약이 있습니다.");
		} else {
			// 예약 가능상태
		}

		// 예약정보저장
		reservationMapper.insertReservation(reservationDto);
		return reservationDto.getRid();

	}

	// 예약 날짜 유효성 검사 메소드
	private void validateReservationDates(ReservationDto reservationDto) {
		if (reservationDto.getCheckInDate() == null || reservationDto.getCheckOutDate() == null) {
			throw new IllegalArgumentException("체크인 및 체크아웃 날짜는 필수입니다.");
		}
		if (reservationDto.getCheckInDate().after(reservationDto.getCheckOutDate())) {
			throw new IllegalArgumentException("체크인 날짜는 체크아웃 날짜보다 이전이어야 합니다.");
		}
	}

	public Boolean confirmReservation(ReservationDto reservationDto) {
		if (reservationDto.getRid() == null) {
			throw new IllegalArgumentException("예약 ID가 필요합니다. ");
		}
		// 결제ID를 사용하여 예약상태를 업데이트
		reservationDto.setResState("결제완료"); // 상태를 변경
		return reservationMapper.updateReservationStatus(reservationDto);

	}

}
