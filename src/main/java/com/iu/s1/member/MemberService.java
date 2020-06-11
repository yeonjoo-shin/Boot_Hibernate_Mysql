package com.iu.s1.member;

import java.io.File;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.member.memberFile.MemberFileRepository;
import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;


@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberFileRepository memberFileRepository;
	
	@Autowired
	private FilePathGenerator filePathGenerator;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${board.member.filePath}")
	private String filePath;
	
	public MemberVO memberSelect(MemberVO memberVO) throws Exception{
		memberVO=memberRepository.findByid(memberVO.getId());
		return memberVO;
	}
	
	public void memberDelete(MemberVO memberVO) throws Exception{
		memberRepository.deleteById(memberVO.getId());
	}
	
	
	public MemberVO memberUpdate(MemberVO memberVO) throws Exception{
		memberVO=memberRepository.save(memberVO);
		return memberVO;
	}
	
	
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		memberVO=memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());
		return memberVO;
	}
	
	
	//회원가입 //save 내장메서드
	public MemberVO memberJoin(MemberVO memberVO,MultipartFile files)throws Exception{

	
		File file = filePathGenerator.getUseClassPathResource(filePath);
		System.out.println(file + " : file service"); //o
		
		String fileName = fileManager.saveFileCopy(files, file);
		System.out.println(fileName + " : sevice filename");
		
		MemberFileVO memberFileVO = new MemberFileVO();
		
		memberFileVO.setFileName(fileName);
		memberFileVO.setOriName(files.getOriginalFilename());
		
		memberVO.setMemberFileVO(memberFileVO);
		memberFileVO.setMemberVO(memberVO);
		
		//2. 파일명을 db에 저장
		
		memberVO = memberRepository.save(memberVO);
		
		return memberVO;
	}
	
	public boolean memberError(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean result = false;
		
		//1.기본제공 검증
		if(bindingResult.hasErrors()) {
			result=true;
		}
			//2.pw 검증
			if(!memberVO.getPw().equals(memberVO.getPwCheck())) {
				bindingResult.rejectValue("pwCheck", "memberVO.password.notSame");
				result = true;
			}
			
			//3.id 검증
			boolean check = memberRepository.existsById(memberVO.getId());
			if(check) {
				bindingResult.rejectValue("id", "memberVO.id.same");
				result= true;
			}
		
		return result;
	}
}
