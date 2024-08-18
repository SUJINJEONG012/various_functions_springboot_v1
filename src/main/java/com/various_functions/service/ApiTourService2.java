package com.various_functions.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.various_functions.common.controller.ApiTourController2;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiTourService2 {

	@Autowired
    private RestTemplate restTemplate;
	       
	private static final String BASE_URL = "http://apis.data.go.kr/B551011/Odii/themeBasedList";
	private static final String SERVICE_KEY = "aawo4vOWfMywpCroEEDHelZG3Ccha5A+wKkmNSs9fpfjQ7UdZBaLfAH+WcK3UeFDY1/1mIhBgPzYGHZQ/66lXw==";

	
    public String callApi(int numOfRows, int pageNo, String mobileOS, String mobileApp, String langCode) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(SERVICE_KEY, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(numOfRows), "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode(mobileOS, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode(mobileApp, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("langCode", "UTF-8") + "=" + URLEncoder.encode(langCode, "UTF-8"));

        // URL 출력
        log.info("Request URL: " + urlBuilder.toString());

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");

        // 응답 코드 확인
        int responseCode = conn.getResponseCode();
        log.info("Response Code : " + responseCode);

        BufferedReader rd;
        if (responseCode >= 200 && responseCode <= 299) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        // 응답 데이터
        String responseData = sb.toString();
        log.info("Response Data : " + responseData);

        // 응답이 XML인 경우 JSON으로 변환
        if (responseData.trim().startsWith("<")) {
            // XML을 JSON으로 변환
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode jsonNode = xmlMapper.readTree(responseData);
            ObjectMapper jsonMapper = new ObjectMapper();
            return jsonMapper.writeValueAsString(jsonNode);
        } else {
            // JSON 응답인 경우 그대로 반환
            return responseData;
        }
    }
    
}
