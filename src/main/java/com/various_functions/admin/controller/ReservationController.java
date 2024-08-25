package com.various_functions.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.admin.dto.ReservationDto;
import com.various_functions.admin.service.ReservationService;
import com.various_functions.admin.vo.ReservationVo;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	@Autowired
    private ReservationService reservationService;
	
	
	@PostMapping
    public String createReservation(@RequestBody ReservationDto request) {
        reservationService.createReservation(request);
        return "Reservation successfully created!";
    }

    
}
