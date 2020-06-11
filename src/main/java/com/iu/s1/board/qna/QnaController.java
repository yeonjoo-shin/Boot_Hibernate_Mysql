package com.iu.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s1.board.BoardVO;

@Controller
@RequestMapping("/qna/**/")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	@GetMapping("qnaWrite")
	public ModelAndView boardWrite() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardVO",new BoardVO());
		mv.addObject("path","Write");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaWrite")
	public ModelAndView boardWrite(ModelAndView mv) throws Exception{
		QnaVO qnaVO = new QnaVO();
		
		qnaVO = qnaService.boardWrite(qnaVO);
		if(qnaVO!=null) {

			mv.setViewName("redirect:qnaList");
		}else {
			System.out.println("실패");
		}
		return mv;
		
	}
	
	@GetMapping("qnaList")
	public ModelAndView boardList(ModelAndView mv) throws Exception{
		List<QnaVO> ar = qnaService.boardList();
		mv.addObject("list",ar);
		mv.setViewName("board/boardList");
		
		return mv;
	}
	
}
