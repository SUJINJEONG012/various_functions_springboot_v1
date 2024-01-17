package com.various_functions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.various_functions.mapper.MemberMapper;
import com.various_functions.vo.MemberVo;


@SpringBootTest
public class MemberMapperTests {

	// 생성자 주입
	@Autowired
	private MemberMapper memberMapper;
	
	//회원 생성 테스트  성공
//	@Test
//	void memberSave() {
//
//		MemberDto memberDto = new MemberDto();
//
//		memberDto.setLoginId("test2");
//		memberDto.setMemberPw("1234");
//		memberDto.setMemberName("테스트로");
//		memberDto.setMemberMail("peekaboo32@naver.com");
//		memberDto.setBirthday(LocalDate.of(1992, 04, 24));
//		memberDto.setMemberAddr1("테스트");
//		memberDto.setMemberAddr2("테스트");
//		memberDto.setMemberAddr3("테스트");
//		memberDto.setGender(Gender.F);
//	
//		memberMapper.save(memberDto);
//		
//		List<MemberVo> members= memberMapper.findAll();
//		System.out.println("전체 회원은 : " + members.size() + "명 입니다.");
//	}
	
	// 테이블의 uk findByLoginId  조건 게시글 조회하는
	@Test
	public void findByLoginId() {
		MemberVo member = memberMapper.findByLoginId("admin");
		try {
			String memberJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(member);
			System.out.println(memberJson);
		}catch(JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
