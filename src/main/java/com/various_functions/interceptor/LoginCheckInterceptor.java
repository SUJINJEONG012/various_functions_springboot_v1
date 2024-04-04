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
			if(request.getRequestURI().startsWith("/inquiry")) {
				if(member == null) {
					response.sendRedirect("/member/login");
					return false;
				}
			}
			
			response.sendRedirect("/member/login");
			return true;
		}
		// 3. 추가 조건체크 (예: 특정역할이나 권한이 있는지 확인)	
		if(request.getRequestURI().startsWith("/admin")) {
			if (!member.isAdmin()) {
		        session.setAttribute("errorMessage", "관리자만 접근할 수 있는 페이지입니다.");
		        response.sendRedirect("/");
		        return false;
		    }
		}
		// 어드민페이지 이외의 모든 페이지에 대한 접근 허용
		return true;
	}

}
