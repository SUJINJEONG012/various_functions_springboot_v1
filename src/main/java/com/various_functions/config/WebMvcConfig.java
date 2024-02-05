package com.various_functions.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.various_functions.interceptor.LoggerInterceptor;
import com.various_functions.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	//메서드 안에 파라피터는 InterceptorRegistry registry,
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 로그 출력
		 registry.addInterceptor(new LoggerInterceptor())
         		 .excludePathPatterns("/css/**", "/images/**", "/js/**");

		 // LoginCheckInterceptor 로그인 여부 체크하는 역할
		 registry.addInterceptor(new LoginCheckInterceptor())
         		 .addPathPatterns("/notice/*", "/example/*")
         		 .excludePathPatterns("/log*");
	}
	
}


