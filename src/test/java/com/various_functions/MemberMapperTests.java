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

		memberDto.setLoginId("test");
		memberDto.setMemberPw("1234");
		memberDto.setMemberName("테스트로 넣은 멤버데이터");
		memberDto.setMemberMail("peekaboo32@naver.com");
		memberDto.setBirthday(LocalDate.of(1992, 04, 24));
		memberDto.setMemberAddr1("120202-120");
		memberDto.setMemberAddr2("부산광역시");
		memberDto.setMemberAddr3("아파트 1동 ");
		memberDto.setGender(Gender.F);
		memberDto.setAdminCk(0);
		memberDto.setMoney(100);;
	
		memberMapper.save(memberDto);
	}	
	
//	@Test
//	public void memberList() {
//		
//		List<MemberDto> members = memberMapper.findAll();
//		log.info("전체 회원수는 : " +  members.size() + "명 입니다.");
//	}
}
