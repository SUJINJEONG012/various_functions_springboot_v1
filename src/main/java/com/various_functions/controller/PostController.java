package com.various_functions.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.various_functions.domain.PostRequest;
import com.various_functions.domain.PostResponse;
import com.various_functions.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	
	/*게시글 작성 페이지
	 * @RequestParam 화면에서 보낸 파라미터를 전달받는데 사용
	 * 
	 * */
	@GetMapping("/post/write")
	public String openPostWrite(@RequestParam(value="id", required= false) final Long id, Model model) {
		if(id != null) {
			PostResponse post = postService.findPostById(id);
			model.addAttribute("post", post);
		}
		return "post/write";
	}
	
	
	// 신규 게시글 생성
    @PostMapping("/post/save")
    public String savePost(final PostRequest params) {
        postService.savePost(params);
        return "redirect:/post/list";
    }
    
    //게시글 리스트 페이지
    @GetMapping("/post/list")
    public String openPostList(Model model) {
    	List<PostResponse> posts = postService.findAllPost();
    	model.addAttribute("posts",posts);
    	return "post/list";
    }
    
    /*
     * 게시글 상세 페이지
     * @RequestParam : HttpServletRequest에서 getParameter의 결과를 파라미터로 전달해달라는 의미
     * */
    @GetMapping("/post/view")
    public String openPostView(@RequestParam final Long id, Model model) {
    	PostResponse post = postService.findPostById(id);
    	model.addAttribute("post",post);
    	return "post/view";
    }
    
    // 기존 게시글 수정
    @PostMapping("/post/update")
    public String updatePost(final PostRequest params) {
    	postService.updatePost(params);
    	return "/redirect:/post/list";
    }

    
}
