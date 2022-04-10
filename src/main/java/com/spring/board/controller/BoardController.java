package com.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import com.spring.common.service.IPagingService;
import com.spring.common.service.PagingService;


@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	PagingService pagingService = new PagingService();

	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// boardList.do
	@RequestMapping(value="/board/boardList.do", method= {RequestMethod.GET, RequestMethod.POST})		// method 2개 작성 가능
	public String boardList(Locale locale, Model model, PageVo pageVo) throws Exception {
		
		List<BoardVo> boardList = null;	// 변수 선언 후 값이 업다고 초기화
		
		int page = 1;
		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);
		}
		
		PagingBean pb = pagingService.getPagingBean(page, totalCnt);
		model.addAttribute("startCnt", Integer.toString(pb.getStartCount()));
		model.addAttribute("endCnt", Integer.toString(pb.getEndCount()));
		
		String codeType = "menu";
		List<ComCodeVo> list = boardService.comCodeList(codeType);	// 0119 추가
		
		boardList = boardService.selectBoardList(pageVo);	// selectBoardList
		totalCnt = boardService.selectBoardCnt(pageVo);			// selectBoardCnt
		
		// System.out.println("CheckNo ===========> " + pageVo.getCheckNo());
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		model.addAttribute("list", list);	// 0119 추가
		
		return "board/boardList";			// 보여줄 view
	}
	
	// boardView.do
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method=RequestMethod.GET)
	public String boardView(Locale locale, Model model
							,@PathVariable("boardType") String boardType
							,@PathVariable("boardNum") int boardNum) throws Exception {
		
		// System.out.println("=========> view boardType : " + boardType);
		// System.out.println("=========> view boardNum : " + boardNum);
		
		BoardVo boardVo = new BoardVo();
		
		boardVo = boardService.selectBoard(boardType,boardNum);	// selectBoard
		
		// model.addAttribute("boardType", boardType);
		// model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	// boardWrite.do
	@RequestMapping(value="/board/boardWrite.do", method=RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception {	// ()안에 있는 값은 받아야 하는 걸 쓴다.
		
		String codeType = "menu";	
		List<ComCodeVo> list = boardService.comCodeList(codeType);	// comCodeList (추가)
		// System.out.println("=======> " + list.get(0).getCodeName());

		model.addAttribute("list", list);	// 추가

		return "board/boardWrite";
	}
	
	// boardWriteAction.do
	@RequestMapping(value="/board/boardWriteAction.do", method=RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale, BoardVo boardVo) throws Exception {
		
		HashMap<String, String> result = new HashMap<String, String>();
		
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardInsert(boardVo);	// boardInsert
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	// boardUpdate.do
	@RequestMapping(value="/board/{boardType}/{boardNum}/boardUpdate.do", method=RequestMethod.GET)
	public String boardUpdate(Locale locale, Model model, ComCodeVo comCodeVo
								,@PathVariable("boardType") String boardType
								,@PathVariable("boardNum") int boardNum) throws Exception {
		
		BoardVo boardVo = new BoardVo();
		
		boardVo = boardService.selectBoard(boardType,boardNum);	// selectBoard
		
		// List<ComCodeVo> list = boardService.comCodeList(comCodeVo);	// comCodeList (추가)
		
		model.addAttribute("board", boardVo);
		// model.addAttribute("list", list);	// 추가
		
		return "board/boardUpdate";
	}
	
	// boardUpdateAction.do
	@RequestMapping(value="/board/boardUpdateAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardUpdateAction(Locale locale, BoardVo boardVo) throws Exception {
		
		HashMap<String, String> result = new HashMap<String, String>();
		
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardUpdate(boardVo);
		result.put("success", (resultCnt > 0)? "Y" : "N");
		
		System.out.println("update boardNum =============> " + boardVo.getBoardNum());
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ", result);
		System.out.println("callbackMsg ====> " + callbackMsg);
		
		return callbackMsg; 
	}
	
	// boardDel.do
	@RequestMapping(value="/board/boardDel.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardDel(BoardVo boardVo) throws Exception {
		CommonUtil commonUtil = new CommonUtil();
		String result = "success";
		
		try {
			int resultCnt = boardService.boardDel(boardVo);
			
			System.out.println("resultCnt-> " + resultCnt);
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
	@RequestMapping(value="/board/boardJoin.do", method = RequestMethod.GET)
	public String boardJoin(Locale locale, Model model) throws Exception {
		
		String codeType = "phone";
		List<ComCodeVo> comCodeList = boardService.comCodeList(codeType);
		
		model.addAttribute("comCodeList", comCodeList);
		
		return "/board/boardJoin";
	}
	
	// boardJoinAction.do
	@RequestMapping(value="/board/boardJoinAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardJoinAction(Locale locale, UserVo userVo) throws Exception {
		
		HashMap<String, String> joinList = new HashMap<String, String>();
		
		CommonUtil commonUtil = new CommonUtil();
		
		int joinListCnt = boardService.boardJoin(userVo);
		joinList.put("success", (joinListCnt > 0)? "Y" : "N");
		
		String callbackMsg = commonUtil.getJsonCallBackString(" ", joinList);
		
		return callbackMsg;
	}
	
	// idCheckAjax : 아이디 중복검사
	@RequestMapping(value="board/idCheckAjax.do", method=RequestMethod.POST)
	@ResponseBody
	public String idCheckAjax(@RequestParam HashMap<String, String> params, UserVo userVo) throws Throwable {
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		int cnt = boardService.idCheck(userVo);
		
		modelMap.put("cnt", cnt);
		
		return mapper.writeValueAsString(modelMap);
		
	}
	
	// boardLogin.do
	@RequestMapping(value="board/boardLogin.do", method = RequestMethod.GET)
	public String boardLogin() { 
		
		return "/board/boardLogin"; 
		
	}
	
	// boardLoginAction.do
	@RequestMapping(value="/board/boardLoginAction.do", method = RequestMethod.GET)
	public String boardUserLogin(UserVo userVo, 
								HttpServletRequest req, 
								RedirectAttributes rttr, 
								Model model) throws Exception {
		
		String userId = userVo.getUserId();
		String userPw = userVo.getUserPw();
		
		HttpSession session = req.getSession();
		
		// try catch (null 값 처리 위해)
		try {
			userVo = boardService.boardLogin(userVo);
			
			if (userId.equals(userVo.getUserId()) && userPw.equals(userVo.getUserPw())) {
				
				String uesrIdCheck = userVo.getUserId();
				
				session.setAttribute("session", userVo);
				rttr.addFlashAttribute("result", uesrIdCheck);
				
				return "redirect:/board/boardList.do";
			} else if (!userPw.equals(userVo.getUserPw())) {
				String userPwCheck = "userPw";
				model.addAttribute("result", userPwCheck);
			}
		// 아이디 잘못 입력 하여 null값이 조회되었을때 예외처리
		} catch (NullPointerException e) {
			String uesrIdCheck = "userId";
			model.addAttribute("result", uesrIdCheck);
		}
		
		return "board/boardLogin";
	}
    
    // boardLogout.do
	@RequestMapping(value="/board/boardLogout.do", method = RequestMethod.GET)
	public String boardLogout(HttpSession session) throws Throwable {
		
		session.invalidate();
		
		return "redirect:/board/boardList.do";
	}
    
   

}	// class end
