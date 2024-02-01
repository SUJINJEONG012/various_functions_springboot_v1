package com.various_functions.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.various_functions.vo.MemberVo;

import lombok.EqualsAndHashCode.CacheStrategy;

public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		
		// 1.세션에서 회원 정보 조회
		// HttpServletRequest를 통해 HttpSession 얻기
		HttpSession session = request.getSession();
		MemberVo member = (MemberVo) session.getAttribute("loginMember");
		
		// 2. 회원정보 체크
		if(member == null || member.getDeleteYn() == true) {
			response.sendRedirect("/member/login");
			return false;
		}
		
		// 3. 추가 조건체크 (예: 특정역할이나 권한이 있는지 확인)
		if(member.getAdminCk() == 1) {
			response.sendRedirect("/admin/dashboard");
			return false;
		}
		 // 4. 부모 클래스의 preHandle 메서드 호출 후에 추가 작업을 수행하고 true 반환
		boolean result = HandlerInterceptor.super.preHandle(request, response, handler);
		
		return result;
	}
}
