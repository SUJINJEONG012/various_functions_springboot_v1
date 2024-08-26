package com.various_functions.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Map<String, Object>> reserve(@RequestBody ReservationDto reservationDto){
		Map<String, Object> response = new HashMap<>();
		try {
			
			reservationService.createReservation(reservationDto);
			response.put("success", true);
			response.put("message", "예약이 성공적으로 완료되었습니다.");
		}catch(Exception e) {
			response.put("success", false);
			response.put("message", "예약에 실패했습니다." + e.getMessage());
		}
		return ResponseEntity.ok(response);
	} 

    
}
