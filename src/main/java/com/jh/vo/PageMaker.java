package com.jh.vo;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	
	private int totalCount; //총 데이터 개수
	private int startPage;  // 누르는 보여지는 시작페이지
	private int endPage; // 누르는 보여지는 마지막페이지 
	private boolean prev; // 이전페이지 링크
	private boolean next; // 이후페이지 링크
	
	private int displayPageNum = 10; //보여지는 페이지 수
	
	private Criteria cri;
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
	}


	public int getStartPage() {
		return startPage;
	}



	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}



	public int getEndPage() {
		return endPage;
	}



	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}



	public boolean isPrev() {
		return prev;
	}



	public void setPrev(boolean prev) {
		this.prev = prev;
	}



	public boolean isNext() {
		return next;
	}



	public void setNext(boolean next) {
		this.next = next;
	}



	public int getDisplayPageNum() {
		return displayPageNum;
	}



	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}



	public Criteria getCri() {
		return cri;
	}



	public void setCri(Criteria cri) {
		this.cri = cri;
	}



	private void calcData() {
		
		//예를들어, 현재페이지가 3페이지이고 displayPageNum 10개를 보여줄수 있다.
		//endPage는  ceil(3/10) * 10 = 10이다. 
		endPage = (int)(Math.ceil(cri.getPage() / (double) displayPageNum)* displayPageNum);
		
		startPage = (endPage - displayPageNum) +1;
		
		//총 마지막 보여지는 페이지 버튼링크
		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum())); //여기서 perPageNum은 10이다.
		
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		
		//9(현재페이지) * 10(보여지는 페이지) >= 122(총 데이터 개수) 이면  다음링크 없음.
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}
	public String makeQuery(int page) {
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page",page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		
		return uriComponents.toUriString();
	}
	
	public String makeSearch(int page) {
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page",page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria)cri).getSearchType())
				.queryParam("keyword", ((SearchCriteria)cri).getKeyword())
				.build();
		
		return uriComponents.toUriString();
	}
	
	
}	
