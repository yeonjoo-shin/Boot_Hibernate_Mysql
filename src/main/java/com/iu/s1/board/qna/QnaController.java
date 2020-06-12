package com.iu.s1.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s1.board.BoardVO;
import com.iu.s1.util.Pager;

@Controller
@RequestMapping("/qna/**/")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	//update 첨부파일은 유지
	@GetMapping("qnaUpdate")
	public ModelAndView boardUpdate(QnaVO qnaVO, ModelAndView mv) throws Exception{
		qnaVO=qnaService.boardSelect(qnaVO);
		
		mv.addObject("boardVO",qnaVO);
		mv.addObject("path","Update");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaUpdate")
	public ModelAndView boardUpdate(QnaVO qnaVO,MultipartFile[] files) throws Exception{
		ModelAndView mv = new ModelAndView();
		qnaService.boardUpdate(qnaVO,files);
		mv.setViewName("redirect:./qnaList");
		return mv;
	}
	
	@GetMapping("qnaDelete")
	public ModelAndView boardDelete(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		boolean result = qnaService.boardDelete(qnaVO);
		if(!result) {
			
		}
		mv.setViewName("redirect:./qnaList");
		return mv;
	}
	
	
	@GetMapping("qnaReply")
	public ModelAndView boardReply(ModelAndView mv, BoardVO boardVO) throws Exception{
		mv.addObject("boardVO",boardVO);
		mv.setViewName("board/boardWrite");
		mv.addObject("path","Reply");
		return mv;
	}
	
	@PostMapping("qnaReply")
	public ModelAndView boardReply(ModelAndView mv , QnaVO qnaVO) throws Exception{
		qnaVO=qnaService.boardReply(qnaVO);
		mv.setViewName("redirect:./qnaList");	
		
		return mv;
	}
	

	
	
	@GetMapping("qnaSelect")
	public ModelAndView boardSelect(ModelAndView mv, QnaVO qnaVO) throws Exception{
		qnaVO=qnaService.boardhit(qnaVO);//조회수 증가
		qnaVO=qnaService.boardSelect(qnaVO);
		mv.addObject("vo",qnaVO);
		mv.setViewName("board/boardSelect");
		return mv;
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
	public ModelAndView boardWrite(ModelAndView mv,QnaVO qnaVO,MultipartFile[] files) throws Exception{
		
		qnaVO = qnaService.boardWrite(qnaVO,files);

		mv.setViewName("redirect:./qnaList");
		
		return mv;
		
	}
	
	@GetMapping("qnaList")
	public ModelAndView boardList(Pager pager/*@PageableDefault(size = 10,page = 0,direction = Direction.DESC,sort = {"num"}) Pageable pageable, ModelAndView mv,@RequestParam(defaultValue = "") String search,String kind*/) throws Exception{
		// pager로 받으므로 jsp에서 curPage로 받아야함.이전에 pageable에서 page로 받았으므로 그냥하면 안돌아감
		//Page<QnaVO> page= qnaService.boardList(pageable,search,kind);
		/*
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
		*/
		ModelAndView mv = new ModelAndView();
		Page<QnaVO> page= qnaService.boardList(pager);
		
		mv.addObject("page",page);
		mv.addObject("pager",pager);
		mv.setViewName("board/boardList");
		
		return mv;
	}
	
}
