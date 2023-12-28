package com.various_functions.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.various_functions.domain.PostRequest;
import com.various_functions.domain.PostResponse;
import com.various_functions.dto.FileDto;
import com.various_functions.dto.FileUtils;
import com.various_functions.dto.MessageDto;
import com.various_functions.dto.PagingResponse;
import com.various_functions.dto.SearchDto;
import com.various_functions.service.FileService;
import com.various_functions.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	private final FileService fileService;
	private final FileUtils fileUtils;
	
	 
    // 사용자에게 메세지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
    	model.addAttribute("params", params);
    	return "common/messageRedirect";
    }
    
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
    public String savePost(final PostRequest params, Model model) {
    	log.info("신규게시글 올라오는 지 확");
    	Long id = postService.savePost(params);
        List<FileDto> files = fileUtils.uploadFiles(params.getFiles());
        fileService.saveFiles(id, files); //업로드 된 파일정보를 db에 저장
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }
   
    //게시글 리스트 페이지
    @GetMapping("/post/list")
    public String openPostList(@ModelAttribute("params") final SearchDto params, Model model) {
    	PagingResponse<PostResponse> response = postService.findAllPost(params);
    	model.addAttribute("response",response);
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
    public String updatePost(final PostRequest params, Model model) {
    	postService.updatePost(params);
    	MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list", RequestMethod.GET, null);
    	return showMessageAndRedirect(message, model);
    }
    
    // 게시글 삭제
    @PostMapping("/post/delete")
   public String deletePost(@RequestParam Long id, final SearchDto queryParams, Model model) {
	   postService.deletePost(id);
	   MessageDto message= new MessageDto("게시글이 삭제되었습니다.","/post/list", RequestMethod.GET, queryParamsToMap(queryParams));
	   return showMessageAndRedirect(message, model);
   }
    
    // 쿼리 스트링 파리미터를 Map에 담아 반환
    private Map<String, Object> queryParamsToMap(final SearchDto queryParams){
    	Map<String, Object> data = new HashMap<>();
    	data.put("page", queryParams.getPage());
    	data.put("recordSize", queryParams.getRecordSize());
    	data.put("pageSize", queryParams.getPageSize());
    	data.put("keyword", queryParams.getKeyword());
    	data.put("searchType", queryParams.getSearchType());
    	return data;
    }
   

    
}
