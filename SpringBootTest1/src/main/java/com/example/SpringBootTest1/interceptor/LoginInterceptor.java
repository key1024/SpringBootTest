package com.example.SpringBootTest1.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.SpringBootTest1.entity.User;

/**
 * 登录拦截器
 * 
 * @author key
 * @date 2019/04/02
 *
 */
@Component
@Aspect
public class LoginInterceptor {
	
	@Pointcut("within(com.example.SpringBootTest1.comtroller..*) && !within(com.example.SpringBootTest1.controller.admin.LoginController)")
	public void pointCut() {
		
	}
	
	@Around("pointCut()")
	public Object trackInfo(ProceedingJoinPoint joinPoint) throws Throwable {
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		User user = (User)request.getSession().getAttribute("user");
		if(user == null) {
			System.out.println("------------用户未登录-------------");
			attributes.getResponse().sendRedirect("/login");//手动转发到/login路径
		}
		System.out.println("-----------用户已登录--------------");
		
		return joinPoint.proceed();
	}
}
