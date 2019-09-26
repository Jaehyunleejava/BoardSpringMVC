package com.jh.dto;

/*
VO패키지가 있는데 DTO를 따로 만든 이유는
두개다 공통적으로 데이터를 담아서 전달하는 역할을 하지만
VO는 데이터베이스의 데이터를 처리하고
DTO는 화면에서 전달되는 데이터를 처리하기 때문에 나누어 작성했다.
Spring MVC를 이용하는 경우에 DTO 검증을 위한 처리가 들어간다.
*/
public class LoginDTO {
	
	private String uid;
	private String upw;
	private boolean useCookie;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	
}
