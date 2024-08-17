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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		
		// 3. 공공데이터 API 호출
        try {
            // RestTemplate을 사용하여 ApiTourController의 API 호출
            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = "http://localhost:8081/metcoRegnVisitrDDList";
            String apiResponse = restTemplate.getForObject(apiUrl, String.class);

            // JSON 데이터를 JsonNode로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(apiResponse);

            // 로깅: 전체 JSON 노드 확인
            log.info("전체 JSON 노드: " + jsonNode.toString());

            // response > body > items > item 구조의 데이터를 추출
            JsonNode itemsNode = jsonNode.path("body").path("items").path("item");

            // 로깅: 추출된 itemsNode 확인
            if (itemsNode.isArray()) {
                List<Map<String, Object>> apiDataList = objectMapper.convertValue(itemsNode, new TypeReference<List<Map<String, Object>>>() {});
                log.info("추출된 API 데이터 리스트: " + apiDataList);
                model.addAttribute("apiData", apiDataList);
            } else {
                log.warn("itemsNode의 형식이 예상과 다릅니다: " + itemsNode);
                model.addAttribute("apiError", "데이터 형식이 올바르지 않습니다.");
            }
        } catch (IOException e) {
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