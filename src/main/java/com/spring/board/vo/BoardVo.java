package com.spring.board.vo;

public class BoardVo {
	
	// 멤버변수 & 필드
	private String 	boardType;
	private int 	boardNum;
	private String 	boardTitle;
	private String 	boardComment;
	private String 	creator;
	private String	modifier;
	private int 	totalCnt;
	
	// resultMap join 을 위해 추가
	// mapper의 collection의 property가 되는 것
	// 이것도 마찬가지로 getter, setter를 만들어줘야함 (왜냐면 값을 넣어야 하니까)
	private ComCodeVo comCodeVo;

	// getter, setter (단축키 : alt + shift + s)
	public int getTotalCnt() {
		return totalCnt;
	}
	
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;		// this.totalcnt = 12번째 줄의 private int totalCnt
	}
	
	public String getBoardType() {
		return boardType;
	}
	
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	
	public int getBoardNum() {
		return boardNum;
	}
	
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	
	public String getBoardTitle() {
		return boardTitle;
	}
	
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	
	public String getBoardComment() {
		return boardComment;
	}
	
	public void setBoardComment(String boardComment) {
		this.boardComment = boardComment;
	}
	
	public String getCreator() {
		return creator;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public String getModifier() {
		return modifier;
	}
	
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public ComCodeVo getComCodeVo() {
		return comCodeVo;
	}

	public void setComCodeVo(ComCodeVo comCodeVo) {
		this.comCodeVo = comCodeVo;
	}
	
	
}	// class end
