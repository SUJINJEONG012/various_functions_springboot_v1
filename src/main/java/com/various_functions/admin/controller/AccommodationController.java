package com.various_functions.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.various_functions.admin.service.AccommodationService;
import com.various_functions.admin.vo.AccommodationsVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AccommodationController {
	
	@Autowired
	private AccommodationService accommodationService;
	

	@GetMapping("/accommodation/list")
	
	public String accommodationList(Model model) {
		//숙소리스트 모델에 추가
		List<AccommodationsVo> accommodations = accommodationService.getAllAwccommodations();
		model.addAttribute("accommodations",accommodations);

		
        // 해당하는 뷰 페이지의 이름을 반환
        return "admin/accommodation/list";
	}
	
}
