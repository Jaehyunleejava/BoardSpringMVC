package com.jh.vo;

//파라미터 2개로 전달할수도 있는데 파라미터가 여러개면 관리가 어려워지기 때문에 아예 클래스로 만들어서 객체로 처리
//DAO 처리를 도와줄 Criteria 클래스
public class Criteria {

	private int page;
	private int perPageNum;
	
	public Criteria() {
		this.page =1;
		this.perPageNum = 10;
	}
	
	public void setPage(int page) {
		if(page <= 0){
			this.page = 1;
			return;
		}
		this.page = page;
	}
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		return page;
	}
	
	//method for MyBatis SQL Mapper-
	public int getPageStart() {
		//ex) 3페이지경우 10개씩 출력한다의 경우 -> limit 20,10 형태!
		return (this.page-1) * perPageNum;
	}
	//method for MyBatis SQL Mapper-
	public int getPerPageNum() {
		return this.perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page="+page+", "+"perPageNum="+perPageNum+"]";
	}
}




