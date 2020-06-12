package com.iu.s1.board.notice;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.iu.s1.member.MemberVO;
import com.iu.s1.util.Pager;

import javassist.expr.NewArray;

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
		Page<NoticeVO> page = noticeService.getSelectList(pager);
		System.out.println("ar ; " +page.getTotalElements());
		
		//mv.addObject("list",ar);
		mv.addObject("pager",pager);
		mv.addObject("page",page);
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
	public ModelAndView noticeWrite(ModelAndView mv)throws Exception{
		//MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		
		
		//boardVO.setWriter(memberVO.getId());
		
		mv.addObject("boardVO",new BoardVO());
		mv.addObject("path","Write");
		mv.setViewName("board/boardWrite");
		
		return mv;
	}
	
	@PostMapping("noticeWrite")
	public ModelAndView noticeWrite(ModelAndView mv,MultipartFile[] files,NoticeVO noticeVO) throws Exception{		
		// 새로 생성하면 안됨. 매개변수로 받아야지 정보를 받아오지  새로생성하면 당연히 널이지 바보야 ㅜㅜㅜㅜㅠㅜㅜㅜ
		noticeVO = noticeService.setInsert(noticeVO, files);
		System.out.println("c : " +noticeVO.getTitle());//null
		
			
		mv.setViewName("redirect:noticeList");
		
		return mv;
	}
}
