package com.iu.s1.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig  implements WebMvcConfigurer{
	
	@Autowired
	private QnaInterceptor qnaInterceptor;
	
	@Autowired
	private QnaInterceptor2 qnaInterceptor2;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(qnaInterceptor2)
		.addPathPatterns("/qna/qnaUpdate")
		.addPathPatterns("/qna/qnaDelete");
		
		registry.addInterceptor(qnaInterceptor)
		.addPathPatterns("/qna/*")
		
		.excludePathPatterns("/qna/qnaList");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}	
	
	
}
