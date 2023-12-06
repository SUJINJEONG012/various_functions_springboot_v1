package com.various_functions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.various_functions.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	
	//게시글 작성 페이지
	@GetMapping("/post/write.do")
	public String openPostWrite(Model model) {
		return "post/write";
	}
	
}
