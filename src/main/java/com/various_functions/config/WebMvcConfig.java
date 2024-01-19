package com.various_functions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.various_functions.interceptor.LoggerInterceptor;
import com.various_functions.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	//메서드 안에 파라피터는 InterceptorRegistry registry,
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor())
		.excludePathPatterns("/css/**", "/images/**", "/js/**");
	
		registry.addInterceptor(new LoginCheckInterceptor())
        .addPathPatterns("/post/**")
        .excludePathPatterns("/log*");
	
	}
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry){
	    registry.addResourceHandler("/**")
	            .addResourceLocations("classpath:/templates/", "classpath:/static/");

	}
	
	@Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        return converter;
    }

}


