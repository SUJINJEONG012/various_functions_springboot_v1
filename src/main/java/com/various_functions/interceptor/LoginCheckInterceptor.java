package com.various_functions.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.various_functions.vo.MemberVo;

public class LoginCheckInterceptor implements HandlerInterceptor {

//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
//		
//		// 1.세션에서 회원 정보 조회
//		HttpSession session = request.getSession();
//		MemberVo member = (MemberVo) session.getAttribute("loginMember");
//		
//		// 2. 회원정보 체크
//		if(member == null || member.getDeleteYn() == true) {
//			response.sendRedirect("/member/login");
//			return false;
//		}
//		return HandlerInterceptor.super.preHandle(request, response, handler);
//		
//	}
}
