package com.various_functions.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.admin.dto.ReservationDto;
import com.various_functions.admin.service.ReservationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	/*
	 * @RequestBody는클라이언트로부터 전송된 json데이터를 java객체로 변환하여 메서드의 매개변수로 전달하는 역할
	 */
	@PostMapping("/reserve")
	public ResponseEntity<Map<String, Object>> reserve(@RequestBody ReservationDto reservationDto,
			HttpSession session) {
		log.info("reserve 진입!!");

		// 요청 본문 로그
		log.info("reservation data: " + reservationDto);

		// 요청에대한 응답을 받기 위해 사용 => 키와값의쌍으로 데이터를 저장
		Map<String, Object> response = new HashMap<>();
		try {
			// 예약생성
			reservationService.createReservation(reservationDto, session);
			response.put("success", true);
			response.put("message", "예약이 성공적으로 완료되었습니다.");
		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "예약에 실패했습니다." + e.getMessage());
		}
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON) // 응답 타입을 명시적으로 설정;
				.body(response);
	}

}
