package com.various_functions.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.various_functions.admin.service.AccommodationFileService;
import com.various_functions.admin.service.AccommodationService;
import com.various_functions.admin.service.NoticeService;
import com.various_functions.admin.vo.AccommodationsFileVo;
import com.various_functions.admin.vo.AccommodationsVo;
import com.various_functions.admin.vo.NoticeVo;
import com.various_functions.service.ApiTourService2;

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
	@Autowired
    private ApiTourService2 apiTourService2;
	
	
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
		
		
		// 3. 공공데이터 API 호출
	    try {
	       String apiResponse = apiTourService2.callApi(10, 1, "ETC", "AppTest", "ko");
	       
	       ObjectMapper objectMapper = new ObjectMapper();
	       JsonNode jsonNode = objectMapper.readTree(apiResponse);
	       
	       log.info("전체 노드 : " + jsonNode.toString());
	       
	       JsonNode itemsNode = jsonNode.path("response").path("body").path("items").path("item");
	       
	       if (itemsNode.isArray()) {
	           List<Map<String, Object>> apiDataList = objectMapper.convertValue(itemsNode, new TypeReference<List<Map<String, Object>>>() {});
	           log.info("추출된 API 데이터 리스트: " + apiDataList);
	           model.addAttribute("apiData", apiDataList);
	       } else {
	           log.warn("itemsNode의 형식이 예상과 다릅니다: " + itemsNode);
	           model.addAttribute("apiError", "데이터 형식이 올바르지 않습니다.");
	       }
	       
	       }catch(IOException e) {
	    	    log.error("공공데이터 API 호출 중 에러 발생", e);
	    	    model.addAttribute("apiError", "공공데이터를 불러오는 중 문제가 발생했습니다.");
	       }
        
		return "/index";
	}
	
	
	
	@GetMapping("/notice")
	public String main() {
		log.info("유저 메인에 진입");
		
		// 1. 게시글 가져오기
		return "/notice/list";
	}
	
	
	
	
	
	
	

}