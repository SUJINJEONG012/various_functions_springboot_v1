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
		String title="제목",
				content="내용",
				writer ="홍길동";
		model.addAttribute("t", title);
		model.addAttribute("c", content);
		model.addAttribute("w",writer);
		return "post/write";
	}
	
}
