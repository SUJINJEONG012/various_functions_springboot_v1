package com.various_functions.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.various_functions.admin.service.AccommodationFileService;
import com.various_functions.admin.service.AccommodationService;
import com.various_functions.admin.service.NoticeService;
import com.various_functions.admin.vo.AccommodationsFileVo;
import com.various_functions.admin.vo.AccommodationsVo;
import com.various_functions.admin.vo.NoticeVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {

	@Autowired
	private NoticeService noticeService;
	@Autowired
	private AccommodationService accommodationService;
	@Autowired
	private AccommodationFileService accommodationFileService;


	@GetMapping("/")
	public String main(HttpServletRequest request, Model model) {
		log.info("유저 메인에 진입");
		HttpSession session = request.getSession();
		String errorMessage = (String) session.getAttribute("errorMessage");
		if(errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
			session.removeAttribute("errorMessage"); // 세션에서 메시지 제거
		}
		
		// 1. 게시글 가져오기
		List<NoticeVo> recentnotices = noticeService.findRecentNotices();
		model.addAttribute("recentnotices", recentnotices);
		
		
		// 2. 숙소리스트 가져오기
		List<AccommodationsVo> accommodations1 = accommodationService.findRecentAccommodations();
		
		// 각 숙소별 파일 리스트 가져오기
	    Map<Long, List<AccommodationsFileVo>> filesMap = new HashMap<>();
	    for (AccommodationsVo accommodation : accommodations1) {
	        List<AccommodationsFileVo> files = accommodationFileService.findFileByAccommodationId(accommodation.getAccommodationId());
	        filesMap.put(accommodation.getAccommodationId(), files);
	    }
	    
		model.addAttribute("accommodations",accommodations1);
		model.addAttribute("filesMap", filesMap);
		return "/index";
	}
	
	
	@GetMapping("/notice")
	public String main() {
		log.info("유저 메인에 진입");
		
		// 1. 게시글 가져오기

		return "/notice/list";
	}
	
	
	
	

}