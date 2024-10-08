package com.various_functions.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.vo.MemberVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class SessionController {

	@GetMapping("/check-login")
	public ResponseEntity<Map<String, Object>> checkLogin(HttpSession session) {

		Map<String, Object> response = new HashMap<>();
		MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
		if (loginMember != null) {
			response.put("isLoggedIn", true);
			response.put("memberId", loginMember.getLoginId());
		} else {
			response.put("isLoggedIn", false);
		}

		return ResponseEntity.ok(response);
	}
}
