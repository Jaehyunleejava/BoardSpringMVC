package com.jh.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jh.vo.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO{
	@Inject
	private SqlSession session;
	
	private String namespace = "com.jh.mapper.MessageMapper";
	
	@Override
	public void create(MessageVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}

	@Override
	public MessageVO readMessage(Integer mid) throws Exception {
		return session.selectOne(namespace + ".readMessage", mid);
	}

	@Override
	public void updateState(Integer mid) throws Exception {
		session.update(namespace + ".updateState", mid);
	}

}
