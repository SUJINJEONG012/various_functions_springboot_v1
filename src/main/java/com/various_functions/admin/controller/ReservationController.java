package com.various_functions.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.admin.service.ResService;

@RestController
public class ReservationController {

	@Autowired
	private ResService resService;
	
	
}
