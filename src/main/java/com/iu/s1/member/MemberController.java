package com.iu.s1.member;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**/")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("memberDelete")
	public String memberDelete(MemberVO memberVO, ModelAndView mv, HttpSession session) throws Exception{
		memberVO=(MemberVO)session.getAttribute("member");
		memberService.memberDelete(memberVO);
		
		return "redirect:../";
	}
	
	@GetMapping("memberPage")
	public void memberPage() throws Exception{
		
	}
	
	
	@GetMapping("memberUpdate")
	public void memberUpdate(MemberVO memberVO) throws Exception{
		
	}
	
	//select한 값을 받아서 덮어씌워서
	//update값이 하나라도 안넘어오면 null이 됨
	@PostMapping("memberUpdate")
	public ModelAndView memberUpdate(MemberVO memberVO,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		String id = ((MemberVO)session.getAttribute("member")).getId();
		memberVO.setId(id);
		
		//memberVO=memberService.memberSelect(memberVO);
		memberVO = memberService.memberUpdate(memberVO);
		
		if(memberVO!=null) {
			session.setAttribute("member",memberVO);
			mv.setViewName("redirect:./memberPage");
		}else {
			mv.addObject("result", "Update Fail");
			 mv.addObject("path", "./memberPage");
			 mv.setViewName("common/result");
		}
		
		return mv;
	}
	
	

	
	@GetMapping("memberLogout")
	public String memberLogout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:../";
	}
		
	
	@GetMapping("memberLogin")
	public String memberLogin(MemberVO memberVO) throws Exception{
		return "member/memberLogin";
	}
	
	@PostMapping("memberLogin")
	public ModelAndView memberLogin(MemberVO memberVO, ModelAndView mv,HttpSession session) throws Exception{
		memberVO=memberService.memberLogin(memberVO);
		
		if(memberVO!=null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}else {
			mv.addObject("result","login fail");
			mv.addObject("path","./memberJoin");
			
			mv.setViewName("member/memberJoin");
		}
		return mv;
	}
	
	
	
	
	
	
	
	@GetMapping("memberJoin")
	public ModelAndView memberJoin(MemberVO memberVO,ModelAndView mv)throws Exception{
		mv.setViewName("member/memberJoin");
		mv.addObject("memberVO",memberVO);
		
		return mv;
	}
	
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(@Valid MemberVO memberVO,BindingResult bindingResult ,ModelAndView mv,MultipartFile files) throws Exception{
		
		System.out.println(memberVO.getId() +"con");
		System.out.println(memberVO.getMemberFileVO()+ "mcon"); //null이 들어옴
		
		boolean result = memberService.memberError(memberVO, bindingResult);
		
		if(result) {
			mv.addObject("member/memberJoin");
		}else {
			memberVO = memberService.memberJoin(memberVO,files);
			
			if(memberVO!=null) {
				
				mv.setViewName("redirect:../");
			}else {
				System.out.println("중복 오류");
			}
			
		}
		
		return mv;
	}

}
