package com.various_functions.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

//관점 (Aspect) 공통적으로 적용될 기능 의미 

@Slf4j
@Aspect
@Component
public class LoggerAspect {

//	@Around("execution(* com.various_functions..*Controller.*(..)) || execution(* com.various_functions..*Service.*(..)) || execution(* com.various_functions..*Mapper.*(..))")
//	public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable{
//		String name= joinPoint.getSignature().getDeclaringTypeName();
//		String type=
//				StringUtils.contains(name, "Controller") ? "Controller ===> " :
//				StringUtils.contains(name, "Service") ? "Service ===> " :
//				StringUtils.contains(name, "Mapper") ? "Mapper ===> ":	
//				"";
//		log.debug("@@ type:: " + type);
//		log.debug("@@ name:: " + name);
//		log.debug("@@ joinPoint.getSignature():: " + joinPoint.getSignature());
//		log.debug("@@ joinPoint.getSignature().getName():: " + joinPoint.getSignature().getName());
//		
//		log.debug("전체적인 합 : " +type + name + "." + joinPoint.getSignature().getName() + "()");
//		
//		return joinPoint.proceed();
//	}
}
