package com.spring.board.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserVo;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String selectTest() throws Exception {
		String a = sqlSession.selectOne("board.boardList");
		
		return a;
	}
	
	@Override
	public List<BoardVo> selectBoardList(PageVo pageVo) throws Exception {
		return sqlSession.selectList("board.boardList",pageVo);
	}
	
	@Override
	public int selectBoardCnt(PageVo pageVo) throws Exception {
		return sqlSession.selectOne("board.boardTotal", pageVo);
	}
	
	@Override
	public BoardVo selectBoard(BoardVo boardVo) throws Exception {
		return sqlSession.selectOne("board.boardView", boardVo);	// boardVo
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		return sqlSession.insert("board.boardInsert", boardVo);
	}
	
	@Override
	public List<ComCodeVo> comCodeList(String codeType) throws Exception {
		return sqlSession.selectList("board.comCodeList", codeType);
	}

	@Override
	public int boardUpdate(BoardVo boardVo) throws Exception {
		return sqlSession.update("board.boardUpdate", boardVo);
	}

	@Override
	public int boardJoin(UserVo userVo) throws Exception {
		return sqlSession.insert("board.boardJoin", userVo);
	}

	@Override
	public int idCheck(UserVo userVo) throws Exception {
		return sqlSession.selectOne("board.idCheck", userVo);
	}
	
	@Override
	public UserVo boardLogin(UserVo userVo) throws Exception {
		return sqlSession.selectOne("board.boardLogin", userVo);
	}

	@Override
	public int boardDel(BoardVo boardVo) throws Exception {
		return sqlSession.delete("board.boardDel", boardVo);
	}


	
}	// class end
