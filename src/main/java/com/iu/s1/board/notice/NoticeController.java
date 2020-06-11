package com.iu.s1.board.notice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.iu.s1.member.MemberVO;
import com.iu.s1.util.Pager;

@Controller
@RequestMapping("/notice/**/")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@GetMapping("noticeList")
	public ModelAndView getSelectList(Pager pager/*@PageableDefault(page = 0,size = 10,sort = {"num"},direction =Direction.DESC ) Pageable pageable,@RequestParam(defaultValue = "") String search,String kind*/) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		//pageable =PageRequest.of(0, 10,Sort.Direction.DESC,"num");// 페이지번호(=curPage), 몇개씩, 정렬desc, 무엇을 기준으로?
		
		//List<NoticeVO> ar = noticeService.getSelectList(pageable,search,kind);
		List<NoticeVO> ar = noticeService.getSelectList(pager);
		System.out.println(ar.size()+"cc");
		
		mv.addObject("list",ar);
		mv.setViewName("board/boardList");
		
		//List<NoticeVO> ar = noticeService.getSelectList();
		//System.out.println(ar.size()+" : size");
		//mv.addObject("list",ar);
		//mv.setViewName("board/boardList");
		
		return mv;
	}
	
	@GetMapping("noticeSelect")
	public ModelAndView getSelectOne(ModelAndView mv,NoticeVO noticeVO) throws Exception{
		 noticeVO = noticeService.getSelectOne(noticeVO);
		 mv.addObject("vo",noticeVO);
		 mv.setViewName("board/boardSelect");
		return mv;
		
	}
	
	@GetMapping("noticeWrite")
	public ModelAndView noticeWrite(ModelAndView mv, HttpSession session)throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		BoardVO boardVO = new BoardVO();
		
		boardVO.setWriter(memberVO.getId());
		
		mv.addObject("boardVO",boardVO);
		mv.addObject("path","Write");
		mv.setViewName("board/boardWrite");
		
		return mv;
	}
	
	@PostMapping("noticeWrite")
	public ModelAndView noticeWrite(ModelAndView mv,MultipartFile[] files) throws Exception{
		NoticeVO noticeVO = new NoticeVO();
		
		noticeVO = noticeService.setInsert(noticeVO, files);
		if(noticeVO!=null) {
			
			mv.setViewName("redirect:noticeList");
		}else {
			System.out.println("실패");
		}
		return mv;
	}
}
