package com.iu.s1.util;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FilePathGenerator {
	//파일의 경로를 가지고 오기위한 클래스
	//실제경로를 file객체에 담아서 리턴
	//매개변수로는 String으로 upload/notice같은 것을 받아올 것임
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private ServletContext servletContext;
	
	//static/upload/notice
	//static/upload/qna
	public File getUserResourceLoader(String path,MultipartFile files) throws Exception{
		//ResourceLoader
		//classes 경로를 받아오기 위해서 사용
		//생성하려는 디렉토리가 없으면 에러를 발생시킴
		//default로 만들어진 static 를 이용해서 file객체를 생성
		//하위 디렉토리를 child로 사용해서 디렉토리 생성
		
		String defaultPath="classpath:/static/";
		File file = resourceLoader.getResource(defaultPath).getFile();//파일객체로 리턴해주겠다. 
		
		//System.out.println(file.getName()); 중간 확인 static 찍힘.
		
		file = new File(file,path);//static,notice/qna를 파일 객체로 만들겟다.
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
	
		System.out.println(file.getAbsolutePath());
		return file;
	}
	
	
	
	public File getUseClassPathResource(String path) throws Exception{
		//classPathResource
		//classes 경로를 받아오기 위해 사용
		//resourceLoader와 같지만
		//시작 경로에 classpath를 쓰지않음
		//개발자가 직접 객체를 생성하는 방식
		
		String defaultPath ="static";
		//객체 생성
		ClassPathResource classPathResource = new ClassPathResource(defaultPath);//경로명을 만들어서 넣어줘야함.
		
		File file = classPathResource.getFile(); //file은 static
		file = new File(file,path); //path는 실제경로 upload/notice
		
		if(!file.exists()) {
			file.mkdirs();
		}
		System.out.println(file.getAbsolutePath());
		return file;
	}
	
	
	public File getUseServletContext(String path) throws Exception{
		//servlet context
		//classpath가 아니라 webapp 하위에 만들어짐
		//생성할 디렉토리가 static이라면
		//webapp하위에 static 폴더가 하나더 만들어짐
		
		path = servletContext.getRealPath(path);
		
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		System.out.println(path);
		return file;
		
	}
	
	
	
	
	
}
