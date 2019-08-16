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
	
	@Transactional
	@Override
	public void regist(BoardVO board)throws Exception{
		
		/*
		 * 게시물 등록 시, 게시물을 먼저 만들고 
		 * 첨부파일의 이름의 배열을 이용해서 각각의 파일 이름을 데이터베이스에 추가하는 형식
		 */
		dao.create(board);
		
		String [] files = board.getFiles();
		
		if(files == null) {
			return;
		}
		
		for(String fileName: files) {
			dao.addAttach(fileName);
		}
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
	
	@Transactional
	@Override
	public void modify(BoardVO board) throws Exception{
		/*
		 * 1. 원래 게시물 수정 
		 * 2. 기존 첨부파일 목록 삭제 
		 * 3. 새로운 첨부파일 정보 입력
		 */
		dao.update(board);
		
		Integer bno = board.getBno();
		
		dao.delAttach(bno);
		
		String[] files = board.getFiles();
		
		if(files == null) return;
		
		for(String fileName : files) {
			dao.replaceAttach(fileName, bno);
		}
	}
	
	@Transactional
	@Override
	public void remove(Integer bno) throws Exception{
		/*
		 * 1. 첨부파일 먼저 삭제해야한다. 
		 * 2. 게시글 삭제한다.
		 * 
		 */	
		dao.delAttach(bno);
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
	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		
		return dao.getAttach(bno);
	}
}
