package com.iu.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iu.s1.member.MemberVO;

@Component
public class QnaInterceptor extends HandlerInterceptorAdapter{
	
	//qna list 누구나 접근
	//select, write , reply 회원들만 접근 가능
	//update ,delete 작성자만 가능
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		boolean check = false;
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		String url = request.getRequestURI();
		String [] ar = url.split("/");
		
		for(int  i=0;i<ar.length;i++) {
			System.out.println(ar[1]);
		}
		
		if(ar[1].equals("qna")) {
			if(memberVO !=null) {
				check=true;
				System.out.println("회원입니다.");
			}else {
				System.out.println("로그인을 먼저 해주세요.");
				 response.sendRedirect("../member/memberLogin");
			}
		}
		
		return check;
	}
	
}
