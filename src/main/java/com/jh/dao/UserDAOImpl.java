package com.jh.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jh.dto.LoginDTO;
import com.jh.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.jh.mapper.UserMapper";
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		
		return session.selectOne(namespace+".login",dto);
	}
	
	@Override
	public void keepLogin(String uid, String sessionId, Date next) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uid", uid);
		paramMap.put("sessionId", sessionId);
		paramMap.put("next", next);
		
		session.update(namespace+".keepLogin",paramMap);
	}
	
	
	@Override
	public UserVO checkUserWithSessionKey(String value) {
		
		return session.selectOne(namespace+".checkUserWithSessionKey",value);
	}
	@Override
	public void joinUs(String uid, String upw, String uname) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("upw", upw);
		paramMap.put("uname", uname);
		
		session.insert(namespace+".joinUs", paramMap);
	}
	
	@Override
	public UserVO idCheck(String uid) throws Exception {
		
		return session.selectOne(namespace+".idCheck",uid);
	}
}
