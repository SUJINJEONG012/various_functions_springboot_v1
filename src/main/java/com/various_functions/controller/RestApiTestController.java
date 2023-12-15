package com.various_functions.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.various_functions.domain.PostResponse;
import com.various_functions.dto.PagingResponse;
import com.various_functions.dto.SearchDto;
import com.various_functions.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestApiTestController {
	
	private final PostService postService;

//	@GetMapping("/members")
//	@ResponseBody
//	public List<Map<String, Object>> findAllMember(){
//		
//		List<Map<String, Object>> members = new ArrayList<>();
//		
//		for(int i = 1; i<=20; i++) {
//			Map<String,Object> member = new HashMap<>();
//			member.put("id", i);
//			member.put("name", i+"번 개발자");
//			member.put("age" , 10 +i);
//			members.add(member);
//		}
//		return members;
//	}
	
	
	@GetMapping("/posts")
	public PagingResponse<PostResponse> findAllPost(){
		return postService.findAllPost(new SearchDto());
	}
	
}
