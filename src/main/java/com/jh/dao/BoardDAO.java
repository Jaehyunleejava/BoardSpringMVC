package com.jh.dao;

import java.util.List;

import com.jh.vo.BoardVO;
import com.jh.vo.Criteria;
import com.jh.vo.SearchCriteria;

public interface BoardDAO {
	
	//게시글 생성
	public void create(BoardVO vo) throws Exception;
	
	//특정 게시글 읽어오기 
	public BoardVO read(Integer bno) throws Exception;
	
	//게시글 수정
	public void update(BoardVO vo) throws Exception;
	
	//게시글 삭제
	public void delete(Integer bno) throws Exception;
	
	//전체 게시글 불러오기
	public List<BoardVO> listAll() throws Exception;
	
	//페이징 처리기능
	public List<BoardVO> listPage(int page)throws Exception;
	
	//사용자 정의 페이지처리 기능
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;

	//페이지 갯수 카운팅
	public int countPaging(Criteria cri) throws Exception;
	
	//검색 동적SQL 목록list
	public List<BoardVO> listSearch(SearchCriteria cri)throws Exception;
	
	
	//검색하는 동적SQL 목록 개수
	public int listSearchCount(SearchCriteria cri)throws Exception;
	
	//댓글의 숫자를 변경하는 메서드
	public void updateReplyCnt(Integer bno, int amount)throws Exception;
	
	//게시물 조회수 증가 메서드
	public void updateViewCnt(Integer bno) throws Exception;
	
	//첨부파일 정보를 저장하는 기능
	public void addAttach(String fullName)throws Exception;
	
	//특정 게시물의 첨부파일을 시간 순서대로 가져오는 기능
	public List<String> getAttach(Integer bno)throws Exception;
	
	//게시물 수정시 업로드한 파일 삭제하는 기능
	public void delAttach(Integer bno)throws Exception;
	
	//게시물 수정시 업로드한 파일 수정하는 기능
	public void replaceAttach(String fullName, Integer bno)throws Exception;
}
