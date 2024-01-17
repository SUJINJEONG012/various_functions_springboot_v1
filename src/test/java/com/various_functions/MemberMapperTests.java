package com.various_functions;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.various_functions.dto.MemberDto;
import com.various_functions.mapper.MemberMapper;
import com.various_functions.vo.Gender;


@SpringBootTest
public class MemberMapperTests {

	// 생성자 주입
	@Autowired
	private MemberMapper memberMapper;
	
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
	
		memberMapper.save(memberDto);
	}
	
//	//로그인 테스트
//	@Test
//	public void findByLoginId() throws Exception{
//		MemberVo memberVo = new MemberVo();
//		/* 올바른 아이디 비번일 경우 */
//		memberVo.setLoginId("test");
//		memberVo.setMemberPw("d1234");
//	}
//	@Test
//	public void memberList() {
//		
//		List<MemberDto> members = memberMapper.findAll();
//		log.info("전체 회원수는 : " +  members.size() + "명 입니다.");
//	}
}
