package com.iu.s1.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


@Component
public class FileManager {
	//실제 저장된것 리턴
	
	public String saveTransfer(MultipartFile file,File dest)throws Exception{
		String fileName = "";
		fileName = UUID.randomUUID().toString();//파일이름은랜덤으로 만들어서 스트링으로 만들어주세요.
		
		fileName = fileName + "_" + file.getOriginalFilename();
		
		dest = new File(dest,fileName);//dest 라는 경로 폴더명(static/~/notice)에   fileName으로 trnasfer
		
		file.transferTo(dest);
		
		
		return fileName;
	}
	
	public String saveFileCopy(MultipartFile file, File dest) throws Exception{
		String fileName="";
		fileName = UUID.randomUUID().toString();
		
		fileName = fileName + "_" + file.getOriginalFilename();
		
		dest = new File(dest, fileName);
		
		FileCopyUtils.copy(file.getBytes(), dest);
		
		return fileName;
	}
	/*
	public String saveFileCopy(MultipartFile file,File dest)throws Exception{
		String fileName = "";
		fileName = UUID.randomUUID().toString();//파일이름은랜덤으로 만들어서 스트링으로 만들어주세요.
		
		fileName = fileName + "_" + file.getOriginalFilename();
		
		dest = new File(dest,fileName);//dest라는 경로 폴더명에  fileName으로 trnasfer
		
		FileCopyUtils.copy(file.getBytes(),dest); //소스들,목적지
		
		
		return fileName;
	}*/
}
