package com.jh.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jh.dao.MessageDAO;
import com.jh.dao.PointDAO;
import com.jh.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {

	@Inject
	private MessageDAO messageDAO;
	
	@Inject
	private PointDAO pointDAO;
	
	@Transactional
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		/*
		 * 메세지 보낸 사람에게 10점을 추가해준다.
		 */
		messageDAO.create(vo);
		pointDAO.updatePoint(vo.getSender(), 10);
		
	}
	
	@Override
	public MessageVO readMessage(String uid, Integer mid) throws Exception {
		/*
		 * 메세지를 읽으면 메세지 상태가 변경되어야하고 
		 * 메세지를 본 사람의 포인트가 5점 증가한다. 
		 * 모든 작업후 메세지 반환
		 */
		messageDAO.updateState(mid);
		pointDAO.updatePoint(uid, 5);
		
		return messageDAO.readMessage(mid);
	}
	

}
