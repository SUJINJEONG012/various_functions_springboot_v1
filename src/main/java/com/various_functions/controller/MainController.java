package com.various_functions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.various_functions.admin.service.AccommodationService;
import com.various_functions.admin.vo.AccommodationsVo;
import com.various_functions.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {

	@Autowired
	private PostService postService;
	@Autowired
	private AccommodationService accommodationService;
	

	@GetMapping("/")
	public String main(Model model) {
		log.info("유저 메인에 진입");
		
		// 1. 게시글 가져오기
		
		// 2. 숙소리스트 가져오기
		List<AccommodationsVo> accommodations = accommodationService.getAllAccommodations();
		model.addAttribute("accommodations",accommodations);


		return "/index";
	}
	
	
	

}