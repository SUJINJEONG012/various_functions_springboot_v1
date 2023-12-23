package com.various_functions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.various_functions.domain.CommentRequest;
import com.various_functions.service.CommentService;

@SpringBootTest
public class CommentServiceTest {

	@Autowired
	CommentService commentService;
	
//	@Test    
//	void save() {        
//		CommentRequest params = new CommentRequest();        
//		params.setPostId(1001L);        
//		params.setContent("1번 댓글 내용");        
//		params.setWriter("테스터");        
//		Long id = commentService.saveComment(params);        
//		System.out.println("생성된 댓글 ID : " + id);    }
	
	@Test
	public void saveByForeach() {
		for(int i = 1; i<=100; i++) {
			CommentRequest params = new CommentRequest();
			params.setPostId(1001L);        
			params.setContent(i+"번 댓글 내용");			
			params.setWriter("테스터"+i);			
			commentService.saveComment(params);
			
		}
	}
	
}
