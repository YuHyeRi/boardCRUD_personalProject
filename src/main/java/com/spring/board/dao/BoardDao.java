package com.spring.board.dao;

import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserVo;

public interface BoardDao {

	public String selectTest() throws Exception;

	public List<BoardVo> selectBoardList(PageVo pageVo) throws Exception;

	public BoardVo selectBoard(BoardVo boardVo) throws Exception;

	public int selectBoardCnt(PageVo pageVo) throws Exception;

	public int boardInsert(BoardVo boardVo) throws Exception;

	public List<ComCodeVo> comCodeList(String codeType) throws Exception;

	public int boardUpdate(BoardVo boardVo) throws Exception;

	public int boardJoin(UserVo userVo) throws Exception;

	public int idCheck(UserVo userVo) throws Exception;
	
	public UserVo boardLogin(UserVo userVo) throws Exception;

	public int boardDel(BoardVo boardVo) throws Exception;

	public int pageCnt(PageVo pageVo) throws Exception;

}
