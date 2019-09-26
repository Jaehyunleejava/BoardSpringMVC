package com.jh.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.jh.dao.UserDAO;
import com.jh.dto.LoginDTO;
import com.jh.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{

	@Inject
	private UserDAO dao;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		
		return dao.login(dto);
	}
	
	@Override
	public void keepLogin(String uid, String sessionId, Date next) throws Exception {
		
		dao.keepLogin(uid, sessionId, next);
	}
	@Override
	public UserVO checkLoginBefore(String value) {
		
		return dao.checkUserWithSessionKey(value);
	}
	@Override
	public void joinUs(String uid, String upw, String uname) throws Exception {
		
		dao.joinUs(uid, upw, uname);
	}
	
	@Override
	public UserVO idCheck(String uid) throws Exception {
		
		return dao.idCheck(uid);
	}
}
