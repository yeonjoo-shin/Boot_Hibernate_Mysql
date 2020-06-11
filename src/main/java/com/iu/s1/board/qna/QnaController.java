package com.iu.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
	public ModelAndView boardList(@PageableDefault(size = 10,page = 0,direction = Direction.DESC,sort = {"num"}) Pageable pageable, ModelAndView mv) throws Exception{
		Page<QnaVO> page= qnaService.boardList(pageable);
		
		System.out.println(page.getContent().size());
		System.out.println(page.getSize()); //몇개씩 볼것인가 
		System.out.println(page.getTotalElements() +" : elements");//총 글의 갯수 205개
		System.out.println(page.getTotalPages()+" : totalPages"); //21개
		System.out.println(page.hasNext()+" : next"); //더보기 , more할때 주로 사용
		System.out.println(page.hasPrevious()+" : previous");
		System.out.println(page.getNumber()+": number");//페이지 번호
		System.out.println(page.hasContent());
		System.out.println(page.isFirst()+": first");
		System.out.println(page.isLast()+": last");
		
		mv.addObject("page",page);
		mv.setViewName("board/boardList");
		
		return mv;
	}
	
}
