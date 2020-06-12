package com.iu.s1.board.notice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s1.board.notice.noticeFile.NoticeFileRepository;
import com.iu.s1.board.notice.noticeFile.NoticeFileVO;
import com.iu.s1.board.qna.QnaVO;
import com.iu.s1.util.FileManager;
import com.iu.s1.util.FilePathGenerator;
import com.iu.s1.util.Pager;




@Service
public class NoticeService  {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private NoticeFileRepository noticeFileRepository;
	
	@Autowired
	private FilePathGenerator pathGenerator;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${board.notice.filePath}") 
	private String filePath;
	
	//pager없을 땐느 매개변수없어도됨
	public Page<NoticeVO> getSelectList(/*Pageable pageable,String search,String kind*/Pager pager) throws Exception {
		//List<NoticeVO> ar = noticeRepository.findAll();
		//return ar;
		//페이징 처리할 때는 아래처럼(검색도 같이)
		//return noticeRepository.findByTitleContaining(search, pageable,kind); //"검색하고자 하는 것"
		pager.makeRow();
		
		Pageable pageable = PageRequest.of(pager.getStartRow(), pager.getSize(),Sort.Direction.DESC,"num");
		
		//List<NoticeVO> ar = new ArrayList<NoticeVO>();
		
		//Pageable pageable = PageRequest.of(pager.getStartRow(), pager.getSize(),Sort.by("ref").descending().and(Sort.by("step").ascending()));
		
		Page<NoticeVO> page = null; //전체페이지의 갯수가 이미 계산되어서 들어가있음
		
		if(pager.getKind().equals("title")) {
			page = noticeRepository.findByTitleContaining(pager.getSearch(), pageable);
		}else if(pager.getKind().equals("contents")) {
			page = noticeRepository.findByContentsContaining(pager.getSearch(), pageable);
		}else {
			page = noticeRepository.findByWriterContaining(pager.getSearch(), pageable);
		}
		
		pager.makePage(page.getTotalPages());
		
		return page;
	}
		
	
	
	
	public NoticeVO getSelectOne(NoticeVO noticeVO) throws Exception{
		Optional<NoticeVO> opt = noticeRepository.findById(noticeVO.getNum());
		noticeVO = opt.get();
		return noticeVO;
	}
	
	
	public NoticeVO setInsert(NoticeVO noticeVO,MultipartFile[] files) throws Exception{
		
		File file =pathGenerator.getUseClassPathResource(filePath);
		
		System.out.println("file >" + file);
		
		noticeVO = noticeRepository.save(noticeVO);
		
		for(MultipartFile multipartFile : files) {
			if(multipartFile.getSize()<=0) {
				
				continue;
				
			}
			
		System.out.println(multipartFile.getName() +"multi");	
		String fileName = fileManager.saveFileCopy(multipartFile, file);
		System.out.println("filename :" +fileName);
		
		NoticeFileVO noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName(fileName);
		noticeFileVO.setOriName(multipartFile.getOriginalFilename());
		
		
		noticeFileVO = noticeFileRepository.save(noticeFileVO);
		
		
		
	}
		noticeVO=noticeRepository.save(noticeVO);
		return noticeVO;


	}
}

