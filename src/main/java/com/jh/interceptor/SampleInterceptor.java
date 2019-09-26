package com.jh.interceptor;

import java.lang.reflect.Method;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter{
	
	//인터셉터를 이용해서 HttpSession에 로그인이 필요한 객체를 보관하는 형태로 작성하면 controller에서 직접 HttpSession 등의 API를 사용하지 않아도됌
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("post handle......");
		
		Object result = modelAndView.getModel().get("result");
		
		if(result != null) {
			request.getSession().setAttribute("result", result);
			response.sendRedirect("/doA");
		}
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//파라미터 handler는 현재 실행하려는 메소드 자체를 의미, 이를 활용하면 현재 실행되는 컨트롤러를 파악하거나 추가적인 메소드를 실행하는 등 작업가능
		
		System.out.println("pre handle........");
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		System.out.println("Bean: " + method.getBean());
		System.out.println("Method: " + methodObj);
		return true;
	}

}
