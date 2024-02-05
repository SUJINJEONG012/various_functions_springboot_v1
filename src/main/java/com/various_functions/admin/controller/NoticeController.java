package com.various_functions.admin.controller;


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

import com.various_functions.admin.service.NoticeService;
import com.various_functions.admin.vo.NoticeRequest;
import com.various_functions.admin.vo.NoticeResponse;
import com.various_functions.dto.FileDto;
import com.various_functions.dto.FileUtils;
import com.various_functions.dto.MessageDto;
import com.various_functions.dto.PagingResponse;
import com.various_functions.dto.SearchDto;
import com.various_functions.service.FileService;
import com.various_functions.vo.FileEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService postService;
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
	@GetMapping("/admin/notice/write")
	public String openPostWrite(@RequestParam(value="memberId", required= false) final Long id, Model model) {
		if(id != null) {
			NoticeResponse post = postService.findPostById(id);
			model.addAttribute("post", post);
		}
		return "/admin/notice/write";
	}
	
	
	// 신규 게시글 생성
    @PostMapping("/admin/post/save")
    public String savePost(final NoticeRequest params, Model model) {
    	log.info("신규게시글 올라오는 지 확");
    	Long id = postService.savePost(params);
        List<FileDto> files = fileUtils.uploadFiles(params.getFiles());
        fileService.saveFiles(id, files); //업로드 된 파일정보를 db에 저장
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }
   
    //게시글 리스트 페이지
    @GetMapping("/admin/notice/list")
    public String openPostList(@ModelAttribute("params") final SearchDto params, Model model) {
    	PagingResponse<NoticeResponse> response = postService.findAllPost(params);
    	model.addAttribute("response",response);
    	return "admin/notice/list";
    }
    
    /*
     * 게시글 상세 페이지
     * @RequestParam : HttpServletRequest에서 getParameter의 결과를 파라미터로 전달해달라는 의미
     * */
    @GetMapping("/admin/notice/view")
    public String openPostView(@RequestParam final Long id, Model model) {
    	NoticeResponse post = postService.findPostById(id);
    	model.addAttribute("post",post);
    	return "admin/notice/view";
    }
    
    // 기존 게시글 수정
    @PostMapping("/admin/notice/update")
    public String updatePost(final NoticeRequest params, final SearchDto queryParams, Model model) {
    	
    	// 1. 게시글 정보수정
    	postService.updatePost(params);
    	
    	// 2. 파일업로드
    	List<FileDto> uploadFiles = fileUtils.uploadFiles(params.getFiles());
    	
    	// 3. 파일 정보 저장
    	fileService.saveFiles(params.getId(), uploadFiles);
    	
    	// 4. 삭제할 파일 정보 조회
    	List<FileEntity> deleteFiles = fileService.findAllFileByIds(params.getRemoveFileIds());
    	
    	// 5. 파일 삭제
    	fileUtils.deleteFiles(deleteFiles);
    	
    	// 6. 파일 삭제
    	fileService.deleteAllFileByIds(params.getRemoveFileIds());
    	
    	MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list", RequestMethod.GET, null);
    	return showMessageAndRedirect(message, model);
    }
    
    // 게시글 삭제
    @PostMapping("/admin/notice/delete")
   public String deletePost(@RequestParam Long id, SearchDto queryParams, Model model) {
	   postService.deletePost(id);
	   MessageDto message= new MessageDto("게시글이 삭제되었습니다.","/notice/list", RequestMethod.GET, queryParamsToMap(queryParams));
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
