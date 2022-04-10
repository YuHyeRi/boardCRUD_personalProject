package com.spring.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserVo;

@Service
public class boardServiceImpl implements boardService {
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public String selectTest() throws Exception {
		return boardDao.selectTest();
	}
	
	@Override
	public List<BoardVo> selectBoardList(PageVo pageVo) throws Exception {
		return boardDao.selectBoardList(pageVo);
	}
	
	@Override
	public int selectBoardCnt(PageVo pageVo) throws Exception {
		return boardDao.selectBoardCnt(pageVo);
	}
	
	@Override
	public BoardVo selectBoard(String boardType, int boardNum) throws Exception {
		BoardVo boardVo = new BoardVo();	// Dao 내 인수에 값은 하나만 들어올 수 있어서 객체를 생성해서 두 개의 값을 set 해줌
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.selectBoard(boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		return boardDao.boardInsert(boardVo);
	}

	@Override
	public List<ComCodeVo> comCodeList(String codeType) throws Exception {
		return boardDao.comCodeList(codeType);
	}

	@Override
	public int boardUpdate(BoardVo boardVo) throws Exception {
		return boardDao.boardUpdate(boardVo);
	}

	@Override
	public int boardJoin(UserVo userVo) throws Exception {
		return boardDao.boardJoin(userVo);
	}

	@Override
	public int idCheck(UserVo userVo) throws Exception {
		return boardDao.idCheck(userVo);
	}
	
	@Override
	public UserVo boardLogin(UserVo userVo) throws Exception {
		return boardDao.boardLogin(userVo);
	}

	@Override
	public int boardDel(BoardVo boardVo) throws Exception {
		return boardDao.boardDel(boardVo);
	}

	@Override
	public int pageCnt(PageVo pageVo) throws Exception {
		return boardDao.pageCnt(pageVo);
	}

	
}	// class end
