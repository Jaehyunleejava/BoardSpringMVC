package com.jh.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.jh.dto.LoginDTO;
import com.jh.service.UserService;
import com.jh.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	/*
	 * 인터셉터 처리 순서 
	 * 1. interceptor의 prehandle 호출 -> true 이면 컨트롤러 메소드 호출
	 * 2. controller의 메소드 실행
	 * 3. interceptor의 posthandle처리
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private UserService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
		
	}
	
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpSession session, Model model)throws Exception{
		UserVO vo = service.login(dto);
		
		if(vo == null) {
			return;
		}
		
		model.addAttribute("userVO",vo);
		
		if(dto.isUseCookie()) {
			
			int amount = 60 * 60 * 24 * 7;//1주일
			
			Date sessionLimit = new Date(System.currentTimeMillis()+(1000*amount));
			
			service.keepLogin(vo.getUid(), session.getId(), sessionLimit);
		}
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		Object obj = session.getAttribute("login");
		
		if(obj != null) {
			UserVO vo = (UserVO) obj;
			
			session.removeAttribute("login");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(vo.getUid(), session.getId(), new Date());
			}
		}
		return "user/logout";
	}
	@RequestMapping(value="joinPage", method=RequestMethod.GET)
	public void joinGET() {
		
	}
	
	@RequestMapping(value="/joinUs", method=RequestMethod.POST)
	public String joinPOST(UserVO vo, Model model)throws Exception{
		logger.info("joinUs post..........");
		logger.info(vo.toString());
		
		service.joinUs(vo.getUid(), vo.getUpw(), vo.getUname());
		model.addAttribute("userVO", vo);
		
		return "user/joinSuccess";
	}
	
	@ResponseBody
	@RequestMapping(value="/idCheck", method=RequestMethod.POST)
	public int idCheckPost(HttpServletRequest req)throws Exception{
		logger.info("idCheck POST.......");
		
		String uid = req.getParameter("uid");
		UserVO userVO = service.idCheck(uid);
		
		int result = 0;
		
		if(userVO != null) {
			return 1;
		} 
		
		return result;
	}
}
