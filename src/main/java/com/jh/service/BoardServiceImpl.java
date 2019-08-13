package com.jh.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jh.dao.BoardDAO;
import com.jh.vo.BoardVO;
import com.jh.vo.Criteria;
import com.jh.vo.SearchCriteria;

//service객체에 사용!!!
@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardDAO dao;
	
	@Override
	public void regist(BoardVO board)throws Exception{
		dao.create(board);
	}
	
	@Transactional(isolation=Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(Integer bno) throws Exception{
		
		/*
		 * 게시물을 읽을 때
		 * 1. 게시물 조회(read)를 하면서
		 * 2. 게시물의 조회수(update)를 해야한다.
		 */
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}
	
	@Override
	public void modify(BoardVO board) throws Exception{
		dao.update(board);
	}
	
	@Override
	public void remove(Integer bno) throws Exception{
		dao.delete(bno);
	}
	
	@Override
	public List<BoardVO> listAll() throws Exception{
		return dao.listAll();
	}
	
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}
	
	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		
		return dao.countPaging(cri);
	}
	
	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		
		return dao.listSearch(cri);
	}
	
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		
		return dao.listSearchCount(cri);
	}
}
