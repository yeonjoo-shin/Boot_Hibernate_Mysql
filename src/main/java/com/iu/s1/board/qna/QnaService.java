package com.iu.s1.board.qna;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.iu.s1.util.Pager;

@Service
@Transactional
public class QnaService {
	@Autowired
	private QnaRepository qnaRepository;
	
	public QnaVO boardWrite(QnaVO qnaVO) throws Exception{
		//ref 0인 상태
		qnaVO.setRef(0L);
		qnaVO.setStep(0L);
		qnaVO.setDepth(0L);
		qnaVO = qnaRepository.save(qnaVO);
		//insert
		qnaVO.setRef(qnaVO.getNum());
		//다시 업데이트
		return qnaRepository.save(qnaVO);
	}
	
	public Page<QnaVO> boardList(Pager pager/*Pageable pageable,String search,String kind*/) throws Exception{
		/*
		Page<QnaVO> page= qnaRepository.findByTitleContaining(search, pageable);
		
		//kind.equals(null) // 애초에 kind가 올때 null.equals로 오기 때문에 null.equals=null이냐고 묻는 것이라서 애초에 null에러가 뜸
		if(kind == null) {
			page = qnaRepository.findAll(pageable);
		}else {
			if(kind.equals("title")) {
				 page=qnaRepository.findByTitleContaining(search, pageable);
			}else if(kind.equals("writer")){
				page = qnaRepository.findByWriterContaining(search, pageable);
			}else {
				page = qnaRepository.findByContentsContaining(search, pageable);
			}
		}*/
		
		pager.makeRow();
		Pageable pageable = PageRequest.of((int)pager.getStartRow(), pager.getPerPage(),Sort.by("ref").descending().and(Sort.by("step").ascending()));
		Page<QnaVO> page = null;
		if(pager.getKind().equals("title")) {
			page = qnaRepository.findByTitleContaining(pager.getSearch(), pageable);
		}else if(pager.getKind().equals("contents")) {
			page = qnaRepository.findByContentsContaining(pager.getSearch(), pageable);
		}else {
			page = qnaRepository.findByWriterContaining(pager.getSearch(), pageable);
		}
		
		
		return page;
	}
}
