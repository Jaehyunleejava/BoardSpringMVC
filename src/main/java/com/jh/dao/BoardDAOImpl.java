package com.jh.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.jh.vo.BoardVO;
import com.jh.vo.Criteria;
import com.jh.vo.SearchCriteria;

//DAO객체에 사용!!!
@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	private SqlSession session;
	
	private	static String namespace = "com.jh.mapper.BoardMapper";
	
	//게시글 생성
	@Override
	public void create(BoardVO vo) throws Exception{
		session.insert(namespace+".create",vo);
	}
	
	//게시글 상세보기
	@Override
	public BoardVO read(Integer bno) throws Exception{
		return session.selectOne(namespace+".read",bno);
	}
	
	//게시글 수정
	@Override
	public void update(BoardVO vo) throws Exception{
		session.update(namespace+".update",vo);
	}
	
	//게시글 삭제
	@Override
	public void delete(Integer bno) throws Exception{
		session.delete(namespace+".delete",bno);
	}
	
	//게시글 전체 불러오기
	@Override
	public List<BoardVO> listAll() throws Exception{
		return session.selectList(namespace+".listAll");
	}
	
	//게시글 페이징 처리 기능
	@Override
	public List<BoardVO> listPage(int page) throws Exception{
		if(page <= 0) {
			page=1;
		}
		
		page = (page-1)*10;
		
		return session.selectList(namespace+".listPage",page);
	}
	
	//게시글 페이징 사용자 정의
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		
		return session.selectList(namespace+".listCriteria",cri);
	}
	
	//게시글 페이징 전체 데이터 갯수 Counting
	@Override
	public int countPaging(Criteria cri) throws Exception {
		
		return session.selectOne(namespace+".countPaging",cri);
	
	}
	
	//검색 동적 SQL 목록list
	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		
		return session.selectList(namespace+".listSearch",cri);
	}
	
	
	//검색하는 동적 SQL 목록 개수
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		
		return session.selectOne(namespace+".listSearchCount",cri);
	}
	
	@Override
	public void updateReplyCnt(Integer bno, int amount) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("bno", bno);
		paramMap.put("amount",amount);
		
		session.update(namespace+".updateReplyCnt", paramMap);
	}
	
	@Override
	public void updateViewCnt(Integer bno) throws Exception {
		
		session.update(namespace+".updateViewCnt", bno);
	}
	
	@Override
	public void addAttach(String fullName) throws Exception {
		session.insert(namespace+".addAttach",fullName);
		
	}
	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		
		return session.selectList(namespace+".getAttach",bno);
	}
	@Override
	public void delAttach(Integer bno) throws Exception {
		
		session.delete(namespace+".deleteAttach",bno);
	}
	@Override
	public void replaceAttach(String fullName, Integer bno) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("fullName",fullName);
		paramMap.put("bno",bno);
		session.insert(namespace+".replaceAttach", paramMap);
	}
	
}
