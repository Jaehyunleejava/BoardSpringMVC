package com.jh.dao;

import java.util.List;

import com.jh.vo.Criteria;
import com.jh.vo.ReplyVO;

public interface ReplyDAO {
	
	public List<ReplyVO> list(Integer bno) throws Exception;
	
	public void create(ReplyVO vo)throws Exception;
	
	public void update(ReplyVO vo)throws Exception;
	
	public void delete(Integer rno)throws Exception;
	
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;
	
	public int count(Integer bno) throws Exception;
	
	//댓글이 삭제될 때 해당 게시물의 번호를 알아내는 기능
	public int getBno(Integer rno) throws Exception;
}
