package com.various_functions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.various_functions.admin.service.NoticeService;
import com.various_functions.admin.vo.NoticeRequest;

@SpringBootTest
public class NoticeServiceTest {

	@Autowired
	NoticeService postService;
	
//	@Test
//	void save(){
//		PostRequest params = new PostRequest();		
//		params.setTitle("1번 게시글 제목");	
//        params.setContent("1번 게시글 내용");
//        params.setWriter("테스터");
//        params.setNoticeYn(false);
//        Long id = postService.savePost(params);
//        System.out.println("생성된 게시글 ID : " + id);
//	}
	
	@Test
	public void saveByForEach() {
		for(int i = 1; i <= 500; i++) {
			NoticeRequest params = new NoticeRequest();
			params.setTitle(i+"번 게시글 제목");
			params.setContent(i+"번 게시글 내용");
			params.setWriter("테스터 " + i);
			params.setNoticeYn(false);
			postService.savePost(params);
		}
	}
}
