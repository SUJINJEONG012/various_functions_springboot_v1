package com.various_functions.common.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.various_functions.controller.MainController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApiTourController {

    private static final String BASE_URL = "http://apis.data.go.kr/B551011/DataLabService/metcoRegnVisitrDDList";
	private static final String SERVICE_KEY = "aawo4vOWfMywpCroEEDHelZG3Ccha5A%2BwKkmNSs9fpfjQ7UdZBaLfAH%2BWcK3UeFDY1%2F1mIhBgPzYGHZQ%2F66lXw%3D%3D";
    
    @GetMapping("/metcoRegnVisitrDDList")
    public ResponseEntity<String> callApi(
             @RequestParam(name = "numOfRows", defaultValue = "10") int numOfRows,
             @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
             @RequestParam(name = "MobileOS", defaultValue = "ETC") String mobileOS,
             @RequestParam(name = "MobileApp", defaultValue = "AppTest") String mobileApp,
             @RequestParam(name = "startYmd", defaultValue = "20210513") String startYmd,
             @RequestParam(name = "endYmd", defaultValue = "20210513") String endYmd) {

        try {
            StringBuilder urlBuilder = new StringBuilder(BASE_URL);
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + SERVICE_KEY);
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(numOfRows), "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pageNo), "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode(mobileOS, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode(mobileApp, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("startYmd", "UTF-8") + "=" + URLEncoder.encode(startYmd, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("endYmd", "UTF-8") + "=" + URLEncoder.encode(endYmd, "UTF-8"));

            // URL 출력
            System.out.println("Request URL: " + urlBuilder.toString());
            
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            
            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            log.info("Response Code : " + responseCode); //200
            
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

            // 응답 데이터 출력
            log.info("Response Data : " + sb.toString());
            
            // XML을 JSON으로 변환
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode jsonNode = xmlMapper.readTree(sb.toString());
            ObjectMapper jsonMapper = new ObjectMapper();
            String jsonString = jsonMapper.writeValueAsString(jsonNode);

            // JSON 형태로 변환된 데이터 출력
            log.info("JSON 형태로 변환된 데이터 : " + jsonString);

            // 처리된 응답을 반환
            return ResponseEntity.ok(jsonString);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred");
        }
    }
}
