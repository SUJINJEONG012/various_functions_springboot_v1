package com.various_functions.common.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.various_functions.service.ApiTourService2;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApiTourController2 {

    private static final String BASE_URL = "http://apis.data.go.kr/B551011/Odii/themeBasedList";
	private static final String SERVICE_KEY = "aawo4vOWfMywpCroEEDHelZG3Ccha5A+wKkmNSs9fpfjQ7UdZBaLfAH+WcK3UeFDY1/1mIhBgPzYGHZQ/66lXw==";
    
	 @Autowired
	 private ApiTourService2 apiTourService2;
	 
	 
	 @GetMapping("/themeBasedList")
	    public ResponseEntity<String> callApi(
	            @RequestParam(name = "numOfRows", defaultValue = "10") int numOfRows,
	            @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
	            @RequestParam(name = "MobileOS", defaultValue = "WIN") String mobileOS,
	            @RequestParam(name = "MobileApp", defaultValue = "관광지") String mobileApp,
	            @RequestParam(name = "langCode", defaultValue = "ko") String langCode) {

	        try {
	            // ApiTourService2를 사용하여 API 호출
	            String apiResponse = apiTourService2.callApi(numOfRows, pageNo, mobileOS, mobileApp, langCode);

	            return ResponseEntity.ok(apiResponse);

	        } catch (IOException e) {
	            log.error("Error occurred while processing API response: ", e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
	        }
	    }
	
}
