package com.jh.dao;

import java.util.Date;

import com.jh.dto.LoginDTO;
import com.jh.vo.UserVO;

public interface UserDAO {
	
	public UserVO login(LoginDTO dto)throws Exception;

	//로그인한 사용자의 sessionkey와 sessionLimit를 업데이트하는 기능
	public void keepLogin(String uid, String sessionId, Date next);
	
	//기록된 값으로 사용자의 정보를 조회하는 기능
	public UserVO checkUserWithSessionKey(String value);
	
	//회원 가입 기능
	public void joinUs(String uid, String upw, String uname)throws Exception;
	
	//회원가입 아이디 중복체크 기능
	public UserVO idCheck(String uid)throws Exception;
}
