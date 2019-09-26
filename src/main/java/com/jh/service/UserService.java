package com.jh.service;

import java.util.Date;

import com.jh.dto.LoginDTO;
import com.jh.vo.UserVO;

public interface UserService {

	public UserVO login(LoginDTO dto)throws Exception;
	
	public void keepLogin(String uid, String sessionId, Date next)throws Exception;
	
	public UserVO checkLoginBefore(String value);
	
	public void joinUs(String uid, String upw, String uname)throws Exception;

	public UserVO idCheck(String uid)throws Exception;
}
