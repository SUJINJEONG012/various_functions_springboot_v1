package com.various_functions.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.service.MemberService;

import lombok.RequiredArgsConstructor;

//@RestController
//@RequiredArgsConstructor
//public class ApiKakaoController {
//
//		@Autowired
//		private MemberService memberService;
//		//http://localhost:8081/auth/kakao/callback?code=
//		@GetMapping("/auth/kakao/callback")
//		public @ResponseBody String kakaoCallback(String code) {
//			return "zke7z1_4nsiG4bhFUg3nNpgYbMesVf7zsg91-O4F-2fyle9bP3auFgAAAAQKKiWPAAABkVTEAa0q17LwdM8QAg";
//		}
//
//}
