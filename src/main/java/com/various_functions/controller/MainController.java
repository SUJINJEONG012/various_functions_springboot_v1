package com.various_functions.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		
		
		// API 호출하여 데이터 가져오기
		String baseUrl = "http://apis.data.go.kr/B551011/DataLabService/metcoRegnVisitrDDList_item";
        String serviceKey = "aawo4vOWfMywpCroEEDHelZG3Ccha5A%2BwKkmNSs9fpfjQ7UdZBaLfAH%2BWcK3UeFDY1%2F1mIhBgPzYGHZQ%2F66lXw%3D%3D";
        StringBuilder urlBuilder = new StringBuilder(baseUrl);
        try {
            urlBuilder.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
            urlBuilder.append("&pageNo=").append(URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&numOfRows=").append(URLEncoder.encode("10", "UTF-8"));
            urlBuilder.append("&MobileOS=").append(URLEncoder.encode("ETC", "UTF-8"));
            urlBuilder.append("&MobileApp=").append(URLEncoder.encode("AppTest", "UTF-8"));
            urlBuilder.append("&startYmd=").append(URLEncoder.encode("20210513", "UTF-8"));
            urlBuilder.append("&endYmd=").append(URLEncoder.encode("20210513", "UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300 ? conn.getInputStream() : conn.getErrorStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            // JSON 데이터를 파싱하고 모델에 추가
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(sb.toString());
            JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");
            model.addAttribute("apiData", itemsNode);
            System.out.println("데이터 들고오는지 확인: " + itemsNode.toString());

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("apiError", "데이터를 가져오는 데 문제가 발생했습니다.");
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