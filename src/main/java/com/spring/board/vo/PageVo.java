package com.spring.board.vo;

public class PageVo {
	
	private int pageNo = 0;
	
	// 추가
	private ComCodeVo comCodeVo;
	
	// 0119 추가
	private String[] checkNo;	// name(key로 구별)이 달라야 하니까 배열로 받음 (map X)
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	// 추가
	public ComCodeVo getComCodeVo() {
		return comCodeVo;
	}
	
	public void setComCodeVo(ComCodeVo comCodeVo) {
		this.comCodeVo = comCodeVo;
	}

	// 0119 추가
	public String[] getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String[] checkNo) {
		this.checkNo = checkNo;
	}
	
}
