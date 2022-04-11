package com.spring.board.vo;

public class PageVo {
	
	// 멤버변수 & 필드
	private int pageNo = 0;
	
	private ComCodeVo comCodeVo;
	private String[] checkNo;	// name(key로 구별)이 달라야 하니까 배열로 받음 (map X)
	
	// getter, setter
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public ComCodeVo getComCodeVo() {
		return comCodeVo;
	}
	public void setComCodeVo(ComCodeVo comCodeVo) {
		this.comCodeVo = comCodeVo;
	}

	public String[] getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String[] checkNo) {
		this.checkNo = checkNo;
	}
	
}
