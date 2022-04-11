package com.spring.board.vo;

public class UserVo {
	
	// 멤버변수 & 필드
	private String userId;
	private String userPw;
	private String userName;
	private String userPhone1;		// 폰 앞 3자리
	private String userPhone2;		// 폰 중간 4자리
	private String userPhone3;		// 폰 끝 4자리
	private String userPostNo;		// xxx-xxxx 형식
	private String userAddress;
	private String userCompany;
	private ComCodeVo comCodeVo;	// 추가
	
	// getter, setter
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPhone1() {
		return userPhone1;
	}
	public void setUserPhone1(String userPhone1) {
		this.userPhone1 = userPhone1;
	}
	
	public String getUserPhone2() {
		return userPhone2;
	}
	public void setUserPhone2(String userPhone2) {
		this.userPhone2 = userPhone2;
	}
	
	public String getUserPhone3() {
		return userPhone3;
	}
	public void setUserPhone3(String userPhone3) {
		this.userPhone3 = userPhone3;
	}
	
	public String getUserPostNo() {
		return userPostNo;
	}
	public void setUserPostNo(String userPostNo) {
		this.userPostNo = userPostNo;
	}
	
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	public String getUserCompany() {
		return userCompany;
	}
	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}
	
	public ComCodeVo getComCodeVo() {
		return comCodeVo;
	}
	public void setComCodeVo(ComCodeVo comCodeVo) {
		this.comCodeVo = comCodeVo;
	}
	
}
