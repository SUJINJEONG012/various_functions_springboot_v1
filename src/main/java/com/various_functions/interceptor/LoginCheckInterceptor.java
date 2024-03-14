package com.various_functions.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.various_functions.controller.InquiryController;
import com.various_functions.vo.MemberVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 1.세션에서 회원 정보 조회
		HttpSession session = request.getSession();
		MemberVo member = (MemberVo) session.getAttribute("loginMember");
		
		
		// 2. 회원정보 체크
		if (member == null || member.getDeleteYn()) {
			response.sendRedirect("/member/login");
			return false;
		}
		
		if(request.getRequestURI().startsWith("/inquiry")) {
			if(member == null) {
				response.sendRedirect("/member/login");
				return false;
			}
		}
		
		// 3. 추가 조건체크 (예: 특정역할이나 권한이 있는지 확인)		
		if (!member.isAdmin()) {
		    if (member.getAdminCk() != 0) { // adminCk가 0이 아닌 경우
		        session.setAttribute("errorMessage", "접근에 제한되었습니다.");
		        response.sendRedirect("/"); // 특정 사용자가 아닌 경우 메인 페이지로 리다이렉트
		        return false;
		    }
		}
		
		// 4. 관리자로 로그인한 경우
		return true;
	}

}
