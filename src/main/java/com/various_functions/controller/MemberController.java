package com.various_functions.controller;


import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.various_functions.admin.service.NoticeService;
import com.various_functions.admin.vo.NoticeVo;
import com.various_functions.dto.MemberDto;
import com.various_functions.service.MemberService;
import com.various_functions.vo.MemberVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	
	private final MemberService memberService;
	private final NoticeService noticeService;
	
	private final JavaMailSender mailSender;
	
	//로그인 페이지 이동
	@GetMapping("/member/login")
	public String openLogin(Model model, HttpSession session) {
//	 메세지를 출력하고싶을때	
//		String errorMessage = (String) session.getAttribute("errorMessage");	
//		if(errorMessage != null) {
//			model.addAttribute("errorMessage", errorMessage);
//			session.removeAttribute("errorMessage");
//		}
		log.info("로그인페이지 진입중");
		return "member/login";
	}
	
	// 로그인 기능 
	@PostMapping("/member/login")
	@ResponseBody
	public MemberVo login(HttpServletRequest request, HttpSession session) {

		// 1. 회원 상세정보 조회
		String loginId = request.getParameter("loginId");
		String memberPw =  request.getParameter("memberPw");		
		MemberVo member = memberService.login(loginId, memberPw);
		
		log.info("member 데이터 들고오는지 확인 :" + member );
		log.info("member.loginId :" + member.getLoginId());
		log.info("member.memberPw :" + memberPw);
		
		// 2. 세션에 회원정보 저장 & 세션 유지시간 설정
		if(member != null) {	
			session.setAttribute("loginMember", member); // 세션에 로그인한 회원 정보 저장
			session.setAttribute("loginMemberName", member.getMemberName());
			log.info("세션에 저장된 회원 정보: " + session.getAttribute("loginMember"));
			session.setMaxInactiveInterval(60*30); // 세션 유효기간 : 30분
			session.setAttribute("isAdmin", member.isAdmin());
		}

		return member;
	}
	
	// 로그아웃 페이지
	@PostMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	// 회원가입 페이지 이동
	@GetMapping("/member/join")
	public String saveMember() {
		log.info("회원가입 페이지 이동!!!!");
		return "member/join";
	}
	
	// 이메일 인증
	@GetMapping("/member/mailCheck")
	@ResponseBody
	public void mailCheckGet(String email) {
		// view 로부터 넘어온 데이터 확인
		log.info("이메일 데이터 전송 확인");
		log.info("인증번호 : " + email);
		
		// 인증번호 (난수) 생성
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		log.info("인증번호 : " + checkNum);
		
		// 이메일 보내기
		String setFrom = "peekaboo32@naver.com";
		String toMail = email;
		String title = "회원가입 인증 이메일입니다. ";
		String content =  " 홈페이지를 방문해주셔서 감사합니다. " + "<br></br>" +
							"인증번호는 " + checkNum + "입니다. " +
							"<br>" + 
							"해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 회원 정보 저장 (회원가입)
    @PostMapping("/member/join")
    @ResponseBody
    public ResponseEntity<String> saveMember(@RequestBody final MemberDto memberDto) {
    	log.info("회원가입 성공여부를 클라이언트에 응답");	
    	Long memberId= memberService.saveMember(memberDto);
    	return ResponseEntity.ok("success!!!!!!!");    	

    }

	// 회원 상세 조회
	@GetMapping("admin/member/{loginId}")
	@ResponseBody
	public MemberVo findMemberById(@PathVariable final String loginId) {
		return memberService.findMemberByLoginId(loginId);
	}
	
	// 회원 리스트 조회
	@GetMapping("admin/member/list")
	public String findAllMember(Model model) {
		List<MemberVo> members = memberService.findAllMember();
		model.addAttribute("members", members);
		return "admin/member/list";
	}
	
	// 회원정보 수정 (마이페이지)
	@GetMapping("/member/mypage")
	public String myPage(HttpSession session, Model model) {
		// 세션에서 현재 사용자 정보가져오기 => getAttribute("loginMember") 세션에 저장되는 속성명은 개발자가 임의로 정할 수 있따.
		MemberVo loginMember = (MemberVo) session.getAttribute("loginMember");
		if(loginMember == null) {
			// 세션이 없는 경우 로그인 페이지로 이동하거나 다른 처리를 수행
			return "redirect://member/login";
		}
		
		// 사용자가 작성한 글 조회 => 현재는 공지사항으로 되어있는걸 나중에 이걸 문의하는 게시판으로 이동
		List<NoticeVo> userNotices = noticeService.findNoticeUserById(loginMember.getLoginId());
		model.addAttribute("userNotices", userNotices);
		return "/member/mypage";
	}

	// 글삭제
//	@PostMapping("/mypage/delete/{postId}")
//	public String deletePost(@PathVariable("postId") Long postId) {
//	    // 글 삭제
//	    boolean deleted = postService.deletePost(postId);
//	    if (deleted) {
//	        return "redirect:/mypage?deleteSuccess=true"; // 삭제 성공 시 마이페이지로 이동
//	    } else {
//	        return "redirect:/mypage?deleteError=true"; // 삭제 실패 시 마이페이지로 이동
//	    }
//	}
	
	// 회원 정보 삭제 (회원탈퇴)
	
	//회원수 카운팅 (id중복체크)
	@GetMapping("/member/member-count")
	@ResponseBody
	public int countMemberByLoginId(@RequestParam final String loginId) {
		return memberService.countMemberByLoginId(loginId);
	}
	
	
}
