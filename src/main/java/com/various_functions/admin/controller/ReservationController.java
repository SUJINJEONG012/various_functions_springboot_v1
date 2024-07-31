package com.various_functions.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.admin.dto.ResDto;
import com.various_functions.admin.service.ResService;

@RestController
public class ReservationController {

	@Autowired
	private ResService resService;
	
	@GetMapping("")
	public String showReservationForm(Model model) {
		model.addAttribute("res", new ResDto());
		return "resForm";
	}
	
	
	
}
