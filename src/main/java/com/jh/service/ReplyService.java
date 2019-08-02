package com.jh.service;

import java.util.List;

import com.jh.vo.ReplyVO;

public interface ReplyService {
	
	public void addReply(ReplyVO vo)throws Exception;
	
	public List<ReplyVO> listReply(Integer bno) throws Exception;
	
	public void modifyReply(ReplyVO vo)throws Exception;
	
	public void removeReply(Integer rno)throws Exception;
	
}
