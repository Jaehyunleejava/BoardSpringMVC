package com.jh.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jh.dao.BoardDAO;
import com.jh.dao.ReplyDAO;
import com.jh.vo.Criteria;
import com.jh.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Inject
	private ReplyDAO replyDAO;
	
	@Inject
	private BoardDAO boardDAO;
	
	@Transactional
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		
		replyDAO.create(vo);
		boardDAO.updateReplyCnt(vo.getBno(), 1);
	}
	
	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {
		
		return replyDAO.list(bno);
	}
	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		
		replyDAO.update(vo);
	}
	
	@Transactional
	@Override
	public void removeReply(Integer rno) throws Exception {
		
		//트랜젝션처리, 댓글을 삭제 할때 댓글의 갯수가 하나 감소하는 트랜젝션이다.
		//tbl_board 테이블에는 댓글을 등록시 게시물번호에 맞게 댓글의 수를 1증가 시킨다.
		//삭제 할 때 역시 게시물번호에 맞는 댓글의 수를 1감소 시켜야하는데, 여기서 게시물 번호를 replyDAO.getBno(rno)에서 가지고 왔다.
		//어떻게 가능한 것일까..?
		int bno = replyDAO.getBno(rno);
		replyDAO.delete(rno);
		boardDAO.updateReplyCnt(bno, -1);
	}
	
	@Override
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {
		
		return replyDAO.listPage(bno, cri);
	}
	
	@Override
	public int count(Integer bno) throws Exception {
		
		return replyDAO.count(bno);
	}
	
	
}
