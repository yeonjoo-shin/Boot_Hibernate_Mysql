package com.iu.s1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iu.s1.board.BoardVO;
import com.iu.s1.board.qna.QnaRepository;
import com.iu.s1.board.qna.QnaVO;
import com.iu.s1.member.MemberVO;

@Component
public class QnaInterceptor2  extends HandlerInterceptorAdapter{
	@Autowired
	private QnaRepository qnaRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String method = request.getMethod();
		
		if(method.equals("GET")) {
			MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
			
			//파라미터로 글의 번호를 꺼내기
			int num = (Integer.parseInt(request.getParameter("num")));
			QnaVO qnaVO = new QnaVO(); 
			qnaVO.setNum(num); 
			qnaVO = qnaRepository.qnaSelect((long)num);
	
			
			if(memberVO !=null && qnaVO.getWriter().equals(memberVO.getId())) {
				return true;
			}else {
				response.sendRedirect("/");
				return false;
			}
		}else {
			return true;
		}
		
		
	}
}
