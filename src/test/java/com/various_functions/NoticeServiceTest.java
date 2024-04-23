package com.various_functions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.various_functions.admin.dto.NoticeDto;
import com.various_functions.admin.service.NoticeService;

@SpringBootTest
public class NoticeServiceTest {

	@Autowired
	NoticeService noticeServicce;
	
//	@Test
//	void save(){
//		NoticeDto params = new NoticeDto();		
//		params.setTitle("1번 게시글 제목");	
//        params.setContent("1번 게시글 내용");
//        params.setWriter("테스터");
//        params.setNoticeYn(false);
//        Long id = noticeServicce.noticeSave(params);
//        System.out.println("생성된 게시글 ID : " + id);
//	}
	
	
	
	@Test
	public void saveByForEach() {
		for(int i = 1; i <= 1000; i++) {
			NoticeDto params = new NoticeDto();
			params.setTitle(i+"번 페이징처리를 하기위한 게시글 제목");
			params.setContent(i+"번 페이징처리를 하기위한 게시글 게시글 내용");
			params.setWriter("테스터 " + i);
			params.setNoticeYn(false);
			noticeServicce.noticeSave(params);
		}
	}
}
