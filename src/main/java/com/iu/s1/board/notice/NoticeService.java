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
	public List<NoticeVO> getSelectList(/*Pageable pageable,String search,String kind*/Pager pager) throws Exception {
		//List<NoticeVO> ar = noticeRepository.findAll();
		//return ar;
		//페이징 처리할 때는 아래처럼(검색도 같이)
		//return noticeRepository.findByTitleContaining(search, pageable,kind); //"검색하고자 하는 것"
		pager.makeRow();
		pager.makePage(noticeRepository.count());
		Pageable pageable = PageRequest.of((int)pager.getStartRow(), pager.getPerPage(),Sort.Direction.DESC,"num");
		
		List<NoticeVO> ar = new ArrayList<NoticeVO>();
		
		if(pager.getKind().equals("writer")) {
			pager.makePage(noticeRepository.countByWriterContaining(pager.getSearch())); 
			ar =  noticeRepository.findByTitleContaining(pager.getSearch(), pageable);
			System.out.println(ar.size()+"ss");
			
		}else if(pager.getKind().equals("contents")){
			pager.makePage(noticeRepository.countByContentsContaining(pager.getSearch()));
			ar = noticeRepository.findByContentsContaining(pager.getSearch(), pageable);
			
		}else {
			pager.makePage(noticeRepository.countByTitleContaining(pager.getSearch()));
			ar= noticeRepository.findByTitleContaining(pager.getSearch(), pageable);
		}
		return ar;
	}
		
	
	
	
	public NoticeVO getSelectOne(NoticeVO noticeVO) throws Exception{
		Optional<NoticeVO> opt = noticeRepository.findById(noticeVO.getNum());
		noticeVO = opt.get();
		return noticeVO;
	}
	
	
	public NoticeVO setInsert(NoticeVO noticeVO,MultipartFile[] files) throws Exception{
		
		File file =pathGenerator.getUseClassPathResource(filePath);
		
		noticeVO = noticeRepository.save(noticeVO);
		
		for(MultipartFile multipartFile : files) {
			if(multipartFile.getSize()<=0) {
				continue;
			}
			
		String fileName = fileManager.saveFileCopy(multipartFile, file);
		NoticeFileVO noticeFileVO = new NoticeFileVO();
		noticeFileVO.setFileName(fileName);
		noticeFileVO.setOriName(multipartFile.getOriginalFilename());
		
		
		noticeFileVO = noticeFileRepository.save(noticeFileVO);
		
		
		
	}
		noticeVO=noticeRepository.save(noticeVO);
		return noticeVO;


	}
}

