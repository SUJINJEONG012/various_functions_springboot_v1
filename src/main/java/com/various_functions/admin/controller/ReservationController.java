package com.various_functions.admin.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.admin.service.RoomInfoService;

@RestController
@RequestMapping("/api")
public class ReservationController {

	@Autowired
    private RoomInfoService roomInfoService;
	
	 @PostMapping("/calculateTotalAmount")
	    public ResponseEntity<Map<String, Object>> calculateTotalAmount(@RequestBody Map<String, String> request) {
	        String checkinDate = request.get("checkinDate");
	        String checkoutDate = request.get("checkoutDate");
	        Long roomId = Long.parseLong(request.get("roomId"));

	        int roomPeakPrice = roomInfoService.getRoomPeakPrice(roomId);
	        // 날짜와 요금 계산 로직을 추가합니다.
	        int totalAmount = calculateAmount(checkinDate, checkoutDate, roomPeakPrice);

	        Map<String, Object> response = new HashMap<>();
	        response.put("totalAmount", totalAmount);

	        return ResponseEntity.ok(response);
	    }
	 
	 private int calculateAmount(String checkinDate, String checkoutDate, int roomPeakPrice) {
	        // 여기서 날짜와 요금을 계산하는 로직을 구현합니다.
	        // 예시로 1박당 100,000원을 설정합니다.
	        // 실제로는 checkinDate와 checkoutDate를 이용하여 정확한 요금을 계산해야 합니다.
	        LocalDate checkin = LocalDate.parse(checkinDate);
	        LocalDate checkout = LocalDate.parse(checkoutDate);

	        long nights = ChronoUnit.DAYS.between(checkin, checkout);
	        //int ratePerNight = 100000; // 예시 요금

	        return (int) (nights * roomPeakPrice);
	    }
	
	
	
}
