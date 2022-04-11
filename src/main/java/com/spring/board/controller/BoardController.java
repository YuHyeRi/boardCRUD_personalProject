package com.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserVo;
import com.spring.common.CommonUtil;
import com.spring.common.bean.PagingBean;
import com.spring.common.service.PagingService;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// boardList.do (+ paging)
	@RequestMapping(value = "/board/boardList.do", method = {RequestMethod.GET, RequestMethod.POST})	// method 2개도 작성가능
	public String boardList(Model model, 																// 파라미터값()은 받아야 하는것을 씀
							PageVo pageVo) throws Exception {
		
		// 변수 선언 후 null로 값 초기화
		List<BoardVo> boardList = null;	
		
		// bean을 생성하지 않아 의존성 주입 대신 객체로 생성
		PagingService pagingService = new PagingService();
		
		// 변수 값 초기화
		int pageNo = 1;
		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0) {
			pageVo.setPageNo(pageNo);
		}
		
		int pageCnt = boardService.pageCnt(pageVo);				// pageCnt
		PagingBean pb = pagingService.getPagingBean(pageNo, pageCnt);
		
		String codeType = "menu";
		List<ComCodeVo> list = boardService.comCodeList(codeType);
		
		boardList = boardService.selectBoardList(pageVo);		// selectBoardList
		totalCnt = boardService.selectBoardCnt(pageVo);			// selectBoardCnt
		
		model.addAttribute("pb", pb);
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("list", list);
		
		return "board/boardList";
	}
	
	// boardView.do
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Model model
							,@PathVariable("boardType") String boardType				// @PathVariable : url 경로에 변수 넣어주기 위함
							,@PathVariable("boardNum") int boardNum) throws Exception {
		
		// System.out.println("=========> view boardType : " + boardType);
		// System.out.println("=========> view boardNum : " + boardNum);
		
		BoardVo boardVo = new BoardVo();
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	// boardWrite.do
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Model model) throws Exception {
		
		String codeType = "menu";	
		List<ComCodeVo> list = boardService.comCodeList(codeType);
		// System.out.println("=======> " + list.get(0).getCodeName());

		model.addAttribute("list", list);

		return "board/boardWrite";
	}
	
	// boardWriteAction.do
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(BoardVo boardVo) throws Exception {
		
		CommonUtil commonUtil = new CommonUtil();
		HashMap<String, String> result = new HashMap<String, String>();
		
		int resultCnt = boardService.boardInsert(boardVo);
		result.put("success", (resultCnt > 0) ? "Y" : "N");
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		System.out.println("callbackMsg::" + callbackMsg);
		
		return callbackMsg;
	}
	
	// boardUpdate.do
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardUpdate.do", method = RequestMethod.GET)
	public String boardUpdate(Model model, 
							ComCodeVo comCodeVo
							,@PathVariable("boardType") String boardType
							,@PathVariable("boardNum") int boardNum) throws Exception {
		
		BoardVo boardVo = new BoardVo();
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("board", boardVo);
		
		return "board/boardUpdate";
	}
	
	// boardUpdateAction.do
	@RequestMapping(value = "/board/boardUpdateAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardUpdateAction(BoardVo boardVo) throws Exception {
		
		CommonUtil commonUtil = new CommonUtil();
		HashMap<String, String> result = new HashMap<String, String>();
		
		int resultCnt = boardService.boardUpdate(boardVo);
		result.put("success", (resultCnt > 0) ? "Y" : "N");
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);
		System.out.println("callbackMsg ====> " + callbackMsg);
		
		return callbackMsg; 
	}
	
	// boardDel.do
	@RequestMapping(value = "/board/boardDel.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardDel(BoardVo boardVo) throws Exception {
		
		CommonUtil commonUtil = new CommonUtil();
		String result = "success";
		
		try {
			int resultCnt = boardService.boardDel(boardVo);
			
			if(resultCnt == 0) {
				result = "failed";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			result = "error";
		}
		return result;
	}
	
	// boardJoin.do
	@RequestMapping(value = "/board/boardJoin.do", method = RequestMethod.GET)
	public String boardJoin(Model model) throws Exception {
		
		String codeType = "phone";
		List<ComCodeVo> comCodeList = boardService.comCodeList(codeType);
		
		model.addAttribute("comCodeList", comCodeList);
		
		return "/board/boardJoin";
	}
	
	// boardJoinAction.do
	@RequestMapping(value = "/board/boardJoinAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardJoinAction(UserVo userVo) throws Exception {
		
		CommonUtil commonUtil = new CommonUtil();
		HashMap<String, String> joinList = new HashMap<String, String>();
		
		int joinListCnt = boardService.boardJoin(userVo);
		joinList.put("success", (joinListCnt > 0) ? "Y" : "N");
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ", joinList);
		
		return callbackMsg;
	}
	
	// idCheckAjax ==> 아이디 중복검사
	@RequestMapping(value = "board/idCheckAjax.do", method = RequestMethod.POST)
	@ResponseBody
	public String idCheckAjax(UserVo userVo,
							@RequestParam HashMap<String, String> params) throws Throwable {
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		int cnt = boardService.idCheck(userVo);
		
		modelMap.put("cnt", cnt);
		
		return mapper.writeValueAsString(modelMap);
		
	}
	
	// boardLogin.do
	@RequestMapping(value = "board/boardLogin.do", method = RequestMethod.GET)
	public String boardLogin() { 
		
		return "/board/boardLogin"; 
	}
	
	// boardLoginAction.do
	@RequestMapping(value = "/board/boardLoginAction.do", method = RequestMethod.GET)
	public String boardUserLogin(UserVo userVo, 
								Model model,
								HttpServletRequest req, 
								RedirectAttributes rttr
								) throws Exception {
		
		// user가 입력한 값을 userVo에 받고 userId, userPw 변수에 값을 대입
		String userId = userVo.getUserId();
		String userPw = userVo.getUserPw();
		
		// 세션 처리
		HttpSession session = req.getSession();
		
		// null값을 받아 처리하기 위한 try_catch문
		try {
			// userVo에서 받아온 id로 아이디를 찾은후 입력한 id와 pw를 비교
			userVo = boardService.boardLogin(userVo);
			
			// 값 비교 후 userId 와 userPw가 맞으면 boardList로 redirect
			if (userId.equals(userVo.getUserId()) && userPw.equals(userVo.getUserPw())) {
				String uesrIdCheck = userVo.getUserId();
				session.setAttribute("session", userVo);
				rttr.addFlashAttribute("result", uesrIdCheck);
				return "redirect:/board/boardList.do";
			} 
			// 아이디가 조회 되었어도 비밀번호가 틀렸을 경우
			else if (!userPw.equals(userVo.getUserPw())) {
				String userPwCheck = "userPw";
				model.addAttribute("result", userPwCheck);
			}
		// 아이디를 잘못 입력 하여 null값이 조회되었을때 예외 처리
		} catch (NullPointerException e) {
			String uesrIdCheck = "userId";
			model.addAttribute("result", uesrIdCheck);
		}
		
		// 정상 로그인 외에 return 되는 view
		return "board/boardLogin";
	}
    
    // boardLogout.do
	@RequestMapping(value = "/board/boardLogout.do", method = RequestMethod.GET)
	public String boardLogout(HttpSession session) throws Throwable {
		session.invalidate();
		return "redirect:/board/boardList.do";
	}
    
}	// class end
