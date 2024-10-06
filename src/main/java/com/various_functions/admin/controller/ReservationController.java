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

@RestController
@RequestMapping("/api")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@PostMapping("/reserve")
	public ResponseEntity<Map<String, Object>> reserve(@RequestBody ReservationDto reservationDto,
			HttpSession session) {
		System.out.println("reserve 진입!!");

		// 요청 본문 로그
		System.out.println("reservation data: " + reservationDto);

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
