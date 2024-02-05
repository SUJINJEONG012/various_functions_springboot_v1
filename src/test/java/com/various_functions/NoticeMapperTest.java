package com.various_functions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.various_functions.admin.dto.NoticeDto;
import com.various_functions.admin.mapper.NoticeMapper;
import com.various_functions.admin.vo.NoticeVo;

@SpringBootTest
public class NoticeMapperTest {

	@Autowired
	private NoticeMapper noticeMapper;
	
//	@Test
//	 void save() {
//		NoticeDto params = new NoticeDto();
//		params.setTitle("1번 게시글 제목");
//		params.setContent("1번 게시글 내용");
//		params.setWriter("안젤라");
//		params.setNoticeYn(false);
//		
//		noticeMapper.noticeSave(params);
//			
//		List<NoticeVo> notices = noticeMapper.findAllMembers();
//		System.out.println("전체 게시글 개수는 : " + notices.size() + "개 입니다.");
//	}

	
//	@Test
//	void findById() {
//		NoticeVo post = noticeMapper.findById(1L);
//		try {
//			String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
//			System.out.println(postJson);
//		}catch(JsonProcessingException e) {
//			throw new RuntimeException(e);
//		}
//	}
	
//	@Test
//	void update() {
//		//1. 게시글 수정
//		NoticeDto params = new NoticeDto();
//		params.setId(1L);
//		params.setTitle("1번 게시글 제목 수정 ");
//		params.setContent("1번 게시글 내용 수정");
//		params.setWriter("안젤라2");
//		params.setNoticeYn(true);
//		noticeMapper.update(params);
//		
//		//2. 게시글 상세정보 조회
//		NoticeVo post= noticeMapper.findById(1L);
//		try {
//			String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
//			System.out.println(postJson);
//		}catch(JsonProcessingException e) {
//			throw new RuntimeException(e);
//		}
//	}
	
//	@Test
//	void delete() {
//		System.out.println("삭제 이전의 전체 게시글 개수는 : " + noticeMapper.findAllMembers().size() + "개 입니다.");
//		noticeMapper.deleteById(1L);
//		System.out.println("삭제 이후의 전체 게시글 개수는 : " + noticeMapper.findAllMembers().size() + "개 입니다.");		
//	}
}

