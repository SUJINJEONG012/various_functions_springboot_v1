package com.various_functions;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.various_functions.dto.MemberDto;
import com.various_functions.mapper.MemberMapper;
import com.various_functions.vo.Gender;
import com.various_functions.vo.MemberVo;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
public class MemberMapperTests {

	// 생성자 주입
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//회원 생성 테스트  성공
	@Test
	void memberSave() {

		MemberDto memberDto = new MemberDto();

		memberDto.setLoginId("test1");
		memberDto.setMemberPw("1234");
		memberDto.setMemberName("테스트로");
		memberDto.setMemberMail("peekaboo32@naver.com");
		memberDto.setBirthday(LocalDate.of(1992, 04, 24));
		memberDto.setMemberAddr1("테스트");
		memberDto.setMemberAddr2("테스트");
		memberDto.setMemberAddr3("테스트");
		memberDto.setGender(Gender.F);
	
		// 입력된 비밀번호를 해시화하여 저장
        String encodedPassword = passwordEncoder.encode(memberDto.getMemberPw());
        memberDto.setMemberPw(encodedPassword);
        
		memberMapper.save(memberDto);
		
		List<MemberVo> members= memberMapper.findAll();
		System.out.println("전체 회원은 : " + members.size() + "명 입니다.");
	}
	
	// 테이블의 uk findByLoginId  조건 게시글 조회하는
//	@Test
//	public void findByLoginId() {
//		MemberVo member = memberMapper.findByLoginId("admin");
//		try {
//			String memberJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(member);
//			System.out.println(memberJson);
//		}catch(JsonProcessingException e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	// 회원수정 테스트
//	@Test
//	void update() {
//		// 1. 게시글 수정
//		MemberDto memberDto = new MemberDto();
//	
//		memberDto.setMemberId(5L);
//		memberDto.setLoginId("test2222");
//		memberDto.setMemberName("dd");
//		memberDto.setMemberPw("12341234");
//		memberDto.setMemberMail("12341234");
//		memberDto.setMemberAddr1("12341234");
//		memberDto.setMemberAddr2("12341234");
//		memberDto.setMemberAddr3("12341234");
//		
//		memberMapper.update(memberDto);
//	}
	
	// 회원삭제 테스트
//	@Test
//	void delete() {
//	
//		 System.out.println("삭제 이전의 전체 게시글 개수는 : " + memberMapper.findAll().size() + "개입니다.");
//		 memberMapper.deleteById(5L);
//	     System.out.println("삭제 이후의 전체 게시글 개수는 : " + memberMapper.findAll().size() + "개입니다.");
//	}
	
}
