package com.various_functions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiTourService {

	@Autowired
    private RestTemplate restTemplate;

    private static final String API_URL = "http://apis.data.go.kr/B551011/DataLabService/metcoRegnVisitrDDList_item?serviceKey=aawo4vOWfMywpCroEEDHelZG3Ccha5A%2BwKkmNSs9fpfjQ7UdZBaLfAH%2BWcK3UeFDY1%2F1mIhBgPzYGHZQ%2F66lXw%3D%3D&pageNo=1&numOfRows=10&MobileOS=ETC&MobileApp=AppTest&startYmd=20210513&endYmd=20210513";

    public String getApiData() {
        return restTemplate.getForObject(API_URL, String.class);
    }
    
}
