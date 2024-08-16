package com.various_functions.controller;


import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.various_functions.admin.service.NoticeService;
import com.various_functions.dto.MemberDto;
import com.various_functions.service.InquiryService;
import com.various_functions.service.MemberService;
import com.various_functions.vo.Gender;
import com.various_functions.vo.InquiryVo;
import com.various_functions.vo.KakaoProfile;
import com.various_functions.vo.MemberVo;
import com.various_functions.vo.OAuthToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	
	private final MemberService memberService;
	private final NoticeService noticeService;
	private final InquiryService inquiryService;
	
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
	
	
	private final String KAKAO_CLIENT_ID = "fd9684394aef9b4670cf57a542fb05e8";
    private final String KAKAO_REDIRECT_URI = "http://localhost:8081/auth/kakao/callback";
    
//  //카카오 로그인 
//    @GetMapping("/auth/kakao/callback")
//    public @ResponseBody String kakaoCallback(String code) { //데이터를 리턴해주는 컨트롤러 함수
//
//        // POST방식으로 key=value 데이터를 요청 (카카오쪽으로)
//        RestTemplate rt = new RestTemplate();  // http요청을 쉽게 할 수 있는 라이브러리
//
//        // HttpHeaders 오브젝트 생성
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        // HttpBody 오브젝트 생성
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code"); // 값을 변수화하는게 낫다
//        params.add("client_id", KAKAO_CLIENT_ID);
//        params.add("redirect_uri", KAKAO_REDIRECT_URI);
//        params.add("code", code);
//
//        // HttpHeaders 와 HttpBody 를 하나의 오브젝트에 담기
//        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
//                new HttpEntity<>(params, headers); //바디 데이터와 와 헤더값을 가지는 entity가 된다
//
//        // Http 요청하기 - POST방식으로 그리고 response 변수의 응답 받음
//        ResponseEntity<String> response = rt.exchange(
//                "https://kauth.kakao.com/oauth/token", //토큰 발급 요청 주소
//                HttpMethod.POST, //요청 메서드 post
//                kakaoTokenRequest,
//                String.class // 응답받을 타입
//        );
//        
//      //Gson, Json Simple, ObjectMapper
//      		ObjectMapper objectMapper = new ObjectMapper();
//      		OAuthToken oauthToken = null;
//      		try {
//   			 oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
//   		} catch (JsonMappingException e) {
//   			
//   			e.printStackTrace();
//   		} catch (JsonProcessingException e) {
//   			
//   			e.printStackTrace();
//   		}
//   		
//   		System.out.println("카카오 액세스 토큰 : " + oauthToken.getAccess_token());
//   		
//   		
//   		
//   		RestTemplate rt2 = new RestTemplate();
//   			// HttpHeader 오브젝트 생성
//   			HttpHeaders headers2 = new HttpHeaders();
//   			headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
//   			headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//   			
//   			// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
//   			HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
//
//   			// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음.
//   			ResponseEntity<String> response2 = rt2.exchange(
//   					"https://kapi.kakao.com/v2/user/me",  // 토큰발급주소
//   					HttpMethod.POST,
//   					kakaoProfileRequest2, 
//   					String.class		
//   		    );
//   		
//   			System.out.println("response2.body() : " + response2.getBody());
//   			
//   			
//   			ObjectMapper objectMapper2 = new ObjectMapper();
//   			KakaoProfile kakaoProfile = null;
//   			
//   			try {
//   				kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
//   			} catch (JsonMappingException e) {
//   				e.printStackTrace();
//   			} catch (JsonProcessingException e) {
//   				e.printStackTrace();
//   			}
//   			
//   			//User 오브젝트 : username, password, email
//   			System.out.println(" 카카오아이디(번호) : "+kakaoProfile.getId());
//   			System.out.println(" 블로그서버 유저네임 : " + kakaoProfile.getKakao_account().getEmail()+ "_" + kakaoProfile.getId());
//   			System.out.println(" 블로그서버 이메일 : " + kakaoProfile.getKakao_account().getEmail()+ "_" + kakaoProfile.getId());
//   		    
//   			//UUID 란 :중복되지 않는 어떤특정값을 만들어내는 알고리즘
//   			UUID garbagePassword = UUID.randomUUID(); //랜덤값 
//   		    System.out.println(" 블로그서버 비밀번호 : " + garbagePassword);
//   			
//   		 
// 			  
// 	    //return "redirect:/"; 		
//   		return "카카오 토큰 요청 완료 : 토큰요청에 대한 응답 : " + response;
//    }
    
    
 // 카카오 로그인 콜백 메서드
    @GetMapping("/auth/kakao/callback")
    public ResponseEntity <String> kakaoCallback(String code) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", KAKAO_CLIENT_ID);
        params.add("redirect_uri", KAKAO_REDIRECT_URI);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("카카오 토큰 요청 실패");
        }

        RestTemplate rt2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper.readValue(response2.getBody(), KakaoProfile.class);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("카카오 사용자 정보 요청 실패");
        }

        // 사용자의 이메일과 카카오 ID를 이용해 회원가입 처리
        String memberMail = kakaoProfile.getKakao_account().getEmail();
        String memberName = memberMail + "_" + kakaoProfile.getId();
        UUID garbagePassword = UUID.randomUUID();

        // MemberDto를 빌더 패턴으로 생성
        MemberDto kakaoUser = MemberDto.builder()
            .memberName(memberName)
            .memberMail(memberMail)
            .memberPw(garbagePassword.toString())
            .loginId(kakaoProfile.getId().toString())
            .build();
        
        
        //중복된 login_id 검사 및 회원가입 또는 로그인 처리
        MemberVo existingMember = memberService.findMemberByLoginId(kakaoUser.getLoginId());
        if(existingMember != null) {
        	
            
            return ResponseEntity.ok("이미 등록된 사용자입니다. 로그인 처리 완료");
        }else {
        	// 회원가입 로직 수행
        	Long memberId = memberService.saveMember(kakaoUser);
        	return ResponseEntity.ok("회원가입 및 로그인 처리 완료");
        }
        
     
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
		
		// 사용자가 작성한 글 조회 =>
		List<InquiryVo> memberInquiry = inquiryService.findInquiryMemberById(loginMember.getMemberId());
		model.addAttribute("memberInquiry", memberInquiry);
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
